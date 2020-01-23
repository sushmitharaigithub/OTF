package com.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.core.Utils;

public class CopyBlock_Web_Elements extends Utils{
	
	@FindBy(xpath="//td[contains(@class,'inner contents')]")
	public static WebElement bodyCopy;
	
	@FindBy(xpath="//td[@class='cta-cont']")
	public static WebElement CopyBlockCta;
	
	@FindBy(xpath="//td[@class='cta-cont']/a")
	public static WebElement CopyBlockCtaLink;

}
