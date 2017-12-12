/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.exception;

/**
 * @author User
 *
 */
public class JwtTokenMissingException extends RuntimeException {


   private String errorMessage;
	public JwtTokenMissingException(String msg) {
		super(msg);

		this.errorMessage = msg;

	}
	
	

	public String getErrorMessage() {
		return errorMessage;
	}
	
	





	
}
