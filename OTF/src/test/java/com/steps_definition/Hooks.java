package com.steps_definition;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.core.BaseMethods;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

	private static final Logger LOG = Logger.getLogger(Hooks.class);

	@Before
	public void openBrowser(Scenario scenario) throws InterruptedException, IOException {
		LOG.debug("message for the browser opening");
		BaseMethods.getBrowserInstance(BaseMethods.browserName.get());
		System.out.println("Browser Launched with the URL" + "\n");
		System.out.println("\"" + scenario.getName() + "\"+ execution started " + "\n");
	}

	@After
	public void tearDown(Scenario scenario) throws IOException, AWTException {

		if (scenario.isFailed()) {

			System.out.println("Scenario " + "\"" + scenario.getName() + "\"+ is " + scenario.getStatus() + "\n");
			System.out.println("Screen shot getting captured for the " + "\"" + scenario.getName() + "\"" + "\n");
			final byte[] screenshot = ((TakesScreenshot) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get())
					.getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");

		}

		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().quit();
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.remove();

		System.out.println("Scenario " + "\"" + scenario.getName() + "\" is " + scenario.getStatus() + "\n");
		System.out.println("\"" + scenario.getName() + "\" execution finished " + "\n");
	}
}
