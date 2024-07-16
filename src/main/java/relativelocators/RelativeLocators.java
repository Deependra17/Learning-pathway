package relativelocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class RelativeLocators {
    WebDriver driver;
    @BeforeMethod
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(5000);
    }
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void relativeLocator() {
        WebElement login = driver.findElement(By.xpath("//h5"));
        WebElement credentials = driver.findElement(RelativeLocator
                .with(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div"))
                .below(login));
        System.out.println(credentials.getText());
    }
    @Test
    public void testNewWindowTab() {
         int numberOfWindow =3;
         for (int i= 0; i<=numberOfWindow; i++) {
             WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
             newWindow.get("https://petstore.octoperf.com/actions/Catalog.action");
             System.out.println("Title: " + driver.getTitle());
         }
    }
    @Test
    public void testWorkingInBothWindowTab() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");
        System.out.println("Title: "+driver.getTitle());

        //work in the new window or tab
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/form/div[1]/div/div[2]/input")).sendKeys("deepen14chhetri@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/div/form/div[2]/button[2]")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Get the window handle
        Set<String> allWindowTabs = driver.getWindowHandles();
        Iterator<String> iterator = allWindowTabs.iterator();
        String mainFirstWindow = iterator.next();

        //switch and work on the main window or tab
        driver.switchTo().window(mainFirstWindow);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Deepen");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("Deepen123");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
        Thread.sleep(5000);
    }
}
