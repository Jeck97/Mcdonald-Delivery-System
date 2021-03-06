package mcdelivery.controller.servlet;

import java.io.IOException;

import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mcdelivery.controller.handler.BankServiceHandler;
import mcdelivery.controller.handler.ReloadHandler;
import mcdelivery.controller.validator.exception.AccountAutenticateFailedException;
import mcdelivery.controller.validator.exception.AccountBalanceInsufficientException;
import mcdelivery.model.Account;
import mcdelivery.model.Customer;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/bankService/transactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get previous path from session
		HttpSession session = request.getSession();
		String pathContinue = session.getAttribute("pathContinue").toString();
		String tracedPathBank = session.getAttribute("tracedPathBank").toString();
		
		// Reload the McWallet
		if(tracedPathBank.contains("reloadMcWallet")) {
			
			// Get customer and amount reloaded from session
			Customer customer = (Customer) session.getAttribute("customer");
			double amount = Double.parseDouble(session.getAttribute("amount").toString());
			
			// Process the reload
			ReloadHandler reloadHandler = new ReloadHandler();
			reloadHandler.processReload(customer, amount);
			
		}
		
		// Redirect to previous path
		response.sendRedirect(pathContinue);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get account number and password from form
		long accountNumber = Long.parseLong(request.getParameter("account_number"));
		String password = request.getParameter("password");
		
		// Get amount from session
		HttpSession session = request.getSession();
		double amount = Double.parseDouble(session.getAttribute("amount").toString());
		
		try {
			
			// Request the transaction to bank service
			BankServiceHandler bankHandler = new BankServiceHandler();
			Account account = bankHandler.transaction(accountNumber, password, amount);
			
			// Set account into session
			session.setAttribute("account", account);
			
			// Redirect to transaction success page
			response.sendRedirect("transactionSuccessPage.jsp");
			
			
		} catch (AccountNotFoundException | AccountAutenticateFailedException | 
				AccountBalanceInsufficientException e1) {
			
			// Set the error message
			session.setAttribute("errorMessage", e1.getMessage());
			
			// Keep the account number in the text field
			session.setAttribute("account_number", accountNumber);
			
			// Redirect back to the transaction page
			response.sendRedirect("transactionForm.jsp");
			
		}		
		
	}

}
