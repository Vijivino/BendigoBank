package com.Bendigo.Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Bendigo.Base.Base;
import com.Bendigo.Listeners.MyListeners;
import com.Bendigo.Pages.ConfirmPage;
import com.Bendigo.Pages.HomePage;
import com.Bendigo.Pages.MoveMoneyPage;
import com.Bendigo.Pages.TransferPage;
import com.Bendigo.Pages.UserAccountsPage;
import com.Bendigo.Utilities.Utils;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class AccountTransferTest extends Base{

	//create constructor to load the properties under the base class constructor
	public AccountTransferTest() {
		super();
	}

	public WebDriver driver;
	HomePage home;
	UserAccountsPage accounts;
	MoveMoneyPage move;
	TransferPage transfer;
	ConfirmPage confirm;
	
	

	@BeforeMethod
	public void setup() throws InterruptedException {

		driver = InitializeBrowser(prop.getProperty("browsername"));
		home=new HomePage(driver);
		UserAccountsPage accounts = home.clickLaunPersonalDemo();
		accounts.clickMoveMoney();
	}
	
	@Test(description="Transfer money within bank with valid amount",
			retryAnalyzer = com.Bendigo.Utilities.RetryEngine.class,dataProvider="validData")
	public void TransferWithinBank(String Amount,String ExpectedMessage) throws InterruptedException, IOException {

		
		move=new MoveMoneyPage(driver);
		TransferPage transfer =move.clickaccountTransfer();
		
		//select from account	
		Assert.assertTrue(transfer.checkValidFromAccount(),"Valid From account is not displayed");
		transfer.selectFromAccount();
		
		//validation for disable
		transfer.checkDisable();
		Assert.assertEquals(transfer.checkDisable(), "true","The selected From account is Not disabled");
        transfer.getSnap();
        MyListeners.test.log(Status.PASS, "Assertion for the selected from account should be disabled is satisfied.");
		MyListeners.test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\DisableFromAccount.png").build());
        
		//select to account
		Assert.assertTrue(transfer.checkValidToAccount(),"Valid To account is not displayed");
		transfer.selectToAccount();
		transfer.enterAmount(Amount);
		transfer.enterDescription();
		
		//validate the length of input field
		SoftAssert sassert=new SoftAssert();
		sassert.assertEquals("18",transfer.checkMaxLength(),"Max length is not satisfied") ;
		sassert.assertAll();
		
		//now should be selected by default
		Assert.assertEquals(transfer.checkDefaultStatus(),"true","The now button is not selected by default");
		transfer.selectNow();
		transfer.takeSnap();
		MyListeners.test.log(Status.PASS, "Assertion for the Now button should be Enabled by default is verified.");
		MyListeners.test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\EnableNowSnap.png").build());
		
		ConfirmPage confirm=transfer.clickNext();
		//click submit
		confirm.clickSubmit();
		//validation of alert message
		Assert.assertTrue(confirm.validateAlertText().contains(ExpectedMessage),"Alert message is not shown");
		confirm.getSnapAlert();
		MyListeners.test.log(Status.PASS, "Assertion for Alert box present is verified.");
		MyListeners.test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\AlertSnap.png").build());
		confirm.clickOkInAlert();
	}

	@DataProvider(name="validData")
	public Object[][] getData() throws IOException {

		Object[][] data=Utils.readExcelData("TC_001");
		return data;

	}



	@Test(priority=1,description="Transfer money within bank with invalid amount",
			retryAnalyzer = com.Bendigo.Utilities.RetryEngine.class,dataProvider="invalidData")
	public void TransferWithinBankInvalid(String Amount,String ExpectedMessage) throws InterruptedException {

		move=new MoveMoneyPage(driver);
		move.clickaccountTransfer();
		//select from and to account
		new TransferPage(driver)
		.selectFromAccount()
		.selectToAccount()
		.enterAmount(Amount)
		.enterDescription()
		.selectNow()
		.clickNext();
		MyListeners.test.log(Status.PASS, "Assertion verified for valid from account as per excel input is selected .");
		MyListeners.test.log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\fromAccountPayee.png").build());
		//validation for alert message
		Assert.assertEquals(new TransferPage(driver).CheckAmountAlert(), ExpectedMessage);
		System.out.println("The validation message for invalid amount is verified as follows : "+new TransferPage(driver).CheckAmountAlert());

	}

	@DataProvider(name="invalidData")
	public Object[][] getinvalidData() throws IOException {

		Object[][] data=Utils.readExcelData("TC_002");
		return data;
	}


	@AfterMethod
	public void tearDown() {

		driver.quit();

	}

}
