package school.tss.shop.exceptions.auth.register;

public class UsernameTakenException extends UserRegistrationException {

	public UsernameTakenException() {
		super("This username is already taken. Please choose another one!");
	}
}
