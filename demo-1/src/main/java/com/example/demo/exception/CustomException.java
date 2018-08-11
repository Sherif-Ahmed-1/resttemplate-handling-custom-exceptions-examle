package com.example.demo.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
	private String message;
	private String errorCode;
	private String devErrorMessage;
	private HttpStatus httpStatus;
}
