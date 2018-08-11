package com.example.demo.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.CustomException;

@RestController
@RequestMapping("dumy")
public class DumyController {

	@GetMapping
	public ResponseEntity<?> throwError() throws CustomException {

		throw new CustomException("message", "errorCode", "devMessage");
	}
}
