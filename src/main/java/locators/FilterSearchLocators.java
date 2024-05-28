package locators;

import org.knowm.xchart.style.theme.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.collections.Lists;
import utils.BookLanguageUtil;
import utils.DriverFactory;

import java.nio.channels.WritableByteChannel;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FilterSearchLocators {
    private WebDriver driver;

    public void filterSearchButton(String bowser) {
        driver = DriverFactory.build(bowser);
        WebElement filterSearchButton = driver.findElement(By.xpath("//div[@class='Home_searchBarContent__hQhab']//div[2]"));
        filterSearchButton.click();
    }

    public void selectCategory() {
        WebElement selectCategory = driver.findElement(By.xpath("//button[@aria-label='Novel']"));
        selectCategory.click();
        System.out.println(selectCategory.isSelected());
    }

    public String getExpectedCategory() {
        WebElement selectCategory = driver.findElement(By.xpath("//button[@aria-label='Novel']"));
        String expectedCategory = selectCategory.getText();
        selectCategory.click();
        System.out.println(selectCategory.isSelected());
        return expectedCategory;
    }

    public void showBooksButton() {
        WebElement showBooksButton = driver.findElement(By.xpath("//div[@aria-label='Show books']"));
        showBooksButton.click();
    }

    public void clickOnBook() {
        WebElement clickOnBook = driver.findElement(By.xpath("//div[contains(@class, 'q')][.//img[@alt='Know My Name']]"));
        clickOnBook.click();
    }

    public String verifyCategory() {
        WebElement verifyCategory = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[3]/div[4]/span/span/label/u"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", verifyCategory);
        String actualCategory = verifyCategory.getText();
        return actualCategory;
    }

    public void closeButton() {
        WebElement closeButton = driver.findElement(By.xpath("//span[@class='ant-modal-close-x']"));
        closeButton.click();
    }

    public void readingLevel() {
        WebElement level = driver.findElement(By.xpath("//button[normalize-space()='3']"));
        level.click();
    }

    public String getLevel() {
        WebElement level = driver.findElement(By.xpath("//button[normalize-space()='3']"));
        String expectedLevel = level.getText();
        System.out.println("Expected Level: " + expectedLevel);
        return expectedLevel;
    }

    public void clickOnBookForReadingLevel() {
        WebElement readingLevel = driver.findElement(By.xpath("//div[contains(@class, 'q')][.//img[@alt='Dira and Chaku']]"));
        readingLevel.click();
    }

    public String getVerifyReadingLevel() {
        WebElement verifyLevel = driver.findElement(By.xpath("//p[normalize-space()='3']"));
        String actualReading = verifyLevel.getText();
        System.out.println("Actual Level: " + actualReading);
        return actualReading;
    }

    public void selectLanguage() {
        WebElement selectLanguage = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='language_label']")));
        selectLanguage.click();

        WebElement firstLanguageOption = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@lang='npi']")));
        firstLanguageOption.click();
    }

    public void verifyAllBooks() {
        // Get all book elements and extract their names
//        List<WebElement> bookElements = driver.findElements(By.xpath(locate.getVerifyAllBooks()));
        List<String> allBookNames = BookLanguageUtil.getAllBookNames(driver, "//div[@class='AllBooksGrid_BooksContainer__43ZZb']");
        System.out.println("Names of all books:");
        for (String bookName : allBookNames) {
            System.out.println(bookName);
        }

        // Verify that all book names are in Nepali language
        BookLanguageUtil.verifyAllBookNamesAreNepali(allBookNames);

        // If no assertion failures occur, all book names are in Nepali
        System.out.println("All book names are in Nepali.");
    }

    public void selectAudio() {
        WebElement selectAudio = driver.findElement(By.xpath("//button[@aria-label='Select or unselect audio']"));
        selectAudio.click();
    }

    public void clickOnAudioBook() {
        WebElement clickOnAudioBook = driver.findElement(By.xpath("//div[@aria-label='Forest Friends']"));
        clickOnAudioBook.click();
    }

    public void verifyAudioBook() {
        WebElement verifyAudioBook = driver.findElement(By.xpath("//button[@aria-label='Listen audio']"));
        String text = verifyAudioBook.getText();
        System.out.println(text);
        if (text.equals("Listen")) {
            System.out.println("This is a audio book");
        } else {
            System.out.println("This is not audio book");
        }
    }


    public void ClickToChooseCountry() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement countryDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-labelledby='Select']")));
        countryDropdown.click();
    }

    public void selectRandomCountry() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> countries = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='ant-modal-body'][.//*[@id='language_modal']]")));

        if (!countries.isEmpty()) {
            System.out.println("List of countries: ");
            for (WebElement country : countries) {
                System.out.println(country.getText());
            }
            Random random = new Random();
            int randomIndex = random.nextInt(countries.size());
            WebElement selectedCountry = countries.get(randomIndex);
            selectedCountry.click();
//            System.out.println("Selected Country: " + selectedCountry.getText());
        } else {
            System.out.println("No countries found in the list.");
        }
        Thread.sleep(3000);
    }

    public String expectedCountry() {
        WebElement country = driver.findElement(By.xpath("//div[@aria-labelledby='Select']//div[@class='FilterModal_dropDown__left__UZv4s']"));
        String expectedCountry = country.getText();
        System.out.println("Expected Country: " + expectedCountry);
        return expectedCountry;
    }

    public void clickOnRandomBook() throws InterruptedException {

        WebElement clickOnBook = driver.findElement(By.xpath("//div[contains(@class, 'q')][.//img[@alt='Different Level']]"));
        clickOnBook.click();

        // Optional: Wait for 3 seconds to observe the click action
        Thread.sleep(3000);

    }

    public String getActualCountryOfOrigin() {
        WebElement actualCountryOfOrigin = driver.findElement(By.xpath("//span[normalize-space()='Algeria']"));
        String actualCountry = actualCountryOfOrigin.getText();
        System.out.println("Actual Country of origin: " + actualCountry);
        return actualCountry;
    }
}
