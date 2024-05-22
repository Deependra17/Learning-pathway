package alert;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;

public class AlertHandling {
    //types
    //simple alerts
    //prompt alerts and
    //confirmation alerts
    WebDriver driver = new ChromeDriver();

    @Test(enabled = false)
    public void simpleAlert() throws InterruptedException {
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");
        WebElement alertButton = driver.findElement(By.xpath("//a[normalize-space()='See an example alert']"));
        alertButton.click();
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        System.out.println(alertMessage);
        Thread.sleep(3000);
        alert.accept();
        driver.quit();
    }

    @Test(enabled = false)
    public void popUpAlert() throws InterruptedException, AWTException {
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");
        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popUp = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='See a sample prompt']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Get the total height of the page
        long totalHeight = (long) js.executeScript("return document.body.scrollHeight");

        // Calculate 35% of the total height
        long scrollAmount = (long) (totalHeight * 0.35);

        // Scroll by the calculated amount
        js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);

        // Optionally, wait for a few seconds to observe the scroll
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        popUp.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("Selenium");
        Thread.sleep(5000);
        alert.accept();
    }

    @Test(enabled = true)
    public void confirmationAlert() throws InterruptedException {
        driver.get("https://www.selenium.dev/documentation/webdriver/interactions/alerts/");
        Thread.sleep(3000);
        WebElement confirmPop = driver.findElement(By.xpath("//a[@onclick='window.confirm(\"Are you sure?\")']"));
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Get the total height of the page
        long totalHeight = (long) js.executeScript("return document.body.scrollHeight");

        // Calculate 35% of the total height
        long scrollAmount = (long) (totalHeight * 0.25);

        // Scroll by the calculated amount
        js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);

        // Optionally, wait for a few seconds to observe the scroll
        try {
            Thread.sleep(5000); // 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        confirmPop.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        driver.quit();
    }
}
