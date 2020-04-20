package school.tss.shop.exceptions.auth;

public class BadCredentialsException extends AuthenticationException{

	public BadCredentialsException() {
	}

	public BadCredentialsException(String message) {
		super(message);
	}

	public BadCredentialsException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadCredentialsException(Throwable cause) {
		super(cause);
	}
}
