package com.page.elements;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.core.Utils;

public class Demo_Web_Elements extends Utils {

	@FindBy(id="user_email")
	public static WebElement userEmail;
	
	@FindBy(id="user_password")
	public static WebElement password;
	
	@FindBy(name = "commit")
	public static WebElement loginButton;
	
	@FindBy(xpath="//div[@class='col-lg-12']//h2")
	public static WebElement welcomeMessage;
	
	@FindBy(xpath = "//input[@name='commit']")
	public static WebElement submitButton;
	
	@FindBy(xpath="//a[contains(text(),'Levels')]")
	public static WebElement levelsNavBarLink;
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	public static WebElement addNewButton;
	
	@FindBy(id="entry_level")
	public static WebElement levelEntryBox;
	
	@FindBy(xpath="//div[@class='alert alert-info fade in']")
	public static WebElement successfulEntryMsg;
	
	@FindBy(xpath="//table[@class='table table-striped']//td[1]")
	public static WebElement glucoseValue;
	
	@FindBy(xpath="//a[contains(text(),'Delete')]")
	public static List<WebElement> deleteButton;
	
	@FindBy(xpath="//div[@class='alert alert-info']")
	public static WebElement maxEnteries;

}
