package testcases.normalsearch;

import locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchByName {
    SearchLocators locate = new SearchLocators();
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void searchByName(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        locate.searchByName(browser);
        locate.bookElements();
        locate.verifyBookName();
        set.tearDown();
    }
}
