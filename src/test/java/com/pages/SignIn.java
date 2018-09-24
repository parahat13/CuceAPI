package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.Driver;

public class SignIn {

	private WebDriver driver;

	public SignIn() {
		this.driver = Driver.getDriver();
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(linkText="Sign in")
	public WebElement SignIn;
}
