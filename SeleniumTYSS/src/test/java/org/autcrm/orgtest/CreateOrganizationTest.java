package org.autcrm.orgtest;

import org.autocrm.genericutils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * 
 * @author Deepak
 *
 */
public class CreateOrganizationTest extends BaseClass{
	
	@Test
	public void createORgtest() throws Throwable {

		
		/* read test script specific data*/
		String orgName = elib.getExcelData("org", 1, 2)+ "_"+ wlib.getRamDomNum();
		String org_Type = elib.getExcelData("org", 1, 3);
		String org_industry = elib.getExcelData("org", 1, 4);

		
		/*step 3 : navigate to Org page*/
		driver.findElement(By.linkText("Organizations")).click();
		
		
		/*step 4 : navigate to create new Org page*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...]")).click();
		
		/*step 5 : create Org*/
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	
		
		WebElement  swb1 = driver.findElement(By.name("accounttype"));
	    wlib.select(swb1, org_Type);
				
		WebElement  swb2 = driver.findElement(By.name("industry"));
		wlib.select(swb2, org_industry);
				
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/*step 6 : verify the Org*/
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));

		
		
	}
	
	@Test
	public void createOrg() throws Throwable {
		/* read test script specific data*/
		String orgName = elib.getExcelData("org", 4 ,2)+ "_"+ wlib.getRamDomNum();
		
		/*step 3 : navigate to Org page*/
		driver.findElement(By.linkText("Organizations")).click();
		
		
		/*step 4 : navigate to create new Org page*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		/*step 5 : create Org*/
		driver.findElement(By.name("accountname")).sendKeys(orgName);
	    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

			/*step 6 : verify the Org*/
			String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

			Assert.assertTrue(actOrgName.contains(orgName));
	}
	
	

}
