package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_07_Search_Nopcommerce extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private RegisterPageObject registerPage;
    private SearchPageObject searchPage;
    private HomePageObject homePage;

    private String txtLastName = fakeData.getData().getLastName();
    private String txtFistName = fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord = fakeData.getData().getPassWord();
    private String txtReviewTitle="Good product";
    private String txtReviewText="Good product....";
    private String txtProductTitleName="Build your own computer";



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
        registerPage.getUrl(driver,appUrl);
    }

    @Test
    public void User_Search_Nopcommerce_Search_01_Test_Box_With_Not_Input_Value(Method method) {
        startTest(method.getName(), "Test_Box_With_Not_Input_Value");
        logInfo("User_Search_Nopcommerce_Search_01_Test_Box_With_Not_Input_Value - Step 01 : Click to button Search and don't input value of Search text box");
        searchPage =PageGeneratorManager.getSearchPage(driver);
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_01_Test_Box_With_Not_Input_Value - Step 02 : Verify message alert 'Please enter some search keyword'");
        verifyEquals(searchPage.getTextAlert(driver),"Please enter some search keyword");
        searchPage.acceptAlert(driver);
   }

    @Test
    public void User_Search_Nopcommerce_Search_02_Search_Test_Box_Value_Less_Than_3_Character(Method method) {
        startTest(method.getName(), "Test_Box_Value_Less_Than_3_Character");
        logInfo("User_Search_Nopcommerce_Search_02_Search_Test_Box_Value_Less_Than_3_Character - Step 01 :Enter value (1) for Search text box");
        searchPage.inputSearchTextBox(driver,"1");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_02_Search_Test_Box_Value_Less_Than_3_Character - Step 02 : Verify message error 'Search term minimum length is 3 characters'");
        verifyEquals(searchPage.getMessageSearchButton(driver),"Search term minimum length is 3 characters");
    }

    @Test
    public void User_Search_Nopcommerce_Search_03_Search_Test_Box_Value_3_Character_And_Not_Found_Product(Method method) {
        startTest(method.getName(), "Test_Box_Value_3_Character_And_Not_Found_Product");
        logInfo("User_Search_Nopcommerce_Search_03_Search_Test_Box_Value_3_Character_And_Not_Found_Product - Step 01 :Enter value (113) for Search text box");
        searchPage.inputSearchTextBox(driver,"113");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_03_Search_Test_Box_Value_3_Character_And_Not_Found_Product - Step 02 : Verify message error 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getMessageSearchButton(driver),"No products were found that matched your criteria.");
    }

    @Test
    public void User_Search_Nopcommerce_Search_04_Search_Test_Box_Value_Exist_Less_Than_1_Item(Method method) {
        startTest(method.getName(), "Search_Test_Box_Value_Exist_Less_Than_1_Item");
        logInfo("User_Search_Nopcommerce_Search_04_Search_Test_Box_Value_Exist_Less_Than_1_Item - Step 01 :Enter value (Lenovo) for Search text box");
        searchPage.inputSearchTextBox(driver,"Lenovo");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_04_Search_Test_Box_Value_Exist_Less_Than_1_Item - Step 02 : Verify result result of 'lenovo' have 2 item");
        verifyTrue(searchPage.checkSearchResult(driver,"Lenovo","2"));
    }

    @Test
    public void User_Search_Nopcommerce_Search_05_Search_Test_Box_Value_Exist_1_Item(Method method) {
        startTest(method.getName(), "Search_Test_Box_Value_Exist_1_Item");
        logInfo("User_Search_Nopcommerce_Search_05_Search_Test_Box_Value_Exist_1_Item - Step 01 :Enter value (MacBook) for Search text box");
        searchPage.inputSearchTextBox(driver,"MacBook");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_05_Search_Test_Box_Value_Exist_1_Item - Step 02 : Verify result result of 'MacBook' have 1 item");
        verifyTrue(searchPage.checkSearchResult(driver,"MacBook","1"));
    }

    @Test
    public void User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories(Method method) {
        startTest(method.getName(), "Advanced_Search_With_Parent_Categories");
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 01 :Enter value (Apple MacBook Pro) for Search text box");
        searchPage.inputSearchTextBox(driver,"Apple MacBook Pro");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 02 : Advanced search is checked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Advanced search");
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 03 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Category","Computers");
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 04 : Automatically search sub categories is unchecked ");
        searchPage.unCheckToCheckBoxOrRadioByLabel(driver,"Automatically search sub categories");
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 05 : Click to Search button of Advanced Search");
        searchPage.clickToButtonSearchOfAdvancedSearch(driver);
        logInfo("User_Search_Nopcommerce_Search_06_Advanced_Search_With_Parent_Categories - Step 06 : Verify message error 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getMessageSearchButton(driver),"No products were found that matched your criteria.");
    }

    @Test
    public void User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories(Method method) {
        startTest(method.getName(), "Advanced_Search_With_Parent_Categories");
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 01 :Enter value (Apple MacBook Pro) for Search text box");
        searchPage.inputSearchTextBox(driver,"macBook");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 02 : Advanced search is checked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Advanced search");
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 03 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Category","Computers");
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 04 : Automatically search sub categories is unchecked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Automatically search sub categories");
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 05 : Click to Search button of Advanced Search");
        searchPage.clickToButtonSearchOfAdvancedSearch(driver);
        logInfo("User_Search_Nopcommerce_Search_07_Advanced_Search_With_Sub_Categories - Step 06 : Verify result result of 'Apple MacBook Pro' have 1 item");
        verifyTrue(searchPage.checkSearchResult(driver,"Apple MacBook Pro","1"));
    }

    @Test
    public void User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer(Method method) {
        startTest(method.getName(), "Advanced_Search_With_Incorrect_Manufacturer");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 01 :Enter value (Apple MacBook Pro) for Search text box");
        searchPage.inputSearchTextBox(driver,"macBook");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 02 : Advanced search is checked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Advanced search");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 03 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Category","Computers");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 04 : Automatically search sub categories is unchecked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Automatically search sub categories");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 05 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Manufacturer","HP");
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 06 : Click to Search button of Advanced Search");
        searchPage.clickToButtonSearchOfAdvancedSearch(driver);
        logInfo("User_Search_Nopcommerce_Search_08_Advanced_Search_With_Incorrect_Manufacturer - Step 07 : Verify message error 'No products were found that matched your criteria.'");
        verifyEquals(searchPage.getMessageSearchButton(driver),"No products were found that matched your criteria.");
    }

    @Test
    public void User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer(Method method) {
        startTest(method.getName(), "Advanced_Search_With_Correct_Manufacturer");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 01 :Enter value (Apple MacBook Pro) for Search text box");
        searchPage.inputSearchTextBox(driver,"macBook");
        searchPage.clickToButtonByText(driver,"Search");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 02 : Advanced search is checked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Advanced search");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 03 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Category","Computers");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 04 : Automatically search sub categories is unchecked ");
        searchPage.checkToCheckBoxOrRadioByLabel(driver,"Automatically search sub categories");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 05 : Category select Computers");
        searchPage.selectDropDownByLabel(driver,"Manufacturer","Apple");
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 06 : Click to Search button of Advanced Search");
        searchPage.clickToButtonSearchOfAdvancedSearch(driver);
        logInfo("User_Search_Nopcommerce_Search_09_Advanced_Search_With_Correct_Manufacturer - Step 07 : Verify result result of 'Apple MacBook Pro' have 1 item");
        verifyTrue(searchPage.checkSearchResult(driver,"Apple MacBook Pro","1"));
    }





    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
