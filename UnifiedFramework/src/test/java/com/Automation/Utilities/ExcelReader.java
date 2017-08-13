package com.Automation.Utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.sql.*;

public class ExcelReader{

	public static ExcelReader xlReader;
	public static synchronized ExcelReader get() throws Exception{
		if(xlReader==null){
			xlReader = new ExcelReader();
		}
		return xlReader;
	}
	
	private String xlFileName = PropertyReader.get().propertyReader("TestSheetPath");
	private Fillo fillo = new Fillo();
	private Connection connection;
	private Recordset recordset;
	private int totalRuns =0;
	private String selectedTool ="";
	
	public ExcelReader() throws Exception{
	 connection = fillo.getConnection(xlFileName);
	}
		
	public void closeConnection(){
	recordset.close();
	connection.close();
	}
	
	public int findNumberofRuns() throws Exception{
		try{
			
		String stryQuery = "Select TC_Name from BatchRun where Run='Y'";
		recordset = connection.executeQuery(stryQuery);
		while(recordset.next()){
			String testSelected = recordset.getField("TC_Name");
			totalRuns = recordset.getCount();
			System.out.println("Name of Selected Tests from the Batch Run:"+testSelected);
		}
		closeConnection();
	} catch(Exception e){
		e.printStackTrace();
	}
		return totalRuns;
	}
	
	public String findSelectedTool() throws Exception{
		try{
			String strQuery = "Select Tools from BatchRun Where Run='Y'";
			recordset = connection.executeQuery(strQuery);
			while(recordset.next()){
			selectedTool = recordset.getField("Tools");
			System.out.println("Selected Tool is: "+selectedTool);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return selectedTool;
	}
	
	public static void main(String[] args) throws Exception{
		ExcelReader.get().findSelectedTool();
	}
}
