package com.example.demo.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.exception.CustomException;
import com.example.demo.model.ErrorEntity;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException customException, WebRequest request) {
		ErrorEntity entity = new ErrorEntity();

		entity.setHttpStatus(HttpStatus.BAD_REQUEST);
		entity.setMessage("erorrMessage");
		return new ResponseEntity<ErrorEntity>(entity, HttpStatus.BAD_REQUEST);
	}
}
