package mcdelivery.controller.validator.exception;

/**
 * This class throws an exception if username 
 * already exist in the database
 * @author Tiang King Jeck
 *
 */
public class UserDuplicatedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public UserDuplicatedException(String username) {
		
		super("User " + username + " already exist");
		
	}

}