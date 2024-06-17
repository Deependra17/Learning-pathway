package testcases.normalsearch;

import locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.time.Duration;

@Listeners(CustomListener.class)
public class EmptySearch {
    SearchLocators locate = new SearchLocators();
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void emptySearch(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        locate.emptySearch(browser);
        locate.booksName();
        set.tearDown();
    }
}
