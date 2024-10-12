package com.artist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.artist.service.impl.EmailServiceImpl;

	@RestController
	public class EmailController {

	    @Autowired
	    private EmailServiceImpl emailService;
	    
	    //發送重置密碼的鏈接：
	    @PostMapping("/sendPasswordResetLink")
	    public ResponseEntity<?> sendPasswordResetLink(@RequestBody Map<String, String> request) {
	        String email = request.get("email");
	        String token = emailService.generatePasswordResetToken(email);
	        String resetLink = "http://localhost:5173/reset-password?token=" + token;  //======>>>???
	        emailService.sendPasswordResetEmail(email, resetLink); // 直接調用
	        return ResponseEntity.ok("重置密碼的連結已發送至 " + email);
	    }
	}

