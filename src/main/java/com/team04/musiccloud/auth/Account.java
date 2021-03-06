package com.team04.musiccloud.auth;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Account {

  @Id
  private String email;
  private String password;
  private String name;
  private String authKey;
  private Boolean resolution;
  private Boolean approval;

  private Integer id;

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

  public void encodePassword() {
    //bcrypt를 이용한 encoding
    PasswordEncoder passwordEncoder = PasswordEncoderFactories
        .createDelegatingPasswordEncoder();
    this.password = passwordEncoder.encode(password);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getApproval() {
    return approval;
  }

  public void setApproval(Boolean apporoval) {
    this.approval = apporoval;
  }

  public Boolean getResolution() {
    if (resolution == null) {
      return false;
    }
    return resolution;
  }

  public void setResolution(Boolean resolution) {
    this.resolution = resolution;
  }

  public String getAuthKey() {
    return authKey;
  }

  public void setAuthKey(String authKey) {
    this.authKey = authKey;
  }
}