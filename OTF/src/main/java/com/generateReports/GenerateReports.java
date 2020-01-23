package com.generateReports;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;

public class GenerateReports extends TestListenerAdapter {

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (System.getProperty("surefire.suiteXmlFiles").equalsIgnoreCase("chrome.xml")) {
			// run bat files
			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				runShChromeReport();
			} else {
				runBatChromeReport();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else if (System.getProperty("surefire.suiteXmlFiles").equalsIgnoreCase("firefox.xml")) {

			if (System.getProperty("os.name").toLowerCase().contains("mac")) {
				runShFirefoxReport();
			} else {
				runBatFirefoxReport();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void runBatChromeReport() {

		System.out.println("Generating Chrome HTML reports ....");

		String dirPath = System.getProperty("user.dir");
		System.out.println(dirPath);
		try {
			Runtime.getRuntime().exec("cmd.exe /c" + dirPath + "/batFiles/generateChromeReport.bat");

		} catch (IOException e) {
			System.out.println("Error in running .bat file");
			e.printStackTrace();
		}

		System.out.println("Chrome HTML reports generated");

	}

	public static void runShChromeReport() {

		String dirPath = System.getProperty("user.dir");
		try {

			Runtime.getRuntime().exec("/bin/bash /" + dirPath + "/batFiles/generateChromeReport.sh");
			System.out.println("Generating chrome Execution report !!!");
		}

		catch (IOException e) {
			System.out.println("Error in running .sh file");
			e.printStackTrace();
		}
		System.out.println("Chrome HTML report generated");

	}

	public static void runShFirefoxReport() {

		String dirPath = System.getProperty("user.dir");
		try {
			Runtime.getRuntime().exec("/bin/bash /" + dirPath + "/batFiles/generateFirefoxReport.sh");
			System.out.println("Generating firefox Execution report !!!");

		}

		catch (IOException e) {
			System.out.println("Error in running .sh file");
			e.printStackTrace();
		}
		System.out.println("Firefox HTML report generated");

	}

	public static void runBatFirefoxReport() {

		System.out.println("Generating Firefox HTML reports ....");

		String dirPath = System.getProperty("user.dir");
		try {
			Runtime.getRuntime().exec("cmd.exe /c" + dirPath + "/batFiles/generateFirefoxReport.bat");

		} catch (IOException e) {
			System.out.println("Error in running .bat file");
			e.printStackTrace();
		}

		System.out.println("Firefox HTML reports generated");

	}

}