package mcdelivery.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReloadMcWalletServlet
 */
@WebServlet("/reloadMcWallet/reloadMcWalletServlet")
public class ReloadMcWalletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReloadMcWalletServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Trace the previous URL path
		String tracedPathReload = request.getHeader("referer");
		
		// Change path if the path come from insufficient balance page
		if(tracedPathReload.contains("insufficientBalancePage"))
			tracedPathReload = "/McDelivery/placeOrder/checkOutPage.jsp";
		
		// Set the path into session
		request.getSession().setAttribute("tracedPathReload", tracedPathReload);
		request.getSession().setAttribute("pathContinue", tracedPathReload);
		
		// Redirect to reload page
		response.sendRedirect("reloadMcWalletForm.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get reload amount from request
		double amount = Double.parseDouble(request.getParameter("amount"));
		
		// Set amount into session
		request.getSession().setAttribute("amount", amount);
		
		// Trace the previous URL path
		String tracedPathBank = request.getHeader("referer");
		
		// Set the path into session
		request.getSession().setAttribute("tracedPathBank", tracedPathBank);
		
		// Redirect to bank service
		response.sendRedirect("../bankService/transactionForm.jsp");
		
	}

}
