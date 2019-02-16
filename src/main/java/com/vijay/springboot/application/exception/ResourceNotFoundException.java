package com.vijay.springboot.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * EmployeeNotFoundException used to define the Resource Not Found Exception
 * 
 * @author Vijay.Kumar
 * @since 15/02/2019
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	/**
	 * serialVersionUID
	 */
	protected static final long serialVersionUID = 7893973444021033078L;

	/**
	 * @param message
	 */
	public ResourceNotFoundException(String message) {
		super();
	}

}
