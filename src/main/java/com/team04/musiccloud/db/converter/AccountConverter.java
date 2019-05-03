package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.auth.Account;
import org.bson.Document;

public class AccountConverter {

    public static Document toDocument(Account account) {
        Document document = new Document("_id", account.getEmail())
                .append("password", account.getPassword())
                .append("name", account.getName())
                .append("username", account.getUsername());

        return document;
    }

    public static Account toAccount(Document document) {
        Account account = new Account();
        account.setEmail((String) document.get("_id"));
        account.setPassword((String) document.get("password"));
        account.setName((String) document.get("name"));
        account.setUsername((String) document.get("username"));

        return account;
    }
}
