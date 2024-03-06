package health;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Search {

    WebDriver driver= new ChromeDriver();

    @Test
    public void searchDeepen() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://health-migrated-dev-blue.alpha.hamrostack.com/doctors");

        WebElement searchButton=driver.findElement(By.xpath("//*[@id=\"search-input\"]"));
        searchButton.sendKeys("Deepen Chhetri");
        searchButton.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[3]/div")).getText();
    }
}
