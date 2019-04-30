package com.team04.musiccloud.auth;

//import com.team04.musiccloud.db.AccounRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class Account {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Id
    private String email;
    private String password;
    private String name;
    private String username;
    private Integer id;
    private String sessId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    //bcrypt를 이용한 encoding
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessId(){
        return sessId;
    }

    public void setSessId(String sess){
        this.sessId = sess;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

}