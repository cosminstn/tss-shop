package school.tss.shop.exceptions.cart;

public class InvalidQtyException extends Exception {

	public InvalidQtyException() {
	}

	public InvalidQtyException(String message) {
		super(message);
	}

	public InvalidQtyException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidQtyException(Throwable cause) {
		super(cause);
	}

	public InvalidQtyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
