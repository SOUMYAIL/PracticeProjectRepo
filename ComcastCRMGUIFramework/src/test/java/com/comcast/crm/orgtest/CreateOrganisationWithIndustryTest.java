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

public class CreateOrganisationWithIndustryTest {
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
		ExcelUtility elib = new ExcelUtility();

		String orgName = elib.getDataFromExcel("org", 5, 2) + randInt;

		String type = elib.getDataFromExcel("org", 5, 4);
		String industries = elib.getDataFromExcel("org", 5, 3);

		driver.get(url);
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPageToLoad(driver);

		// step 1: login to app

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
		// cnop.CreateNewOrganisation(orgName);

		cnop.CreateNewOrganisation(orgName, type, industries);

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
		String actualTypeInfo = driver.findElement(By.id("mouseArea_Type")).getText();
		if (actualTypeInfo.equals(type)) {
			System.out.println(type + "is verified PASS");
		} else {
			System.out.println(type + "is not verified FAIL");
		}

		hp.logout();

		driver.quit();

	}

}
