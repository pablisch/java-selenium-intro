package selenium.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class MakersSearchTest {
    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new FirefoxDriver();
    }

    @DisplayName("Test FAQ search terms")
    @ParameterizedTest(name = "Search for {0} should return {1}")
    @CsvSource({
            "Badger, No results for \"Badger\"",
            "java, Results for \"java\"",
            "quality, Results for \"quality\""
    })
    public void testSearchTerms(String searchTerm, String resultText) {
        driver.get("https://makers.tech/");
        driver.findElement(By.linkText("FAQs")).click();
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).click();
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys(searchTerm);
        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"),resultText));
        assertEquals(resultText, driver.findElement(By.cssSelector("h1")).getText());
    }

//    @Test
//    public void shouldFindSearchResultsForJava() {
//        driver.get("https://makers.tech/");
//        driver.findElement(By.linkText("FAQs")).click();
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).click();
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys("java");
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys(Keys.ENTER);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"),"Results for \"java\""));
//        assertEquals("Results for \"java\"", driver.findElement(By.cssSelector("h1")).getText());
//    }

//    @Test
//    public void shouldNotFindSearchResultsForBadger() {
//        driver.get("https://makers.tech/");
//        driver.findElement(By.linkText("FAQs")).click();
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).click();
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys("badger");
//        driver.findElement(By.cssSelector(".kb-search__bar .kb-search__input")).sendKeys(Keys.ENTER);
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBe(By.cssSelector("h1"),"No results for \"badger\""));
//        assertEquals("No results for \"badger\"", driver.findElement(By.cssSelector("h1")).getText());
//    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
