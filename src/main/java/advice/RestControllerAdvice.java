package advice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import error.ErrorResponse;
import exceptions.NotAllowedException;
import exceptions.NotFoundException;
import exceptions.UnauthorisedException;

@ControllerAdvice
public class RestControllerAdvice {
   
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex){
		 
		 ErrorResponse errorResponse = new ErrorResponse();
		 errorResponse.setMessage(ex.getLocalizedMessage());
		 LocalDateTime nowDateTime = LocalDateTime.now();
		 errorResponse.setTimeStampString(nowDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
		 
		 if(ex.getClass().equals(NotFoundException.class))
			  errorResponse.setStatus(HttpStatus.NOT_FOUND);
         
         if(ex.getClass().equals(UnauthorisedException.class))
 			  errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
         
         if(ex.getClass().equals(NotAllowedException.class))
			  errorResponse.setStatus(HttpStatus.NOT_ACCEPTABLE);
         
         if(errorResponse.getStatus()==null)
       	  errorResponse.setStatus(HttpStatus.BAD_REQUEST);
         
		 return new ResponseEntity<ErrorResponse>(errorResponse,errorResponse.getStatus());
	}
	
}
