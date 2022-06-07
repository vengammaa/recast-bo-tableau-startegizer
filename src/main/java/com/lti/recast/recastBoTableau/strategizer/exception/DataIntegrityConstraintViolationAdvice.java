package com.lti.recast.recastBoTableau.strategizer.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DataIntegrityConstraintViolationAdvice {
	@ResponseBody
	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	String DataInegrityConstraintViolationHandler(DataIntegrityViolationException e) {
		return "Please delete dependent resources before deleting the parent";
	}
	
}
