package com.ui.automation.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeSuite;



public class Base {
	
	public WebDriver driver;
	
	@BeforeSuite
	public void InitializeDriver() throws IOException {
		
		
		FileInputStream fisProp = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\settings.properties");
		Properties prop = new Properties();
		prop.load(fisProp);
		
		String browser = prop.getProperty("BROWSER");
		
		if(browser.equalsIgnoreCase("CHROME")) {
			System.setProperty("Webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\software\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("IE")) {
			System.setProperty("Webdriver.ie.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\software\\IE.exe");
			driver = new InternetExplorerDriver();
		}else if(browser.equalsIgnoreCase("FIREFOX")) {
			System.setProperty("Webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\software\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

}
