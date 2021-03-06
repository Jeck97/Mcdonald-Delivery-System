package mcdelivery.controller.validator.exception;

/**
 * This class throws an exception if username 
 * does not match with password
 * @author Tiang King Jeck
 *
 */
public class LoginFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginFailedException() {
		
		super("Wrong username or password");
		
	}
	
}
