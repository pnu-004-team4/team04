package com.team04.musiccloud.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.dao.AccountDao;
import com.team04.musiccloud.utilities.StaticKeys;
import org.bson.Document;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountCustomRepository {

    private AccountCustomRepository instance;
    private AccountDao accountDao;

    public AccountCustomRepository() {
        MongoClientURI mongoClientURI = new MongoClientURI(StaticKeys.getKeys());
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("MusicCloud")
                .getCollection("account");
        this.accountDao = new AccountDao(mongoCollection);
    }

    public AccountCustomRepository getInstance() {
        if (instance == null) {
            instance = new AccountCustomRepository();
        }
        return instance;
    }

    /*
    Account가 전달되면 DB에 저장하는 함수.
    이때 Account.java에 @Id로 인해 email이 primary key 역할을 함.
    */
    public boolean registerAccount(Account account) {
        return accountDao.create(account);
    }

    /*
    전달된 매개 변수 내용들로 사용자 정보를 수정하는 함수.
    Account.java에 @Id로 인해 email이 primary key 역할을 하므로,
    전달된 email로 DB상에서 수정할 사용자를 찾아냄.
    각 매개 변수들이 null로 전달되면 변경 사항이 없는 것으로 간주.

    예시) updateAccount("test@test.com", "1234", null, "Kim");
    test@test.com을 아이디로 사용하는 유저의 정보를 주어진 매개 변수 내용들로 바꿈.
    이 예시에선 password는 1234로 바꾸고, name은 바꾸지 않고, username은 Kim으로 바꿈, .
    */
    public boolean updateAccount(String email, String password, String name, Boolean resolution) {
        Account found = accountDao.getAccount(email);
        if ( password != null ) {
            found.setPassword(password);
        }
        if ( name != null ) {
            found.setName(name);
        }
        if ( resolution != null ) {
            found.setResolution(resolution);
        }

        return accountDao.update(found);
    }

    /*
    email이라는 String으로 사용자 정보 찾는 함수.
    Account.java에선 email이 @Id로 인해 primary key와 같은 역할을 함.
    같은 email을 가진 사용자 정보를 찾으면 Account를, 없으면 null을 반환.
    */
    public Account findAccountByEmail(String email) {
        return accountDao.getAccount(email);
    }

    public Account getCurrentAccount() {
        return findAccountByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /*
    전달 받은 email을 사용하는 사용자 정보를 삭제하는 함수.
    Account.java에선 email이 @Id로 인해 primary key와 같은 역할을 함.
    같은 email을 가진 사용자 정보를 찾아 삭제하면 true를, 없으면 false을 반환.
     */
    public boolean deleteAccount(String email) {
        return accountDao.delete(email);
    }
}
