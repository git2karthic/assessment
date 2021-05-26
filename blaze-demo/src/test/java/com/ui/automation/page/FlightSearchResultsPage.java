package com.ui.automation.page;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class FlightSearchResultsPage {

	public WebDriver driver;

	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/h3[1]")
	WebElement text_flightSearchResult;
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/table[1]/tbody[1]")
	WebElement table_flights;

	public FlightSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String GetResultHeader() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(text_flightSearchResult));
		String resultHeader = text_flightSearchResult.getText();
		return resultHeader;
	}

	public void ValidateSearchResult(String resultHeader, String departure, String destination) {
		assertTrue(resultHeader.contains("Flights from " + departure + " to " + destination));
		Reporter.log("Search header is " + resultHeader);
	}

	public void SelectFlight(String searchUsing, String searchValue) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(table_flights));
		List<WebElement> tempElement = table_flights.findElements(By.tagName("tr"));
		int flightRowCount = tempElement.size();

		for (int irow = 1; irow < flightRowCount; irow++) {
			if (searchUsing.equalsIgnoreCase("Flight Number")) {
				WebElement flightRow = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/table[1]/tbody[1]/tr[" + irow + "]/td[2]"));
				int flightNumberInScreen = Integer.parseInt(flightRow.getText());
				if (Integer.parseInt(searchValue) == flightNumberInScreen) {
					WebElement button_chooseTheFlight = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/table[1]/tbody[1]/tr[" + irow + "]/td[1]/input[1]"));
					button_chooseTheFlight.click();
					Reporter.log("Clicked on the flight " + searchValue);
					break;
				}
			}
			if (searchUsing.equalsIgnoreCase("Airline")) {
				WebElement flightRow = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/table[1]/tbody[1]/tr[" + irow + "]/td[3]"));
				String airlineInScreen = flightRow.getText();
				if (airlineInScreen.equalsIgnoreCase(searchValue)) {
					WebElement button_chooseTheFlight = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/table[1]/tbody[1]/tr[" + irow + "]/td[1]/input[1]"));
					button_chooseTheFlight.click();
					Reporter.log("Clicked on the flight " + searchValue);
					break;
				}
			}
		}

	}

}
