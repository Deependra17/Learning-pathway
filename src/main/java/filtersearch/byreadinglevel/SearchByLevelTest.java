package filtersearch.byreadinglevel;

import filtersearch.FilterButton;
import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;

public class SearchByLevelTest {
    LoginUtils loginUtils;
    private  WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        Thread.sleep(3000);
    }

    @Test
    public void searchBuBookLevel() throws InterruptedException {
        FilterButton clickButton = new FilterButton(driver);
        clickButton.clickOnFilterButton();
        FilterSearchLocators locate = new FilterSearchLocators();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement level = driver.findElement(By.xpath(locate.getLevel()));
        Thread.sleep(2000);
        String expectedLevel = level.getText();
        System.out.println("Expected Level: " + expectedLevel);
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

        WebElement closeButton = driver.findElement(By.xpath(locate.getCloseButton()));
        closeButton.click();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
