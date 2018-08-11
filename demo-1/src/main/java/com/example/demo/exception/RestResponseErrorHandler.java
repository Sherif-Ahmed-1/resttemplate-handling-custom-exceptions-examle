package com.example.demo.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseErrorHandler;

import com.example.demo.config.model.ErrorEntity;

public class RestResponseErrorHandler<T> implements ResponseErrorHandler {

	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

	private final HttpMessageConverterExtractor<T> delegate;

	public RestResponseErrorHandler(Class<T> responseType) {
		// Set up the message Converters
		this.messageConverters.add(new MappingJackson2HttpMessageConverter());

		this.delegate = new HttpMessageConverterExtractor<T>(responseType, this.messageConverters);

	}

	// If a 400 or 500 series error is returned then we want to handle the error,
	// otherwise not
	public boolean hasError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = response.getStatusCode();
		if (statusCode.series() == Series.CLIENT_ERROR || statusCode.series() == Series.SERVER_ERROR)
			return true;
		return false;
	}

	public void handleError(ClientHttpResponse response) throws IOException {
		ResponseEntity<ErrorEntity> responseEntity = new ResponseEntity<ErrorEntity>(
				(ErrorEntity) this.delegate.extractData(response), response.getHeaders(), response.getStatusCode());
		CustomException customException = new CustomException();
		customException.setDevErrorMessage(responseEntity.getBody().getDevErrorMessage());
		customException.setErrorCode(responseEntity.getBody().getErrorCode());
		customException.setHttpStatus(responseEntity.getBody().getHttpStatus());
		customException.setMessage(responseEntity.getBody().getMessage());
		throw customException;
	}

}