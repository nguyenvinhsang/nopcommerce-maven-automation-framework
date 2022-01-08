package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLapFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;
	
	public SauceLapFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}
	public WebDriver createDriver() {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platformName", osName);
		capability.setCapability("browserName",browserName);
		capability.setCapability("browser_Version", "latest");
		capability.setCapability("name","Run on"+osName+"|"+browserName);
		
		Map<String, Object> sauceOptions = new HashMap<>();
		if (osName.contains("Windows")) {
			sauceOptions.put("screenResolution", "1920x1080");
		}else {
			sauceOptions.put("screenResolution", "1920x1440");
		}
		capability.setCapability("sauce:options", sauceOptions);
		
		try {
			driver =new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getSaucelapUrl()),capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
	
}
