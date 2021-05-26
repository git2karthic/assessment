package com.ui.automation.page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSummaryPage {
	
	public WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//h1[normalize-space()='Thank you for your purchase today!']")
	WebElement header_OrderSummary;
	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[2]")
	WebElement text_confirmationId;
	@FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[2]")
	WebElement text_Status;
	@FindBy(how = How.XPATH, using = "//tbody/tr[3]/td[2]")
	WebElement text_amount;
	@FindBy(how = How.XPATH, using = "//tbody/tr[4]/td[2]")
	WebElement text_cardNumber;
	@FindBy(how = How.XPATH, using = "//tbody/tr[5]/td[2]")
	WebElement text_expiration;
	@FindBy(how = How.XPATH, using = "//tbody/tr[6]/td[2]")
	WebElement text_AuthCode;
	@FindBy(how = How.XPATH, using = "//tbody/tr[7]/td[2]")
	WebElement text_Date;
	
	public OrderSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> GetOrderSummary() {
		
		WebDriverWait wait = new WebDriverWait(this.driver, 10);
		wait.until(ExpectedConditions.visibilityOf(header_OrderSummary));
		
		List<String> orderInfoList = new ArrayList<String>();
		orderInfoList.add("header : " + header_OrderSummary.getText().trim());
		orderInfoList.add("id : " + text_confirmationId.getText().trim());
		orderInfoList.add("status : " + text_Status.getText().trim());
		orderInfoList.add("amount : " + text_amount.getText().trim());
		orderInfoList.add("card : " + text_cardNumber.getText().trim());
		orderInfoList.add("expiration : " + text_expiration.getText().trim());
		orderInfoList.add("authCode : " + text_AuthCode.getText().trim());
		orderInfoList.add("date : " + text_Date.getText());
		
		return orderInfoList;
	}

}
