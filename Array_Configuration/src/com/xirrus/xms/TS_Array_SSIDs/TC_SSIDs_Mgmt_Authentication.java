package com.xirrus.xms.TS_Array_SSIDs;

import java.io.IOException;
import java.util.Arrays;



import com.xirrus.xms.globallib.Test_Utility;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xirrus.xms.globallib.*;

@Test
public class TC_SSIDs_Mgmt_Authentication extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	

	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsserverIP;
	private String xmsuser;
	private String xmspasswd;
	private String screencaptureFileName;
	private String methodName;
	private String testCaseResult;
	private String testMethodResult;
	
	@BeforeTest
	public void checkTestSkip() throws IOException{
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTC-TestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Array_SSIDs_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	
	@Test(priority=1)
	public void testCase_VerifyDefaultSSIDDefaultAuthentication() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify Default SSID Default Authentication");
		//Navigate to SSID panel select Default SSID "xirrus"
		//Click Authentication panel
		//Get Encryption value by GUI
		//Get Encryption value by SNMP
		//Get Encryption value by CLI
		
		//Get Authentication value by GUI
		//Get Authentication value by SNMP
		//Get Authentication value by CLI
		
		App_Log.debug("End-Ref-2.1_Verify Default SSID Default Authentication");		
	}
	@Test(priority=2)
	public void testCase_VerifyAddSSIDwithDefaultGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.2_Verify Add SSID with Default Authentication");
		//Navigate to SSID panel select Default SSID "kartik"
		//Click Authentication panel
		//Get Encryption value by GUI
		//Get Encryption value by SNMP
		//Get Encryption value by CLI
		
		//Get Authentication value by GUI
		//Get Authentication value by SNMP
		//Get Authentication value by CLI
		
		App_Log.debug("End-Ref-2.2_Verify Add SSID with Default Authentication");		
	}
	@Test(priority=3)
	public void testCase_VerifyADDSSIDwithWEPOPENAuthentication() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify ADD SSID with WEP-OPEN Authentication");
		//Add SSID with Encryption WEP-Open Authentication Global "SSID-WEP"
		//Navigate to SSID panel select Default SSID "SSID-WEP"
		//Click Authentication panel
		//Get Encryption value by GUI
		//Get Encryption value by SNMP
		//Get Encryption value by CLI
		
		//Get Authentication value by GUI
		//Get Authentication value by SNMP
		//Get Authentication value by CLI
		
		App_Log.debug("End-Ref-2.3_Verify ADD SSID with WEP-OPEN Authentication");		
	}
	
	
	@Test(priority=4)
	public void testCase_VerifyEditSSIDGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Edit SSID General Settings");
		
		App_Log.debug("End-Ref-1.3_Verify Edit SSID General Settings");		
	}
		
	
	@AfterTest
	public void testCompleted() throws IOException, InterruptedException{
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
		
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Array_General_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
