package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import pageUIs.user.nopCommerce.SortPageUI;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_08_Sort_Display_Paging_Nopcommerce extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private RegisterPageObject registerPage;
    private HomePageObject homePage;
    private SortPageObject sortPage;

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
/*        log.info("Pre-Condition-Step: 02 - Step 01 : Create account");
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver, "Register");
        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.inputFristNameTextBox(txtFistName);
        registerPage.inputLastNameTextBox(txtLastName);
        registerPage.inputEmailTextBox(txtEmail);
        registerPage.inputPasswordTextBox(txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        registerPage.clickToRegisterButton();
        registerPage.getUrl(driver,appUrl);*/
    }

    @Test
    public void User_08_TC_01_Sort_With_Name_A_to_Z(Method method) {
        startTest(method.getName(), "Sort_With_Name_A_to_Z");
        logInfo("User_08_TC_01_Sort_With_Name_A_to_Z - Step 01 : Click to Header menu (Computer)");
        sortPage =PageGeneratorManager.getSortPage(driver);
        sortPage.clickToHeaderMenuByText(driver,"Computers");
        logInfo("User_08_TC_01_Sort_With_Name_A_to_Z - Step 02 : Click to NoteBooks link");
        sortPage.clickToSide2ByTitleAndNameLink(driver,"Categories","Notebooks");
        logInfo("User_08_TC_01_Sort_With_Name_A_to_Z - Step 03 : Select 'Name: A to Z' of dropbox 'Sort by' ");
        sortPage.selectDropDownBySpan(driver,"Sort by","Name: A to Z");
        logInfo("User_08_TC_01_Sort_With_Name_A_to_Z - Step 04 : Verify product name sort a-z");
        verifyTrue(sortPage.verifySort("Name: A to Z"));

    }
    @Test
    public void User_08_TC_02_Sort_With_Name_Z_to_A(Method method) {
        startTest(method.getName(), "Sort_With_Name_Z_to_A");
        logInfo("User_08_TC_02_Sort_With_Name_Z_to_A - Step 01 : Select 'Name: Z to A' of dropbox 'Sort by' ");
        sortPage.selectDropDownBySpan(driver,"Sort by","Name: Z to A");
        logInfo("User_08_TC_02_Sort_With_Name_Z_to_A - Step 02 : Verify product name sort Z-A");
        verifyTrue(sortPage.verifySort("Name: Z to A"));

    }

    @Test
    public void User_08_TC_03_Sort_With_Price_Low_To_High(Method method) {
        startTest(method.getName(), "Sort_With_Price_Low_To_High");
        logInfo("User_08_TC_03_Sort_With_Price_Low_To_High - Step 01 : Select 'Price: Low to High' of dropbox 'Sort by' ");
        sortPage.selectDropDownBySpan(driver,"Sort by","Price: Low to High");
        logInfo("User_08_TC_03_Sort_With_Price_Low_To_High - Step 02 : Verify product Price: Low to High");
        verifyTrue(sortPage.verifySort("Price: Low to High"));

    }

    @Test
    public void User_08_TC_04_Sort_With_Price_High_To_Low(Method method) {
        startTest(method.getName(), "Sort_With_Price_High_To_Low");
        logInfo("User_08_TC_04_Sort_With_Price_High_To_Low - Step 01 : Select 'Price: High to Low' of dropbox 'Sort by' ");
        sortPage.selectDropDownBySpan(driver,"Sort by","Price: High to Low");
        logInfo("User_08_TC_04_Sort_With_Price_High_To_Low - Step 02 : Verify product Price: High to Low");
        verifyTrue(sortPage.verifySort("Price: High to Low"));

    }

    @Test
    public void User_08_TC_05_Paging_With_Display_3_Products_On_Page(Method method) {
        startTest(method.getName(), "Paging_With_Display_3_Product_On_Page");
        logInfo("User_08_TC_05_Paging_With_Display_3_Products_On_Page - Step 01 : Select '3' of dropbox 'Display' ");
        sortPage.selectDropDownBySpan(driver,"Display","3");
        logInfo("User_08_TC_05_Paging_With_Display_3_Products_On_Page - Step 02 : Verify display <=3 product on page");
        verifyTrue(sortPage.isProductLessThanOrEqualTo("3"));
        logInfo("User_08_TC_05_Paging_With_Display_3_Products_On_Page - Step 03 : Verify next icon display when current page is 1");
        verifyTrue(sortPage.isDisPlayNextIcon());
        logInfo("User_08_TC_05_Paging_With_Display_3_Products_On_Page - Step 04 : Verify previous icon display when current page is 2");
        sortPage.clickToPagerByText("2");
        verifyTrue(sortPage.isDisPlayPreviousIcon());
    }

    @Test
    public void User_08_TC_06_Paging_With_Display_6_Products_On_Page(Method method) {
        startTest(method.getName(), "Paging_With_Display_6_Product_On_Page");
        logInfo("User_08_TC_06_Paging_With_Display_6_Products_On_Page - Step 01 : Select '6' of dropbox 'Display' ");
        sortPage.selectDropDownBySpan(driver,"Display","6");
        logInfo("User_08_TC_06_Paging_With_Display_6_Products_On_Page - Step 02 : Verify display <=6 product on page");
        verifyTrue(sortPage.isProductLessThanOrEqualTo("6"));
        logInfo("User_08_TC_06_Paging_With_Display_6_Products_On_Page - Step 03 : Verify paging not display");
        verifyFalse(sortPage.isDisPlayPaging());
    }

    @Test
    public void User_08_TC_07_Paging_With_Display_9_Products_On_Page(Method method) {
        startTest(method.getName(), "Paging_With_Display_9_Product_On_Page");
        logInfo("User_08_TC_07_Paging_With_Display_9_Products_On_Page - Step 01 : Select '9' of dropbox 'Display' ");
        sortPage.selectDropDownBySpan(driver,"Display","9");
        logInfo("User_08_TC_07_Paging_With_Display_9_Products_On_Page - Step 02 : Verify display <=9 product on page");
        verifyTrue(sortPage.isProductLessThanOrEqualTo("9"));
        logInfo("User_08_TC_07_Paging_With_Display_9_Products_On_Page - Step 03 : Verify paging not display");
        verifyFalse(sortPage.isDisPlayPaging());
    }



    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
