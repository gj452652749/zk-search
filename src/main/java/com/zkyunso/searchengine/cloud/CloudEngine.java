package com.zkyunso.searchengine.cloud;

import org.gj.microservice.http.util.HttpRest;
import org.gj.microservice.http.util.SpringRestImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class CloudEngine {
	@Autowired
	EngineStatus status;
	HttpRest httpRest=new SpringRestImp();
		// TODO Auto-generated constructor stub
	public void init() {
		
	}
	public String getHost() {
		return status.getSearchBaseUrl();
	}
	public String generateSaveCmdJson(String json) {
		return null;
	}
	public String generateDeleteCmdJson(String json) {
		return null;
	}
	public String get() {
		httpRest.get("");
		return null;
	}
	public String post() {
		httpRest.post("",null);
		return null;
	}
}
