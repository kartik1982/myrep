package com.xirrus.xms.TS_Profile_Network;

import com.xirrus.xms.globallib.*;
import com.xirrus.xms.base.TestBase;
import com.xirrus.xms.locallib.*;


import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import com.xirrus.xms.localLib.*;

public class TestSuiteBase extends TestBase{
//public Navigation NavigateTo=null;
	String testSuite= "TS_Profile_Network";
	LocalLib xmslocallib= new LocalLib();
	NavigateTo navigatelib = new NavigateTo();

	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		App_Log.debug("*********************Start Of Test Suite*************************************");
		App_Log.debug("StartTestSuite-"+ testSuite);
		//Check is Test suite mark as executable or not
		System.out.println("First Time");
		App_Log.debug("Checking Run Mode of "+testSuite+" Test Suite");
		if(!Test_Utility.isTestSuiteRunnable(test_Suite_xls, "TS_Profile_Network")){
			App_Log.debug("Skipped Test Suite "+testSuite+" was marked as No");
			throw new SkipException("Run mode of Test Suite is "+testSuite+" is set to No");
		}else{
			App_Log.debug("!!!Test Suite "+testSuite+" is set as Yes, Test cases are going to execute!!!");
		}
		App_Log.debug("StartSetup:-"+testSuite+" Test suite Setup");
		try{
		//Start Test Execution for test cases covered under Test suite
		//Open Browser for testing
		openBrowser();
		//Access website under test
		LocalLib.AccessTestSite();
		//#Refresh Array 
		xmslocallib.refreshAccessPoint(CONFIG.getProperty("ArrayIPAddress"));
		//#Create new Profile
		//Navigate to Profile 
		navigatelib.ConfigureProfile();
		//Delete all profiles if exists
		xmslocallib.deletAllProfile();
		//Create new profile
		xmslocallib.createProfile(CONFIG.getProperty("ProfileName"));
		//#Add Array into profile
		navigatelib.ConfigureProfile();
		//Select CheckBox for profile
		xmslocallib.selectRowCheckbox(CONFIG.getProperty("ProfileName"));
		//Click edit button
		xmslocallib.clickButton("Edit");
		//Select Checkbox for array
		xmslocallib.selectRowCheckbox(CONFIG.getProperty("ArrayIPAddress"));
		//Click OK button
		xmslocallib.clickButton("OK");
		}catch (Exception e){
			
		}
		App_Log.debug("EndSetup:-"+testSuite+" Test suite Setup");
		
	}
	
	
	@AfterSuite
	public void cleanup(){
		LocalLib.logoutFromTestSite();
		closeBroweser();
		System.out.println("First time closed");
		App_Log.debug("Test Suite "+testSuite+" is completed !!!");
		App_Log.debug("EndTestSuite-"+testSuite+" is completed !!!");
		App_Log.debug("*************************End Of Test Suite*********************************");
	}
	
	
}
