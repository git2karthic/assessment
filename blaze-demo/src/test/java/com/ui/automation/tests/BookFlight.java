package com.ui.automation.tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.automation.base.Base;
import com.ui.automation.dataProvider.BookFlight_DP;
import com.ui.automation.page.FindFlightsPage;
import com.ui.automation.page.FlightSearchResultsPage;
import com.ui.automation.page.OrderSummaryPage;
import com.ui.automation.page.PaymentPage;
import com.ui.automation.utility.ExcelManager;

public class BookFlight extends Base {
	
	public WebDriverWait wait;
	
	@BeforeMethod
	public void NavigateURL() {
		driver.navigate().to("https://blazedemo.com/");
		wait = new WebDriverWait(driver, 20);
	}
	
	@Test(dataProvider = "DP_Bookflight", dataProviderClass = BookFlight_DP.class)
	public void Test_SearchAndBookFlight(String testCaseId, String testCaseDescription, String iteration, String departureCity, String destinationCity, String searchUsing, String searchValue,
			String name, String address, String city, String state, String zipCode, String cardType, String cardNumber, String month, String year, String nameOnCard) throws IOException {
		FindFlightsPage findFlightsPage = new FindFlightsPage(driver);
		findFlightsPage.SearchFlights(departureCity, destinationCity);
		
		FlightSearchResultsPage flightSearchResultsPage = new FlightSearchResultsPage(driver);
		String strResultHeader = flightSearchResultsPage.GetResultHeader();
		flightSearchResultsPage.ValidateSearchResult(strResultHeader, departureCity, destinationCity);
		
		flightSearchResultsPage.SelectFlight(searchUsing, searchValue);
		
		PaymentPage paymentPage = new PaymentPage(driver);
		List<String> priceInfoList = paymentPage.MakePayment(name, address, city, state, zipCode, cardType, cardNumber, month, year, nameOnCard);
		
		// Write data to the excel file
		ExcelManager xl = new ExcelManager();
		xl.putCellData("TC_001", "AIRLINE", priceInfoList.get(0).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "FLIGHT_NUMBER", priceInfoList.get(1).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "PRICE", priceInfoList.get(2).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "FEES_AND_TAXES", priceInfoList.get(3).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "TOTAL_PRICE", priceInfoList.get(4), iteration);
			
		// Get order summary information
		OrderSummaryPage orderSummaryPage = new OrderSummaryPage(driver);
		List<String> orderSummaryList = orderSummaryPage.GetOrderSummary();
		xl.putCellData("TC_001", "CONFIRMATION_ID", orderSummaryList.get(1).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "STATUS", orderSummaryList.get(2).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "AUTH_CODE", orderSummaryList.get(6).split(":")[1].trim(), iteration);
		xl.putCellData("TC_001", "ORDER_DATE", orderSummaryList.get(7).split(":")[1].trim(), iteration);
		assertNotEquals(orderSummaryList.get(0).split(":")[1].trim(), "");
		
		// Compare card details displayed in the summary page
		String cardNumberDisplayed = orderSummaryList.get(4).split(":")[1].trim();
		String lastFourDigitsDisplayed = cardNumberDisplayed.substring(cardNumberDisplayed.length()-4);
		String lastFourDigitsActual = cardNumber.substring(cardNumber.length()-4);
		assertEquals(lastFourDigitsDisplayed, lastFourDigitsActual);

	}
	
	
	@AfterMethod
	public void CloseBrowser() {
		System.out.println("Test Completed");
	}

}
