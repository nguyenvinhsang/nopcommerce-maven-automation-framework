package com.nopcommerce.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
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

public class Login_15_Close_Browser_Grid extends BaseTest {
	
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
		log.info("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
		driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
		txtEmail = getRanDomEmail();
		txtPassWord = "123456";
	}

	@Test
	public void User_01_Register_To_System() {
		homePage = PageGeneratorManager.getHomePage(driver);
		log.info("User_01_Register_To_System - Step 02 : Click To Register Link");
		registerPage= homePage.clickToRegisterLink(driver);
		log.info("User_01_Register_To_System - Step 03 : Click To Mail Radio");
		registerPage.clickToMaleRadio();
		log.info("User_01_Register_To_System - Step 04 : Enter to Fist Name Text Box witch value sang ");
		registerPage.inputFristNameTextBox("sang");
		log.info("User_01_Register_To_System - Step 05 : Enter to Last Name Text Box witch value nguyen ");
		registerPage.inputLastNameTextBox("nguyen");
		log.info("User_01_Register_To_System - Step 06 : Enter to to Email Text Box witch value  "+txtEmail);
		registerPage.inputEmailTextBox(txtEmail);
		log.info("User_01_Register_To_System - Step 07 : Enter to PassWoed Text Box witch value "+txtPassWord);
		registerPage.inputPasswordTextBox(txtPassWord);
		log.info("User_01_Register_To_System - Step 08 : Enter to Comfirm PassWoed Text Box witch value "+txtPassWord);
		registerPage.inputConfirmPasswordTextBox(txtPassWord);
		log.info("User_01_Register_To_System - Step 09 : Click To Register Button ");
		registerPage.clickToRegisterButton();
		log.info("User_01_Register_To_System - Step 10 : Verify Success message display ");
		verifyTrue(registerPage.isSuccessMessageDisplayed());
		log.info("User_01_Register_To_System - Step 11 : Click To Logout link - Home-> Page ");
		homePage= registerPage.clickToLogoutLink(driver);
		log.info("User_01_Register_To_System - Step 12 : Verify Home Page Slider Displayed");
		verifyFalse(homePage.isHomePageSliderDisplayed());
	}

	@Test
	public void User_02_Login_To_System() {
		log.info("User_02_Login_To_System - Step 1 : Click To Login Link");
		logigPage=homePage.clickToLoginLink(driver);
		log.info("User_02_Login_To_System - Step 2 : Enter to Email Text Box wuth value "+txtEmail);
		logigPage.inputEmailTextBox(txtEmail);
		log.info("User_02_Login_To_System - Step 3 : Enter to PassWord Text Box wuth value "+txtPassWord);
		logigPage.inputPasswordTextBox(txtPassWord);
		log.info("User_02_Login_To_System - Step 4 : Click To Login Button");
		homePage= logigPage.clickToLoginButton(driver);
		log.info("User_02_Login_To_System - Step 5 :Verify Home Page login Displayed");
		verifyTrue(homePage.isHomePageSliderDisplayed());
		
	}
	

	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void closeBrower(String browserName) {
		log.info("Pre-Condition-Step: Close browser : "+browserName);
		cleanBrowserAndDriver();
	}

	public String getRanDomEmail() {
		Random ran = new Random();
		return "testing" + ran.nextInt(999999) + "@live.com";
	}

}
