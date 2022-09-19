package com.test.runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.test.base.BaseClass;

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

}
