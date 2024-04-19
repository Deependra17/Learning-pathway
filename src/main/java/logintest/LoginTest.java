package logintest;

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
        Assert.assertEquals(driver.getTitle(), "Let's Read | Children's Books | Free to Read Download Translate");
        System.out.println("User logged in Successfully");

        String expectedTitle= "Let's Read | Children's Books | Free to Read Download Translate";
        String actualTitle= driver.getTitle();
        System.out.println("Actual Title: "+actualTitle);
        Assert.assertEquals(actualTitle,expectedTitle, "Title does not match");
    }

    @Test
    @Parameters("browser")
    public void loginTestWithInvalidUsername(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.loginWithInvalidCredentials();

//        WebElement Error = driver.findElement(By.xpath(""));
//        String actualError = Error.getText();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement actualError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Error message')]")));


        System.out.println("Error Message: " + actualError);
        String expectedError = "Couldn't find your Google Account";

        Assert.assertTrue(actualError.isDisplayed());

        Assert.assertEquals(actualError, expectedError, "Not Matched");
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }
}
