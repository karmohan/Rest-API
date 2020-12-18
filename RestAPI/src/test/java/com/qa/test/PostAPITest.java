package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.data.users;

public class PostAPITest extends Testbase{
	
	
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
	
	@Test
	public void postTest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> hearderMap = new HashMap<>();
		hearderMap.put("Content-Type", "application/json");
		
		//Jackson API - used to convert java object to JSON object - its called Marshal
		ObjectMapper mapper = new ObjectMapper();
		users user=new users("morpheus", "leader");
		
		//java object to json file
		mapper.writeValue(new File("C:\\Users\\Karthik\\eclipse-workspace\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), user);
	    
		//object to json in string
		String userString = mapper.writeValueAsString(user);
		System.out.println("The JSON request is " + userString);
	     
		closeableHttpResponse = restClient.post(url, userString, hearderMap);
		
		//Get Status code
		int StstusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code is --->"+ StstusCode);
		Assert.assertEquals(StstusCode, RESPONSE_CODE_201,  "Status code is not 201");
		
		//Get JSON response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Resonse body is ---->"+ responseJSON);
		
		//verifying the response
		//JSON to java object - unMarshal
		users userResObj = mapper.readValue(responseString, users.class);
		System.out.println(userResObj);
		
		//Assertion to check the actuals value(user.getName()) to received value (userResObj.getName())
		Assert.assertTrue(user.getName().equals(userResObj.getName()));
		Assert.assertTrue(user.getJob().equals(userResObj.getJob()));
		
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
		
		//Get Headers
		Header[] getAllHeader = closeableHttpResponse.getAllHeaders();
		HashMap<String,String> allHeaders = new HashMap<>();
		for(Header header: getAllHeader) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are --->" + allHeaders);
		
		
	
	
	}

}
