package bank.rest.controller.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import bank.rest.controller.handler.TransactionHandler;
import bank.rest.controller.manager.AccountDataManager;
import bank.rest.model.Account;


/**
 * This class represent the service provides through the web.
 * 
 * This class tells the framework when the URI is 
 * -> http://localhost:8085/BankServiceProvider/services/bank
 * 
 * It is referring to all the services declared in this class.
 * 
 * @author Tiang King Jeck
 *
 */
@Path("/bank")
public class BankService {


	/**
	 * This method process the transaction using the account given from client
	 * 
	 * It will validate the existence of account, authentication of account 
	 * and sufficiency of account balance to process the transaction orderly
	 * 
	 * @param amount
	 * @param account
	 * 
	 * @return the error message if requirement not match, otherwise 
	 * return the account with all information except the password
	 */
	@POST
	@Path("/transaction/{amount}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response transaction(@PathParam("amount") double amount, Account account) {

		long accountNumber = account.getAccountNumber();

		// Find account in account manager by account number
		AccountDataManager accountDataMngr = new AccountDataManager();
		Account accountInUsed = accountDataMngr.getAccount(accountNumber);

		// Response a message if the account is not found
		if(accountInUsed == null)
			return Response.status(Status.NOT_FOUND)
					.entity("Account " + accountNumber + " is not found")
					.type(MediaType.TEXT_PLAIN).build();
		
		// Instantiate a handler to handle the transaction of this account
		TransactionHandler transactionHandler = new TransactionHandler(accountInUsed);

		// Response a message if the password is not match
		if(!transactionHandler.isPasswardMatch(account.getPassword())) 
			return Response.status(Status.PRECONDITION_FAILED)
					.entity("Account number or password is wrong")
					.type(MediaType.TEXT_PLAIN).build();

		// Response a message if the balance is insufficient
		if(!transactionHandler.isBalanceSufficient(amount))
			return Response.status(Status.EXPECTATION_FAILED)
					.entity("Insufficient amount balance in account " + accountNumber)
					.type(MediaType.TEXT_PLAIN).build();
		
		// Update balance amount
		transactionHandler.updateBankAccountBalance(amount);

		// Response the account that exclude the password if all requirement is satisfied
		return Response.ok(transactionHandler.getAccountInUsedExcludePassword(), 
				MediaType.APPLICATION_JSON).build();

	}


}
