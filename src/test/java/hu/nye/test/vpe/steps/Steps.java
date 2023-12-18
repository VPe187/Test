package hu.nye.test.vpe.steps;

import java.time.Duration;

import hu.nye.test.vpe.factory.WebDriverFactory;
import hu.nye.test.vpe.pages.CommunityPage;
import hu.nye.test.vpe.pages.EventPage;
import hu.nye.test.vpe.pages.MainPage;
import hu.nye.test.vpe.pages.VideoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import static hu.nye.test.vpe.helpers.WebAdress.COMMUNITY_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.EVENT_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.MAIN_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.VIDEO_PAGE;
import static org.junit.Assert.assertEquals;

public class Steps {
    private static final int TIMEOUT_SECONDS = 5;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    WebDriverFactory webDriverFactory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MainPage mainPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    CommunityPage communityPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    VideoPage videoPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    EventPage eventPage;

    @Given("the {string} page is opened")
    public void thePageIsOpened(String pageName) {
        switch (pageName) {
            case "Main" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(MAIN_PAGE);
            }
            case "Community" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(COMMUNITY_PAGE);
            }
            case "Video" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(VIDEO_PAGE);
            }
            case "Event" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(EVENT_PAGE);
            }
            default -> throw new RuntimeException(pageName + " is not a defined page.");
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

    @When("I type {string} into the search field")
    public void iTypeInputIntoTheSearchField(String text) throws InterruptedException {
        Thread.sleep(1000);
        communityPage.searchFor(text);
    }

    @Then("I see {int} cards")
    public void iSeeNumberOfCardsCards(int expectedCardCount) {
        var driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    usedDriver -> communityPage.getEventCards().size() == expectedCardCount
            );
        } catch (TimeoutException e) {
            Assert.fail("Expected card count " + expectedCardCount + " did not match actual card count " +
                    communityPage.getEventCards().size());
        }
    }

    @When("I type {string} into the video search field")
    public void inputSearchVideos(String text) throws InterruptedException {
        Thread.sleep(1000);
        videoPage.searchFor(text);
    }

    @Then("I see {int} video cards")
    public void iSeeNumberOfVideoCards(int expectedVideoCardCount) {
        var driver = webDriverFactory.getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    usedDriver -> videoPage.getVideoCards().size() == expectedVideoCardCount
            );
        } catch (TimeoutException e) {
            Assert.fail("Expected video card count " + expectedVideoCardCount + " did not match actual video card count " +
                    videoPage.getVideoCards().size());
        }
    }

    @Then("I can see {int} upcoming event cards")
    public void iSeeNumberOfUpcomingEvents(int expectedIncomingEvents) {
        var driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    usedDriver -> eventPage.getUpcomingEvents() == expectedIncomingEvents
            );
        } catch (TimeoutException e) {
            Assert.fail("Expected incoming event count " + expectedIncomingEvents + " did not match actual incoming event count " +
                    eventPage.getUpcomingEvents());
        }
    }

}
