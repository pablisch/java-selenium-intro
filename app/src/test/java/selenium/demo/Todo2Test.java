package selenium.demo;

import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class Todo2Test {
    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @DisplayName("Test FAQ search terms")
    @ParameterizedTest(name = "Create todo '{0}' in React Todo list")
    @CsvFileSource(resources = "./todo_data.csv")
//    @CsvSource({"Learn Selenium", "Take a break", "Eat lunch", "Go climbing"})
    void testSearchTerms(String newTodo) {
        driver.get("https://todomvc.com");
        driver.findElement(By.linkText("React")).click();
//        driver.find_element_by_css_selector('[data-reactid="your-reactid-value"]')
        driver.findElement(By.cssSelector("[data-reactid='.0.0.1']"))
                .sendKeys(newTodo);
        driver.findElement(By.cssSelector("[data-reactid='.0.0.1']"))
                .sendKeys(Keys.ENTER);
    }
//    @Test
//    void shouldLocateGitHubLinkButton() {
//        driver.findElement(By.linkText("React")).click();
////        driver.find_element_by_css_selector('[data-reactid="your-reactid-value"]')
//        driver.findElement(By.cssSelector("[data-reactid='.0.0.1']"))
//                .sendKeys("Learn Java");
//        driver.findElement(By.cssSelector("[data-reactid='.0.0.1']"))
//                .sendKeys(Keys.ENTER);
//        driver.findElement(By.cssSelector(".toggle")).click();
//        driver.findElement(By.cssSelector(".destroy")).click();
//    }


    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}

