package generatePdf;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.io.File;
import java.time.Duration;

public class PDFValidationTest {

    private WebDriver driver;
    LoginUtils loginUtils;
    private final String downloadDir = "/path/to/download/directory";

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = DriverFactory.build("chrome");
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        System.out.println("User logged in Successfully");
    }

    @Test
    public void testPDFValidation() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement book = driver.findElement(By.xpath("//*[@id=\"BOOK_WITH_ACTIVITY\"]/div[3]"));
        book.click();
        // Find and click on the link to download the PDF file
        WebElement downloadButton = driver.findElement(By.xpath("//*[@id=\"wrapperDiv\"]/div[2]/div[2]/span/svg/path"));
        downloadButton.click();

        WebElement downloadPdf = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div[2]/div[1]"));
        downloadPdf.click();
        WebElement firstLiElement = driver.findElement(By.xpath("//li[1]"));
        firstLiElement.click();

        // Wait for the file to be downloaded
        waitForFileDownload(30);

        // Specify the path to the downloaded PDF file
        String downloadedFilePath = getDownloadedFilePath();

        // Perform PDF validation
        File downloadedFile = new File(downloadedFilePath);
        Assert.assertTrue(downloadedFile.exists(), "PDF file is not downloaded successfully");

        // Add PDF validation logic using a PDF comparison library or tool
        // Example:
        // boolean isContentMatched = PDFComparator.comparePDF(downloadedFilePath, expectedPDFFilePath);
        // Assert.assertTrue(isContentMatched, "PDF content does not match the expected content");
    }

    @AfterClass
    public void tearDown() {
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    private void waitForFileDownload(int timeoutInSeconds) throws InterruptedException {
        File[] files;
        int waited = 0;
        do {
            Thread.sleep(1000);
            waited++;
            files = new File(downloadDir).listFiles((dir, name) -> name.endsWith(".pdf"));
        } while (files == null || files.length == 0 || waited < timeoutInSeconds);
    }

    private String getDownloadedFilePath() {
        File[] files = new File(downloadDir).listFiles((dir, name) -> name.endsWith(".pdf"));
        if (files != null && files.length > 0) {
            return files[0].getAbsolutePath();
        }
        throw new RuntimeException("PDF file not found in the download directory");
    }
}
