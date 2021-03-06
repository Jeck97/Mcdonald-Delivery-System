<%@page import="mcdelivery.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="mcdelivery.controller.facade.ProductFacade"%>
<%@page import="mcdelivery.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	// Get the customer from session
	Customer customer = (Customer) request.getSession().getAttribute("customer");

	// Get the product list from database
	ProductFacade productFacade = new ProductFacade();
	List<Product> products = productFacade.selectProducts();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Page</title>
</head>
<body>

	<h1>McDelivery Home Page</h1>
	
	<span style="font-size:20px;"><%= customer.getUsername() %></span>
	
	<a href="../logout/logoutServlet" style="font-size:20px; float:right;"><button>Logout</button></a>
	
	<p>
		Select one of the item and specify the quantity of the selected item.<br> 
		Click the <b>McWallet Balance</b> to reload McWallet.<br>
		Click <b>Add to Cart</b> to add product into cart.<br>
		Click <b>Check Out Order</b> to calculate the total amount of order.
	</p>
	
	<hr>
	
	<p style="text-align:right;">
		<a href="../reloadMcWallet/reloadMcWalletServlet">
			McWallet Balance: RM <%= String.format("%.2f", customer.getMcWalletBalance()) %>
		</a>
	</p>

	<div style="font-size:20px"><b>Current Ordered Product: ${numberOfOrder}</b></div>

	<form action="placeOrderServlet" method="post">

		<p style="font-size:18px">Please select a product and enter the quantity :</p>
		
		<table>
			<% for(Product product : products) { %>
			<% int productId = product.getId(); %>
			
				<tr title="<%= product.getType().getName() %>">
					<td><input type="radio" id="<%= productId %>" name="product" value="<%= productId %>" required></td> 
					<td><label for="<%= productId %>"><%= product.getName() %></label></td>
					<td><label for="<%= productId %>"> - RM <%= String.format("%.2f", product.getPrice()) %></label></td>
				</tr>
			
			<% } %>
		</table><br>
		
		<label for="quantity">Quantity: </label>
		<input type="number" id="quantity" name="quantity" min="1" required title="Quantity of product"><br><br><br>
		
		<input type="submit" value="Add to Cart"> &nbsp;
		
		<a href="checkOutServlet"><button type="button">Check Out Order</button></a>
		
		
	</form><br>
	
</body>
</html>