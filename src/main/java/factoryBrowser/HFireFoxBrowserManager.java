package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HFireFoxBrowserManager implements IBrowser {
	@Override
	public WebDriver getBrowserDrive() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		firefoxOptions.setHeadless(true);
		firefoxOptions.addArguments("window-size=1920*1080");
		return new FirefoxDriver(firefoxOptions);
	}
}
