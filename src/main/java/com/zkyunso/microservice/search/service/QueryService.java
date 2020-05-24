package com.zkyunso.microservice.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.zkyunso.microservice.search.dao.QueryDao;
import com.zkyunso.searchengine.stmt.QueryStmt;
@Service
public class QueryService extends BasicService{
	@Autowired
	QueryDao dao;
	public String select(String collName,QueryStmt stmt) {
		String json=dao.getResult(collName,stmt);
		if(!StringUtils.isEmpty(stmt.getFacetField())) {
			if(stmt.getFacetField().contains(","))
				return formatMultiFacetResultWithRows(json);
			return formatFacetResultWithRows(json);
		}
		return formatResultWithRows(json);
	}
	public String query(String collName,QueryStmt stmt) {
		String json=dao.getResult(collName,stmt);
		return json;
	}

}
