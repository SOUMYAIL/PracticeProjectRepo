package com.comcast.crm.practicedataprovidertest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String BrandName, String ProductName)
			throws FileNotFoundException, InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");

		driver.findElement(By.name("q")).sendKeys(BrandName, Keys.ENTER);

		Thread.sleep(3000);
		String price = driver.findElement(By.xpath("(//div[text()='" + ProductName + "']/../..//div/div/div/div)[1]"))
				.getText();

		System.out.println(price);
		driver.quit();
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		ExcelUtility elib = new ExcelUtility();
		int rowCount = elib.getRowCount("dataprovider");
		System.out.println(rowCount);

		Object[][] objAr = new Object[rowCount][2];
		for (int i = 0; i < rowCount; i++) {
			objAr[i][0] = elib.getDataFromExcel("dataprovider", i + 1, 0);

			objAr[i][1] = elib.getDataFromExcel("dataprovider", i + 1, 1);

		}

		return objAr;

	}

}
