package com.cuke.runners;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Demo {
	
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver","C:/Users/Home/SeleniumDrivers/chromedriver_win32/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://github.com/");
	}

}
