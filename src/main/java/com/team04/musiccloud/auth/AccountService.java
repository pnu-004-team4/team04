package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static javax.swing.JOptionPane.showMessageDialog;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, invalidEmailException {
            System.out.println("loaduserbyusername() has been called");
            AccountCustomRepository accountRepository = new AccountCustomRepository();
            Account account;
            try {
                account = accountRepository.findAccountByEmail(email);
            } catch (NullPointerException e) {
                System.out.println("Null pointer exception detected. invalid email");
                showMessageDialog(null, "로그인 실패");
                throw new invalidEmailException("Invalid account");
            }
            System.out.println("FindAccountbyEmail() has been called" + " email : " + account.getEmail() + " password : " + account.getPassword());
            List<GrantedAuthority> authorities = new ArrayList<>();
            // 유저 권한을 주도록 합니다.
            if(account.getEmail().equals("admin@admin.com")){
                // 관리자에게는 ADMIN 권한 부여
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
            else{
                // 유저마다 email로 권한 부여
                authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getEmail()));
            }
            System.out.println("User's authority : " + authorities);
            return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
