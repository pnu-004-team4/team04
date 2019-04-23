package com.team04.musiccloud.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // [[[[ 주의 ]]]] 아래 코드는 로그인 인증과 관련된 코드로 혹여 권한 오류 생길 시 확인바랍니다.
        http.authorizeRequests() // 요청을 어떻게 보안을 걸 것인지?
                .antMatchers("/login", "/register", "/css/**", "/js/**").permitAll() // 얘네는 전부다 허용.
                .anyRequest().authenticated() // 나머지 모든 요청들은 인증이 필요함.
                .and()
            .formLogin() // 로그인으로 가는 페이지가 무엇일까
                .loginPage("/login") // 이거다
                .permitAll() // 모든 사람에게 permit
                .and()
            .logout()
                .logoutUrl("/logout") // 로그아웃시 동작하는 url?
                .logoutSuccessUrl("/login") // 로그아웃 성공하면 가는 url
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
