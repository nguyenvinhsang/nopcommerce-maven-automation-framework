package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPageObject extends BasePage {
    WebDriver driver;
    public ShoppingCartPageObject(WebDriver driver) {
        this.driver=driver;
    }
}
