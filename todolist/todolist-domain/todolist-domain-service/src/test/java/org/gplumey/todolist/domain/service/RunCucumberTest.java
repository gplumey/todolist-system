package org.gplumey.todolist.domain.service;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("usecases")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,  html:target/cucumber-reports/Cucumber.html")
// other available reports : junit:target/cucumber-reports/Cucumber.xml,  json:target/cucumber-reports/Cucumber.json,timeline:target/cucumber-reports/CucumberTimeline
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.gplumey")
public class RunCucumberTest {
}
