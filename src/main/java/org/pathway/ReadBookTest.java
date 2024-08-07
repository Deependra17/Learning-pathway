package org.pathway;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
import utils.RetryAnalyzer;

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

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void readBook() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Configuration config = new Configuration();
        WebElement book = driver.findElement(By.xpath(config.getClickOnBook()));
        System.out.println(book.getText());
        book.click();
        if (book.isDisplayed()) {
            System.out.println("Book is displayed");
        } else {
            System.out.println("Book is not displayed");
        }

        driver.findElement(By.xpath(config.getClickOnReadButton())).click();
        while (true) {
            try {
                WebElement nextPageArrow = driver.findElement(By.xpath(config.getTurnPage()));
                if (nextPageArrow.isEnabled()) {
                    System.out.println("Arrow is enabled");
                } else {
                    System.out.println("Arrow is not enabled");
                }
                if (nextPageArrow.isDisplayed()) {
                    System.out.println("Next page arrow is displayed");
                } else {
                    System.out.println("Next page arrow is not displayed");
                }

                nextPageArrow.click();
            } catch (StaleElementReferenceException e) {
                break;
            }
        }
        WebElement button = driver.findElement(By.xpath(config.getCLickOnNextButton()));
        button.click();

        System.out.println("Next button clicked");
        WebElement nextButton = driver.findElement(By.xpath(config.getSecondNextButton()));
        nextButton.click();

        WebElement activity = driver.findElement(By.xpath(config.getActivity()));
        activity.click();


        WebElement closeButton = driver.findElement(By.xpath(config.getCloseButton()));
        closeButton.click();
        Thread.sleep(4000);
        System.out.println("Text book completed Successfully");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            driver.close();
        }
    }

}
