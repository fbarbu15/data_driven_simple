package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.openqa.selenium.By;

import base.TestBase;

public class BankManagerLoginTest extends TestBase {


	@Test(priority=0)
	public void loginAsBankManager() throws InterruptedException {

		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		log.debug("Button Bank Manager Login was clicked"); 
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login was not successful: ");
		log.debug("Login was successful");  

	}

	@Test(priority=1, dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode) throws InterruptedException {
		
		log.debug("Test begin"); 
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(postCode);
		log.debug("Test ended"); 

	}

	@DataProvider
	public Object[][] getData() {

		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {
			for (int colNum = 0; colNum < cols; colNum++) {
				{
					data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
				}
			}
		}
		return data;
	}

}
