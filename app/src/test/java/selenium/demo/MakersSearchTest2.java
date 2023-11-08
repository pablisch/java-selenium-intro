package selenium.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakersSearchTest2 {
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
        MakersSearchPage searchPage = new MakersSearchPage(driver);
        searchPage.navigate();
        searchPage.searchFor(searchTerm);
        searchPage.waitForResultsText(resultText);
        assertEquals(resultText, searchPage.getSearchResultsHeading());
    }
//    @Test
//    public void shouldFindSearchResultsForJava() {
//        MakersSearchPage searchPage = new MakersSearchPage(driver);
//        searchPage.navigate();
//        searchPage.searchFor("java");
//        searchPage.waitForResultsText("Results for \"java\"");
//        assertEquals("Results for \"java\"", searchPage.getSearchResultsHeading());
//    }

//    @Test
//    public void shouldNotFindSearchResultsForBadger() {
//        MakersSearchPage searchPage = new MakersSearchPage(driver);
//        searchPage.navigate();
//        searchPage.searchFor("badger");
//        searchPage.waitForResultsText("No results for \"badger\"");
//        assertEquals("No results for \"badger\"", searchPage.getSearchResultsHeading());
//    }

    @AfterAll
    static void closeBrowser() {
        driver.quit();
    }
}
