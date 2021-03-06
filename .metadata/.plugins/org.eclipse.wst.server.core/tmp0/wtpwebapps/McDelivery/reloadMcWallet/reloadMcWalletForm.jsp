<%@page import="mcdelivery.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	// Get the customer from session
	Customer customer = (Customer) request.getSession().getAttribute("customer");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reload Page</title>
</head>
<body>

	<h1>McDelivery Reload McWallet</h1>
	
	<span style="font-size:20px;"><%= customer.getUsername() %></span>
	
	<a href="../logout/logoutServlet" style="font-size:20px; float:right;"><button>Logout</button></a>
	
	<p>
		Please enter the amount you want to reload.<br>
		Click <b>Reload</b> button to reload the McWallet.<br>
		Click <b>Back</b> to return to previous page.
	</P>
	
	<hr>
	
	<h3>Current McWallet Balance: RM <%= String.format("%.2f", customer.getMcWalletBalance()) %></h3>
	
	<br>
	
	<form action="reloadMcWalletServlet" method="post">
	
		<label for="amount">Reload Amount: RM</label> 
		<input type="number" id="amount" name="amount" step="0.01" min="10" title="Minimum reload amount is RM 10.00" placeholder="10.00" required>
		<input type="submit" value="Reload">

	</form>

	<br><br>
	
	<a href="<%= session.getAttribute("tracedPathReload") %>">Back</a>
	
</body>
</html>