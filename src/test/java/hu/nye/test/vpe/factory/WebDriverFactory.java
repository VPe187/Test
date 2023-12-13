package hu.nye.test.vpe.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.Objects;

public class WebDriverFactory {
    @Value("${headless:false}")
    private Boolean headless;

    @Value("${browserName:edge}")
    private String browserName;

    @Value("${width:1920}")
    private int width;

    @Value("${height:1080}")
    private int height;

    private WebDriver driver;

    private static final Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(10);

    /**
     * Gets the WebDriver instance, initializing it if necessary.
     *
     * @return The WebDriver instance.
     */
    public WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = setUpWebDriver();
        }
        return driver;
    }

    /**
     * Sets up and returns a new WebDriver instance with configured options.
     *
     * @return The configured WebDriver instance.
     */
    private WebDriver setUpWebDriver() {
        WebDriver driver;
        switch (browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(
                        new ChromeOptions().setHeadless(headless).addArguments("--remote-allow-origins=*")
                );
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(new FirefoxOptions().setHeadless(headless));
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(new EdgeOptions());
            }
            default ->
                    throw new RuntimeException(String.format("The %s as provided browser name is not valid.", browserName));
        }
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        driver.manage().window().setSize(new Dimension(width, height));
        return driver;
    }

    /**
     * Tears down the WebDriver instance, quitting the browser.
     * If the WebDriver instance is not null, it is quitted and set to null.
     */
    public void tearDown() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}
