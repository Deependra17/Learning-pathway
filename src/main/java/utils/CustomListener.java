package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Finished");
    }
}
