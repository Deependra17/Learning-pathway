package filtersearch.bycategory;

import locators.FilterLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.*;

import java.util.concurrent.TimeUnit;

@Listeners(CustomListener.class)
public class SearchLocatorsBookByCategory {
    TestSetUp set = new TestSetUp();
    FilterLocators locate = new FilterLocators();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void bookSearchByCategoryTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        locate.filterSearchButton(browser);
        locate.selectCategory();
        String expectedCategory = locate.getExpectedCategory();
        Thread.sleep(3000);
        locate.showBooksButton();
        locate.clickOnBook();
        Thread.sleep(3000);
        String actualCategory = locate.verifyCategory();
        Assert.assertEquals(actualCategory, expectedCategory, "Category Does not match");
        locate.closeButton();
        set.tearDown();
    }
}
