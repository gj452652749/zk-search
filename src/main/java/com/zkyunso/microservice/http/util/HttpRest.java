package com.zkyunso.microservice.http.util;

import java.util.Map;

public interface HttpRest {

	public String get(String url);

	public String post(String url, String dataJson);

	String get(String url, Map<String, String> paraMap);
}
