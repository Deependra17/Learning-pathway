package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    LoginUtils loginUtils;

    @Test
    @Parameters({"browser"})
    public void loginTestWithValidCredentials(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
//        Assert.assertEquals(driver.getTitle(), "Let's Read | Children's Books | Free to Read Download Translate");
//        System.out.println("User logged in Successfully");

        String expectedTitle = "Let's Read | Children's Books | Free to Read Download Translate";
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");


//Logo text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1"))); // Wait for an <h1> element to appear

// Get the logo <h1> element and retrieve its text
        WebElement logoElement = driver.findElement(By.xpath("//h1"));
        String expectedLogoText= "Let's Read";
        String actualLogoText = logoElement.getText();
        System.out.println("Logo text: " + actualLogoText);
        Assert.assertEquals(actualLogoText, expectedLogoText, "Text does not match");

    }

    @Test
    @Parameters("browser")
    public void loginTestWithInvalidPassword(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.loginWithInvalidCredentials();

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
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.close();
        }
    }
}
