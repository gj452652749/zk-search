package com.zkyunso.microservice.search.stmt;

public class AnalysisStmt {
	String core;
	String type;
	String query;
	
	public AnalysisStmt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AnalysisStmt(String core, String type, String query) {
		super();
		this.core = core;
		this.type = type;
		this.query = query;
	}
	public String getCore() {
		return core;
	}
	public void setCore(String core) {
		this.core = core;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	

}
