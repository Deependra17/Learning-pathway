package filtersearch.bycategory;

import filtersearch.FilterButton;
import filtersearch.FilterSearchLocators;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.BookLanguageUtil;
import utils.DriverFactory;
import utils.LoginUtils;


import java.time.Duration;
import java.util.List;

public class SearchBookByLanguageTest {
    LoginUtils loginUtils;
    private WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void beforeMethod(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        Thread.sleep(2000);

        FilterButton click = new FilterButton(driver);
        click.clickOnFilterButton();
    }

    @Test()
    public void bookSearchByLanguage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        FilterSearchLocators locate = new FilterSearchLocators();

        WebElement languageDropdown = driver.findElement(By.xpath(locate.getSelectLanguage()));
        languageDropdown.click();

        WebElement nepaliOption = driver.findElement(By.xpath(locate.getChooseLanguage()));
        nepaliOption.click();
        Thread.sleep(5000);

        WebElement showBooks = driver.findElement(By.xpath(locate.getShowBooksButton()));
        showBooks.click();
        Thread.sleep(4000);

        // Get all book elements and extract their names
//        List<WebElement> bookElements = driver.findElements(By.xpath(locate.getVerifyAllBooks()));
        List<String> allBookNames = BookLanguageUtil.getAllBookNames(driver, locate.getVerifyAllBooks());
        System.out.println("Names of all books:");
        for (String bookName : allBookNames) {
            System.out.println(bookName);
        }

        // Verify that all book names are in Nepali language
        BookLanguageUtil.verifyAllBookNamesAreNepali(allBookNames);

        // If no assertion failures occur, all book names are in Nepali
        System.out.println("All book names are in Nepali.");
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
        }
    }
}
