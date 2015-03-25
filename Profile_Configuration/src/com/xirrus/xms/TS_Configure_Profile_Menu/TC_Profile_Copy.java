package com.xirrus.xms.TS_Configure_Profile_Menu;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Profile_Copy extends TestSuiteBase{

	//This will check runmode of test case in excel sheet
	@BeforeTest
	public void checkTestSkip(){
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Profile_Menu_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
	}
	
	@Test(dataProvider="getTestData")
	public void testCase_C1(){
		App_Log.debug("Test CAse C1");
		//NavigateTo.MonitorSSIDs();
	}
	
	@AfterTest
	public void testCompleted(){
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Profile_Menu_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
