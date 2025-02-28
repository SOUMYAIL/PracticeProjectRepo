package com.comcast.crm.objectrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Productpage {
	@FindBy(linkText = "Products")
	private WebElement productLink;

	@FindBy(linkText = "Organizations")
	private WebElement orgLink;


}
