/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.exception;

/**
 * @author User
 *
 */
public class ResourceNotFoundException extends RuntimeException{
	
	
	public ResourceNotFoundException(String msg){
		super(msg);
	}

}
