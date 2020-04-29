package school.tss.shop.exceptions.cart;

public class InvalidProductIdException extends Exception {
	public InvalidProductIdException() {
	}

	public InvalidProductIdException(String message) {
		super(message);
	}

	public InvalidProductIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidProductIdException(Throwable cause) {
		super(cause);
	}

	public InvalidProductIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
