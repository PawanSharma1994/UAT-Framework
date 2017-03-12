package com.Automation.UnifiedFramework;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class DriverClass {

	public static AndroidDriver<WebElement> mydriver;
	public static AppiumDriverLocalService service;
	public static DriverClass D;
	
	public DriverClass(String Element) throws MalformedURLException{
				service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
				.withAppiumJS(new File("C:/Program Files/Appium/node_modules/appium/bin/appium.js"))
				.withLogFile(new File("C:/Users/pawan/Downloads/Appium docs/logs.txt")));
				service.start();
				DesiredCapabilities cap = new DesiredCapabilities();
				 
				  if(Element.equalsIgnoreCase("MobileApp")){
					  cap.setCapability("deviceName", "ca25ba1");
					  cap.setCapability("appPackage","com.android.dialer");
					  cap.setCapability("appActivity","com.android.dialer.DialtactsActivity");
					  mydriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
				  }else if(Element.equalsIgnoreCase("MobileWebBrowser")){
					  
					  cap.setCapability("platformName", "Android");
					  cap.setCapability("deviceName", "ca25ba1");
				//	  cap.setCapability("platformVersion", "21");
					  cap.setCapability("browserName", "chrome");
					  mydriver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
					  mydriver.manage().timeouts().implicitlyWait(5L,TimeUnit.SECONDS);
				  }
	}
	
	public static void test() throws MalformedURLException, InterruptedException{
		ReusableFunctions r = new ReusableFunctions("");
	//	r.sendKeys("com.android.dialer:id/search_view","");
		Thread.sleep(5000);
	//	r.clickElement("com.android.dialer:id/call_history_button");
	}
	
	public static void main(String[] args) throws MalformedURLException{
		try{
		
		new DriverClass("MobileApp");
		
		test();
		
		}catch(Exception e){
			System.out.println("Exception!!"+e);
		} finally{
			mydriver.quit();
			service.stop();
		}
	}
	
}
