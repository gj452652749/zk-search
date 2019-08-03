package com.zkyunso.microservice.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkyunso.microservice.search.dao.AnalysisDao;
import com.zkyunso.microservice.search.dao.DocDao;
import com.zkyunso.microservice.search.stmt.AnalysisStmt;
import com.zkyunso.microservice.search.stmt.SearchStmt;
@Service
public class AnalysisService {
	@Autowired
	AnalysisDao dao;
	public String get(String collName,AnalysisStmt stmt) {
		String json=dao.get(collName,stmt);
		return json;
	}

}
