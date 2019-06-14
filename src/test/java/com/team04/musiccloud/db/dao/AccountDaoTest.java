package com.team04.musiccloud.db.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.team04.musiccloud.auth.Account;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDaoTest {
    private Account account;
    private AccountDao accountDao;

    @Before
    public void setUp() {
        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://test:test@35.200.2.141:27017/test");
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("test")
                .getCollection("account");
        this.accountDao = new AccountDao(mongoCollection);

        this.account = new Account();
        account.setEmail("test@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);
        account.setApproval(false);
        account.setAuthKey("test");
    }

    @Test
    public void testCreate() {
        if (accountDao.exists(account.getEmail())) {
            accountDao.delete(account.getEmail());
        }
        assertTrue(accountDao.create(account));
    }

    @Test
    public void testUpdate() {
        if (accountDao.exists(account.getEmail())) {
            accountDao.delete(account.getEmail());
        }
        accountDao.create(account);
        assertTrue(accountDao.update(account));
    }

    @Test
    public void testDelete() {
        if (accountDao.exists(account.getEmail())) {
            accountDao.delete(account.getEmail());
        }
        accountDao.create(account);
        assertTrue(accountDao.delete(account.getEmail()));
    }

    @Test
    public void testExists() {
        if (accountDao.exists(account.getEmail())) {
            accountDao.delete(account.getEmail());
        }
        accountDao.create(account);
        assertTrue(accountDao.exists(account.getEmail()));
    }

    @Test
    public void testGetList() {
        assertNotNull(accountDao.getList());
    }

    @Test
    public void testGetAccount() {
        if (accountDao.exists(account.getEmail())) {
            accountDao.delete(account.getEmail());
        }
        accountDao.create(account);
        assertEquals(account.getEmail(), accountDao.getAccount(account.getEmail()).getEmail());
        assertEquals(account.getName(), accountDao.getAccount(account.getEmail()).getName());
        assertEquals(account.getPassword(), accountDao.getAccount(account.getEmail()).getPassword());
        assertEquals(account.getResolution(), accountDao.getAccount(account.getEmail()).getResolution());
    }
}