package filtersearch.bylanguage;

import locators.FilterLocators;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import utils.*;


import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchLocatorsBookByLanguage {
    TestSetUp set = new TestSetUp();
    FilterLocators locate = new FilterLocators();
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
