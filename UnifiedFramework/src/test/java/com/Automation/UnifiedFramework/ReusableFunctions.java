package com.Automation.UnifiedFramework;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class ReusableFunctions extends DriverClass {

	

	public ReusableFunctions() throws MalformedURLException, FileNotFoundException {
		super();
	}

	protected void sendKeys(By Object, String sendText) {
		try {
			mydriver.findElement(Object).sendKeys(sendText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickElement(By Object) {
		try {
			mydriver.findElement(Object).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
}
