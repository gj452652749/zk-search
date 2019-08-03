package com.zkyunso.searchengine.stmt;


public class QueryStmt {
	String qPara=null;
	String fqPara=null;
	String extraPara=null;//&fl=xxx
	int start=0;
	int rows=10;
	
	public QueryStmt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public QueryStmt(String qPara, String fqPara, String extraPara, int start, int rows) {
		super();
		this.qPara = qPara;
		this.fqPara = fqPara;
		this.extraPara = extraPara;
		this.start = start;
		this.rows = rows;
	}

	public String getqPara() {
		return qPara;
	}
	public void setqPara(String qPara) {
		this.qPara = qPara;
	}
	public String getFqPara() {
		return fqPara;
	}
	public void setFqPara(String fqPara) {
		this.fqPara = fqPara;
	}
	
	public String getExtraPara() {
		return extraPara;
	}

	public void setExtraPara(String extraPara) {
		this.extraPara = extraPara;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
