package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("dumy")
public class DumyController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping()
	public ResponseEntity<?> callThrowException() {
		return restTemplate.getForEntity("http://localhost:8080/dumy", String.class);
	}
}
