package org.pathway;

import org.openqa.selenium.*;
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
        System.out.println(book.isEnabled());
        System.out.println(book.isDisplayed());
        System.out.println("Book Detail View");

        driver.findElement(By.xpath(config.getClickOnReadButton())).click();
        while (true) {
            try {
                WebElement nextPageArrow = driver.findElement(By.xpath(config.getTurnPage()));
                nextPageArrow.isEnabled();
                nextPageArrow.isDisplayed();
//            if (nextPageArrow != null) {
//                nextPageArrow.click();
//            } else {
//                break;
//            }
                nextPageArrow.click();
            } catch (StaleElementReferenceException e) {
//                System.out.println("Error: " + e);
                break;
            }
        }
        WebElement button = driver.findElement(By.xpath(config.getCLickOnNextButton()));
        button.click();
        System.out.println(button.isDisplayed());
        System.out.println(button.isEnabled());
        System.out.println("Next button clicked");

        driver.findElement(By.xpath(config.getSecondNextButton())).click();
        driver.findElement(By.xpath(config.getActivity())).click();
        driver.findElement(By.xpath(config.getCloseButton()));
        Thread.sleep(4000);
        System.out.println("Text book completed Successfully");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        driver.close();
    }

}
