package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import utilities.DataUtil;
import java.lang.reflect.Method;
import static reportConfig.ExtentTestManager.startTest;

public class User_02_Login_Nopcommerce extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private RegisterPageObject registerPage;
    private String txtLastName= fakeData.getData().getLastName();
    private String txtFistName= fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord=fakeData.getData().getPassWord();

    @Parameters({"browser","url","envName","ipAddress","portNumber","osName","osVersion"})
    @BeforeClass
    public void initBrowser(@Optional("chrome") String browserName, @Optional("dev") String appUrl, @Optional("local") String envName, @Optional("localhost")String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        log.info("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
        driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
        log.info("Pre-Condition-Step: 02 - Step 01 : Create account");
        homePage= PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver,"Register");
        registerPage= PageGeneratorManager.getRegisterPage(driver);
        registerPage.inputFristNameTextBox(txtFistName);
        registerPage.inputLastNameTextBox(txtLastName);
        registerPage.inputEmailTextBox(txtEmail);
        registerPage.inputPasswordTextBox(txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        registerPage.clickToRegisterButton();
        registerPage.clickToHeaderLinkByText(driver,"Log out");
    }

    @Test
    public void User_Login_01_Email_With_Invalid_Email(Method method) {
        startTest(method.getName(),"User_Login_01_Email_With_Invalid_Email");
        logInfo("User_Login_01_Email_With_Invalid_Email - Step 01: Go to Login page");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver,"Log in");
        loginPage=PageGeneratorManager.getLoginPage(driver);
        logInfo("User_Login_01_Email_With_Invalid_Email - Step 02: Enter value for Email with empty value and check message error(Please enter your email)");
        verifyEquals(loginPage.getMessageErrorTextBox("Email",""),"Please enter your email");
        logInfo("User_Login_01_Email_With_Invalid_Email - Step 03: Go to Login page");
        homePage.clickToHeaderLinkByText(driver,"Log in");
        logInfo("User_Login_01_Email_With_Invalid_Email - Step 04: Enter value for Email with invalid kely@gmai. value and check message error(Wrong email)");
        loginPage = PageGeneratorManager.getLoginPage(driver);
        verifyEquals(loginPage.getMessageErrorTextBox("Email","kely@gmil."),"Wrong email");
    }

    @Test
    public void User_Login_02_Login_With_Email_Not_Register(Method method) {
        startTest(method.getName(),"Login_With_Email_Not_Register");
        logInfo("User_Login_02_Login_With_Email_Not_Register Step 01: Enter value Email value (kely@yahoo.com), Password value (null) and verify message error (Login was unsuccessful. Please correct the errors and try again. No customer account found)");
        loginPage.refreshToPage(driver);
        verifyEquals(loginPage.getMessageErrorLogin("kely@yahoo.com",""),"Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }

    @Test
    public void User_Login_03_Login_With_Email_Registered_And_PassWord_Incorrect(Method method) {
        startTest(method.getName(),"Email_Registered_And_PassWord_Incorrect");
        logInfo("User_Login_03_Login_With_Email_Registered_And_PassWord_Incorrect Step 01: Enter value Email value "+txtEmail+", Password value (44545678) and verify message error (Login was unsuccessful. Please correct the errors and try again. No customer account found)");
        loginPage.refreshToPage(driver);
        verifyEquals(loginPage.getMessageErrorLogin(txtEmail,"45545678"),"Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect");
    }

    @Test
    public void User_Login_04_Login_With_Email_Registered_And_PassWord_IsNull(Method method) {
        startTest(method.getName(),"Login_With_Email_Not_Register");
        logInfo("User_Login_04_Login_With_Email_Registered_And_PassWord_IsNull Step 01: Enter value Email value (kenna.carroll@yahoo.com), Password value (44545678) and verify message error (Login was unsuccessful. Please correct the errors and try again. No customer account found)");
        loginPage.refreshToPage(driver);
        verifyEquals(loginPage.getMessageErrorLogin(txtEmail,""),"Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect");
    }

    @Test
    public void User_Login_05_Login_With_True_Value(Method method) {
        startTest(method.getName(),"Login_With_Email_Not_Register");
        loginPage.refreshToPage(driver);
        logInfo("User_Login_05_Login_With_True_Value Step 01: Enter value Email value "+txtEmail+", Password value "+txtPassWord);
        loginPage.inputEmailTextBox(txtEmail);
        loginPage.inputPasswordTextBox(txtPassWord);
        logInfo("User_Login_05_Login_With_True_Value Step 02: Click to Login button ");
        loginPage.clickToLoginButton(driver);
        logInfo("User_Login_05_Login_With_True_Value Step 03: Verify home page displayed ");
        verifyTrue(loginPage.isHomePageDisplay());
    }

    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }
}
