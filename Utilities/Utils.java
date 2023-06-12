package com.Bendigo.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utils {

	//fix the wait time permanently for the entire project
	public static final int IMPLICIT_WAIT_TIME=30;
	public static final int PAGE_LOAD_TIME=20;
   
	public static Object[][] readExcelData(String sheetName) throws IOException {

		File excelfile=new File("C:\\Selenium Software\\BendigoTestData1.xlsx");
		FileInputStream fisexcel=new FileInputStream (excelfile);
		XSSFWorkbook workbook=new XSSFWorkbook(fisexcel);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int RowNum = sheet.getLastRowNum();
		int cellNum = sheet.getRow(0).getLastCellNum();
		//System.out.println(RowNum+" and " +cellNum);
		Object[][] data=new Object[RowNum][cellNum];	
		for(int i=0;i<RowNum;i++) {
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cellNum;j++) {
				XSSFCell cell=row.getCell(j);
				CellType cellType = cell.getCellType();

				switch(cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					//convert the integer cell value to string
					data[i][j] =Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				    //String cellValue=cell.getStringCellValue();
					//data[i-1][j]=cellValue;
                }
			}
		}
		
		workbook.close();
		return data;
	}
	
	public static String getScreenshot(WebDriver driver,String testName) {
		
		File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("dd.MM.yy-HH.mm.ss").format(new Date());
		String path="C:\\Users\\vijayalaxmi.rajaa\\eclipse-workspace\\BendigoBank\\Screenshots\\"+testName+" "+timeStamp+".png";
		try {
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return path;
	}

}
