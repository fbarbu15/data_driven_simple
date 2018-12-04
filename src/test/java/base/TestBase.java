package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUpSuite() throws IOException {
		if (driver == null) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/Config.properties");
			config.load(fis);
			log.debug("Config file loaded");

			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.properties");
			OR.load(fis);
			log.debug("OR file loaded");

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver");
				driver = new FirefoxDriver();
				log.debug("Firefox Launched");

			} else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver");
				driver = new ChromeDriver();
				log.debug("Chrome Launched");
			}

			driver.get(config.getProperty("testSiteUrl"));
			log.debug("Navigated to : " + config.getProperty("testSiteUrl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}

		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}
	
	public void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			TestUtil.captureScreenshot();
		}
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(LogStatus.INFO, "Typing in : " + locator + " and entered value as : " + value);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterSuite
	public void tearDownSuite() {
		if (driver != null) {
			driver.quit();
		}
	}

}
