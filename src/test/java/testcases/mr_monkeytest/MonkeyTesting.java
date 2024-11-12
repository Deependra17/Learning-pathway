package testcases.mr_monkeytest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class MonkeyTesting {
    @Test
    public static void monkeyTest() {

        WebDriver driver = new ChromeDriver();
        ChaosTestSimulation test = new ChaosTestSimulation();

        // Open the web page
        driver.get("https://app-dev.hamrostack.com/");
        test.chaosTest();

        // Perform random actions
        Random random = new Random();
        int actions = 10;  // Define the number of random actions

        for (int i = 0; i < actions; i++) {
            List<WebElement> elements = driver.findElements(By.xpath("//*"));
            WebElement randomElement = elements.get(random.nextInt(elements.size()));

            try {
                if (randomElement.isDisplayed()) {
                    if (randomElement.getTagName().equals("input")) {
                        randomElement.sendKeys("RandomInput" + random.nextInt(1000));
                    } else {
                        randomElement.click();
                    }
                }
            } catch (Exception e) {
                System.out.println("Skipped an element due to: " + e.getMessage());
            }
        }

        // Close the browser
        driver.quit();
    }
}
