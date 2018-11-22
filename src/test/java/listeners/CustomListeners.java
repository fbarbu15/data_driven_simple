package listeners;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.TestUtil;

public class CustomListeners implements ITestListener {
	public static Logger log = Logger.getLogger("devpinoyLogger");

	public void onTestStart(ITestResult result) {
		log.debug("Starting test <<<<<<<<<<<<<<< " + result.getMethod().getMethodName() + " >>>>>>>>>>>>>>>");

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {

		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("Capturing screenshot");
		try {
			TestUtil.captureScreenshot(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("<a href="+TestUtil.screenshotPath + TestUtil.screenshotName+">Screenshot</a>");

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
