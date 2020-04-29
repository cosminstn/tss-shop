package school.tss.shop.exceptions.cart;

public class QtyTooLargeException extends Exception {

	public QtyTooLargeException() {
	}

	public QtyTooLargeException(String message) {
		super(message);
	}

	public QtyTooLargeException(String message, Throwable cause) {
		super(message, cause);
	}

	public QtyTooLargeException(Throwable cause) {
		super(cause);
	}

	public QtyTooLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
