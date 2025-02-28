package com.comcast.crm.objectrepository;
/**
 * 
 * @author Soumya
 * 
 * Contains Create new organisation Page elements and Business Librariries
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganisationPage {
	@FindBy(name = "accountname")
	private WebElement organisationNameTextField;

	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;

	@FindBy(xpath = "//select[@name='industry']")
	private WebElement industryDropdown;

	@FindBy(xpath = "//select[@name='accounttype']")
	private WebElement typeDropdown;

	@FindBy(id = "phone")
	private WebElement phoneNumberTextField;

	public CreateNewOrganisationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getorganisationNameTextField() {
		return organisationNameTextField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}

	public WebElement getTypeDropdown() {
		return typeDropdown;
	}

	public WebElement getPhoneNumberTextField() {
		return phoneNumberTextField;
	}

	public void CreateNewOrganisation(String orgName) {

		organisationNameTextField.sendKeys(orgName);
		saveButton.click();

	}

	public void CreateNewOrganisation(String orgName, String Pindustry) {
		organisationNameTextField.sendKeys(orgName);
		Select sel = new Select(industryDropdown);
		sel.selectByVisibleText(Pindustry);
		saveButton.click();

	}

	public void CreateNewOrganisation(String orgName, String Ptype, String Pindustry) {
		organisationNameTextField.sendKeys(orgName);
		Select sel = new Select(typeDropdown);
		Select sel1 = new Select(industryDropdown);

		sel.selectByVisibleText(Ptype);
		sel1.selectByVisibleText(Pindustry);

		saveButton.click();

	}

	public void CreateNewOrganisations(String orgName, String phoneNumber) {
		organisationNameTextField.sendKeys(orgName);
		
		phoneNumberTextField.sendKeys(phoneNumber);
		saveButton.click();

	}

}
