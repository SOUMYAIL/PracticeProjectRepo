package com.comcast.crm.contacttest;


import java.io.IOException;


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
import com.comcast.crm.objectrepository.HomePage;
import com.comcast.crm.objectrepository.LoginPage;

public class CreateContactWithSupportDateTest {
	public static void main(String[] args) throws IOException, InterruptedException {
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
				String lastName = elib.getDataFromExcel("org", 1, 0) + randInt;

		driver.get(url);
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPageToLoad(driver);

		LoginPage lp = new LoginPage(driver);
		lp.loginToapp(username, password);
		// click on create organisation button
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		 
		ContactPage cp =new ContactPage(driver);
		cp.getCreateContLink().click();
		
		

		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameTextField().sendKeys(lastName);

		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate= jlib.getRequiredDateYYYYDDMM(30);

		
        cncp.getStartDate().clear();
        cncp.getStartDate().sendKeys(startDate);
		
		
		cncp.getEndDate().clear();
		cncp.getEndDate().sendKeys(endDate);

		

		cncp.getSaveButton().click();

		Thread.sleep(2000);

		// verify supportdate after 30 days of current date Expected result
		String actualstartDate = cncp.getActualStartDate().getText();
		if (actualstartDate.contains(startDate)) {
			System.out.println(startDate + "is created PASS");
		} else {
			System.out.println(startDate + "is not created FAIL");
		}
		String actualendDate= cncp.getActualendDate().getText();
		if (actualendDate.contains(endDate)) {
			System.out.println(endDate + "is created PASS");
		} else {
			System.out.println(endDate + "is not created FAIL");
		}


		driver.quit();

	}

}
