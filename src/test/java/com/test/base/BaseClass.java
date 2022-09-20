package com.test.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v103.network.Network;
import org.openqa.selenium.devtools.v103.network.model.ResourceType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BaseClass {

	public WebDriver driver;
	String path = System.getProperty("user.dir");
	public static ThreadLocal<Properties> property = new ThreadLocal<Properties>();
	public static String configPath = "config/config.properties";
	public static String screenshotdir = System.getProperty("user.dir") + "/test-output/screenshots/";
	volatile Set<String> pendingRequest = new HashSet<String>();

	public static void initializePropertyFile() {

		Properties pr = new Properties();

		try {
			InputStream inStream = new FileInputStream(configPath);
			pr.load(inStream);
			property.set(pr);
		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public WebDriver browserSetup(String browser, String os) throws Exception {
		if (os.equalsIgnoreCase("windows")) {

			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", path + "\\drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
			}

			else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("chromeDriver");
				System.setProperty("webdriver.chrome.driver", path + "\\drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.setHeadless(true); //Set Chrome option
				driver = new ChromeDriver(options);

			}

			else if (browser.equalsIgnoreCase("IE")) {
				System.out.println("IEDriver");
				System.setProperty("webdriver.IE.driver", path + "\\drivers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

			else if (browser.equalsIgnoreCase("Edge")) {
				System.out.println("edgeDriver");
				System.setProperty("webdriver.edge.driver", path + "\\drivers\\msedgedriver.exe");
				driver = new EdgeDriver();
			}
		}

		else if (os.equalsIgnoreCase("mac")) {

			if (browser.equalsIgnoreCase("safari")) {
				System.setProperty("webdriver.safari.driver", path + "\\drivers\\safaridriver.exe");
				driver = new SafariDriver();
			}

			else if (browser.equalsIgnoreCase("chrome")) {
				System.out.println("chromeDriver");
				System.setProperty("webdriver.chrome.driver", path + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}

		}

		driver.manage().window().maximize();
		initializeNetworkListener(browser);
		return driver;
	}

	public void initializeNetworkListener(String browserName) throws Exception {

		DevTools devTools = null;

		switch (browserName) {
		case "Chrome":
			devTools = ((ChromeDriver) driver).getDevTools();
			break;
		case "Edge":
			devTools = ((EdgeDriver) driver).getDevTools();
			break;
		case "firefox":
			// not implemented for firefox
			// devTools = ((FirefoxDriver) driver).getDevTools();
			return;
		default:
			throw new Exception("Invalid browser " + browserName);

		}
		;

		devTools.createSession();
		devTools.send(Network.enable(Optional.of(1024), Optional.of(1024), Optional.of(1024)));

		devTools.addListener(Network.requestWillBeSent(), requestSent -> {
			if (requestSent.getType().get() == ResourceType.XHR)
				pendingRequest.add(requestSent.getRequestId().toString());
		});

		devTools.addListener(Network.responseReceived(), responseReceived -> {
			if (responseReceived.getType() == ResourceType.XHR)
				pendingRequest.remove(responseReceived.getRequestId().toString());
		});

		devTools.addListener(Network.loadingFailed(), loadingFailed -> {
			//System.out.println("Load failed reason: " + loadingFailed.toString());
		});

	}

	public void waitForNetworkReq() throws InterruptedException {
		short waitCount = 0;
		while (pendingRequest.size() > 0 && waitCount < 10) {
			// System.out.println("Pending req : " + pendingRequest.size());
			Thread.sleep(500);
			++waitCount;
		}
		Thread.sleep(500); // Let react process received info
	}

}
