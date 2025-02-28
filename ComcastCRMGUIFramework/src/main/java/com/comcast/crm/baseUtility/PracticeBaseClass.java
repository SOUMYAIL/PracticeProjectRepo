package com.comcast.crm.baseUtility;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * PracticeBaseClass serves as a basic structure for setting up and tearing down
 * tests. It manages database connections, browser setup, login/logout, and
 * cleanup operations.
 * 
 * Author: Soumya
 */
public class PracticeBaseClass {

	/**
	 * Establish a connection to the database and configure the report before the
	 * entire suite starts.
	 */
	@BeforeSuite
	public void configBS() {
		Reporter.log("=== Connecting to Database and Configuring Reports ===", true);
	}

	/**
	 * Launches the browser before executing any class-specific test cases.
	 */
	@BeforeClass
	public void configBC() {
		Reporter.log("=== Opening Browser ===", true);
	}

	/**
	 * Logs into the application before each test method execution.
	 */
	@BeforeMethod
	public void configBM() {
		Reporter.log("=== Logging into Application ===", true);
	}

	/**
	 * Logs out of the application after each test method execution.
	 */
	@AfterMethod
	public void configAM() {
		Reporter.log("=== Logging Out from Application ===", true);
	}

	/**
	 * Closes the browser after all the methods in the current class are executed.
	 */
	@AfterClass
	public void configAC() {
		Reporter.log("=== Closing Browser ===", true);
	}

	/**
	 * Closes the database connection and finalizes the report after the entire
	 * suite ends.
	 */
	@AfterSuite
	public void configAS() {
		Reporter.log("=== Closing Database Connection and Finalizing Report ===", true);
	}
}
