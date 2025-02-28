package com.comcast.crm.contacttest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.ContactPage;
import com.comcast.crm.objectrepository.CreateNewContactPage;
import com.comcast.crm.objectrepository.CreateNewOrganisationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganisationPage;

public class CreateContactWithOrgTest {
	public static void main(String[] args) throws Throwable {
		// read common data from properties
		PropertiesUtility proplib = new PropertiesUtility();

		String browser = proplib.getDataFromProperties("browser");
		String url = proplib.getDataFromProperties("url");
		String username = proplib.getDataFromProperties("username");
		String password = proplib.getDataFromProperties("password");

		WebDriver driver = null;
		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		// generate the random number
		JavaUtility jlib = new JavaUtility();
		int randInt = jlib.getRandomNumber();
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 12, 2) + randInt;
		String phoneNumber = elib.getDataFromExcel("org", 12, 5);
		String lastName = elib.getDataFromExcel("org", 1, 4) + randInt;

		driver.get(url);
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPageToLoad(driver);

		// step 1: login to app

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(username, password);
		// click on create organisation button
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();

		// get organisation name data from excel file

		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		cnop.CreateNewOrganisations(orgName, phoneNumber);

		// verify Header msg Expected result
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		String HeaderInfo = cncp.getHeaderInfo().getText();

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

		wlib.switchToWindowURL(driver, "module=Accounts&action=Popup");
		Thread.sleep(2000);
		cncp.getSearchTextfield().sendKeys(orgName);
		cncp.getSearchNowButton().click();

		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wlib.switchBackToParentWindowURL(driver, "module=Contacts&action=EditView&return_action");
		cncp.getSaveButton().click();

		// verify org name info Expected result

		String actualOrgNameInfo = cncp.getActualOrgNameInfo().getText();
		if (actualOrgNameInfo.trim().equals(orgName)) {
			System.out.println(orgName + "is displayed PASS");
		} else {
			System.out.println(orgName + "is not displayed FAIL");
		}
		hp.logout();

		driver.quit();

	}

}
