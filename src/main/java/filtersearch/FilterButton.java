package filtersearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilterButton {
    private final WebDriver driver;

    public FilterButton(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnFilterButton() {

        FilterSearchLocators locate = new FilterSearchLocators();
        WebElement filterButton = driver.findElement(By.xpath(locate.getFilterSearchButton()));
        filterButton.click();

    }
}
