package org.autcrm.orgtest;

import org.autocrm.genericutils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOrgAndDeleteTest extends BaseClass
{
	@Test
	public void createORgtest() throws Throwable {
		
		/* read test script specific data*/
		String orgName = elib.getExcelData("org", 1, 2)+ "_"+ wlib.getRamDomNum();
		String org_Type = elib.getExcelData("org", 1, 3);
		String org_industry = elib.getExcelData("org", 1, 4);
		
		
		
		
		
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
		
		/*Step 7 : Click and goto list of Organization */
		driver.findElement(By.xpath("//a[text()=\"Organizations\"]")).click();
		
		
		
		
		/*Step 8 : Select the organization name at drop down */
		
		WebElement sb1 = driver.findElement(By.name("search_field"));
		wlib.select(sb1,"Organization Name");
		
		/* Step 9 : Search for the organization created */
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("submit")).click();
		
		/* Step 10: Select all the pages in by clicking org checkbox*/
		String s1="//a[text()=\"";
		String s2="\"]/ancestor::tr[1]/descendant::td//input[@name=\"selected_id\"]";
		String s3=s1+orgName+s2;
		
		driver.findElement(By.xpath(s3)).click();
		
		
		/*Step 11: Click on the delete button*/
		driver.findElement(By.xpath("//input[@class=\"crmbutton small delete\"]")).click();
		
		/*s12 : Handle the delete alert */
		wlib.alertOk(driver);
		
		/* S13: Verify delete	 */
		driver.findElement(By.name("submit")).click();
		String orgdel = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found !')]")).getText();
		Assert.assertTrue(orgdel.contains("No Organization Found !"));
			
		/*step 1 : logout*/
		WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        wlib.moveMouseToElemnet(driver, wb);
		driver.findElement(By.linkText("Sign Out")).click();
		
		/*close browse*/
		driver.close();
		
	}
}
