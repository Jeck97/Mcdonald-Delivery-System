package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.controller.validator.Validator;
import mcdelivery.controller.validator.exception.LoginFailedException;
import mcdelivery.model.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		// Get username and password from form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Wrap into customer object
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);

		try {
			
			// Validate login
			customer = Validator.validateLogin(customer);

			// Add the logged in customer object to session
			HttpSession session = request.getSession();
			session.setAttribute("customer", customer);
			session.setAttribute("numberOfOrder", "0");

			// Redirect to the main page
			response.sendRedirect("../placeOrder/orderForm.jsp");
			
		} catch (LoginFailedException e) {

			// Set the error message
			request.getSession().setAttribute("errorMessage", e.getMessage());

			// Keep the username in the text field
			request.getSession().setAttribute("username", request.getParameter("username"));

			// Redirect back to the login page
			response.sendRedirect("loginForm.jsp");
			
		}
		
	}

}
