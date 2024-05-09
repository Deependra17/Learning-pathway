package filtersearch.bycategory;

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

import java.time.Duration;


public class SearchByCategoryTest {
    LoginUtils loginUtils;
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        Thread.sleep(2000);

        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();
    }

    @Test()
    public void bookSearchByCategoryTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        FilterSearchLocators locate = new FilterSearchLocators();

        WebElement category = driver.findElement(By.xpath(locate.getSelectCategory()));
        Thread.sleep(3000);
        String expectedCategory = category.getText();
        System.out.println("Expected Category: " + expectedCategory);
        category.click();

        WebElement showBook = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showBook.click();

        WebElement book = driver.findElement(By.xpath(locate.getClickOnBook()));
        book.click();
        Thread.sleep(3000);

        WebElement verify = driver.findElement(By.xpath(locate.getVerifyCategory()));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verify);
        String actualCategory = verify.getText();
        System.out.println("Actual Category: " + actualCategory);
        Thread.sleep(2000);

        Assert.assertEquals(actualCategory, expectedCategory, "Category does not match");
        System.out.println("Category Matched");
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
