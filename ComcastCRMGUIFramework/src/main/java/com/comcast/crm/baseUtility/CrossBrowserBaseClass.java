
package com.comcast.crm.baseUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepository.CreateNewContactPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

/**
 * CrossBrowserBaseClass manages test setup and teardown across multiple
 * browsers. Supports cross-browser execution using TestNG parameters.
 * 
 * Author: Soumya
 */
public class CrossBrowserBaseClass {

	// Utility and WebDriver declarations
	public PropertiesUtility proplib = new PropertiesUtility();
	public DatabaseUtility dblib = new DatabaseUtility();
	public ExcelUtility elib = new ExcelUtility();
	public JavaUtility jlib = new JavaUtility();
	public WebDriver driver = null;

	public CreateNewContactPage cncp;
	public HomePage hp;
	public LoginPage lp;

	/**
	 * Establishes a database connection before the test suite starts.
	 */
	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configBS() throws Throwable {
		Reporter.log("=== Connecting to Database and Configuring Reports ===", true);
		dblib.getDbConnectionHardcode();
	}

	/**
	 * Launches the specified browser before tests run using if-else statements.
	 *
	 * @param browser Browser name passed from TestNG XML, defaults to Chrome if not
	 *                provided.
	 */
	@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true, groups = { "SmokeTest", "RegressionTest" })
	public void configBC(@Optional("chrome") String browser) throws IOException {
		Reporter.log("=== Launching Browser: " + browser + " ===", true);

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			Reporter.log("Invalid browser specified. Defaulting to Chrome.", true);
			driver = new ChromeDriver();
		}
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
	 * Logs out from the application after each test method.
	 */
	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configAM() {
		Reporter.log("=== Logging out from Application ===", true);
		hp = new HomePage(driver);
		hp.logout();
	}

	/**
	 * Closes the browser after all tests in the class are executed.
	 */
	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void configAC() {
		Reporter.log("=== Closing Browser ===", true);
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Closes the database connection after the test suite finishes.
	 */
	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configAS() throws SQLException {
		Reporter.log("=== Closing Database Connection and Finalizing Report ===", true);
		dblib.closeConnection();
	}
}
