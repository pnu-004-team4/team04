package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

  private static final Logger logger = Logger.getGlobal();

  @Override
  public UserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException, LoginException {
    logger.info("loaduserbyusername() has been called");
    AccountCustomRepository accountRepository = new AccountCustomRepository();
    Account account;
    try {
      account = accountRepository.findAccountByEmail(email);
    } catch (NullPointerException e) {
      logger.warning("Null pointer exception detected. invalid email");
      throw new LoginException("Invalid account");
    }

    if (account.getApproval() == null) {
      account.setApproval(false);
    }

    if (!account.getApproval()) {
      logger.warning("Account is not approved. Please check your email");
      throw new LoginException("Not approved account");
    }

    logger.info(
        "FindAccountbyEmail() has been called" + " email : " + account.getEmail() + " password : "
            + account.getPassword());
    List<GrantedAuthority> authorities = new ArrayList<>();
    // 유저마다 email로 권한 부여
    authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getEmail()));
    logger.info("User's authority : " + authorities);
    return new User(account.getEmail(), account.getPassword(), authorities);
  }
}
