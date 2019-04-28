package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountCustomMethods implements AccountCustomRepository {

    @Autowired
    AccounRepository accounRepository;

    @Override
    public boolean registerAccount(Account account) {
        accounRepository.insert(account);
        return true;
    }

    @Override
    public boolean updateAccount(String email, String password, String name) {
        boolean updateCompleted = true;

        Optional<Account> found = accounRepository.findByEmail(email);
        if ( found.isPresent() ) {
            if ( password != null ) {
                found.get().setPassword(password);
            }
            if ( name != null ) {
                found.get().setName(name);
            }
            accounRepository.save(found.get());
        } else {
            updateCompleted = false;
        }

        return updateCompleted;
    }
}
