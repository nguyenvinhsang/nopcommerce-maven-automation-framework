package pageObjects.user.nopCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.BaseUI;
import pageUIs.user.nopCommerce.HomePageIU;
import pageUIs.user.nopCommerce.LoginPageUI;


public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputEmailTextBox(String txtEmail) {
		waitForElementClickable(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, txtEmail);
	}

	public void inputPasswordTextBox(String txtPassWord) {
		waitForElementClickable(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, txtPassWord);
	}

	public HomePageObject clickToLoginButton(WebDriver driver) {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

    public String getMessageErrorTextBox(String idTextBox, String textBoxValue) {
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
			clickToLoginButton(driver);
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

    public String getMessageErrorLogin(String txtEmail, String txtPassWord) {
		String message="";
		waitForElementClickable(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, txtEmail);
		waitForElementClickable(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, txtPassWord);
		clickToLoginButton(driver);
		try {
			waitForElementVisible(driver,LoginPageUI.MESSAGE_ERROR_LOGIN_BUTTON);
			if (isElementDisplayed(driver,LoginPageUI.MESSAGE_ERROR_LOGIN_BUTTON)){
				message= getElementText(driver,LoginPageUI.MESSAGE_ERROR_LOGIN_BUTTON);
			}
		}catch (Exception e){
			message="";
		}
		return message;
    }

	public boolean isHomePageDisplay() {
		return isElementDisplayed(driver, HomePageIU.HOME_PAGE_SLIDER);
	}
}
