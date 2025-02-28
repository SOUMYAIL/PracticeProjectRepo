package com.comcast.crm.practiceall;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchToChildWIndow {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.shoppersstack.com/products_page/15");
		Thread.sleep(15000);
		driver.findElement(By.xpath("//button[@name='compare']")).click();
		String parentId = driver.getWindowHandle();
		Set<String> windowids = driver.getWindowHandles();
		for(String id:windowids) {
			if(!id.equals(parentId)) {
				driver.switchTo().window(id);
				break;
			}
			
		}
		Point p = driver.manage().window().getPosition();
		System.out.println(p.getX() + "Y" + p.getY());
		driver.switchTo().window(parentId);
		System.out.println(driver.getTitle());
		driver.quit();
		
		
		
	}

}
