package dataprovider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Configuration;

public class LoginPage extends BasePage {
    Configuration config = new Configuration();

    private final By usernameInput = By.id(config.getCLickOnNextButton());
    private final By passwordInput = By.id(config.getPasswordInput());
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By successMessage = By.cssSelector("#flash.success");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getSuccessMessage() {
        waitForElementVisibility(successMessage, 10);
        return driver.findElement(successMessage).getText();
    }

}
