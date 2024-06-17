package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitClass {
    private WebDriver driver;
    private WebDriverWait wait;

    public WaitClass(String bowser, WebDriverWait wait) {
        driver = DriverFactory.build(bowser);   
        this.wait = wait;
    }

    public void useImplicitlyWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBePresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForCondition(ExpectedCondition<?> condition) {
        wait.until(condition);
    }
    public WebElement waitForVisibilityOfAllElementsLocatedBy(By locator){
        return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

    }
}
