package mcdelivery.controller.validator.exception;

/**
 * This class throws an exception if account 
 * does not exist in the bank service
 * @author Tiang King Jeck
 *
 */
public class AccountNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String message) {
		
		super(message);
		
	}
	
}