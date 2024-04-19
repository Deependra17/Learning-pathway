package dataprovider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                {"test_user", "password123", "Welcome, test_user!"},
                {"admin", "admin123", "Welcome, admin!"}
                // Add more test data as needed
        };
    }

    @Test(dataProvider = "loginData")
    public void testSuccessfulLogin(String username, String password, String expectedMessage) {
        driver.get("https://reader-dot-lets-read-dev.uc.r.appspot.com/login");
        loginPage.login(username, password);
        String successMessage = loginPage.getSuccessMessage();
        Assert.assertTrue(successMessage.contains(expectedMessage));
    }
}
