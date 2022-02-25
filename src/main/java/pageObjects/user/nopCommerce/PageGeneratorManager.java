package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    private PageGeneratorManager() {
    }

    public static HomePageObject getHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver) {

        return new RegisterPageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static OrdersPageObject getOrdersPage(WebDriver driver) {
        return new OrdersPageObject(driver);
    }

    public static NewPageObject getNewPage(WebDriver driver) {
        return new NewPageObject(driver);
    }

    public static SearchPageObject getSearchPage(WebDriver driver) {
        return new SearchPageObject(driver);
    }


    public static MyAccountPageObject getMyAccountPage(WebDriver driver){
        return new MyAccountPageObject(driver);
    }

    public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
        return new ProductDetailPageObject(driver);
    }

    public static ProductReviewsPageObject getProductReviewsPage(WebDriver driver) {
        return new ProductReviewsPageObject(driver);
    }

    public static SortPageObject getSortPage(WebDriver driver) {
        return new SortPageObject(driver);
    }

    public static CategoryPageObject getCategoryPage(WebDriver driver) {
        return new CategoryPageObject(driver);
    }

    public static WishlistPageObject getWishlistPage(WebDriver driver) {
        return new WishlistPageObject(driver);
    }

    public static ShoppingCartPageObject getShoppingCartPage(WebDriver driver) {
        return new ShoppingCartPageObject(driver);
    }

    public static CompareProductPageObject getCompareProductPage(WebDriver driver) {
        return new CompareProductPageObject(driver);
    }

    public static RecentlyViewedProductPageObject getRecentlyViewedProductPage(WebDriver driver) {
        return new RecentlyViewedProductPageObject(driver);
    }
}
