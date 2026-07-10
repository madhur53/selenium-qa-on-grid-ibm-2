package com.js.selenium_qa.base;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.js.selenium_qa.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setup() {
		WebDriverManager.chromedriver().setup();
	//  Changed below for CICD	
	//	driver = new ChromeDriver();
		try {
			driver = WebDriverFactory.createDriver();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
						Long.parseLong( ConfigReader.get("implicit.wait") ) 
						));
		driver.get( ConfigReader.get("base.url") );
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver != null) driver.quit();
	}
	
	
	

}
