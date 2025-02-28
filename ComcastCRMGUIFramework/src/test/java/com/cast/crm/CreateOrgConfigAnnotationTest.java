package com.cast.crm;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateOrgConfigAnnotationTest {
	@BeforeSuite
	public void configBS() {
		Reporter.log("execute BS", true);
	}

	@BeforeTest
	public void configBT() {
		Reporter.log("execute BT", true);
	}

	@BeforeClass
	public void configBC() {
		Reporter.log("execute BC", true);

	}

	@BeforeMethod
	public void configBM() {
		Reporter.log("execute BM", true);

	}

	@Test
	public void createOrgTest() {
		Reporter.log("execute OrgTest", true);

	}

	@Test
	public void createOrgWithIndustry() {
		Reporter.log("execute OrgTest with industry", true);

	}

	@AfterMethod
	public void configAM() {
		Reporter.log("execute AM", true);

	}

	@AfterClass
	public void configAC() {
		Reporter.log("execute AC", true);

	}

	@AfterTest
	public void configAT() {
		Reporter.log("execute AT", true);
	}

	@AfterSuite
	public void configAS() {
		Reporter.log("execute AS", true);

	}

}
