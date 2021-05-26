package com.ui.automation.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class FindFlightsPage {
	
	public WebDriver driver;
	

	
	@FindBy(how = How.XPATH, using = "//select[@name='fromPort']")
	WebElement list_departureCity;
	@FindBy(how = How.XPATH, using = "//select[@name='toPort']")
	WebElement list_destinationCity;
	@FindBy(how = How.XPATH, using = "//input[@value='Find Flights']")
	WebElement button_findFlights;
	
	public FindFlightsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void SearchFlights(String departure, String destination) {
		
		/*  Code to wait till the departure and destination city dropdowns are visible and clickable */
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(list_departureCity));
		Reporter.log("Departure city dropdown is visible");
		wait.until(ExpectedConditions.elementToBeClickable(list_destinationCity));
		Reporter.log("Destination city dropdown is visible");
		
		/*  Select the departure city */
		Select selectDepartureCity = new Select(list_departureCity);
		List<WebElement> listDepartureCity = selectDepartureCity.getOptions();
		for(int ilist = 0; ilist < listDepartureCity.size(); ilist++) {
			String departureCityTemp = listDepartureCity.get(ilist).getText();
			if(departureCityTemp.equalsIgnoreCase(departure)) {
				selectDepartureCity.selectByIndex(ilist);
				Reporter.log("Selected the city " + departure + " from the departure dropdown");
			}
		}
		
		
		/* Select the destination city */
		Select selectDestinationCity = new Select(list_destinationCity);
		List<WebElement> listDestinationCity = selectDestinationCity.getOptions();
		for(int ilist = 0; ilist < listDestinationCity.size(); ilist++) {
			String destinationCityTemp = listDestinationCity.get(ilist).getText();
			if(destinationCityTemp.equalsIgnoreCase(destination)) {
				selectDestinationCity.selectByIndex(ilist);
				Reporter.log("Selected the city " + destination + " from the destination dropdown");
			}
		}
				
		button_findFlights.click();
		Reporter.log("Clicked on the search flights button");
	}

}
