package mcdelivery.controller.validator.exception;

/**
 * This class throws an exception if account number 
 * not match with password
 * @author Tiang King Jeck
 *
 */
public class AccountAutenticateFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountAutenticateFailedException(String message) {
		
		super(message);
		
	}
	
}