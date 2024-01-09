package org.gplumey.todolist.domain.service;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("usecases")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.gplumey")
public class RunCucumberTest {
}
