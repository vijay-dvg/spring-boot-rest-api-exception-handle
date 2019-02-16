package com.vijay.springboot.application.exception;

import java.util.Date;

import lombok.Data;

/**
 * ErrorDetails
 * @author Vijay.Kumar
 * @since 15/02/2019
 *
 */
@Data
public class ErrorDetails {

	private Date timestamp;
	private String mesage;
	private String details;
	/**
	 * @param timestamp
	 * @param mesage
	 * @param details
	 */
	public ErrorDetails(Date timestamp, String mesage, String details) {
		super();
		this.timestamp = timestamp;
		this.mesage = mesage;
		this.details = details;
	}
	
	
}
