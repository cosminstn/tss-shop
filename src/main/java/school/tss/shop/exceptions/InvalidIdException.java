package school.tss.shop.exceptions;

public class InvalidIdException extends Exception{

	public InvalidIdException() {
	}

	public InvalidIdException(String message) {
		super(message);
	}

	public InvalidIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIdException(Throwable cause) {
		super(cause);
	}
}
