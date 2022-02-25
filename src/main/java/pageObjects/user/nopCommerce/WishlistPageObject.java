package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.user.nopCommerce.WishlistPageUI;

public class WishlistPageObject extends BasePage {
    WebDriver driver;
    public WishlistPageObject(WebDriver driver) {
        this.driver=driver;
    }


    public void clickToUrlSharing() {
        waitForElementVisible(driver,WishlistPageUI.URL_SHARING);
        clickToElement(driver,WishlistPageUI.URL_SHARING);
    }


    public String getWishlistPageTitle() {
        waitForElementVisible(driver,WishlistPageUI.WISHLIST_PAGE_TITLE);
        String fullName= getElementText(driver,WishlistPageUI.WISHLIST_PAGE_TITLE).replace("Wishlist of","").trim();
        return  fullName;
    }

    public void checkToCheckBoxAddToCart() {
        waitForElementClickable(driver,WishlistPageUI.CHECKBOX_ADD_TO_CART);
        checkToCheckBoxOrRadio(driver,WishlistPageUI.CHECKBOX_ADD_TO_CART);
    }

    public void clickToFirstRemoveIcon() {
        waitForElementClickable(driver,WishlistPageUI.REMOVE_ICON);
        clickToElement(driver,WishlistPageUI.REMOVE_ICON);
    }
}
