<%@page import="java.time.LocalDate"%>
<%@page import="mcdelivery.model.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	// Get account for displaying information
	Account account = (Account) session.getAttribute("account");
			
	// Get amount
	String amount = String.format("%.2f", session.getAttribute("amount"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Receipt Page</title>
</head>
<body>

	<br>
	<hr>

	<h1 style="text-align: center;">Transaction Receipt</h1>

	<h3 style="text-align: center;">Transaction has successfully processed.</h3>

	<hr>

	<table style="align-content: space-between; margin:auto; width: auto;">
		<tr>
			<td>Account Number:</td>
			<td><b><%= account.getAccountNumber() %></b></td>
		</tr>
		<tr>
			<td>Account Holder:</td>
			<td><b><%= account.getHolderName() %></b></td>
		</tr>
		<tr>
			<td>Account Bank:</td>
			<td><b><%= account.getBank().getName() %></b></td>
		</tr>
		<tr>
			<td>Amount Balance:</td>
			<td><b>RM <%= account.getBalanceAmount() %></b></td>
		</tr>
		<tr>
			<td>Amount Transaction:</td>
			<td><b>RM <%= amount %></b></td>
		</tr>
		<tr>
			<td>Date Transaction:</td>
			<td><b><%= LocalDate.now().toString() %></b></td>
		</tr>
	</table>
	
	<br>
	<br>

	<div style="text-align: center;"><a href="transactionServlet">Continue</a></div>
	
	<br><hr>
		
</body>
</html>