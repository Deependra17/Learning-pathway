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
public class SearchByLanguage {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void searchBookByLanguage(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        utils.Configuration config = new Configuration();
        WebElement search = driver.findElement(By.xpath(config.getInputField()));
        search.sendKeys("मेरो शरीर");
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

        List<String> allBookNames = BookLanguageUtil.getAllBookNames(driver, "//div[@class='AllBooksGrid_BooksContainer__43ZZb']");
        System.out.println("Names of all books:");
        for (String bookName : allBookNames) {
            System.out.println(bookName);
        }

        // Verify that all book names are in Nepali language
        BookLanguageUtil.verifyAllBookNamesAreNepali(allBookNames);

        // If no assertion failures occur, all book names are in Nepali
        System.out.println("All book names are in Nepali.");

        set.tearDown();
        ;
    }

}
