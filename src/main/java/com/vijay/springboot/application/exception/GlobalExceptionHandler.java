package com.vijay.springboot.application.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * GlobalExceptionHandler is used to handle the global exceptions
 * 
 * @author Vijay.Kumar
 * @Since 15/02/2019
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException exp, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception exp, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exp.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
