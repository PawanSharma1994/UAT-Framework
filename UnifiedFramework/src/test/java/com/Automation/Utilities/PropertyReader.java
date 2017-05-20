package com.Automation.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {
	
	public static String AppType;
	
	
	
	public String propertyReader(String property) throws FileNotFoundException{
		
		try{
			
			Properties prop = new Properties();
			FileInputStream f = new FileInputStream(System.getProperty("user.dir\\")+"Testproperties.properties");
			prop.load(f);
			prop.getProperty(property);
		}catch(Exception e){
			e.printStackTrace();
		}
		 return property;
	}
	
	 
	
}
