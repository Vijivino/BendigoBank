package com.Bendigo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[contains(text(),'Personal Demo')]")
	private WebElement launPersonalDemo;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}
	
	public UserAccountsPage clickLaunPersonalDemo() {
		
		launPersonalDemo.click();
		return new UserAccountsPage(driver);
	}
	

}
