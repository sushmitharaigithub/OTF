package com.page.methods;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.BaseMethods;
import com.page.elements.CopyBlock_Web_Elements;

public class CopyBlock_Web_Methods extends CopyBlock_Web_Elements {
	
	
	public CopyBlock_Web_Methods() {
		PageFactory.initElements(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get(), this);
	}
	
	
	public static void verifyCopyBlockBody()
	{
		Assert.assertTrue(bodyCopy.isDisplayed());
		
	}
	
	
	public static void verifyCopyBlockCTA()
	{
		Assert.assertTrue(CopyBlockCta.isDisplayed());
		
	}
	
	public static void clickCopyBlockCTA() {
		
		
		javaClick(CopyBlockCtaLink);
		
		
	}
	
	
	public static void verifyLink() {
		
		System.out.println("5"+ BaseMethods.CONFIG.getProperty("copyBlockCTA"));
		Assert.assertTrue(switchToactiveWindow1(BaseMethods.CONFIG.getProperty("copyBlockCTA")));
		System.out.println("6");
	}

}
