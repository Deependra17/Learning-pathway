package testcases.normalsearch;

import pages.NormalSearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CustomListener;
import utils.DriverFactory;
import utils.TestSetUp;

import java.time.Duration;

@Listeners(CustomListener.class)
public class SqlInjection {

    TestSetUp set = new TestSetUp();
    NormalSearchPage locators = new NormalSearchPage();

    private WebDriver driver;

    @Test()
    @Parameters({"browser"})
    public void securityTest(String browser) throws InterruptedException {
        set.beforeMethod(browser);
        driver = DriverFactory.build(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        locators.sqlInjection(browser);
        set.tearDown();
    }
}
