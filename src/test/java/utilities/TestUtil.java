package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		screenshotPath = System.getProperty("user.dir") + "/target/surefire-reports/html/";
		screenshotName = System.currentTimeMillis() + ".jpg";
		FileUtils.copyFile(scrFile, new File(screenshotPath + screenshotName));

	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
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
