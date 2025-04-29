package extractdata;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExtractData {
    private  WebDriver driver;

    public void extractDataFromAgeCategory() {
     driver = new ChromeDriver();
     driver.get("");
    }
}
