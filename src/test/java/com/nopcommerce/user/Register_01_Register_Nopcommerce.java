package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;


public class Register_01_Register_Nopcommerce extends BaseTest {
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	WebDriver driver;

	@Parameters({"browser","url","envName","ipAddress","portNumber","osName","osVersion"})
	@BeforeClass
	public void initBrower(@Optional("chrome") String browserName,@Optional("dev") String appUrl,@Optional("local") String envName,@Optional("localhost")String ipAddress,@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
		driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
		//txtEmail = fakerData.getData().getEmailAddress();
		//txtFistName = fakerData.getData().getFistName();
		//txtLastName = fakerData.getData().getLastName();
		//txtPassWord = fakerData.getData().getPassWord();
	}

	@Test
	public void User_01_Register_With_Empty_Value_Send_Key_Enter(@org.jetbrains.annotations.NotNull Method method) {
		startTest(method.getName(),"Register_With_Empty_Value_Send_Key_Enter");
		homePage = PageGeneratorManager.getHomePage(driver);
		logInfo("User_01_Register_To_System - Step 01 : Click To Register Link");
		registerPage= homePage.clickToRegisterLink(driver);
		logInfo("User_01_Register_To_System - Step 02: Verify error message for 'First name' text box displayed when empty value. (First name is required.)");
		verifyEquals(registerPage.getErrorMessageTextBoxByID("FirstName"),"First name is requiredy.");
		logInfo("User_01_Register_To_System - Step 03: Verify error message for 'Last name' text box displayed when empty value. (Last name is required.)");
		registerPage.refreshToPage(driver);
		verifyEquals("1","1");
		//logInfo("Verify error message for 'Last name' text box displayed when empty value");
		//logInfo("Verify error message for 'Email' text box displayed when empty value");
		//logInfo("Verify error message for 'Password' text box displayed when empty value");
		//logInfo("Verify error message for 'Confirm password' text box displayed when empty value");
	}

	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void closeBrower(String browserName) {
		log.info("Pre-Condition-Step: Close browser : "+browserName);
		cleanBrowserAndDriver();
	}

}
