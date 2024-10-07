package com.artist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.CustomersDTO;
import com.artist.dto.LoginResponse;
import com.artist.entity.Customers;
import com.artist.service.impl.CustomersServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersServiceImpl csi;

    // 註冊
    @PostMapping(value ="/register", consumes = "application/json")
    public ResponseEntity<?> createCustomer(@RequestBody CustomersDTO customersDTO) {
    	csi.create(customersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
    //登入
    @PostMapping(value ="/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody CustomersDTO customersDTO) {
        String token = csi.login(customersDTO.getEmail(), customersDTO.getPassword());
		String customerId = csi.getCustomerIdFromToken(token);
		Customers customer = csi.getByCustomerId(customerId);
        String nickName = customer.getNickName();
        LoginResponse response = new LoginResponse(token, nickName);
        return ResponseEntity.ok((response));
    }
    // 刷新 token
    @PostMapping("/token/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam String token) {
        String newToken = csi.refreshToken(token);
        if (newToken != null) {
            return ResponseEntity.ok(newToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token.");
        }
    }
    
}
