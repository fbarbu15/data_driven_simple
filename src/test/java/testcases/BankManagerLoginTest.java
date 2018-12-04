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

	@Test(priority = 0)
	public void loginAsBankManager() throws InterruptedException {

		click("bmlBtn");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login was not successful: ");
		log.debug("Login was successful");
		Reporter.log("Login was successful");
//		Assert.fail("FAIL INTENDED");

	}

	@Test(priority = 1, dataProvider = "getData")
	public void addCustomer(String firstName, String lastName, String postCode, String alertText)
			throws InterruptedException {

		click("addCustBtn");
		type("firstNameField", firstName);
		type("lastNameField", lastName);
		type("postCodeField", postCode);
		Thread.sleep(2000);
		click("confirmAdd");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText),
				"Expected alert " + alertText + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		log.debug("New customer added");
		Reporter.log("New customer added");
//		Assert.fail("FAIL INTENDED");

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
