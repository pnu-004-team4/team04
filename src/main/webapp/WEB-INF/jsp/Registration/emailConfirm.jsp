<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>email verification</title>
    <link rel="stylesheet" href="/css/login.css">
    <style rel="stylesheet">
        html {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="form">
    <div class="form-toggle"></div>
    <div class="form-panel one">
        <div class="form-header">
            <h1>Verification Complete!</h1>
        </div>
        <div class="form-content">
            <div class="form-group">
                <button type="button" onclick="location.href='http://35.200.2.141:13246/login'">Go to Login page</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>