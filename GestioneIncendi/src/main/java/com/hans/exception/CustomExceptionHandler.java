package com.hans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntityExistsException.class)
	public ResponseEntity<String> manageEntityExistsException(EntityExistsException e) {
		return new ResponseEntity<String>(e.getMessage()+" Exception Handler",HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> EntityNotFoundException(EntityNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage()+" Exception Handler",HttpStatus.BAD_REQUEST);	
	}
	
}
