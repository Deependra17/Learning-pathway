package testcases.filtersearch.byaudio;

import locators.FilterSearchLocators;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.RetryAnalyzer;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

public class SearchBookByAudio {

    TestSetUp set = new TestSetUp();
    FilterSearchLocators locate = new FilterSearchLocators();
    private WebDriver driver;
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"browser"})
    public void searchBookByAudio(String browser) throws InterruptedException {
    set.beforeMethod(browser);
    driver= DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        locate.filterSearchButton(browser);
        locate.selectAudio();
        locate.showBooksButton();
        locate.clickOnAudioBook();
        locate.verifyAudioBook();
        set.tearDown();
    }
}
