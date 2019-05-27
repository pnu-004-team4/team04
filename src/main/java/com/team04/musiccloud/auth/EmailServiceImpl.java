package com.team04.musiccloud.auth;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl{

    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to){
        SimpleMailMessage message = new SimpleMailMessage();

        String subject = "Music Cloud complete your registration!";
        String text = "Please click the link down below to approve your registration";

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);

    }
}

