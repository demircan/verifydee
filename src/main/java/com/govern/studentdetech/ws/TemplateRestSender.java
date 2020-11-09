package com.govern.studentdetech.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class TemplateRestSender<R, T> {

	protected ObjectMapper objectMapper;
	protected RestTemplate restTemplate;

	protected Logger logger	= LoggerFactory.getLogger(getClass());

	protected void init() {
		
		objectMapper = new ObjectMapper();
		restTemplate = new RestTemplate();
		
	}

	public ResponseBeanWrapped<T> sendRest(R request, String serviceAddress, HttpHeaders  headers) throws Exception {

		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<R> entity = new HttpEntity<R>(request, headers);
		ResponseEntity<String> response;
		ResponseBeanWrapped<T> responseBeanWrapped = new ResponseBeanWrapped<T>();
		T t = null;

		try {
			response = restTemplate.exchange(serviceAddress, getHttpMethod(), entity, String.class);
			logger.info("Http response for request: {} responseCode: {} responseBody: {}", request, response.getStatusCode(), response.getBody());
			 t = objectMapper.readValue(response.getBody(), getResponseClass());
		} catch (Exception e) {
			logger.error("Error for request: {} ", request, e);
			throw e;
		}

		responseBeanWrapped.setResponse(t);
		responseBeanWrapped.setStatus(response.getStatusCode());

		return responseBeanWrapped;
	}

	public abstract HttpMethod getHttpMethod();

	public abstract Class<T> getResponseClass();

}
