<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout Page</title>
</head>
<body>

	<h3>Are you sure want to logout from McDelivery?</h3>
	
	<form action="logoutServlet" method="post">
		<input type="submit" value="Yes"> &nbsp;
		<a href="<%= request.getAttribute("tracedPathLogout") %>"><button type="button">No</button></a>
	</form>
	
	
	
</body>
</html>