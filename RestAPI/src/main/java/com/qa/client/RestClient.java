package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); //http get request
		CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpGet); //hit the GET URL
		return closeableHttpResponse;
		}
	
	public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); //http get request
		for(Map.Entry<String, String> entry: headerMap.entrySet()) {
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpGet); //hit the GET URL
		return closeableHttpResponse;
		}
	
	public CloseableHttpResponse post(String url, String entryString,  HashMap<String,String> headmap) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClients = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); //http post request
		httpPost.setEntity(new StringEntity(entryString)); //passing payload (request JSON objects)
		
		for(Map.Entry<String, String> entry: headmap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClients.execute(httpPost);
		return closeableHttpResponse;
		
	}

}
