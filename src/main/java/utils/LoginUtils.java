package utils;

import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

@Getter
public class LoginUtils {
    private final WebDriver driver;

    private String parentHandle;

    public LoginUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws InterruptedException {
        Configuration config = new Configuration();
        Credentials cred = new Credentials();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(config.getUrl());
//        driver.manage().window().maximize();
        List<WebElement> x = driver.findElements(By.tagName("a"));
// To find the count of the link
        System.out.println(x.size());
        driver.findElement(By.xpath(config.getToLogin())).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(config.getClickOnLogin())).click();

        this.parentHandle = driver.getWindowHandle();
        System.out.println("Parent window - " + parentHandle);
        driver.findElement(By.xpath(config.getContinueWithGoogle())).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<String> handles = driver.getWindowHandles();
        if (handles.size() <= 1) {
            System.out.println("No new windows found.");
            return;
        }
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                System.out.println("Switched to new window with handle: " + handle);
                System.out.println("User is on the new window");
            }
        }
        WebElement usernameField = driver.findElement(By.xpath(config.getEmailInput()));
        usernameField.sendKeys(cred.getUsername());
        usernameField.sendKeys(Keys.ENTER);
        if (usernameField.isEnabled()) {
            System.out.println("Input field is enabled");
        } else {
            System.out.println("Not enabled");
        }


        WebElement passwordField = driver.findElement(By.xpath(config.getPasswordInput()));
        passwordField.sendKeys(cred.getPassword());
        passwordField.sendKeys(Keys.ENTER);

        driver.switchTo().window(parentHandle);
        Thread.sleep(5000);
        WebElement profile = driver.findElement(By.xpath(config.getChooseProfile()));
        try {
            profile.click();
        } catch (StaleElementReferenceException e) {
            // Handle StaleElementReferenceException
            System.out.println("Element reference is stale. Retrying click...");
            // Refresh the element reference and retry click
            profile = driver.findElement(By.xpath(config.getChooseProfile()));
            profile.click();
        } catch (ElementClickInterceptedException e) {
            // Handle ElementClickInterceptedException
            System.out.println("Element click intercepted. Retrying click...");
            // Wait for some time and retry click
            Thread.sleep(2000); // Adjust the wait time as needed
            profile.click();
        }
        System.out.println("Profile is Chosen");
        Thread.sleep(2000);
        driver.findElement(By.xpath(config.getClickOnHome())).click();

    }

    public void loginWithInvalidCredentials() throws InterruptedException {
        Configuration config = new Configuration();
        Credentials cred = new Credentials();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(config.getUrl());
        driver.findElement(By.xpath(config.getToLogin())).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath(config.getClickOnLogin())).click();

        this.parentHandle = driver.getWindowHandle();
        System.out.println("Parent window - " + parentHandle);
        driver.findElement(By.xpath(config.getContinueWithGoogle())).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Set<String> handles = driver.getWindowHandles();
        if (handles.size() <= 1) {
            System.out.println("No new windows found.");
            return;
        }
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                driver.switchTo().window(handle);
                System.out.println("Switched to new window with handle: " + handle);
                System.out.println("User is on the new window");
            }
        }
        WebElement usernameField = driver.findElement(By.xpath(config.getEmailInput()));
        usernameField.sendKeys(cred.getUsername());
        usernameField.sendKeys(Keys.ENTER);

        WebElement passwordField = driver.findElement(By.xpath(config.getPasswordInput()));
        passwordField.sendKeys(config.getInvalidPassword());
        System.out.println(config.getInvalidPassword());
        passwordField.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

    }

}
