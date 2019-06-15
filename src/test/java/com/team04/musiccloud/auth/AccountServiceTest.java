package com.team04.musiccloud.auth;

import static org.junit.Assert.*;

import com.team04.musiccloud.utilities.StaticKeys;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountServiceTest {

  private AccountService accountService;

  @Before
  public void setUp() throws Exception {
    StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
    StaticKeys.setDbName("test");
    accountService = new AccountService();
  }

  @Test
  public void loadUserByUsername() {
    assertNotNull(accountService.loadUserByUsername("admin@admin.com"));
  }
}