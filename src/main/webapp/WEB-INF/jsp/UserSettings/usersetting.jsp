<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import ="com.team04.musiccloud.auth.Account" import ="com.team04.musiccloud.auth.FaceAccount" language= "java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

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
<body>

<%
    FaceAccount fake = new FaceAccount();
    Account acc = fake.fakeaccount();
    String username = acc.getName();
    String getemail = acc.getEmail();

%>


<!-- Form-->
<div class="form">
    <div class="form-toggle"></div>
    <div class="form-panel one">
        <div class="form-header">
            <h1>유저 세팅</h1>
        </div>
        <div class="form-content">
            <form:form action="/setcheck" method="post">
                <div class="form-group">
                    <label for="name">name</label>
                    <input type="text" id="name" name="name" value = "<%=username %>"/>
                </div>
<%--                <div class="form-group">--%>
<%--                    <label for="username">Username</label>--%>
<%--                    <input type="text" id="username" name="username" required="required"/>--%>
<%--                </div>--%>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password"/>
                </div>
<%--                <div class="form-group">--%>
<%--                    <label for="cpassword">Confirm Password</label>--%>
<%--                    <input type="password" id="cpassword" name="cpassword" required="required"/>--%>
<%--                </div>--%>
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" value = "<%= getemail %>" readonly/>
                </div>
                <div class="form-group">
                    <button type="submit">Create Account</button>
                </div>
            </form:form>
        </div>
    </div>
</div>


</body>
</html>
