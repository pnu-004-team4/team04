package com.team04.musiccloud.User;


// 로그인시 데이터 베이스에서 사용자 정보를 받아 활용한다.
//
public class UserInform {
    private String sessID;
    private String userID;
    private String pw;
    private String name;
    private String email;

    public String getSessID(){
        return sessID;
    }

    public void setSessID(String sess){
        this.sessID = sess;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserID(String user){
        this.userID = user;
    }

    public String getPw(){
        return pw;
    }

    public void setpw(String pw){
        this.pw = pw;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
