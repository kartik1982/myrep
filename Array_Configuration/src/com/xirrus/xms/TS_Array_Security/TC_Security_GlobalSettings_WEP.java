package com.xirrus.xms.TS_Array_Security;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Security_GlobalSettings_WEP extends TestSuiteBase{
	private String radiusSrvrSNMP;
	private String radiusSrvrGUI;
	private String radiusSrvrCLI;
	private String enableTKIPSNMP;
	private String enableTKIPGUI;
	private String enableTKIPCLI;
	private String enableAESSNMP;
	private String enableAESGUI;
	private String enableAESCLI;
	private String locationPeriodSNMP;
	private String locationPeriodGUI;
	private String locationPeriodCLI;
	private String radiusSrvrExp;
	private String enableTKIPExp;
	private String enableAESExp;
	private String locationPeriodExp;
	private String radiusSrvrOID;
	private String enableTKIPOID;
	private String enableAESOID;
	private String locationPeriodOID;
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
	
	//This will check runmode of test case in excel sheet
	@BeforeTest
	public void checkTestSkip() throws IOException{
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Security_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-2.15_Verify WEP Encryption Key 1 Default Value
	@Test(priority=1)
	public void testCase_VerifyWEPEncryptionKey1DefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.15_Verify WEP Encryption Key 1 Default Value");

		App_Log.debug("End-Ref-2.15_Verify WEP Encryption Key 1 Default Value");
	}
	//Ref-2.16_Verify WEP Encryption Key 2 Default Value
	@Test(priority=2)
	public void testCase_VerifyWEPEncryptionKey2DefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.16_Verify WEP Encryption Key 2 Default Value");

		App_Log.debug("End-Ref-2.16_Verify WEP Encryption Key 2 Default Value");
	}
	//Ref-2.17_Verify WEP Encryption Key 3 Default Value
	@Test(priority=3)
	public void testCase_VerifyWEPEncryptionKey3DefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.17_Verify WEP Encryption Key 3 Default Value");

		App_Log.debug("End-Ref-2.17_Verify WEP Encryption Key 3 Default Value");
	}
	//Ref-2.18_Verify WEP Encryption Key 4 Default Value
	@Test(priority=4)
	public void testCase_VerifyWEPEncryptionKey4DefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.18_Verify WEP Encryption Key 4 Default Value");

		App_Log.debug("End-Ref-2.18_Verify WEP Encryption Key 4 Default Value");
	}
	//Ref-2.19_Verify WEP Default Key Default Value
	@Test(priority=5)
	public void testCase_VerifyWEPDefaultKeyDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.19_Verify WEP Default Key Default Value");

		App_Log.debug("End-Ref-2.19_Verify WEP Default Key Default Value");
	}
	//Ref-2.20_Verify WEP Encryption Key 1 Set Value
	@Test(priority=6)
	public void testCase_VerifyWEPEncryptionKey1SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.20_Verify WEP Encryption Key 1 Set Value");

		App_Log.debug("End-Ref-2.20_Verify WEP Encryption Key 1 Set Value");
	}
	//Ref-2.21_Verify WEP Encryption Key 2 Set Value
	@Test(priority=7)
	public void testCase_VerifyWEPEncryptionKey2SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.21_Verify WEP Encryption Key 2 Set Value");

		App_Log.debug("End-Ref-2.21_Verify WEP Encryption Key 2 Set Value");
	}

	//Ref-2.22_Verify WEP Encryption Key 3 Set Value
	@Test(priority=8)
	public void testCase_VerifyWEPEncryptionKey3SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.22_Verify WEP Encryption Key 3 Set Value");

		App_Log.debug("End-Ref-2.22_Verify WEP Encryption Key 3 Set Value");
	}
	//Ref-2.23_Verify WEP Encryption Key 4 Set Value
	@Test(priority=9)
	public void testCase_VerifyWEPEncryptionKey4SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.23_Verify WEP Encryption Key 4 Set Value");

		App_Log.debug("End-Ref-2.23_Verify WEP Encryption Key 4 Set Value");
	}
	//Ref-2.24_Verify WEP Default Key Set Value
	@Test(priority=10)
	public void testCase_VerifyWEPDefaultKeySetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.24_Verify WEP Default Key Set Value");

		App_Log.debug("End-Ref-2.24_Verify WEP Default Key Set Value");
	}
	
	@AfterTest
	public void testCompleted(){
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Array_Network_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
