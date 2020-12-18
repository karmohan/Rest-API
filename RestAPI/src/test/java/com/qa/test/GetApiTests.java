package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.RestClient;

public class GetApiTests extends Testbase{
	Testbase testbase;
	RestClient restClient;
	String url;
	String EndpointURL;
	String ServiceURL;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setup() throws IOException {
		//testbase= new Testbase();
		EndpointURL = prop.getProperty("URL");
		ServiceURL = prop.getProperty("serviceURL");
		//https://reqres.in/api/users
		url = EndpointURL + ServiceURL;

		}
	
	@Test(priority=1)
	public void getTestWithoutHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//Getting Status code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code is --->"+ statusCode);
				Assert.assertEquals(statusCode , RESPONSE_CODE_200 , "Status code is not 200");
				
				//Getting JSON response
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJSON = new JSONObject(responseString);
				System.out.println("Resonse body is ---->"+ responseJSON);
				
				//Getting All headers
				Header[] getAllHeader = closeableHttpResponse.getAllHeaders();
				
				HashMap<String,String> allHeaders = new HashMap<>();
				for (Header herader : getAllHeader) {
					
					allHeaders.put(herader.getName(), herader.getValue());
				}
				
				System.out.println("Headers are --->" + allHeaders);
	}
	
	@Test(priority=2)
	public void getTestWithHeader() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String,String> hearderMap = new HashMap<>();
		hearderMap.put("Content-Type", "application/json");
		closeableHttpResponse = restClient.get(url,hearderMap);
		
		//Getting Status code
				int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status code is --->"+ statusCode);
				Assert.assertEquals(statusCode , RESPONSE_CODE_200 , "Status code is not 200");
				
				//Getting JSON response
				String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responseJSON = new JSONObject(responseString);
				System.out.println("Resonse body is ---->"+ responseJSON);
				
				//Getting All headers
				Header[] getAllHeader = closeableHttpResponse.getAllHeaders();
				
				HashMap<String,String> allHeaders = new HashMap<>();
				for (Header herader : getAllHeader) {
					
					allHeaders.put(herader.getName(), herader.getValue());
				}
				
				System.out.println("Headers are --->" + allHeaders);
	}
}
