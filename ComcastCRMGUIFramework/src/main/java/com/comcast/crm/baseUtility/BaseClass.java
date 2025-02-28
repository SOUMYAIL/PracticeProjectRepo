package com.comcast.crm.baseUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepository.CreateNewContactPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

/**
 * BaseClass sets up the test environment and manages setup and teardown
 * processes. Includes browser initialization, login/logout, and DB connection
 * handling.
 * 
 * Groups: SmokeTest, RegressionTest
 * 
 * Author: Soumya
 */
public class BaseClass {

	// Utilities and WebDriver declarations
	public PropertiesUtility proplib = new PropertiesUtility();
	public DatabaseUtility dblib = new DatabaseUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	public CreateNewContactPage cncp;
	public HomePage hp;
	public LoginPage lp;
	
	

	/**
	 * Establishes a database connection before the test suite starts.
	 */
	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configBS() throws Throwable {

		Reporter.log("=== Connecting to DB and Configuring Reports ===", true);
		dblib.getDbConnectionHardcode();

		
	}

	/**
	 * Launches the browser before any tests in the class run.
	 */
	@BeforeClass(groups = { "SmokeTest", "RegressionTest" })
	public void configBC() throws IOException {
		Reporter.log("=== Launching Browser ===", true);

		String browser = proplib.getDataFromProperties("browser");

		if (browser.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();

		}

		sdriver = driver;
		UtilityClassObject .setDriver(driver);

	}

	/**
	 * Logs into the application before each test method.
	 */
	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configBM() throws Throwable {
		Reporter.log("=== Logging into Application ===", true);

		String URL = proplib.getDataFromProperties("url");
		String USERNAME = proplib.getDataFromProperties("username");
		String PASSWORD = proplib.getDataFromProperties("password");

		lp = new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}

	/**
	 * Logs out of the application after each test method.
	 */
	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configAM() {
		Reporter.log("=== Logging out of Application ===", true);
		hp = new HomePage(driver);
		hp.logout();
	}

	/**
	 * Closes the browser after all tests in the class have run.
	 */
	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void configAC() {
		Reporter.log("=== Closing Browser ===", true);
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Closes the database connection after the test suite ends.
	 */
	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configAS() throws SQLException {
		Reporter.log("=== Closing DB Connection and Generating Reports ===", true);
		dblib.closeConnection();

		
	}
}
