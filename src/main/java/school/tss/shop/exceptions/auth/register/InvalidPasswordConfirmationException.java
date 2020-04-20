package school.tss.shop.exceptions.auth.register;

public class InvalidPasswordConfirmationException extends UserRegistrationException {

	public InvalidPasswordConfirmationException() {
		super("The password confirmation does not match the password!");
	}
}
