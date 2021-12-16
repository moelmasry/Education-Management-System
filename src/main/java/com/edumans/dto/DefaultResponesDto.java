package com.edumans.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * This class work as Default Data Transfer object for API successful response
 * 
 * @author mohamed.elmasry
 *
 */
public class DefaultResponesDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	private String message;

	public DefaultResponesDto() {
	}

	public DefaultResponesDto(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
