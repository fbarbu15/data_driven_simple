package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;

public class BankManagerLoginTest extends TestBase {


	@Test(priority=0)
	public void loginAsBankManager() throws InterruptedException {
		
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		log.debug("Button Bank Manager Login was clicked"); 
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login was not successful: ");
		log.debug("Login was successful");
		Reporter.log("Login was successful");
		Assert.fail("FAIL INTENDED");

	}

	@Test(priority=1, dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText) throws InterruptedException {
		
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstNameField"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastNameField"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postCodeField"))).sendKeys(postCode);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(OR.getProperty("confirmAdd"))).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText), "Expected alert " + alertText + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(3000);
		log.debug("New customer added");
		Reporter.log("New customer added");
		Assert.fail("FAIL INTENDED");

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
