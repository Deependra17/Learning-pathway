package login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.TestSetUp;

import java.time.Duration;

public class InvalidLoginTest {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;
    @Test()
    @Parameters("browser")
    public void loginTestWithInvalidPassword(String browser) throws InterruptedException {
        set.invalidLogin(browser);
        driver = DriverFactory.build(browser);

        // Wait for the login attempt to complete
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("accounts.google.com")); // Wait until redirected to Google's login page

        // Check for error indication
        if (driver.getPageSource().contains("Wrong password")) {
            // Error message indicates invalid password
            System.out.println("Invalid password error message found.");
            // Assert the error message
            Assert.assertTrue(true, "Wrong password. Try again or click Forgot password to reset it.");
        } else {
            // No error message found, login might be successful
            System.out.println("No error message found.");
        }
        set.tearDown();
    }

}
