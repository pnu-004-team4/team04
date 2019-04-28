package com.team04.musiccloud.db;

import com.team04.musiccloud.auth.Account;

public interface AccountCustomRepository {

    /*
    Account가 전달되면 DB에 저장.
    이때 Account.java에 @Id로 인해 email이 primary key 역할을 한다.
    */
    public boolean registerAccount(Account account);

    /*
    Account.java에 @Id로 인해 email이 primary key 역할을 하므로,
    전달된 email로 DB상에서 수정할 사용자를 찾아낸다.
    여기서 사용자가 바꿀 수 있는 부분은 비밀번호와 이름인데,
    각각 null로 전달되면 변경 사항이 없는 것으로 간주한다.

    예시) updateAccount("test@test.com", "1234", null);
    test@test.com을 아이디로 사용하는 유저의 정보를 바꾸는데,
    비밀번호는 1234로, 이름은 변경하지 않는다.
    */
    public boolean updateAccount(String email, String password, String name);
}
