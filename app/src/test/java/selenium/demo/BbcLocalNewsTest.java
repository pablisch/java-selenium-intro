package selenium.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class BbcLocalNewsTest {
    private static FirefoxDriver driver;
    @BeforeAll
    static void launchBrowser() {
        driver = new FirefoxDriver();
    }
    @DisplayName("Test Local News search terms")
    @ParameterizedTest(name = "Search for {0} should return main heading {0}")
    @CsvSource({"London", "Bury", "Scunthorpe", "Bognor Regis"})
    public void testSearchTerms(String searchTerm) {
        BbcLocalNewsPage searchPage = new BbcLocalNewsPage(driver);
        BbcLocalNewsLocalPage localNewsPage = new BbcLocalNewsLocalPage(driver);
        searchPage.navigate();
        String resultsUrl = searchPage.searchFor(searchTerm);
        localNewsPage.navigate(resultsUrl);
        String mainHeading = localNewsPage.getMainHeading();
        assertEquals(searchTerm, mainHeading);
    }
//    @Test
//    public void shouldSearchForLocalNewsInLondonAndLoadPageWithLondonHeading() throws InterruptedException {
//        String searchTerm = "London";
//        BbcLocalNewsPage searchPage = new BbcLocalNewsPage(driver);
//        BbcLocalNewsLocalPage localNewsPage = new BbcLocalNewsLocalPage(driver);
//        searchPage.navigate();
//        String resultsUrl = searchPage.searchFor(searchTerm);
//        localNewsPage.navigate(resultsUrl);
//        String mainHeading = localNewsPage.getMainHeading();
//        assertEquals(searchTerm, mainHeading);
//    }
//    @Test
//    public void shouldSearchForLocalNewsInBuryAndLoadPageWithBuryHeading() throws InterruptedException {
//        String searchTerm = "Bury";
//        BbcLocalNewsPage searchPage = new BbcLocalNewsPage(driver);
//        BbcLocalNewsLocalPage localNewsPage = new BbcLocalNewsLocalPage(driver);
//        searchPage.navigate();
//        String resultsUrl = searchPage.searchFor(searchTerm);
//        localNewsPage.navigate(resultsUrl);
//        String mainHeading = localNewsPage.getMainHeading();
//        assertEquals(searchTerm, mainHeading);
//    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
