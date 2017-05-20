package com.Automation.UnifiedFramework;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Automation.Utilities.PropertyReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverClass {

	public static AppiumDriver<MobileElement> mydriver;
	public static AppiumDriverLocalService service;
	public static DriverClass D;
	public static PropertyReader reader = new PropertyReader();
	
	
	public void initialSetup(String Element) throws MalformedURLException, FileNotFoundException{
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
						.withAppiumJS(new File("C:/Program Files/Appium/node_modules/appium/bin/appium.js"))
						.withLogFile(new File("C:/Users/pawan/Downloads/Appium docs/logs.txt")));
		service.start();
		DesiredCapabilities cap = new DesiredCapabilities();

		if (Element.equalsIgnoreCase("MobileApp")) {
			cap.setCapability("deviceName", "ca25ba1");
			cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			cap.setCapability("appActivity", "com.amazon.micron.StartupActivity");
			mydriver = new AndroidDriver<MobileElement>(new URL(reader.propertyReader("URL")), cap);
		} else if (Element.equalsIgnoreCase("MobileWebBrowser")) {

			cap.setCapability("platformName", "Android");
			cap.setCapability("deviceName", "ca25ba1");
			cap.setCapability("browserName", "chrome");
			mydriver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
			mydriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		}

	}
	public DriverClass() throws MalformedURLException, FileNotFoundException {

		initialSetup("MobileApp");
	}

	public static void test() throws MalformedURLException, InterruptedException, FileNotFoundException {
		ReusableFunctions r = new ReusableFunctions();
		// r.sendKeys("com.android.dialer:id/search_view","");
		By searchbox = By.id("in.amazon.mShop.android.shopping:id/search_bar_plate");
		By autotext1 = By.id("in.amazon.mShop.android.shopping:id/search_auto_text");
		Thread.sleep(5000);
		 r.clickElement(searchbox);
		 r.sendKeys(autotext1,"Shoes");
		 Thread.sleep(3000);
		 ((AndroidDeviceActionShortcuts) mydriver).pressKeyCode(AndroidKeyCode.KEYCODE_SEARCH);
	}

	public static void main(String[] args) throws MalformedURLException {
		try {

			new DriverClass();

			test();

		} catch (Exception e) {
			System.out.println("Exception!!" + e);
		} finally {
			mydriver.quit();
			service.stop();
		}
	}

}
