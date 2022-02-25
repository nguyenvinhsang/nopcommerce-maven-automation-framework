package pageObjects.user.nopCommerce;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.MyAccountPageUI;


public class MyAccountPageObject extends BasePage {
    WebDriver driver;

    public MyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void clickToRadioButtonByName(String radioName) {
        waitForElementClickable(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO,radioName);
        checkToCheckBoxOrRadio(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO,radioName);
    }


    public void inputDataOfBirth(String txtDay, String txtMonth, String txtYear) {
        waitForElementClickable(driver, MyAccountPageUI.DROPDOWN_DAY_BIRTH);
        selectDropdownByValue(driver, MyAccountPageUI.DROPDOWN_DAY_BIRTH,txtDay);
        waitForElementClickable(driver, MyAccountPageUI.DROPDOWN_MONTH_BIRTH);
        selectDropdownByValue(driver, MyAccountPageUI.DROPDOWN_MONTH_BIRTH,txtMonth);
        waitForElementClickable(driver, MyAccountPageUI.DROPDOWN_YEAR_BIRTH);
        selectDropdownByValue(driver, MyAccountPageUI.DROPDOWN_YEAR_BIRTH,txtYear);
    }

    public boolean isSelectRadioButtonByName(String labelName) {
        waitForElementVisible(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO,labelName);
        return isElementSelected(driver, MyAccountPageUI.DYNAMIC_GENDER_RADIO,labelName);
    }

    public String getTextBoxValueByLabelName(String labelName) {
        waitForElementVisible(driver, BaseUI.DYNAMIC_TEXT_BOX_BY_LABEL,labelName);
        return  getElementAttribute(driver,BaseUI.DYNAMIC_TEXT_BOX_BY_LABEL,"value",labelName);
    }
    public void selectDropDownCountryAndState(String txtCountry, String txtState) {
        selectDropDownByLabel(driver,"Country",txtCountry);
        waitForElementVisible(driver, BaseUI.ICON_LOADING);
        waitForElementInvisible(driver, BaseUI.ICON_LOADING);
        selectDropDownByLabel(driver,"State",txtState);
    }

    public boolean checkDateOfBirth(String txtDay, String txtMonth, String txtYear) {
        waitForElementVisible(driver, MyAccountPageUI.DROPDOWN_DAY_BIRTH);
        waitForElementVisible(driver, MyAccountPageUI.DROPDOWN_MONTH_BIRTH);
        waitForElementVisible(driver, MyAccountPageUI.DROPDOWN_YEAR_BIRTH);
        String dayValue= getElementAttribute(driver, MyAccountPageUI.DROPDOWN_DAY_BIRTH,"value");
        String monthValue= getElementAttribute(driver, MyAccountPageUI.DROPDOWN_MONTH_BIRTH,"value");
        String yearValue= getElementAttribute(driver, MyAccountPageUI.DROPDOWN_YEAR_BIRTH,"value");
        if (dayValue.equals(txtDay) && monthValue.equals(txtMonth) && yearValue.equals(txtYear)){
            return true;
        }else {
            return false;
        }
    }


    public boolean verifyProductReview(String txtReviewTitle, String txtReviewText, String txtProductTitleName) {
        String reviewTitle= getElementText(driver, MyAccountPageUI.PRODUCT_REVIEW_TITLE).trim();
        String reviewText= getElementText(driver, MyAccountPageUI.PRODUCT_REVIEW_TEXT).trim();
        String productTitle= getElementText(driver, MyAccountPageUI.PRODUCT_TITLE).trim();
        if (reviewTitle.equals(txtReviewTitle) && reviewText.equals(txtReviewText) && productTitle.equals(txtProductTitleName) ){
            return true;
        }else{
            return false;
        }
    }
}
