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
<body>


<!-- Form-->
<div class="form">
    <div class="form-toggle"></div>
    <div class="form-panel one">
        <div class="form-header">
            <h1>Register</h1>
        </div>
        <div class="form-content">
            <form:form action="/login" method="post">
                <div class="form-group">
                    <label for="name">name</label>
                    <input type="text" id="name" name="name" required="required"/>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" required="required"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required="required"/>
                </div>
                <div class="form-group">
                    <label for="cpassword">Confirm Password</label>
                    <input type="password" id="cpassword" name="cpassword" required="required"/>
                </div>
                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" required="required"/>
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
