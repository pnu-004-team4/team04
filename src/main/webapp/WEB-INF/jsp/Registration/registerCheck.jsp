<%@ page import="com.mongodb.MongoWriteException" %>
<%@ page import="com.team04.musiccloud.auth.Account" %>
<%@ page import="com.team04.musiccloud.auth.EmailServiceImpl" %>
<%@ page import="com.team04.musiccloud.db.AccountCustomRepository" %>
<%@ page import="javax.mail.MessagingException" %>
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
    String currentUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "");
    Account account = new Account();
    AccountCustomRepository accountRepository = new AccountCustomRepository();
    EmailServiceImpl service = new EmailServiceImpl();
    String name = request.getParameter("name");
    String password = request.getParameter("password");
    String email = request.getParameter("email");

    account.setName(name);
    account.setPassword(password);
    account.encodePassword();
    account.setEmail(email);
    account.setApproval(false);
    try {
        service.sendAuthMail(account, currentUrl);
    } catch (MessagingException e) {
        out.println(e.toString());
    }

    //중복 가입 방지
    try {
        accountRepository.registerAccount(account);
    } catch (MongoWriteException e) {
        out.println("<script>alert('This email is already registered');" +
                "location.href=\"login\"</script>");
    }

    out.println("<script>alert('Registration Complete! Please check your email to verify your account.');</script>");
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
            <<div class="form-group">
            <button type="button" onclick="location.href='login'">Go back to Login page</button>
        </div>
        </div>
    </div>
</div>


</body>
</html>
