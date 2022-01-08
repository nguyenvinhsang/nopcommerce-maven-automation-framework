package com.nopcommerce.user;


import commons.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageUIs.user.nopCommerce.BaseUI;
import utilities.DataUtil;


import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;


public class Register_01_Register_Nopcommerce extends BaseTest {
    DataUtil fakeData;
    HomePageObject homePage;
    RegisterPageObject registerPage;
    WebDriver driver;
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord=fakeData.getData().getPassWord();

    @Parameters({"browser","url","envName","ipAddress","portNumber","osName","osVersion"})
    @BeforeClass
    public void initBrowser(@Optional("chrome") String browserName, @Optional("dev") String appUrl, @Optional("local") String envName, @Optional("localhost")String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        log.info("Pre-Condition-Step: 01 Open browser "+browserName+" and navigate to " + "appUrl");
        driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
    }

    @Test
    public void User_Register_01_With_Empty_Value(Method method) {
        startTest(method.getName(),"Register_With_Empty_Value_Send_Key_Enter");
        homePage = PageGeneratorManager.getHomePage(driver);
        logInfo("User_01_Register_To_System - Step 01 : Click To Register Link");
        registerPage= homePage.clickToRegisterLink(driver);
        logInfo("User_01_Register_To_System - Step 02: Verify error message for 'First name' text box displayed when empty value. (First name is required.)");
        verifyEquals(registerPage.checkMessageByTextBoxID("FirstName"),"First name is required.");
        logInfo("User_01_Register_To_System - Step 03: Verify error message for 'Last Name' text box displayed when empty value. (Last Name is required.)");
        verifyEquals(registerPage.checkMessageByTextBoxID("LastName"),"Last name is required.");
        logInfo("User_01_Register_To_System - Step 04: Verify error message for 'Email' text box displayed when empty value. (Email is required.)");
        registerPage.refreshToPage(driver);
        verifyEquals(registerPage.checkMessageByTextBoxID("Email"),"Email is required.");
        logInfo("User_01_Register_To_System - Step 05: Verify error message for 'Password' text box displayed when empty value. (Password is required.)");
        registerPage.refreshToPage(driver);
        verifyEquals(registerPage.checkMessageByTextBoxID("Password"),"Password is required.");
        logInfo("User_01_Register_To_System - Step 05: Verify error message for 'ConfirmPassword' text box displayed when empty value. (Password is required.)");
        verifyEquals(registerPage.checkMessageByTextBoxID("ConfirmPassword"),"Password is required.");
    }

    @Test
    public void User_Register_02_With_Invalid_Email(Method method) {
        startTest(method.getName(),"Register_With_Invalid_Email");
        logInfo("User_01_Register_To_System - Step 01 : Verify error message for 'Email' text box displayed when invalid value (123asd) .(Wrong email)");
        registerPage.refreshToPage(driver);
        verifyEquals(registerPage.checkMessageByTextBoxID("Email","123asd"),"Wrong email");
        logInfo("User_01_Register_To_System - Step 02 : Verify error message for 'Email' text box displayed when invalid value (zxczxczx@zczxc.) .(Wrong email)");
        registerPage.refreshToPage(driver);
        verifyEquals(registerPage.checkMessageByTextBoxID("Email","zxczxczx@zczxc."),"Wrong email");
    }

    @Test
    public void User_Register_03_With_True_Value(Method method) {
        startTest(method.getName(),"Register_With_With_True_Value");
        logInfo("User_01_Register_To_System - Step 02 : Click To Register Link");
        registerPage= homePage.clickToRegisterLink(driver);
        logInfo("User_01_Register_To_System - Step 03 : Click To Mail Radio");
        registerPage.clickToMaleRadio();
        logInfo("User_01_Register_To_System - Step 04 : Enter to Fist Name Text Box witch value sang ");
        registerPage.inputFristNameTextBox("sang");
        logInfo("User_01_Register_To_System - Step 05 : Enter to Last Name Text Box witch value nguyen ");
        registerPage.inputLastNameTextBox("nguyen");
        logInfo("User_01_Register_To_System - Step 06 : Enter to to Email Text Box witch value  "+txtEmail);
        registerPage.inputEmailTextBox(txtEmail);
        logInfo("User_01_Register_To_System - Step 07 : Enter to PassWord Text Box witch value "+txtPassWord);
        registerPage.inputPasswordTextBox(txtPassWord);
        logInfo("User_01_Register_To_System - Step 08 : Enter to Confirm PassWord Text Box witch value "+txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        logInfo("User_01_Register_To_System - Step 09 : Click To Register Button ");
        registerPage.clickToRegisterButton();
        logInfo("User_01_Register_To_System - Step 10 : Verify Success message display ");
        verifyTrue(registerPage.isSuccessMessageDisplayed());
        logInfo("User_01_Register_To_System - Step 11 : Click To Logout link - Home-> Page ");
        homePage= registerPage.clickToLogoutLink(driver);
        logInfo("User_01_Register_To_System - Step 12 : Verify Home Page Slider Displayed");
        verifyTrue(homePage.isHomePageSliderDisplayed());
    }

    @Test
    public void User_Register_04_With_Email_Already_Exist(Method method) {
        startTest(method.getName(),"Register_With_Valid_Value");
        logInfo("User_01_Register_To_System - Step 01 : Click To Register Link");
        homePage.clickToHeaderLinkByText(driver,"Register");
        registerPage=PageGeneratorManager.getRegisterPage(driver);
        logInfo("User_01_Register_To_System - Step 02 : Click To Mail Radio");
        registerPage.clickToMaleRadio();
        logInfo("User_01_Register_To_System - Step 03 : Enter to Fist Name Text Box witch value sang ");
        registerPage.inputFristNameTextBox("sang");
        logInfo("User_01_Register_To_System - Step 04 : Enter to Last Name Text Box witch value nguyen ");
        registerPage.inputLastNameTextBox("nguyen");
        logInfo("User_01_Register_To_System - Step 05 : Input Email already exits with value "+ txtEmail);
        registerPage.inputEmailTextBox(txtEmail);
        logInfo("User_01_Register_To_System - Step 06 : Enter to PassWord Text Box witch value "+txtPassWord);
        registerPage.inputPasswordTextBox(txtPassWord);
        logInfo("User_01_Register_To_System - Step 07 : Enter to Confirm PassWord Text Box witch value "+txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        logInfo("User_01_Register_To_System - Step 08 : Click To Register Button ");
        registerPage.clickToRegisterButton();
        logInfo("User_01_Register_To_System - Step 08 : Verify message email already exit");
        verifyEquals(registerPage.getMessageEmailAlreadyExits(),"The specified email already exists");
    }

    @Test
    public void User_Register_05_With_PassWord_Less_Than_6_Char(Method method) {
        startTest(method.getName(),"Register_With_Valid_Value");
        logInfo("User_01_Register_To_System - Step 01 : Enter to PassWord Text Box witch value (12345) and sen key enter");
        registerPage.refreshToPage(driver);
        registerPage.inputPasswordTextBox("12345");
        registerPage.sendKeyBoardToElementNoClear(driver, BaseUI.DYNAMIC_TEXT_BOX, Keys.ENTER,"Password");
        logInfo("User_01_Register_To_System - Step 02 : Verify message error 'Password must meet the following rules: must have at least 6 characters' pass word already exit");
        verifyTrue(registerPage.isDisplayErrorPasWordLessThan6Char());
        logInfo("User_01_Register_To_System - Step 03 : Enter to PassWord Text Box witch value (12345) and click register button");
        registerPage.refreshToPage(driver);
        registerPage.inputPasswordTextBox("12345");
        registerPage.clickToRegisterButton();
        logInfo("User_01_Register_To_System - Step 04 : Verify message error 'Password must meet the following rules: must have at least 6 characters' pass word already exit");
        verifyTrue(registerPage.isDisplayErrorPasWordLessThan6Char());
    }

    @Test
    public void User_Register_06_With_PassWord_Mismatched_Confirm_PassWord(Method method) {
        startTest(method.getName(),"Register_With_Valid_Value");
        logInfo("User_01_Register_To_System - Step 01 : Enter to Confirm PassWord Text Box witch value (12345Addf) and verify message 'The password and confirmation password do not match.' ");
        registerPage.refreshToPage(driver);
        verifyEquals(registerPage.checkMessageByTextBoxID("ConfirmPassword","12345Addf"),"The password and confirmation password do not match.");
    }


    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
