package selenium.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

public class HelloLupo {
    public static void main(String[] args) throws Exception{

        // Create a new instance of Selenium
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        // Use WebDriver to open a new instance of Chrome
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        // Instruct the driver to browse to the Makers website
//        driver.get("https://makers.tech");
        driver.get("https://lupo.onrender.com/");

        // Take a screenshot of what's currently on the page,
        // and store it in a file 'makers.png' in your project root
//        takeScreenshot(driver, "makers.png");
        takeScreenshot(driver, "lupo.png");

        // Find the title of the webpage (the value inside the HTML
        // <title> element) and print it to the terminal
        System.out.println(driver.getTitle());
        String firstWordOfTitle = driver.getTitle().split(" ")[0];
        System.out.println(firstWordOfTitle);

        // Close down Selenium and end the test
        driver.quit();

        HelloLupo.takeScreenshotsInFirefoxAndEdge();
    }

    // Helper function for taking screenshots using WebDriver
    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }

    public static void takeScreenshotsInFirefoxAndEdge() throws Exception {
        // Create a new instance of Selenium
//        WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        // Use WebDriver to open a new instance of Firefox
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver driver = new ChromeDriver();
        WebDriver driver2 = new FirefoxDriver();

        // Instruct the driver to browse to the Makers website
        driver2.get("https://www.whatismybrowser.com/");

        // Take a screenshot of what's currently on the page,
        takeScreenshot(driver2, "whatFirefox.png");

        // Close down Selenium and end the test
        driver2.quit();

        // Create a new instance of Selenium
        WebDriverManager.chromedriver().setup();
//        WebDriverManager.edgedriver().setup();
//        WebDriverManager.firefoxdriver().setup();
        // Use WebDriver to open a new instance of Firefox
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();
        WebDriver driver3 = new ChromeDriver();

        // Instruct the driver to browse to the Makers website
        driver3.get("https://www.whatismybrowser.com/");

        // Take a screenshot of what's currently on the page,
        takeScreenshot(driver3, "whatChrome.png");

        // Close down Selenium and end the test
        driver3.quit();

    }
}