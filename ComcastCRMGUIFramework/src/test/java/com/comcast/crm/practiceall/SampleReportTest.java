package com.comcast.crm.practiceall;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest {

	ExtentReports report;

	@BeforeSuite
	public void configBS() {
		// spark report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test SUite Results");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);

		// add environment information
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "WIndows 10");
		report.setSystemInfo("BROWSER", "Chrome");

	}

	@AfterSuite
	public void configAS() {
		report.flush();

	}

	@Test
	public void createContactTest() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");

		TakesScreenshot ts = (TakesScreenshot) driver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);

		ExtentTest test = report.createTest("create contact test");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("ICIC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			test.addScreenCaptureFromBase64String(temp, "ErrorFile");

		}
		driver.close();
		

	}

	/*@Test
	public void createContactTWIthOrgest() {
		ExtentTest test = report.createTest("create contact with org test");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			test.log(Status.FAIL, "contact is not created");

		}

	}

	@Test
	public void createContactWithPhoneNumbereTest() {
		ExtentTest test = report.createTest("create contact with phone number test");
		test.log(Status.INFO, "login to app");
		test.log(Status.INFO, "navigate to contact page");
		test.log(Status.INFO, "create contact");
		if ("HDFC".equals("HDFC")) {
			test.log(Status.PASS, "contact is created");
		} else {
			test.log(Status.FAIL, "contact is not created");

		}
*/
	}

