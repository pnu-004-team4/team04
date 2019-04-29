package com.team04.musiccloud.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Account {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String email;
    private String password;
    private String name;
    private String username;
    private Integer id;
    private String sessID;

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

    public String getSessID(){
        return sessID;
    }

    public void setSessID(String sess){
        this.sessID = sess;
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