package com.comcast.crm.practicedataprovidertest;

import java.io.FileNotFoundException;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;

public class GetProductTest {
	@Test(dataProviderClass = Datastorage.class, dataProvider = "getData")
	public void getProductInfoTest(String BrandName, String ProductName)
			throws FileNotFoundException, InterruptedException {

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		/*
		 * System.out.
		 * println("Solve the CAPTCHA manually, then press Enter to continue...");
		 * Scanner scanner = new Scanner(System.in); scanner.nextLine(); // Wait for
		 * user confirmation Thread.sleep(2000);
		 */

		Thread.sleep(5000);
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(BrandName, Keys.ENTER);

		Thread.sleep(2000);
		WebElement price = driver.findElement(
				By.xpath("//span[text()='" + ProductName + "']/../../../..//span[@class='a-price-whole']"));
		//// span[text()='Apple iPhone 15 (128 GB) -
		//// Black']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div/a/span/span[1]
		String x = price.getText();
		System.out.println(x);
		driver.quit();
	}

	/*
	 * @DataProvider public Object[][] getData() throws EncryptedDocumentException,
	 * IOException { ExcelUtility elib = new ExcelUtility(); int rowCount =
	 * elib.getRowCount("dataprovider"); System.out.println(rowCount);
	 * 
	 * Object[][] objAr = new Object[rowCount][2]; for (int i = 0; i < rowCount;
	 * i++) { objAr[i][0] = elib.getDataFromExcel("dataprovider", i + 1, 0);
	 * 
	 * objAr[i][1] = elib.getDataFromExcel("dataprovider", i + 1, 1);
	 * 
	 * }
	 * 
	 * return objAr;
	 * 
	 * }
	 */

}
