package com.xirrus.xms.TS_Profile_Network;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Network_CDP extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	@BeforeTest
	public void checkTestSkip(){
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Profile_Network_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
	}
	
	@Test(dataProvider="getTestData")
	public void testCase_C1()throws InterruptedException, IOException{
		App_Log.debug("Test CAse C1");
		//#Navigate to Network LLDP  
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(CONFIG.getProperty("ProfileName"));
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		//#Enable CDP from profile for XMS-E
		xmslocallib.selectRadioButton("Enable CDP", 1);
		//Set CDP Interval to 80 sec
		xmslocallib.setText("CDP Interval", "80");
		//Set CDP Hold Time to 240 sec
		xmslocallib.setText("CDP Hold Time", "240");
		//Apply configuration to profile and arrays member of profile
		xmslocallib.clickButton("Apply Config");
		xmslocallib.clickButton("OK");
		
		xmslocallib.setTimeout(90);
		if (! xmslocallib.findText("Done configuring Array")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
		}


		xmslocallib.setTimeout(60);
		App_Log.debug("stop");
	}
	
	@AfterTest
	public void testCompleted(){
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Profile_Menu_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
