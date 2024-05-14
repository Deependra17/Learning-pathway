package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY_COUNT = 1; // Maximum number of retry attempts
    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true; // Retry the test method
        }
        return false; // Do not retry further
    }
}
