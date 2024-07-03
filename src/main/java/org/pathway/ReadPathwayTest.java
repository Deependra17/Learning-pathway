package org.pathway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.Configuration;
import utils.CustomListener;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;
import java.util.List;

@Listeners(CustomListener.class)
public class ReadPathwayTest {
    private WebDriver driver;
    LoginUtils loginUtils;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
    }

    @Test
    public void readPathway() throws InterruptedException {
        Configuration config = new Configuration();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//*[@id=\"scrolling_div\"]/div[2]/div/div[2]")).click();
        System.out.println("Click on pathway");
        Thread.sleep(2000);

        //driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[3]/button")).click();

        String firstLevelBooksContainerXPath = "//*[@id=\"singleLevel_aca07030-ac07-4ce7-be39-d48e1c85a49b\"]/div[3]";
        List<WebElement> firstLevelBooks = driver.findElements(By.xpath(firstLevelBooksContainerXPath));
        System.out.println(firstLevelBooks.size());

        driver.findElement(By.xpath("//*[@id=\"steppingStone_08af4cd3-0103-4c6b-bf4b-2c018014c3e7\"]/abbr/div/div[1]")).click();
        int totalNumberOfPages = 16;
        for (int page = 1; page <= totalNumberOfPages; page++) {
            // Add a delay to simulate reading time (optional)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Identify and interact with the element to turn the page
            WebElement nextPageArrow = driver.findElement(By.xpath(config.getTurnPage()));
            nextPageArrow.click();

            // Extract text from the page
            WebElement pageContent = driver.findElement(By.xpath(config.getPageContent()));
            String pageText = pageContent.getText();
            System.out.println("Page " + page + ": " + pageText);

            //Check if it is last page
            if (page == totalNumberOfPages) {
                // Perform the action to click the "Next" button
                WebElement button = driver.findElement(By.xpath(config.getCLickOnNextButton()));
                button.click();
                System.out.println("Next button clicked");

                if (button.isDisplayed()) {
                    System.out.println("Book read completely. Continuing to the next book...");
                    // You may also add any additional actions here, like clicking on a 'Next' button to unlock the next book
                } else {
                    // If the book is not read completely, you may need to handle this scenario accordingly
                    System.out.println("Book not read completely. Handling this scenario...");
                    // For example, you may need to wait for some more time or handle any pop-ups/alerts
                }

            }
        }

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.close();
        }
    }
}

