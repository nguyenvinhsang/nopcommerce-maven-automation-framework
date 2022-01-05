package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.nopCommerce.HomePageIU;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public RegisterPageObject clickToRegisterLink(WebDriver driver) {
		waitForElementClickable(driver, HomePageIU.REGISTER_LINK);
		clickToElement(driver, HomePageIU.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
		
	}

	public boolean isHomePageSliderDisplayed() {
		//waitForElementVisible(driver, HomePageIU.HOMEPAGE_SLIDER);
		return isElementDisplayed(driver, HomePageIU.HOMEPAGE_SLIDER);
	}

	public LoginPageObject clickToLoginLink(WebDriver driver) {
		waitForElementClickable(driver, HomePageIU.LOGIN_LINK);
		clickToElement(driver, HomePageIU.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

}
