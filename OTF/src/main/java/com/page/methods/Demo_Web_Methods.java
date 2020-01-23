package com.page.methods;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.core.BaseMethods;
import com.page.elements.Demo_Web_Elements;

public class Demo_Web_Methods extends Demo_Web_Elements {

	static String glucoseLevel = null;
	
	

	public Demo_Web_Methods() {
		PageFactory.initElements(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get(), this);
	}
	
	
public static void enterUserCrendentials(String arg1, String arg2) throws InterruptedException {
		
		arg1 = BaseMethods.CONFIG.getProperty("userEmail");
		arg2 = BaseMethods.CONFIG.getProperty("password");
		
		scrollAndHighlightElement(userEmail);
		userEmail.clear();
		userEmail.sendKeys(arg1);
		
		scrollAndHighlightElement(password);
		password.clear();
		password.sendKeys(arg2);
		
	}
	
	public static void clickLoginButton() throws InterruptedException {
		
		scrollAndHighlightElement(loginButton);
		loginButton.click();
		waitForAjaxLoad();
		
	}
	
	public static void verifySuccessfulLogin() throws InterruptedException {
		
		scrollAndHighlightElement(welcomeMessage);
		assertTrue(welcomeMessage.getText().equals(BaseMethods.CONFIG.getProperty("welcomeMessage")));
	}

	
	public static String enterBloodGlucoseLevel(String arg1) throws InterruptedException {
		
		glucoseLevel = arg1;
		
		scrollAndHighlightElement(levelsNavBarLink);
		levelsNavBarLink.click();
		waitForAjaxLoad();
		
		scrollAndHighlightElement(addNewButton);
		addNewButton.click();
		waitForAjaxLoad();
		
		scrollAndHighlightElement(levelEntryBox);
		levelEntryBox.clear();
		
		levelEntryBox.sendKeys(glucoseLevel);
		
		scrollAndHighlightElement(submitButton);
		submitButton.click();
		waitForAjaxLoad();
		
		return glucoseLevel;
		
	}
	
	public static void verifyGlucoseLevelEntry() throws InterruptedException {
		
		System.out.println(successfulEntryMsg.getText().trim());
		
		if(successfulEntryMsg.getText().trim().equals(BaseMethods.CONFIG.getProperty("entrySuccessfulMsg"))) {
			scrollAndHighlightElement(successfulEntryMsg);
			assertTrue(glucoseValue.getText().trim().contains(glucoseLevel));
			
		}else {
			
			Assert.fail("Entered Blood Glucose Level is not available in the record table");
		}
		
	}
	
	public static void deleteGlucoseLevel() throws InterruptedException {
		
		for (int i = 0; i < deleteButton.size(); i++) {
			
			scrollAndHighlightElement(deleteButton.get(i));
			deleteButton.get(i).click();
			acceptAlert();
			waitForAjaxLoad();
			
		}

	}
	
	public static void verifyMaxEntriesMessageAllowed() throws InterruptedException {
		
		if(maxEnteries.getText().trim().equals(BaseMethods.CONFIG.getProperty("maxEntriesMsg"))) {
			
			assertTrue(true);
			scrollAndHighlightElement(maxEnteries);
		}
	}

}
