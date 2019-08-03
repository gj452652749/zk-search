package com.zkyunso.microservice.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkyunso.microservice.search.service.AnalysisService;
import com.zkyunso.microservice.search.stmt.AnalysisStmt;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {
	@Autowired
	AnalysisService service;
	public String get(String collName,AnalysisStmt stmt) {
		return service.get(collName,stmt);
	}

}
