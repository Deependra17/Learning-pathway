package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverFactory;

public class CalendarLocators {
    private WebDriver driver;


    public void getUrl(String browser) {
        driver = DriverFactory.build(browser);
        driver.get("https://app-dev.hamrostack.com/");
    }
    public void clickOnCalendar() {
        WebElement calendar= driver.findElement(By.xpath("/html/body/div[1]/div[1]/main/div[1]/div[1]/div/div/div[2]/div[2]/a/button"));
        calendar.click();
    }
    public String verifyDate() {
        WebElement date = driver.findElement(By.xpath("//span[contains(@class, 'text-lg')]"));
        String actualDate= date.getText();
        System.out.println(date.isDisplayed());
        return actualDate;
    }
}
