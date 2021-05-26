package com.rishabh.restframework.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.rishabh.restframework.constants.Constants;
import com.rishabh.restframework.utils.ConfigReader;

public class TestUtils

{
	/*
	 * Test Name in the RUNMANAGER should be matching any @Test methods in the class
	 * files mentioned in testng.xml DataProvider method used to provide data for
	 * multiple iterations.
	 * 
	 * So the test name in the RUNMANAGER should be same as first column in TESTDATA
	 * and sheet name should also be same as test name.
	 */
	@DataProvider(name = "dataProviderForIterations", parallel = false)
	public static Object[][] getDataForIterations(Method m) {
		ConfigReader.configReader();
		String excel_path = Constants.EXCELPATH + File.separator + ConfigReader.getConfigData().get("test_excel_name");
		return getDataForDataprovider(excel_path, Constants.TESTDATASHEETNAME, m.getName());
	}

	/*
	 * Finding number of iterations available for test case and return the data
	 * accordingly. Using hashtable avoids multiple parameters entry to the test
	 * case.
	 * 
	 */
	private static Object[][] getDataForDataprovider(String testdata, String sheetname, String testcasename) {

		int totalcolumns = ExcelUtils.getLastColumnNum(sheetname, 0);
		ArrayList<Integer> rowscount = getNumberofIterationsForATestCase(sheetname, testcasename);
		Object[][] b = new Object[rowscount.size()][1];
		Hashtable<String, String> table = null;
		for (int i = 1; i <= rowscount.size(); i++) {
			table = new Hashtable<String, String>();
			for (int j = 0; j < totalcolumns; j++) {
				table.put(ExcelUtils.getCellContent(sheetname, 0, j),
						ExcelUtils.getCellContent(sheetname, rowscount.get(i - 1), j));
				b[i - 1][0] = table;
			}
		}
		return b;
	}

	/*
	 * Used to return the rownumber of the test cases for multiple iterations.
	 * Suppose if testcase 1 is available in row 4 and 7 is test data , it return
	 * the arraylist with values 4,7 If the particular iteration is set to yes in
	 * the test data sheet -->Then it will be executed. Otherwise ignored.
	 */
	private static ArrayList<Integer> getNumberofIterationsForATestCase(String sheetname, String testcasename) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 1; i <= ExcelUtils.getLastRowNum(sheetname); i++) {
			if (testcasename.equalsIgnoreCase(ExcelUtils.getCellContent(sheetname, i, 0))) {
				if (ExcelUtils.getCellContent(sheetname, i, 1).equalsIgnoreCase("Yes")) {
					a.add(i);
				}
			}
		}

		return a;
	}


}
