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
import java.util.concurrent.TimeUnit;

public class SearchBookByLanguageInMultipleWindows {
    private final String url = "https://reader-dot-lets-read-dev.uc.r.appspot.com/";
    private final String searchFieldXpath = "//*[@id=\"__next\"]/div/main/div[2]/div[1]/span/span/input";
    private final String languageButtonXpath = "//*[@id=\"__next\"]/div/header/div[3]/button";
    private final String languagePopupXpath = "/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[2]/div[1]";
    private final String okButtonXpath = "/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[3]/button";

    WebDriver driver;
    private final String englishBook = "My Body";
    private final String indoBook = "Pejuang Kemanusiaan";
    private final String nepaliBook = "हामी किन दाँत माझ्छौं?";

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
    public void searchBookByLanguageInMultipleWindows() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        int numberOfWindows = 3;
        WebDriver[] windows = new WebDriver[numberOfWindows];

        List<List<String>> allSearchResults = new ArrayList<>();

        for (int i = 0; i < numberOfWindows; i++) {
            windows[i] = driver.switchTo().newWindow(WindowType.TAB);
            windows[i].get(url);

            switch (i) {
                case 0:
                    chooseLanguage(windows[i], "Nepali");
                    searchBook(windows[i], nepaliBook, allSearchResults);
                    break;

                case 1:
                    chooseLanguage(windows[i], "English");
                    searchBook(windows[i], englishBook, allSearchResults);
                    break;

                case 2:
                    chooseLanguage(windows[i], "Indonesian");
                    searchBook(windows[i], indoBook, allSearchResults);
                    break;
            }
        }

        validateSearchResults(allSearchResults);

        for (WebDriver window : windows) {
            window.quit();
        }
    }

    private void chooseLanguage(WebDriver window, String languageToSelect) throws InterruptedException {
        WebElement chooseLanguageButton = window.findElement(By.xpath(languageButtonXpath));
        chooseLanguageButton.click();
        Thread.sleep(5000);

        List<WebElement> languageOptions = window.findElements(By.xpath(languagePopupXpath));
        for (WebElement option : languageOptions) {
            if (option.getText().equals(languageToSelect)) {
                option.click();
                break;
            }
        }

        WebElement okButton = window.findElement(By.xpath(okButtonXpath));
        okButton.click();
        Thread.sleep(10000);
    }

    private void searchBook(WebDriver window, String bookName, List<List<String>> allSearchResults) throws InterruptedException {
        WebElement searchField = window.findElement(By.xpath(searchFieldXpath));
        searchField.sendKeys(bookName);
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> searchResults = window.findElements(By.xpath("//*[@id=\"__next\"]/div/div[3]/div"));
        List<String> results = new ArrayList<>();
        for (WebElement result : searchResults) {
            String text = result.getText();
            results.add(text);
            System.out.println("Result for book: " + "\n" + text);
        }
        allSearchResults.add(results);
        Thread.sleep(5000);
    }

    private void validateSearchResults(List<List<String>> allSearchResults) {
        for (String result : allSearchResults.get(0)) {
            Assert.assertTrue(result.contains(nepaliBook), "Expected book '" + nepaliBook + "' not found in search results");
        }

        for (String result : allSearchResults.get(1)) {
            Assert.assertTrue(result.contains(englishBook), "Expected book '" + englishBook + "' not found in search results");
        }

        for (String result : allSearchResults.get(2)) {
            Assert.assertTrue(result.contains(indoBook), "Expected book '" + indoBook + "' not found in search results");
        }
    }
}
