package com.artist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.artist.service.EmailService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class EmailServiceImpl implements EmailService{

	 private final JavaMailSender mailSender;

	 @Value("${jwt.secret}")
		private String jwtSecret;
		
	    public EmailServiceImpl(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }
	
	    //發送郵件的邏輯
	    public void sendPasswordResetEmail(String email, String resetLink) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("重置密碼請求");
	        message.setText("請點擊以下連結來重置您的密碼:\n" + resetLink);
	        mailSender.send(message);
	    }
	    
	    //生成 JWT 的方法
	    public String generatePasswordResetToken(String email) {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("email", email);
	        return Jwts.builder()
	                .setSubject(email)
	                .addClaims(claims)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + 900000)) // 15 分鐘
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }
	    
	    

}
