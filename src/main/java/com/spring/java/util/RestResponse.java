package com.spring.java.util;

import org.springframework.http.HttpStatus;

import com.spring.java.model.Program;

/**
 * @author By Dinesh Bade 10:45:08 AM
 * @param <T>
 *
 */
public class RestResponse<T> {
	
	private Integer status;
	private T data;
	private String message;
	public Integer getStatus() {
		return status;
	}
	
	
	
	/**
	 * @param status
	 * @param data
	 * @param message
	 */
	public RestResponse(Integer status, T data, String message) {

		this.status = status;
		this.data = data;
		this.message = message;
	}



	


	public void setStatus(Integer status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	


}
