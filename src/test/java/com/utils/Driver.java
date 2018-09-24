package com.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

//	static WebDriver driver;
//
//	public static WebDriver getDriver() {
//
//		if (driver != null) {
//			return driver;
//		}
//
//		System.setProperty("webdriver.chrome.driver","C:/Users/Home/SeleniumDrivers/chromedriver_win32/chromedriver.exe");
//		driver = new ChromeDriver();
//		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		//driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
//		driver.manage().window().fullscreen();
//		return driver;
//	}
//
//	public static void closeDriver() { // this is for hooks
//		if (driver != null) {
//			driver.quit();
//			driver = null;
//		}
//	}
	
	private Driver() {
	}

	private static WebDriver driver;

	public static WebDriver getDriver() {
		if (driver == null) {
			switch (ConfigurationReader.getProperty("browser")) {

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				DesiredCapabilities caps = DesiredCapabilities.chrome();
				caps.setPlatform(Platform.ANY);
				try {
					driver = new RemoteWebDriver(new URL("http://52.70.143.199:4444/wd/hub"), caps);
				} catch (MalformedURLException e) {

					e.printStackTrace();
				}
				break;
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().fullscreen();
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				break;
			default:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
		}
		return driver;
	}

	public static void closeDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}
