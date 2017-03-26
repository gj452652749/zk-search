package com.zkyunso.microservice.search.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zkyunso.microservice.search.context.AppContext;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.microservice.search.stmt.SearchStmt;
import com.zkyunso.microservice.search.stmt.StmtParser;
@Repository
public class DocDao {
	private static final Logger logger=LoggerFactory.getLogger(SchemaDao.class);
	@Autowired
	AppContext context;
	@Autowired
	StmtParser parser;
	@Autowired
	SearchManager searchManager;
	public String get(SearchStmt stmt) {
		String url=parser.parseStmt(stmt);
		String json=searchManager.getHttpRest().get(url);
		logger.info(url);
		return json;
	}
	public void save(String core,String docJsonStr) {
		String cmdJsonStr="{add :{doc:"+docJsonStr+",boost:1,"
				+ "overwrite:true,commitWithin:1000}}";
		String url=context.getEngineUrl()+"/"+core+"/update?commit=true";
		String result=searchManager.getHttpRest().post(url, cmdJsonStr);
		logger.info(result);
	}
	/**
	 * 用户查出结果，选择id集合，然后删除(只能通过界面删，不能rest调用)
	 * @param idJsonArrayStr
	 */
	public void remove(String idJsonArrayStr) {
			
	}
	public void update(String docJsonStr) {
		
	}

}
