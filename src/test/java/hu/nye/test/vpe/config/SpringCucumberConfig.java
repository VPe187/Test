package hu.nye.test.vpe.config;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = Baseconfig.class)
public class SpringCucumberConfig {
}
