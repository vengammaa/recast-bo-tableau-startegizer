package com.lti.recast.recastBoTableau.strategizer.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleExceptions(ResourceNotFoundException resourceNotFoundException, WebRequest webRequest){
		
		ExceptionResponse exceptionResponse = new ExceptionResponse(resourceNotFoundException.getMessage(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(RuntimeException.class) public ResponseEntity<Object>
	 * handleExceptions(RuntimeException exception, WebRequest webRequest){
	 * 
	 * ExceptionResponse exceptionResponse = new
	 * ExceptionResponse(exception.getMessage(),LocalDateTime.now(),HttpStatus.
	 * INTERNAL_SERVER_ERROR.value());
	 * 
	 * return new
	 * ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
}
