package utilities;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import base.TestBase;

public class TestUtil extends TestBase{
	
	public static String screenshotPath;
	public static String screenshotName;
	public static void captureScreenshot(ITestResult result) throws IOException {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		screenshotPath = System.getProperty("user.dir") + "/target/surefire-reports/html/";
		screenshotName = result.getMethod().getMethodName() + System.currentTimeMillis() + ".jpg";
		FileUtils.copyFile(scrFile, new File(screenshotPath + screenshotName));
		
	}
}