package com.rishabh.restframework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rishabh.restframework.constants.Constants;
import com.rishabh.restframework.utils.ConfigReader;

public class ExcelUtils {

	private static Logger log = LogManager.getLogger(ExcelUtils.class.getName());
	public static FileInputStream fs;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static List<String> testCases = new ArrayList<String>();
	public static List<String> runStatus = new ArrayList<String>();
	public static List<String> testDescription = new ArrayList<String>();
	public static List<String> priority = new ArrayList<String>();
	public static HashMap<Integer, String> rowAndTestCaseMap = new HashMap<Integer, String>();

	public static Object[][] getTestData(String filePath) {

		String[][] testData = null;
		ArrayList<ArrayList<String>> fullDataList = new ArrayList<>();

		try {
			log.debug("Inside getTestData() method");
			FileInputStream fis = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Identify the test-cases by scanning the 1st row

			int totalRows = sheet.getLastRowNum();
			log.info("Number of rows are : " + totalRows);
			int totalColumns = sheet.getRow(0).getLastCellNum();
			log.info("Number of columns are : " + totalColumns);

			for (int i = 1; i <= 8; i++) {
				ArrayList<String> dataList = new ArrayList<>();
				Row row = sheet.getRow(i);
				// Condition-Check on Specific Cell Value
				if (row.getCell(3).getStringCellValue().equalsIgnoreCase("Female")) {
					log.debug("Data is Found : Female");
				} else {
					log.debug("Data is Found : Male");
				}
				for (int j = 1; j < 4; j++) {
					log.debug(row.getCell(j).getStringCellValue());
					dataList.add(row.getCell(j).getStringCellValue());
				}
				fullDataList.add(dataList);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		testData = fullDataList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
		log.info("getTestData() gets completed");
		return testData;

	}

	/*
	 * Reads the data from the excel sheet and store the values in respective lists
	 * which will be used in annotation transformer class
	 */

	public static void getRunStatus() throws Exception {
		try {
			ConfigReader.configReader();
			String excel_path = Constants.EXCELPATH + File.separator
					+ ConfigReader.getConfigData().get("test_excel_name");
			fs = new FileInputStream(excel_path);
			workbook = new XSSFWorkbook(fs);
			sheet = workbook.getSheet(Constants.RUNMANAGERSHEET);
			for (int i = 1; i <= getLastRowNum(Constants.RUNMANAGERSHEET); i++) {
				testCases.add(getCellContent(Constants.RUNMANAGERSHEET, i, "TestCaseName"));
				testDescription.add(getCellContent(Constants.RUNMANAGERSHEET, i, "Test Case Description"));
				runStatus.add(getCellContent(Constants.RUNMANAGERSHEET, i, "Execute"));
				priority.add(getCellContent(Constants.RUNMANAGERSHEET, i, "Priority"));
			}
			log.trace("Unique Test Cases are : " + testCases.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Takes rowname and sheetname as parameter return row number based of rowname
	 */
	public static int getRowNumForRowName(String sheetname, String rowName) {
		int rownum = 0;
		sheet = workbook.getSheet(sheetname);
		for (int i = 1; i <= getLastRowNum(sheetname); i++) {
			if (rowName.equalsIgnoreCase(sheet.getRow(i).getCell(0).getStringCellValue())) {
				rownum = i;
				break;
			}
		}

		return rownum;
	}

	/*
	 * Takes columnname and sheetname as parameter return column number based of
	 * columnheader
	 */

	public static int getColumnNumForColumnName(String sheetname, String columnname) {
		int colnum = 0;
		sheet = workbook.getSheet(sheetname);
		for (int i = 0; i < getLastColumnNum(sheetname, 0); i++) {
			if (columnname.equalsIgnoreCase(sheet.getRow(0).getCell(i).getStringCellValue())) {
				colnum = i;
				break;
			}
		}

		return colnum;

	}

	/*
	 * Takes sheetname as parameter return last row number of the sheet
	 */
	public static int getLastRowNum(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	/*
	 * Takes sheetname, row number as parameter return last cell number of the row
	 */
	public static int getLastColumnNum(String sheetname, int rownum) {
		return workbook.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}

	/*
	 * Takes sheetname, row number, column number as parameter return cell value
	 */
	public static String getCellContent(String sheetname, int rownum, int colnum) {
		sheet = workbook.getSheet(sheetname);
		Cell cell = sheet.getRow(rownum).getCell(colnum);
		String temp = "";
		if (cell.getCellType() == CellType.STRING) {
			temp = cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
			temp = String.valueOf(cell.getNumericCellValue());
			//To convert 209868.0 to 209868
			temp = String.valueOf((int) Double.parseDouble(temp));
		} else if (cell.getCellType() == CellType.BOOLEAN) {
			temp = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == CellType.ERROR) {
			temp = String.valueOf(cell.getErrorCellValue());
		}
		return temp;
	}

	/*
	 * Takes sheetname, row number, column name as parameter return cell value
	 */
	public static String getCellContent(String sheetname, int rownum, String columnname) {
		sheet = workbook.getSheet(sheetname);
		int colnum = getColumnNumForColumnName(sheetname, columnname);
		return getCellContent(sheetname, rownum, colnum);

	}

	/*
	 * Takes sheetname, row name, column name as parameter return cell value
	 */
	public static String getCellContent(String sheetname, String rowname, String columnname) {
		sheet = workbook.getSheet(sheetname);
		int rownum = getRowNumForRowName(sheetname, rowname);
		int colnum = getColumnNumForColumnName(sheetname, columnname);

		return getCellContent(sheetname, rownum, colnum);

	}

	public static void setCellContent(String sheetname, int rownum, int colnum, String value) {
		sheet = workbook.getSheet(sheetname);
		if (rownum <= getLastRowNum(sheetname)) {
			sheet.getRow(rownum).createCell(colnum).setCellValue(value);
		} else {
			sheet.createRow(rownum).createCell(colnum).setCellValue(value);
		}
	}

	public static void setCellContent(String sheetname, String rowname, int colnum, String value) {
		sheet = workbook.getSheet(sheetname);
		setCellContent(sheetname, getRowNumForRowName(sheetname, rowname), colnum, value);
	}

	public static void setCellContent(String sheetname, int rownum, String colname, String value) {
		sheet = workbook.getSheet(sheetname);
		setCellContent(sheetname, rownum, getColumnNumForColumnName(sheetname, colname), value);
	}

	public static void setCellContent(String sheetname, String rowname, String colname, String value) {
		sheet = workbook.getSheet(sheetname);
		setCellContent(sheetname, getRowNumForRowName(sheetname, rowname),
				getColumnNumForColumnName(sheetname, colname), value);
	}

}
