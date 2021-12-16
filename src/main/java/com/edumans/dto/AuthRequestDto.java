package com.edumans.dto;

import java.io.Serializable;

/**
 * This class work as Data Transfer object for API Authentication Requests
 * 
 * @author mohamed.elmasry
 *
 */
public class AuthRequestDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// need default constructor for JSON Parsing
	public AuthRequestDto() {

	}

	public AuthRequestDto(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
}
