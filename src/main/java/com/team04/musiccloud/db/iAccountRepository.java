package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;

public interface iAccountRepository {

    /*
    Account가 전달되면 DB에 저장하는 함수.
    이때 Account.java에 @Id로 인해 email이 primary key 역할을 함.
    */
    void registerAccount(Account account);

    /*
    전달된 매개 변수 내용들로 사용자 정보를 수정하는 함수.
    Account.java에 @Id로 인해 email이 primary key 역할을 하므로,
    전달된 email로 DB상에서 수정할 사용자를 찾아냄.
    각 매개 변수들이 null로 전달되면 변경 사항이 없는 것으로 간주.
    */
    void updateAccount(String email, String password, String name, Boolean resolution);

    /*
    email이라는 String으로 사용자 정보 찾는 함수.
    Account.java에선 email이 @Id로 인해 primary key와 같은 역할을 함.
    같은 email을 가진 사용자 정보를 찾으면 Account를, 없으면 null을 반환.
    */
    Account findAccountByEmail(String email);

    /*
    전달 받은 email을 사용하는 사용자 정보를 삭제하는 함수.
    Account.java에선 email이 @Id로 인해 primary key와 같은 역할을 함.
    같은 email을 가진 사용자 정보를 찾아 삭제하면 true를, 없으면 false을 반환.
     */
    void deleteAccount(String email);
}
