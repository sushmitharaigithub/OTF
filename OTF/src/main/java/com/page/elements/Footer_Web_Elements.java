package com.page.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Footer_Web_Elements {
	
	@FindBy(xpath="//a[@class='company-logo']/img")
	public static WebElement footerLogo;
	
	@FindBy(xpath="//td[@class='footerbody']")
	public static WebElement footerLegal;
	
	
	@FindBy(xpath="//td[contains(@class,'footer-links')]")
	public static WebElement footerLinks;
	
	
	@FindBy(xpath="//td[@class='social-links']")
	public static WebElement FooterSocialIcon;
	
	
	@FindBy(xpath="//td[@class='social-links']//a")
	public static List<WebElement> footerSocialLinks;

}
