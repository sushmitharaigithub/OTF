package com.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.core.Utils;

public class Header_Web_Elements extends Utils {
	
	@FindBy(xpath="//td[@class='company-logo']")
	public static WebElement headerLogo;
	
	
	@FindBy(xpath="//td[@class='company-logo']//img/..")
	public static WebElement logoHref;
	
	@FindBy(xpath="//td[@class='company-logo']/a")
	public static WebElement headerLink;

}
