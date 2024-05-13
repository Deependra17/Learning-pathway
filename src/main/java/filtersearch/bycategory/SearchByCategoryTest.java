package filtersearch.bycategory;

import filtersearch.FilterButton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import filtersearch.FilterSearchLocators;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchByCategoryTest {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test
    @Parameters({"browser"})
    public void bookSearchByCategoryTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        System.out.println("Test started..");
        driver = DriverFactory.build(browser);
        FilterSearchLocators locate = new FilterSearchLocators();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();

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

        set.tearDown();
    }
}
