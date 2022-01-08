package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class SafariBrowserManager implements IBrowser {
	@Override
	public WebDriver getBrowserDrive() {
		SafariOptions options = new SafariOptions();
		options.setCapability("safari.cleanSession", true);
		return new SafariDriver(options);
	}
}
