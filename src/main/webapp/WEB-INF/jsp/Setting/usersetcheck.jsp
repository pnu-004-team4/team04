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
<%@ page import="com.team04.musiccloud.auth.Account" language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="com.team04.musiccloud.db.AccountCustomRepository" %>

<!doctype html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Settings</title>
    <link rel="stylesheet" href="/css/setting.css">

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
    String Password = request.getParameter("password");
    String Email = request.getParameter("email");
    String Name = request.getParameter("name");

    String CurPass, CurName;
    String CheckEmail, CheckName;
    boolean isTranscode = false;

    //기존의 내용 불러오기.
    acc = repository.findAccountByEmail(Email);

    CurPass = acc.getPassword();
    CurName = acc.getName();

    //빈칸인지 아닌지 확인한 후 빈칸이 아니면 업데이트.
    if (!Name.equals("")) {
        CurName = Name;
    }
    if (!Password.equals("")) {
        CurPass = Password;
    }
    if (request.getParameter("radio-btn").equals("yes")) {
        isTranscode = true;
    }

    EncodeAcc.setPassword(CurPass);
    EncodeAcc.encodePassword();
    CurPass = EncodeAcc.getPassword();

    CheckName = CurName;
    CheckEmail = Email;
%>
<!-- Form-->
<div class="container">
    <div class="settings dark">
        <div class="row">
            <header>
                <h1>Setting Result</h1>
            </header>
        </div>

        <div class="row">
            <section class="user">
                <form:form action="/setcheck" method="get">
                <input type="hidden" name="email" value="<%=Email%>">
                <input type="hidden" name="password" value="<%=CurPass%>">
                <input type="hidden" name="name" value="<%=CurName%>">
                <input type="hidden" name="isTranscode" value="<%=isTranscode%>">
                <h2>Name</h2>
                <p><%= CheckName %>
                </p>
                <h2>Password</h2>
                <p><%= Password %>
                </p>
                <h2>Email</h2>
                <p><%= CheckEmail %>
                </p>
                <p></p>
            </section>
            <section class="music">
                <p></p>
                <p></p>
                <p></p>
                <div class="button">
                    <button type="submit">Confirm</button>
                </div>
            </section>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>


