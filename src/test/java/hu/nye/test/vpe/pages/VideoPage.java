package hu.nye.test.vpe.pages;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VideoPage {
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;
    @FindBy(css = ".evnt-search-filter .evnt-text-fields.evnt-search")
    private WebElement searchField;
    @FindBy(css = ".evnt-talk-card")
    private List<WebElement> eventVideoCards;
    @FindBy(id = "filter_tag")
    private WebElement filterTag;

    private final WebDriver webDriver;

    public VideoPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void searchFor(String text) {
        searchField.sendKeys(text);
    }

    public List<WebElement> getVideoCards() {
        return eventVideoCards;
    }

    public void clickFilterTag() {
        filterTag.click();
    }

    public boolean checkFilterPanelOpened() {
        return filterTag.getAttribute("aria-expanded").equalsIgnoreCase("true");
    }
}
