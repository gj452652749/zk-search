package com.zkyunso.microservice.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkyunso.microservice.search.service.AppService;
import com.zkyunso.microservice.search.stmt.SearchStmt;

@RestController
@RequestMapping("/zkyunso")
public class AppController {
	@Autowired
	AppService service;
	@RequestMapping("/do")
	@ResponseBody
	public String doThing() {
		System.out.println();
		return "sucess";
	}
	@RequestMapping("/search")
	@ResponseBody
	public String search(SearchStmt stmt) {
		return service.doSearch(stmt);
	}
	@RequestMapping("/inorderserach")
	@ResponseBody
	public String inorderserach(String json) {
		return null;
	}
	@RequestMapping("/offsetserach")
	@ResponseBody
	public String offsetserach(String json) {
		return null;
	}
}
