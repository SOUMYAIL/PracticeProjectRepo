package com.comcast.crm.objectrepository;
/**
 * 
 * @author Soumya
 * 
 * Contains Create New Contact Page elements and Business Librariries 
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactPage {
	WebDriver driver;
	@FindBy(name = "lastname")
	private WebElement lastNameTextField;

	@FindBy(name = "accountname")
	private WebElement organisationNameTextField;
	
	@FindBy(name = "accountname")
	private WebElement organisationNameTextFieldButton;

	@FindBy(id = "phone")
	private WebElement phoneNumberTextField;

	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement imgButton;

	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;

	@FindBy(id = "search_txt")
	private WebElement searchTextfield;

	@FindBy(name = "search")
	private WebElement searchNowButton;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement actualOrgNameInfo;

	@FindBy(xpath = "//span[contains(@class, 'dvHeaderText')]")
	private WebElement headerInfo;

	@FindBy(className = "dvHeaderText")
	private WebElement HeaderText;

	@FindBy(id = "dtlview_Last Name")
	private WebElement lastNameInfo;

	@FindBy(name = "support_start_date")
	private WebElement startDate;

	@FindBy(name = "support_end_date")
	private WebElement endDate;

	@FindBy(id = "mouseArea_Support Start Date")
	private WebElement actualStartDate;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement actualendDate;

	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public WebElement getLastNameInfo() {
		return lastNameInfo;
	}

	public WebElement getHeaderText() {
		return HeaderText;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}

	public WebElement getImgButton() {
		return imgButton;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrganisationNameTextField() {
		return organisationNameTextField;
	}

	public WebElement getPhoneNumberTextField() {
		return phoneNumberTextField;
	}

	public WebElement getSearchTextfield() {
		return searchTextfield;
	}

	public WebElement getSearchNowButton() {
		return searchNowButton;
	}

	public WebElement getActualOrgNameInfo() {
		return actualOrgNameInfo;
	}

	public WebElement getHeaderInfo() {
		return headerInfo;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getActualStartDate() {
		return actualStartDate;
	}

	public WebElement getActualendDate() {
		return actualendDate;
	}

	public void CreateNewContact(String lastName, WebDriver driver, String partialTitle) {

		lastNameTextField.sendKeys(lastName);
		imgButton.click();

		WebDriverUtility wlib = new WebDriverUtility();
		wlib.switchToWindowURL(driver, partialTitle);
		saveButton.click();

	}

	public void CreateNewContact(String lastName, String orgName, String phoneNumber) {
		phoneNumberTextField.sendKeys(phoneNumber);
		organisationNameTextField.sendKeys(orgName);
		lastNameTextField.sendKeys(lastName);
		saveButton.click();

	}

}
