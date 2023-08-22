package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.base.BaseClass;
import com.pages.DemoLoginPage;





public class LoginTestOne extends BaseClass {

	DemoLoginPage demoLoginPage;
	private static ExtentReports extent;
    private static ExtentTest test;
    Logger logger;
  
    
    
    @BeforeSuite
    public void beforeSuite() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(".//test-output/extent-report.html");
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    @BeforeTest
    public void beforeTest() {
        test = extent.createTest("My Test", "Test Description");
    }



	@BeforeMethod
	public void setup() {
		logger = LoggerFactory.getLogger(LoginTestOne.class);
		logger.debug("Launching browser");
		WebDriver driver = launchBrowser();
		logger.debug("Launching url");
		driver.get("https://demoqa.com/login");
		logger.debug("Maximizing window");
		driver.manage().window().maximize();
		demoLoginPage = PageFactory.initElements(driver, DemoLoginPage.class);
		
		
	}

	@Test(priority = -2)
	public void userOne() throws InterruptedException, IOException {
		logger.debug("User one is logging to to the application");
		demoLoginPage.loginIds(getData("Data", 1, 0), getData("Data", 1, 1));

	}
	@Test(priority = -1)
	public void userTwo() throws InterruptedException, IOException {
		logger.debug("User two is logging to to the application");
		demoLoginPage.loginIds(getData("Data", 2, 0), getData("Data", 2, 1));

	}
	@Test(priority=0)
	public void userThree() throws InterruptedException, IOException {
		logger.debug("User three is logging to to the application");
		demoLoginPage.loginIds(getData("Data", 3, 0), getData("Data", 3, 1));	
	}
	@Test(priority = 1)
	public void userFour() throws InterruptedException, IOException {
		logger.debug("User four is logging to to the application");
		demoLoginPage.loginIds(getData("Data", 4, 0), getData("Data", 4, 1));


	}
	@Test(priority = 2)
	public void userFive() throws InterruptedException, IOException {
		logger.debug("User five is logging to to the application");
		demoLoginPage.loginIds(getData("Data", 5, 0), getData("Data", 5, 1));


	}

	@AfterMethod
	public void teardown(ITestResult result) {
		logger.debug("Taking screenshot after test failure");
		
		if (result.getStatus() == ITestResult.FAILURE) {
			// Capture screenshot
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(screenshotFile, new File("screenshots/" + result.getName() + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		driver.quit();
	}
	@AfterTest
    public void afterTest() {
        extent.flush();
    }

    
	
}
