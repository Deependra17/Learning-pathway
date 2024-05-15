package filtersearch.byaudio;

import filtersearch.FilterButton;
import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.RetryAnalyzer;
import utils.TestSetUp;

import java.util.concurrent.TimeUnit;

public class SearchAudioBookTest {

    TestSetUp set = new TestSetUp();
    private WebDriver driver;
    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Parameters({"browser"})
    public void searchBookByAudio(String browser) throws InterruptedException {
    set.beforeMethod(browser);
    driver= DriverFactory.build(browser);
        FilterSearchLocators locate = new FilterSearchLocators();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();

        WebElement audio = driver.findElement(By.xpath(locate.getSelectAudio()));
        audio.click();

        WebElement showbook = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showbook.click();
        Thread.sleep(3000);

        WebElement book = driver.findElement(By.xpath(locate.getClickOnAudioBook()));
        book.click();
        Thread.sleep(3000);

        WebElement verify = driver.findElement(By.xpath(locate.getVerifyAudio()));
         String text=verify.getText();
        System.out.println(text);
        if (text.equals("Listen")) {
            System.out.println("This is a audio book");
        } else {
            System.out.println("This is not audio book");
        }
        set.tearDown();
    }
}
