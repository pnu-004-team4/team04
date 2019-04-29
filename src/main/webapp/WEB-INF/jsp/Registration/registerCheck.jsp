<%@ page import="com.team04.musiccloud.auth.Account" %>
<%@ page import="com.team04.musiccloud.db.AccountCustomMethods" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <link rel="stylesheet" href="/css/login.css">
    <style rel="stylesheet">
        html {
            width: 100%;
            height: 100%;
        }
    </style>
</head>

<%
    System.out.println("Checking registration(jsp page)...");
    Account account = new Account();
    AccountCustomMethods accountRepository = new AccountCustomMethods();

    boolean password_Identical = false;

    String name = request.getParameter("name");
    String username  = request.getParameter("username");
    String password = request.getParameter("password");
    String cpassword = request.getParameter("cpassword");
    String email = request.getParameter("email");
    if(password.equals(cpassword)) {
        password_Identical = true;
        account.setName(name);
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        if(accountRepository.registerAccount(account)){
            System.out.println("Successfullly registered!!");
        }
        System.out.println("email : "+ account.getEmail() + " name : " + account.getName());
    }
    else{
        System.out.println("password not identical");
        System.out.println("password : " + password + "confirm password : " + cpassword);
    }

%>

<body>
<!-- Form-->
<div class="form">
    <div class="form-toggle"></div>
    <div class="form-panel one">
        <div class="form-header">
            <h1>Registration Complete!</h1>
        </div>
        <div class="form-content">
            <form:form action="login" method="post">
                <div class="form-group">
                    <button type="submit">Go back to Login page</button>
                </div>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>
