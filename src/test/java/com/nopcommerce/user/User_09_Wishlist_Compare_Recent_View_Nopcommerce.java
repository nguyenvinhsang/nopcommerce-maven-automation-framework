package com.nopcommerce.user;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.nopCommerce.*;
import utilities.DataUtil;

import java.lang.reflect.Method;

import static reportConfig.ExtentTestManager.startTest;

public class User_09_Wishlist_Compare_Recent_View_Nopcommerce extends BaseTest {
    DataUtil fakeData;
    private WebDriver driver;
    private RegisterPageObject registerPage;
    private HomePageObject homePage;
    private CategoryPageObject categoryPage;
    private WishlistPageObject wishlistPage;
    private ShoppingCartPageObject shoppingCartPage;
    private CompareProductPageObject compareProductPage;
    private RecentlyViewedProductPageObject recentlyViewedProductPage;

    private String txtLastName = fakeData.getData().getLastName();
    private String txtFistName = fakeData.getData().getFistName();
    private String txtEmail = fakeData.getData().getEmailAddress();
    private String txtPassWord = fakeData.getData().getPassWord();
    private String txtFullName=txtFistName+" "+txtLastName;
    private String urlHomePage;



    @Parameters({"browser", "url", "envName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void initBrowser(@Optional("chrome") String browserName, @Optional("dev") String appUrl, @Optional("local") String envName, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber, @Optional("Windows") String osName, @Optional("10") String osVersion) {
        log.info("Pre-Condition-Step: 01 Open browser " + browserName + " and navigate to " + "appUrl");
        driver = getBrowserDriver(browserName, appUrl, envName, ipAddress, portNumber, osName, osVersion);
        log.info("Pre-Condition-Step: 02 - Step 01 : Create account and login");
        urlHomePage=appUrl;
        homePage = PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderLinkByText(driver, "Register");
        registerPage = PageGeneratorManager.getRegisterPage(driver);
        registerPage.inputFristNameTextBox(txtFistName);
        registerPage.inputLastNameTextBox(txtLastName);
        registerPage.inputEmailTextBox(txtEmail);
        registerPage.inputPasswordTextBox(txtPassWord);
        registerPage.inputConfirmPasswordTextBox(txtPassWord);
        registerPage.clickToRegisterButton();
        homePage =PageGeneratorManager.getHomePage(driver);
        homePage.clickToHeaderMenuByText(driver,"Computers");
        categoryPage =PageGeneratorManager.getCategoryPage(driver);
        categoryPage.clickToSide2ByTitleAndNameLink(driver,"Categories","Notebooks");
    }

    @Test
    public void User_08_TC_01_Add_Product_To_Wishlist(Method method) {
        startTest(method.getName(), "Add_Product_To_Wishlist");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 01 : Click to title product (Apple MacBook Pro 13-inch)");
        categoryPage.clickToProductByTitle(driver,"Apple MacBook Pro 13-inch");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 02 : Click to button (Add to wishlist)");
        categoryPage.clickToButtonByText(driver,"Add to wishlist");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 03 : Verify message display : 'The product has been added to your wishlist'");
        verifyEquals(categoryPage.successMessage(driver),"The product has been added to your wishlist");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 04 : Click to header link 'Wishlist'");
        categoryPage.clickToHeaderLinkByText(driver,"Wishlist");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 05 : Verify the product was added to wishlist successfully");
        wishlistPage =PageGeneratorManager.getWishlistPage(driver);
        verifyEquals(wishlistPage.getFristProductName(driver),"Apple MacBook Pro 13-inch");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 06 : Go to link from 'Your wishlist URL for sharing:'");
        wishlistPage.clickToUrlSharing();
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 07 : Verify Name user share link ( "+ txtFullName +" )");
        verifyEquals(wishlistPage.getWishlistPageTitle(),txtFullName);
    }

    @Test
    public void User_08_TC_02_Add_Product_To_Cart_From_Wishlist(Method method) {
        startTest(method.getName(), "Add_Product_To_Cart_From_Wishlist");
        logInfo("User_08_TC_02_Add_Product_To_Cart_From_Wishlist - Step 01 : Go to Page 'Wishlist'");
        wishlistPage.backToPage(driver);
        logInfo("User_08_TC_02_Add_Product_To_Cart_From_Wishlist - Step 02 : Click to check box add to cart (product Apple MacBook Pro 13-inch)");
        wishlistPage.checkToCheckBoxAddToCart();
        logInfo("User_08_TC_02_Add_Product_To_Cart_From_Wishlist - Step 03 : Click to 'ADD TO CART' button");
        wishlistPage.clickToButtonByText(driver,"Add to cart");
        logInfo("User_08_TC_02_Add_Product_To_Cart_From_Wishlist - Step 04 : Verify product was been added in Shopping cart");
        shoppingCartPage= PageGeneratorManager.getShoppingCartPage(driver);
        verifyEquals(shoppingCartPage.getFristProductName(driver),"Apple MacBook Pro 13-inch");
        logInfo("User_08_TC_02_Add_Product_To_Cart_From_Wishlist - Step 05 : Verify product was been deleted in wishlist page (product Apple MacBook Pro 13-inch)");
        shoppingCartPage.clickToHeaderLinkByText(driver,"Wishlist");
        wishlistPage =PageGeneratorManager.getWishlistPage(driver);
        verifyEquals(wishlistPage.getFristProductName(driver),"");
    }

    @Test
    public void User_08_TC_03_Remove_Product_In_Wishlist(Method method) {
        startTest(method.getName(), "Remove_Product_In_Wishlist");
        logInfo("User_08_TC_03_Remove_Product_In_Wishlist - Step 01 : Add product to Wishlist (Apple MacBook Pro 13-inch)");
        wishlistPage.clickToHeaderMenuByText(driver,"Computers");
        categoryPage =PageGeneratorManager.getCategoryPage(driver);
        categoryPage.clickToSide2ByTitleAndNameLink(driver,"Categories","Notebooks");
        categoryPage.clickToProductByTitle(driver,"Apple MacBook Pro 13-inch");
        categoryPage.clickToButtonByText(driver,"Add to wishlist");
        categoryPage.clickToHeaderLinkByText(driver,"Wishlist");
        logInfo("User_08_TC_03_Remove_Product_In_Wishlist - Step 02 : Click to check box Remove (product Apple MacBook Pro 13-inch)");
        wishlistPage =PageGeneratorManager.getWishlistPage(driver);
        wishlistPage.clickToFirstRemoveIcon();
        logInfo("User_08_TC_03_Remove_Product_In_Wishlist - Step 03 : Verify message display (The wishlist is empty!)");
        verifyEquals(wishlistPage.getPageBodyNoData(driver),"The wishlist is empty!");
    }

    @Test
    public void User_08_TC_04_Add_Product_To_Compare(Method method) {
        startTest(method.getName(), "Add_Product_To_Wishlist");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 01 : Go to category page (Notebooks)");
        wishlistPage.clickToHeaderMenuByText(driver,"Computers");
        categoryPage =PageGeneratorManager.getCategoryPage(driver);
        categoryPage.clickToSide2ByTitleAndNameLink(driver,"Categories","Notebooks");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 02 : Click to Compare button (Apple MacBook Pro 13-inch) ");
        categoryPage.clickToButtonAddCompareListByProductTitle(driver,"Apple MacBook Pro 13-inch");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 03 : Verify message display : 'The product has been added to your product comparison'");
        verifyEquals(categoryPage.successMessage(driver),"The product has been added to your product comparison");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 04 : Click to Compare button (Asus N551JK-XO076H Laptop) ");
        categoryPage.clickToButtonAddCompareListByProductTitle(driver,"Asus N551JK-XO076H Laptop");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 06 : Verify message display : 'The product has been added to your product comparison'");
        verifyEquals(categoryPage.successMessage(driver),"The product has been added to your product comparison");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 09 : Click to link 'product comparison'");
        categoryPage.clickToFooterLinkByText(driver,"Compare products list");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 10 : Verify product information display (Asus N551JK-XO076H Laptop and Apple MacBook Pro 13-inch)");
        compareProductPage = PageGeneratorManager.getCompareProductPage(driver);
        String str[]={"Asus N551JK-XO076H Laptop","Apple MacBook Pro 13-inch"};
        verifyTrue(compareProductPage.isDisplayProduct(str));
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 11 : Click to link 'CLEAR LIST' button");
        compareProductPage.clickToClearListButton();
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 12 : Verify message display : 'You have no items to compare.'");
        verifyEquals(compareProductPage.getPageBodyNoData(driver),"You have no items to compare.");
        logInfo("User_08_TC_01_Add_Product_To_Wishlist - Step 13 : Verify product is not display in Compare page (product: Asus N551JK-XO076H Laptop and Apple MacBook Pro 13-inch)");
        verifyFalse(compareProductPage.isDisplayProduct(str));
    }

    @Test
    public void User_08_TC_05_Recently_Viewed_Product(Method method) {
        startTest(method.getName(), "Recently_Viewed_Product");
        logInfo("User_08_TC_05_Recently_Viewed_Product - Step 01 : Click to Header menu (Computer)");
        categoryPage =PageGeneratorManager.getCategoryPage(driver);
        categoryPage.clickToHeaderMenuByText(driver,"Computers");
        logInfo("User_08_TC_05_Recently_Viewed_Product - Step 02 : Click to NoteBooks link");
        categoryPage.clickToSide2ByTitleAndNameLink(driver,"Categories","Notebooks");
        logInfo("User_08_TC_05_Recently_Viewed_Product - Step 03 : Click to title product (random 5 product)");
        String[] strProductsTitle = categoryPage.get5ProductTitle();
        categoryPage.viewProductAndBackPage(strProductsTitle);
        logInfo("User_08_TC_05_Recently_Viewed_Product - Step 04 : Go to recently viewed product");
        categoryPage.clickToFooterLinkByText(driver,"Recently viewed products");
        logInfo("User_08_TC_05_Recently_Viewed_Product - Step 05 : Verify product 3 last product");
        recentlyViewedProductPage =PageGeneratorManager.getRecentlyViewedProductPage(driver);
        verifyTrue(recentlyViewedProductPage.isProductRecentlyViewCorrect(strProductsTitle));
    }



    @Parameters({"browser"})
    @AfterClass(alwaysRun = true)
    public void closeBrowser(String browserName) {
        log.info("Pre-Condition-Step: Close browser : "+browserName);
        cleanBrowserAndDriver();
    }

}
