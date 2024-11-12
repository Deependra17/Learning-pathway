package pages;

import org.openqa.selenium.*;
import org.testng.Assert;
import utils.DriverFactory;

import java.util.List;
import java.util.NoSuchElementException;

public class NormalSearchPage {

    private WebDriver driver;

    public void emptySearch(String browser) {
        driver = DriverFactory.build(browser);
        WebElement inputField = driver.findElement(By.xpath("//input"));
        inputField.sendKeys(" ");
        inputField.sendKeys(Keys.ENTER);
    }

    public void sqlInjection(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        WebElement search = driver.findElement(By.xpath("//input"));
        search.sendKeys("' OR '1'='1");
        Thread.sleep(2000);
        search.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

        try {
            WebElement errorMessage = driver.findElement(By.xpath("//div[@class='Empty_content__right__text__MS8vL']"));
        } catch (NoSuchElementException e) {
            System.out.println(e);
            List<WebElement> bookElements = driver.findElements(By.xpath("//div[@class='AllBooksGrid_BooksContainer__43ZZb']"));

            if (bookElements.isEmpty()) {
                System.out.println("No books found");
            } else {
                System.out.println("Book Names:");
                for (WebElement bookElement : bookElements) {
                    String bookName = bookElement.getText();
                    System.out.println(bookName);
                }
            }
        }
    }

    public void specialCharacters(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        WebElement search = driver.findElement(By.xpath("//input"));
        search.sendKeys("@#jdfskh%$");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        WebElement errorMessage = driver.findElement(By.xpath("//div[@class='Empty_content__right__text__MS8vL']"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed");
    }

    public void booksName() {
        List<WebElement> bookElements = driver.findElements(By.xpath("//div[@class='AllBooksGrid_BooksContainer__43ZZb']"));
        int bookCount = bookElements.size();
        System.out.println("Total count of books: " + bookCount);
        System.out.println("Book Names:");
        for (WebElement bookElement : bookElements) {
            String bookName = bookElement.getText();
            System.out.println(bookName);
        }
    }

    public void searchByName(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        long startTime = System.currentTimeMillis();

        WebElement search = driver.findElement(By.xpath("//input"));
        search.sendKeys("My Body");
        search.sendKeys(Keys.ENTER);
        long endTime = System.currentTimeMillis();

        // Calculate response time
        long responseTime = endTime - startTime;
        System.out.println("Response Time: " + responseTime + " ms");
        Thread.sleep(5000);
        // Record end time
    }

    public void bookElements() {

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

    public void verifyBookName() throws InterruptedException {
        String expectedName = "My Body";
        String actualName = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div[3]/div/div[2]/div[2]")).getText();
        Assert.assertEquals(actualName, expectedName, "Book name does not match");
        Thread.sleep(5000);
    }
}
