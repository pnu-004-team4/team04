<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
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

<!-- flexbox container -->
<div class="container">
    <div class="settings dark">
        <form:form action="#" method="POST">
            <div class="row">
                <header>
                    <h1>settings</h1>
                </header>
            </div>

            <div class="row">
                <section class="user">
                    <h2>User Account</h2>
                    <input type="email" name="email" value="moods@moodmuzik.com">
                    <input type="password" name="password" value="settings1">
                </section>
            </div>

            <div class="row">
                <section class="music">
                    <h2>Username</h2>
                    <p>abc</p>
                    <h2>Name</h2>
                    <p>jongjin</p>
                    <h2>Resolution use</h2>
                    <p>
                        <input type="radio" id="radio1" name="radio-btn"/>Use
                        <input type="radio" id="radio2" name="radio-btn"/>No
                    </p>
                    <p></p>
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
