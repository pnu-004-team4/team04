package com.team04.musiccloud.auth;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Account {

    //bcrypt를 이용한 encoding
    private PasswordEncoder passwordEncoder;

    @Id
    private String email;
    private String password;
    private String name;
    private Boolean resolution;
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

    public void encodePassword(){
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public String getSessId(){
        return sessId;
    }

    public void setSessId(String sess){ this.sessId = sess; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Boolean getResolution() {
        return resolution;
    }

    public void setResolution(Boolean resolution) {
        this.resolution = resolution;
    }
}