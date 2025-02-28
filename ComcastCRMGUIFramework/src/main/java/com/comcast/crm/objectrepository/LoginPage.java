package com.comcast.crm.objectrepository;
/**
 * 
 * @author Soumya
 * 
 * Contains Login Page elements and Business Librariries Like Login
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(name = "user_name")
	private WebElement usernameTextField;

	@FindBy(name = "user_password")
	private WebElement passwordTextField;

	@FindBy(id = "submitButton")
	private WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getUsernameTextField() {
		return usernameTextField;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	//run 5: provide action
	/**
	 * login to application based on username password argument
	 * @param username
	 * @param password
	 */
	public void loginToapp(String username , String password) {
		
		
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}
	public void loginToapp(String url,String username , String password) {
		System.out.println("D:"+driver);
		driver.get(url);
		usernameTextField.sendKeys(username);
		passwordTextField.sendKeys(password);
		loginButton.click();
	}

}
