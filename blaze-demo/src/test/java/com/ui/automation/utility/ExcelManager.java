package com.ui.automation.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManager {

	public static XSSFWorkbook wb;
	public static String strExcelFilePath;

	public ExcelManager() throws IOException {
		FileInputStream fisProp = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\settings.properties");
		Properties prop = new Properties();
		prop.load(fisProp);

//		Get the excel file name and path
		strExcelFilePath = prop.getProperty("Test_Data_Path");
		fisProp.close();

//		Load the excel file
		FileInputStream fisExcel = new FileInputStream(System.getProperty("user.dir") + strExcelFilePath);
		wb = new XSSFWorkbook(fisExcel);
	}

	public int getRowCount(String sheetName) {
		int rowCount = -999;
		rowCount = wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}

	public int getColumnCount(String sheetName) {
		int columnCount = -999;
		int rowNumber = 0;
		columnCount = wb.getSheet(sheetName).getRow(rowNumber).getLastCellNum();
		return columnCount;

	}

	public String getCellData(String sheetName, int rowNum, int colNum) {
		XSSFRow row = wb.getSheet(sheetName).getRow(rowNum);
		XSSFCell cell = row.getCell(colNum);
		String cellData = "";
		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			boolean blnValue = cell.getBooleanCellValue();
			cellData = String.valueOf(blnValue);
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			cellData = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			cellData = cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			if (DateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
				cellData = dateFormat.format(cell.getDateCellValue());
			} else {
				double intCellData = cell.getNumericCellValue();
				cellData = String.valueOf(intCellData);
			}
		}

		return cellData;
	}
	
	public int putCellData(String testCase, String columnName, String cellText, String iteration) throws IOException {
		
//		Get the row index
		int rowCount = getRowCount("Test_Data_Sheet");
		int colCount = getColumnCount("Test_Data_Sheet");
		int rowIndex = -999;
		int colIndex = -999;
		
		for(int icol = 0; icol <= colCount; icol++) {
			if(columnName.equals(getCellData("Test_Data_Sheet", 0, icol))) {
				colIndex = icol;
				break;
			}
		}
		
		for(int irow = 0; irow <= rowCount; irow++) {
			if(testCase.equals(getCellData("Test_Data_Sheet", irow, 1)) && iteration.equalsIgnoreCase(getCellData("Test_Data_Sheet", irow, 3))) {
				rowIndex = irow + 1;
				break;
			}
		}
		
		
		
		XSSFRow row = wb.getSheet("Test_Data_Sheet").getRow(rowIndex-1);
		if(row == null) {
			wb.getSheet("Test_Data_Sheet").createRow(rowIndex-1);
		}
		XSSFCell cell = wb.getSheet("Test_Data_Sheet").getRow(rowIndex-1).getCell(colIndex);
		if(cell == null) {
			cell = wb.getSheet("Test_Data_Sheet").getRow(rowIndex-1).createCell(colIndex);
		}
		
		cell.setCellValue(cellText);
		saveExcel();
		
		return 0;
	}
	
	public void saveExcel() throws IOException {
		FileOutputStream fisOutput = new FileOutputStream(System.getProperty("user.dir") + strExcelFilePath);
		wb.write(fisOutput);
		fisOutput.close();
	}

}
