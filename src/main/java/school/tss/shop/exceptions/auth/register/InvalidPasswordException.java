package school.tss.shop.exceptions.auth.register;

public class InvalidPasswordException extends UserRegistrationException {

	public InvalidPasswordException() {
		super("The password is required and must be at least 8 chars long!");
	}

}
