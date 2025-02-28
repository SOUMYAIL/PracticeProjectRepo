package com.comcast.crm.objectrepository;
/**
 * 
 * @author Soumya
 * 
 * Contains Contact Page elements and Business Librariries
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement createContLink;
	
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreateContLink() {
		return createContLink;
	}

}
