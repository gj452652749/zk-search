package com.zkyunso.microservice.search.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zkyunso.microservice.search.context.AppContext;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.microservice.search.stmt.StmtParser;

@Repository
public class SchemaDao {
	private static final Logger logger=LoggerFactory.getLogger(SchemaDao.class);
	@Autowired
	AppContext context;
	@Autowired
	StmtParser parser;
	@Autowired
	SearchManager searchManager;
	/**
	 * http://localhost:8983/solr/address/schema/fields?wt=json
	 * @param stmt
	 * @return
	 */
	public String getALl(String core) {
		String url=context.getEngineUrl()+"/"+core+"/schema/fields?wt=json";
		String resultJson=searchManager.getHttpRest().get(url);
		logger.info(resultJson);
		return resultJson;
	}
	/**
	 * {
		  "add-field":{
		     "name":"sell-by",
		     "type":"tdate",
		     "stored":true }
		}
	 * @param fieldJsonStr
	 */
	public void save(String core,String jsonStr) {
		String cmdJsonStr="{\"add-field\":"+jsonStr+"}";
		String resultJson=doCmd(core, cmdJsonStr);
		logger.info(resultJson);
	}
	/**
	 * {
		  "delete-field" : { "name":"sell-by" }
		}
	 * @param idJsonArrayStr
	 */
	public void remove(String core,String jsonStr) {
		String cmdJsonStr="{\"delete-field\":"+jsonStr+"}";
		String resultJson=doCmd(core, cmdJsonStr);
		logger.info(resultJson);
	}
	/**
	 * {
		  "replace-field":{
		     "name":"sell-by",
		     "type":"date",
		     "stored":false }
		}
	 * @param docJsonStr
	 */
	public void update(String core,String jsonStr) {
		String cmdJsonStr="{\"replace-field\":"+jsonStr+"}";
		String resultJson=doCmd(core, cmdJsonStr);
		logger.info(resultJson);
	}
	public String doCmd(String core,String cmdJsonStr) {
		String url=context.getEngineUrl()+"/"+core+"/schema";
		String resultJson=searchManager.getHttpRest().post(url, cmdJsonStr);
		return resultJson;
	}
}
