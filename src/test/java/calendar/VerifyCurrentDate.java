package calendar;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class VerifyCurrentDate {
    private WebDriver driver;

    public void writeToExcel(String expectedMonth, String actualMonth, String additionalInfo) {
        String filePath = "src/test/java/test_result/test_results.xlsx";
        File file = new File(filePath);
        Workbook workbook;
        Sheet sheet;

        // Create directories if they do not exist
        file.getParentFile().mkdirs();

        if (file.exists()) {
            try (FileInputStream fileIn = new FileInputStream(filePath)) {
                workbook = new XSSFWorkbook(fileIn);
                sheet = workbook.getSheetAt(0);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Test Results");
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Expected Data of the current month");
            headerRow.createCell(1).setCellValue("Actual Data of the current month");
            headerRow.createCell(2).setCellValue("Additional Info");
        }

        // Find the next empty row
        int rowCount = sheet.getLastRowNum();
        Row dataRow = sheet.createRow(++rowCount);

        dataRow.createCell(0).setCellValue(expectedMonth);
        dataRow.createCell(1).setCellValue(actualMonth);
        dataRow.createCell(2).setCellValue(additionalInfo);

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Parameters({"browser"})
    public void testCurrentDate(String browser) throws InterruptedException {
        try {
            CalendarDev dev = new CalendarDev();
            CalendarProd prod = new CalendarProd();
        driver = DriverFactory.build(browser);

            dev.getDevUrl(browser);
            System.out.println("Dev Url: " + driver.getCurrentUrl());
            String actualMonth = dev.verifyDataOfActualMonth();
            System.out.printf("Actual month data: " + dev.verifyDataOfActualMonth());

            prod.getProdUrl(browser);
            Thread.sleep(5000);
            System.out.println("Prod Url: " + driver.getCurrentUrl());
            String expectedMonth = prod.verifyDateOfCurrentMonth();
            System.out.printf("Expected month data: " + prod.verifyDateOfCurrentMonth());

//            String expectedDate = prod.verifyExpectedDate();
//            String actualDate = dev.verifyActualDate();
//        System.out.println("Expected Date:" +expectedDate);
//        System.out.println("Actual Date: "+ actualDate);
//        Assert.assertEquals(actualDate,expectedDate,"Date does not match");


            writeToExcel(prod.verifyDateOfCurrentMonth(), dev.verifyDataOfActualMonth(),"ok");
        } finally {
            driver.quit();
        }
    }
}
