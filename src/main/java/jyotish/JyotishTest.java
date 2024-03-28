package jyotish;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JyotishTest {
    private WebDriver driver;
    private DevTools devTools;
    private final List<String> apiUrls = new ArrayList<>();
    private final List<Integer> statusCodes = new ArrayList<>();
    private static final String EXCEL_FILE_NAME = "api_responses.xlsx";
    private static final String URL = "https://jyotishsewa.alpha.hamrostack.com/";


    @BeforeMethod
    public void setUp() {
        // Use WebDriverManager to setup Chrome driver
        WebDriverManager.chromedriver().setup();

        // Initialize Chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Get DevTools instance directly from the ChromeDriver
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @Test
    public void testAPIRequests() throws InterruptedException {
        // Enable network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Navigate to the website
        driver.get(URL);

        // Wait until all elements are visible using WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));



        // Wait for network requests to complete
        Thread.sleep(10000);

        // Add listener to capture network responses
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            String apiUrl = response.getUrl();
            int statusCode = response.getStatus();
            apiUrls.add(apiUrl);
            statusCodes.add(statusCode);
        });

        // Wait for a reasonable amount of time to capture all requests
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        // Print captured URLs and status codes
        for (int i = 0; i < apiUrls.size(); i++) {
            System.out.println("URL: " + apiUrls.get(i) + ", Status Code: " + statusCodes.get(i));
        }

        // Store data in an Excel file
        writeDataToExcel(apiUrls, statusCodes);

        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    private void writeDataToExcel(List<String> urls, List<Integer> codes) throws IOException {
        // Delete existing Excel file if it exists
        File file = new File(EXCEL_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("API Responses");

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("URL");
        headerRow.createCell(1).setCellValue("Status Code");

        // Write data to the sheet
        for (int i = 0; i < urls.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(urls.get(i));
            row.createCell(1).setCellValue(codes.get(i));
        }

        // Write the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE_NAME)) {
            workbook.write(fileOut);
        }

        // Close the workbook
        workbook.close();
    }
}
