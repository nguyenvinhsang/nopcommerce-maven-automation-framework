package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.ProductDetailPageUI;

import java.util.List;

public class CategoryPageObject extends BasePage {
    WebDriver driver;

    public CategoryPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String[] get5ProductTitle() {
        int i=0;
        waitForAllElementVisible(driver, BaseUI.PRODUCT_TITLE_NAME);
        List<WebElement> allElement=getElements(driver, BaseUI.PRODUCT_TITLE_NAME);
        String[] element5product = new String[5];
        while(i<5){
            element5product[i]=allElement.get(i+1).getText();
            i++;
        }
        return element5product;
    }


    public void viewProductAndBackPage(String[] strProductsTitle) {
        for (String txtProduct: strProductsTitle) {
            waitForElementClickable(driver,BaseUI.DYNAMIC_PRODUCT_TITLE_NAME,txtProduct);
            clickToElement(driver,BaseUI.DYNAMIC_PRODUCT_TITLE_NAME,txtProduct);
            backToPage(driver);
        }
    }


}
