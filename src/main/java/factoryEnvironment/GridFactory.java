package factoryEnvironment;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GridFactory {
	private String browserName;
	private String ipAddress;
	private String portNumber;
	private WebDriver driver;
	private DesiredCapabilities capability;

	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}

	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		capability = null;
		System.out.println(browser);
		if (browser == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
//			capability = DesiredCapabilities .chrome();
			capability.setBrowserName("chrome");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.merge(capability);
			createRemoteWebDriver(chromeOptions);
		} else if (browser == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
//			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.merge(capability);
			createRemoteWebDriver(firefoxOptions);
		} else if (browser == BrowserList.EDGE_CHROMIUM) {
			WebDriverManager.chromedriver().setup();
//			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.merge(capability);
			createRemoteWebDriver(edgeOptions);
		} else if (browser == BrowserList.SAFARI) {
			driver = new SafariDriver();
//			capability = DesiredCapabilities.safari();
			capability.setBrowserName("safari");
			SafariOptions safariOptions = new SafariOptions();
			safariOptions.merge(capability);
			createRemoteWebDriver(safariOptions);
		} else if (browser == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
//			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(true);
			chromeOptions.addArguments("window-size=1920*1080");
			chromeOptions.merge(capability);
			createRemoteWebDriver(chromeOptions);
		} else if (browser == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
//			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setHeadless(true);
			firefoxOptions.addArguments("window-size=1920*1080");
			firefoxOptions.merge(capability);
			createRemoteWebDriver(firefoxOptions);
		} else {
			throw new BrowserNotSupportedException(browserName);
		}

		return driver;
	}
	
	private void createRemoteWebDriver(Capabilities option) {
		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)),
					option);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
