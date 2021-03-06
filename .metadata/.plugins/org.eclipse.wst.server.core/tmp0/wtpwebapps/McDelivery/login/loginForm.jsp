<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
</head>
<body>

	<h1>McDelivery Login</h1>
	
	<p>
		Please enter the username and password.<br>
		Click <b>Login</b> button to log into the McDelivery.<br>
		Click <b>Register a new account</b> to register a new McDelivery account.
	</P>
	
	<hr>
	
	<form action="loginServlet" method="post">
	
		<div style="color: #FF0000;">${errorMessage}</div><br>

		<label>Username:</label> 
		<input type="text" name="username" value="${username}" required autofocus="autofocus"><br><br> 
		
		<label>Password:</label>
		<input type="password" name="password" required><br><br><br><br>
		
		<input type="submit" value="Login">

	</form>

	<br>
	
	<a href="../register/registerForm.jsp">Register a new account</a>
	
	<% 
		session.removeAttribute("errorMessage"); 
		session.removeAttribute("username");
	%>
	
</body>
</html>