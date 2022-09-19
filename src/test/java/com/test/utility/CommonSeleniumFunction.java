package com.test.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.test.base.BaseClass;

public class CommonSeleniumFunction extends BaseClass {
	static HashMap<String, String> map = new HashMap<String, String>();

	public static FluentWait<WebDriver> getWait(WebDriver driver, long waitTimeoutSec, long pollTimeMs) {
		return new FluentWait<>(driver).pollingEvery(Duration.ofMillis(pollTimeMs))
				.withTimeout(Duration.ofSeconds(waitTimeoutSec)).ignoring(NoSuchElementException.class);
	}

	public static FluentWait<WebDriver> getWait(WebDriver driver) {
		return getWait(driver, 20, 250);
	}

	public static HashMap readExcel() throws IOException {
		// Create an object of File class to open xlsx file
		String path = System.getProperty("user.dir");
		String fileName = "TestData.xlsx";
		File file = new File(path + "\\fixture\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			workbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet sheet = workbook.getSheet("TestData");
		// Find number of rows in excel file
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		HashMap<String, String> map = new HashMap<String, String>();

		return map;
	}

	// Button click
	public static void clickOnButton(WebDriver driver, String buttonName) {
		FluentWait<WebDriver> wait = getWait(driver);
		try {
			WebElement buttonClick = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='" + buttonName + "']")));
			buttonClick.click();
		} catch (StaleElementReferenceException e) {
			WebElement buttonClick = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='" + buttonName + "']")));
			buttonClick.click();
		}
	}

	// Link click
	public static void clickOnLink(WebDriver driver, String text) {
		FluentWait<WebDriver> wait = getWait(driver);
		WebElement linkClick = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),\"" + text + "\")]")));
		linkClick.click();
	}

	
	// Enter text in textbox
	public static void enterText(WebDriver driver, String value, String label) throws InterruptedException {

		FluentWait<WebDriver> wait = getWait(driver);

		WebElement enterText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@label='" + label + "']")));
		enterText.clear();
		enterText.sendKeys(value);
	}

	// wait for element label to be invisible
	public static void waitForElementLabelToBeInvisible(WebDriver driver, String label) {
		FluentWait<WebDriver> wait = getWait(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@label=\"" + label + "\"]")));
	}

	// wait for element text to be invisible
	public static void waitForElementTextToBeInvisible(WebDriver driver, String text) {
		FluentWait<WebDriver> wait = getWait(driver);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()=\"" + text + "\"]")));
	}

	

	// Verify text
	public static void verifyText(WebDriver driver, String value) {
		FluentWait<WebDriver> wait = getWait(driver);
		WebElement verifyText = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),\"" + value + "\")]")));
		Assert.assertTrue(verifyText.getText().contains(value));
	}

	// Wait for response page to load
	public static void waitfor_ResponsePage_ToLoad(WebDriver driver) {
		FluentWait<WebDriver> wait = getWait(driver, 120, 1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'progress-bar')]")));
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'progress-bar')]")));
	}

	// Check value in Textbox
	public static boolean verifyDataInTextbox(WebDriver driver, String label, String value) {
		FluentWait<WebDriver> wait = getWait(driver);
		WebElement verifyText = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@label='" + label + "']")));
		if (verifyText.isDisplayed() && verifyText.getAttribute("value").equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	// Get Current Date
	public static String getCurrentDate(int offset) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, offset);
		SimpleDateFormat sdf = new SimpleDateFormat("MMddYYYY");
		String sDate = sdf.format(cal.getTime());
		return sDate;
	}

	// Wait for page to load
	public static void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
	}

	// Capture Screenshot for failed cases
	public static String getScreenshot(WebDriver driver) throws IOException { //
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotdir + "image_" + getCurrentDate(0) + ".png"));
		byte[] fileContent = FileUtils.readFileToByteArray(src);
		String screenshot1 = "data:image/png:base64," + Base64.getEncoder().encodeToString(fileContent);
		return screenshot1;
	}

	public static byte[] getByteScreenshot(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}