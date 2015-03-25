package com.xirrus.xms.TS_Array_Network;

import com.xirrus.xms.globallib.*;
import com.xirrus.xms.base.TestBase;
import com.xirrus.xms.locallib.*;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestSuiteBase extends TestBase{
//public Navigation NavigateTo=null;
	String testSuite= "TS_Array_Network";
	private String screencaptureFileName;
	NavigateTo navigatelib = new NavigateTo();
	LocalLib xmslocallib= new LocalLib();
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		App_Log.debug("*********************Start Of Test Suite*************************************");
		App_Log.debug("StartTS-TestSuite-"+ testSuite);
		//Check is Test suite mark as executable or not	
		App_Log.debug("Checking Run Mode of "+testSuite+" Test Suite");
		if(!Test_Utility.isTestSuiteRunnable(test_Suite_xls, testSuite)){
			App_Log.debug("Skipped Test Suite "+testSuite+" was marked as No");
			throw new SkipException("Run mode of Test Suite is "+testSuite+" is set to No");
		}else
		{
			App_Log.debug("!!!Test Suite "+testSuite+" is set as Yes, Test cases are going to execute!!!");
		}
		App_Log.debug("StartTS-Setup:-"+testSuite+" Test suite Setup");
		try{
			//Start Test Execution for test cases covered under Test suite
			//Open Browser for testing
			openBrowser();
			//Access website under test
			LocalLib.AccessTestSite();
			//#Refresh Array by IP address or hostname
			xmslocallib.refreshAccessPoint(CONFIG.getProperty("ArrayIPAddress"));
			}catch (Exception e){
				App_Log.error("Error:-"+e);
				screencaptureFileName="SetupError-"+testSuite+".png";
				xmslocallib.captureScreenshot(screencaptureFileName);
				throw new SkipException("SetupError:-"+testSuite+" Test Suite execution is aborted");
				
			}

		
		App_Log.debug("EndSetup:-"+testSuite+" Test suite Setup");
	}
	
	
	@AfterSuite
	public void cleanup(){
		//logout from test website
		LocalLib.logoutFromTestSite();
		//Close browser
		closeBroweser();
		App_Log.debug("EndTS-TestSuite-"+testSuite+" is completed !!!");
		App_Log.debug("*************************End Of Test Suite*********************************");
	}
	
	
}
