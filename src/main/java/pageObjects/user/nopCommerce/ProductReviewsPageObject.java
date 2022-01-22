package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.user.nopCommerce.ProductDetailPageUI;

public class ProductReviewsPageObject extends BasePage {
    WebDriver driver;

    public ProductReviewsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputReviewText(String txtReviewText) {
        waitForElementClickable(driver, ProductDetailPageUI.REVIEW_TEXT_TEXTAREA);
        sendKeyToElement(driver, ProductDetailPageUI.REVIEW_TEXT_TEXTAREA,txtReviewText);
    }
}
