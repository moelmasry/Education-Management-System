package com.edumans.utils;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.edumans.dto.error.ErrorDto;
import com.edumans.exception.ResourceNotFoundException;

import io.jsonwebtoken.JwtException;
/**
 * Controller Advice for exception handler to handle API Exceptions
 * @author mohamed.elmasry
 *
 */
@ControllerAdvice
public class RestExceptionHandler {

	
	// JwtException
	@ExceptionHandler({ JwtException.class })
	public ResponseEntity<Object> handleJwtException(Exception ex, WebRequest request) {
		return buildResponseEntity(new ErrorDto(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex));

	}
	//  not Found Exception handler (entity/endpoint) 
	@ExceptionHandler({ EntityNotFoundException.class, ResourceNotFoundException.class, NoHandlerFoundException.class })
	protected ResponseEntity<Object> handleEntityNotFound(Exception ex) {
		return buildResponseEntity(new ErrorDto(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
	}

	// Exception
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		return buildResponseEntity(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), ex));

	}

	private ResponseEntity<Object> buildResponseEntity(ErrorDto apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
