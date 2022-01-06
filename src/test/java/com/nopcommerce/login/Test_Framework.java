package com.nopcommerce.login;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.OrdersPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;

import static reportConfig.ExtentTestManager.startTest;


public class Test_Framework extends BaseTest {
	
	HomePageObject homePage;
	LoginPageObject logigPage;
	RegisterPageObject registerPage;
	WebDriver driver;
	BasePage basePage;
	String projectLocation = System.getProperty("user.dir");
	String txtPassWord, txtEmail;
	SearchPageObject searchPage;
	OrdersPageObject orderspage;
	

	@Parameters({"browser","url","envName","ipAddress","portNumber","osName","osVersion"})
	@BeforeClass
	public void initBrower(@Optional("chrome") String browserName,@Optional("dev") String appUrl,@Optional("local") String envName,@Optional("localhost")String ipAddress,@Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
		startTest("initBrower","Open browser");
		logInfo("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
		driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
		txtEmail = getRanDomEmail();
		txtPassWord = "123456";
	}

	@Test
	public void User_01_Register_To_System(Method method) {
		startTest(method.getName(),"User_01_Register_To_System");
		logInfo("User_01_Register_To_System");
		//createNote("User_01_Register_To_System - Step 02 : Click To Register Link");
		homePage = PageGeneratorManager.getHomePage(driver);
		//createNote("User_01_Register_To_System - Step 02 : Click To Register Link");
		logInfo("User_01_Register_To_System - Step 02 : Click To Register Link");
		registerPage= homePage.clickToRegisterLink(driver);
		//createNote("User_01_Register_To_System - Step 03 : Click To Mail Radio");
		logInfo("User_01_Register_To_System - Step 03 : Click To Mail Radio");
		registerPage.clickToMaleRadio();
		logInfo("User_01_Register_To_System - Step 04 : Enter to Fist Name Text Box witch value sang ");
		registerPage.inputFristNameTextBox("sang");
		logInfo("User_01_Register_To_System - Step 05 : Enter to Last Name Text Box witch value nguyen ");
		registerPage.inputLastNameTextBox("nguyen");
		logInfo("User_01_Register_To_System - Step 06 : Enter to to Email Text Box witch value  "+txtEmail);
		registerPage.inputEmailTextBox(txtEmail);
		logInfo("User_01_Register_To_System - Step 07 : Enter to PassWoed Text Box witch value "+txtPassWord);
		registerPage.inputPasswordTextBox(txtPassWord);
		logInfo("User_01_Register_To_System - Step 08 : Enter to Comfirm PassWoed Text Box witch value "+txtPassWord);
		registerPage.inputConfirmPasswordTextBox(txtPassWord);
		logInfo("User_01_Register_To_System - Step 09 : Click To Register Button ");
		registerPage.clickToRegisterButton();
		logInfo("User_01_Register_To_System - Step 10 : Verify Success message display ");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		logInfo("User_01_Register_To_System - Step 11 : Click To Logout link - Home-> Page ");
		homePage= registerPage.clickToLogoutLink(driver);
		//createNote("User_01_Register_To_System - Step 12 : Verify Home Page Slider Displayed");
		logInfo("User_01_Register_To_System - Step 12 : Verify Home Page Slider Displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void User_02_Login_To_System(Method method) throws InterruptedException {
		startTest(method.getName(),"User_02_Login_To_System");
		logInfo("User_02_Login_To_System - Step 1 : Click To Login Link");
		logigPage=homePage.clickToLoginLink(driver);
		logInfo("User_02_Login_To_System - Step 2 : Enter to Email Text Box wuth value "+txtEmail);
		logigPage.inputEmailTextBox(txtEmail);
		logInfo("User_02_Login_To_System - Step 3 : Enter to PassWord Text Box wuth value "+txtPassWord);
		logigPage.inputPasswordTextBox(txtPassWord);
		logInfo("User_02_Login_To_System - Step 4 : Click To Login Button");
		homePage= logigPage.clickToLoginButton(driver);
		Thread.sleep(3000);
		logInfo("User_02_Login_To_System - Step 5 :Verify Home Page login Displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
	}
	

	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void closeBrower(String browserName) {
		startTest("closeBrower","Open browser");
		logInfo("Pre-Condition-Step: Close browser : "+browserName);
		cleanBrowserAndDriver();
	}

	public String getRanDomEmail() {
		Random ran = new Random();
		return "testing" + ran.nextInt(999999) + "@live.com";
	}

}
