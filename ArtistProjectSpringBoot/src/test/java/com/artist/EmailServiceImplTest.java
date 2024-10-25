package com.artist;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.artist.service.impl.EmailServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmailServiceImplTest {

    @MockBean
    private JavaMailSender mailSender;

    @Autowired
    private EmailServiceImpl emailService;

    @BeforeEach
    public void setup() {
        // Mock the JavaMailSender to return a new MimeMessage
        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
    }

    @Test
    public void emailService() {
    	
        emailService.sendAuctionRemiderEmail();
        System.out.println("信件已寄");
    }
}
