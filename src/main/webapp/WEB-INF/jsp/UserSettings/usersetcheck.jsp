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
<%@ page import="org.springframework.security.core.userdetails.User" %>
<%@ page import="com.drew.metadata.Face" %>
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

    FaceAccount fake = new FaceAccount();
    Account acc = fake.fakeaccount();

    String Username = request.getParameter("name");
    String Password = request.getParameter("password");

    if(!Username.equals("")) acc.setName(Username);

    if(!Password.equals("")) acc.setPassword(Password);

    Username = acc.getName();
    Password = acc.getPassword();

    String email = acc.getEmail();

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

                Username : <%= Username %> <br>
                Password : <%= Password %> <br>
                Email    : <%= email %> <br>

                <div class="form-group">
                    <button type="submit">확인했습니다.</button>
                </div>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>


