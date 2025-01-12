package delivery.app.exceptions;

public class DatabaseInsertionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseInsertionException(String message) {
        super(message);
    }
}