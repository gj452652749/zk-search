package com.zkyunso.microservice.search.service;

import org.gj.microservice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkyunso.microservice.search.dao.QueryDao;
import com.zkyunso.searchengine.stmt.QueryStmt;
@Service
public class QueryService extends BasicService{
	@Autowired
	QueryDao dao;
	public String select(String collName,QueryStmt stmt) {
		ResponseResult response=new ResponseResult();
		String json=dao.getResult(collName,stmt);
		return formatResultWithRows(json);
	}
	public String query(String collName,QueryStmt stmt) {
		String json=dao.getResult(collName,stmt);
		return json;
	}

}
