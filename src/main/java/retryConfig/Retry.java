package retryConfig;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.util.RetryAnalyzerCount;

import java.util.concurrent.atomic.AtomicInteger;

public class Retry extends RetryAnalyzerCount {
    private static final int MAX_RETRY_ATTEMPTS = 1;
    private AtomicInteger counter = new AtomicInteger(1); //used only for logging purposes

    public Retry() {
        setCount(MAX_RETRY_ATTEMPTS);
    }

    @Override
    public boolean retryMethod(ITestResult result) {
        // log example: [15/04/20 13:31] WARN [RetryAnalyzer] RETRY failed test 'displaySearchResultForValidInput' (1 out of 3 times)
        String methodName = result.getMethod().getMethodName();
        log.warn("RETRY failed test '{}' ({} out of {} times)",
                methodName,
                this.counter.getAndIncrement(),
                MAX_RETRY_ATTEMPTS);
        // enough is only the return statement
        return true;
    }
    private static final Logger log = LogManager.getLogger(Retry.class);

}