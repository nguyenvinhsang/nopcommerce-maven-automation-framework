package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.user.nopCommerce.ProductDetailPageUI;

public class ProductDetailPageObject extends BasePage {
    WebDriver driver;
    public ProductDetailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectOptionProduct(String processor, String ram, String hdd, String os, String[] software) {
        selectDropdownByText(driver,ProductDetailPageUI.PROCESSOR_DROPDOWN,processor);

        selectDropdownByText(driver,ProductDetailPageUI.RAM_DROPDOWN,ram);

        checkToCheckBoxOrRadioByLabel(driver,hdd);

        checkToCheckBoxOrRadioByLabel(driver,os);

        for (String item : software) {
            checkToCheckBoxOrRadioByLabel(driver,item);
        }
    }
}
