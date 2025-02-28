package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * OrganisationPage class contains elements and business libraries related to
 * the Organisation page functionality.
 * 
 * @author Soumya
 */
public class OrganisationPage {

	// WebElements Declaration using @FindBy annotation (Page Object Model)

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement createOrgLink;

	@FindBy(name = "search_text")
	private WebElement searchForTextField;

	@FindBy(id = "bas_searchfield")
	private WebElement orgDropdown;

	@FindBy(name = "submit")
	private WebElement searchNowLink;

	private WebDriverWait wait;

	/**
	 * Constructor to initialize web elements and explicit wait.
	 * 
	 * @param driver - WebDriver instance
	 */
	public OrganisationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// Getter methods to access private elements

	public WebElement getCreateOrgLink() {
		return createOrgLink;
	}

	public WebElement getSearchForTextField() {
		return searchForTextField;
	}

	public WebElement getOrgDropdown() {
		return orgDropdown;
	}

	public WebElement getSearchNowLink() {
		return searchNowLink;
	}

	// Business Libraries (Reusable Methods)

	/**
	 * Clicks on the 'Create Organization' link after waiting for it to be
	 * clickable.
	 */
	public void clickOnCreateOrgLink() {
		wait.until(ExpectedConditions.elementToBeClickable(createOrgLink));
		createOrgLink.click();
	}

	/**
	 * Enters text into the search field.
	 * 
	 * @param orgName - Name of the organization to search for.
	 */
	public void enterSearchText(String orgName) {
		wait.until(ExpectedConditions.visibilityOf(searchForTextField));
		searchForTextField.clear();
		searchForTextField.sendKeys(orgName);
	}

	/**
	 * Clicks on the 'Search Now' button after entering search text.
	 */
	public void clickSearchNow() {
		wait.until(ExpectedConditions.elementToBeClickable(searchNowLink));
		searchNowLink.click();
	}
}
