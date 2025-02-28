package com.comcast.crm.objectrepository;

/**
 * 
 * @author Soumya
 * 
 * Contains Home Page elements and Business Librariries
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(linkText = "Products")
	private WebElement productLink;

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;

	@FindBy(linkText = "Contacts")
	private WebElement contactLink;

	@FindBy(linkText = "Campaigns")
	private WebElement campaignLink;

	@FindBy(linkText = "More")
	private WebElement moreLink;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutLink;

	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminLogo;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public WebElement getSignoutLink() {
		return signoutLink;
	}

	public WebElement getOrgLink() {
		return orgLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getCampaignLink() {
		return campaignLink;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getMoreLink() {
		return moreLink;
	}

	public WebElement getAdminLogo() {
		return adminLogo;
	}

	public void naviagateToCampaignLink() {
		Actions act = new Actions(driver);
		act.moveToElement(moreLink).perform();
		campaignLink.click();
	}

	public void logout() {
		Actions act = new Actions(driver);
		act.moveToElement(adminLogo).perform();
		signoutLink.click();

	}

}
