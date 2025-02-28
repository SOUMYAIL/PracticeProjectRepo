package com.comcast.crm.orgtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.comcast.crm.objectrepository.OrganisationInformationPage;
import com.comcast.crm.objectrepository.OrganisationPage;

public class DeleteOrgTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		// read common data from properties
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
		String orgName = elib.getDataFromExcel("org", 15, 2) + randInt;

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
		cnop.CreateNewOrganisation(orgName, "Education");

		// verify Header msg Expected result

		OrganisationInformationPage ornp = new OrganisationInformationPage(driver);
		String headerInfo = ornp.getHeaderMsg().getText();

		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " is verified PASS");
		} else {
			System.out.println(orgName + " is not verified FAIL");
		}
		
		//go back to organisation page
		hp.getOrgLink().click();
		
		
		
		//search for organisation
		op.getSearchForTextField().sendKeys(orgName);
		WebElement searchDropdown = op.getOrgDropdown();
	 wlib.select(searchDropdown, "Organization Name");
		//in dynamic web table selecet and delete org
	 op.getSearchNowLink().click();
	 
	 
	 
	 
	 driver.findElement(By.xpath("(//a[text()='"+orgName+"'])[2]/../..//a[text()='del']")).click();
	 wlib.switchToAlertAndAccept(driver);
	 
	 System.out.println(orgName+"is deleted successfully");

	}
}
