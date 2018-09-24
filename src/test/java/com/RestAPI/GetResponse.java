package com.RestAPI;

import org.junit.Test;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetResponse {
	
	@Test
	public void getRequest() {
		
		Response res=RestAssured.get("http://api.github.com/users/parahat13/repos");
		
		System.out.println(res.statusCode());
		System.out.println(res.asString());
		
	}
	
	

}
