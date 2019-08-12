package com.zkyunso.microservice.search.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zkyunso.microservice.search.context.AppContext;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.microservice.search.stmt.SearchStmt;
import com.zkyunso.microservice.search.stmt.StmtParser;
import com.zkyunso.searchengine.cloud.CloudEngine;

@Repository
public class DocDao {
	private static final Logger logger = LoggerFactory.getLogger(SchemaDao.class);
	@Autowired
	AppContext context;
	@Autowired
	StmtParser parser;
	@Autowired
	SearchManager searchManager;
	@Autowired
	CloudEngine cloudEngine;

	public String get(SearchStmt stmt) {
		String url = parser.parseStmt(stmt);
		String json = searchManager.getHttpRest().get(url);
		logger.info(url);
		return json;
	}

	public void save(String collName, String docJsonStr) {
		String cmdJsonStr = "{add :{doc:" + docJsonStr + ",boost:1," + "overwrite:true,commitWithin:1000}}";
		String url = cloudEngine.getHost() + "/" + collName + "/update?commit=true";
		String result = searchManager.getHttpRest().post(url, cmdJsonStr);
		logger.info(result);
	}

	/**
	 * 用户查出结果，选择id集合，然后删除(只能通过界面删，不能rest调用)
	 * 
	 * @param idJsonArrayStr
	 */
	public void remove(String idJsonArrayStr) {

	}

	public void execUpdateCmd(String collName, String json) {
		// String
		// url="http://localhost:8080/solr/"+config.getColl()+"/update?boost=1.0&overwrite=true&wt=json";
		String url = cloudEngine.getHost() + "/" + collName
				+ "/update?boost=1.0&commitWithin=1000&overwrite=true&wt=json";
		logger.info(url);
		logger.info(json);
		String result = searchManager.getHttpRest().post(url, json);
		logger.info(result);
	}

	/**
	 * 支持批量提交，doc1,doc2,..docn
	 * 
	 * @param collName
	 * @param docJsonStr
	 */
	public void add(String collName, String docJsonStr) {
		execUpdateCmd(collName, docJsonStr);
	}

	public void set(String collName, String id, String field, String value) {
		String json = "[{\"id\":\"" + id + "\"," + field + ":{\"set\":\"" + value + "\"}}]";
		execUpdateCmd(collName, json);
	}

	public void incById(String collName, String id, String fieldName, int offset) {
		String json = "[{\"id\":" + id + ",\"" + fieldName + "\":{\"inc\":" + offset + "}}]";
		execUpdateCmd(collName, json);
	}

	public void delete(String collName, String idJsonArray) {
		String json = "{ \"delete\":" + idJsonArray + " }";
		execUpdateCmd(collName, json);
	}
}
