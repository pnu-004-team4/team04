package com.team04.musiccloud.db.repository;

import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.iAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepository implements iAccountRepository {
    @Autowired
    iAccountMongoDBRepository accountMongoDBRepository;

    public void registerAccount(Account account) {
        accountMongoDBRepository.insert(account);
    }

    //@TODO 간소화 가능.
    public void updateAccount(String email, String password, String name, Boolean resolution) {
        Account account = findAccountByEmail(email);
        if ( password != null ) {
            account.setPassword(password);
        }
        if ( name != null ) {
            account.setName(name);
        }
        if ( resolution != null ) {
            account.setResolution(resolution);
        }

        accountMongoDBRepository.save(account);
    }

    public Account findAccountByEmail(String email) {
        Account account = null;

        Optional<Account> found = accountMongoDBRepository.findById(email);
        if (found.isPresent()) {
            account = found.get();
        }

        return account;
    }

    public void deleteAccount(String email) {
        accountMongoDBRepository.deleteById(email);
    }
}
