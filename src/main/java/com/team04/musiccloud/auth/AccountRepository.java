package com.team04.musiccloud.auth;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository
public class AccountRepository {

    private Map<String, Account> accounts = new HashMap<>();
    private Random random = new Random();


    //광: 암호화 한 후 데이터에 세이브한다.
    public Account save(Account account) {
        account.setId(random.nextInt());
        accounts.put(account.getEmail(), account);
        return account;
    }

    public Account findByEmail(String username) {
        return accounts.get(username);
    }
}
