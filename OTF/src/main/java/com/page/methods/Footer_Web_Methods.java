package com.page.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.BaseMethods;
import com.page.elements.Footer_Web_Elements;

public class Footer_Web_Methods extends Footer_Web_Elements {

	public Footer_Web_Methods() {
		PageFactory.initElements(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get(), this);
	}

	public static void verifyFooterLogo() {
		Assert.assertTrue(footerLogo.isDisplayed());
	}

	public static void verifyFooterLegalCopy() {
		Assert.assertTrue(footerLegal.isDisplayed());

	}

	public static void verifyFooterLinks() {
		Assert.assertTrue(footerLinks.isDisplayed());
		System.out.println("successs");
	}

	public static void verifySocialIcons() {
		Assert.assertTrue(FooterSocialIcon.isDisplayed());
	}

	public static void clickSocialIcons() {

		for (WebElement footerSocialLink : footerSocialLinks) {

			String href = footerSocialLink.getAttribute("href");
			System.out.println("hrf" + href);
			footerSocialLink.click();
			switchToactiveWindow2(href);

		}

	}

	public static boolean switchToactiveWindow2(String title) {

		Set<String> availableWindows = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandles();
		String parentWindow = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandle();
		availableWindows.remove(parentWindow);
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				System.out.println("URLs are "
						+ BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(windowId).getCurrentUrl());
				System.out.println("Titles are " + title);
				if (BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(windowId).getCurrentUrl()
						.equals(title)) {
					BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().close();
					BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(parentWindow);

					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

}
