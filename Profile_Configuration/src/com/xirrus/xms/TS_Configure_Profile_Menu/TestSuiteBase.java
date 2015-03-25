package com.xirrus.xms.TS_Configure_Profile_Menu;

import com.xirrus.xms.globallib.*;
import com.xirrus.xms.locallib.LocalLib;
import com.xirrus.xms.base.TestBase;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import com.xirrus.xms.localLib.*;

public class TestSuiteBase extends TestBase{
//public Navigation NavigateTo=null;
	String testSuite= "TS_Configure_Profile_Menu";
	LocalLib xmslocallib= new LocalLib();
	@BeforeSuite
	public void checkSuiteSkip() throws Exception{
		initialize();
		//initializeTestSuiteFile(testSuite);
		System.out.println("First Time");
//		openBrowser();
//		LocalLib.Login();
		App_Log.debug("Checking Run Mode of "+testSuite+" Test Suite");
		if(!Test_Utility.isTestSuiteRunnable(test_Suite_xls, "TS_Configure_Profile_Menu")){
			App_Log.debug("Skipped Test Suite "+testSuite+" was marked as No");
			throw new SkipException("Run mode of Test Suite is "+testSuite+" is set to No");
		}else
			App_Log.debug("Test Suite "+testSuite+" is set as Yes, Test cases are going to execute!!!");
		
	}
	
	
	@AfterSuite
	public void cleanup(){
		//LocalLib.logoutFromTestSite();
		//closeBroweser();
		System.out.println("First time closed");
		App_Log.debug("Test Suite "+testSuite+" is completed !!!");
	}
	
	
}
