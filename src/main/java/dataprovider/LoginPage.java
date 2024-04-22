package dataprovider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Configuration;

public class LoginPage extends BasePage {
    Configuration config = new Configuration();

    private By usernameInput = By.id(config.getCLickOnNextButton());
    private By passwordInput = By.id(config.getPasswordInput());
    private By loginButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.cssSelector("#flash.success");

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
