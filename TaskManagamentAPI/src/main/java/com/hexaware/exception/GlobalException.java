package com.hexaware.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorDetails> taskNotFoundException(TaskNotFoundException ex) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> userNotFoundException(UserNotFoundException ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> globalException(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
