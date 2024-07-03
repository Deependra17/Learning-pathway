package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Credentials;

import java.time.Duration;
import java.util.Set;

public class LoginPage {
    Credentials cred = new Credentials();
    private WebDriver driver;
    private String parentHandle;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitingTIme() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void getUrl() {
        driver.get("https://reader-dot-lets-read-dev.uc.r.appspot.com");
    }

    public void clickForLogin() throws InterruptedException {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/header/div[3]/div"));
        loginButton.click();
        Thread.sleep(2000);
    }

    public void clickOnLogin() {
        WebElement clickOnLogin = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[1]/div/div[1]/button"));
        clickOnLogin.click();
    }

    public void windowHanding() {
        this.parentHandle = driver.getWindowHandle();
        System.out.println("Parent window - " + parentHandle);
        WebElement continueWithGoogle = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div/div[2]/button"));
        continueWithGoogle.click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
    }

    public void enterEmail() {
        WebElement email = driver.findElement(By.xpath("//*[@id=\"identifierId\"]"));
        email.sendKeys(cred.getUsername());
        email.sendKeys(Keys.ENTER);
    }

    public void enterPassword() throws InterruptedException {
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        passwordField.sendKeys(cred.getPassword());
        passwordField.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.switchTo().window(parentHandle);
        Thread.sleep(5000);

    }

    public void chooseProfile() throws InterruptedException {
        WebElement profile = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"));
        try {
            profile.click();
        } catch (StaleElementReferenceException e) {
            // Handle StaleElementReferenceException
            System.out.println("Element reference is stale. Retrying click...");
            // Refresh the element reference and retry click
            profile = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]"));
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
    }

    public void clickOnHomeButton() throws InterruptedException {
        WebElement homeButton = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/header/div[2]/div[1]"));
        homeButton.click();
        Thread.sleep(3000);
    }

    public String logoElement() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1")));
        WebElement logoText = driver.findElement(By.xpath("//h1"));
        String actualLogoText = logoText.getText();
        return actualLogoText;
    }
}
