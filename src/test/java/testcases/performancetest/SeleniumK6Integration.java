package testcases.performancetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class SeleniumK6Integration {
    public void loadTest() throws InterruptedException, IOException {
       WebDriver driver = new ChromeDriver();

        driver.get("https://app.hamropatro.com");

        // Trigger k6 load test after Selenium test
        String command = "k6 run /home/deependra17/PathwayTest/src/main/loadtest/load-test.js";
        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();

        driver.quit();
    }
}

