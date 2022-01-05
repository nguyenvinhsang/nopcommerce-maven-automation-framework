package factoryEnvironment;

import org.openqa.selenium.WebDriver;

import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeBrowserManager;
import factoryBrowser.EdgeBrowserManager;
import factoryBrowser.FireFoxBrowserManager;
import factoryBrowser.HChromeBrowserManager;
import factoryBrowser.HFireFoxBrowserManager;
import factoryBrowser.SafariBrowserManager;

public class LocalFactory {

	private WebDriver driver;
	private String browserName;

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case CHROME:
			driver = new ChromeBrowserManager().getBrowserDriver();
			break;
		case FIREFOX:
			driver = new FireFoxBrowserManager().getBrowserDriver();
			break;
		case EDGE_CHROMIUM:
			driver = new EdgeBrowserManager().getBrowserDriver();
			break;
		case SAFARI:
			driver = new SafariBrowserManager().getBrowserDriver();
			break;
		case H_CHROME:
			driver = new HChromeBrowserManager().getBrowserDriver();
			break;
		case H_FIREFOX:
			driver = new HFireFoxBrowserManager().getBrowserDriver();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
