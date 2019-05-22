package com.team04.musiccloud.db.repository;

import com.team04.musiccloud.auth.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface iAccountMongoDBRepository extends MongoRepository<Account, String> {

}
