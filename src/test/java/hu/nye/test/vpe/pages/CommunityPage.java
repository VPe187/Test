package hu.nye.test.vpe.pages;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommunityPage {
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;
    @FindBy(css = ".evnt-search-filter .evnt-text-fields.evnt-search")
    private WebElement searchField;
    @FindBy(css = ".evnt-community-card")
    private List<WebElement> eventCards;
    private final WebDriver webDriver;

    public CommunityPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public void searchFor(String text) {
        searchField.sendKeys(text);
    }

    public List<WebElement> getEventCards() {
        return eventCards;
    }
}
