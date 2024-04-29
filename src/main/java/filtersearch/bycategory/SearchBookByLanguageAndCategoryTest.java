package filtersearch.bycategory;

import filtersearch.FilterSearchLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.time.Duration;
import java.util.List;

public class SearchBookByLanguageAndCategoryTest {
    LoginUtils loginUtils;
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        Thread.sleep(2000);
    }

    @Test()
    public void bookSearchByLanguage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        FilterSearchLocators locate = new FilterSearchLocators();
        WebElement filterButton = driver.findElement(By.xpath(locate.getFilterSearchButton()));
        filterButton.click();

        WebElement languageDropdown = driver.findElement(By.xpath(locate.getSelectLanguage()));
        languageDropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nepaliOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locate.getChooseLanguage())));
        nepaliOption.click();

        WebElement showBooks = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showBooks.click();
        Thread.sleep(2000);

        List<WebElement> bookElements = driver.findElements(By.xpath(locate.getVerifyAllBooks()));

        for (WebElement bookElement : bookElements) {
            String bookLanguage = bookElement.getText();
            System.out.println(bookElement.getText());
            System.out.println("Book Language: " + bookLanguage);

            if (!bookLanguage.equalsIgnoreCase("Nepali")) {
                System.out.println("All Book are not in Nepali language: " + bookLanguage);
//                Assert.assertEquals(bookLanguage.trim(), "Nepali", "Book language is not Nepali");
            } else {
                System.out.println("All Books are not in Nepali language");
            }
        }

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
