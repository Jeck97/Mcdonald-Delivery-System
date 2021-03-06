<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
	String amount = String.format("%.2f", session.getAttribute("amount"));
	String tracedPathBank = session.getAttribute("tracedPathBank").toString();
%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Page</title>
</head>
<body>

	<br>

	<hr>

	<h1 style="text-align: center;">Bank Service</h1>

	<hr>

	<br>
	
	<div style="color: #FF0000; text-align: center; font-size: 15px;">${errorMessage}</div>
	
	<br>

	<form action="transactionServlet" method="post">

		<table style="align-content: space-between; margin: auto; width: auto; height: 100px;">
			<tr style="font-size: 20px">
				<td><label for="account_number">Account Number:</label></td>
				<td><input id="account_number" name="account_number" type="number" value="${account_number}" required></td>
			</tr>
			<tr style="font-size: 20px">
				<td><label for="password">Password:</label></td>
				<td><input id="password" name="password" type="password" required></td>
			</tr>
			<tr style="font-size: 20px">
				<td><label>Amount:</label></td>
				<td><b>RM <%= amount %></b></td>
			</tr>
		</table>
		
		<br><br>
		
		<div style="text-align: center;">
			<input type="submit" value="Transact"> &nbsp;
			<a href="<%= tracedPathBank %>"><button type="button">Cancel</button></a>
		</div>

	</form>
	
	<br><br>
	
	<hr>
	
	<%
		session.removeAttribute("errorMessage");
		session.removeAttribute("account_number");
	%>

</body>
</html>