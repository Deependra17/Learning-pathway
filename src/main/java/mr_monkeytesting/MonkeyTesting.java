package mr_monkeytesting;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class MonkeyTesting {

    @Test
    public void mrMonkeyTesting() {
        WebDriver driver = new ChromeDriver();
        Faker faker = new Faker();
        Random random = new Random();

        driver.get("https://app-dev.hamrostack.com/");
        driver.manage().window().maximize();

        int maxInteractions = 100;
        for ( int i = 0; i< maxInteractions; i++) {
            List < WebElement > clickableElements = driver.findElements(By.xpath("//a | //button | //inpit[@type= 'submit']"));
            if (clickableElements.size() > 0) {
                WebElement randomElement = clickableElements.get(random.nextInt((clickableElements.size())));
                String tagName = randomElement.getTagName();

                try {
                    if ("input".equals(tagName) && "text".equals(randomElement.getAttribute("type"))) {
                        randomElement.sendKeys(faker.name().firstName());
                    } else {
                        randomElement.click();
                    }
                }
                catch (Exception e) {
                    System.out.println("Failed t interect with element: " + e.getMessage());
                }
            }
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        driver.quit();
    }
}
