package org.autcrm.contacttest;

import org.autocrm.genericutils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 
 * @author Sasha
 *
 */

public class CreateContactWithOrgTest extends BaseClass
{
	@Test
	public void createContactWithOrgtest() throws Throwable {
		
		
		
		/* read test script specific data*/
		String orgName = elib.getExcelData("contact", 1, 2)+ "_"+ wlib.getRamDomNum();
		String org_Type = elib.getExcelData("contact", 1, 3);
		String org_industry = elib.getExcelData("contact", 1, 4);
		String contactName = elib.getExcelData("contact", 1, 5);
		
		/*step 3 : navigate to Org page*/
		driver.findElement(By.linkText("Organizations")).click();
		
		
		/*step 4 : navigate to create new Org page*/
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
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
		
		
		/*step 7 : navigate to Contact page*/
		driver.findElement(By.linkText("Contacts")).click();
		
		/*step 8 : navigate to create new Contact page*/
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		/*step 9 : creat new Contact page*/
		driver.findElement(By.name("lastname")).sendKeys(contactName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		   //open new tab
	      wlib.switchToNewTab(driver, "specific_contact_account_address");
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(orgName)).click();
		
		//come back to parent Window
		wlib.switchToNewTab(driver, "Administrator - Contacts");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		/*step  10: verify the Org*/
		String actconatct = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(actconatct.contains(contactName));
		

		
		
	}

	

}
