package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class EmailServiceImplement {

    @Autowired
    public JavaMailSender mailSender;

    AccountCustomRepository accountService = new AccountCustomRepository();

    public void sendAuthMail(Account account) throws MessagingException, UnsupportedEncodingException {
        String key = new TempKey().getKey(50, false);
        //@TODO DB에 있는 AuthKey에 저장해야함 set(account.getEmail(), key) 형식
        account.setAuthKey(key);
        System.out.println("Authentication key is:" + key);

        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("Music Cloud - Verify your account");
        sendMail.setText(
                new StringBuffer().append("<h1>메일인증</h1>").append("<a href='http://localhost:13246/emailConfirm?userEmail=").append(account.getEmail()).append("&authKey=").append(key).append("' target='_blank'>이메일 인증 확인</a>").toString());
        sendMail.setFrom("musiccloudtest@gmail.com", "Music Cloud");
        sendMail.setTo(account.getEmail());
        System.out.println("sending email...");
        sendMail.send();
        System.out.println("sending complete!");
    }

    public void emailVerification(String email, String key){
        Account account = accountService.findAccountByEmail(email);
        if(key.equals(account.getAuthKey())){
            account.setApproval(true);
        }
        System.out.println("This account's approval is " + account.getApproval());
    }
}

