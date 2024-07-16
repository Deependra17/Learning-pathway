package searchindifferentwindows;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchUsingDifferentMultipleWindows {
    private final String url = "https://reader-dot-lets-read-dev.uc.r.appspot.com/";
    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(url);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


    @Test
    public void searchBookUsingMultipleWindows() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        int numberOfWindows = 3;
        String searchInputField = "//*[@id=\"__next\"]/div/main/div[2]/div[1]/span/span/input";
        WebDriver[] windows = new WebDriver[numberOfWindows];

        List<List<String>> allSearchResults = new ArrayList<>();

        for (int i = 0; i < numberOfWindows; i++) {
            windows[i] = driver.switchTo().newWindow(WindowType.TAB);
            windows[i].get(url);

            switch (i) {
                case 0:
                    WebElement search1 = windows[i].findElement(By.xpath(searchInputField));
                    search1.sendKeys("My Body");
                    search1.sendKeys(Keys.ENTER);

                    List<WebElement> searchResults1 = windows[i].findElements(By.xpath("//*[@id=\"__next\"]/div/div[3]/div"));
                    System.out.println("Search results in window: " + windows[i].getTitle());
                    List<String> results1 = new ArrayList<>();
                    for (WebElement result : searchResults1) {
                        String text = result.getText();
                        results1.add(text);
                        System.out.println("Result for first search: " + "\n" + text);
                    }
                    allSearchResults.add(results1);
                    Thread.sleep(5000);
                    break;

                case 1:
                    WebElement search2 = windows[i].findElement(By.xpath(searchInputField));
                    search2.sendKeys("New year!");
                    search2.sendKeys(Keys.ENTER);

                    List<WebElement> searchResults2 = windows[i].findElements(By.xpath("//*[@id=\"__next\"]/div/div[3]/div"));
                    System.out.println("Search results in window: " + windows[i].getTitle());
                    List<String> results2 = new ArrayList<>();
                    for (WebElement result : searchResults2) {
                        String text = result.getText();
                        results2.add(text);
                        System.out.println("Result for second search: " + text);
                    }
                    allSearchResults.add(results2);
                    Thread.sleep(5000);
                    break;

                case 2:
                    WebElement search3 = windows[i].findElement(By.xpath(searchInputField));
                    search3.sendKeys("Too Many Bananas");
                    search3.sendKeys(Keys.ENTER);

                    List<WebElement> searchResults3 = windows[i].findElements(By.xpath("//*[@id=\"__next\"]/div/div[3]/div"));
                    System.out.println("Search results in window: " + windows[i].getTitle());
                    List<String> results3 = new ArrayList<>();
                    for (WebElement result : searchResults3) {
                        String text = result.getText();
                        results3.add(text);
                        System.out.println("Result for third search: " + "\n" + text);
                    }
                    allSearchResults.add(results3);
                    Thread.sleep(5000);
                    break;
            }

        }

        for (String result : allSearchResults.get(0)) {
            Assert.assertTrue(result.contains("My Body"), "Expected book 'My Body' not found in search results");
        }

        for (String result : allSearchResults.get(1)) {
            Assert.assertTrue(result.contains("New year!"), "Expected book 'New year!' not found in search results");
        }

        for (String result : allSearchResults.get(2)) {
            Assert.assertTrue(result.contains("Too Many Bananas"), "Expected book 'Too Many Bananas' not found in search results");
        }
        driver.switchTo().defaultContent();

        for (WebDriver window : windows) {
            window.quit();
        }
    }

    @Test
    public void searchBookByLanguageInMultipleWindows() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        int numberOfWindows = 3;
        String searchInputField = "//*[@id=\"__next\"]/div/main/div[2]/div[1]/span/span/input";
        WebDriver[] windows = new WebDriver[numberOfWindows];

        List<List<String>> allSearchResults = new ArrayList<>();

        for (int i = 0; i < numberOfWindows; i++) {
            windows[i] = driver.switchTo().newWindow(WindowType.TAB);
            windows[i].get(url);
            WebElement chooseLanguage = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/header/div[3]/button"));
            chooseLanguage.click();
            Thread.sleep(5000);
            WebElement nepali = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div[1]"));
            nepali.click();
            WebElement okButton = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[3]/button"));
            okButton.click();
            Thread.sleep(10000);
            System.out.println(nepali.getText());
        }
    }
}
