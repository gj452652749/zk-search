package com.zkyunso.microservice.search.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.microservice.search.stmt.AnalysisStmt;
import com.zkyunso.searchengine.cloud.CloudEngine;

@Repository
public class AnalysisDao {
	private static final Logger logger=LoggerFactory.getLogger(AnalysisDao.class);
	@Autowired
	CloudEngine cloudEngine;
	@Autowired
	SearchManager searchManager;
	public String get(AnalysisStmt stmt,String collName,String mode) {
		StringBuilder tokens=new StringBuilder();
		String url=cloudEngine.getHost()+"/"+collName+"/analysis/field?wt=json"
				+ "&analysis.showmatch=true&analysis.fieldvalue="+stmt.getQuery()
				+ "&analysis.query="+stmt.getQuery()+"&analysis.fieldtype="+stmt.getType()+"&omitHeader=true";
		String json=searchManager.getHttpRest().get(url);
		try {
			JSONObject jsonObj=JSONObject.parseObject(json);
			JSONArray indexJsonArray= jsonObj.getJSONObject("analysis")
									.getJSONObject("field_types")
									.getJSONObject("text_ik_mutable")
									.getJSONArray(mode);
			
			JSONArray lastArray=indexJsonArray.getJSONArray(indexJsonArray.size()-1);
			
			for(int i=0;i<=lastArray.size()-1;i++) {
				JSONObject ele=lastArray.getJSONObject(i);
				tokens.append(ele.getString("text")).append(" ");
			}
			System.out.println("getText:"+tokens);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tokens.toString();
	}
	public String get(String collName,AnalysisStmt stmt) {
		String indexTokens=get(stmt,collName,"index");
		String queryTokens=get(stmt,collName,"query");
		String returnJson="{\"index\":\""+indexTokens+"\",\"query\":\""+queryTokens+"\"}";		
		logger.info(returnJson);
		return returnJson;
	}
}
