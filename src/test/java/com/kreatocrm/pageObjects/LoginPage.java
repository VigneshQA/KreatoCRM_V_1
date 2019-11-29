package com.kreatocrm.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	/*	UserName Text box	*/
	@FindBy(name="Login$txtUserName")						// Locating UserName text box
	@CacheLookup
	WebElement txtUserName;									// Assigning located UserName text box to txtUserName WebElement
	
	/*	Password Text box	*/	
	@FindBy(name="Login$txtPassword")
	@CacheLookup
	WebElement txtPassword;
	
	/*	Login Button	*/
	@FindBy(name="Login$btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	/* User Info Button	*/
	@FindBy(id="dvmasteruserDetails")
	@CacheLookup
	WebElement btnUserInfo;
	
	/* Logout Button	*/
	@FindBy(id="ctl00_lnkLogOut")
	@CacheLookup
	WebElement btnLogout;
	
	/* Multi Login Warning Popup OK button	*/
	@FindBy(xpath="//span[text()='Ok']")
	@CacheLookup
	public	WebElement btnMLWOk;
	
	/* Multi Login Warning Popup Cancel button	*/
	@FindBy(xpath="//span[text()='Cancel']")
	@CacheLookup
	WebElement btnMLWCancel;
	
		
	public void setUserName(String username){txtUserName.sendKeys(username);}
	public void setPassword(String password){txtPassword.sendKeys(password);}
	public void clickLogin(){btnLogin.click();}
	public void clickUserInfo() {btnUserInfo.click();}
	public void clickLogout() {btnLogout.click();}
	public void clickMultiLoginOk() {btnMLWOk.click();}
	public void clickMultiLoginCancel() {btnMLWCancel.click();}
	
}
