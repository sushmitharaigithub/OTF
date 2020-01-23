package com.testRunners;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.core.BaseMethods;
import com.generateReports.GenerateReports;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "./src/test/resources/features/",

		tags = { "@Footer2" }, glue = { "com.steps_definition" },

		plugin = { "json:target/chromeOutput/chrome.json" }, strict = false)

public class Chrome_TestRunner extends AbstractTestNGCucumberTests {
	@Parameters("browserValue")

	@BeforeClass
	public void setBrowserValue(String browserValue) {
		BaseMethods.browserName.set(browserValue);

	}

	@AfterClass
	public void setReport() {
		if (System.getProperty("os.name").toLowerCase().contains("mac")) {

			GenerateReports.runShChromeReport();

		} else {

			GenerateReports.runBatChromeReport();

		}

	}

}