package com.Bendigo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectAPayeePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='flag__content']/span[not(text()='New Payee')]")
	private WebElement firstPayee;
	@FindBy(xpath="//span[text()='Demo Multi Signatory Account']")
	private WebElement fromAccount;
	@FindBy(name="amount")
	private WebElement amount;
	@FindBy(name="description")
	private WebElement descriptionInput;
	@FindBy(xpath="//input[@class='input']")
	private WebElement referenceInput;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement nextButton;

	public SelectAPayeePage(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectFirstPayee() throws InterruptedException {
		System.out.println("Payee selected is "+firstPayee.getText());
		firstPayee.click();
		Thread.sleep(2000);
		
	}
	public String checkValidFromAccount() {
		String fromAccType = fromAccount.getText();
		return fromAccType;	
	}
	public void selectFromAccount() {
		fromAccount.click();
		System.out.println("Amount transfered from :"+fromAccount.getText());
		
	}
	
	public void enterAmount(String money) {
		amount.sendKeys(money);
		
	}
	
	public void enterDescription() {
		descriptionInput.sendKeys("For Testing");
		
	}
	
	public void enterReference() {
		referenceInput.sendKeys("5678902");
		
	}
	
	public void clickNext() throws InterruptedException {
		nextButton.click();
		Thread.sleep(2000);
	}
	
	
	
	
	
}
