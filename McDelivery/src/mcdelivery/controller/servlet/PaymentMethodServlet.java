package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.controller.handler.PaymentHandler;
import mcdelivery.model.Order;

/**
 * Servlet implementation class PaymentMethodServlet
 */
@WebServlet("/placeOrder/paymentMethodServlet")
public class PaymentMethodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentMethodServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get data from session
		HttpSession session = request.getSession();

		// Get payment method from form
		int paymentMethod = Integer.parseInt
				(request.getParameter("payment_method").toString());

		if(paymentMethod == PaymentHandler.MCWALLET) {

			// Get the validation of McWallet from session
			boolean suffient = Boolean.parseBoolean(
					session.getAttribute("sufficient").toString());

			if(suffient) {
				
				// Set payment method into session
				session.setAttribute("payment_method", paymentMethod);
				
				// Redirect to payment servlet
				response.sendRedirect("paymentServlet");
				
			} else {

				// Redirect to insufficient balance page 
				response.sendRedirect("insufficientBalancePage.html");

			}

		} else if(paymentMethod == PaymentHandler.INTERBANK) {
			
			// Set payment method into session
			session.setAttribute("payment_method", paymentMethod);

			// Set amount into session
			Order order = (Order) session.getAttribute("order");
			session.setAttribute("amount", order.getAmountAfterTax());
			
			// Trace the previous URL path
			String tracedPathBank = request.getHeader("referer");

			// Set the previous path into session
			session.setAttribute("tracedPathBank", tracedPathBank);
			
			// Set the path to go after transaction
			session.setAttribute("pathContinue", "../placeOrder/paymentServlet");

			// redirect to transaction page
			response.sendRedirect("../bankService/transactionForm.jsp");

		}

	}

}
