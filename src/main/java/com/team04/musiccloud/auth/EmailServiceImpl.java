package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailServiceImpl {

  @Autowired
  public JavaMailSenderImpl javaMailSender;
  private AccountCustomRepository accountService = new AccountCustomRepository();

  public void sendAuthMail(Account account, String currentUrl) throws MessagingException {
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
    Session session = Session.getInstance(props,
        new Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });

    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(from));
    message.setRecipients(Message.RecipientType.TO,
        InternetAddress.parse(to));
    message.setSubject("Music Cloud - Verify your account");
    message.setText("Please verify your account by going to the link down below "
        + System.getProperty("line.separator")
        + currentUrl + "/emailConfirm?userEmail="
        + account.getEmail()
        + "&authKey="
        + key);
    Transport.send(message);
  }

  public void emailVerification(String email, String key) {
    Account account = accountService.findAccountByEmail(email);
    if (key.equals(account.getAuthKey())) {
      accountService.approveAccount(email, true);
    }
  }

}

