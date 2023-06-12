package com.Bendigo.Base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.Bendigo.Utilities.Utils;

public class Base {

	WebDriver driver;
	public Properties prop;

	//load the properties inside the constructor so that it can be called inside all the tests 
	public Base(){

		prop=new Properties();
		try {
		FileInputStream fis=new FileInputStream("C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\src\\test\\java\\com\\Bendigo\\Config\\Config.properties");
		prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	public WebDriver InitializeBrowser(String browser) {

		if(browser.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utils.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utils.PAGE_LOAD_TIME));

		return driver;
	}
	

}
