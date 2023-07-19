package advice;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import error.ErrorResponse;
import exceptions.NotFoundException;

@ControllerAdvice
public class RestControllerAdvice {
   
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){
		 
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setMessage(ex.getMessage());
		 errorResponse.setTimeStampString(System.currentTimeMillis()+"");
		 
		 if(ex.getClass().equals(NotFoundException.class))
			errorResponse.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<ErrorResponse>(errorResponse,errorResponse.getStatus());
	}
	
}
