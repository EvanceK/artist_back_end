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
	    
	    
	    //發送得標信
	    public void sendBidSuccessEmail(String email, String itemName, double bidAmount) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Congratulations! You have successfully placed a bid!");
	        message.setText(String.format(
	                "Thank you for participating! We are pleased to inform you:\n\n" +
	                "Item: %s\n" +
	                "Winning Amount: %.2f\n\n" +
	                "Please complete your payment within 24 hours. If you have any questions, please contact us.\n\n" +
	                "Thank you for your support, and we look forward to seeing you at the next auction!",
	                itemName, bidAmount
	        ));
	        mailSender.send(message);
	    }

}
