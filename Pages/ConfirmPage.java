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

public class ConfirmPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submitButton;
	@FindBy(id="alert-desc")
	private WebElement alertBox;
	@FindBy (xpath="//span[text()='OK']")
	private WebElement okButton;


	public ConfirmPage(WebDriver driver) {

		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSubmit() {
		
		submitButton.click();
	}

	public String validateAlertText() {
		String Alerttext = alertBox.getText();
		return Alerttext;
	}
	
	public void getSnapAlert() throws IOException {
		File source1=((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
		File destination1=new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\AlertSnap.png");
		FileUtils.copyFile(source1, destination1);
	}
	
	public void clickOkInAlert() {
		okButton.click();
	}
}
