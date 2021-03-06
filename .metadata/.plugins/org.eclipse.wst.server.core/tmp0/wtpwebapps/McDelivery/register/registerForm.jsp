<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>

	<h1>McDelivery Registration</h1>
	
	<p>
		Please enter the information below.<br>
		Click <b>Register</b> button to register a new McDelivery account.<br>
		Click <b>Back to Login Page</b> to the Login Page.
	</p>

	<hr>

	<form action="registerServlet" method="post">
	
		<div style="color: #FF0000;">${errorMessage}</div><br>
		
		<table>
			<tr>
				<td><label>Name:</label></td> 
				<td><input type="text" name="name" value="${name}" size="30" maxlength="45" title=" Full Name (as per IC/Passport)" required></td>
			</tr>
			
			<tr>
				<td><label>Address 1:</label></td>
				<td><input type="text" name="address1" value="${address1}" size="30" maxlength="100" placeholder="e.g. No 2, Jalan Durian Tunggal 1" title="Residential number, street" required></td>
			</tr>
			
			<tr>
				<td><label>Address 2:</label></td> 
				<td><input type="text" name="address2" value="${address2}" size="30" maxlength="100" placeholder="e.g. Durian Tunggal Heights" title="Residential area" required></td>
			</tr> 
			
			<tr>
				<td><label>Postcode:</label></td> 
				<td><input type="number" name="postcode" value="${postcode}" size="30" min="1000" max="99999" placeholder="e.g. 76100" title="Postal code of the area" required></td>
			</tr> 
			
			<tr>
				<td><label>Area:</label></td> 
				<td><input type="text" name="area" value="${area}" size="30" maxlength="45" placeholder="e.g. Durian Tunggal" title="Area" required></td>
			</tr> 
			
			<tr>
				<td><label>State:</label></td> 
				<td><input type="text" name="state" value="${state}" size="30" maxlength="45" placeholder="e.g. Melaka" title="State" required></td>
			</tr> 
			
			<tr>
				<td><label>Username:</label></td> 
				<td><input type="text" name="username" value="${username}" size="30" maxlength="20" title="Unique name that use in the application" required></td>
			</tr> 
			
			<tr>
				<td><label>Password:</label></td>
				<td><input type="password" name="password" size="30" minlength="8" maxlength="20" title="Minimum 8 charaters" required></td>
			</tr>
		</table>
		
		<br><br>
		
		<input type="submit" value="Register">

	</form>

	<br>
	
	<a href="../login/loginForm.jsp">Back to Login Page</a>
	
	<% 
		session.removeAttribute("errorMessage"); 
		session.removeAttribute("name");
		session.removeAttribute("address1");
		session.removeAttribute("address2");
		session.removeAttribute("postcode");
		session.removeAttribute("area");
		session.removeAttribute("state");
		session.removeAttribute("username");
	%>

</body>
</html>