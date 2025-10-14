package com.ecommerce.project.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class TestAWS {


    private final S3Client s3Client;
    private final AWSS3Service aWSS3Service;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("keyName") String keyName) {

        URL savedFile = aWSS3Service.uploadFile(keyName, file);
        return ResponseEntity.ok("SAVED SUCCESSFULLY" + savedFile.toString());

    }


}
