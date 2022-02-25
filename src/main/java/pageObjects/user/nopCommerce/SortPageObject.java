package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.SortPageUI;

import java.time.Duration;
import java.util.List;

public class SortPageObject extends BasePage {
    WebDriver driver;

    public SortPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean verifySort(String typeSort) {
        boolean status = true;
        if (typeSort == "Name: A to Z") {
            areJQueryAndJSLoadedSuccess(driver);
            List<WebElement> sortElement = driver.findElements(By.xpath(BaseUI.PRODUCT_TITLE_NAME));
            int count = sortElement.size();
            String temp;
            String strS[] = new String[count];
            String str[] = new String[count];
            for (int i = 0; i < count; i++) {
                strS[i] = sortElement.get(i).getText();
                str[i] = sortElement.get(i).getText();
            }
            str = strS;
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (str[i].compareTo(str[j]) > 0) {
                        temp = str[i];
                        str[i] = str[j];
                        str[j] = temp;
                    }
                }
            }
            if (strS != str) {
                status = false;
            } else {
                status = true;
            }
        }

        if (typeSort == "Name: Z to A") {
            areJQueryAndJSLoadedSuccess(driver);
            List<WebElement> sortElement = driver.findElements(By.xpath(BaseUI.PRODUCT_TITLE_NAME));
            int count = sortElement.size();
            String temp;
            String strS[] = new String[count];
            String str[] = new String[count];
            for (int i = 0; i < count; i++) {
                strS[i] = sortElement.get(i).getText();
                str[i] = sortElement.get(i).getText();
            }
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (str[i].compareTo(str[j]) < 0) {
                        temp = str[i];
                        str[i] = str[j];
                        str[j] = temp;
                    }
                }
            }
            if (strS != str) {
                status = false;
            } else {
                status = true;
            }
        }

        if (typeSort == "Price: Low to High") {
            areJQueryAndJSLoadedSuccess(driver);
            List<WebElement> sortElement = driver.findElements(By.xpath(BaseUI.PRODUCT_PRICE));
            int count = sortElement.size();
            Long temp;
            Long strS[] = new Long[count];
            Long str[] = new Long[count];
            for (int i = 0; i < count; i++) {
                String strValue = sortElement.get(i).getText().replace("$", "").replace(",", "").replace(".00", "").trim();
                Long sortValue = Long.parseLong(strValue);
                strS[i] = sortValue;
                str[i] = sortValue;
            }

            for (int i = 0 ; i < count - 1; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (str[i] > str[j]) {
                        temp = str[j];
                        str[j] = str[i];
                        str[i] = temp;
                    }
                }
            }
            for (int i = 0; i < count; i++) {
                if (strS[i] != str[i]) {
                    status = false;
                    break;
                }
            }
        }

        if (typeSort == "Price: High to Low") {
            areJQueryAndJSLoadedSuccess(driver);
            List<WebElement> sortElement = driver.findElements(By.xpath(BaseUI.PRODUCT_PRICE));
            int count = sortElement.size();
            Long temp;
            Long strS[] = new Long[count];
            Long str[] = new Long[count];
            for (int i = 0; i < count; i++) {
                String strValue = sortElement.get(i).getText().replace("$", "").replace(",", "").replace(".00", "").trim();
                Long sortValue = Long.parseLong(strValue);
                strS[i] = sortValue;
                str[i] = sortValue;
            }

            for (int i = 0 ; i < count - 1; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (str[i] < str[j]) {
                        temp = str[j];
                        str[j] = str[i];
                        str[i] = temp;
                    }
                }
            }
            for (int i = 0; i < count; i++) {
                if (strS[i] != str[i]) {
                    status = false;
                    break;
                }
            }
        }

        return status;
    }

    public boolean isProductLessThanOrEqualTo(String valueNumber) {
        areJQueryAndJSLoadedSuccess(driver);
        List<WebElement> sortElement = driver.findElements(By.xpath(BaseUI.PRODUCT_TITLE_NAME));
        if(sortElement.size() <= Integer.parseInt(valueNumber)){
            return true;
        }
        return false;
    }

    public boolean isDisPlayNextIcon() {
       return isElementDisplayed(driver, SortPageUI.NEXT_ICON);
    }

    public boolean isDisPlayPreviousIcon() {
        return isElementDisplayed(driver, SortPageUI.PREVIOUS_ICON);
    }

    public void clickToPagerByText(String expValue) {
        waitForElementClickable(driver,SortPageUI.DYNAMIC_PAGER_BY_TEXT,expValue);
        clickToElement(driver,SortPageUI.DYNAMIC_PAGER_BY_TEXT,expValue);
    }

    public boolean isDisPlayPaging() {
        boolean status;
        areJQueryAndJSLoadedSuccess(driver);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
            waitForElementVisible(driver,SortPageUI.PAGING);
            status =true;
        } catch (Exception e) {
            status =false;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return status;
    }
}