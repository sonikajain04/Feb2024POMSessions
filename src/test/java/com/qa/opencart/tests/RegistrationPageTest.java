package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

public class RegistrationPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegisterPage();
		
	}
	
	@DataProvider
	public Object[][] userRegTestData(){
		return new Object[][] {
			{"Arti", "Automation","9809999110","arti@12345","yes"},
			{"Ritu", "Automation","9809999330","ritu@12345","no"},
			{"Siri", "Automation","9809998890","siri@12345","yes"}
		};
	}
	
	@DataProvider
	public void userRegTestDataFromSheet() {
	//	ExcelUtil.getTestData(AppConstants.REGISTER_SHEET);
	}
 	

	@Test(dataProvider="userRegTestData")
	public void userRegisterationTest(String firstName,String lastName,String telephone, String Password,String subscribe) {
		Assert.assertTrue(regPage.userRegister(firstName,lastName,StringUtil.getRandomEmailId(),Password,subscribe,AppError.USER_REG_NOT_DONE));
	}

}
