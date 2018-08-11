package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.example.demo.config.model.ErrorEntity;
import com.example.demo.exception.RestResponseErrorHandler;

@Configuration
public class Config {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
		restTemplate.setErrorHandler(new RestResponseErrorHandler<ErrorEntity>(ErrorEntity.class));
		return restTemplate;
	}
}
