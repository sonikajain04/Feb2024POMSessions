package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.listeners.AnnotationTransformer;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EP 001 - open cart")
@Story("US 101 : Design login")
@Listeners(AnnotationTransformer.class)

public class LoginPageTest extends BaseTest{

	@Description("======= checking login page title ======")
	@Severity(SeverityLevel.MINOR)
	@Owner("Sonika Jain")
	@Feature("login page title feature")
	@Test(priority=1)
	public void loginPageTitleTest() {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}

	@Description("======= checking login page URL ======")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Sonika Jain")
	@Feature("checking login page URL feature")
	@Test(priority=2)
	public void loginPageUrlTest() {
		String actURL=loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.LOGIN_PAGE_FRACTION_URL),AppError.URL_NOT_FOUND);
	}
	
	@Description("======= checking forgot password link ======")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Sonika Jain")
	@Feature("checking forgot password link feature")
	@Test(priority=3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.checkForgotPwdLinkExist(),AppError.ELEMENT_NOT_FOUND);
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("======= checking account page title ======")
	@Test(priority=4)
	public void loginTest() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	
}
