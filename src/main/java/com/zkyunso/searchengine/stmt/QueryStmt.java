package com.zkyunso.searchengine.stmt;

import com.zkyunso.microservice.search.constant.SearchModeEnums;

public class QueryStmt {
	String qPara = null;
	String fqPara = null;
	String fl;
	String sfield = null;
	String pt = null;
	int d = 0;
	String sort = null;
	String extraPara = null;// &fl=xxx
	String searchMode = SearchModeEnums.COMMON;
	String facetField=null;
	int start = 0;
	int rows = 10;

	public QueryStmt() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QueryStmt(String qPara, String fqPara,String fl, String sfield, String pt, int d, String sort, String extraPara,
			String searchMode,String facetField, int start, int rows) {
		super();
		this.qPara = qPara;
		this.fqPara = fqPara;
		this.fl=fl;
		this.sfield = sfield;
		this.pt = pt;
		this.d = d;
		this.sort = sort;
		this.extraPara = extraPara;
		this.searchMode = searchMode;
		this.facetField=facetField;
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

	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}

	public String getSfield() {
		return sfield;
	}

	public void setSfield(String sfield) {
		this.sfield = sfield;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getExtraPara() {
		return extraPara;
	}

	public void setExtraPara(String extraPara) {
		this.extraPara = extraPara;
	}

	public String getFacetField() {
		return facetField;
	}

	public void setFacetField(String facetField) {
		this.facetField = facetField;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

}
