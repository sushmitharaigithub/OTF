package com.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseMethods {

	public static String env_name;

	public static ThreadLocal<WebDriver> WEB_DRIVER_THREAD_LOCAL = new InheritableThreadLocal<WebDriver>();
	public static ThreadLocal<String> browserName = new InheritableThreadLocal<String>();
	public WebDriver driver = WEB_DRIVER_THREAD_LOCAL.get();

	public static Properties CONFIG = null;
	public static String baseURL;
	public static String OS;
	public static String dirPath;

	@SuppressWarnings("deprecation")
	public static void getBrowserInstance(String browserValue) throws InterruptedException, IOException {
		
		loadConfig();

		if (browserValue.equalsIgnoreCase("Chrome") | browserValue == null) {
			WebDriverManager.chromedriver().setup();
			WEB_DRIVER_THREAD_LOCAL.set(new ChromeDriver());
			browserURL();
			WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();

		} else if (browserValue.equalsIgnoreCase("Firefox")) {

			WebDriverManager.firefoxdriver().setup();
			WEB_DRIVER_THREAD_LOCAL.set(new FirefoxDriver());
			browserURL();
			WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();

		}  else if (browserValue.equalsIgnoreCase("Safari")) {

			SafariOptions options = new SafariOptions();
			options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			options.setCapability(CapabilityType.SUPPORTS_ALERTS, true);

			WEB_DRIVER_THREAD_LOCAL.set(new SafariDriver(options));
			browserURL();
			WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
			WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		}  else if (browserValue.equalsIgnoreCase("IE")) {

			WebDriverManager.iedriver().setup();

			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ieCapabilities.setJavascriptEnabled(true);

			WEB_DRIVER_THREAD_LOCAL.set(new InternetExplorerDriver(ieCapabilities));
			browserURL();
			WEB_DRIVER_THREAD_LOCAL.get().manage().window().maximize();
			WEB_DRIVER_THREAD_LOCAL.get().navigate().to("javascript:document.getElementById('overridelink').click()");

		}

	}

	public static void browserURL() {
		
		WEB_DRIVER_THREAD_LOCAL.get().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		baseURL = CONFIG.getProperty("siteURL");
		System.out.println("Navigating to url " + baseURL);
		WEB_DRIVER_THREAD_LOCAL.get().get(baseURL);

	}

	// load CONFIG
	public static void loadConfig() {
		env_name = System.getProperty("prop.name");

		if (CONFIG == null) {

			try {
				// initialize OR
				if (env_name == null || env_name.equalsIgnoreCase("Testing")) {

					CONFIG = new Properties();

					FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
							+ "//src//main//resources//Profile//Config_Testing.properties");
					CONFIG.load(fs);

				} else if (env_name.equalsIgnoreCase("Prod")) {
					CONFIG = new Properties();

					FileInputStream fs = new FileInputStream(
							System.getProperty("user.dir") + "//src//main//resources//Profile//Config_Prod.properties");
					CONFIG.load(fs);

				}

			} catch (Exception e) {
				System.out.println("Error in intializing properties files");
				e.printStackTrace();
				System.out.println(e.getMessage());
			}

		}

	}

}
