package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.PropertiesUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepository.CreateNewOrganisationPage;
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;
import com.comcast.crm.objectrepository.OrganisationPage;

public class CreateOrganisationWithPhoneNumberTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		PropertiesUtility plib = new PropertiesUtility();
		String browser = plib.getDataFromProperties("browser");
		String url = plib.getDataFromProperties("url");
		String username = plib.getDataFromProperties("username");
		String password = plib.getDataFromProperties("password");

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
		// read org name from excel
		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 5, 2) + randInt;

		String phoneNumber = elib.getDataFromExcel("org", 5, 5);

		driver.get(url);
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPageToLoad(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(username, password);

		// navigate to organisation

		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// click on create organisation image
		OrganisationPage op = new OrganisationPage(driver);
		op.getCreateOrgLink().click();

		// get organisation name data from excel file
		CreateNewOrganisationPage cnop = new CreateNewOrganisationPage(driver);
		cnop.CreateNewOrganisations(orgName, phoneNumber);

		// verify industry and type info Expected result
		String actualPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actualPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + " is verified PASS");
		} else {
			System.out.println(phoneNumber + " is not verified FAIL");
		}

		driver.quit();

	}

}
