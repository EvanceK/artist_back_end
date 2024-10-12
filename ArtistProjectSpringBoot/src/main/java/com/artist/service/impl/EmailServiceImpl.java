package com.artist.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.artist.service.EmailService;

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
	        message.setSubject("Artist重置密碼請求");
	        message.setText("我們收到您更改密碼的請求。如果您希望重置密碼，請點擊下方按鈕。如果這不是您的操作，請忽略此郵件。\n" + resetLink+"\n請注意，此鏈接將在 15分鐘內過期。");

	        mailSender.send(message);
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
