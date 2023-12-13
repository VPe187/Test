package hu.nye.test.vpe.steps;

import java.time.Duration;

import hu.nye.test.vpe.factory.WebDriverFactory;
import hu.nye.test.vpe.pages.MainPage;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import static hu.nye.test.vpe.helpers.WebAdress.MAIN_PAGE;

public class Steps {
    private static final Duration TIMEOUT_SECONDS = Duration.ofSeconds(3);

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    MainPage mainPage;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    WebDriverFactory webDriverFactory;

    @Given("the {string} site is opened")
    public void thePageIsOpened(String pageName) {
        WebDriver driver = webDriverFactory.getDriver();
        driver.get(MAIN_PAGE);
    }
}
