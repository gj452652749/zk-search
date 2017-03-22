package com.zkyunso.microservice.search.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zkyunso.microservice.http.util.HttpRest;
@Component
public class SearchManager {
	@Autowired
	HttpRest httpRest;
	public HttpRest getHttpRest() {
		return httpRest;
	}

}
