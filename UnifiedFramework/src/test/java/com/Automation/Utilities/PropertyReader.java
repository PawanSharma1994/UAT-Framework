package com.Automation.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader {
	
	public static String AppType;
	
	
	
	public void propertyReader() throws FileNotFoundException{
		
		try{
			
			Properties prop = new Properties();
			FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"");
			prop.load(f);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
}
