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
                //.antMatchers("/server/storage/" + account.getEmail() + "/**").hasRole("" + account.getEmail())
                //.antMatchers("/server/temp/" + account.getEmail() + "/**").hasRole("" + account.getEmail())
                // @TODO 조만간 위의 코드로 바뀔 코드입니다 현재 ADMIN 권한을 가진 유저만 해당 폴더의 파일에 접근할 수 있습니다
                .antMatchers("/server/storage/CSK/**").hasRole("ADMIN")
                .antMatchers("/server/temp/CSK/**").hasRole("ADMIN")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
    }
}
