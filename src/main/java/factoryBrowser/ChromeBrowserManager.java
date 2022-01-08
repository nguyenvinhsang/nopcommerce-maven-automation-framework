package factoryBrowser;

import java.util.Collections;
import java.util.HashMap;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeBrowserManager implements IBrowser {
	@Override
	public WebDriver getBrowserDrive() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", true);
		options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-geolocation");

		HashMap<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("download.default_directory", GlobalConstants.getGlobalConstants().getDownloadFolderPath());
		options.setExperimentalOption("prefs", prefs);

		return new ChromeDriver(options);
	}
}
