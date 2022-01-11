package commons;

import lombok.Getter;
import utilities.DataUtil;

import java.io.File;

@Getter
public class GlobalConstants {
	private static GlobalConstants globalConstants;

	private GlobalConstants() {
	}

	public static synchronized GlobalConstants getGlobalConstants() {
		return (globalConstants == null) ? (new GlobalConstants()) : globalConstants;
	}

	private final String devAppUrl = "";
	private final String stagingAppUrl = "";
	private final String testingAppUrl = "";

	private final String shortTimeout = "2";
	private final String longTimeout = "30";


	private final String projectPath = System.getProperty("user.dir");
	private final String uploadFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String downloadFolderPath = projectPath + File.separator + "downloadFiles";
	private final String browserLogPath = projectPath + File.separator + "browserLogs" + File.separator;

	private final String browserStackUserName = "xxx";
	private final String browserStackAutomateKey = "xxx";
	private final String browserStackUrl = "https://" + browserStackUserName + ":" + browserStackAutomateKey
			+ "@hub-cloud.browserstask.com/wd/hub";

	private final String saucelapUserName = "xxx";
	private final String saucelapAutomateKey = "xxx";
	private final String saucelapUrl = "https://" + saucelapUserName + ":" + saucelapAutomateKey
			+ "@ondemand.eu-central-1.saucelaps.com:443/wd/hub";

	private final String crossBrowserUserName = "xxx";
	private final String crossBrowserAutomateKey = "xxx";
	private final String crossBrowserUrl = "https://" + crossBrowserUserName + ":" + crossBrowserAutomateKey + "";

	private final String lambdaUserName = "xxx";
	private final String lambdaAutomateKey = "xxx";
	private final String lambdaUrl = "https://" + lambdaUserName + ":" + lambdaAutomateKey + "";

}
