package com.team04.musiccloud.auth;

import static org.junit.Assert.*;

import com.team04.musiccloud.utilities.StaticKeys;
import javax.mail.MessagingException;
import org.junit.Before;
import org.junit.Test;

public class EmailServiceImplTest {
  private EmailServiceImpl emailService;

  @Before
  public void setUp() throws Exception {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    emailService = new EmailServiceImpl();
  }

  @Test
  public void sendAuthMailTest() {
    Account account = new Account();
    account.setEmail("sample@sample.sample");
    try {
      emailService.sendAuthMail(account, "127.0.0.1");
    } catch (MessagingException e) {
      fail(e.toString());
    }
  }
}