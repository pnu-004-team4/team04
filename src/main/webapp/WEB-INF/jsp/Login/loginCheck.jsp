<%@ page import ="com.team04.musiccloud.auth.Account" language= "java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 데이터</title>
</head>
<body>


<%
    //commit 수정
	request.setCharacterEncoding("UTF-8");

	String Username = request.getParameter("username");
	String Password = request.getParameter("password");

//	// 세션 저장.
//	HttpSession sess = request.getSession();
//
//	UserInform VO = new UserInform();
//
//	//@TODO 데이터베이스에서 받아와서 세션에 저장할 것.
//
//	VO.setUserID(Username);
//	VO.setpw(Password);
//	VO.setEmail("mks487@pusan.ac.kr");
//	VO.setName("MunKS");
//	VO.setSessID(sess.getId());
//	sess.setAttribute("UserVO", VO); // 받아올 때는 UserInform 객체로 형변환해서 받아오면 된다.
//
//	String sessID = VO.getSessID();
//
//	System.out.println("session id = " + sessID);
%>

	<h1>로그인 정보</h1> <br/>
	Username: <%=Username %> <br/><br/>
	Password: <%=Password %> <br/><br/>
	<h2><a href = "logout">logout</a></h2>

</body>
</html>