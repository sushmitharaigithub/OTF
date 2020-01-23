package com.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.core.BaseMethods;;



public class Utils {
	private static SimpleDateFormat dateFormat;
	private static Calendar cal;
	private static JavascriptExecutor jse;
	public static Wait<WebDriver> wait = new FluentWait<WebDriver>(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
	public static Actions action;
	public static Select select;
	public static File srcFile;
	public static Alert alert;
	static String monthName;
	static String currentYear;
	static String currentDate;

	public static void jsPageScrollDown() {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("window.scrollBy(0,900)", "");
	}

	public static void jsPageScrollUp() {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("window.scrollBy(0,-900)", "");
	}

	public static void jsPageScrollDowndynamic() {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("window.scrollBy(0,500)", "");
	}

	public static void jsPageScrollVertical() {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("scroll(0, 250);");
	}

	public static void scrolltoElementLocation(Point p) {
		int y = p.getY();
		int newy = y - 250;
		((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get()).executeScript("window.scroll(0," + newy + ")", "");
	}

	public static String generateRandomEmailId() {
		dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		cal = Calendar.getInstance();
		String rnd = generateRandomNumber(0, 9999) + "";
		String emailId = "test" + dateFormat.format(cal.getTime()) + "@ecommerce" + rnd + ".com";
		System.out.println(emailId);
		return emailId;
	}

	public static int generateRandomNumber(int min, int max) {
		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		return rand.nextInt((max - min) + 1) + min;
	}

	public static String getStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}

	public static void hoverOverElement(WebElement element) {
		Actions action = new Actions(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
		action.moveToElement(element).build().perform();
	}

	public static boolean ifElementIsPresentAndDisplayed(List<WebElement> webElement) {
		int counter = 0;
		if (!webElement.isEmpty()) {
			System.out.println("WebElement is present");
			for (int i = 0; i < webElement.size(); i++) {
				if (webElement.get(i).isDisplayed()) {
					System.out.println("WebElement is displayed" + webElement.get(i).getText());
					counter++;
				}
			}
			return counter > 0;
		} else {
			System.out.println("WebElement is not present");
			Assert.fail(webElement + "WebElement is not present");
			return false;
		}
	}

	public static boolean waitForElementToBeClickable(WebElement element, int timeoutInSeconds) {
		try {
			((FluentWait<WebDriver>) wait).withTimeout(Duration.ofSeconds(timeoutInSeconds))
					.ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean waitForElementToBeVisible(WebElement element, int timeoutInSeconds) {
		((FluentWait<WebDriver>) wait).withTimeout(Duration.ofSeconds(timeoutInSeconds))
				.ignoring(NoSuchElementException.class).until(ExpectedConditions.visibilityOf(element));
		return true;
	}

	public static void waitForListOfElementsToBeVisible(List<WebElement> elements, int timeoutInSeconds) {
		WebDriverWait webDriverWait = new WebDriverWait(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get(), timeoutInSeconds);
		webDriverWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public static boolean waitForElementToBeInVisible(WebElement element, int timeoutInSeconds) {
		try {
			((FluentWait<WebDriver>) wait).withTimeout(Duration.ofSeconds(timeoutInSeconds))
					.ignoring(NoSuchElementException.class).until(ExpectedConditions.invisibilityOf(element));
			return true;
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean isClickable(WebElement element) {
		try {
			element.isEnabled();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void javaClick(WebElement element) {
		JavascriptExecutor ex = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		ex.executeScript("arguments[0].click();", element);
	}

	public static void javaverifyELementPresent(WebElement element) {
		String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get()).executeScript(js, element);
		Assert.assertTrue(element.isDisplayed(), "Element : " + element + " is  not displayed");
	}

	public static void javaClickELement(WebElement element) {
		String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
		((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get()).executeScript(js, element);
		element.click();
	}

	public static boolean javaHiddenElementNotPresent(WebElement element) {
		try {
			String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
			((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get()).executeScript(js, element);
			Assert.assertTrue(element.isDisplayed(), "Element: " + element + " is not displayed");
		} catch (NoSuchElementException e) {
			System.out.println(element + " not present");
			return true;
		}
		Assert.fail("Seems " + element.getText() + "  is present Header is configured incorrectly.");
		return false;
	}

	public static void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("window.scrollBy(0,800)", "");
	}

	public static void clickAction(WebElement element) {
		action.moveToElement(element).build().perform();
		action.click(element).build().perform();
	}

	public static boolean switchWindow(String title) {
		return switchWindowByTitle(title);
	}

	// Method to select element by Text
	public static void selectByText(WebElement element, String selectByText) {
		if (element.isDisplayed()) {
			select = new Select(element);
			select.selectByVisibleText(selectByText);
		}
	}

	// Method to select element by Text
	public static void selectByIndex(WebElement element, int selectByIndex) {
		if (element.isDisplayed()) {
			select = new Select(element);
			select.selectByIndex(selectByIndex);
		}
	}
	// method to take screenshot

	public static void getScreenshot() throws IOException {

		srcFile = ((TakesScreenshot) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get()).getScreenshotAs(OutputType.FILE);
		dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		cal = Calendar.getInstance();
		// Code to get caller methodName
		StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
		StackTraceElement e = stacktrace[2];// maybe this number needs to be
											// corrected
		String methodName = e.getMethodName();
		System.out.println(methodName);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "//screenshots" + "//screenshot_"
				+ methodName + "_" + dateFormat.format(cal.getTime()) + ".png"));
	}

	public static void acceptAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.out.println("Alert is not present ! !");
		}
		alert = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().alert();
		alert.accept();
	}

	public static void dismissAlert() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.out.println("Alert is not present !! ");
		}
		alert = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().alert();
		alert.dismiss();
	}

	public static String getAlertText() {
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			System.out.println("Alert is not present  !!");
		}
		alert = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().alert();
		return alert.getText();
	}

	public static void pageRefresh() {
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().navigate().refresh();
	}

	public static void pageForward() {
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().navigate().forward();
	}

	// verify page Title
	public static void verifyTitle(String expectedTitle) {
		String actualTitle = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getTitle();
		System.out.println("Actual Title " + actualTitle);
		Assert.assertTrue(expectedTitle.equalsIgnoreCase(actualTitle), "Title is not present" + actualTitle);
	}

	// verify attribute present and compare value
	public static boolean isAttribtuePresent(WebElement element, String attribute, String expectedValue) {
		Boolean result = false;
		try {
			String value = element.getAttribute(attribute);
			if (value != null && value.equalsIgnoreCase(expectedValue)) {
				result = true;
			}
		} catch (Exception e) {
			System.out.println("Error in identifying attribute or attribute value is not same.");
		}
		return result;
	}

	public static boolean switchWindowByTitle(String title) {
		String currentWindow = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandle();
		Set<String> availableWindows = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(windowId).getTitle().equals(title)) {
					return true;
				} else {
					BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(currentWindow);
				}
			}
		}
		return false;
	}

	public static void switchToactiveWindow() {
		Set<String> availableWindows = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(windowId);
			}
		}
	}

	public static void switchtoDefaultFrame() {
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().defaultContent();
	}

	// Switch frame by Name of the frame
	public static void switchtoFramebyName(String name) {
		try {
			BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().frame(name);
			System.out.println("Inside Frame: " + name);
		} catch (Exception e) {
			System.out.println(name + " Frame not present.");
		}
	}

	// Switch frame by ID of frame
	public static void switchtoFramebyIndex(String index) {
		try {
			BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().frame(index);
		} catch (Exception e) {
			System.out.println("Frame with" + index + " not present.");
		}
	}

	// Switch frame by locator
	public static void switchtoFramebyLocator(WebElement locator) {
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().frame(locator);
	}

	// To Verify element is visible
	public static void verifyElementPresent(WebElement element) {
		Assert.assertTrue(element.isDisplayed(), "Element: " + element + " is not displayed");
	}

	public static void verifyElementsPresent(List<WebElement> element) {
		Assert.assertTrue(!element.isEmpty(), "List of elements not present");
	}

	// Right Click action
	public static void rightclick(WebElement element) {
		action.moveToElement(element).build().perform();
		action.contextClick(element).build().perform();
	}

	// Highlight the current element
	public static void fnHighlightElement(WebElement element) throws InterruptedException {

		waitForAjaxLoad();
		JavascriptExecutor js = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		js.executeScript("arguments[0].style.border='4px solid RED'", element);
		Thread.sleep(500);
		js.executeScript("arguments[0].style.border=''", element);
	}

	// Scroll into view of the element
	public static void scrollIntoView(List<WebElement> element) {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("arguments[0].scrollIntoView(false);", element);
	}

	// Scroll into view of the element
	public static void scrollIntoView(WebElement element) {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("arguments[0].scrollIntoView(false);", element);
	}

	// verify Text element
	public static void verifyText(WebElement element, String expectedData) {
		try {
			String actualData = element.getText();
			System.out.println("Actual Text on element " + actualData);
			Assert.assertTrue(actualData.equalsIgnoreCase(expectedData),
					"Actual and Expected text are different. It should be" + actualData);
		} catch (Exception e) {
			System.out.println("Actual Text for the element is  " + element.getText());
		}
	}

	public static void verifyStringText(String actual, String expectedData) {
		try {
			System.out.println("Actual Text on element" + actual);
			Assert.assertTrue(actual.equalsIgnoreCase(expectedData),
					"Actual and Expected text are different. It should be" + actual + "but found with different "
							+ expectedData);
		} catch (Exception e) {
			System.out.println("Actual Text for the element is  " + actual);
		}
	}

	static int timeout = 300;
	public static void waitForAjaxLoad() {
		JavascriptExecutor executor = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		boolean state = false;
		while (!state) {
			try {
				state = ((Long) (executor).executeScript("return jQuery.active") == 0);
				Thread.sleep(timeout);
				timeout++;
			} catch (Exception e) {
				// no jQuery present
				state = true;
			}
		}
	}

	public static String getTestData(String s) {
		if (s.startsWith("DD")) {
			String testData = splitString(s);
			testData = BaseMethods.CONFIG.getProperty(testData);
			return testData;
		} else {
			return s;
		}
	}

	public static String splitString(String str) {
		System.out.println("Spliting test data -- " + str);
		String leastPerText = str;
		String temp[] = leastPerText.split("-");
		return temp[temp.length - 1];
	}

	public static String getCurrentUrl() {
		return BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getCurrentUrl();
	}

	public static void verifyURL(WebElement element) throws IOException {
		String value = element.getAttribute("href");
		System.out.println("value of URL=" + value);
		URL myurl = new URL(value);
		HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
		int code = connection.getResponseCode();
		System.out.println("code=" + code);
		Assert.assertEquals(code, 200);
	}

	public static void verifyURLList(List<WebElement> element) throws IOException {
		for (WebElement link : element) {
			String value = link.getAttribute("href");
			URL myurl = new URL(value);
			HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
			connection.setRequestMethod("HEAD");
			int code = connection.getResponseCode();
			Assert.assertEquals(code, 200);
		}
	}

	// verifyText overloaded function to compare 2 Strings
	public static void verifyText(String object, String data) {
		Assert.assertEquals(object, data, "Actual text is " + object);
	}

	public static void peviousPage() {
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().navigate().back();
	}

	public static boolean verifyelementnotPresent(WebElement element) {
		try {
			element.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println(element + "not present ");
			return true;
		}
		return false;
	}

	public static boolean verifyelementListnotPresent(List<WebElement> listElement) {
		System.out.println("size of listelement =" + listElement.size());
		try {
			System.out.println("we are in try loop");
			for (WebElement element : listElement) {
				System.out.println("finding list element=" + element);
				element.isDisplayed();
			}
		} catch (NoSuchElementException e) {
			System.out.println(listElement + "not  present");
			return true;
		}
		Assert.fail(" Header is configured incorrectly.");
		return false;
	}

	public void verifyAttributeNotBlank(WebElement atribute) {
		try {
			String imgAtribut = atribute.getAttribute("src");
			if (!imgAtribut.isEmpty()) {
				Assert.assertTrue(true, "Attribute is not blank");
			}
		} catch (NoSuchElementException e) {
			Assert.assertFalse(false, "Attribute is blank");
		}
	}

	public static String iterateOverFeatureFileTableOne(List<List<String>> data) {
		Iterator<List<String>> itr = data.iterator();
		List<String> sList = itr.next();
		return sList.get(0);
	}

	public static boolean isChecked(WebElement element) {
		return element.isSelected();
	}

	public static void scrollTop() {
		jse = (JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		jse.executeScript("window.scrollBy(0,-1000)", "");
	}

	// verify Text element
	public static void verifyText(List<WebElement> listelement, String expectedData) {
		try {
			for (WebElement element : listelement) {
				String actualData = element.getText();
				System.out.println("Actual Text on element" + actualData);
				Assert.assertEquals(actualData, expectedData);
			}
		} catch (Exception e) {
			Assert.fail("Your list text not matching");
		}
	}

	public static void ifElementIsPresent(List<WebElement> webElement) {
		System.out.println("size is: " + webElement.size());
		if (!webElement.isEmpty()) {
			System.out.println("WebElement is present");
			for (int i = 0; i < webElement.size(); i++) {
				if (webElement.get(i).isDisplayed()) {
					System.out.println("WebElement is displayed" + webElement.get(i).getText());
				}
			}
		}
	}

	public static void defaultSelected(WebElement ele, String property) {
		if (ele.getAttribute("class").contains(property)) {
			Assert.assertTrue(true, "Element " + ele + "is not selected default");
		} else {
			Assert.assertFalse(false, "Element " + ele + " is default selected");
		}
	}

	public static boolean verifyElementPresentReturn(WebElement ele) {
		try {
			if (ele.isDisplayed()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(ele + "not present");
			return false;
		}
	}

	// Get Data through JS
	public static String getData(WebElement element) {
		JavascriptExecutor myExecutor = ((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
		String data = (String) myExecutor.executeScript("return arguments[0].textContent", element);
		System.out.println("original data" + data);
		return data.substring(3);
	}

	public static String generateRandomEmailIdLimit20() {
		String rnd1 = generateRandomNumber(0, 9999) + "";
		String rnd2 = generateRandomNumber(0, 9999) + "";
		String emailId = "qa" + rnd1 + "@ecom" + rnd2 + ".com";
		System.out.println(emailId);
		return emailId;
	}

	public static String randomNumber() {
		return generateRandomNumber(0, 9999) + "";
	}

	public static String waitForElementNotVisible(int timeOutInSeconds, WebDriver driver, String elementXPath) {
		if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {
			return "Wrong usage of WaitforElementNotVisible()";
		}
		(new WebDriverWait(driver, timeOutInSeconds))
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXPath)));
		return null;
	}

	public static boolean verifyelementListPresent(List<WebElement> listElement) {
		try {
			for (WebElement element : listElement) {
				element.isDisplayed();
			}
			return true;
		} catch (NoSuchElementException e) {
			System.out.println(listElement + "not present");
			return false;
		}
	}

	public static void verifyelementListPresent1(List<WebElement> listElement) {
		try {
			System.out.println("Listelement = " + listElement.size());
			for (WebElement element : listElement) {
				element.isDisplayed();
			}
		} catch (Exception e) {
			Assert.assertTrue(false, "Element not present");
		}
	}

	public static String getItemFromLocalStorage(String key) {
		JavascriptExecutor myExecutor = ((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
		return (String) myExecutor.executeScript(String.format("return window.localStorage.getItem('%s');", key));
	}

	public static void waitUntilPageisReady() {
		WebDriver driver = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get();
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='loading']")));
	}

	public static void search(String txt, WebElement ele) {
		ele.sendKeys(txt);
		ele.sendKeys(Keys.ENTER);
	}

	public static void openLinkInNewTab(WebElement socialLink) throws InterruptedException {
		Actions action = new Actions(BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
		action.keyDown(Keys.SHIFT).click(socialLink).keyUp(Keys.SHIFT).build().perform();
	}

	public static void windowHandle() throws InterruptedException {
		String perentWindow = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandle();
		Set<String> windowHandle = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandles();
		Iterator<String> itr = windowHandle.iterator();
		while (itr.hasNext()) {
			String s = itr.next();
			System.out.println(s);
			if (!s.equals(perentWindow)) {
				BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(s);
				System.out.println("new window title" + BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getTitle());
				BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().close();
			}
		}
		BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(perentWindow);
	}

	public static void enterData(String value, WebElement element) {
		JavascriptExecutor myExecutor = ((JavascriptExecutor) BaseMethods.WEB_DRIVER_THREAD_LOCAL.get());
		myExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public static void verifyBothElementsEquals(WebElement element1, WebElement element2) {
		Assert.assertEquals(element1, element2, "Mismatch of elements");
	}

	public static void selectByValue(WebElement element, String selectByValue) {
		try {
			if (element.isDisplayed()) {
				select = new Select(element);
				select.selectByValue(selectByValue);
			}
		} catch (Exception e) {
			System.out.println("Error in selecting item from drop downlist " + e);
		}
	}

	public static String getSystemDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public static int getCurrentDate() {
		currentDate = getSystemDate();
		currentDate = currentDate.split("\\-")[0];
		System.out.println(Integer.parseInt(currentDate));
		return Integer.parseInt(currentDate);
	}

	// Return current Month in Numeric : ex: for "May" it will return 5
	public static int getCurrentMonth() {
		String currentMonth = getSystemDate();
		currentMonth = currentMonth.split("\\-")[1];
		System.out.println(Integer.parseInt(currentMonth));
		return Integer.parseInt(currentMonth);
	}

	// Return Month Name in Upper Case letter - ex: MAY
	public static String getCurrentMonthName() {
		Format formatter = new SimpleDateFormat("MMM");
		monthName = formatter.format(new Date()).toUpperCase();
		System.out.println(monthName);
		return monthName;
	}

	public static int getCurrentYear() {
		currentYear = getSystemDate();
		currentYear = currentYear.split("\\-")[2];
		System.out.println(Integer.parseInt(currentYear));
		return Integer.parseInt(currentYear);
	}

	public static void scrollAndHighlightElement(WebElement element) throws InterruptedException {
		waitForAjaxLoad();
		scrollIntoView(element);
		fnHighlightElement(element);
	}
	
	public static boolean switchToactiveWindow1(String title) {
		Set<String> availableWindows = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandles();
		String parentWindow = BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().getWindowHandle();
		availableWindows.remove(parentWindow);
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (BaseMethods.WEB_DRIVER_THREAD_LOCAL.get().switchTo().window(windowId).getTitle().equals(title)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

}
