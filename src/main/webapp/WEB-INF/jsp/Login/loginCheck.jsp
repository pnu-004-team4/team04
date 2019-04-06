<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 데이터</title>
</head>
<body>

<%
	request.setCharacterEncoding("UTF-8");

	String Username = request.getParameter("username");
	String Password = request.getParameter("password");
	

%>

	<h1>로그인 정보</h1> <br/>
	Username: <%=Username %> <br/><br/>
	Password: <%=Password %> <br/><br/>

</body>
</html>