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
import utils.RetryAnalyzer;
import utils.TestSetUp;

import java.time.Duration;

public class ValidLoginTest {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void loginTestWithValidCredentials(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        Thread.sleep(3000);
        try {
            String expectedTitle = "Let's Read | Children's Books | Free to Read Download Translate";
            String actualTitle = driver.getTitle();
            System.out.println("Actual Title: " + actualTitle);
            Assert.assertEquals(actualTitle, expectedTitle, "Title does not match");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
            WebElement logoElement = driver.findElement(By.xpath("//h1"));
            String expectedLogoText = "Let's Read";
            String actualLogoText = logoElement.getText();
            System.out.println("Logo text: " + actualLogoText);
            Assert.assertEquals(actualLogoText, expectedLogoText, "Text does not match");
        } finally {
            set.tearDown();
        }
    }
}
