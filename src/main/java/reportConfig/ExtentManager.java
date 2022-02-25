package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager { public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/nopcommerce-extent-report.html");
        System.setProperty("org.freemarker.loggerLibrary", "none");
        reporter.config().setReportName("Nopcommerce Report");
        reporter.config().getTheme();
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Name", "NopCommerce");
        extentReports.setSystemInfo("Author", "Sang Nguyen");
        return extentReports;
    }
}
