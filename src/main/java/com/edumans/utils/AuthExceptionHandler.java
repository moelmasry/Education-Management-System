package com.edumans.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.edumans.dto.error.ErrorDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
/**
 * AuthExceptionHandler is custom handler to handle the JWT validation exception
 * 
 * @author mohamed.elmasry
 *
 */
@Component
public class AuthExceptionHandler {

	/**
	 * handle the validation exceptions by building an Error DTO with the Exception Info.
	 * @param request
	 * @param response
	 * @param exception
	 * @throws IOException
	 * @throws ServletException
	 */
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, Exception exception)
			throws IOException, ServletException {
		// build response 
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		ErrorDto authenticationError = new ErrorDto(HttpStatus.UNAUTHORIZED, "JWT signature problem", exception);
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		response.getWriter().write(objectMapper.writeValueAsString(authenticationError));
	};
}

