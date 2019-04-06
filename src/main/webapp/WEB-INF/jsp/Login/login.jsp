<!doctype html>
<html lang="kr">
	<head>
	<meta charset="UTF-8">
	<title>login</title>
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
      <h1>Account Login</h1>
    </div>
    <div class="form-content">
      <form action="loginCheck.jsp" method="post">
        <div class="form-group">
          <label for="username">Username</label>
          <input type="text" id="username" name="username" required="required"/>
        </div>
        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" id="password" name="password" required="required"/>
        </div>
        <div class="form-group">
          <button type="submit">Log In</button>
        </div>
        <div>
          <p>
          </p>
        </div>
      </form>
      <div class="form-content">
        <form action="register" method="post">
          <div class="form-group">
            <button type="submit">Sign Up</button>
          </div>
        </form>
    </div>
  </div>
 </div>
</div>
</body>
</html>
