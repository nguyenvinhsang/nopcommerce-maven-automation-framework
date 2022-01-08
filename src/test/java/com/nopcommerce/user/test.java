package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageUIs.user.nopCommerce.BaseUI;
import retryConfig.Retry;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;


public class test extends BaseTest {
	
	HomePageObject homePage;
	RegisterPageObject registerPage;
	WebDriver driver;

	@Parameters({"browser","url","envName","ipAddress","portNumber","osName","osVersion"})
	@BeforeClass
	public void initBrowser(@Optional("chrome") String browserName,@Optional("dev") String appUrl,@Optional("local") String envName,@Optional("localhost")String ipAddress,@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		log.info("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
		driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
	}

	@Test
	public void User_Register_01_With_Empty_Value(Method method) {
		startTest(method.getName(),"Register_With_Empty_Value_Send_Key_Enter");
		homePage = PageGeneratorManager.getHomePage(driver);
		logInfo("User_01_Register_To_System - Step 02: Verify error message for 'First name' text box displayed when empty value. (First name is required.)");
		registerPage= homePage.clickToRegisterLink(driver);
		verifyEquals(registerPage.checkMessageByTextBoxID("FirstName"),"First name is required.");
		registerPage.refreshToPage(driver);
		verifyEquals(registerPage.checkMessageByTextBoxID("FirstName","1245"),null);
	}




	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void closeBrowser(String browserName) {
		log.info("Pre-Condition-Step: Close browser : "+browserName);
		cleanBrowserAndDriver();
	}

}
