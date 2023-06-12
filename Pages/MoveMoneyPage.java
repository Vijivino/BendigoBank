package com.Bendigo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoveMoneyPage {
	
	WebDriver driver;

	@FindBy(xpath="//div[text()='Account Transfer']")
	private WebElement accountTransfer;
	@FindBy(xpath="//div[text()='Pay Anyone']")
	private WebElement payAnyone;

	public MoveMoneyPage(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public TransferPage clickaccountTransfer() throws InterruptedException {
		//Thread.sleep(2000);
		accountTransfer.click();
		return new TransferPage(driver);
	}
	
	public SelectAPayeePage clickPayAnyone() throws InterruptedException {
		//Thread.sleep(2000);
		payAnyone.click();
		//Thread.sleep(2000);
		return new SelectAPayeePage(driver);
	}

}
