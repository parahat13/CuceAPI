package com.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.utils.Driver;

public class HomePage {
	
private WebDriver driver;
	
	public HomePage() {
		
		this.driver=Driver.getDriver();
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@id='user-links']/li[3]/details/summary/span")
	public WebElement myProfile;
	
	
	@FindBy(xpath="//*[@id='user-links']/li[3]/details/details-menu/ul/li[3]/a")
	public WebElement yourProfile;
	
	@FindBy(xpath="//*[@id='js-pjax-container']/div/div[2]/div[2]/nav/a[2]/span")
	public WebElement repoCount;
	
	
	@FindBy(xpath="//a[@itemprop='name codeRepository']")
	public List<WebElement> listofRepos;
	

}
