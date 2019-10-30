package com.zkyunso.microservice.search.service;

import org.gj.microservice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.zkyunso.microservice.search.dao.DocDao;

/**
 * 处理文档的增删改
 * 
 * @author gaojun
 *
 */
@Service
public class DocService {
	@Autowired
	DocDao dao;
	public String save(String collName,String docJsonStr) {
		dao.save(collName,docJsonStr);
		return JSONObject.toJSONString(new ResponseResult());
	}

	public String execUpdateCmd(String collName, String cmdJson) {
		dao.execUpdateCmd(collName, cmdJson);
		return JSONObject.toJSONString(new ResponseResult());
	}

	public String add(String collName, String docJsonStr) {
		dao.add(collName, docJsonStr);
		return JSONObject.toJSONString(new ResponseResult());
	}

	public void set(String collName, String id, String field, String value) {
		dao.set(collName, id, field, value);
	}

	public String delete(String collName, String idJsonArray) {
		dao.delete(collName, idJsonArray);
		return JSONObject.toJSONString(new ResponseResult());
	}
}
