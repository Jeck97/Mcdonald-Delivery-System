package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.controller.handler.PaymentHandler;
import mcdelivery.model.Customer;
import mcdelivery.model.Order;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/placeOrder/paymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get data from session
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		Order order = (Order) session.getAttribute("order");
		int paymentMethod = Integer.parseInt
				(session.getAttribute("payment_method").toString());
		
		// Instantiate the payment handler to handle payment
		PaymentHandler paymentHandler = new PaymentHandler();
		
		// Set the payment method of order
		order.setPaymentMethod(paymentMethod);
		
		// Process the payment and get the order with order number
		order = paymentHandler.processPayment(customer, order);
		
		// Set order into session
		session.setAttribute("order", order);
		
		// Redirect to receipt page
		response.sendRedirect("receiptOrderPage.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get customer from session
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		// Delete whole session
		session.invalidate();
				
		// Set customer into new session
		HttpSession newSession = request.getSession(true);
		newSession.setAttribute("customer", customer);
		newSession.setAttribute("numberOfOrder", "0");
		
		// Redirect to insufficient balance page 
		response.sendRedirect("orderForm.jsp");
		
	}

}
