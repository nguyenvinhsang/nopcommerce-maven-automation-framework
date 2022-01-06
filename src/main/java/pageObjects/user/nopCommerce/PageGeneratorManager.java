package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {

	private static OrdersPageObject ordersPage;
	private static NewPageObject newPage;
	public static SearchPageObject searchPage;

	private PageGeneratorManager() {
	}

	public static HomePageObject getHomePage(WebDriver driver) {
		return new HomePageObject(driver);
	}

	public static RegisterPageObject getRegisterPage(WebDriver driver) {

		return new RegisterPageObject(driver);
	}

	public static LoginPageObject getLoginPage(WebDriver driver) {

		return new LoginPageObject(driver);
	}

	public static OrdersPageObject getOrdersPage(WebDriver driver) {
		return new OrdersPageObject(driver);
	}

	public static NewPageObject getNewPage(WebDriver driver) {
		return new NewPageObject(driver);
	}

	public static SearchPageObject getSearchPage(WebDriver driver) {
		return new SearchPageObject(driver);
	}

}
