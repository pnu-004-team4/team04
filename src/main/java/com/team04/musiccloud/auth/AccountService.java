package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static javax.swing.JOptionPane.showMessageDialog;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loaduserbyusername() has been called");
        AccountCustomRepository accountRepository = new AccountCustomRepository();
        try{
            accountRepository.findAccountByEmail(email);
        }catch(NullPointerException e){
            System.out.println("exception detected");
            showMessageDialog(null, "로그인 실패");
        }
        Account account = accountRepository.findAccountByEmail(email);

        if(account.getEmail() == null){ throw new UsernameNotFoundException("Invalid account"); }

        System.out.println("FindAccountbyEmail() has been called" + " email : " + account.getEmail() + " password : " + account.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 유저 권한을 주도록 합니다.
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

}
