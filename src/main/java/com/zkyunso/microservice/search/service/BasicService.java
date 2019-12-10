package com.zkyunso.microservice.search.service;

import java.util.Map;

import org.gj.microservice.vo.ResponseResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class BasicService {
	public JSONArray getDocJsonArray(String result) {
		JSONObject jsonObj=JSON.parseObject(result);
		JSONArray jsonArray=jsonObj.getJSONObject("response")
									.getJSONArray("docs");
		return jsonArray;
	}
	public String formatResult(String result) {
		ResponseResult resultResp=new ResponseResult();
		JSONArray docArray=getDocJsonArray(result);
		resultResp.setData(docArray);
		return JSONObject.toJSONString(resultResp);
	}
	public String formatResultWithRows(String result) {
		ResponseResult resultResp=new ResponseResult();
		JSONObject jsonObj=JSON.parseObject(result);
		JSONArray jsonArray=jsonObj.getJSONObject("response")
									.getJSONArray("docs");
		JSONObject dataJson=new JSONObject();
		int numFound=jsonObj.getJSONObject("response").getIntValue("numFound");
		dataJson.put("numFound", numFound);
		dataJson.put("records", jsonArray);
		resultResp.setData(dataJson);
		return JSONObject.toJSONString(resultResp);
	}
	
	public String formatFacetResultWithRows(String result) {
		ResponseResult resultResp=new ResponseResult();
		JSONObject jsonObj=JSON.parseObject(result);
		JSONArray jsonArray=jsonObj.getJSONObject("response")
									.getJSONArray("docs");
		JSONObject dataJson=new JSONObject();
		int numFound=jsonObj.getJSONObject("response").getIntValue("numFound");
		dataJson.put("numFound", numFound);
		dataJson.put("records", jsonArray);
		
		JSONObject facetJsonObj=jsonObj.getJSONObject("facet_counts")
				.getJSONObject("facet_fields");
		JSONArray facetCounts =new JSONArray();
		for (Map.Entry<String, Object> entry : facetJsonObj.entrySet()) {
			facetCounts.add(entry.getValue());
        }
		dataJson.put("facet_counts", facetCounts);
		
		resultResp.setData(dataJson);
		return JSONObject.toJSONString(resultResp);
	}
}
