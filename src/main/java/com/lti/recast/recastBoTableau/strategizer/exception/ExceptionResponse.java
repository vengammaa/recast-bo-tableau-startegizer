package com.lti.recast.recastBoTableau.strategizer.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
	
	private String message;
	private LocalDateTime dateTime;
	private int code;
	
	
	public ExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ExceptionResponse(String message, LocalDateTime dateTime, int code) {
		super();
		this.message = message;
		this.dateTime = dateTime;
		this.code = code;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}
	
	

}
