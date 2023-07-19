package exceptions;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = -1997069109076644065L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public CustomException(String message) {
		super(message);
	}
}
