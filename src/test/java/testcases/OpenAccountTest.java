package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {

		if (!TestUtil.isTestRunnable("openAccountTest", excel)) {
			throw new SkipException("Skipping the test openAccountTest per excel instruction");
		}

		click("openAccBtn_XPATH");
		select("custName_XPATH", data.get("customer"));
		select("curr_XPATH", data.get("currency"));
		Thread.sleep(2000);
		click("process_XPATH");
		Thread.sleep(5000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertText")),
				"Expected alert " + data.get("alertText") + " but got instead: " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		log.debug("New account opened");
		Reporter.log("New account opened");
		test.log(LogStatus.INFO, "New account opened");

	}

}
