package com.js.selenium_qa.pages;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.js.selenium_qa.base.BasePage;

public class LoginPage extends BasePage{

	private final By usernameField = By.id("username");
	private final By passwordField = By.id("password");
	private final By submitButton = By.cssSelector("button[type='submit']");
	private final By flashMessage = By.id("flash");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void navigateTo() {
		String urlString = URI.create( driver.getCurrentUrl() )
				.resolve("/login")
				.toString();
		driver.get( urlString );
	}
	
	public DashboardPage loginAs(String username, String password) {
		this.clearAndType(usernameField,username);
		this.clearAndType(passwordField, password);
		this.waitAndClick(submitButton);
		return new DashboardPage(driver);
	}
	
	
}
