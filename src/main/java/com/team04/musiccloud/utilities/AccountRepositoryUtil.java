package com.team04.musiccloud.utilities;

import com.team04.musiccloud.auth.Account;
import com.team04.musiccloud.db.AccountCustomRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AccountRepositoryUtil {

  private static AccountRepositoryUtil accountRepositoryUtil = null;
  private AccountCustomRepository accountCustomRepository;

  private AccountRepositoryUtil() {
    accountCustomRepository = new AccountCustomRepository();
  }

  public static AccountRepositoryUtil getInstance() {
    if (accountRepositoryUtil == null) {
      accountRepositoryUtil = new AccountRepositoryUtil();
    }
    return accountRepositoryUtil;
  }

  private Authentication getCurrentAuthInfo() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  public Account getCurrentAccount() {
    return accountCustomRepository.findAccountByEmail(getCurrentAuthInfo().getName());
  }
}
