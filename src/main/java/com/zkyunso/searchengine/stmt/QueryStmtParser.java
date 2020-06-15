package com.zkyunso.searchengine.stmt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.zkyunso.microservice.search.constant.SearchModeEnums;

/**
 * 将stmt解析成 engine rest url
 * 
 * @author gaoj
 *
 */
@Component
public class QueryStmtParser {
	
	//传入的是'type,city'，输出标准solr格式'facet.field=yupe&facet.field=city'
	public String generateFacetFieldPara(String facetField) {
		if(!facetField.contains(","))
			return "&facet.field="+facetField;
		String[] fields=facetField.split(",");
		String result="";
		for(String ele : fields) {
			result=result+"&facet.field="+ele;
		}
		return result;
	}
	
	//输入"labels:爬山 city:杭州"
	//输出"(labels:爬山 AND city:杭州)
	public String formatFq(String fqPara) {
		if(StringUtils.isEmpty(fqPara))
			return "";
		String fq="("+fqPara.trim().replaceAll(" +", " AND ")+")";
//		String fq="(";
//		String[] fqTerms=fqPara.trim().split(" ");
//		for(int i=0;i<=fqTerms.length-1;i++) {
//			fq=fq+fqTerms[i];
//			if(i!=fqTerms.length-1) fq=fq+" AND ";
//		}
//		fq=fq+")";
		System.out.println("formatFq:"+fq);
		return fq;
		
	}
	public String parseStmt(QueryStmt stmt) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(stmt.getqPara())) {
			sb.append("q=").append(stmt.getqPara());
		}
		if (!StringUtils.isEmpty(stmt.getFqPara())) {
			sb.append("&fq=").append(stmt.getFqPara());
		}
		sb.append("&q.op=OR");
		if (!StringUtils.isEmpty(stmt.fl)) {
			sb.append("&fl=").append(stmt.getFl());
		}
		if (!StringUtils.isEmpty(stmt.getSort())) {
			sb.append("&sort=").append(stmt.getSort());
		}
		if (!StringUtils.isEmpty(stmt.getFacetField())) {
			sb.append("&facet=true").append(generateFacetFieldPara(stmt.getFacetField())).append("&facet.mincount=1");
		}
		// 防止start为负数
		if (stmt.getStart() < 0)
			stmt.setStart(0);
		sb.append("&start=").append(stmt.getStart()).append("&rows=").append(stmt.getRows());
		// 加入扩展参数，如fl、sort等
		if (!StringUtils.isEmpty(stmt.getExtraPara()))
			sb.append("&").append(stmt.getExtraPara());
		return sb.toString();
	}

	public String parsePoiStmt(QueryStmt stmt) {
		StringBuilder sb = new StringBuilder();
		if (!StringUtils.isEmpty(stmt.getqPara())) {
			sb.append("q=").append(stmt.getqPara());
		}
		//sb.append("&d=" + stmt.getD());
		sb.append("&fq={fq}");
		sb.append("&q.op=OR");
		sb.append("&sfield=loc");
		sb.append("&fl=*,score,_dist_:geodist()");
		if (!StringUtils.isEmpty(stmt.getPt())) {
			sb.append("&pt=").append(stmt.getPt());
		}
		if (!StringUtils.isEmpty(stmt.getSort())) {
			sb.append("&sort=").append(stmt.getSort());
		} else sb.append("&sort=geodist() asc");
		// 防止start为负数
		if (stmt.getStart() < 0)
			stmt.setStart(0);
		sb.append("&start=").append(stmt.getStart()).append("&rows=").append(stmt.getRows());
		// 加入扩展参数，如fl、sort等
		if (!StringUtils.isEmpty(stmt.getExtraPara()))
			sb.append("&").append(stmt.getExtraPara());
		return sb.toString();
	}

	public String generateUrl(String engineHost, String coll, QueryStmt stmt) {
		StringBuilder sb = new StringBuilder();
		sb.append(engineHost).append("/").append(coll).append("/select?");
		if (SearchModeEnums.COMMON.equals(stmt.getSearchMode()))
			sb.append(parseStmt(stmt));
		else
			sb.append(parsePoiStmt(stmt));
		sb.append("&wt=json&indent=true&omitHeader=true");
		return sb.toString();
	}

	public String generateUpdateUrl(String engineHost, String collName, QueryStmt stmt) {
		StringBuilder sb = new StringBuilder();
		sb.append(engineHost).append(collName).append("/update?commit=true");
		return sb.toString();
	}

	public String generateSaveUrl(String engineHost, String collName, QueryStmt stmt) {
		return generateUpdateUrl(engineHost, collName, stmt);
	}

	public String generateDeleteUrl(String engineHost, String collName, QueryStmt stmt) {
		return generateUpdateUrl(engineHost, collName, stmt);
	}

}
