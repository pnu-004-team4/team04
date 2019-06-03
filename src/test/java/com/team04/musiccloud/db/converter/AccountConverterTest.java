package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.auth.Account;
import org.bson.Document;
import org.junit.Test;

import static com.team04.musiccloud.db.converter.AccountConverter.toAccount;
import static com.team04.musiccloud.db.converter.AccountConverter.toDocument;
import static org.junit.Assert.*;

public class AccountConverterTest {
    private Account account;
    private Document document;

    public AccountConverterTest() {
        this.account = new Account();
        account.setEmail("test@test.com");
        account.setPassword("test");
        account.setName("test");
        account.setResolution(false);

        this.document = new Document("_id", "test@test.com")
        .append("password", "test")
        .append("name", "test")
        .append("resolution", "false");
    }

    @Test
    public void testToDocument() {
        assertEquals(document.toString(), toDocument(this.account).toString());
    }

    @Test
    public void testToAccount() {
        assertEquals(account.getEmail(), toAccount(this.document).getEmail());
        assertEquals(account.getPassword(), toAccount(this.document).getPassword());
        assertEquals(account.getName(), toAccount(this.document).getName());
        assertEquals(account.getResolution(), toAccount(this.document).getResolution());
    }
}