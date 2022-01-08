package pageObjects.user.nopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.RegisterPageUI;

import java.security.Key;


public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToMaleRadio() {
		waitForElementClickable(driver, RegisterPageUI.GENDRE_MALE_RADIO);
		clickToElement(driver, RegisterPageUI.GENDRE_MALE_RADIO);
	}

	public void inputFristNameTextBox(String fistName) {
		waitForElementVisible(driver, RegisterPageUI.FISTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FISTNAME_TEXTBOX, fistName);
	}

	public void inputLastNameTextBox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void inputEmailTextBox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void inputPasswordTextBox(String passWord) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, passWord);
	}

	public void inputConfirmPasswordTextBox(String confirmPassWord) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassWord);
	}

	public boolean isSuccessMessageDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.SUCCESS_MESSAGE);
		return isElementDisplayed(driver, RegisterPageUI.SUCCESS_MESSAGE);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public HomePageObject clickToLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String checkMessageByTextBoxID(String idTextBox){
		String check="";
		waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
		clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
		sendKeyBoardToElement(driver, BaseUI.DYNAMIC_TEXT_BOX, Keys.ENTER,idTextBox);
		waitForElementVisible(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
		check =getElementText(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
		String check2 =checkMessageCLick(idTextBox,"");
		if (check2.equals(check)){
			return check;
		}else {
			check ="false";
		}
		return check;
	}

	public String checkMessageByTextBoxID(String idTextBox,String txtValue){
		String check="";
		waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
		clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
		sendKeyToElement(driver, BaseUI.DYNAMIC_TEXT_BOX, txtValue,idTextBox);
		driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_TEXT_BOX,idTextBox))).sendKeys(Keys.ENTER);
		waitForElementVisible(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
		check =getElementText(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
		String check2 =checkMessageCLick(idTextBox,txtValue);
		if (check2.equals(check)){
			return check;
		}else {
			check ="false";
		}
		return check;
	}

	private String checkMessageCLick(String idTextBox,String txtValue) {
		if (txtValue == "") {
			refreshToPage(driver);
			clickToRegisterButton();
			waitForElementVisible(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX, idTextBox);
			return getElementText(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX, idTextBox);
		} else {
			refreshToPage(driver);
			waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX, idTextBox);
			clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX, idTextBox);
			sendKeyToElement(driver, BaseUI.DYNAMIC_TEXT_BOX, txtValue, idTextBox);
			clickToRegisterButton();
			waitForElementVisible(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX, idTextBox);
			return getElementText(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX, idTextBox);
		}
	}

	public String getMessageErrorTextBoxByID(String idTextBox){
		waitForElementVisible(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
		return  getElementText(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
	}

	public String getMessageEmailAlreadyExits() {
		waitForElementVisible(driver,RegisterPageUI.MESSAGE_EMAIL_ALREADY_EXITS);
		return  getElementText(driver,RegisterPageUI.MESSAGE_EMAIL_ALREADY_EXITS);
	}



	public boolean isDisplayErrorPasWordLessThan6Char() {
		waitForElementVisible(driver,RegisterPageUI.MESSAGE_ERROR_PASSWORD_RULE);
		waitForElementVisible(driver,RegisterPageUI.MESSAGE_ERROR_PASSWORD_LESS_THAN_6_CHAR);
		if (driver.findElement(By.xpath(RegisterPageUI.MESSAGE_ERROR_PASSWORD_RULE)).isDisplayed() && driver.findElement(By.xpath(RegisterPageUI.MESSAGE_ERROR_PASSWORD_LESS_THAN_6_CHAR)).isDisplayed()){
			return true;
		}else {
		return false;
		}
	}


}
