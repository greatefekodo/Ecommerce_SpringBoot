package com.ecommerce.project.service;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO, User user);


    List<AddressDTO> getAddresses();

   AddressDTO getAddressById(Long adrressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddressById(Long adrressId, AddressDTO addressDTO);

    String deleteAddress(Long adrressId);
}
