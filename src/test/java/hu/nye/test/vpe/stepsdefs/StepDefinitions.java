package hu.nye.test.vpe.stepsdefs;

import java.time.Duration;

import hu.nye.test.vpe.factory.WebDriverFactory;
import hu.nye.test.vpe.pages.ArticlePage;
import hu.nye.test.vpe.pages.CommunityPage;
import hu.nye.test.vpe.pages.EventPage;
import hu.nye.test.vpe.pages.MainPage;
import hu.nye.test.vpe.pages.SpeakerPage;
import hu.nye.test.vpe.pages.VideoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import static hu.nye.test.vpe.helpers.WebAdress.ARTICLE_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.COMMUNITY_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.EVENT_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.MAIN_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.SPEAKER_PAGE;
import static hu.nye.test.vpe.helpers.WebAdress.VIDEO_PAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {
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

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    SpeakerPage speakerPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ArticlePage articlePage;

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
            case "Speaker" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(SPEAKER_PAGE);
            }
            case "Article" -> {
                WebDriver driver = webDriverFactory.getDriver();
                driver.get(ARTICLE_PAGE);
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
            wait.until(usedDriver -> communityPage.getEventCards().size() == expectedCardCount);
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
            wait.until(usedDriver -> videoPage.getVideoCards().size() == expectedVideoCardCount);
        } catch (TimeoutException e) {
            Assert.fail("Expected video card count " + expectedVideoCardCount + " did not match actual video card count " +
                videoPage.getVideoCards().size());
        }
    }

    @Then("I can see {int} upcoming event cards")
    public void iSeeNumberOfUpcomingEvents(int expectedUpcomingEvents) {
        var driver = webDriverFactory.getDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
        try {
            wait.until(usedDriver -> eventPage.getUpcomingEvents() == expectedUpcomingEvents);
        } catch (TimeoutException e) {
            Assert.fail("Expected upcoming event count " + expectedUpcomingEvents + " did not match actual upcoming event count " +
                eventPage.getUpcomingEvents());
        }
    }

    @Then("I can set filter to 'Hungary'")
    public void setFilterToHungary() {
        eventPage.setFilterToHungary();
    }

    @Then("I can see {int} events card in Hungary")
    public void iSeeNumberOfEventsCardsHungary(int expectedEventCards) {
        var driver = webDriverFactory.getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
        try {
            wait.until(webDriver -> {
                jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                return eventPage.getEventCards() == expectedEventCards;
            });
        } catch (TimeoutException e) {
            Assert.fail("Expected incoming event card count " + expectedEventCards + " did not match actual event card count " +
                eventPage.getUpcomingEvents());
        }
    }

    @When("I click filter_tag")
    public void iClickFilterTag(){
        var driver = webDriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("filter_tag")));
        videoPage.clickFilterTag();
    }

    @Then("Filter panel is opened")
    public void filterPanelIsOpened() {
        assertTrue(videoPage.checkFilterPanelOpened());
    }

    @When("I type {string} into the speaker search field")
    public void inputSearchSpeaker(String text) throws InterruptedException {
        Thread.sleep(1000);
        speakerPage.searchFor(text);
    }

    @Then("I see {int} speaker cards")
    public void iSeeNumberOfSpeakerCards(int expectedSpeakerCardCount) {
        var driver = webDriverFactory.getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(webDriver -> {
                jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                return speakerPage.getUserCards() == expectedSpeakerCardCount;
            });
        } catch (TimeoutException e) {
            Assert.fail("Expected speaker cards count " + expectedSpeakerCardCount + " did not match actual speaker cards count " +
                    speakerPage.getUserCards());
        }
    }

    @When("I type {string} into the article search field")
    public void inputSearchArticle(String text) throws InterruptedException {
        Thread.sleep(1000);
        articlePage.searchFor(text);
    }

    @Then("I see {int} article cards")
    public void iSeeNumberOfArticleCards(int expectedArticleCardCount) {
        var driver = webDriverFactory.getDriver();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT_SECONDS))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(webDriver -> {
                jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                return articlePage.getArticleCards() == expectedArticleCardCount;
            });
        } catch (TimeoutException e) {
            Assert.fail("Expected article card count " + expectedArticleCardCount + " did not match actual article card count " +
                    articlePage.getArticleCards());
        }
    }

}
