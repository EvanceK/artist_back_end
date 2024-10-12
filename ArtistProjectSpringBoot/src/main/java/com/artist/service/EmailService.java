package com.artist.service;

public interface EmailService {
    
    
    public void sendPasswordResetEmail(String email, String resetLink);
  
    public String generatePasswordResetToken(String email);



}
