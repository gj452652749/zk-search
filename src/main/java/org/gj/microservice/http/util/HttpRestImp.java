package org.gj.microservice.http.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpRestImp implements HttpRest{

	CloseableHttpClient httpclient;
	public HttpRestImp() {
		super();
		// TODO Auto-generated constructor stub
		httpclient=HttpClientBuilder.create()
				.setMaxConnTotal(80)
				.setMaxConnPerRoute(800)
				.build();
	}

	@Override
	public String get(String url) {
		// TODO Auto-generated method stub
		HttpGet get=new HttpGet(url);
		CloseableHttpResponse response;
		try {
			response = httpclient.execute(get);
			HttpEntity entity=response.getEntity();
			String result=EntityUtils.toString(entity);
			return result;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String post(String url, String dataJson) {
		// TODO Auto-generated method stub
		return null;
	}

}
