package hu.nye.test.vpe.pages;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class MainPage {
    @FindBy(xpath = "//*[@id=\"app\"]/header/div/div/ul[1]/li[1]/a")
    private WebElement communitiesButton;

    private final WebDriver webDriver;

    public MainPage(WebDriverFactory webDriverFactory) {
        this.webDriver = webDriverFactory.getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getCommunitiesButton() {
        return communitiesButton;
    }
}
