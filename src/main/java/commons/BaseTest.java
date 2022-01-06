package commons;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.Reporter;

import factoryEnvironment.BrowserStaskFactory;
import factoryEnvironment.CrossBrowserFactory;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LambdaFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SauceLapFactory;

import static reportConfig.ExtentTestManager.getTest;


public abstract class BaseTest {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	protected final Logger log;
	protected BaseTest() {
		log = LogManager.getLogger(getClass());
	}

	private enum ENVIRONMENT {
		LOCAL, GRID, BROWSERSTACK, SAUCELAP, CROSSBROWSER, LAMBDA
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	protected WebDriver getBrowserDriver(String browserName, String appUrl, String envName, String ipAddress,
			String portNumber, String osName, String osVersion) {
		ENVIRONMENT environment = ENVIRONMENT.valueOf(envName.toUpperCase());
		switch (environment) {
		case LOCAL:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		case GRID:
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
			break;
		case BROWSERSTACK:
			driver.set(new BrowserStaskFactory(browserName, osName, osVersion).createDriver());
			break;
		case SAUCELAP:
			driver.set(new SauceLapFactory(browserName, osName).createDriver());
			break;
		case CROSSBROWSER:
			driver.set(new CrossBrowserFactory(browserName, osName).createDriver());
			break;
		case LAMBDA:
			driver.set(new LambdaFactory(browserName, osName).createDriver());
			break;
		default:
			driver.set(new LocalFactory(browserName).createDriver());
			break;
		}
		driver.get().manage().timeouts().implicitlyWait(
				Duration.ofSeconds(Long.parseLong(GlobalConstants.getGlobalConstants().getLongTimeout())));
		driver.get().get(appUrl);
		driver.get().manage().window().maximize();
		return driver.get();

	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
				getTest().log(Status.FAIL, "-------------------------- FAILED --------------------------");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry Logging : logList) {
				log.info("---------------------" + Logging.getLevel().toString() + "---------------------\n"
						+ Logging.getMessage());
			}

		}
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
				getTest().log(Status.FAIL, "-------------------------- FAILED --------------------------");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected void logInfo(String logDetail){
		log.info(logDetail);
		getTest().log(Status.INFO, logDetail);
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
			getTest().log(Status.INFO, "-------------------------- PASSED --------------------------");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			Reporter.getCurrentTestResult().setThrowable(e);
			String scrBase64 = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.BASE64);
			String imgBase64= "<img src="+"data:image/png;base64,"+scrBase64+" /"+">";
			getTest().log(Status.FAIL, "-------------------------- FAILED --------------------------"+"</br>"+Reporter.getCurrentTestResult().getThrowable()+"</br>"+imgBase64);
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void cleanBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.get().toString().toLowerCase();
			// String driverInstanceName =browserName.toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}
			if (driver != null) {
				driver.get().manage().deleteAllCookies();
				driver.get().quit();
				driver.remove();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}