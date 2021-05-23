package com.rishabh.restframework.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static Logger log = LogManager.getLogger(ExcelUtils.class.getName());

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

}
