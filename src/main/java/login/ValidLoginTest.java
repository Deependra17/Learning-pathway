package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;

public class ValidLoginTest {
    LoginUtils loginUtils;
    private WebDriver driver;


    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        Thread.sleep(2000);
    }

    @Test(threadPoolSize = 2, invocationCount = 2)
    public void loginTestWithValidCredentials() {

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
        String expectedLogoText = "Let's Read";
        String actualLogoText = logoElement.getText();
        System.out.println("Logo text: " + actualLogoText);
        Assert.assertEquals(actualLogoText, expectedLogoText, "Text does not match");

    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null) {
            driver.close();
        }
    }
}
