package com.zkyunso.searchengine.stmt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


/**
 * 将stmt解析成 engine rest url
 * @author gaoj
 *
 */
@Component
public class QueryStmtParser {
	public String parseStmt(QueryStmt stmt) {
		StringBuilder sb=new StringBuilder();
		if(!StringUtils.isEmpty(stmt.getqPara())) {
		sb.append("q=").append(stmt.getqPara());
		}
		if(!StringUtils.isEmpty(stmt.getFqPara())) {
			sb.append("&fq=").append(stmt.getFqPara());
		}
		sb.append("&q.op=AND");
		//防止start为负数
		if(stmt.getStart()<0)
			stmt.setStart(0);
		sb.append("&start=").append(stmt.getStart())
		.append("&rows=").append(stmt.getRows());
		//加入扩展参数，如fl、sort等
		if(!StringUtils.isEmpty(stmt.getExtraPara()))
			sb.append("&").append(stmt.getExtraPara());
		return sb.toString();
	}
	public String generateUrl(String engineHost,String coll,QueryStmt stmt) {
		StringBuilder sb=new StringBuilder();
		sb.append(engineHost).append("/").append(coll).append("/select?");	
		sb.append(parseStmt(stmt));
		sb.append("&wt=json&indent=true&omitHeader=true");
		return sb.toString();
	}
	public String generateUpdateUrl(String engineHost,String collName,QueryStmt stmt) {
		StringBuilder sb=new StringBuilder();
		sb.append(engineHost).append(collName).append("/update?commit=true");	
		return sb.toString();
	}
	public String generateSaveUrl(String engineHost,String collName,QueryStmt stmt) {	
		return generateUpdateUrl(engineHost,collName,stmt);
	}
	public String generateDeleteUrl(String engineHost,String collName,QueryStmt stmt) {		
		return generateUpdateUrl(engineHost,collName,stmt);
	}

}
