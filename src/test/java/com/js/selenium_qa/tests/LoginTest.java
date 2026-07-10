package com.js.selenium_qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.js.selenium_qa.base.BaseTest;
import com.js.selenium_qa.pages.DashboardPage;
import com.js.selenium_qa.pages.LoginPage;

public class LoginTest extends BaseTest{
	
	LoginPage loginPage;
	
	@BeforeMethod(alwaysRun = true)
	public void goToLogin() {
		loginPage = new LoginPage(driver);
		loginPage.navigateTo();
	}
	
	@Test( description = "Valid login navigates to secure area", groups = {"Login"} )
	public void testValidLogin() {
		DashboardPage dashboardPage = loginPage.loginAs("tomsmith", "SuperSecretPassword!");
		String flashMessage = dashboardPage.getFlashMessage();
		Assert.assertTrue(flashMessage.contains("secure area"), 
				"Expected - secure area - but got: " + flashMessage);
	}
	
	@Test( description = "Invalid login shows a error message")
	public void testInvalidLogin() {
		DashboardPage dashboardPage = loginPage.loginAs("wrong", "wrong");
		String flashMessage = dashboardPage.getFlashMessage();
		Assert.assertTrue(flashMessage.contains("invalid"),
				"Expected - invalid - but got: " + flashMessage);
	}
	

}
