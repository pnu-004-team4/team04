/*
package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountCustomRepositoryTest {
    private AccountCustomRepository accountCustomRepository;
    private Account account, account1;

    @Test
    public void getInstance() {
        this.account = new Account();
        account.setEmail("test@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);

        this.account1 = new Account();
        account1.setEmail("test1@test.com");
        account1.setPassword("test");
        account1.setName("test");
        account1.setResolution(false);

        //AccountCustomRepository accountCustomRepository = AccountCustomRepository.getInstance();
    }

    @Test
    public void registerAccount() {
        assertTrue(accountCustomRepository.registerAccount(account));
    }

    @Test
    public void updateAccount() {
        assertTrue(accountCustomRepository.updateAccount("test@test.com", "test", "test", false));
    }

    @Test
    public void findAccountByEmail() {
        assertEquals(account, accountCustomRepository.findAccountByEmail("test@test.com"));
    }

    @Test
    public void getCurrentAccount() {
        //accountCustomRepository.getCurrentAccount()
    }

    @Test
    public void deleteAccount() {
        assertTrue(accountCustomRepository.deleteAccount("test1@test.com"));
    }
}

 */