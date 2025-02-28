package com.comcast.crm.practiceall;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ClinicIn {
	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.clinique.in/");
		Actions act = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//a[@aria-la9bel='View Skincare menu']"));
		act.moveToElement(element).perform();
		List<WebElement> elements = driver
				.findElements(By.xpath("(//div[@class='gnav-desktop-sub-nav-content-column space-y-4'])[9]"));
		Thread.sleep(2000);

		for (int i = 1; i < elements.size(); i++) {
			System.out.println(elements.get(i).getText());
		}

		/*
		 * for (WebElement ele : elements) { System.out.println(ele.getText()); }
		 */

	}
}
