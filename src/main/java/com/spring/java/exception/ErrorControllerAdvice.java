/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author User
 *
 */
@ControllerAdvice
@EnableWebMvc
  
public class ErrorControllerAdvice  {
	 
	 
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Not Found");
	        response.setErrorMessage(ex.getMessage());
	 
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	    }
	 @ExceptionHandler(JwtTokenMissingException.class)
	 public ResponseEntity<ExceptionResponse> jwtTokenMissing(JwtTokenMissingException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("jwt token missing");
	        response.setErrorMessage(ex.getMessage());
	 
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	 }
}
