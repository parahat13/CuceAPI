package post_api;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.PostValues;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetPostRequest {
	
	PostValues pv=new PostValues();
	
	
	@Test
	public void postRequest() throws JsonProcessingException {
		
		Random random=new Random();
		PostValues pv=new PostValues();
		
		pv.setAuthor("Sarah");
		pv.setId(random.nextInt(10));
		pv.setTitle("BA");
		pv.setAuthor("Mike");
		pv.setId(random.nextInt(10));
		pv.setTitle("DEV");
		pv.setAuthor("John");
		pv.setId(random.nextInt(10));
		pv.setTitle("PO");
		
		ObjectMapper ob=new ObjectMapper();
		
		String jsonFormat=ob.writeValueAsString(pv);
		
		RestAssured.given().contentType(ContentType.JSON)
		     	    .body(jsonFormat)
		            .post("http://localhost:3000/posts");
			
	}
	
	
	@Test
	public void postDataVerify() throws JsonProcessingException {
		
		Response res=RestAssured.get("http://localhost:3000/posts");
		
//		System.out.println("Status code :"+res.statusCode());
//		System.out.println(res.asString());
//	
		JsonPath js=res.jsonPath();
	//	System.out.println(js.toString());
		
		int id=js.getInt("[2].id");
		String title=js.get("[2].title").toString();
		String author=js.get("[2].author").toString();
		
		System.out.println(id);
		System.out.println(title);
		System.out.println(author);
		
		Assert.assertEquals(id, 10);
		Assert.assertEquals(title, "DEV");
		Assert.assertEquals(author, "Mike");
		
		
		
	}
	
	@Test
	public void deleteRequest() {
		
		RestAssured.when().delete("http://localhost:3000/posts/8");
		
		
		
	}
	
	

}
