<%@page import="mcdelivery.model.Customer"%>
<%@page import="java.time.LocalDate"%>
<%@page import="mcdelivery.model.OrderedProduct"%>
<%@page import="java.util.List"%>
<%@page import="mcdelivery.model.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	// Get customer for displaying customer information
	Customer customer = (Customer) session.getAttribute("customer");
			
	// Get order to display ordered products in the list
	Order order = (Order) session.getAttribute("order");
	List<OrderedProduct> orderedProducts = order.getOrderedProducts();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Cart</title>
</head>
<body>

	<h1>McDelivery Order Cart</h1>
	
	<span style="font-size:20px;"><%= customer.getUsername() %></span>
	
	<a href="../logout/logoutServlet" style="font-size:20px; float:right;"><button>Logout</button></a>
	
	<p>
		Please check the list of ordered products and customer information before proceed to payment.<br> 
		Click the <b>McWallet Balance</b> to reload McWallet.<br>
		Click <b>Add More Product</b> to add more product into cart.<br>
		Click <b>Payment</b> to pay the order using selected method.<br>
		Click <b>Cancel Order</b> to cancel this order.<br>
	</p>

	<hr>
	
	<p style="text-align:right;">
		<a href="../reloadMcWallet/reloadMcWalletServlet">
			McWallet Balance: RM <%= String.format("%.2f", customer.getMcWalletBalance()) %>
		</a>
	</p>

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

	<form action="paymentMethodServlet" method="post">
	
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
			
			<%
				
				String textColor = new String();
				String walletStatus = new String();
				
				// Check wether the McWallet is sufficient
				boolean sufficient = (customer.getMcWalletBalance() >= order.getAmountAfterTax());
				session.setAttribute("sufficient", sufficient);
			
				if(sufficient) {
					
					// Green if sufficient
					textColor = "#00FF00";
					walletStatus = "RECOMMENDED";
					
				} else {
					
					// Red if insufficient
					textColor = "#FF0000";
					walletStatus = "INSUFFICIENT";
					
				}
				
			
			%>
			
			<tr>
				<td><label>Payment Method</label></td>
				<td>: <input type="radio" id="mc_wallet" name="payment_method" value="1" checked="checked" required>
					<label for="mc_wallet">McWallet</label> &nbsp;
					<span style="color:<%= textColor %>;"><b>(<%= walletStatus %>)</b></span></td>
			</tr>
			<tr>
				<td></td>
				<td>&nbsp;&nbsp;<input type="radio" id="interbank" name="payment_method" value="2" required>
					<label for="interbank">Interbank</label></td>
			</tr>
		</table>
		
		<br><br>
		
		<a href="orderForm.jsp"><button type="button">Add More Product</button></a> &nbsp;
		<input type="submit" value="Payment"> &nbsp; &nbsp; &nbsp;
		<a href="cancelOrderPage.jsp"><button type="button">Cancel Order</button></a>
		
	</form>

</body>
</html>