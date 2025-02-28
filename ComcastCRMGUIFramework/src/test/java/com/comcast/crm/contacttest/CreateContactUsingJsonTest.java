package com.comcast.crm.contacttest;



import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.JsonUtility;

import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateContactUsingJsonTest {
	public static void main(String[] args) throws ParseException, Throwable {
		// create object for json file
		JsonUtility jlib = new JsonUtility();
		// create object for excel file
		ExcelUtility elib = new ExcelUtility();
		String browser = jlib.readDataFromJson("browser");
		String url = jlib.readDataFromJson("url");
		String username = jlib.readDataFromJson("username");
		String password = jlib.readDataFromJson("password");

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
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.waitForPageToLoad(driver);

		// generate the random number
		JavaUtility jalib = new JavaUtility();
		int randInt = jalib.getRandomNumber();
		// read lastName name from contact sheet excel
		String lastName = elib.getDataFromExcel("contact", 1, 0) + randInt;

		driver.get(url);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();

		// click on create contacts button

		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// get last name data from excel file
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

		Thread.sleep(2000);

		// verify Header msg Expected result
		String lastNameInfo = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (lastNameInfo.contains(lastName)) {
			System.out.println(lastName + " is verified ==  PASS");
		} else {
			System.out.println(lastName + " is not verifies FAIL");
		}

		driver.quit();

	}

}
