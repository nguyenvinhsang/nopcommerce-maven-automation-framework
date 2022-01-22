package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_03_My_Account_Nopcommerce_Customer_Info extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private HomePageObject homePage;
    private MyAccountPageObject myAccountPageObject;
    private RegisterPageObject registerPage;
    private String txtLastName= fakeData.getData().getLastName();
    private String txtFistName= fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord=fakeData.getData().getPassWord();


    private String txtLastNameEdit= fakeData.getData().getLastName();
    private String txtFistNameEdit= fakeData.getData().getFistName();
    private String txtEmailEdit = fakeData.getData().getEmailAddress();
    private String txtCompanyName = fakeData.getData().getCompanyName();

    private String txtGender="Female";
    private String txtBirthDay ="5";
    private String txtBirthMonth ="10";
    private String txtBirthYear="1999";


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
    }

    @Test
    public void User_My_Account_Customer_Info_01_Update_Information(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info");
        myAccountPageObject = PageGeneratorManager.getMyAccountPage(driver);
        myAccountPageObject.clickToHeaderLinkByText(driver,"My account");
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 01 : Click to radio button :"+txtGender);
        myAccountPageObject.clickToRadioButtonByName(txtGender);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 02 : Enter value ("+txtFistNameEdit+") for fist name text box");
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name",txtFistNameEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 03 : Enter value ("+txtLastNameEdit+") for last name text box");
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name",txtLastNameEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 04 : Enter value Day:("+txtBirthDay+"Month: "+txtBirthMonth+"Year: "+txtBirthYear+") for date of birth: text box");
        myAccountPageObject.inputDataOfBirth(txtBirthDay,txtBirthMonth,txtBirthYear);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 05 : Enter value ("+txtEmailEdit+") for email text box");
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email",txtEmailEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 06 : Enter value ("+txtCompanyName+") for company name text box");
        myAccountPageObject.inputTextBoxByLabelName(driver,"Company name",txtCompanyName);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 07 : Click to Save Button");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 08 : Verify radio button is select :"+txtGender);
        verifyTrue(myAccountPageObject.isSelectRadioButtonByName(txtGender));
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 09 : Verify value for fist name text box :"+txtFistNameEdit);
        verifyEquals(myAccountPageObject.getTextBoxValueByLabelName("First name"),txtFistNameEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 10 : Verify value for last name text box :"+txtLastNameEdit);
        verifyEquals(myAccountPageObject.getTextBoxValueByLabelName("Last name"),txtLastNameEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 11 : Verify value for Date of birth value : Day:("+txtBirthDay+"Month: "+txtBirthMonth+"Year: "+txtBirthYear+")");
        verifyTrue(myAccountPageObject.checkDateOfBirth(txtBirthDay,txtBirthMonth,txtBirthYear));
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 12 : Verify value for email name text box :"+txtEmailEdit);
        verifyEquals(myAccountPageObject.getTextBoxValueByLabelName("Email"),txtEmailEdit);
        logInfo("User_My_Account_01_Update_Information_Customer_Info - Step 13 : Verify value for company name text box :"+txtCompanyName);
        verifyEquals(myAccountPageObject.getTextBoxValueByLabelName("Company name"),txtCompanyName);
    }

    @Test
    public void User_My_Account_Customer_Info_02_Update_Information_Empty_Value_Click_Save(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Empty_Value_Click_Save");
        logInfo("User_My_Account_02_Update_Information_Customer_Info_Empty_Value_Click_Save - Step 01 : Verify message error for Fist Name text box with empty value : 'First name is required.'");
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name","");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"First name"),"First name is required.");
        logInfo("User_My_Account_02_Update_Information_Customer_Info_Empty_Value - Step 02 : Verify message error for Last Name text box with empty value : 'Last name is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name","");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Last name"),"Last name is required.");
        logInfo("User_My_Account_02_Update_Information_Customer_Info_Empty_Value - Step 02 : Verify message error for Fist Name text box with empty value : 'Email is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Email is required.");
    }


    @Test
    public void User_My_Account_Customer_Info_03_Update_Information_Empty_Value_Enter_Key(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Empty_Value_Enter_Key");
        logInfo("User_My_Account_03_Update_Information_Customer_Info_Empty_Value_Enter_Key - Step 01 : Verify message error for Fist Name text box with empty value : 'First name is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name","");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"First name", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"First name"),"First name is required.");
        logInfo("User_My_Account_03_Update_Information_Customer_Info_Empty_Value_Enter - Step 02 : Verify message error for Fist Name text box with empty value : 'Last name is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name","");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Last name", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Last name"),"Last name is required.");
        logInfo("User_My_Account_03_Update_Information_Customer_Info_Empty_Value_Enter - Step 03 : Verify message error for Fist Name text box with empty value : 'Email is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Email", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Email is required.");
    }

    @Test
    public void User_My_Account_Customer_Info_04_Update_Information_Email_Invalid_Click_Save(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Email_Invalid_Click_Save");
        logInfo("User_My_Account_04_Update_Information_Customer_Info_Email_Invalid_Click_Save - Step 01 : Verify message error for Email text box with invalid Email : 'Wrong email'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","dfsfs@affs.");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Wrong email");
    }

    @Test
    public void User_My_Account_Customer_Info_05_Update_Information_Email_Invalid_Enter_Key(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Email_Invalid_Enter_Key");
        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 01 : Verify message error for Email text box with invalid Email : 'Wrong email'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","dfsfs@affs.");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Email", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Wrong email");
    }


    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }
}
