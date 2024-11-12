package calendar;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class VerifyCurrentMonthData {
    private WebDriver driver;
    private String devUrl = "https://app-dev.hamrostack.com/calendar?v=patro";
    private String prodUrl = "https://www.hamropatro.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyCurrentMonthData() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(devUrl);
        WebElement currentYearAndMonth = driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/div/div[1]/div/div[2]/span[1]"));
        String currentYearAndMonthText = currentYearAndMonth.getText();
        System.out.println("Current Date and Month: " + "\n" + currentYearAndMonth.getText());

        WebElement weekData = driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/div/div[2]"));
        String weekDataText = weekData.getText();
        System.out.println("Week data: " + "\n" + weekData.getText());

        WebElement wholeMonthDate = driver.findElement(By.xpath("//*[@id=\"calendar\"]/div/div/div[3]"));
        String wholeMonthDateText = wholeMonthDate.getText();
        System.out.println("Whole Month data: " + "\n" + wholeMonthDate.getText());

        // Define the path to the test_results package
        Path path = Paths.get("src/test/test_result/test_results.xlsx");

        // Ensure the directory exists
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");

        // Create header and data rows
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Current Year and Month");
        headerRow.createCell(1).setCellValue("Week Data");
        headerRow.createCell(2).setCellValue("Whole Month Data");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(currentYearAndMonthText);
        dataRow.createCell(1).setCellValue(weekDataText);
        dataRow.createCell(2).setCellValue(wholeMonthDateText);

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(path.toFile())) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
