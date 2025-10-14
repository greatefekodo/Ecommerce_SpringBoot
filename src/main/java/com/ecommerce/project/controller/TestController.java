package com.ecommerce.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://ecom-development-frontend.s3-website-us-east-1.amazonaws.com")
public class TestController {

    @GetMapping("/api/test-cors")
    public String test() {
        return "CORS is working!";
    }
}
