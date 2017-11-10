package com.zkyunso.microservice.search.stmt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zkyunso.microservice.search.constant.ConfigConsts;
import com.zkyunso.microservice.search.context.AppContext;
import com.zkyunso.searchengine.cloud.CloudEngine;

/**
 * 将stmt解析成 engine rest url
 * @author gaoj
 *
 */
@Component
public class StmtParser {
	@Autowired
	CloudEngine cloudEngine;
	public String combineStmtParas(SearchStmt stmt) {
		StringBuilder sb=new StringBuilder();
		//拼q参数
		if((stmt.getMode()).equals(ConfigConsts.INORDER_MODE)) {
			sb.append("q=\"").append(stmt.getqPara()).append("\"~12");
			sb.append("&defType=complexPhrase");
		}else if((stmt.getMode()).equals(ConfigConsts.OFFSET_MODE)) {
			sb.append("q=\"").append(stmt.getqPara()).append("\"~12");
			sb.append("&defType=offsetphrase");
		}else {
			sb.append("q=").append(stmt.getqPara());
		}
		if(!StringUtils.isEmpty(stmt.getFqPara()) )
				sb.append("&fq=").append(stmt.getFqPara());
		sb.append("&start=").append(stmt.getStart());
		sb.append("&rows=").append(stmt.getRows());		
		sb.append("&wt=json&omitHeader=true");
		return sb.toString();
	}
	public String parseStmt(SearchStmt stmt) {
		StringBuilder sb=new StringBuilder();
		sb.append(cloudEngine.getHost());
		sb.append("/").append(stmt.getCore()).append("/select?");
		return null;
	}

}
