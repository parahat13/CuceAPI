package steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pages.HomePage;
import com.pages.Login;
import com.pages.SignIn;
import com.utils.Driver;

import beans.RepoNames;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class GITAPISTEPS {
	
	SignIn signIn= new SignIn();
	Login login=new Login();
	HomePage homePage=new HomePage();
	Response response;
	
	
	WebDriver driver;
	
	@Given("I go to following link {string}")
	public void i_go_to_following_link(String url) {
		Driver.getDriver().get(url);
	}

	@When("I click on sign in button")
	public void i_click_on_sign_in_button() {
	   signIn.SignIn.click();
	}

	@Then("I enter my username {string} and password {string}")
	public void i_enter_my_username_and_password(String userName, String password) {
		login.userName.sendKeys(userName);
		login.password.sendKeys(password);
	 }
	
	@Then("I click login button")
	public void i_click_login_button() {
	  login.clickLogin.click();
	}

	@Then("I click on my profile")
	public void i_click_on_my_profile() {
	   homePage.myProfile.click();
	   homePage.yourProfile.click();
	}

	@Then("I should see my list of repos to be {string}")
	public void i_should_see_my_list_of_repos_to_be(String expected) {
	  
		String actual = homePage.repoCount.getText();
		System.out.println(actual);
		Assert.assertEquals(expected, actual);
	}

	
	@When("I navigate to the following link {string}")
	public void i_navigate_to_the_following_link(String url2) {
	   
		Driver.getDriver().get(url2);
	}

	@When("I make a GET request with following link {string}")
	public void i_make_a_GET_request_with_following_link(String urlAPI) {
	  response=RestAssured.get(urlAPI);
	  int actual=response.getStatusCode();
	  Assert.assertEquals(200, actual);
	}

	@Then("I validate repos' names in UI against the names in API")
	public void i_validate_repos_names_in_UI_against_the_names_in_API() throws JsonParseException, JsonMappingException, IOException {
	   
		// Access endPoint
		response=RestAssured.get("http://api.github.com/users/parahat13/repos");
		
		// Assign response to Json
		JsonPath jsPath=response.jsonPath();
				
		ObjectMapper objMap=new ObjectMapper();
		
		// Mapping to into the POJO
		RepoNames[] repNames=objMap.readValue(response.asString(), RepoNames[].class);
		RepoNames[] repFullNames=objMap.readValue(response.asString(), RepoNames[].class);
		
		
		List<String> apiNames=new ArrayList<>();
		List<String> UI_Names=new ArrayList<>();
		
		
		for (RepoNames repoNames : repNames) {
			apiNames.add(repoNames.getName());
		}
		
		for (WebElement repos : homePage.listofRepos) {
			UI_Names.add(repos.getText());
		}
		
		Collections.sort(UI_Names);
		System.out.println("UI Names :"+UI_Names);
		
		//Collections.sort(apiNames);
		System.out.println("API NAMES :"+apiNames);
		
		int count=0;
		
		for (int i = 0; i < UI_Names.size(); i++) {
			
			Assert.assertEquals("Name Matching Failed :"+count, apiNames.get(i), UI_Names.get(i));
			
		}
		
		System.out.println("PASSED");
		
		
		
	}

	
	
}
