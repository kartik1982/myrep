package com.xirrus.xms.globallib;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DataFile_Reader {
	// Create variable for filename, path, input file stream and output file

	public static String fileName = System.getProperty("user.dir")
			+ "\\src\\config\\testcases\\TestData.xlsx";
	public String path;
	public FileInputStream fileInput = null;
	public FileOutputStream fileOutput = null;
	// create object for workbook, worksheet and cee with row
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	
	/**
	 * This constructor will accept path with file name and create workbook
	 * 
	 * @param path
	 */
	public DataFile_Reader(String path) {
		this.path = path;
		try {
			// Get the workbook instance for XLSX file
			fileInput = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInput);
			sheet = workbook.getSheetAt(0);
			fileInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This function is to get total number of rows present in selected
	 * worksheet
	 * 
	 * @param SheetName
	 * @return
	 */
	public int getRowCount(String SheetName) {
		int rCount = 0;
		int index = workbook.getSheetIndex(SheetName);
		if (index == -1)
			return rCount;
		else {
			sheet = workbook.getSheetAt(index);
			rCount = sheet.getLastRowNum() + 1;
		}
		return rCount;
	}
	
	
	/**
	 * This function will return contain on cell using sheet column name and row
	 * num
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @return
	 */
	public String getCellContain(String sheetName, String colName, int rowNum) {
		String data = "";
		int colNum = -1;
		try {
			if (rowNum <= 0)
				return data;
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return data;
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			// Find which column looking for
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim()
						.equalsIgnoreCase(colName.trim()))
					colNum = i;
			}
			if (colNum == -1)
				return data;
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return data;
			cell = row.getCell(colNum);
			
			if (cell == null)
				return data;
			
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				data = String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(DateUtil.getJavaDate(d));
					data = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(2);
					data = cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.MONTH) + 1 + "/" + data;
				}
				return data;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return data;
			else
				return String.valueOf(cell.getBooleanCellValue());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colName
					+ " does not exist in xls";
		}
	}
	
	
	/**
	 * This function will return cell contain by suing Column and row number
	 * 
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return
	 */
	public String getCellContain(String sheetName, int colNum, int rowNum) {
		String data = "";
		try {
			if (rowNum <= 0)
				return data;
			
			int index = workbook.getSheetIndex(sheetName);
			
			if (index == -1)
				return data;
			
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return data;
			cell = row.getCell(colNum - 1);
			if (cell == null)
				return data;
			
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
				
				data = String.valueOf(cell.getNumericCellValue());
				return data;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return data;
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum
					+ " does not exist  in xls";
		}
	}
	
	
	/**
	 * This function is used to create new worksheet in curruent workbook
	 * 
	 * @param sheetname
	 * @return
	 */
	public boolean addWorkSheet(String sheetname) {
		try {
			workbook.createSheet(sheetname);
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
			fileOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * This function is to delete worksheet from current workbook
	 * 
	 * @param sheetName
	 * @return
	 */
	public boolean deleteWorkSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;
		try {
			workbook.removeSheetAt(index);
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
			fileOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * This function is used to set value for cell with column name and Row
	 * Number
	 * 
	 * @param sheetName
	 * @param colName
	 * @param rowNum
	 * @param data
	 * @return
	 */
	public boolean setCellData(String sheetName, String colName, int rowNum,
			String data) {
		try {
			fileInput = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInput);
			if (rowNum <= 0)
				return false;
			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().trim()
						.equalsIgnoreCase(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;
			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);
			
			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);
			
			
			// cell style
			CellStyle cs = workbook.createCellStyle();
			cs.setWrapText(true);
			cell.setCellStyle(cs);
			cell.setCellValue(data);
			
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
			fileOutput.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * To check passed worksheet name is present or not
	 * 
	 * @param sheetName
	 * @return
	 */
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}
	
	
	/**
	 * This function is used to get column count in present worrksheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
			return -1;
		return row.getLastCellNum();
	}
	
	
	/**
	 * This function is to get row number for column name and cell value
	 * 
	 * @param sheetName
	 * @param colName
	 * @param cellValue
	 * @return
	 */
	public int getCellRowNum(String sheetName, String colName, String cellValue) {
		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellContain(sheetName, colName, i).equalsIgnoreCase(
					cellValue)) {
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * This function is to added column into current worksheet
	 * 
	 * @param sheetName
	 * @param colName
	 * @return
	 */
	public boolean addColumn(String sheetName, String colName) {
		try {
			fileInput = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInput);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;
			
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());
			cell.setCellValue(colName);
			cell.setCellStyle(style);
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
			fileOutput.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	/**
	 * This function is to remove column and its contain
	 * 
	 * @param sheetName
	 * @param colNum
	 * @return
	 */
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fileInput = new FileInputStream(path);
			workbook = new XSSFWorkbook(fileInput);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			style.setFillPattern(CellStyle.NO_FILL);
			
			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOutput = new FileOutputStream(path);
			workbook.write(fileOutput);
			fileOutput.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
}
