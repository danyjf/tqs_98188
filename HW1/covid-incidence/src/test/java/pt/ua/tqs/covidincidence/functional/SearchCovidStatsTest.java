package pt.ua.tqs.covidincidence.functional;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("pt/ua/tqs/covidincidence/functional")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "pt.ua.tqs.covidincidence.functional")
public class SearchCovidStatsTest {
}
