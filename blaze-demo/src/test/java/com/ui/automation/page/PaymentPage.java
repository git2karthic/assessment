package com.ui.automation.page;

import java.util.ArrayList;
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

public class PaymentPage {
	
	public WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/p[1]")
	WebElement text_Airline;
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/p[2]")
	WebElement text_FlightNumber;
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/p[3]")
	WebElement text_Price;
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/p[4]")
	WebElement text_TaxesAndFees;
	@FindBy(how = How.XPATH, using = "/html[1]/body[1]/div[2]/p[5]/em[1]")
	WebElement text_TotalCost;
	
	@FindBy(how = How.XPATH, using = "//input[@id='inputName']")
	WebElement input_Name;
	@FindBy(how = How.XPATH, using = "//input[@id='address']")
	WebElement input_Address;
	@FindBy(how = How.XPATH, using = "//input[@id='city']")
	WebElement input_City;
	@FindBy(how = How.XPATH, using = "//input[@id='state']")
	WebElement input_State;
	@FindBy(how = How.XPATH, using = "//input[@id='zipCode']")
	WebElement input_ZipCode;
	@FindBy(how = How.XPATH, using = "//select[@id='cardType']")
	WebElement dropdown_CardType;
	@FindBy(how = How.XPATH, using = "//input[@id='creditCardNumber']")
	WebElement input_creditCardNumber;
	@FindBy(how = How.XPATH, using = "//input[@id='creditCardMonth']")
	WebElement input_Month;
	@FindBy(how = How.XPATH, using = "//input[@id='creditCardYear']")
	WebElement input_Year;
	@FindBy(how = How.XPATH, using = "//input[@id='nameOnCard']")
	WebElement input_NameOnCard	;
	
	@FindBy(how = How.XPATH, using = "//body/div[2]/form[1]/div[11]/div[1]/input[1]")
	WebElement button_Purchase	;
	
	
	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> MakePayment(String name, String address, String city, String state, String zipCode, String cardType, String cardNumber, String month, String year, String nameOnCard){
		
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.visibilityOf(input_Name));
		wait.until(ExpectedConditions.elementToBeClickable(input_Name));
				
		List<String> priceInfoList = new ArrayList<String>();
		priceInfoList.add(text_Airline.getText());
		priceInfoList.add(text_FlightNumber.getText());
		priceInfoList.add(text_Price.getText());
		priceInfoList.add(text_TaxesAndFees.getText());
		priceInfoList.add(text_TotalCost.getText());
		
		
		input_Name.sendKeys(name);
		Reporter.log("Entered the name of the passenger : " + name);
		input_Address.sendKeys(address);
		Reporter.log("Entered the address : " + address);
		input_City.sendKeys(city);
		Reporter.log("Entered the city : " + city);
		input_State.sendKeys(state);
		Reporter.log("Entered the state : " + state);
		input_ZipCode.sendKeys(zipCode);
		Reporter.log("Entered the zipcode : " + zipCode);
		
		Select selecCard = new Select(dropdown_CardType);
		if(cardType.equalsIgnoreCase("Visa")) {
			selecCard.selectByIndex(0);
		}
		if(cardType.equalsIgnoreCase("American Express")) {
			selecCard.selectByIndex(1);
		}
		Reporter.log("Selected the card type : " + cardType);
		
		input_creditCardNumber.sendKeys(cardNumber);
		Reporter.log("Entered the credit card number : " + cardNumber);
		input_Month.clear();
		input_Month.sendKeys(month);
		Reporter.log("Entered the month : " + month);
		input_Year.clear();
		input_Year.sendKeys(year);
		Reporter.log("Entered the year : " + year);
		input_Name.clear();
		input_NameOnCard.sendKeys(nameOnCard);
		Reporter.log("Entered the name on card : " + nameOnCard);
		
		button_Purchase.click();
		Reporter.log("");
		
		return priceInfoList;
	}

}
