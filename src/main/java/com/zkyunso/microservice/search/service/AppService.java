package com.zkyunso.microservice.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkyunso.microservice.http.util.HttpRest;
import com.zkyunso.microservice.search.stmt.SearchStmt;
import com.zkyunso.microservice.search.stmt.StmtParser;
@Service
public class AppService {
	@Autowired
	StmtParser parser;
	@Autowired
	HttpRest httpRest;
	public String doSearch(SearchStmt stmt) {
		String url=parser.parseStmt(stmt);
		String json=httpRest.get(url);
		return json;
	}

}
