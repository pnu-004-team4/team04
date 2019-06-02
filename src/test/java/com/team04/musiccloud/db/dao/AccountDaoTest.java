package com.team04.musiccloud.db.dao;

import com.team04.musiccloud.auth.Account;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDaoTest {
    private Account account, account1;
    private AccountDao accountDao;

    public AccountDaoTest() {
        this.account = new Account();
        account.setEmail("test@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);

        this.account1 = new Account();
        account.setEmail("test1@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);

        accountDao.create(account1);
    }

    @Test
    public void testCreate() {
        assertTrue(accountDao.create(account));
    }

    @Test
    public void testUpdate() {
        assertTrue(accountDao.update(account));
    }

    @Test
    public void testDelete() {
        assertTrue(accountDao.delete("test1@test.com"));
    }

    @Test
    public void testExists() {
        assertTrue(accountDao.exists("test@test.com"));
    }

    @Test
    public void testGetList() {
        accountDao.getList();
    }

    @Test
    public void testGetAccount() {
        assertEquals(account, accountDao.getAccount("test@test.com"));
    }
}