package health;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DoctorsList {
    WebDriver driver = new ChromeDriver();

    @Test
    public void getDoctorList(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                String url = "https://health.hamropatro.com/doctors";
                try {
                    driver.get(url);

                    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
                    List<WebElement> doctorElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("MuiTypography-h5")));

                    int doctorCount = 0;
                    for (WebElement element : doctorElements) {
                        String doctorName = element.getText();
                        System.out.println("Doctor name: " + doctorName);
                        doctorCount++;
                    }
                    System.out.println("Total number of doctors: " + doctorCount);
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                } finally {
                    driver.close();
                }
        }
    @Test
    public void getDoctorPrice(){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            String url = "https://health.hamropatro.com/doctors";
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("MuiTypography-h5")));

            for (WebElement element : priceElements) {
                String doctorName = element.getText();
                WebElement priceElement = element.findElement(By.xpath("./following-sibling::div[contains(@class, 'MuiTypography-h6')]"));

                String price = priceElement.getText();
                System.out.println("Doctor: " + doctorName + ", Price: " + price);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
