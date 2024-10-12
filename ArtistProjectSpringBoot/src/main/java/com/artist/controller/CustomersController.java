package com.artist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.dto.request.CustomersDTO;
import com.artist.dto.response.LoginResponse;
import com.artist.dto.response.WalletDTO;
import com.artist.dto.response.WalletResponse;
import com.artist.entity.Customers;
import com.artist.service.impl.BidrecordServiceImpl;
import com.artist.service.impl.CustomersServiceImpl;

@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomersServiceImpl csi;
    @Autowired
    private BidrecordServiceImpl bsi;

    // 註冊
    @PostMapping(value ="/register", consumes = "application/json")
    public ResponseEntity<?> createCustomer(@RequestBody CustomersDTO customersDTO) {
        try {
        	csi.create(customersDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("註冊成功");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    
    // 登入
    @PostMapping(value ="/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody CustomersDTO customersDTO) {
        try {
			String token = csi.login(customersDTO.getEmail(), customersDTO.getPassword());
			String customerId = csi.getCustomerIdFromToken(token);
			Customers customer = csi.getByCustomerId(customerId);
			System.out.println(customer);
			String nickName = customer.getNickName();
			LoginResponse response = new LoginResponse(token, nickName);
			return ResponseEntity.ok(response);
		} catch (RuntimeException  e) {
			 // 捕捉密碼或電子郵件錯誤，返回錯誤信息
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
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
    
    // 
    @GetMapping("/mywallet/{token}")
    public ResponseEntity<?> wallet(@PathVariable String token) {
        try {
            String customerId = csi.getCustomerIdFromToken(token);
            Customers customer = csi.getByCustomerId(customerId);

            if (customer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("客戶不存在");
            }

            String bankAccount = customer.getBankAccount();
            Double bankBalance = customer.getBankBalance();
            String creditCardNo = customer.getCreditCardNo();
            List<WalletDTO> depositRecord = bsi.getDepositRecord(customerId, "refunded");

            WalletResponse walletDTO = new WalletResponse(bankAccount, creditCardNo, bankBalance, depositRecord);
            return ResponseEntity.ok(walletDTO);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無效的請求：" + e.getMessage());
        }
    }
    
}
