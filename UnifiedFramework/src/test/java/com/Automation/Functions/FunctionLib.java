package com.Automation.Functions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.Automation.DriverScript.DriverClass;

public class FunctionLib extends DriverClass{

	
	public FunctionLib() throws MalformedURLException, FileNotFoundException {
		super();
	}
	
	public void installApp() throws FileNotFoundException{ 
		String ApkFilePath = super.reader.propertyReader("ApkFilePath");
		super.mydriver.installApp(ApkFilePath);
	}
	
	
}
