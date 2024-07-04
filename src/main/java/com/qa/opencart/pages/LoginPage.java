package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	// 1. Page Objects: By locator
	private By emailId = By.id("input-email");
	private By pwd = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	
	//2. create public const.. of the page
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3. public page actions/methods
	@Step("get login page title")
	public String getLoginPageTitle() {
		
		String title=eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE,TimeUtil.DEFAULT_TIME);
		System.out.println("Login page title : "+title);
		return title;
	}
	@Step("getting the login page URL")
	public String getLoginPageURL() {
		String url=eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
		System.out.println("Login page URL : "+url);
		return url;
	}
	@Step("getting the state of forgot password")
	public boolean checkForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("login to application with username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String password) {
		eleUtil.doSendKeys(emailId, username,TimeUtil.DEFAULT_MEDIUM_TIME);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink,TimeUtil.DEFAULT_MEDIUM_TIME);
		return new RegisterPage(driver);
		
	}
}
