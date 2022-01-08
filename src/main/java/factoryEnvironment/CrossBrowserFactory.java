package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CrossBrowserFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	
	public CrossBrowserFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", osName);
		capability.setCapability("browserName",browserName);
		capability.setCapability("record_video", "true");
		capability.setCapability("name","Run on"+osName+"|"+browserName);
		
		if (osName.contains("Windows")) {
			capability.setCapability("screenResolution", "1920x1080");
		}else {
			capability.setCapability("screenResolution", "1920x1440");
		}
		capability.setCapability("name", "Run on"+osName+"|"+browserName);
		
		try {
			driver =new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getCrossBrowserUrl()),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
}

