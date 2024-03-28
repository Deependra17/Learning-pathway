package autosuggestion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AutoSuggestions {
    WebDriver driver = new ChromeDriver();

    @Test
    public void autoSuggestionTest() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.google.com/");

        driver.manage().window().maximize();
        driver.findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(2000);
// To get address of all the suggestions
        String xp = "//span[contains(text(),'selenium')]";
        List<WebElement> allSuggestions = driver.findElements(By.xpath(xp));
// To count number of suggetions
        int count = allSuggestions.size();
        System.out.println(count);
// To print all the suggestions
        for (int i = 0; i < count; i++) {
            WebElement suggestion = allSuggestions.get(i);
            String text = suggestion.getText();
            System.out.println(text);
        }
// To click on last suggestion
        allSuggestions.get(count - 1).click();
    }
    public void tearDown() {
        if (driver !=null) {
            driver.close();
        }
    }
}
