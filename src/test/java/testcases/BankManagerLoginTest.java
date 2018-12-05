package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.io.IOException;
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

	}

	@Test(priority = 1, dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(String firstName, String lastName, String postCode, String alertText)
			throws InterruptedException {

		click("addCustBtn_CSS");
		type("firstNameField_CSS", firstName);
		type("lastNameField_CSS", lastName);
		type("postCodeField_CSS", postCode);
		Thread.sleep(2000);
		click("confirmAdd_CSS");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText),
				"Expected alert " + alertText + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		log.debug("New customer added");
		Reporter.log("New customer added");
		Assert.fail("FAIL INTENDED");

	}

}
