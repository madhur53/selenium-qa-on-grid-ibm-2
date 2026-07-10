package com.js.selenium_qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.js.selenium_qa.base.BasePage;

public class DashboardPage extends BasePage{
	public final By heading = By.tagName("h2");
	public final By flashMessage = By.id("flash");
	public final By logoutButton = By.cssSelector("a.button");

	public DashboardPage(WebDriver driver) {
		super(driver);
	}
	
	public String getHeading() {
		return this.getText(heading);
	}
	
	public String getFlashMessage() {
		return this.getText(flashMessage);
	}
	
	public LoginPage logout() {
		this.waitAndClick(logoutButton);
		return new LoginPage(driver);
	}
}
