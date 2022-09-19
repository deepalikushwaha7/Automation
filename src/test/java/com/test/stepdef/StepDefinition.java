package com.test.stepdef;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.test.base.BaseClass;
import com.test.utility.CommonSeleniumFunction;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class StepDefinition {
	WebDriver driver;
	String browserName;
	String winHandleBefore;

	BaseClass base;

	@Before
	public void setBrowser() throws Exception {
		base = new BaseClass();
		browserName = BaseClass.property.get().get("browserType").toString();
		driver = base.browserSetup(browserName, BaseClass.property.get().get("operatingSystem").toString());
	}

	@Given("Homepage is loaded")
	public void homepage_is_loaded() throws Exception {
		driver.get(BaseClass.property.get().get("url").toString());
		winHandleBefore = driver.getWindowHandle();
		CommonSeleniumFunction.waitForPageToLoad(driver);
		Thread.sleep(2000);
	}

	// Click on button. Pass button name as Value
	@And("Click on button with title {string}")
	public void click_on_button_with_title(String string) throws Exception {
		Thread.sleep(600);
		CommonSeleniumFunction.clickOnButton(driver, string);
	}

	// Wait for response page to load
	@And("Click on {string} and wait for response page to Load")
	public void waitfor_ResponsePage(String string) throws Exception {
		CommonSeleniumFunction.clickOnButton(driver, string);
		CommonSeleniumFunction.waitfor_ResponsePage_ToLoad(driver);
	}

	// Click on link. Pass link name as Value
	@And("Click on link with title {string}")
	public void click_on_link_with_title(String string) throws Exception {
		CommonSeleniumFunction.clickOnLink(driver, string);
	}

	// Enter Text in Textbox. Pass text value and textbox name.
	@And("Enter value {string} in the textbox with label {string}")
	public void enter_value_in_textbox(String value, String label) throws Exception {
		CommonSeleniumFunction.enterText(driver, value, label);
	}

	// Verify Text on Screen
	@Then("Check if text {string} is present on the screen")
	public void verfy_text(String value) throws Exception {
		base.waitForNetworkReq();
		CommonSeleniumFunction.verifyText(driver, value);
	}

	@After
	public void closeDriver(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			scenario.attach(CommonSeleniumFunction.getByteScreenshot(driver), "image/png", scenario.getName());
		}
//		driver.quit();
		System.out.println(scenario.getName() + " : [" + scenario.getStatus().toString() + "]");
	}

}