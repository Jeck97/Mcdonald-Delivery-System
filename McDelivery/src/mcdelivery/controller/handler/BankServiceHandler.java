package mcdelivery.controller.handler;

import javax.security.auth.login.AccountNotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mcdelivery.controller.validator.Validator;
import mcdelivery.controller.validator.exception.AccountAutenticateFailedException;
import mcdelivery.controller.validator.exception.AccountBalanceInsufficientException;
import mcdelivery.model.Account;

public class BankServiceHandler {
	
	private static final String url = "http://localhost:8085/BankServiceProvider/service/bank";
	
	private Client client;
	private WebTarget webTarget;
	
	public BankServiceHandler() {
		
		// Create JAX-RS client
		client = ClientBuilder.newClient();
		
		// Get WebTarget for URL
		webTarget = client.target(url);
		
	}
	
	
	/**
	 * This method sends request to bank service to process the transaction 
	 * using the account given from customer
	 * 
	 * @param amount
	 * @param account
	 * 
	 * @return account with information
	 * @throws JsonProcessingException 
	 * @throws AccountBalanceInsufficientException 
	 * @throws AccountAutenticateFailedException 
	 * @throws AccountNotFoundException 
	 */
	public Account transaction(long accountNumber, String password, double amount) 
			throws JsonProcessingException, AccountNotFoundException, 
			AccountAutenticateFailedException, AccountBalanceInsufficientException {
		
		// Add path
		webTarget = webTarget.path("transaction");
		
		// Add amount into path
		webTarget = webTarget.path(String.valueOf(amount));
		
		// Declare and initialize account
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		account.setPassword(password);
		
		// Parse account to JSON format
		ObjectMapper mapper = new ObjectMapper();
		String jsonAccount = mapper.writeValueAsString(account);
		
		// Execute HTTP POST method
		Entity<String> entityAccount = Entity.entity(jsonAccount, MediaType.APPLICATION_JSON);
		Response response = webTarget.request(MediaType.APPLICATION_JSON).post(entityAccount);
		
		// Validate response
		jsonAccount = Validator.validateTransaction(response);
		
		// Parse the result to account object
		account = mapper.readValue(jsonAccount, Account.class);
		
		return account;
		
	}

}
