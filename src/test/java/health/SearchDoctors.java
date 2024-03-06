package health;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class SearchDoctors {
    WebDriver driver = new ChromeDriver();

    @Test
    public void getAllDoctors() throws InterruptedException {
        String url = "https://health.hamropatro.com/doctors";
        driver.get(url);
        Thread.sleep(5000);

        List<WebElement> doctorNameElements = driver.findElements(By.xpath(".//h5"));

        int totalDoctors = doctorNameElements.size();
        System.out.println("Total number of doctors: " + totalDoctors);

        for (WebElement doctorNameElement : doctorNameElements) {

            String doctorName = doctorNameElement.getText().trim();
            System.out.println("Doctor Name: " + doctorName);
        }
        driver.close();
    }

    @Test
    public void getSpecialist() throws InterruptedException {
        String url = "https://health.hamropatro.com/doctors";
        driver.get(url);

        Thread.sleep(5000);
        List<WebElement> doctorElements = driver.findElements(By.xpath(".//h5"));
        List<WebElement> doctorsDetailElements = driver.findElements(By.xpath(".//p"));

        int totalDoctors = Math.min(doctorElements.size(), doctorsDetailElements.size());
        System.out.println("Total number of doctors: " + totalDoctors);

        for (int i = 0; i < totalDoctors; i++) {
            WebElement doctorElement = doctorElements.get(i);
            WebElement doctorDetailElement = doctorsDetailElements.get(i);

            String doctorName = doctorElement.getText().trim();
            String doctorDetail = doctorDetailElement.getText().trim();

            System.out.println("Name: " + doctorName + ":\n " + "Details: " + doctorDetail);
        }
            driver.close();
}
    @Test
    public void getDoctorsFee() throws InterruptedException {
        String url = "https://health.hamropatro.com/doctors";
        driver.get(url);
        Thread.sleep(3000);
        // Wait for the page to load

        // Click on the fee button to reveal the fees
        WebElement feeButton = driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div[3]/div[1]"));
        feeButton.click();

        // Wait for the fees to load
        Thread.sleep(3000);

        // Find all fee elements
        List<WebElement> feeElements = driver.findElements(By.xpath("//*[@id='root']/div/div/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div"));

        // Iterate through fee elements and print fees
        for (WebElement feeElement : feeElements) {
            String doctorFee = feeElement.getText();
            System.out.println("Doctor Fee: " + doctorFee);
        }
        driver.close();
    }
}
