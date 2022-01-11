package pageObjects.user.nopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import org.openqa.selenium.WebElement;
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
		scrollToElementJs(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public HomePageObject clickToLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
		clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePage(driver);
	}

	public String getMessageErrorTextBox(String idTextBox, String textBoxValue){
		String messageEnter="";
		String messageClick="";
		String message ="";
		try {
			waitForElementClickable(driver,BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
			clickToElement(driver,BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
			driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_TEXT_BOX,idTextBox))).sendKeys(Keys.ENTER);
			waitForElementVisible(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
			messageEnter = getElementText(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox) ;
		}catch (Exception e){
			messageEnter = "Check message error fail";
		}

		if(textBoxValue=="" && messageEnter!="Check message error fail" ){
			messageEnter=messageEnter;
		}else if(textBoxValue!="" && messageEnter!="Check message error fail"){
			sendKeyToElement(driver,BaseUI.DYNAMIC_TEXT_BOX,textBoxValue,idTextBox);
			try {
				waitForElementVisibleShort(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
				if(isElementDisplayed(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox)){
					messageEnter=getElementText(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
				}else{
					messageEnter ="Element not displayed";
				}
			}catch (Exception e){
				messageEnter ="";
			}
		}

		try {
			refreshToPage(driver);
			waitForElementClickable(driver,BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
			sendKeyToElement(driver,BaseUI.DYNAMIC_TEXT_BOX,textBoxValue,idTextBox);
			clickToRegisterButton();
			waitForElementVisibleShort(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
			if(isElementDisplayed(driver, BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox)){
				messageClick=getElementText(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
			}else{
				messageClick ="Element not displayed";
			}
		}catch (Exception e){
			messageClick ="";
		}

		if (messageEnter.equals(messageClick)){
			message= messageEnter;
		}else {
			message ="message Click "+messageClick+", message Enter " + messageEnter ;
		}
		return message;
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


    public boolean checkErrorMessageTrueValueTextBoxByID(String idTextBox,String textValue) {
		boolean pass= true;
		try {
					waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_TEXT_BOX,idTextBox))).sendKeys(Keys.ENTER);
					waitForElementVisible(driver,BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox);
					WebElement elementActionSenKey = driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox)));
					waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					sendKeyToElement(driver,BaseUI.DYNAMIC_TEXT_BOX,textValue,idTextBox);
					driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_TEXT_BOX,idTextBox))).sendKeys(Keys.ENTER);
					waitForElementInvisibleOfElementLocated(driver,elementActionSenKey);

					refreshToPage(driver);
					clickToRegisterButton();
					waitForElementClickable(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					WebElement elementActionClick = driver.findElement(By.xpath(getDynamicLocator(BaseUI.DYNAMIC_ERROR_MESSAGE_TEXT_BOX,idTextBox)));
					clickToElement(driver, BaseUI.DYNAMIC_TEXT_BOX,idTextBox);
					sendKeyToElement(driver,BaseUI.DYNAMIC_TEXT_BOX,textValue,idTextBox);
					clickToRegisterButton();
					waitForElementInvisibleOfElementLocated(driver,elementActionClick);
		}catch (Exception e){
			pass=false;
		}
		return pass;
    }
}
