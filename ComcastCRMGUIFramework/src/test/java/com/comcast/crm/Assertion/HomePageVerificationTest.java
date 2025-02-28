package com.comcast.crm.Assertion;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;


public class HomePageVerificationTest {
	@Test
	public void homePageTest(Method mtd) {
		System.out.println(mtd.getName() + "Home Page test starts");
		String expectedTitle = "Home Page";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();

		String actTitle = driver.findElement(By.xpath("//a[@class='hdrLink']")).getText();
		Assert.assertEquals(actTitle, expectedTitle);
		driver.quit();

	}

	@Test
	public void logiHomePageTest(Method mtd) {
		System.out.println(mtd.getName() + "Home Page test starts");
		//String expectedTitle = "Home";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status = driver.findElement(By.xpath("//img[@src='themes/softed/images/Home.PNG']")).isEnabled();
		//Assert.assertEquals(true, status);
		Assert.assertTrue(status);
		System.out.println(mtd.getName()+" Test End");
		driver.quit();

	}

}
