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
public class TC_SSIDs_Mgmt_Limits extends TestSuiteBase{
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
	public void testCase_VerifyDefaultSSIDGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify Default SSID General Settings");
		//Navigate to SSID panel
		//Select Default SSID "xirrus"
		
		//Read SSID Name through GUI
		//Read SSID Name through SNMP
		//Read SSID Name through CLI
		
		//Read Enabled state through GUI
		//Read Enabled state through SNMP
		//Read Enabled state through CLI
		
		//Read Band through GUI
		//Read Band through SNMP
		//Read Band through CLI
		
		//Read VLAN through GUI
		//Read VLAN through SNMP
		//Read VLAN through CLI
		
		//Read QOS through GUI
		//Read QOS through SNMP
		//Read QOS through CLI
		
		//Read DHCP Pool through GUI
		//Read DHCP Pool through SNMP
		//Read DHCP Pool through CLI
		
		//Read Filter List through GUI
		//Read Filter List through SNMP
		//Read Filter List through CLI
		
		//Read Roaming Assist through GUI
		//Read Roaming Assist through SNMP
		//Read Roaming Assist through CLI
		
		//Read Fallback through GUI
		//Read Fallback through SNMP
		//Read Fallback through CLI
		
		//Read MDM through GUI
		//Read MDM through SNMP
		//Read MDM through CLI
		
		App_Log.debug("End-Ref-1.1_Verify Default SSID General Settings");		
	}
	@Test(priority=2)
	public void testCase_VerifyAddSSIDwithDefaultGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.2_Verify Add SSID with Default General Settings");
		//Add SSID "kartik"
		
		//Navigate to SSID panel
		//Select Default SSID "kartik"
		
		//Read SSID Name through GUI
		//Read SSID Name through SNMP
		//Read SSID Name through CLI
		
		//Read Enabled state through GUI
		//Read Enabled state through SNMP
		//Read Enabled state through CLI
		
		//Read Band through GUI
		//Read Band through SNMP
		//Read Band through CLI
		
		//Read VLAN through GUI
		//Read VLAN through SNMP
		//Read VLAN through CLI
		
		//Read QOS through GUI
		//Read QOS through SNMP
		//Read QOS through CLI
		
		//Read DHCP Pool through GUI
		//Read DHCP Pool through SNMP
		//Read DHCP Pool through CLI
		
		//Read Filter List through GUI
		//Read Filter List through SNMP
		//Read Filter List through CLI
		
		//Read Roaming Assist through GUI
		//Read Roaming Assist through SNMP
		//Read Roaming Assist through CLI
		
		//Read Fallback through GUI
		//Read Fallback through SNMP
		//Read Fallback through CLI
		
		//Read MDM through GUI
		//Read MDM through SNMP
		//Read MDM through CLI
		App_Log.debug("End-Ref-1.2_Verify Add SSID with Default General Settings");		
	}
	@Test(priority=3)
	public void testCase_VerifyAddSSIDwithcustomGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Add SSID with custom General Settings");
		//Add SSID "custom"
		
		//Navigate to SSID panel
		//Select Default SSID "custom"
		
		//Read SSID Name through GUI
		//Read SSID Name through SNMP
		//Read SSID Name through CLI
		
		//Read Enabled state through GUI
		//Read Enabled state through SNMP
		//Read Enabled state through CLI
		
		//Read Band through GUI
		//Read Band through SNMP
		//Read Band through CLI
		
		//Read VLAN through GUI
		//Read VLAN through SNMP
		//Read VLAN through CLI
		
		//Read QOS through GUI
		//Read QOS through SNMP
		//Read QOS through CLI
		
		//Read DHCP Pool through GUI
		//Read DHCP Pool through SNMP
		//Read DHCP Pool through CLI
		
		//Read Filter List through GUI
		//Read Filter List through SNMP
		//Read Filter List through CLI
		
		//Read Roaming Assist through GUI
		//Read Roaming Assist through SNMP
		//Read Roaming Assist through CLI
		
		//Read Fallback through GUI
		//Read Fallback through SNMP
		//Read Fallback through CLI
		
		//Read MDM through GUI
		//Read MDM through SNMP
		//Read MDM through CLI
		
		App_Log.debug("End-Ref-1.3_Verify Add SSID with custom General Settings");		
	}
	@Test(priority=4)
	public void testCase_VerifyEditSSIDGeneralSettings() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Edit SSID General Settings");
		//Edit SSID "kartik"
		
		//Navigate to SSID panel
		//Select Default SSID "kartik"
		
		//Read SSID Name through GUI
		//Read SSID Name through SNMP
		//Read SSID Name through CLI
		
		//Read Enabled state through GUI
		//Read Enabled state through SNMP
		//Read Enabled state through CLI
		
		//Read Band through GUI
		//Read Band through SNMP
		//Read Band through CLI
		
		//Read VLAN through GUI
		//Read VLAN through SNMP
		//Read VLAN through CLI
		
		//Read QOS through GUI
		//Read QOS through SNMP
		//Read QOS through CLI
		
		//Read DHCP Pool through GUI
		//Read DHCP Pool through SNMP
		//Read DHCP Pool through CLI
		
		//Read Filter List through GUI
		//Read Filter List through SNMP
		//Read Filter List through CLI
		
		//Read Roaming Assist through GUI
		//Read Roaming Assist through SNMP
		//Read Roaming Assist through CLI
		
		//Read Fallback through GUI
		//Read Fallback through SNMP
		//Read Fallback through CLI
		
		//Read MDM through GUI
		//Read MDM through SNMP
		//Read MDM through CLI
		
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
