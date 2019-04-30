package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        Account account = accountRepository.findAccountByEmail(email);
        System.out.println("FindAccountbyEmail() has been called");
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 유저 권한을 주도록 합니다.
        /*try{
            loadUserByUsername(email);
        }catch (UsernameNotFoundException e){
            showMessageDialog(null, "로그인 실패");
        }*/
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(account.getEmail(), account.getPassword(), authorities);
    }

}
