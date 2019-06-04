package com.team04.musiccloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl{

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to){
        SimpleMailMessage message = new SimpleMailMessage();

        String subject = "Music Cloud. Complete your registration!";
        String text = "Please click the link down below to approve your registration";

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);

    }
}

