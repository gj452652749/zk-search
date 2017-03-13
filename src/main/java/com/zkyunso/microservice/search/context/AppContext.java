package com.zkyunso.microservice.search.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class AppContext {

	@Value("${engine.url}")
	private String engineUrl;

	public String getEngineUrl() {
		return engineUrl;
	}
	
}
