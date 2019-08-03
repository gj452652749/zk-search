package org.gj.microservice.vo;

public class ResponseResult {
	public static final Integer SUCESS = 0;
    public static final Integer ERROR = 1;
	int code=0;
	String errmsg;
	Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

}
