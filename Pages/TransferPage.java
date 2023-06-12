package com.Bendigo.Pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//ul[@aria-describedby='fromId-field-error']//li[@aria-label='Demo Everyday Account']")
	private WebElement fromAccount;
	@FindBy(xpath="//ul[@aria-describedby='toId-field-error']//li[@aria-label='Demo Everyday Account']")
	private WebElement fromAccInToList;
	@FindBy(xpath="//ul[@aria-describedby='toId-field-error']//li[@aria-label='Demo Multi Signatory Account']")
	private WebElement toAccount;
	@FindBy(xpath="//input[@name='amount']")
	private WebElement amountInput;
	@FindBy(xpath="//input[@placeholder='Description']")
	private WebElement descriptionInput;
	@FindBy(xpath="//a[@data-semantic='now']")
	private WebElement nowButton;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement nextButton;
	@FindBy(id="amount-field-error")
	private WebElement amountAlert;
	@FindBy(xpath="//ul[@aria-describedby='toId-field-error']//li[@data-semantic='checkable-group-title']")
	private WebElement AccHolderName;
	@FindBy(xpath="//li[@aria-label='Demo Multi Signatory Account']//span[contains(text(),'Current')]")
    private WebElement AccBalance;

	public TransferPage(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public boolean checkValidFromAccount() {
		boolean displayed = fromAccount.isDisplayed();
		return displayed;
	}
	public TransferPage selectFromAccount() {
		fromAccount.click();
		return this;
		
	}
	public String checkDisable() {
		String disableStatus=fromAccInToList.getAttribute("aria-disabled");
		return disableStatus;
		
	}
	
	public void getSnap() throws IOException {
		File source=((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
		File destination=new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\DisableFromAccount.png");
		FileUtils.copyFile(source, destination);
	}
	public boolean checkValidToAccount() {
		boolean displayed1 = toAccount.isDisplayed();
		return displayed1;
	}
	public TransferPage selectToAccount() throws InterruptedException {
		Thread.sleep(2000);
		toAccount.click();
		
		String AccHoName = AccHolderName.getText();
		System.out.println("Account Holder name: "+AccHoName);
		String currentAmount = AccBalance.getText();
		System.out.println("Amount in that account :"+currentAmount);
		return this;
	}
	public TransferPage enterAmount(String amountFromExcel) {
		amountInput.sendKeys(amountFromExcel);
		return this;
			}
	public TransferPage enterDescription() {
		descriptionInput.sendKeys("For testing");
		return this;
	}
	public String checkMaxLength() {
		String maxlen = descriptionInput.getAttribute("maxlength");
		return maxlen;
	}
	public String checkDefaultStatus() {
		String defaultStatus = nowButton.getAttribute("aria-selected");
		return defaultStatus;
	}
	public TransferPage selectNow() throws InterruptedException{
		nowButton.click();
		Thread.sleep(2000);
		return this;
	}
	public void takeSnap() throws IOException {
		File source1=((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
		File destination1=new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\EnableNowSnap.png");
		FileUtils.copyFile(source1, destination1);
	}
	
	public ConfirmPage clickNext() throws InterruptedException {
		nextButton.click();
		Thread.sleep(2000);
		return new ConfirmPage(driver);
	}
	public String CheckAmountAlert() {
		String text = amountAlert.getText();
		return text;
	}
	

}
