package com.team04.musiccloud.auth;

public class Account {

    private Integer id;
    private String email;
    private String password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSessID() {
        return sessID;
    }

    public void setSessID(String sessID) {
        this.sessID = sessID;
    }
}
