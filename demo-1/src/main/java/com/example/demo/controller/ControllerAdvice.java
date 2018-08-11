package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.config.model.ErrorEntity;
import com.example.demo.exception.CustomException;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleException(CustomException customException, WebRequest request) {
		{

			ErrorEntity entity = new ErrorEntity();
			entity.setDevErrorMessage(customException.getDevErrorMessage());
			entity.setErrorCode(customException.getErrorCode());
			entity.setMessage(customException.getMessage());
			return new ResponseEntity<ErrorEntity>(entity, customException.getHttpStatus());
		}
	}
}
