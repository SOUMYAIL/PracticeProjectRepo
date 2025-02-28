package com.comcast.crm.GroupTestcaseTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import org.openqa.selenium.By;

import org.testng.annotations.Test;

import com.comcast.crm.baseUtility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;

import com.comcast.crm.objectrepository.CreateNewOrganisationPage;
import com.comcast.crm.objectrepository.HomePage;

import com.comcast.crm.objectrepository.OrganisationInformationPage;
import com.comcast.crm.objectrepository.OrganisationPage;

public class CreateOrganisationTest extends BaseClass {
	@Test(groups="SmokeTest")
	public void createOrgTest() throws IOException, InterruptedException {

		int randInt = jlib.getRandomNumber();
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();
		String orgName = elib.getDataFromExcel("org", 1, 2) + randInt;

		// navigate to organisation

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		Thread.sleep(2000);
		// click on create organisation image
		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();

		// get organisation name data from excel file
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		// cnop.CreateNewOrganisation(orgName);
		Thread.sleep(2000);
		cnop.CreateNewOrganisation(orgName, "Education");
		Thread.sleep(2000);

		// verify Header msg Expected result

		OrganisationInformationPage ornp = new OrganisationInformationPage(driver);
		String headerInfo = ornp.getHeaderMsg().getText();
		Thread.sleep(2000);
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is verified PASS");
		} else {
			System.out.println(orgName + " is not verified FAIL");
		}

		// verify org name info Expected result
		String actualOrgNameInfo = ornp.getActOrgName().getText();
		Thread.sleep(2000);
		if (actualOrgNameInfo.contains(orgName)) {
			System.out.println(orgName + " is displayed PASS");
		} else {
			System.out.println(orgName + " is not displayed FAIL");
		}

	}

	@Test(groups="RegressionTest")
	public void createOrgWIthIndustry() throws InterruptedException, IOException {

		int randInt = jlib.getRandomNumber();
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 5, 2) + randInt;

		String type = elib.getDataFromExcel("org", 5, 4);
		String industries = elib.getDataFromExcel("org", 5, 3);

		// navigate to organisation
		Thread.sleep(2000);
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation image
		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();
		Thread.sleep(2000);
		// get organisation name data from excel file
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		// cnop.CreateNewOrganisation(orgName);

		cnop.CreateNewOrganisation(orgName, type, industries);
		Thread.sleep(2000);
		// verify Header msg Expected result

		//OrganisationInformationPage ornp = new OrganisationInformationPage(driver);
		//String headerInfo = ornp.getHeaderMsg().getText();

		Thread.sleep(2000);

		// verify industry and type info Expected result
		String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actualIndustry.equals(industries)) {
			System.out.println(industries + "is verified PASS");
		} else {
			System.out.println(industries + "is not verified FAIL");
		}
		Thread.sleep(2000);
		String actualTypeInfo = driver.findElement(By.id("mouseArea_Type")).getText();
		if (actualTypeInfo.equals(type)) {
			System.out.println(type + "is verified PASS");
		} else {
			System.out.println(type + "is not verified FAIL");
		}

	}

	@Test(groups="RegressionTest")
	public void createOrgwithPhoneNumber() throws EncryptedDocumentException, IOException, InterruptedException {

		int randInt = jlib.getRandomNumber();

		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 5, 2) + randInt;

		String phoneNumber = elib.getDataFromExcel("org", 5, 5);

		// navigate to organisation

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		Thread.sleep(2000);
		// click on create organisation image
		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();

		// get organisation name data from excel file
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		cnop.CreateNewOrganisations(orgName, phoneNumber);
		Thread.sleep(2000);
		// verify industry and type info Expected result
		String actualPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actualPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is verified PASS");
		} else {
			System.out.println(phoneNumber + " is not verified FAIL");
		}
	}

}
