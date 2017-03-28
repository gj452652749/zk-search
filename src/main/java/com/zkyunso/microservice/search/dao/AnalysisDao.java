package com.zkyunso.microservice.search.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zkyunso.microservice.search.context.AppContext;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.microservice.search.stmt.AnalysisStmt;

@Repository
public class AnalysisDao {
	private static final Logger logger=LoggerFactory.getLogger(AnalysisDao.class);
	@Autowired
	AppContext context;
	@Autowired
	SearchManager searchManager;
	public String get(AnalysisStmt stmt,String mode) {
		StringBuilder tokens=new StringBuilder();
		String url=context.getEngineUrl()+"/"+stmt.getCore()+"/analysis/field?wt=json"
				+ "&analysis.showmatch=true&analysis.fieldvalue="+stmt.getQuery()
				+ "&analysis.query="+stmt.getQuery()+"&analysis.fieldtype="+stmt.getType()+"&omitHeader=true";
		String json=searchManager.getHttpRest().get(url);
		try {
			JSONObject jsonObj=new JSONObject(json);
			JSONArray indexJsonArray= jsonObj.getJSONObject("analysis")
									.getJSONObject("field_types")
									.getJSONObject("text_ik_mutable")
									.getJSONArray(mode);
			
			JSONArray lastArray=indexJsonArray.getJSONArray(indexJsonArray.length()-1);
			
			for(int i=0;i<=lastArray.length()-1;i++) {
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
	public String get(AnalysisStmt stmt) {
		String indexTokens=get(stmt,"index");
		String queryTokens=get(stmt,"query");
		String returnJson="{\"index\":\""+indexTokens+"\",\"query\":\""+queryTokens+"\"}";		
		logger.info(returnJson);
		return returnJson;
	}
}
