package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.CompareProductPageUI;

import java.time.Duration;
import java.util.List;

public class CompareProductPageObject extends BasePage {
    WebDriver driver;
    public CompareProductPageObject(WebDriver driver) {
        this.driver=driver;
    }

    public boolean isDisplayProduct(String[] str) {
        boolean check;
        int checkCount=0;
        areJQueryAndJSLoadedSuccess(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        try {
            waitForElementVisible(driver, BaseUI.DYNAMIC_TABLE,"compare-products-table");
            waitForAllElementVisible(driver,CompareProductPageUI.COMPARE_PAGE_PRODUCT_NAME);
            waitForAllElementVisible(driver,CompareProductPageUI.COMPARE_PAGE_REMOVE_BUTTON);
            List<WebElement> elementsProductName = driver.findElements(By.xpath(CompareProductPageUI.COMPARE_PAGE_PRODUCT_NAME));
            List<WebElement> elementsRemoveButton = driver.findElements(By.xpath(CompareProductPageUI.COMPARE_PAGE_REMOVE_BUTTON));
            for (int i =0;i < str.length;i++){
                for (WebElement item : elementsProductName ){
                    if (item.getText().equals(str[i])){
                        checkCount++;
                    }
                }
            }
            if (checkCount==str.length && elementsProductName.size()==elementsRemoveButton.size() && driver.findElement(By.xpath(CompareProductPageUI.CLEAR_LIST_BUTTON)).isDisplayed()){
                check= true;
            }else{
                check= false;
            }
        }catch (Exception e){
            check = false;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return  check;
    }

    public void clickToClearListButton() {
        waitForElementClickable(driver,CompareProductPageUI.CLEAR_LIST_BUTTON);
        clickToElement(driver,CompareProductPageUI.CLEAR_LIST_BUTTON);
    }
}
