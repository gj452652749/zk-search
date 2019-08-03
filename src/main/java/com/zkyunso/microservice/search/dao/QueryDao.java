package com.zkyunso.microservice.search.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zkyunso.microservice.search.manager.SearchManager;
import com.zkyunso.searchengine.cloud.CloudEngine;
import com.zkyunso.searchengine.stmt.QueryStmt;
import com.zkyunso.searchengine.stmt.QueryStmtParser;


@Repository
public class QueryDao {
	protected final static Logger logger = LoggerFactory.getLogger(QueryDao.class);
	@Autowired
	QueryStmtParser queryStmtParser;
	@Autowired
	CloudEngine cloudEngine;
	@Autowired
	SearchManager searchManager;
	/**
	 * 规范化stmt,特殊字符的处理
	 * @param stmt
	 * @return
	 */
	public void formatStmt(QueryStmt stmt) {
		stmt.setqPara(stmt.getqPara().trim());
		//如果中间包含空格，则变成phrase查询
		String[] terms=stmt.getqPara().split(" ");
		if(terms.length>1) {
			StringBuilder sb=new StringBuilder();
			for(int i=0;i<=terms.length-1;i++) {
				String term=terms[i];
				sb.append("\"").append(term).append("\"");
				if(i!=(terms.length-1))
					sb.append(" ");
			}
			stmt.setqPara(sb.toString());
		}
		
	}
	public String getResult(String collName,QueryStmt stmt) {
		// TODO Auto-generated method stub
		//formatStmt(stmt);
		String url=queryStmtParser.generateUrl(cloudEngine.getHost()
				,collName,stmt);
		logger.info(url);
		String result=searchManager.getHttpRest().get(url);
		logger.info(result);
		return result;
	}

	public Object getValueByField(String collName,String id,String fieldName) {
		QueryStmt stmt =new QueryStmt();
		stmt.setqPara("id:"+id);
		stmt.setExtraPara("fl="+fieldName);
		stmt.setRows(1);
		String url=queryStmtParser.generateUrl(cloudEngine.getHost()
				,collName,stmt);
		String result=searchManager.getHttpRest().get(url);
		JSONObject jsonObj=JSON.parseObject(result);
		JSONArray jsonArray=jsonObj.getJSONObject("response")
				.getJSONArray("docs");
		if(jsonArray.size()!=0) {
			Object value=jsonArray.getJSONObject(0).get(fieldName);
			return value;
		}
		return null;
	}
}
