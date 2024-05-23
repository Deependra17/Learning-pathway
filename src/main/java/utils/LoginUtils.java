package utils;

import locators.LoginLocators;
import lombok.Getter;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.Set;

@Getter
public class LoginUtils {
    private final WebDriver driver;

    private String parentHandle;

    public LoginUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void login() throws InterruptedException {
        LoginLocators locate = new LoginLocators(driver);
        locate.waitingTIme();
        locate.getUrl();
        locate.clickForLogin();
        locate.clickOnLogin();
        locate.windowHanding();
        locate.enterEmail();
        locate.enterPassword();
        locate.chooseProfile();
        locate.clickOnHomeButton();

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
//        System.out.println(config.getInvalidPassword());
        passwordField.sendKeys(Keys.ENTER);
        Thread.sleep(5000);

    }

}
