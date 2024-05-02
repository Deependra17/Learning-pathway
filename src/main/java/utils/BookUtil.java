package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BookUtil {

    public static void verifyAllBookNamesAreNepali(List<String> bookNames) {
        for (String bookName : bookNames) {
            // Verify that each book name is in Nepali language
            if (!isNepaliLanguage(bookName)) {
                Assert.fail("Book language is not Nepali for book: " + bookName);
            }
        }
    }

    private static boolean isNepaliLanguage(String text) {
        // Check if the given text contains characters from the Nepali language
        // You can implement a more robust check based on your requirements
        // This is just a basic example
        boolean containsNepaliCharacters = false;

        // Check if the text contains any Nepali characters
        // You may need to implement a more sophisticated check based on your use case
        if (text != null) {
            // Example: Check for Nepali characters in the Unicode range for Nepali script
            // Adjust this logic based on your specific requirements
            for (char ch : text.toCharArray()) {
                if ((int) ch >= 0x0900 && (int) ch <= 0x097F) {
                    containsNepaliCharacters = true;
                    break;
                }
            }
        }

        return containsNepaliCharacters;
    }

    // Utility method to extract book names from a list of WebElements
    public static List<String> getAllBookNames(WebDriver driver, String xpath) {
        List<WebElement> bookElements = driver.findElements(By.xpath(xpath));
        List<String> bookNames = new ArrayList<>();

        for (WebElement bookElement : bookElements) {
            bookNames.add(bookElement.getText().trim());
        }

        return bookNames;
    }
}
