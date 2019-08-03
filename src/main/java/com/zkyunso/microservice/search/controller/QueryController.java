package com.zkyunso.microservice.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkyunso.microservice.search.service.QueryService;
import com.zkyunso.searchengine.stmt.QueryStmt;

@RestController
@RequestMapping("/search/{collName}/query")
@CrossOrigin
public class QueryController {
	@Autowired
	QueryService service;
	@RequestMapping("/select")
	@ResponseBody
	public String search(@PathVariable(value = "collName") String collName, QueryStmt stmt) throws Exception {
		// return service.select("im_chatrecord",stmt);
		return service.select(collName, stmt);
	}
}
