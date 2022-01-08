package factoryBrowser;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FireFoxBrowserManager implements IBrowser {


	@Override
	public WebDriver getBrowserDrive() {
		WebDriverManager.firefoxdriver().setup();
		FirefoxOptions options =new FirefoxOptions();
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.getGlobalConstants().getBrowserLogPath()+"firefox.log");

		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.getGlobalConstants().getDownloadFolderPath());
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("pdfjs.disabled", true);

		return new FirefoxDriver(options);
	}
}
