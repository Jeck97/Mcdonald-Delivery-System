<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancel Order Page</title>
</head>
<body>

	<h3>Are you sure want to cancel this order?</h3>
	
	<a href="cancelOrderServlet"><button>Yes</button></a> &nbsp;
	<a href="<%= request.getHeader("referer") %>"><button>No</button></a>

</body>
</html>