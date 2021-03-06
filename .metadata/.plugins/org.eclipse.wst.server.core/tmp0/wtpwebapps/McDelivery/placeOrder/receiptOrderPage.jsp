<%@page import="mcdelivery.model.Customer"%>
<%@page import="java.time.LocalDate"%>
<%@page import="mcdelivery.model.OrderedProduct"%>
<%@page import="java.util.List"%>
<%@page import="mcdelivery.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	// Get order to display ordered products in the list
	Order order = (Order) session.getAttribute("order");
	List<OrderedProduct> orderedProducts = order.getOrderedProducts();
	
	// Get customer for displaying customer information
	Customer customer = order.getCustomer();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Receipt Page</title>
</head>
<body>

	<h1>McDelivery Order Receipt</h1>

	<h3>
		Payment has successfully processed.<br> 
		Your order is currently being prepared.
	</h3>

	<hr>
	
	<h3>Order Number: <%= order.getId() %></h3>

	<h3>List of Ordered Products</h3>
	
	<table style="width:auto; padding-right:100px;">
		<tr>
			<th style="text-align:left;">Product</th>
			<th style="text-align:right; padding-right:10px;">Quantity</th>
			<th style="text-align:right; padding-right:10px;">Unit Price (RM)</th>
			<th style="text-align:right;">Total Price (RM)</th>
		</tr>

		<%
			// Loop for getting and displaying products' information
			for (OrderedProduct orderedProduct : orderedProducts) {
				String productName = orderedProduct.getProduct().getName();
				int quantity = orderedProduct.getQuantity();
				String unitPrice = String.format("%.2f", orderedProduct.getProduct().getPrice());
				String totalPrice = String.format("%.2f", orderedProduct.getSubtotal());
		%>

		<tr>
			<td style="text-align:left;"><%=productName%></td>
			<td style="text-align:right; padding-right:10px;"><%=quantity%></td>
			<td style="text-align:right; padding-right:10px;"><%=unitPrice%></td>
			<td style="text-align:right;"><%=totalPrice%></td>
		</tr>

		<% } %>
		
	</table>
	<br>
	<br>

	<%
		// Get the total amount before and after tax
		String amountBeforeTax = String.format("%.2f", order.getAmountBeforeTax());
		String serviceTax = String.format("%.2f", order.getServiceTax());
		String amountAfterTax = String.format("%.2f", order.getAmountAfterTax());
	%>

	<table>
		<tr>
			<td>Total Amount (before tax)</td>
			<td>: <b>RM <%=amountBeforeTax%></b></td>
		</tr>
		<tr>
			<td>Service Tax (6%)</td>
			<td>: <b>RM <%=serviceTax%></b></td>
		</tr>
		<tr>
			<td>Total Amount (after tax)</td>
			<td>: <b> RM <%=amountAfterTax%></b></td>
		</tr>
	</table>
	
	<br>

	<span>This order is made on <b><%=LocalDate.now().toString()%></b></span><br>
	
	<hr style="width:50%; text-align:left;">

	<h3>Customer Information</h3>
	
	<table>
		<tr>
			<td>Customer Name</td>
			<td>: <b><%= customer.getName() %></b></td>
		</tr>
		<tr>
			<td>Delivery Address</td>
			<td>: <b><%=customer.getAddress1()%>,</b></td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;&nbsp;<b><%=customer.getAddress2()%>,</b></td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;&nbsp;<b><%=customer.getPostcode()%>, <%=customer.getArea()%>,</b></td>
		</tr>
		<tr>
			<td></td>
			<td>&nbsp;&nbsp;<b><%=customer.getState()%>.</b></td>
		</tr>
		<tr>
			<td><label>Payment Method</label></td>
			<td>: <b><%= (order.getPaymentMethod() ==  1) ? "McWallet" : "Interbank"%></b></td>
		</tr>
	</table>
		
	<br>
		
	<p style="font-size:20px">
		<b>McWallet Balance: RM <%= String.format("%.2f", customer.getMcWalletBalance()) %></b>
	</p>
		
	<br><br>
		
	<form action="paymentServlet" method="post">
		<input type="submit" value="Back to Order Page">
	</form>

</body>
</html>