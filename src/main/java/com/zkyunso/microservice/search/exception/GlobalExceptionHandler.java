package com.zkyunso.microservice.search.exception;

import javax.servlet.http.HttpServletRequest;

import org.gj.microservice.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public String jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.error(e.getMessage(),e);
		ResponseResult r = new ResponseResult();
		r.setCode(ResponseResult.ERROR);
		r.setErrmsg(e.getMessage());
		return JSONObject.toJSONString(r);
	}

}