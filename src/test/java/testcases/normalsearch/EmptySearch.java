package testcases.normalsearch;

import pages.NormalSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.time.Duration;

@Listeners(CustomListener.class)
public class EmptySearch {
    NormalSearchPage locate = new NormalSearchPage();
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void emptySearch(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        locate.emptySearch(browser);
        locate.booksName();
        set.tearDown();
    }
}
