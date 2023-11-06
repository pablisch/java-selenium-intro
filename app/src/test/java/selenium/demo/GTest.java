package selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

public class GTest {
    private static FirefoxDriver driver;

    @BeforeAll
    static void launchBrowser() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    // Your tests will go here!
    @Test
    void shouldLoadHomepage() {
        driver.get("https://www.garethneal.co.uk/");
    }
    @Test
    void shouldLocateGitHubLinkButton() {
        driver.get("https://www.garethneal.co.uk/");
        WebElement collectButton = driver.findElement(By.linkText("COLLECT"));
        System.out.println(collectButton.getText());
    }
    @Test
    void shouldClickToNewsThenProjectsThenCombTableArticle() {
        driver.get("https://www.garethneal.co.uk/");
        WebElement newsButton = driver.findElement(By.linkText("NEWS"));
        System.out.println(newsButton.getText());
        newsButton.click();
//        driver.findElement(By.linkText("NEWS")).click();
        WebElement projectButton = driver.findElement(By.linkText("PROJECTS"));
        System.out.println(projectButton.getText());
        projectButton.click();
//        driver.findElement(By.linkText("PROJECTS")).click();
        WebElement articleIndex5Link = driver.findElement(By.cssSelector(".article-index-5 .image"));
        System.out.println(articleIndex5Link.getText());
        articleIndex5Link.click();
//        driver.findElement(By.cssSelector(".article-index-5 .image")).click();
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }

}
