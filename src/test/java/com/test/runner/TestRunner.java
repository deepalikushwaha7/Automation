package com.test.runner;

import org.apache.commons.mail.EmailException;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.test.base.BaseClass;
import com.test.utility.EmailNotification;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "feature/Test1.feature", 
				 glue = "com.test.stepdef", 
				 tags = (""),
				 plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })


public class TestRunner extends AbstractTestNGCucumberTests {

	@Parameters("browser")
	@BeforeClass
	public static void setConfigrations(@Optional("Chrome") String browser) {
		BaseClass.initializePropertyFile();

		if (!browser.equals("param_val_not_found")) {
			BaseClass.property.get().setProperty("browserType", browser);
		}
	}
	
	@AfterSuite
	public static void sendReport(ITestContext context) {
		EmailNotification emailNotifier = new EmailNotification();
		try {
			emailNotifier.email(context);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
