package selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class TodoTest {
    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    // Your tests will go here!
    @Test
    void shouldLoadHomepage() {
        driver.get("https://todomvc.com");
    WebElement githubButton = driver.findElement(By.linkText("View on GitHub"));
    System.out.println(githubButton.getText());
    WebElement javascriptExampleLink = driver.findElement(By.cssSelector(".x-scope:nth-child(1) > .tab-content"));
    System.out.println(javascriptExampleLink.getText());


    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
