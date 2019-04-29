package com.team04.musiccloud.auth;

import com.team04.musiccloud.db.AccountCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    AccountCustomRepository accounRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accounRepository.FindAccountbyEmail(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 유저 권한을 주도록 합니다.
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
