<%--
  Created by IntelliJ IDEA.
  User: munkwangsu
  Date: 2019-04-30
  Time: 오전 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import ="com.team04.musiccloud.auth.Account" import ="com.team04.musiccloud.auth.FaceAccount" language= "java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.team04.musiccloud.db.AccountCustomRepository" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>

<!doctype html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>UserSetCheck</title>
    <link rel="stylesheet" href="/css/login.css">

    <style rel="stylesheet">
        html {
            width: 100%;
            height: 100%;
        }


    </style>
</head>
<body>

<%
    request.setCharacterEncoding("UTF-8");


    Account acc;
    Account EncodeAcc = new Account();
    AccountCustomRepository repository = new AccountCustomRepository();

    //입력값
    String Username = request.getParameter("username");
    String Password = request.getParameter("password");
    String Email = request.getParameter("email");
    String Name = request.getParameter("name");

    String CurUsername, CurPass, CurName;
    String CheckUsername, CheckPass, CheckEmail, CheckName;

    //기존의 내용 불러오기.
    acc = repository.findAccountByEmail(Email);

    CurUsername = acc.getUsername();
    CurPass = acc.getPassword();
    CurName = acc.getName();

    System.out.println("바꾸기 전 password : " + CurPass);
    System.out.println("바꾸기 전 name : " + CurName);


    //빈칸인지 아닌지 확인한 후 빈칸이 아니면 업데이트.
    if(!Username.equals("")) CurUsername = Username;
    if(!Name.equals("")) CurName = Name;
    if(!Password.equals("")) CurPass = Password;

    System.out.println("1차 변경 password : " + CurPass);
    System.out.println("1차 변경 name : " + CurName);

    EncodeAcc.setPassword(CurPass);
    EncodeAcc.encodePassword();
    CurPass = EncodeAcc.getPassword();

    System.out.println("인코딩 후 password : " + CurPass);

    //updateAccount
    repository.updateAccount(Email, CurPass, CurName, CurUsername);

//    Object account = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //update된 내용 불러오기.
    acc = repository.findAccountByEmail(Email);

    CheckUsername = acc.getUsername();
    CheckName = acc.getName();
    CheckPass = acc.getPassword();
    CheckEmail = acc.getEmail();

    System.out.println("업데이트된 password : " + CheckPass);
    System.out.println("업데이트된 name : " + CheckName);


%>
<!-- Form-->
<div class="form">
    <div class="form-toggle"></div>
    <div class="form-panel one">
        <div class="form-header">
            <h1>결과창</h1>
        </div>

        <div class="form-content">
            <form:form action="/login" method="post">

                Username : <%= CheckUsername %> <br>
                Name    : <%= CheckName %> <br>
                Password : <%= Password %> <br>
                Email    : <%= CheckEmail %> <br>
                <div class="form-group">
                    <button type="submit">확인했습니다.</button>
                </div>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>


