package search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import utils.*;

import java.time.Duration;
import java.util.List;
@Listeners(CustomListener.class)
public class EmptySearchTest {

    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test
    @Parameters({"browser"})
    public void emptySearch(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        utils.Configuration config = new Configuration();
        WebElement search = driver.findElement(By.xpath(config.getInputField()));
        search.sendKeys(" ");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        List<WebElement> bookElements = driver.findElements(By.xpath(config.getBookElements()));
        int bookCount = bookElements.size();

        System.out.println("Total count of books: " + bookCount);
        System.out.println("Book Names:");
        for (WebElement bookElement : bookElements) {
            String bookName = bookElement.getText();
            System.out.println(bookName);
        }
        set.tearDown();
    }
}
