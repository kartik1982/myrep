package com.xirrus.xms.globallib;

public class Test_Utility {

	/**
	 * This function is to check run mode for dataset is Yes or No through
	 * passing row number
	 * 
	 * @param xls
	 * @param workSheet
	 * @param rowNum
	 * @return
	 */
	public static boolean isTestdataRunable(DataFile_Reader xls,
			String workSheet, int rowNum) {
		boolean isExecutable = false; // variable to hold executable status
		// Reading from workbook and work-sheet passed in function to get suite
		// execution status
		String run = xls.getCellContain(workSheet, "RunMode", rowNum);
		if (run.equalsIgnoreCase("Y")) {
			isExecutable = true;
		} else {
			isExecutable = false;
		}

		xls = null; // release memory
		return isExecutable;
	}

	/**
	 * This function is to find which suite is going to run in current project
	 * from file passed into function INPUT: object of "DataSheet_Reader" class
	 * and Suite Name. OUTPUT: TRUE= Suite is going to execute and FALSE= suite
	 * is not going to execute Note: Test work-sheet should have TSID= test case
	 * ID and RunMode column.
	 */
	public static boolean isTestSuiteRunnable(DataFile_Reader xls,
			String suiteName) {
		boolean isExecutable = false; // variable to hold executable status
		// Reading from workbook and work-sheet passed in function to get suite
		// execution status
		for (int i = 2; i <= xls.getRowCount("TestSuite"); i++) {
			String name = xls.getCellContain("TestSuite", "TSID", i);
			String run = xls.getCellContain("TestSuite", "RunMode", i);
			if (name.equalsIgnoreCase(suiteName)) {
				if (run.equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}
		xls = null; // release memory
		return isExecutable;
	}

	/**
	 * This function is to find which Test Case is going to run in current
	 * project from file passed into function INPUT: object of
	 * "DataSheet_Reader" class and Test Name. OUTPUT: TRUE= Suite is going to
	 * execute and FALSE= suite is not going to execute Note: Test work-sheet
	 * should have TCID= test case ID and RunMode column.
	 */
	public static boolean isTestCaseRunnable(DataFile_Reader xls,
			 String testName) {
		boolean isExecutable = false; // variable to hold executable status
		// Reading from workbook and work-sheet passed in function to get suite
		// execution status
		for (int i = 2; i <= xls.getRowCount("TestCase"); i++) {
			String name = xls.getCellContain("TestCase", "TCID", i);
			String run = xls.getCellContain("TestCase", "RunMode", i);
			if (name.equalsIgnoreCase(testName)) {
				if (run.equalsIgnoreCase("Y")) {
					isExecutable = true;
				} else {
					isExecutable = false;
				}
			}
		}
		xls = null; // release memory
		return isExecutable;
	}

	/**
	 * This function is used to get two dimensional array data from test case if
	 * file is not present just return empty array
	 * 
	 * @param xls
	 * @param worksheet
	 * @param testCase
	 * @return
	 */
	public static Object[][] getTestData(DataFile_Reader xls, String worksheet,
			String testCase) {
		// IF sheet is not present return nothing blank array
		if (!xls.isSheetExist(worksheet)) {
			xls = null; // release memory
			return new Object[1][0];
		}

		Object data[][] = new Object[xls.getRowCount(worksheet) - 1][xls
				.getColumnCount(worksheet) - 1];
		for (int rowCount = 2; rowCount <= xls.getRowCount(worksheet); rowCount++) {
			for (int colCount = 1; colCount <= xls.getColumnCount(worksheet) - 1; colCount++) {
				data[rowCount - 2][colCount - 1] = xls.getCellContain(
						worksheet, colCount, rowCount);
			}
		}
		return data;
	}

	public static void reportDatasetResult(DataFile_Reader xls,
			String worksheet, int rowNum, String result) {
		// set result to xlsx file
		xls.setCellData(worksheet, "Result", rowNum, result);
	}

	public static int getRowNum(DataFile_Reader xls, String workSheet,
			String testName) {
		int num = 0;
		for (int i = 2; i <= xls.getRowCount(workSheet); i++) {
			String name = xls.getCellContain(workSheet, "TCID", i);
			if (name.equalsIgnoreCase(testName))
				num = i;
		}
		return num;
	}

}
