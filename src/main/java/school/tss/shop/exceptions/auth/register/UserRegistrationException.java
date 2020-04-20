package school.tss.shop.exceptions.auth.register;

public class UserRegistrationException extends Exception {

	public UserRegistrationException() {
	}

	public UserRegistrationException(String message) {
		super(message);
	}

	public UserRegistrationException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserRegistrationException(Throwable cause) {
		super(cause);
	}
}
