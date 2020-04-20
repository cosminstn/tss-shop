package school.tss.shop.exceptions.auth.register;

public class InvalidUsernameException extends UserRegistrationException {

	public InvalidUsernameException() {

	}

	public InvalidUsernameException(String message) {
		super(message);
	}

	public InvalidUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidUsernameException(Throwable cause) {
		super(cause);
	}
}
