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

public class User_04_My_Account_Nopcommerce_Addresses extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private HomePageObject homePage;
    private MyAccountPageObject myAccountPageObject;
    private RegisterPageObject registerPage;
    private String txtLastName = fakeData.getData().getLastName();
    private String txtFistName = fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord = fakeData.getData().getPassWord();


    private String txtLastNameNew = fakeData.getData().getLastName();
    private String txtFistNameNew = fakeData.getData().getFistName();
    private String txtEmailNew = fakeData.getData().getEmailAddress();
    private String txtCountryNew = "Canada";
    private String txtState ="Quebec";
    private String txtCityNameNew = fakeData.getData().getCityName();
    private String txtStreetAddress =fakeData.getData().getStreetAddresses();
    private String txtZipCode= fakeData.getData().getZipCode();
    private String txtPhoneNumber =fakeData.getData().getPhoneNumber();



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
        myAccountPageObject = PageGeneratorManager.getMyAccountPage(driver);
        myAccountPageObject.clickToHeaderLinkByText(driver,"My account");
        myAccountPageObject.clickToMenuMyAccountByText(driver,"Addresses");
        myAccountPageObject.clickToButtonByText(driver,"Add new");
    }

    @Test
    public void User_My_Account_Nopcommerce_Addresses_01_Empty_Value_Click_Save(Method method) {
        startTest(method.getName(), "Addresses_Empty_Value_Click_Save");
        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 01 : Verify message error for Fist Name text box with empty value : 'First name is required.'");
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "First name"), "First name is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 02 : Verify message error for Last Name text box with empty value : 'Last name is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Last name"), "Last name is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 03 : Verify message error for Fist Name text box with empty value : 'Email is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Email"), "Email is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 04 : Verify message error for Fist Name text box with empty value : 'City is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"City", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "City"), "City is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 05 : Verify message error for Address 1 text box with empty value : 'Street address is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Address 1", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Address 1"), "Street address is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 06 : Verify message error for Zip / postal code text box with empty value : 'Zip / postal code is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Zip / postal code", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Zip / postal code"), "Zip / postal code is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 07 : Verify message error for Phone number text box with empty value : 'Phone is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Phone number", "");
        myAccountPageObject.clickToButtonByText(driver, "Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Phone number"), "Phone is required");
    }

    @Test
    public void User_My_Account_Nopcommerce_Addresses_02_Empty_Value_Enter_Key(Method method) {
        startTest(method.getName(), "Addresses_Empty_Value_Click_Save");
        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 01 : Verify message error for Fist Name text box with empty value : 'First name is required.'");
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"First name", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "First name"), "First name is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 02 : Verify message error for Last Name text box with empty value : 'Last name is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Last name", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Last name"), "Last name is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 03 : Verify message error for Email text box with empty value : 'Email is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Email", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Email"), "Email is required.");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 04 : Verify message error for City text box with empty value : 'City is required.'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"City", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"City", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "City"), "City is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 05 : Verify message error for Address 1 text box with empty value : 'Street address is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Address 1", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Address 1", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Address 1"), "Street address is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 06 : Verify message error for Zip / postal code text box with empty value : 'Zip / postal code is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Zip / postal code", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Zip / postal code", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Zip / postal code"), "Zip / postal code is required");

        logInfo("User_My_Account_01_Nopcommerce_Addresses_Empty_Value_Click_Save - Step 07 : Verify message error for Phone number text box with empty value : 'Phone is required'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Phone number", "");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Phone number", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver, "Phone number"), "Phone is required");
    }
    @Test
    public void User_My_Account_Nopcommerce_Addresses_03_Email_Invalid_Click_Save(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Email_Invalid_Click_Save");
        logInfo("User_My_Account_04_Update_Information_Customer_Info_Email_Invalid_Click_Save - Step 01 : Verify message error for Email text box with invalid Email : 'Wrong email'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","dfsfs@affs.");
        myAccountPageObject.clickToButtonByText(driver,"Save");
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Wrong email");
    }

    @Test
    public void User_My_Account_Nopcommerce_Addresses_04_Email_Invalid_Enter_Key(Method method) {
        startTest(method.getName(),"Update_Information_Customer_Info_Email_Invalid_Enter_Key");
        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 01 : Verify message error for Email text box with invalid Email : 'Wrong email'");
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email","dfsfs@affs.");
        myAccountPageObject.senKeyToTextBoxByLabelName(driver,"Email", Keys.ENTER);
        verifyEquals(myAccountPageObject.getMessageErrorByLabelName(driver,"Email"),"Wrong email");
    }

    @Test
    public void User_My_Account_Nopcommerce_Addresses_05_Add_New_Addresses(Method method) {
        startTest(method.getName(),"Add_New_Addresses");
        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 01 : Enter value for First name :"+txtFistNameNew);
        myAccountPageObject.refreshToPage(driver);
        myAccountPageObject.inputTextBoxByLabelName(driver,"First name",txtFistNameNew);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 02 : Enter value for Last name :"+txtLastNameNew);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Last name",txtLastNameNew);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 03 : Enter value for Email :"+txtEmailNew);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Email",txtEmailNew);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 04 : Select value for Country :"+txtCountryNew);
        myAccountPageObject.selectDropDownCountryAndState(txtCountryNew,txtState);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 05 : Enter value for City :"+txtCityNameNew);
        myAccountPageObject.inputTextBoxByLabelName(driver,"City",txtCityNameNew);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 06 : Enter value for Address 1 :"+txtStreetAddress);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Address 1",txtStreetAddress);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 07 : Enter value for Zip / postal code :"+txtZipCode);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Zip / postal code",txtZipCode);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 08 : Enter value for Phone number :"+txtPhoneNumber);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Phone number",txtPhoneNumber);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 08 : Enter value for Phone number :"+txtPhoneNumber);
        myAccountPageObject.inputTextBoxByLabelName(driver,"Phone number",txtPhoneNumber);

        logInfo("User_My_Account_05_Update_Information_Customer_Info_Email_Invalid_Enter_Key - Step 09 : Click to Save Button");
        myAccountPageObject.clickToButtonByText(driver,"Save");
    }


    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
