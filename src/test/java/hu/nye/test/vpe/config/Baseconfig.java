package hu.nye.test.vpe.config;

import hu.nye.test.vpe.factory.WebDriverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("hu.nye.test.vpe")
public class Baseconfig {
    @Bean(destroyMethod = "tearDown")
    WebDriverFactory webDriverFactory() {
            return new WebDriverFactory();
        }
}
