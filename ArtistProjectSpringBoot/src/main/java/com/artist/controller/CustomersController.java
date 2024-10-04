package com.artist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.CustomersDTO;
import com.artist.service.impl.CustomersServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersServiceImpl csi;

    //註冊
    @PostMapping("/register")
    public ResponseEntity<?> createCustomer(@RequestBody CustomersDTO customersDTO) {
    	csi.create(customersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    //登入
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CustomersDTO customersDTO) {
        String token = csi.login(customersDTO.getEmail(), customersDTO.getPassword());
        return ResponseEntity.ok((token));
    }
    
    
}
