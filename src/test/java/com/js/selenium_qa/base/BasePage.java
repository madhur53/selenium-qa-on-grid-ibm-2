package com.js.selenium_qa.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.js.selenium_qa.utils.ConfigReader;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(
					Long.parseLong( ConfigReader.get("explicit.wait") )
				));
		PageFactory.initElements(driver, this);
	}
	
	public WebElement waitForVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitAndClick(By locator) {
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	
	public void clearAndType(By locator, String text) {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(text);
	}
	
	public String getText(By locator) {
		return waitForVisible(locator).getText().trim();
	}
	
	public boolean isDisplayed(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
	
}
