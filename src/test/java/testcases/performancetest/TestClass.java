package testcases.performancetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class TestClass {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://app.hamropatro.com");
        SeleniumK6Integration run = new SeleniumK6Integration();
     run.loadTest();
    }
}
