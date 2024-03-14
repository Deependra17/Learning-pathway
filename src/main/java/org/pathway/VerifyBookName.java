package org.pathway;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyBookName {
    WebDriver driver = new ChromeDriver();

    @Test
    public void verifyName() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://health.hamropatro.com/doctors");
        driver.getTitle();
        driver.manage().window().maximize();

        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-input")));
        element.sendKeys("Imran Ansari");
        element.sendKeys(Keys.ENTER);

        String expectedResult = "Dr. Imran Ansari";
        System.out.println("Expected Result: "+expectedResult);
        String actualResult = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[3]/div/div/div/div/div[2]/div/h5")).getText();
        Assert.assertEquals(actualResult, expectedResult, "Book name not matched");
        System.out.println("Actual Result: "+actualResult);
        Thread.sleep(5000);
        driver.close();
    }
    @Test
    public void test2(){

    }
}
