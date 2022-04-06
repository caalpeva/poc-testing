package team.boolbee.acceptance;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
* Acceptance Test
*/
@RunWith(Cucumber.class)
//@CucumberOptions(features = "classpath:acceptance/calculator.feature")
@CucumberOptions(plugin={"pretty", "html:build/test-results/test-report"},
    glue="cucumber.tags.steps",
    features= {"classpath:acceptance/calculator.feature"})
 public class AcceptanceTest {

}
