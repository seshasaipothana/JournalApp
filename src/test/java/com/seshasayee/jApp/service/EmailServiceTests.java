package com.seshasayee.jApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;


    @Test
    public void testSendEmail(){
        emailService.sendEmail("pothanaseshasayee@gmail.com","Testing java mail sender","If you're seeing this,Then you've surely received the message");
    }
}
