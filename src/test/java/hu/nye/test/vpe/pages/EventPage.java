package hu.nye.test.vpe.pages;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventPage {
    @FindBy(className = "evnt-event-card")
    private List<WebElement> eventCards;
    @FindBy(className = "evnt-tab-counter")
    private WebElement upcomingEventsSpan;
    @FindBy(id = "filter_location")
    private WebElement filterLocationSelect;
    @FindBy(className = "evnt-filter-item")
    private List<WebElement> evntFilterItems;
    private final WebDriver webDriver;

    public EventPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public int getUpcomingEvents() {
        return Integer.parseInt(upcomingEventsSpan.getText());
    }

    public void setFilterToHungary() {
        filterLocationSelect.click();
        for (WebElement evntFilterItem : evntFilterItems) {
            if(evntFilterItem.getText().equalsIgnoreCase("hungary")) {
                WebElement checkBox = evntFilterItem.findElement(By.className("form-check-label"));
                checkBox.click();
            }
        }
    }

    public int getEventCards() {
        return eventCards.size();
    }
}

