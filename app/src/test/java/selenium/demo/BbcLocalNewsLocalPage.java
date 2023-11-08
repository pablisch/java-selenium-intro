package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BbcLocalNewsLocalPage {
    protected WebDriver driver;
    private final By mainHeading = By.cssSelector("#main-heading");

    public BbcLocalNewsLocalPage(WebDriver driver) { this.driver = driver; }

    public void navigate(String url) { driver.get(url); }
    public String getMainHeading() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(mainHeading));
        return driver.findElement(mainHeading).getText();
    }
}
