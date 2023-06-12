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

import com.Bendigo.Base.Base;
import com.Bendigo.Pages.ConfirmPage;
import com.Bendigo.Pages.HomePage;
import com.Bendigo.Pages.MoveMoneyPage;
import com.Bendigo.Pages.SelectAPayeePage;
import com.Bendigo.Pages.UserAccountsPage;
import com.Bendigo.Utilities.Utils;

public class PayAnyoneTest extends Base{

	public PayAnyoneTest() {
		super();
	}

	public WebDriver driver;
	HomePage home;
	UserAccountsPage accounts;
	MoveMoneyPage move;
	SelectAPayeePage payee;
	ConfirmPage confirm;


	@BeforeMethod
	public void setup() throws InterruptedException, IOException {

		driver = InitializeBrowser(prop.getProperty("browsername"));
		home=new HomePage(driver);
		home.clickLaunPersonalDemo();
        accounts=new UserAccountsPage(driver);
		accounts.clickMoveMoney();
		
	}

	@Test(priority=2,description="Transfer money to anyone who is an existing payee",
			retryAnalyzer = com.Bendigo.Utilities.RetryEngine.class,
			dataProvider="payeeData")
	public void TransferToAnyone(String FromAccount,String Amount) throws InterruptedException, IOException {

		move=new MoveMoneyPage(driver);
		move.clickPayAnyone();
		payee=new SelectAPayeePage(driver);
		payee.selectFirstPayee();
		
		Assert.assertEquals(payee.checkValidFromAccount(),FromAccount,"Valid From Account is not available");
		payee.selectFromAccount();
		File source1=((RemoteWebDriver)driver).getScreenshotAs(OutputType.FILE);
		File destination1=new File("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\fromAccountPayee.png");
		FileUtils.copyFile(source1, destination1);
		
		payee.enterAmount(Amount);
		payee.enterDescription();
		payee.enterReference();
		payee.clickNext();
		confirm=new ConfirmPage(driver);
		confirm.clickSubmit();
		
	}

	@DataProvider(name="payeeData")
	public Object[][] getData() throws IOException {

		Object[][] data=Utils.readExcelData("TC_003");
		return data;

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
