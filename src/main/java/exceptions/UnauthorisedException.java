package exceptions;

public class UnauthorisedException extends RuntimeException{
	
	private static final long serialVersionUID = -6687929912132987819L;

	public UnauthorisedException(String message) {
		super(message);
	}
   
}