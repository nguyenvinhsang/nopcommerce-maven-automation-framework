package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.nopCommerce.BaseUI;

import java.util.List;

public class RecentlyViewedProductPageObject extends BasePage {
    WebDriver driver;
    public RecentlyViewedProductPageObject(WebDriver driver) {
        this.driver=driver;
    }

    public boolean isProductRecentlyViewCorrect(String[] strProductsTitle) {
        int j=0;
        List<WebElement> allProductName= getElements(driver, BaseUI.PRODUCT_TITLE_NAME);
        for (int i=2;i<5;i++){
            for (WebElement element :allProductName) {
                if(element.getText().equals(strProductsTitle[i])){
                    j++;
                }
            }
        }
        if (j==3){
            return true;
        }else {
            return false;
        }
    }
}
