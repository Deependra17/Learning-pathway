package search;

import locators.SearchLocators;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.*;

import java.time.Duration;

@Listeners(CustomListener.class)
public class SearchBySpecialCharacters {
    TestSetUp set = new TestSetUp();
    SearchLocators locator = new SearchLocators();
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
