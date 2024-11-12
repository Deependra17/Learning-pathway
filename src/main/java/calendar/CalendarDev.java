package calendar;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;

public class CalendarDev {
    private WebDriver driver;

    public void getDevUrl(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        String devUrl = "https://hamropatro.alpha.hamrostack.com/calendar";
        driver.get(devUrl);
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); // Scroll down by 500 pixels
        Thread.sleep(4000);
    }

    public String verifyActualDate() {
        WebElement date = driver.findElement(By.xpath("//*[@id='2024-7-1']"));
        String actualDate = date.getText();
        System.out.println(date.isDisplayed());
        return actualDate;
    }

    public String verifyDataOfActualMonth() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement currentMonth = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='dates clearfix']")));
            String actualMonth = currentMonth.getText();
            return actualMonth;
        }
        catch (ElementNotVisibleException e) {
            System.out.printf("Error: " +e);
        }
        return null;
    }

}
