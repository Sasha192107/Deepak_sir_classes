package org.autcrm.contacttest;

import org.autocrm.genericutils.BaseClass;
import org.autocrm.genericutils.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteContactAndCheckOrgTest extends BaseClass
{
	@Test
	public void createContactWithOrgtest() throws Throwable
	{
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
		
		/* Step 11 : Goto contacts page*/
		driver.findElement(By.linkText("Contacts")).click();
		
		/* S12: Search the contact*/
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		WebElement e1 = driver.findElement(By.name("search_field"));
		wlib.select(e1, "Organization Name");
		driver.findElement(By.name("submit")).click();
		
		/* S13 : Select all contacts*/
		
		String s1="//a[text()=\"";
		String s2 ="\"]/ancestor::tr[1]/descendant::td//input";
		String s3=s1+orgName+s2;
		
		Thread.sleep(500);
		driver.findElement(By.xpath(s3)).click();
		
		/*Step 14: Click on the delete button*/
			
		driver.findElement(By.xpath("//input[@class=\"crmbutton small delete\"]")).click();
		
		/*s12 : Handle the delete alert */
		wlib.alertOk(driver);
		
		/* S13: Again click on Organization link */
		driver.findElement(By.linkText("Organizations")).click();
		
		/* S14: Click on organisation search box and search for org*/
		
		WebElement sb1 = driver.findElement(By.name("search_field"));
		
		wlib.select(sb1,"Organization Name");
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("submit")).click();
		
		/* Step 10: Select all the pages in by clicking org checkbox*/
		
		String s4="//a[text()=\"";
		String s5="\"]/ancestor::tr[1]/descendant::td//input[@name=\"selected_id\"]";
		String s6=s4+orgName+s5;
		driver.findElement(By.xpath(s6)).click();
		
		
		/*Step 11: Click on the delete button*/
		driver.findElement(By.xpath("//input[@class=\"crmbutton small delete\"]")).click();
		
		/*s12 : Handle the delete alert */
		wlib.alertOk(driver);
		
		/* S13: Verify delete	 */
		driver.findElement(By.name("submit")).click();
		String orgdel = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]")).getText();
		Assert.assertTrue(orgdel.contains("No Organization Found !"));
			
		
		
	}

}
