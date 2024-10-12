package com.artist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artist.entity.Customers;
import com.artist.service.impl.CustomersServiceImpl;
import com.artist.service.impl.EmailServiceImpl;
import com.artist.utils.JwtUtil;

	@RestController
	public class EmailController {

	    @Autowired
	    private EmailServiceImpl emailService;
	    
	    @Autowired
	    private CustomersServiceImpl csi;
	    
	    @Autowired
	    private JwtUtil jwtUtil;
	    
	    //發送重置密碼的超連結
	    @PostMapping("/sendPasswordResetLink")
	    public ResponseEntity<?> sendPasswordResetLink(@RequestBody Map<String, String> request) {
	        String email = request.get("email");
	        String token = jwtUtil.generatePasswordResetToken(email);
	        String resetLink = "http://localhost:5173/home/reset-password/" + token;  //======>>>???
	        emailService.sendPasswordResetEmail(email, resetLink); // 直接調用
	        return ResponseEntity.ok("重置密碼的連結已發送至 " + email);
	    }
	    
	    //有無過期
	    @GetMapping("/verifyResetUrl")
	    public ResponseEntity<String> verifyResetUrl(@RequestParam String token) {
	        if (jwtUtil.isTokenExpired(token)) {//有過期
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("連結已過期,請重新请求修改密碼。");
	        }else {
	        	 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("連結有效");
	        	}
	        }

	    @PostMapping("/reset-password")
	    public ResponseEntity<String> updatePassword(@RequestParam String token, @RequestParam String newPassword) {
	        if (jwtUtil.isTokenExpired(token)) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("連結已過期,請重新请求修改密碼。");
	        }
	        // 解析 token
	        String customerId = jwtUtil.getCustomerIdFromToken(token);
	        Customers customer = csi.getByCustomerId(customerId);
	        // 更新用户密碼
	        customer.setEmail(newPassword);
	        csi.update(customer);
	        return ResponseEntity.ok("密碼已成功重置。");
	    }

	    
	    //發送得標信
	    @PostMapping("/bid-success")
	    public String bidSuccess(
	            @RequestParam String email,
	            @RequestParam String paintingId,
	            @RequestParam double bidAmount) {
	        emailService.sendBidSuccessEmail(email, paintingId, bidAmount);
	        return "The winning letter has been sent!";
	    }
	    
	    
	    
	}

