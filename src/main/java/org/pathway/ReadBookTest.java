package org.pathway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Configuration;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;

public class ReadBookTest {
    private WebDriver driver;
    LoginUtils loginUtils;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        System.out.println("User logged in Successfully");
    }

    @Test
    public void readBook() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Configuration config = new Configuration();
        WebElement book = driver.findElement(By.xpath(config.getClickOnBook()));
        book.click();
        System.out.println("Book Detail View");

        driver.findElement(By.xpath(config.getClickOnReadButton())).click();
        int totalNumberOfPages = 10;
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

                driver.findElement(By.xpath(config.getSecondNextButton())).click();
                driver.findElement(By.xpath(config.getActivity())).click();
                driver.findElement(By.xpath(config.getCloseButton()));
                Thread.sleep(4000);
                System.out.println("Text book completed Successfully");
            }
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        driver.close();
    }

}
