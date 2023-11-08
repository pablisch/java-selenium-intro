package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BbcLocalNewsPage {
    protected WebDriver driver;
    private By searchBoxBy = By.cssSelector("#ls-c-search__input-label");
    private By firstSearchOptionBy = By.cssSelector("#location-list > li:nth-child(1) > a");
    private String urlStartSection = "https://www.bbc.co.uk/news/topics/";

    public BbcLocalNewsPage(WebDriver driver) { this.driver = driver; }

    public void navigate() { driver.get("https://www.bbc.co.uk/news/localnews"); }

    public String searchFor(String searchTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchBoxBy));
        WebElement searchBox = driver.findElement(searchBoxBy);
        searchBox.sendKeys(searchTerm);
        wait.until(ExpectedConditions.presenceOfElementLocated(firstSearchOptionBy));
        WebElement firstSearchOption = driver.findElement(firstSearchOptionBy);
        firstSearchOption.click();
        wait.until(ExpectedConditions.urlContains(urlStartSection));
        return driver.getCurrentUrl();
    }
}
