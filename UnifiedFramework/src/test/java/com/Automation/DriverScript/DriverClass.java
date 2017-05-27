package com.Automation.DriverScript;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.Automation.Utilities.ExcelReader;
import com.Automation.Utilities.PropertyReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDeviceActionShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverClass {

	public static AppiumDriver<MobileElement> mydriver=null;
	public static AppiumDriverLocalService service;
	public static DriverClass D;
	public PropertyReader reader = new PropertyReader();
	public  ExcelReader xlReader  = new ExcelReader();
	
	public void initialSetup(String Element) throws Exception{
		service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File(reader.propertyReader("AppiumDriverExe")))
						.withAppiumJS(new File(reader.propertyReader("AppiumJS")))
						.withLogFile(new File(reader.propertyReader("AppiumLogFile"))));
		service.start();
		DesiredCapabilities cap = new DesiredCapabilities();
		if (Element.equalsIgnoreCase("MobileApp")) {
			cap.setCapability("deviceName", "ca25ba1");
			cap.setCapability("appPackage", "in.amazon.mShop.android.shopping");
			cap.setCapability("appActivity", "com.amazon.micron.StartupActivity");
			mydriver = new AndroidDriver<MobileElement>(new URL(reader.propertyReader("AppiumPort")), cap);
		} else if (Element.equalsIgnoreCase("MobileWebBrowser")) {

			cap.setCapability("platformName", "Android");
			cap.setCapability("deviceName", "ca25ba1");
			cap.setCapability("browserName", "chrome");
			mydriver = new AndroidDriver<MobileElement>(new URL(reader.propertyReader("AppiumPort")), cap);
			mydriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		}

	}
	public DriverClass() throws Exception {
		if(mydriver==null){
		initialSetup("MobileApp");
	//	xlReader.readXL();
		}
	}


	public static void main(String[] args) throws Exception {
		try {

			new DriverClass();
			
			
		} catch (Exception e) {
			System.out.println("Exception!!" + e);
		} finally {
			mydriver.quit();
			service.stop();
		}
	}

}
