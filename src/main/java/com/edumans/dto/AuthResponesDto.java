package com.edumans.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

/**
 * This class work as Data Transfer object for API Authentication Responses
 * 
 * @author mohamed.elmasry
 *
 */
public class AuthResponesDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;
	private String message;
	private String jwtToken;

	public AuthResponesDto() {
	}

	public AuthResponesDto(HttpStatus httpStatus, String message,String jwtToken) {
		this.httpStatus = httpStatus;
		this.message = message;
		this.jwtToken = jwtToken;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public String getJwtToken() {
		return jwtToken;
	}
}
