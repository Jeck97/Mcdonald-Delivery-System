package mcdelivery.controller.validator;

import javax.security.auth.login.AccountNotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import mcdelivery.controller.facade.CustomerFacade;
import mcdelivery.controller.validator.exception.AccountAutenticateFailedException;
import mcdelivery.controller.validator.exception.AccountBalanceInsufficientException;
import mcdelivery.controller.validator.exception.LoginFailedException;
import mcdelivery.controller.validator.exception.UserDuplicatedException;
import mcdelivery.model.Customer;

public class Validator {


	/**
	 * This static method validates the login process of the visitor
	 * @param customer
	 * @return Validated customer
	 * @throws LoginFailedException
	 */
	public static Customer validateLogin(Customer customer) throws LoginFailedException {

		// Get the customer from database by username and password
		CustomerFacade customerFacade = new CustomerFacade();
		Customer dbCustomer = customerFacade.selectCustomer(customer);

		// Throw exception if the customer not found in database
		if(dbCustomer.getId() == 0)
			throw new LoginFailedException();

		return dbCustomer;

	}

	
	
	/**
	 * This static method validates the register process of the visitor
	 * @param customer
	 * @throws UserDuplicatedException
	 */
	public static void validateRegister(Customer customer) throws UserDuplicatedException {

		// Get the count of customer from database by username
		CustomerFacade customerFacade = new CustomerFacade();
		int count = customerFacade.countCustomer(customer);
		
		// Throw exception if the customer username is not duplicated in database
		if(count != 0)
			throw new UserDuplicatedException(customer.getUsername());

	}
	
	
	/**
	 * This static method validates the transaction process of an account
	 * @param response
	 * @throws AccountNotFoundException
	 * @throws AccountAutenticateFailedException
	 * @throws AccountBalanceInsufficientException
	 * @return account object in JSON String format
	 */
	public static String validateTransaction(Response response) 
			throws AccountNotFoundException, 
			AccountAutenticateFailedException, 
			AccountBalanceInsufficientException {
		
		// Get the status from response
		int status = response.getStatus();
		
		// Read the result in String from response
		String result = response.readEntity(String.class);
		
		// Throw exception if the account not found from bank service
		if(status == Status.NOT_FOUND.getStatusCode())
			throw new AccountNotFoundException(result);
			
		// Throw exception if the password not match with the account	
		if(status == Status.PRECONDITION_FAILED.getStatusCode())
			throw new AccountAutenticateFailedException(result);
			
		// Throw exception if the balance of account is insufficient	
		if(status == Status.EXPECTATION_FAILED.getStatusCode())
			throw new AccountBalanceInsufficientException(result);
		
		return result;
		
	}
	
}
