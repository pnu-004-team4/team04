package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImplement {

    @Autowired
    public JavaMailSenderImpl javaMailSender;
    AccountCustomRepository accountService = new AccountCustomRepository();

    public void sendAuthMail(Account account) {
        String to = account.getEmail();
        String from = "musiccloudtest@gmail.com";
        final String username = "musiccloudtest@gmail.com";
        final String password = "music123!@#";
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        String key = new TempKey().getKey(50, false);
        account.setAuthKey(key);
        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Music Cloud - Verify your account");
            message.setText(new StringBuffer().append("<h1>메일인증</h1>").append("<a href=\"http://localhost:13246/emailConfirm?userEmail=")
                    .append(account.getEmail()).append("&authKey=").append(key).append("\" target=\"_blank\">이메일 인증 확인</a>").toString());
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void emailVerification(String email, String key){
        Account account = accountService.findAccountByEmail(email);
        if(key.equals(account.getAuthKey())){
            accountService.approveAccount(email, true);
        }
    }

}

