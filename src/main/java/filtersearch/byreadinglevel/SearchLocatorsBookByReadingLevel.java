package filtersearch.byreadinglevel;

import locators.FilterLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

public class SearchLocatorsBookByReadingLevel {
    TestSetUp set = new TestSetUp();
    FilterLocators locate = new FilterLocators();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void searchBookByReadingLevelTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        System.out.println("Test started..");
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        locate.filterSearchButton(browser);
        locate.readingLevel();
        String expectedReadingLevel = locate.getLevel();
        locate.showBooksButton();
        locate.clickOnBookForReadingLevel();
        String actualLevel = locate.getVerifyReadingLevel();
        Assert.assertEquals(actualLevel, expectedReadingLevel, "Reading level does not match");
        locate.closeButton();
        set.tearDown();
    }
}
