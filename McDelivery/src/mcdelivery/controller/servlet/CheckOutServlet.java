package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.controller.handler.OrderHandler;
import mcdelivery.model.Order;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/placeOrder/checkOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckOutServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get session attribute and cast to order object
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");

		// Check whether the order is null
		if (order == null) {

			// Redirect to zero order page
			response.sendRedirect("zeroOrderPage.html");

		} else {
			
			// Calculate total quantity and total order
			OrderHandler orderHandler = new OrderHandler();
			order = orderHandler.processOrder(order);

			// Add order into session
			session.setAttribute("order", order);

			// Redirect to check out page
			response.sendRedirect("checkOutPage.jsp");
			
		}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
