package com.example.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.CenterNotFoundException;
import com.example.exception.ErrorMessage;
//import com.example.exception.ErrorMessage;
import com.example.exception.ListEmptyException;
import com.example.exception.ResourceNotFoundException;
@ControllerAdvice
public class HcsControllerAdvice extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ListEmptyException.class)
	public ResponseEntity<String> handleListEmptyException(ListEmptyException listEmptyException) {

		return new ResponseEntity<String>(listEmptyException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(CenterNotFoundException.class)
	public ResponseEntity<String> handleCenterNotFoundException(CenterNotFoundException listEmptyException) {

		return new ResponseEntity<String>(listEmptyException.getMessage(), HttpStatus.NOT_FOUND);
	}

	 @ExceptionHandler(ResourceNotFoundException.class)
	  @ResponseStatus(value = HttpStatus.NOT_FOUND)
	  public ErrorMessage resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.NOT_FOUND.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
	 
	  @ExceptionHandler(Exception.class)
	  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	  public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
	    ErrorMessage message = new ErrorMessage(
	        HttpStatus.INTERNAL_SERVER_ERROR.value(),
	        new Date(),
	        ex.getMessage(),
	        request.getDescription(false));
	    
	    return message;
	  }
}
