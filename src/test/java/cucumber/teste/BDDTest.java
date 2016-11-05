package cucumber.teste;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(format = "pretty", snippets = SnippetType.CAMELCASE, features="src/test/resources")
public class BDDTest {


}
