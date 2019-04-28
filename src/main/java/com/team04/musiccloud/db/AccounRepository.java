package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccounRepository extends MongoRepository<Account, String> {

    public Optional<Account> findByEmail(String email);
}
