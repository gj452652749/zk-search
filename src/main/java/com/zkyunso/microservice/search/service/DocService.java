package com.zkyunso.microservice.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zkyunso.microservice.search.dao.DocDao;
/**
 * 处理文档的增删改
 * @author gaojun
 *
 */
@Service
public class DocService {
	@Autowired
	DocDao dao;
	public void add(String collName,String docJsonStr) {
		dao.add(collName,docJsonStr);
	}
	public void set(String collName,String id,String field,String value) {
		dao.set(collName,id,field,value);
	}
	public void delete(String idJsonArray) {
		
	}
}
