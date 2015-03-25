package com.xirrus.xms.TS_ArrayConfiguration;

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
public class TC_ArrayConfig_Services_Location extends TestSuiteBase{
	//Location Parameters
	private String locationEnableSNMP;
	private String locationEnableGUI;
	private String locationEnableCLI;
	private String locationURLSNMP;
	private String locationURLGUI;
	private String locationURLCLI;
	private String locationKeySNMP;
	private String locationKeyGUI;
	private String locationKeyCLI;
	private String locationPeriodSNMP;
	private String locationPeriodGUI;
	private String locationPeriodCLI;
	private String locationEnableExp;
	private String locationURLExp;
	private String locationKeyExp;
	private String locationPeriodExp;
	private String locationEnableOID;
	private String locationURLOID;
	private String locationKeyOID;
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
	
	@BeforeTest
	public void checkTestSkip() throws IOException{
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTC-TestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_ArrayConfiguration_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	
	@Test(priority=1)
	public void testCase_VerifyArrayConfigServicesLocationParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Services Location Parameters");
		// *****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");

		arrayIPadd = CONFIG.getProperty("ArrayIPAddress");
		arrayHostName = CONFIG.getProperty("ArrayHostName");
		communityStr = CONFIG.getProperty("community");
		xmsserverIP = CONFIG.getProperty("XMSServerIPAddress");
		xmsuser = CONFIG.getProperty("XMSUsername");
		xmspasswd = CONFIG.getProperty("XMSPassword");
		methodName = new Object() {
		}.getClass().getEnclosingMethod().getName();
		testMethodResult = "FAIL";
		// *****************************************************************
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Get Location Version value configured into array 
		if(xmslocallib.isRadioButtonSelected("Enable Location Support",1))locationEnableGUI="enabled"; else locationEnableGUI="NotMatch";//Location reporting Disable
		//Verify Location reporting enable status configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Location reporting status default value is disable in array");
		boolean guistate=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableGUI, locationEnableExp);
		//Verify Location reporting enable status configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationEnableOID);
		boolean snmpstate=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableSNMP, "1");
		//Verify NLocation reporting enable status configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "State");
		boolean clistate=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableCLI, locationEnableExp);
		//Get Location URL value configured into array 
		locationURLGUI= xmslocallib.getText("Location URL");
		//Verify Location URL configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Location URL value in array");
		boolean guiurl=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLGUI, locationURLExp);
		//Verify Location URL configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Location URL value in array");
		locationURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationURLOID);
		boolean snmpurl=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLSNMP, locationURLExp);
		//Verify Location URL Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Location URL value in array");
		locationURLCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Server URL");
		boolean cliurl=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLCLI, locationURLExp);
		//Get Location key value configured into array 
		locationKeyGUI= xmslocallib.getText("Location Key");
		//Verify Location Key configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Location Key value in array");
		boolean guikey=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyGUI, locationKeyExp);
		//Verify Location Key configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Location Key value in array");
		locationKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationKeyOID);
		boolean snmpkey=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeySNMP, locationKeyExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Location Key value in array");
		locationKeyCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Customer Key");
		boolean clikey=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyCLI, locationKeyExp);
		//Get Location Period value configured into array 
		locationPeriodGUI= xmslocallib.getText("Location Period");
		//Verify Location Period configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Location Period default value is empty in array");
		boolean guiperiod=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodGUI, locationPeriodExp);
		//Verify Location Period configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Location Period default value is empty in array");
		locationPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationPeriodOID);
		boolean snmpperiod=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodSNMP, locationPeriodExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Location Period default value is empty in array");
		locationPeriodCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Period");
		locationPeriodCLI=locationPeriodCLI.replace("seconds", "").trim();
		boolean cliperiod=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodCLI, locationPeriodExp);
		
		App_Log.debug("End-Verify Array Config Services Location Parameters");	
	}
	
	@AfterTest
	public void testCompleted() throws IOException, InterruptedException{
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
		
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_ArrayConfiguration_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
