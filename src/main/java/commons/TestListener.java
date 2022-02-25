package commons;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.*;
import org.testng.internal.Utils;
import reportConfig.ExtentManager;

import java.util.List;

import static reportConfig.ExtentTestManager.getTest;

public class TestListener extends BaseTest implements ITestListener,IInvokedMethodListener {
    private WebDriver driver;
    private static       int count  = 0;
    private final static int maxTry = 1;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("Start method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
    }
    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("Finish method " + iTestContext.getName());
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is starting.");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is succeed.");
        getTest().log(Status.PASS, "Test "+getTestMethodName(iTestResult)+" passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is failed.");
        getTest().log(Status.FAIL, "Test "+ getTestMethodName(iTestResult) +" Failed");
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(getTestMethodName(iTestResult) + " test is skipped.");
        getTest().log(Status.SKIP, "Test "+getTestMethodName(iTestResult)+" Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("Before invocation of " + method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        log.debug("After invocation of " + method.getTestMethod().getMethodName());
        Reporter.setCurrentTestResult(result);
        if (method.isTestMethod()) {
            VerificationFailures allFailures = VerificationFailures.getFailures();
            if (result.getThrowable() != null) {
                allFailures.addFailureForTest(result, result.getThrowable());
            }
            List<Throwable> failures = allFailures.getFailuresForTest(result);
            int size = failures.size() - 1;
            if (size > 0) {
                result.setStatus(ITestResult.FAILURE);
                if (size == 1) {
                    result.setThrowable(failures.get(0));
                } else {
                    StringBuffer message = new StringBuffer("Multiple failures (").append(size).append("):\n");
                    for (int failure = 0; failure < size - 1; failure++) {
                        message.append("Failure ").append(failure + 1).append(" of ").append(size).append("\n");
                        message.append(Utils.longStackTrace(failures.get(failure), false)).append("\n");
                    }
                    Throwable last = failures.get(size - 1);
                    message.append("Failure ").append(size).append(" of ").append(size).append("\n");
                    message.append(last.toString());
                    Throwable merged = new Throwable(message.toString());
                    merged.setStackTrace(last.getStackTrace());
                    result.setThrowable(merged);
                }
            }
        }
    }


    private static final Logger log = LogManager.getLogger(TestListener.class);
}