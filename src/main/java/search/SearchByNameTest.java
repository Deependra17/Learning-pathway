package search;

import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchByNameTest {

    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"browser"})
    public void searchByName(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        utils.Configuration config = new Configuration();
        long startTime = System.currentTimeMillis();

        WebElement search = driver.findElement(By.xpath(config.getInputField()));
        search.sendKeys("My Body");
        search.sendKeys(Keys.ENTER);
        long endTime = System.currentTimeMillis();

        // Calculate response time
        long responseTime = endTime - startTime;
        System.out.println("Response Time: " + responseTime + " ms");
        Thread.sleep(5000);
        // Record end time

        String expectedName = "My Body";
        String actualName = driver.findElement(By.xpath(config.getBookName())).getText();
        Assert.assertEquals(actualName, expectedName, "Book name does not match");
        Thread.sleep(5000);


        List<WebElement> bookElements = driver.findElements(By.xpath(config.getBookElements()));
        int bookCount = bookElements.size();

        System.out.println("Total count of books: " + bookCount);
        System.out.println("Book Names:");
        for (WebElement bookElement : bookElements) {
            String bookName = bookElement.getText();
            System.out.println(bookName);
        }

// Check if "My Body" is present
        boolean isMyBodyPresent = false;
        for (WebElement bookElement : bookElements) {
            if (bookElement.getText().contains("My Body")) {
                isMyBodyPresent = true;
                break;
            }
        }

        if (isMyBodyPresent) {
            System.out.println("Book 'My Body' is present in the list.");
        } else {
            System.out.println("Book 'My Body' is not present in the list.");
        }
        set.tearDown();
    }
}
