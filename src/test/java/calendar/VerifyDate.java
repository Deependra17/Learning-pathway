package calendar;

import locators.CalendarLocators;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.TestSetUp;


public class VerifyDate {
    TestSetUp set = new TestSetUp();
    private WebDriver driver;

    @Test
    @Parameters({"browser"})
    public void testDate(String browser) throws InterruptedException {
        CalendarLocators calender = new CalendarLocators();
        driver = DriverFactory.build(browser);
        calender.getUrl(browser);
        Thread.sleep(5000);
        calender.clickOnCalendar();
        Thread.sleep(5000);
        String expectedDate ="असार २०८१";
        String actualDate = calender.verifyDate();
        System.out.println("Expected Date:" +expectedDate);
        System.out.println("Actual Date: "+ actualDate);
        Assert.assertEquals(actualDate,expectedDate,"Date does not match");
        driver.quit();
    }
}
