package search;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.Configuration;
import utils.DriverFactory;
import utils.LoginUtils;

import java.util.List;

public class SearchFunctionalityTest {

    LoginUtils loginUtils;
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
    }

    @Test

    public void searchByName() throws InterruptedException {
        utils.Configuration config = new Configuration();

        WebElement search = driver.findElement(By.xpath(config.getInputField()));
        search.sendKeys("My Body");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        String expectedName = "My Body";
        String actualName = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[7]/div[2]")).getText();
        Assert.assertEquals(actualName, expectedName, "Book name does not match");
        Thread.sleep(5000);


        List<WebElement> bookElements = driver.findElements(By.xpath("//div[@class='AllBooksGrid_BooksContainer__43ZZb']"));
        int bookCount = bookElements.size();

        System.out.println("Total count of books: " + bookCount);
        System.out.println("Book Names:");
        for (WebElement bookElement : bookElements) {
            String bookName = bookElement.getText();
            System.out.println(bookName);
        }

// Check if "My Body" is present
        boolean isMyBodyPresent = false;
        for (WebElement bookElement : bookElements) {
            if (bookElement.getText().contains("My Body")) {
                isMyBodyPresent = true;
                break;
            }
        }

        if (isMyBodyPresent) {
            System.out.println("Book 'My Body' is present in the list.");
        } else {
            System.out.println("Book 'My Body' is not present in the list.");
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
