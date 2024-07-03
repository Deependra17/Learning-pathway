package testcases.normalsearch;

import pages.NormalSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.time.Duration;

@Listeners(CustomListener.class)
public class SearchBySpecialCharacters {
    TestSetUp set = new TestSetUp();
    NormalSearchPage locator = new NormalSearchPage();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void searchWithSpecialCharacter(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        locator.specialCharacters(browser);
        set.tearDown();
    }
}
