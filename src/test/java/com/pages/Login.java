package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.Driver;

public class Login {
	
	private WebDriver driver;
	
	public Login() {
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="login_field")
	public WebElement userName;
	

	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(xpath="//*[@id='login']/form/div[3]/input[3]")
	public WebElement clickLogin;
	

}
