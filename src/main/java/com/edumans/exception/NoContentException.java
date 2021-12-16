package com.edumans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Custom exception To handle empty entity lists
 * 
 * and it is thrown in case there are no students or courses 
 * 
 * @author mohamed.elmasry
 *
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoContentException(String resourceName) {
		super(String.format("No data found : '%s'", resourceName));
	}
}
