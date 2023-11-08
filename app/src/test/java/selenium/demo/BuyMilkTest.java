package selenium.demo;


import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class BuyMilkTest {
//    private static FirefoxDriver driver;
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }
    @BeforeEach
    void loadHomepage() {
        driver.get("https://todomvc.com");
    }
    @Test
    void shouldAddABuyMilkTodo() throws InterruptedException {
        driver.findElement(By.linkText("React")).click();
        String pageTitle = driver.getTitle();
        assertEquals("React • TodoMVC", pageTitle);

        WebElement newTodoInput = driver.findElement(By.cssSelector
                ("input[placeholder='What needs to be done?']"));
        newTodoInput.sendKeys("Buy some milk");
        newTodoInput.sendKeys(Keys.ENTER);

        WebElement milkTodo = driver.findElement(By.xpath
                ("//label[contains(., 'Buy some milk')]"));
        assertEquals("Buy some milk", milkTodo.getText());

        WebElement itemsLeft = driver.findElement(By.cssSelector("span[class='todo-count']"));
        assertEquals("1 item left", itemsLeft.getText());

        driver.findElement(By.cssSelector(".toggle")).click();
        itemsLeft = driver.findElement(By.cssSelector("span[class='todo-count']"));
        assertEquals("0 items left", itemsLeft.getText());
    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
