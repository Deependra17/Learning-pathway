package testcases.filtersearch.bycountryoforigin;

import locators.FilterSearchLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.TestSetUp;

public class SearchByCountryOfOriginTest {
    TestSetUp set = new TestSetUp();
    FilterSearchLocators locate = new FilterSearchLocators();
    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void searchBookByCountryOfOrigin(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        locate.filterSearchButton(browser);
        locate.ClickToChooseCountry();
        locate.selectRandomCountry();
        String expectedCountryOfOrigin = locate.expectedCountry();
        locate.showBooksButton();
        locate.clickOnRandomBook();
        String actualCountryOfOrigin = locate.getActualCountryOfOrigin();

        Assert.assertEquals(actualCountryOfOrigin, expectedCountryOfOrigin, "Country of origin does not match");
        set.tearDown();
    }
}
