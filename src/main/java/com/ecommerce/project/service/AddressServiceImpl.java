package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO, User user) {
        Address address = modelMapper.map(addressDTO, Address.class);

        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);

        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getAddresses() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(address -> modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO getAddressById(Long adrressId) {
        Address addresses = addressRepository.findById(adrressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "adrressId", adrressId));

        return modelMapper.map(addresses, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> getUserAddresses(User user) {
       List<Address> address = user.getAddresses();
        return address.stream()
                .map(add -> modelMapper.map(add, AddressDTO.class))
                .toList();


    }

    @Override
    public AddressDTO updateAddressById(Long adrressId, AddressDTO addressDTO) {
        Address addressFromDatabase = addressRepository.findById(adrressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "adrressId", adrressId));

        addressFromDatabase.setBuildingName(addressDTO.getBuildingName());
        addressFromDatabase.setCity(addressDTO.getCity());
        addressFromDatabase.setPincode(addressDTO.getPincode());
        addressFromDatabase.setStreet(addressDTO.getStreet());
        addressFromDatabase.setState(addressDTO.getState());
        addressFromDatabase.setCountry(addressDTO.getCountry());

        Address updatedAddress = addressRepository.save(addressFromDatabase);

        User user = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address ->  address.getAddressId().equals(adrressId));
        user.getAddresses().add(updatedAddress);
        userRepository.save(user);


        return modelMapper.map(updatedAddress, AddressDTO.class);

    }

    @Override
    public String deleteAddress(Long adrressId) {
        Address addressFromDatabase = addressRepository.findById(adrressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "adrressId", adrressId));
        addressRepository.delete(addressFromDatabase);

        User user = addressFromDatabase.getUser();
        user.getAddresses().removeIf(address -> address.getAddressId().equals(adrressId));
        userRepository.save(user);
        return "Address deleted successfully with address id: " + adrressId;
    }


}
