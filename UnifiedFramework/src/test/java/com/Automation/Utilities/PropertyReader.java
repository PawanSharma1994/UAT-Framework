package com.Automation.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertyReader{
	
	public static PropertyReader reader;
	
	public static synchronized PropertyReader get(){
		if(reader==null){
			reader = new PropertyReader();
		}
		return reader;
	}
	 public String propertyReader(String property) throws FileNotFoundException{
		String value="";
		try{
			
			Properties prop = new Properties();
			FileInputStream f = new FileInputStream("Testproperty.properties");
			prop.load(f);
			value =	prop.getProperty(property);
		}catch(Exception e){
			e.printStackTrace();
		}
		 return value;
	}
	
}
