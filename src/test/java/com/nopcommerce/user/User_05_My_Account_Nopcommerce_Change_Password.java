package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_05_My_Account_Nopcommerce_Change_Password extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private HomePageObject homePage;
    private MyAccountPageObject myAccountPage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;

    private String txtLastName = fakeData.getData().getLastName();
    private String txtFistName = fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord = fakeData.getData().getPassWord();

    private String txtNewPassWord = fakeData.getData().getPassWord();



    @Parameters({"browser", "url", "envName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void initBrowser(@Optional("chrome") String browserName, @Optional("dev") String appUrl, @Optional("local") String envName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        log.info("Pre-Condition-Step: 01 Open browser " + browserName + " and navigate to " + "appUrl");
        driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
        log.info("Pre-Condition-Step: 02 - Step 01 : Create account");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver, "Register");
        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.inputFristNameTextBox(txtFistName);
        registerPage.inputLastNameTextBox(txtLastName);
        registerPage.inputEmailTextBox(txtEmail);
        registerPage.inputPasswordTextBox(txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        registerPage.clickToRegisterButton();
        myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
        myAccountPage.clickToHeaderLinkByText(driver,"My account");
        myAccountPage.clickToMenuMyAccountByText(driver,"Change password");
    }

    @Test
    public void User_My_Account_Nopcommerce_Change_Password_01_Empty_Value(Method method) {
        startTest(method.getName(), "Change_Password_01_Empty_Value");
        logInfo("User_My_Account_Nopcommerce_Change_Password_01_Empty_Value - Step 01 : Click to  button 'Change password' with Old password, New password, Confirm password is null");
        myAccountPage.clickToButtonByText(driver, "Change password");
        logInfo("User_My_Account_Nopcommerce_Change_Password_01_Empty_Value - Step 02 : Verify message error for Old password text box with empty value : 'Old password is required.'");
        verifyEquals(myAccountPage.getMessageErrorByLabelName(driver, "Old password"), "Old password is required.");
        logInfo("User_My_Account_Nopcommerce_Change_Password_01_Empty_Value - Step 03 : Verify message error for New password text box with empty value : 'Password is required.'");
        verifyEquals(myAccountPage.getMessageErrorByLabelName(driver, "New password"), "Password is required.");
        logInfo("User_My_Account_Nopcommerce_Change_Password_01_Empty_Value - Step 04 : Verify message error for Confirm password text box with empty value : 'Password is required.'");
        verifyEquals(myAccountPage.getMessageErrorByLabelName(driver, "Confirm password"), "Password is required.");
    }

    @Test
    public void User_My_Account_Nopcommerce_Change_Password_02_PassWord_Invalid(Method method) {
        startTest(method.getName(), "Change_Password_02_PassWord_Invalid");
        logInfo("User_My_Account_Nopcommerce_Change_Password_02_PassWord_Invalid - Step 01 : Enter value (<6 chard )'1111' for New password");
        myAccountPage.inputTextBoxByLabelName(driver,"New password","1111");
        logInfo("User_My_Account_Nopcommerce_Change_Password_02_PassWord_Invalid - Step 02 : Verify message error : 'Password must meet the following rules:\n"+
                "must have at least 6 characters' for New password");
        verifyEquals(myAccountPage.getMessageErrorByLabelName(driver,"New password"),"Password must meet the following rules:\n"+
                "must have at least 6 characters");
    }

    @Test
    public void User_My_Account_Nopcommerce_Change_Password_03_Confirm_PassWord_Invalid(Method method) {
        startTest(method.getName(), "Change_Password_03_Confirm_PassWord_Invalid");
        logInfo("User_My_Account_Nopcommerce_Change_Password_03_Confirm_PassWord_Invalid - Step 01 : Enter value '111111' for New password");
        myAccountPage.inputTextBoxByLabelName(driver,"New password","111111");
        logInfo("User_My_Account_Nopcommerce_Change_Password_03_Confirm_PassWord_Invalid - Step 01 : Enter value (don't match new password) '1111111' for Confirm  password");
        myAccountPage.inputTextBoxByLabelName(driver,"Confirm password","1111111");
        logInfo("User_My_Account_Nopcommerce_Change_Password_03_Confirm_PassWord_Invalid - Step 02 : Verify message error : 'The new password and confirmation password do not match.' for Confirm  password");
        verifyEquals(myAccountPage.getMessageErrorByLabelName(driver,"Confirm password"),"The new password and confirmation password do not match.");
    }


    @Test
    public void User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid(Method method) {
        startTest(method.getName(), "Change_Password_04_Old_PassWord_Invalid");
        logInfo("User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid - Step 01 : Enter value (don't match old password) :"+txtNewPassWord+" for Old password");
        myAccountPage.inputTextBoxByLabelName(driver,"Old password",txtNewPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid - Step 02 : Enter value :"+txtNewPassWord+" for New password");
        myAccountPage.inputTextBoxByLabelName(driver,"New password",txtNewPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid - Step 03 : Enter value :"+txtNewPassWord+" for Confirm  password");
        myAccountPage.inputTextBoxByLabelName(driver,"Confirm password",txtNewPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid - Step 04 : Click To Change password button");
        myAccountPage.clickToButtonByText(driver, "Change password");
        logInfo("User_My_Account_Nopcommerce_Change_Password_04_Old_PassWord_Invalid - Step 05 : Verify message error : 'The new password and confirmation password do not match.' for Confirm  password");
        verifyEquals(myAccountPage.getMessageSummaryError(driver),"Old password doesn't match");
    }

    @Test
    public void User_My_Account_Nopcommerce_Change_Password_05_True_Value(Method method) {
        startTest(method.getName(), "Change_Password_05_True_Value");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 01 : Click to  button 'Change password' with Old password, New password, Confirm password is null");
        myAccountPage.clickToButtonByText(driver, "Change password");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 02 : Enter value (match old password) :"+txtPassWord+" for Old password");
        myAccountPage.inputTextBoxByLabelName(driver,"Old password",txtPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 03 : Enter value :"+txtNewPassWord+" for New password");
        myAccountPage.inputTextBoxByLabelName(driver,"New password",txtNewPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 04 : Verify message error not display for new password");
        verifyTrue(myAccountPage.errorMessageUnDisplayByLabelName(driver,"New password"));
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 05 : Enter value :"+txtNewPassWord+" for Confirm  password");
        myAccountPage.inputTextBoxByLabelName(driver,"Confirm password",txtNewPassWord);
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 06 : Verify message error not display for Confirm password");
        verifyTrue(myAccountPage.errorMessageUnDisplayByLabelName(driver,"Confirm password"));
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 07 : Click To Change password button");
        myAccountPage.clickToButtonByText(driver, "Change password");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 08 : Verify success message 'Password was changed' and close notification ");
        verifyEquals(myAccountPage.successMessage(),"Password was changed");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 09 : Click Log Out link");
        myAccountPage.clickToHeaderLinkByText(driver,"Log out");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 09 : Click Log in link");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver,"Log in");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 09 : Enter information user and log in to system. User Name: "+txtEmail+" New pass word :"+txtNewPassWord);
        loginPage = PageGeneratorManager.getLoginPage(driver);
        loginPage.inputTextBoxByLabelName(driver,"Email",txtEmail);
        loginPage.inputTextBoxByLabelName(driver,"Password",txtNewPassWord);
        loginPage.clickToButtonByText(driver,"Log in");
        logInfo("User_My_Account_Nopcommerce_Change_Password_05_True_Value - Step 10 : Verify log in to system success");
        homePage = PageGeneratorManager.getHomePage(driver);
        verifyTrue(homePage.isHomePageSliderDisplayed());
        ////h2[@class='product-title']/a[text()='Build your own computer']
    }


    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
