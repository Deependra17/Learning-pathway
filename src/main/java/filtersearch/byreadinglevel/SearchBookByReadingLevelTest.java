package filtersearch.byreadinglevel;

import filtersearch.FilterButton;
import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import java.util.concurrent.TimeUnit;

public class SearchBookByReadingLevelTest {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"browser"})
    public void searchBookByReadingLevelTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        System.out.println("Test started..");
        driver = DriverFactory.build(browser);
        FilterSearchLocators locate = new FilterSearchLocators();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();

        WebElement level = driver.findElement(By.xpath(locate.getLevel()));
        Thread.sleep(2000);
        String expectedLevel = level.getText();
        System.out.println("Expected Level: "+expectedLevel);
        level.click();

        WebElement showBook = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showBook.click();

        WebElement book = driver.findElement(By.xpath(locate.getClickOnBook()));
        book.click();
        Thread.sleep(3000);
        WebElement verify = driver.findElement(By.xpath(locate.getVerifyLevel()));
        String actualLevel = verify.getText();
        System.out.println("Actual Level: " + actualLevel);
        Thread.sleep(2000);
        Assert.assertEquals(actualLevel, expectedLevel, "Level does not match");
        System.out.println("Level Matched");

        WebElement closeButton = driver.findElement(By.xpath(locate.getCloseButton()));
        closeButton.click();
        set.tearDown();
    }
}
