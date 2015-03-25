package com.xirrus.xms.TS_ProfileConfiguration;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_ProfileConfig_Setup extends TestSuiteBase{
	
	
	//This will check runmode of test case in excel sheet
	@BeforeTest
	public void checkTestSkip() throws IOException{
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_ProfileConfiguration_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-4.1_Verify DHCP Server Default Values
	@Test(priority=1)
	public void testCase_verifyDHCPServerDefaultValues()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.1_Verify DHCP Server Default Values");
		
		App_Log.debug("End-Ref-4.18_Verify DHCP Server Delete Multiple DHCP Server");
	}
	
	
	@AfterTest
	public void testCompleted() throws InterruptedException{
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_ProfileConfiguration_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
