package com.zkyunso.microservice.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zkyunso.microservice.search.service.DocService;

@RestController
@RequestMapping("/search/{collName}/doc")
@CrossOrigin
public class DocController {
	@Autowired
	DocService docService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(@PathVariable(value = "collName") String collName, @RequestBody String docJsonStr)
			throws Exception {
		return docService.add(collName, docJsonStr);
	}

	@RequestMapping(value = "/set", method = RequestMethod.POST)
	@ResponseBody
	public void set(String collName, String id, String field, String value) throws Exception {
		docService.set("im_chatrecord", id, field, value);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String delete(@PathVariable(value = "collName") String collName,@RequestBody String idJsonArray) throws Exception {
		return docService.delete(collName,idJsonArray);
	}
}
