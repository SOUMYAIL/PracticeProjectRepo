package com.comcast.crm.GroupTestcaseTest;

import java.io.IOException;

import org.openqa.selenium.By;

import org.testng.annotations.Test;

import com.comcast.crm.baseUtility.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.CreateNewContactPage;
import com.comcast.crm.objectrepository.CreateNewOrganisationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganisationPage;

public class CreateContactTest extends BaseClass {
	@Test(groups = { "SmokeTest", "RegressionTest" })
	public void createContactTest() throws IOException, InterruptedException {

		// generate the random number
		JavaUtility jlib = new JavaUtility();
		int randInt = jlib.getRandomNumber();
		// read lastName name from contact sheet excel
		String lastName = elib.getDataFromExcel("contact", 1, 0) + randInt;
		// lp=new LoginPage(driver);

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContLink().click();

		// get last name data from excel file
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(lastName);
		cncp.getSaveButton().click();

		Thread.sleep(2000);

		// verify Header msg Expected result

		String lastNameInfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (lastNameInfo.contains(lastName)) {
			System.out.println(lastName + " is verified ==  PASS");
		} else {
			System.out.println(lastName + " is not verifies FAIL");
		}

	}

	@Test(groups = "RegressionTest")
	public void createContactWithOrgTest() throws Throwable {

		int randInt = jlib.getRandomNumber();
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 12, 2) + randInt;
		String phoneNumber = elib.getDataFromExcel("org", 12, 5);
		String lastName = elib.getDataFromExcel("org", 1, 4) + randInt;

		// click on create organisation button
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();

		// get organisation name data from excel file

		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		cnop.CreateNewOrganisations(orgName, phoneNumber);
		Thread.sleep(2000);
		// verify Header msg Expected result
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		String HeaderInfo = cncp.getHeaderInfo().getText();
		// System.out.println(HeaderInfo);
		Thread.sleep(2000);

		if (HeaderInfo.contains(orgName)) {
			System.out.println(orgName + "is created PASS");
		} else {
			System.out.println(orgName + "is not created FAIL");
		}

		// click on create contacts button

		hp.getContactLink().click();
		ContactPage cp = new ContactPage(driver);
		cp.getCreateContLink().click();

		cncp.getLastNameTextField().sendKeys(lastName);
		cncp.getImgButton().click();
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.switchToWindowURL(driver, "module=Accounts&action=Popup");
		Thread.sleep(2000);
		cncp.getSearchTextfield().sendKeys(orgName);
		cncp.getSearchNowButton().click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wlib.switchBackToParentWindowURL(driver, "module=Contacts&action=EditView&return_action");
		cncp.getSaveButton().click();

		// verify org name info Expected result
		Thread.sleep(2000);
		String actualOrgNameInfo = cncp.getActualOrgNameInfo().getText();
		if (actualOrgNameInfo.trim().equals(orgName)) {
			System.out.println(orgName + "is displayed PASS");
		} else {
			System.out.println(orgName + "is not displayed FAIL");
		}

	}

	@Test(groups = "RegressionTest")
	public void createContactWithSuuportDateTest() throws Throwable {

		int randInt = jlib.getRandomNumber();
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();
		String lastName = elib.getDataFromExcel("org", 1, 0) + randInt;

		lp = new LoginPage(driver);

		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		ContactPage cp = new ContactPage(driver);
		cp.getCreateContLink().click();

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(lastName);

		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDateYYYYDDMM(30);

		cncp.getStartDate().clear();
		cncp.getStartDate().sendKeys(startDate);

		cncp.getEndDate().clear();
		cncp.getEndDate().sendKeys(endDate);

		cncp.getSaveButton().click();

		Thread.sleep(2000);

		String actualstartDate = cncp.getActualStartDate().getText();
		if (actualstartDate.contains(startDate)) {
			System.out.println(startDate + "is created PASS");
		} else {
			System.out.println(startDate + "is not created FAIL");
		}
		Thread.sleep(2000);
		String actualendDate = cncp.getActualendDate().getText();
		if (actualendDate.contains(endDate)) {
			System.out.println(endDate + "is created PASS");
		} else {
			System.out.println(endDate + "is not created FAIL");
		}

	}
}
