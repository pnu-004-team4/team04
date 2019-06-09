<%@ page import="com.team04.musiccloud.auth.Account" %>
<%@ page import="com.team04.musiccloud.db.AccountCustomRepository" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



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

<%
    String email;
    String name;

    AccountCustomRepository repository = new AccountCustomRepository();
    Account SavedAccount;

    //session에 등록된 account 정보.
    Object account = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    //여기서 세션에 저장된 username, 로그인에 쓰인 username을 받는다.
    //따라서 email을 받는 것임.
    if (account instanceof UserDetails) {
        email = ((UserDetails) account).getUsername();
    } else {
        email = account.toString();
    }

    SavedAccount = repository.findAccountByEmail(email);

    name = SavedAccount.getName();

    System.out.println("name : " + name);

%>


<body>

<!-- flexbox container -->
<div class="container">
    <div class="settings dark">
        <form:form action="/setcheck" method="post">
            <div class="row">
                <header>
                    <h1>settings</h1>
                </header>
            </div>

            <div class="row">
                <section class="user">
                    <h2>User Account</h2>
                    <input type="email" name="email" readonly
                           value=<%= email%>  required="required">
                    <h2>Password</h2>
                    <input type="password" name="password" required="required">
                    <h2>Name - alphabet only</h2>
                    <input type="text" pattern="^[a-zA-Z]*$" name="name"
                           value=<%= name%> required="required">
                </section>
            </div>

            <div class="row">
                <section class="music">
                    <h2>Resolution use</h2>
                    <p>
                        <input type="radio" id="radio1" name="radio-btn" value="yes"/>Use
                        <input type="radio" id="radio2" name="radio-btn" value="no" checked/>No
                    </p>
                    <p></p>
                    <div class="button">
                        <button type="submit">SAVE</button>
                    </div>
                </section>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
