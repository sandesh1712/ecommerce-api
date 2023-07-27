package exceptions;

public class UnauthorizedException extends RuntimeException{
	
	private static final long serialVersionUID = -6687929912132987819L;

	public UnauthorizedException(String message) {
		super(message);
	}
   
}