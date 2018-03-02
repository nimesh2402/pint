package com.tns.automation.pinterest;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.How;
public class LoginPage extends PageFactoryInit {

	
	
	
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Log in')]/..")
	WebElement weStartUpLogin;
	
	
	
	@FindBy(how = How.XPATH, using = ".//*[@id='email']")
	WebElement weLogin;
	@FindBy(how = How.XPATH, using = ".//*[@id='password']")
	WebElement wePassword;
	@FindBy(how = How.XPATH, using = "//form/button/div[contains(.,'Log in')]/..")
	WebElement weLoginButton;
	public LoginPage(WebDriver driver) {
		// Invocation of constructor of other extended class
		super(driver);
	}
	public void Login(String strEmail, String strPassword) throws InterruptedException {
		weStartUpLogin.click();
		new WaitAndWatch(weLogin,10);
		// Sending key value for Email
		weLogin.sendKeys(strEmail);
		// Sending key value for Password
		wePassword.sendKeys(strPassword);
	}
	public PinsPage getLogin() throws InterruptedException {
		// Clicking on login button and confirming page navigation
		weLoginButton.click();
		Thread.sleep(5000);
		return new PinsPage(driver);
	}
}