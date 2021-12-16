package com.edumans.dto.error;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class work as Data Transfer object for API Errors/ Exceptions
 * 
 * @author mohamed.elmasry
 *
 */
public class ErrorDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private int statusCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;

	private ErrorDto() {
		timestamp = LocalDateTime.now();
	}

	public ErrorDto(HttpStatus status) {
		this();
		this.status = status;
	}

	public ErrorDto(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.toString();
	}

	public ErrorDto(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.toString();
		this.setStatusCode(status.value());

	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "{\n" + "status=" + status + ",\nstatuscode='" + statusCode + '\'' + ",\n timestamp='" + timestamp + '\''
				+ ",\n message='" + message + '\'' + ",\n debugessage='" + debugMessage + '\'' + '}';
	}

}
