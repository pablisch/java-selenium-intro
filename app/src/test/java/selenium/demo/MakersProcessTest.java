package selenium.demo;


import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MakersProcessTest {
    private static FirefoxDriver driver;


    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
    @BeforeEach
    void loadHomepage() {
        driver.get("https://makers.tech/");
    }
    @Test
    void shouldLocateGitHubLinkButton() throws InterruptedException {
        String pageTitle = driver.getTitle();
        String expectedTitleExtract = "Change Your Life";
        assert pageTitle.contains(expectedTitleExtract);

        WebElement conductLink = driver.findElement(By.cssSelector
                (".column:nth-child(7) a > strong"));
//        System.out.println(conductLink.getText());
        assertEquals("Code of Conduct", conductLink.getText());

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", conductLink);

        expectedTitleExtract = "Code of Conduct";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.titleContains(expectedTitleExtract));
        pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        assert pageTitle.contains(expectedTitleExtract);

        // Return to Homepage
        driver.findElement(By.linkText("Makers Academy")).click();
        driver.findElement(By.linkText("FAQs")).click();
        String expectedUrl = "https://faq.makers.tech/en/knowledge";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        assertEquals(expectedUrl, actualUrl);

        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input"))
                .click();
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input"))
                .sendKeys("badger");
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input"))
                .sendKeys(Keys.ENTER);

        String expectedUrlExtract = "term=badger";
        wait.until(ExpectedConditions.urlContains(expectedUrlExtract));

        WebElement searchResults = driver.findElement(By.cssSelector
                ("h1[class='kb-search-results__heading']"));
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe
                (By.cssSelector("h1[class='kb-search-results__heading']"), "")));

        assertEquals("No results for \"badger\"", searchResults.getText());

    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
