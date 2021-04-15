package mcdelivery.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.model.Customer;

/**
 * Servlet implementation class CancelOrder
 */
@WebServlet("/placeOrder/cancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get customer from session
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		// Delete whole session
		session.invalidate();
		
		// Set customer into new session
		HttpSession newSession = request.getSession(true);
		newSession.setAttribute("customer", customer);
		newSession.setAttribute("numberOfOrder", "0");
		
		// Display cancel order success message
		PrintWriter	writer = response.getWriter();
		writer.append("<html><head><meta charset=\"ISO-8859-1\">");
		writer.append("<title>Cancel Order Successful</title></head>");
		writer.append("<body><h3>You order had been cancelled successfully</h3>");
		writer.append("<a href=\"orderForm.jsp\">Click here to continue</a>");
		writer.append("</body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
