package selenium.demo;

import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BbcTest {
    private static FirefoxDriver driver;

       @BeforeAll
       static void launchBrowser() {
           WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
       }
       @BeforeEach
       void loadHomepage() {
           driver.get("https://www.bbc.co.uk/news/localnews");
       }
       @Test
       void shouldLocateGitHubLinkButton() {

           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ls-c-search__input-label")));

           driver.findElement(By.id("ls-c-search__input-label")).click();
           driver.findElement(By.id("ls-c-search__input-label")).sendKeys("London");
           driver.findElement(By.id("ls-c-search__input-label")).sendKeys(Keys.ENTER);
           driver.findElement(By.id("ls-c-search__input-label")).sendKeys(Keys.ENTER);

           WebElement searchSubOption = driver.findElement
                   (By.cssSelector("#location-list > li:nth-child(1) > a > span.gel-long-primer"));
           searchSubOption.click();

           WebElement mainHeading = driver.findElement(By.id("main-heading"));
           mainHeading.click();
           WebElement moreLocalNews = driver.findElement(By.linkText("More Local News"));
           moreLocalNews.click();
       }






       @AfterAll
       static void closeBrowser() {
           driver.quit();
       }

}
