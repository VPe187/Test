package hu.nye.test.vpe.pages;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpeakerPage {
    @FindBy(css = ".evnt-search-filter .evnt-text-fields.evnt-search")
    private WebElement searchField;
    @FindBy(className = "evnt-user-card")
    private List<WebElement> userCards;
    private final WebDriver webDriver;

    public SpeakerPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchFor(String text) {
        searchField.sendKeys(text);
    }

    public int getUserCards() {
        return userCards.size();
    }
}
