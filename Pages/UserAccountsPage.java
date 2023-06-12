package com.Bendigo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountsPage {

	WebDriver driver;

	@FindBy(xpath="//span[contains(text(),'Move Money')]")
	private WebElement moveMoney;

	public UserAccountsPage(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void clickMoveMoney() throws InterruptedException {
		Actions builder=new Actions(driver);
		builder.moveToElement(moveMoney).click().perform();
		Thread.sleep(2000);
	}


}
