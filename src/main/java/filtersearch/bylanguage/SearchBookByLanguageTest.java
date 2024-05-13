package filtersearch.bylanguage;

import filtersearch.FilterButton;
import filtersearch.FilterSearchLocators;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import utils.*;


import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Listeners(CustomListener.class)
public class SearchBookByLanguageTest {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void bookSearchByLanguage(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        System.out.println("Test started..");
        driver = DriverFactory.build(browser);
        FilterSearchLocators locate = new FilterSearchLocators();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();

        WebElement languageDropdown = driver.findElement(By.xpath(locate.getSelectLanguage()));
        languageDropdown.click();

        WebElement nepaliOption = driver.findElement(By.xpath(locate.getChooseLanguage()));
        nepaliOption.click();
        Thread.sleep(5000);

        WebElement showBooks = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showBooks.click();
        Thread.sleep(4000);

        // Get all book elements and extract their names
//        List<WebElement> bookElements = driver.findElements(By.xpath(locate.getVerifyAllBooks()));
        List<String> allBookNames = BookLanguageUtil.getAllBookNames(driver, locate.getVerifyAllBooks());
        System.out.println("Names of all books:");
        for (String bookName : allBookNames) {
            System.out.println(bookName);
        }

        // Verify that all book names are in Nepali language
        BookLanguageUtil.verifyAllBookNamesAreNepali(allBookNames);

        // If no assertion failures occur, all book names are in Nepali
        System.out.println("All book names are in Nepali.");

        set.tearDown();
    }

}
