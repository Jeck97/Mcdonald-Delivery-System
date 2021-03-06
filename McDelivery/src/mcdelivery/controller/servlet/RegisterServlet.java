package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mcdelivery.controller.facade.CustomerFacade;
import mcdelivery.controller.validator.Validator;
import mcdelivery.controller.validator.exception.UserDuplicatedException;
import mcdelivery.model.Customer;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register/registerServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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

		// Get data from form and parse to appropriate type
		String name = request.getParameter("name");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		int postcode = Integer.parseInt(request.getParameter("postcode"));
		String area = request.getParameter("area");
		String state = request.getParameter("state");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Wrap into customer object
		Customer customer = new Customer();
		customer.setName(name);
		customer.setAddress1(address1);
		customer.setAddress2(address2);
		customer.setPostcode(postcode);
		customer.setArea(area);
		customer.setState(state);
		customer.setUsername(username);
		customer.setPassword(password);
		
		try {

			// Validate register
			Validator.validateRegister(customer);
			
			// Add the new customer into database and set the ID generated
			CustomerFacade customerFacade = new CustomerFacade();
			int customerId = customerFacade.addCustomer(customer);
			customer.setId(customerId);

			// Forward to the success page page
			request.getRequestDispatcher("registerSuccessPage.html").forward(request, response);

		} catch (UserDuplicatedException e) {

			// Set the error message
			request.getSession().setAttribute("errorMessage", e.getMessage());

			// Keep the data in the text field
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("address1", address1);
			request.getSession().setAttribute("address2", address2);
			request.getSession().setAttribute("postcode", postcode);
			request.getSession().setAttribute("area", area);
			request.getSession().setAttribute("state", state);
			request.getSession().setAttribute("username", username);

			// Forward to the same page
			response.sendRedirect("registerForm.jsp");

		}

	}

}
