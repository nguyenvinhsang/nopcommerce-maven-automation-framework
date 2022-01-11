package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.user.nopCommerce.BaseUI;

public class BasePage {

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public Alert waitForAlertPresent(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresent(driver);
		alert.accept();
	}

	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresent(driver);
		alert.dismiss();
	}

	public void sendKesyToAlert(WebDriver driver, String expValue) {
		alert = waitForAlertPresent(driver);
		alert.sendKeys(expValue);
	}

	public String getTextAlert(WebDriver driver, String expValue) {
		alert = waitForAlertPresent(driver);
		return alert.getText();
	}

	public void switchToWindowByID(WebDriver driver, String prentID) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			if (!id.equals(prentID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String exTitle) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			driver.switchTo().window(id);
			// driver.findElement(By.xpath("(//title)[1]"));
			// explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//title)[1]")));
			String title = driver.getTitle();
			if (title.equals(exTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowExceptPrentID(WebDriver driver, String exParentID) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			driver.switchTo().window(id);
			// driver.findElement(By.xpath("(//title)[1]"));
			// explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//title)[1]")));
			if (!id.equals(exParentID)) {
				driver.close();
			}

			if (driver.getWindowHandles().size() == 1) {
				break;
			}

		}
	}

	public void closeAllWindowExceptPrentTitle(WebDriver driver, String exTitle) {

		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			driver.switchTo().window(id);
			String title = driver.getTitle();
			if (!title.equals(exTitle)) {
				driver.close();
			}
			if (driver.getWindowHandles().size() == 1) {
				break;
			}
		}
	}

	public void sleepInTime(int s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public WebElement getElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}

	public List<WebElement> getElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}

	public int getElementSize(WebDriver driver, String locator) {
		return getElements(driver, locator).size();
	}

	public By getByXpath(String locator) {
		return By.xpath(locator);
	}

	public By getByCssSelector(String locator) {
		return By.cssSelector(locator);
	}

	public By getByID(String locator) {
		return By.id(locator);
	}

	public By getByName(String locator) {
		return By.name(locator);
	}

	public By getByTagName(String locator) {
		return By.tagName(locator);
	}

	public By getByLinkText(String locator) {
		return By.linkText(locator);
	}

	public void clickToElement(WebDriver driver, String locator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(2);
		} else {
			getElement(driver, locator).click();
		}
	}

	public void clickToElement(WebDriver driver, String locator, String... params) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, getDynamicLocator(locator, params));
			sleepInSecond(2);
		} else {
			getElement(driver, getDynamicLocator(locator, params)).click();
		}
	}

	public void sendKeyToElement(WebDriver driver, String locator, String exValue) {
		getElement(driver, locator).clear();
		getElement(driver, locator).sendKeys(exValue);
	}

	public void sendKeyToUploadElement(WebDriver driver, String locator, String exValue) {
		getElement(driver, locator).sendKeys(exValue);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String exValue, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).clear();
		getElement(driver, getDynamicLocator(locator, params)).sendKeys(exValue);
	}

	public void sendKeyBoardToElement(WebDriver driver, String locator, Keys exValue, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).clear();
		getElement(driver, getDynamicLocator(locator, params)).sendKeys(exValue);
	}

	public void selectItemInDropdown(WebDriver driver, String locator) {
		if (driver.toString().contains("internet explorer")) {
			clickToElementByJS(driver, locator);
			sleepInSecond(2);
		} else {
			getElement(driver, locator).click();
		}
	}

	public void selectDropdownByText(WebDriver driver, String locator, String exValue) {
		select = new Select(getElement(driver, locator));
		select.selectByVisibleText(exValue);
	}

	public void selectDropdownByText(WebDriver driver, String locator, String exValue, String... params) {
		select = new Select(getElement(driver, getDynamicLocator(locator, params)));
		select.selectByVisibleText(exValue);
	}

	public String getSelectedItemDropdown(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		select = new Select(getElement(driver, locator));
		return select.isMultiple();
	}

	public void sleepInSecond(int s) {
		try {
			Thread.sleep(s * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator,
			String expectedItem) {
		getElement(driver, parentLocator).click();
		sleepInSecond(1);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				scrollToElementByWebElementJs(driver, item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void scrollToElementJs(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
	}

	public void scrollToElementJs(WebDriver driver, String locator,String...params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(driver, getDynamicLocator(locator, params)));
	}

	public void scrollToElementByWebElementJs(WebDriver driver, WebElement element) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String getElementAttribute(WebDriver driver, String locator, String exAttributeName) {
		return getElement(driver, locator).getAttribute(exAttributeName);
	}

	public String getElementAttribute(WebDriver driver, String locator, String exAttributeName, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).getAttribute(exAttributeName);
	}

	public String getElementText(WebDriver driver, String locator) {
		return getElement(driver, locator).getText();
	}

	public String getElementText(WebDriver driver, String locator, String... pareams) {
		return getElement(driver, getDynamicLocator(locator, pareams)).getText();
	}

	public void checkToCheckBoxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, locator);
			} else {
				getElement(driver, locator).click();
			}
		}
	}

	public void unCheckToCheckBox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			if (driver.toString().contains("internet explorer")) {
				clickToElementByJS(driver, locator);
			} else {
				getElement(driver, locator).click();
			}
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		return getElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getElement(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return getElement(driver, locator).isSelected();
	}

	public WebDriver switchToIframeByElement(WebDriver driver, String locator) {
		return driver.switchTo().frame(getElement(driver, locator));
	}

	public WebDriver switchToDefaultContent(WebDriver driver) {
		return driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getElement(driver, locator)).perform();
	}

	public void hoverAndClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.clickAndHold(getElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getElement(driver, locator)).perform();
	}

	public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
	}

	public void pressKeyElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, locator), key).perform();
	}

	public void pressKeyElement(WebDriver driver, String locator, Keys key, String... params) {
		action = new Actions(driver);
		action.sendKeys(getElement(driver, getDynamicLocator(locator, params)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getElement(driver, locator));
	}

	public void sendKeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String... params) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getElement(driver, getDynamicLocator(locator, params)));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.visibilityOf(getElement(driver, locator)));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.visibilityOf(getElement(driver, getDynamicLocator(locator, params))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(getElements(driver, locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(getElement(driver, getDynamicLocator(locator, params))));

	}

	public void waitForElementToBeSelected(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait
				.until(ExpectedConditions.elementToBeSelected(getElement(driver, getDynamicLocator(locator, params))));
	}

	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getElement(driver, locator)));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.invisibilityOf(getElement(driver, locator)));
	}

	public void waitForElementInvisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.invisibilityOf(getElement(driver, getDynamicLocator(locator, params))));
	}

	public void waitForElementStaleness(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.stalenessOf(element));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getElements(driver, locator)));
	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
	}

	//Nopcommerce

	public void clickToHeaderLinkByText(WebDriver driver,String...nameLink){
		waitForElementClickable(driver, BaseUI.DYNAMIC_HEADER_LINK,nameLink);
		clickToElement(driver, BaseUI.DYNAMIC_HEADER_LINK,nameLink);
	}

	public void sendKeyBoardToElementNoClear(WebDriver driver, String locator, Keys exValue, String... params) {
		getElement(driver, getDynamicLocator(locator, params)).sendKeys(exValue);
	}

	public void waitForElementInvisibleOfElementLocated(WebDriver driver,WebElement element) {
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}
	public void waitForElementVisibleOfElementLocated(WebDriver driver,WebElement element) {
		timeOut = Long.parseLong(GlobalConstants.getGlobalConstants().getShortTimeout());
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.visibilityOf(element));
		timeOut = Long.parseLong(GlobalConstants.getGlobalConstants().getLongTimeout());
	}

	public void waitForElementVisibleShort(WebDriver driver,String locator, String...params) {
		timeOut = Long.parseLong(GlobalConstants.getGlobalConstants().getShortTimeout());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		explicitWait.until(ExpectedConditions.visibilityOf(getElement(driver, getDynamicLocator(locator, params))));
		timeOut = Long.parseLong(GlobalConstants.getGlobalConstants().getLongTimeout());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}






	private long timeOut= Long.parseLong(GlobalConstants.getGlobalConstants().getLongTimeout());

	private WebDriverWait explicitWait;

	private Alert alert;

	private Select select;

	private JavascriptExecutor jsExecutor;

	private Actions action;
}
