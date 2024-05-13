package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class TestSetUp {
    private WebDriver driver;
    private LoginUtils loginUtils;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        System.out.println("Login successfully..");
        Thread.sleep(2000);
    }
    @BeforeMethod
    @Parameters({"browser"})
    public void invalidLogin(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        loginUtils = new LoginUtils(driver);
        loginUtils.loginWithInvalidCredentials();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
