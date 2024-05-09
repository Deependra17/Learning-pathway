package search;

import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BookLanguageUtil;
import utils.Configuration;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;
import java.util.List;

public class SearchByLanguageTest {

    LoginUtils loginUtils;
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
    }

    @Test
    public void searchBookByLanguage() throws InterruptedException {
        FilterSearchLocators locate = new FilterSearchLocators();
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

        List<String> allBookNames = BookLanguageUtil.getAllBookNames(driver, locate.getVerifyAllBooks());
        System.out.println("Names of all books:");
        for (String bookName : allBookNames) {
            System.out.println(bookName);
        }

        // Verify that all book names are in Nepali language
        BookLanguageUtil.verifyAllBookNamesAreNepali(allBookNames);

        // If no assertion failures occur, all book names are in Nepali
        System.out.println("All book names are in Nepali.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }

}
