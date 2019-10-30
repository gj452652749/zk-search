package com.zkyunso.microservice.http.util;

import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class SpringRestImp implements HttpRest {

	private RestTemplate restTemplate = null;

	public SpringRestImp() {
		super();
		restTemplate = new RestTemplate();
		CloseableHttpClient httpclient = HttpClientBuilder.create().setMaxConnTotal(800).setMaxConnPerRoute(800)
				.build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpclient));
		// TODO Auto-generated constructor stub
	}

	@Override
	public String get(String url) {
		// TODO Auto-generated method stub
		String json = restTemplate.getForObject(url, String.class);
		return json;
	}

	@Override
	public String get(String url, Map<String, String> paraMap) {
		// TODO Auto-generated method stub
		String json = restTemplate.getForObject(url, String.class, paraMap);
		return json;
	}

	public String post(String url, String dataJson) {
		// TODO Auto-generated method stub
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return post(url, dataJson, headers);
	}

	public String post(String url, String dataJson, HttpHeaders headers) {
		HttpEntity<String> entity = new HttpEntity<String>(dataJson, headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		return response.getBody();
	}

}
