package autosuggestion;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.DriverFactory;
import utils.LoginUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinksTest {
    private WebDriver driver;
    LoginUtils loginUtils;

    @Test
    @Parameters({"browser"})
    public void getAllBrokenLinks(String browser) throws InterruptedException {
        driver = DriverFactory.build(browser);
        loginUtils = new LoginUtils(driver);
        loginUtils.login();
        // Find all links on the webpage
        List<WebElement> links = driver.findElements(By.tagName("a"));

        // Iterate through each link and check if it's broken
        for (WebElement link : links) {
            String url = link.getAttribute("href");
            if (url != null && !url.isEmpty()) {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200) {
                        System.out.println("Broken Link: " + url);
                    }
                    connection.disconnect();
                } catch (Exception e) {
                    System.out.println("Exception occurred while checking link: " + url);
                }
            }
        }
    }
    @AfterMethod
    public void tearDown(){
        if(driver !=null) {
            driver.close();
        }
    }
}
