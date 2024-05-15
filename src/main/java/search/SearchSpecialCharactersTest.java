package search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.*;

import java.time.Duration;

@Listeners(CustomListener.class)
public class SearchSpecialCharactersTest {
    TestSetUp set = new TestSetUp();

    private WebDriver driver;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"browser"})
    public void searchWithSpecialCharacter(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        utils.Configuration config = new Configuration();
        WebElement search = driver.findElement(By.xpath(config.getInputField()));
        search.sendKeys("@#jdfskh%$");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        WebElement errorMessage = driver.findElement(By.xpath(config.getErrorMessage()));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
        set.tearDown();
    }
}
