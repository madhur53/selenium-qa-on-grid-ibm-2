package com.js.selenium_qa.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverFactory {
    public static WebDriver createDriver() throws MalformedURLException {
        // Read the environment variable (defaults to local if not set)
        String env = System.getenv("EXECUTION_ENV");
        if (env == null) {
            env = "LOCAL";
        }

        ChromeOptions options = new ChromeOptions();
        if (env.equalsIgnoreCase("REMOTE")) {
            // Configuration for Selenium Grid / Docker
            options.addArguments("--headless", "--no-sandbox",
                    "--disable-dev-shm-usage", "--disable-gpu");
            // selenium-hub is docker service name
            String gridUrl = "http://selenium-hub:4444/wd/hub"; 
            return new RemoteWebDriver(new URL(gridUrl), options);
        } else {
            // Configuration for local development
        	options.addArguments("--headless", "--no-sandbox", 
        			"--disable-dev-shm-usage", "--disable-gpu");
            return new ChromeDriver();
        }
    }
}

