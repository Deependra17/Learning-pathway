package org.pathway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class VerifyBookName {
    WebDriver driver = new ChromeDriver();

    @Test
    public void verifyName() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://reader-dot-lets-read-dev.uc.r.appspot.com/");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"d8675fd9-29b3-4153-9ede-6c1b15812eba5206.19290458278\"]")).click();

        String expectedResult = "Master Book";
        String actualResult = driver.findElement(By.xpath("//*[@id=\"bookModalTitle\"]")).getText();
        Assert.assertEquals(actualResult,expectedResult, "Book name not matched");
    }
}
