
package com.comcast.crm.objectrepository;

/**
 * 
 * @author Soumya
 * 
 * Contains Organisation Information Page elements and Business Librariries
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationInformationPage {
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement actOrgName;

	public OrganisationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getActOrgName() {
		return actOrgName;
	}

}
