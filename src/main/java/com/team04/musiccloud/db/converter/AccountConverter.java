package com.team04.musiccloud.db.converter;

import com.team04.musiccloud.auth.Account;
import org.bson.Document;

public class AccountConverter {

    public static Document toDocument(Account account) {
        return new Document("_id", account.getEmail())
                .append("password", account.getPassword())
                .append("name", account.getName())
                .append("resolution", account.getResolution())
                .append("approval", account.getApproval())
                .append("authKey", account.getAuthKey());
    }

    public static Account toAccount(Document document) {
        Account account = new Account();
        account.setEmail((String) document.get("_id"));
        account.setPassword((String) document.get("password"));
        account.setName((String) document.get("name"));
        account.setResolution(Boolean.parseBoolean(document.get("resolution").toString()));
        account.setApproval(Boolean.parseBoolean(document.get("approval").toString()));
        account.setAuthKey((String) document.get("authKey"));

        return account;
    }
}
