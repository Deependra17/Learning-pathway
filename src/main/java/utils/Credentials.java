package utils;

import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

@Getter
public class Credentials {

    private String Username;
    private String Password;

    public Credentials() {
        String filePath = "/home/deependra17/inputs/credentials.xlsx";
        try (
                FileInputStream fileInputStream = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming credentials are in the first sheet

            for (Row row : sheet) {
                Cell usernameCell = row.getCell(0); // Assuming username is in the first column
                Cell passwordCell = row.getCell(1); // Assuming password is in the second column

                if (usernameCell != null && passwordCell != null) {
                    this.Username = usernameCell.getStringCellValue();
                    this.Password = passwordCell.getStringCellValue();

                    System.out.println("Username: " + getUsername());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
