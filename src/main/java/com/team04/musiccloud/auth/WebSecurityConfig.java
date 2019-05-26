package com.team04.musiccloud.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // [[[[ 주의 ]]]] 아래 코드는 로그인 인증과 관련된 코드로 혹여 권한 오류 생길 시 확인바랍니다.
        http.authorizeRequests()
                .antMatchers("/login", "/register", "/css/**", "/js/**", "/registerCheck").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
        http.csrf().disable();
    }
}
