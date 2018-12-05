package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(String cutomer, String currency, String alertText) throws InterruptedException {
		click("openAccBtn_XPATH");
		select("custName_XPATH", cutomer);
		select("curr_XPATH", currency);
		Thread.sleep(2000);
		click("process_XPATH");
		Thread.sleep(5000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText),
				"Expected alert " + alertText + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		log.debug("New account opened");
		Reporter.log("New account opened");
		test.log(LogStatus.INFO, "New account opened");

	}

}
