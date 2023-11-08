package selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;


public class GovTest {
    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }
    @BeforeEach
    void loadHomepage() {
          driver.get("https://www.gov.uk/");
//        driver.get("https://todomvc.com");
    }

    @Test
    void shouldSearchForCarTaxAndNavigateToTaxCarPage() {
//        driver.get("https://www.gov.uk/");
        // accept cookies
        driver.findElement(By.cssSelector(".js-confirmation-buttons > .gem-c-button:nth-child(1)")).click();
        // hide message
        driver.findElement(By.cssSelector(".gem-c-cookie-banner__hide-button")).click();
//        WebElement searchBar = driver.findElement(By.id("search-main-8e8ee9d1"));
        // press search with no text entered
        driver.findElement(By.cssSelector(".govuk-\\!-margin-top-5 .gem-c-search__submit")).click();
        // select search bar on new page
        driver.findElement(By.id("search-main")).click();
        // enter text
        driver.findElement(By.id("search-main")).sendKeys("car tax");
        // click search button
        driver.findElement(By.cssSelector(".search-header .gem-c-search__submit")).click();
        // select tax your vehicle
        driver.findElement(By.linkText("Tax your vehicle")).click();
    }
    @Test
    void shouldSelectTaxYourVehicleFromSearchPage() {
        driver.get("https://www.gov.uk/search/all?keywords=car+tax&order=relevance");
        driver.findElement(By.linkText("Tax your vehicle")).click();
    }


    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
