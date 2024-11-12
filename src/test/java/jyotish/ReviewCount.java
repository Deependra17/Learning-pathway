package jyotish;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class ReviewCount {
    WebDriver driver;

    @Test
    public void verifyReviewCount() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://jyotishsewa.hamropatro.com/jyotish-detail?zi3k9S3YWveh2S7yO9lfqp0Yi6H3");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll vertically by 500 pixels
        js.executeScript("window.scrollBy(0, 500);");

        WebElement review = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div"));
        WebElement userName = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div[1]/div/div[2]/div/div[1]/div[2]/div/h4"));
        userName.getText();
        System.out.println("Total Review: " +review.getSize());
        System.out.println(review.getText());
        driver.quit();
    }
}
