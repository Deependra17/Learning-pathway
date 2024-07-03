package calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class CalendarProd {
    private WebDriver driver;

    public void getProdUrl(String browser) {
        driver = DriverFactory.build(browser);
        String prodUrl= "https://www.hamropatro.com/";
        driver.get(prodUrl);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);"); // Scroll down by 500 pixels

    }

    public String verifyExpectedDate() {
        WebElement date = driver.findElement(By.xpath("//*[@id=\"2024-7-1\"]"));
        String expectedDate = date.getText();
        System.out.println(date.isDisplayed());
        return expectedDate;
    }
    public  String verifyDateOfCurrentMonth() {
        WebElement currentMonth= driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/div[2]/div[2]/ul[3]"));
        String expectedMonth = currentMonth.getText();
        return expectedMonth;
    }
}
