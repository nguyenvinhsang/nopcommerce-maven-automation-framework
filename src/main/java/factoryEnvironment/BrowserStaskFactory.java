package factoryEnvironment;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class BrowserStaskFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String osVersion;
	
	public BrowserStaskFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}
	
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser",browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstask.debug","true");
		capability.setCapability("project","xxxxx");
		capability.setCapability("resulution","1920x1080");
		capability.setCapability("name","Run on"+osName+"|"+osVersion+"|"+browserName);
		try {
			driver =new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()),capability);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
