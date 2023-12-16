package hu.nye.test.vpe.steps;

import java.time.Duration;

import hu.nye.test.vpe.factory.WebDriverFactory;
import hu.nye.test.vpe.pages.MainPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import static hu.nye.test.vpe.helpers.WebAdress.MAIN_PAGE;
import static org.junit.Assert.assertEquals;

public class Steps {
    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(3);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MainPage mainPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    WebDriverFactory webDriverFactory;

    @Given("the {string} page is opened")
    public void thePageIsOpened(String pageName) {
        switch (pageName) {
            case "Main" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(MAIN_PAGE);
            }
            default -> throw new RuntimeException(pageName + "is not a defined page.");
        }
    }

    @When("I close the cookie disclaimer")
    public void iCloseTheCookieDisclaimer() {
        mainPage.acceptCookies();
    }

    @Then("I can see {int} event cards")
    public void iCanSeeEventCards(int expectedCardCount) {
        int actualCardCount = mainPage.getEventCards().size();
        assertEquals(expectedCardCount, actualCardCount);
    }

    @And("the {string} tab is active")
    public void theTabIsActive(String tabName) {
        assertEquals(tabName, mainPage.getActiveTab().getText());
    }

    @And("there are {int} tabs")
    public void thereAreTabs(int expectedTabCount) {
        int actualTabCount = mainPage.getAllTabs().size();
        assertEquals(expectedTabCount, actualTabCount);
    }
}
