package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_06_My_Account_Nopcommerce_My_Product_Review extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private HomePageObject homePage;
    private MyAccountPageObject myAccountPage;
    private RegisterPageObject registerPage;
    private ProductDetailPageObject productDetailPage;
    private ProductReviewsPageObject productReviewsPage;

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
    public void User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_Review(Method method) {
        startTest(method.getName(), "My_Product_Review_01_Add_With_Empty_Value");
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 01 : Click to title product : 'Build your own computer'");
        homePage =PageGeneratorManager.getHomePage(driver);
        homePage.clickToProductByTitle(driver,txtProductTitleName);
        productDetailPage = PageGeneratorManager.getProductDetailPage(driver);
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 02 : Click link text : 'Add your review'");
        productDetailPage.clickToLinkByText(driver,"Add your review");
        productReviewsPage = PageGeneratorManager.getProductReviewsPage(driver);
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 03 : Enter value ("+txtReviewTitle+") for Review title");
        productReviewsPage.inputTextBoxByLabelName(driver,"Review title",txtReviewTitle);
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 04 : Enter value ("+txtReviewText+") for Review text");
        productReviewsPage.inputReviewText(txtReviewText);
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 05 : Click to button Submit review");
        productReviewsPage.clickToButtonByText(driver,"Submit review");
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 06 : Go to MyAccount -> My product reviews");
        productReviewsPage.clickToHeaderLinkByText(driver,"My account");
        myAccountPage = PageGeneratorManager.getMyAccountPage(driver);
        myAccountPage.clickToMenuMyAccountByText(driver,"My product reviews");
        logInfo("User_06_My_Account_Nopcommerce_My_Product_Review_01_Add_With_Empty_Value - Step 07 : Verify My product reviews ReviewTitle: "+txtReviewTitle+"/ Review text: "+txtReviewText+"/ Product title text: "+txtProductTitleName);
        verifyTrue(myAccountPage.verifyProductReview(txtReviewTitle,txtReviewText,txtProductTitleName));
   }



    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
