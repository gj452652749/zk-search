package com.zkyunso.microservice.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkyunso.microservice.search.dao.DocDao;
import com.zkyunso.microservice.search.stmt.SearchStmt;
@Service
public class AppService {
	@Autowired
	DocDao docDao;
	public String doSearch(SearchStmt stmt) {
		String json=docDao.get(stmt);
		return json;
	}

}
