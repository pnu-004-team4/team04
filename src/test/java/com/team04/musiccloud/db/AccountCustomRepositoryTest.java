package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.utilities.StaticKeys;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountCustomRepositoryTest {
    private AccountCustomRepository accountCustomRepository;
    private Account account;

    @Before
    public void setUp() {
        StaticKeys.setKeys("mongodb://test:test@35.200.2.141/test");
        StaticKeys.setDbName("test");

        accountCustomRepository = AccountCustomRepository.getInstance();

        this.account = new Account();
        account.setEmail("test@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);
        account.setApproval(false);
        account.setAuthKey("test");
    }

    @Test
    public void getInstance() {
        assertEquals(accountCustomRepository, AccountCustomRepository.getInstance());
    }

    @Test
    public void registerAccount() {
        if (accountCustomRepository.findAccountByEmail("test@test.com") != null) {
            accountCustomRepository.deleteAccount("test@test.com");
        }
        assertTrue(accountCustomRepository.registerAccount(account));
    }

    @Test
    public void updateAccount() {
        assertTrue(accountCustomRepository.updateAccount("test@test.com", "test", "test", false));
    }

    @Test
    public void findAccountByEmail() {
        assertNotNull(accountCustomRepository.findAccountByEmail("test@test.com"));
    }

    @Test
    public void deleteAccount() {
        assertTrue(accountCustomRepository.deleteAccount("test@test.com"));
    }
}