package com.page.methods;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.BaseMethods;
import com.page.elements.Header_Web_Elements;

public class Header_Web_Methods extends Header_Web_Elements {

	public Header_Web_Methods() {

		PageFactory.initElements(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get(), this);
	}

	public static void verifyHeader() {
		Assert.assertTrue(headerLogo.isDisplayed());
		System.out.println("Testing attribute" + logoHref.getAttribute("href"));

	}

	public static void clickLogo() {

		javaClick(headerLink);

	}

	public static void verifyHeaderLink() {
		Assert.assertTrue(switchToactiveWindow1(BaseMethods.CONFIG.getProperty("otfHomepageTitle")));

		System.out.println("Coming after title testing");
	}

	

}
