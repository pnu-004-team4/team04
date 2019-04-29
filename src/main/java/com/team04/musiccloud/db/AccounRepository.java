package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccounRepository extends MongoRepository<Account, String> {

    /*
    email이라는 String으로 사용자 정보 찾는 함수.
    Account.java에선 email이 @Id로 인해 primary key와 같은 역할을 한다.
    고로 찾으면 Optional<Account>로 isPresent 또는 get 함수를 사용할 수 있다.

    예시) Optional<Account> found = accountRepository.findByEmail(email);
    만약 사용자 정보가 존재한다면 found.isPresent() = true가 되고,
    found.get()을 사용해 원하는 Account.class를 가져올 수 있다.
    존재하지 않다면 found.isPresent() = false이고 found.get()은 null을 반환한다.
    */
    public Optional<Account> findByEmail(String email);
}
