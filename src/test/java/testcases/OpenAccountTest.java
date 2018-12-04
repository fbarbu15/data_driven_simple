package testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {


	@Test(dataProviderClass = TestUtil.class, dataProvider="dp")
	public void openAccountTest(String cutomer, String currency) throws InterruptedException {
		click("openAccBtn_XPATH");
		click("custName_XPATH");
		Thread.sleep(3000);



	}

}
