package com.jeffersonssousa.investHub.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;
import com.jeffersonssousa.investHub.services.exceptions.DataBaseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ControllerNotFoundException.class)
	public ResponseEntity<StandardError> controllerNotFound(ControllerNotFoundException e, HttpServletRequest request) {
		String error = "Controller not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now()
				                              , status.value()
				                              , error
				                              , e.getMessage()
				                              ,request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
	
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
		String error = "Controller error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now()
				                              , status.value()
				                              , error
				                              , e.getMessage()
				                              ,request.getRequestURI());
		return ResponseEntity.status(status).body(err);
		
	}
}
