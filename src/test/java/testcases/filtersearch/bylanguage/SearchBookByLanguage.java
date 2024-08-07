package testcases.filtersearch.bylanguage;

import pages.FilterSearchPage;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchBookByLanguage {
    TestSetUp set = new TestSetUp();
    FilterSearchPage locate = new FilterSearchPage();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void bookSearchByLanguageTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        locate.filterSearchButton(browser);
        locate.selectLanguage();
        locate.showBooksButton();
        locate.verifyAllBooks();
        set.tearDown();
    }
}
