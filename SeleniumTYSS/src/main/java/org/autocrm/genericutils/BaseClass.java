package org.autocrm.genericutils;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public DataBaseLib dblib = new DataBaseLib();
	public ExcelLib elib = new ExcelLib();
	public PropLib plib = new PropLib();
	public WebDriverUtils wlib= new WebDriverUtils();
	public WebDriver driver = null;
	
	/*Connect to DB*/
	
	@BeforeSuite
	public void ConfigBS()
	{
		dblib.connectToDB();
	}
	
	@BeforeClass
	public void ConfigBC() throws Throwable 
	{
		String BROWSER= plib.getPropertyKeyValue("browser");
		
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		
		else if(BROWSER.equals("firefox"))
		{
			driver= new FirefoxDriver();
		}
		
		else if(BROWSER.equals("ie"))
		{
			driver=new InternetExplorerDriver();
		}
		
		else 
		{
			driver=new FirefoxDriver();
		}
		
	}
	
	
/*	@Parameters("browser")
	@BeforeTest
	public void configBT(String BROWSER) throws Throwable{
		  
		 if(BROWSER.equals("chrome")) {
		   driver= new ChromeDriver();
		 } else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		 }else if(BROWSER.equals("ie")) {
				driver = new InternetExplorerDriver();
	     }else {
	    	 driver = new FirefoxDriver();
	     }
	}*/
	
	/* To login */
	@BeforeMethod
	public void configBM() throws Throwable
	/*To read data from property file*/
	{
		String USERNAME=plib.getPropertyKeyValue("username");
		String PASSWORD=plib.getPropertyKeyValue("password");
		String URL=plib.getPropertyKeyValue("url");
		
		/*Go to the app*/
		wlib.waitForPagetoLoad(driver);
		driver.get(URL);
		
		/* Login inside app*/
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();	
		
	}
	
	@AfterMethod
	public void configAm() {
		/*logout */
		
		/*step 1 : logout*/
		WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        wlib.moveMouseToElemnet(driver, wb);
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	@AfterClass
	public void configAC(){
		/*close the browser*/
		driver.close();
	}
	
	
	@AfterSuite
	public void confiAs() {
		/*dissconnect to db*/
		dblib.dissconnectToDb();
	}
	
	
	
}
