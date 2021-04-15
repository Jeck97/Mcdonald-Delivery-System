package mcdelivery.controller.validator.exception;

/**
 * This class throws an exception if balance amount of account
 * is not sufficient to process the transaction
 * @author Tiang King Jeck
 *
 */
public class AccountBalanceInsufficientException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountBalanceInsufficientException(String message) {
		
		super(message);
		
	}
	
}