package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.TestBase;
import utilities.TestUtil;

public class BankManagerLoginTest extends TestBase {

	@Test(priority = 0)
	public void loginAsBankManagerTest() throws InterruptedException, IOException {
		Thread.sleep(1000);
		verifyEquals("asd", "sadsd");
		Thread.sleep(3000);
		click("bmlBtn_CSS");
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),
				"Login was not successful: ");
		log.debug("Login was successful");
		Reporter.log("Login was successful");
		Assert.fail("FAIL INTENDED");

	}

	@Test(priority = 1, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {

		if (!data.get("runMode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Run mode for data set is N");
		}
		click("addCustBtn_CSS");
		type("firstNameField_CSS", data.get("firstName"));
		type("lastNameField_CSS", data.get("lastName"));
		type("postCodeField_CSS", data.get("postCode"));
		Thread.sleep(2000);
		click("confirmAdd_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")),
				"Expected alert " + data.get("alertText") + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		log.debug("New customer added");
		Reporter.log("New customer added");

	}

}
