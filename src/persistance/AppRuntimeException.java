package persistance;

public class AppRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String altMessage;

	public AppRuntimeException(Throwable t) {
		super(t);
	}

	public AppRuntimeException(String message, Throwable t) {
		super(t);
		this.altMessage = message;
	}

	public String getMessage() {
		return altMessage;
	}
}
