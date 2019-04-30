package com.team04.musiccloud.auth;

public class FaceAccount {

    public FaceAccount(){

    }

    public Account fakeaccount(){

        Account Fakelove = new Account();

        Fakelove.setEmail("smile@smile.com");
        Fakelove.setPassword("1234");
        Fakelove.setName("우서");


        return Fakelove;
    }
}
