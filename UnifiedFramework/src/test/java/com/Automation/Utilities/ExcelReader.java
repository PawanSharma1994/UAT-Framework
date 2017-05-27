package com.Automation.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.DriverManager;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.html.FormFieldWithNameHistory;

import java.sql.*;

public class ExcelReader extends PropertyReader{

	private String File_Name = "";
	final static String Database_URL = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls, *.xlsx, *.xlsm, *.xlsb)};DBQ=%s;DriverID=22;READONLY=false";
	final static String Driver_Name  = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	public ExcelReader() throws FileNotFoundException{
	  File_Name = super.propertyReader("TestSheetPath");
	  
	}
	
	public void readXLfromDB() throws Exception{
		try{
		File file = new File(File_Name);
		Class.forName(Driver_Name);
		Connection con =  DriverManager.getConnection(String.format(Database_URL,file.getAbsolutePath()));
		Statement stmt = con.createStatement();
		ResultSet rs  = stmt.executeQuery("Select SNO from [BatchRun$]");
		while(rs.next()){
			System.out.println("DB"+rs.getString(1));
			System.out.println(rs.getString("SNO"));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ReadExcel(){
		try{
			
			FileInputStream excelFile = new FileInputStream(new File(File_Name));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheet("BatchRun");
			Iterator<Row> iterator = sheet.iterator();
			
			while(iterator.hasNext()){
				
				Row CurrentRow = iterator.next();
				Iterator<Cell> cellIterator = CurrentRow.iterator();
				while(cellIterator.hasNext()){
					Cell currentCell = cellIterator.next();
					String Value = currentCell.getStringCellValue();
					System.out.println("Value is"+Value);
				}	
			}
			workbook.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
