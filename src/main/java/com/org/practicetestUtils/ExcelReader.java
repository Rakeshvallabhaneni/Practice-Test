package com.org.practicetestUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;


public class ExcelReader {
	public String xlPath;
	
	public ExcelReader(String xlWorkbookPath) {
		this.xlPath = xlWorkbookPath;
	}
	
	public Object[][] getData() throws Exception{
		Object[][] data = null;
        FileInputStream excelFile;
		try {
			excelFile = new FileInputStream(new File(this.xlPath));
			HSSFWorkbook wb = new HSSFWorkbook(excelFile);
	        Sheet datasheet = wb.getSheetAt(0);
	        
	        System.out.println(datasheet.getLastRowNum()+1);
	        data = new Object[datasheet.getLastRowNum()][1];
	        List<String> headers= new ArrayList<String>();
	        for(int row = 0; row < datasheet.getLastRowNum()+1; row++) {
	        	
	        	int nCell = 0;
	        	if(row == 0) {
			       nCell = datasheet.getRow(row).getLastCellNum();
			       for(int cell=0;cell < nCell; cell++) {
			    	   Cell col = datasheet.getRow(0).getCell(cell);
			    	   headers.add(col.getStringCellValue());
			       }
	        	}else {
		        	Hashtable<String,String> table = new Hashtable<String,String>();
		        	nCell = datasheet.getRow(row).getLastCellNum();
			        for(int cell=0;cell < nCell; cell++) {
			        	try {
			        		table.put(headers.get(cell), datasheet.getRow(row).getCell(cell).getStringCellValue());
			        	}catch(Exception e) {
			        		table.put(headers.get(cell), "");
			        	}
			        }
			        data[row-1][0] = table;
	        	}
	        }
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
				
	}
}
