package factoryEnvironment;

import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

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
			driver = new ChromeBrowserManager().getBrowserDrive();
			break;
		case FIREFOX:
			driver = new FireFoxBrowserManager().getBrowserDrive();
			break;
		case EDGE_CHROMIUM:
			driver = new EdgeBrowserManager().getBrowserDrive();
			break;
		case SAFARI:
			driver = new SafariBrowserManager().getBrowserDrive();
			break;
		case H_CHROME:
			driver = new HChromeBrowserManager().getBrowserDrive();
			break;
		case H_FIREFOX:
			driver = new HFireFoxBrowserManager().getBrowserDrive();
			break;
		default:
			throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
