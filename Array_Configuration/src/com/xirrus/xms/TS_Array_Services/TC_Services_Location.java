package com.xirrus.xms.TS_Array_Services;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Services_Location extends TestSuiteBase{
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
	
	//This will check runmode of test case in excel sheet
	@BeforeTest
	public void checkTestSkip() throws IOException{
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Services_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-3.1_Verify Enable Location Support Default status
	@Test(priority=1)
	public void testCase_verifyNetflowDefaultVersion()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.1_Verify Enable Location Support Default status");
		//*****************************************************************
		locationEnableExp="disabled";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		
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
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		boolean locationEnable=xmslocallib.isRadioButtonSelected("Enable Location Support",2); //Location Disable
		if(locationEnable)
			locationEnableGUI="disabled";
		else
			locationEnableGUI="NotMatch";
		//Verify Location reporting enable status configured into array by GUI
		App_Log.debug("Ref-3.1_GUI-Verification:-Verify Services Location reporting status default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableGUI, locationEnableExp);
		//Verify Location reporting enable status configured into array by SNMP
		App_Log.debug("Ref-3.1_SNMP-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableSNMP, "0");
		//Verify NLocation reporting enable status configured into array by CLI
		App_Log.debug("Ref-3.1_CLI-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableCLI, locationEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.1_Verify Enable Location Support Default status");
	}
	//Ref-3.2_Verify Location URL Default value
	@Test(priority=2)
	public void testCase_verifyLocationURLDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.2_Verify Location URL Default value");
		//*****************************************************************
		locationURLExp="";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location URL paramters configured into Array by SNMP, CLI and GUI
		//Get Location URL value configured into array 
		locationURLGUI= xmslocallib.getText("Location URL");
		//Verify Location URL configured into array by GUI
		App_Log.debug("Ref-3.2_GUI-Verification:-Verify Services Location URL default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLGUI, locationURLExp);
		//Verify Location URL configured into array by SNMP
		App_Log.debug("Ref-3.2_SNMP-Verification:-Verify Services Location URL default value is empty in array");
		locationURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationURLOID);
		if(locationURLSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			locationURLSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLSNMP, locationURLExp);
		//Verify Location URL Configured into array by CLI
		App_Log.debug("Ref-3.2_CLI-Verification:-Verify Services Location URL default value is empty in array");
		locationURLCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Server URL");
		boolean cliResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLCLI, locationURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		
		App_Log.debug("End-Ref-3.2_Verify Location URL Default value");
	}
	//Ref-3.3_Verify Location Key Default value
	@Test(priority=3)
	public void testCase_verifyLocationKeyDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.3_Verify Location Key Default value");
		//*****************************************************************
		locationKeyExp="";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location Key paramters configured into Array by SNMP, CLI and GUI
		//Get Location key value configured into array 
		locationKeyGUI= xmslocallib.getText("Location Key");
		//Verify Location Key configured into array by GUI
		App_Log.debug("Ref-3.3_GUI-Verification:-Verify Services Location Key default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyGUI, locationKeyExp);
		//Verify Location Key configured into array by SNMP
		App_Log.debug("Ref-3.3_SNMP-Verification:-Verify Services Location Key default value is empty in array");
		locationKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationKeyOID);
		if(locationKeySNMP.contains("1.3.6.1.4.1.21013.1.2")){
			locationKeySNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeySNMP, locationKeyExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("Ref-3.3_CLI-Verification:-Verify Services Location Key default value is empty in array");
		locationKeyCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Customer Key");
		boolean cliResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyCLI, locationKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.3_Verify Location Key Default value");
	}
	//Ref-3.4_Verify Location Period Default value  
	@Test(priority=4)
	public void testCase_verifyLocationPeriodDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.4_Verify Location Period Default value");
		//*****************************************************************
		locationPeriodExp="15";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location Key paramters configured into Array by SNMP, CLI and GUI
		//Get Location Period value configured into array 
		locationPeriodGUI= xmslocallib.getText("Location Period");
		//Verify Location Period configured into array by GUI
		App_Log.debug("Ref-3.4_GUI-Verification:-Verify Services Location Period default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodGUI, locationPeriodExp);
		//Verify Location Period configured into array by SNMP
		App_Log.debug("Ref-3.4_SNMP-Verification:-Verify Services Location Period default value is empty in array");
		locationPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationPeriodOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodSNMP, locationPeriodExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("Ref-3.4_CLI-Verification:-Verify Services Location Period default value is empty in array");
		locationPeriodCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Period");
		locationPeriodCLI=locationPeriodCLI.replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodCLI, locationPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.4_Verify Location Period Default value");
	}
	//Ref-3.5_Verify Enable Location Support 
	@Test(priority=5)
	public void testCase_verifyLocationSupportEnable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.5_Verify Enable Location Support");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			xmslocallib.selectRadioButton("Enable Location Support", 1);
			xmslocallib.setText("Location URL", locationURLExp);
			xmslocallib.setText("Location Key", locationKeyExp);
			xmslocallib.setText("Location Period", locationPeriodExp);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		//Get Location Version value configured into array 
		boolean locationEnable=xmslocallib.isRadioButtonSelected("Enable Location Support",1); //Location reporting Disable
		if(locationEnable)
			locationEnableGUI="enabled";
		else
			locationEnableGUI="NotMatch";
		//Verify Location reporting enable status configured into array by GUI
		App_Log.debug("Ref-3.5_GUI-Verification:-Verify Services Location reporting status default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableGUI, locationEnableExp);
		//Verify Location reporting enable status configured into array by SNMP
		App_Log.debug("Ref-3.5_SNMP-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableSNMP, "1");
		//Verify NLocation reporting enable status configured into array by CLI
		App_Log.debug("Ref-3.5_CLI-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableCLI, locationEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.5_Verify Enable Location Support");
	}
	//Ref-3.6_Verify  Location URL value edit
	@Test(priority=6)
	public void testCase_verifyLocationURLEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.6_Verify  Location URL value edit");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");;
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location URL paramters configured into Array by SNMP, CLI and GUI
		//Get Location URL value configured into array 
		locationURLGUI= xmslocallib.getText("Location URL");
		//Verify Location URL configured into array by GUI
		App_Log.debug("Ref-3.6_GUI-Verification:-Verify Services Location URL value in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLGUI, locationURLExp);
		//Verify Location URL configured into array by SNMP
		App_Log.debug("Ref-3.6_SNMP-Verification:-Verify Services Location URL value in array");
		locationURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationURLOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLSNMP, locationURLExp);
		//Verify Location URL Configured into array by CLI
		App_Log.debug("Ref-3.6_CLI-Verification:-Verify Services Location URL value in array");
		locationURLCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Server URL");
		boolean cliResult=xmslocallib.verifyStringequals("LocationURL", this.getClass().getSimpleName(), methodName, locationURLCLI, locationURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.6_Verify  Location URL value edit");
	}
	//Ref-3.7_Verify Location Key value edit 
	@Test(priority=7)
	public void testCase_verifyLocationKeyEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.7_Verify Location Key value edit");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");;
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location Key paramters configured into Array by SNMP, CLI and GUI
		//Get Location key value configured into array 
		locationKeyGUI= xmslocallib.getText("Location Key");
		//Verify Location Key configured into array by GUI
		App_Log.debug("Ref-3.7_GUI-Verification:-Verify Services Location Key value in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyGUI, locationKeyExp);
		//Verify Location Key configured into array by SNMP
		App_Log.debug("Ref-3.7_SNMP-Verification:-Verify Services Location Key value in array");
		locationKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationKeyOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeySNMP, locationKeyExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("Ref-3.7_CLI-Verification:-Verify Services Location Key value in array");
		locationKeyCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Customer Key");
		boolean cliResult=xmslocallib.verifyStringequals("LocationKey", this.getClass().getSimpleName(), methodName, locationKeyCLI, locationKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.7_Verify Location Key value edit");
	}
	//Ref-3.8_Verify Location Period value edit     
	@Test(priority=8)
	public void testCase_verifyLocationPeriodEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.8_Verify Location Period value edit");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");;
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
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
		//Verification on Location Key paramters configured into Array by SNMP, CLI and GUI
		//Get Location Period value configured into array 
		locationPeriodGUI= xmslocallib.getText("Location Period");
		//Verify Location Period configured into array by GUI
		App_Log.debug("Ref-3.8_GUI-Verification:-Verify Services Location Period default value configured in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodGUI, locationPeriodExp);
		//Verify Location Period configured into array by SNMP
		App_Log.debug("Ref-3.8_SNMP-Verification:-Verify Services Location Period default value configured in array");
		locationPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationPeriodOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodSNMP, locationPeriodExp);
		//Verify Location Key Configured into array by CLI
		App_Log.debug("Ref-3.8_CLI-Verification:-Verify Services Location Period default value configured in array");
		locationPeriodCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "Period");
		locationPeriodCLI=locationPeriodCLI.replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("LocationPeriod", this.getClass().getSimpleName(), methodName, locationPeriodCLI, locationPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.8_Verify Location Period value edit");
	}
	//Ref-3.9_Verify  Location URL field validate  
	@Test(priority=9)
	public void testCase_verifyLocationURLFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.9_Verify  Location URL field validate");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp="";
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");;
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			xmslocallib.selectRadioButton("Enable Location Support", 1);
			xmslocallib.setText("Location URL", locationURLExp);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify URL field should not accept Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Please specify a Location URL";
		boolean locationURLEmptywarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationURLEmptywarning){
			xmslocallib.verifyStringequals("LocationURLEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationURLEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		xmslocallib.selectRadioButton("Enable Location Support", 1);
		//256 character
		locationURLExp="http://www.efghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijk.com";
		xmslocallib.setText("Location URL", locationURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify Location URL must be between 0 and 255 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Location URL is longer than the maximum of 255 characters.";
		boolean locationURLLengthwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationURLLengthwarning){
			xmslocallib.verifyStringequals("LocationURLLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationURLLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		xmslocallib.selectRadioButton("Enable Location Support", 1);
		//256 character
		locationURLExp="Tester Test Software To release";
		xmslocallib.setText("Location URL", locationURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify Location URL is accept only valid entry");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Location URL is not valid";
		boolean locationURLValidwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationURLValidwarning){
			xmslocallib.verifyStringequals("LocationURLLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationURLLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(locationURLEmptywarning&&locationURLLengthwarning&&locationURLValidwarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.9_Verify  Location URL field validate");
	}
	//Ref-3.10_Verify Location Key field validate  
	@Test(priority=10)
	public void testCase_verifyLocationKeyFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.10_Verify Location Key field validate");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp="testingforxmstestingforxmstestingforxmstestingforxm"; //51 characters
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");;
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			xmslocallib.selectRadioButton("Enable Location Support", 1);
			xmslocallib.setText("Location Key", locationKeyExp);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-3.10_GUI-Verification:-Verify Location Key is longer than the maximum of 50 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Location Key is longer than the maximum of 50 characters.";
		boolean locationKeyLengthwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationKeyLengthwarning){
			xmslocallib.verifyStringequals("LocationKeyLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationKeyLengthWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(locationKeyLengthwarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.10_Verify Location Key field validate");
	}
	//Ref-3.11_Verify Location Period field validate   
	@Test(priority=11)
	public void testCase_verifyLocationPeriodFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.11_Verify Location Period field validate");
		//*****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp="50001";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			xmslocallib.selectRadioButton("Enable Location Support", 1);
			xmslocallib.setText("Location Period", locationPeriodExp);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify Location Period must be between 5 and 50000");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Location Period must be between 5 and 50000";
		boolean locationPeriodWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationPeriodWarning){
			xmslocallib.verifyStringequals("LocationPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		xmslocallib.selectRadioButton("Enable Location Support", 1);
		xmslocallib.setText("Location Period", "4");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		warningmessage="Location Period must be between 5 and 50000";
		locationPeriodWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(locationPeriodWarning){
			xmslocallib.verifyStringequals("LocationPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		xmslocallib.selectRadioButton("Enable Location Support", 1);
		xmslocallib.setText("Location Period", "");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify Location Period should not accept empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Please specify a Location Period";
		boolean periodemptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodemptyWarning){
			xmslocallib.verifyStringequals("LocationPeriodEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationPeriodEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		xmslocallib.selectRadioButton("Enable Location Support", 1);
		xmslocallib.setText("Location Period", "Kartik");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify Location Period should not accept other than Integer value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Please specify a Location Period";
		boolean periodNonIntegerWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodNonIntegerWarning){
			xmslocallib.verifyStringequals("LocationPeriodNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationPeriodNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(locationPeriodWarning&&periodemptyWarning&&periodNonIntegerWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.11_Verify Location Period field validate");
	}
	//Ref-3.12_Verify Disable Location Support  
	@Test(priority=12)
	public void testCase_verifyLocationSupportDisable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.12_Verify Disable Location Support");
		//*****************************************************************
		locationEnableExp="disabled";
		locationEnableOID=SNMPOID.getProperty("LocationReportingOnOID");
		locationURLOID=SNMPOID.getProperty("LocationReportingURLOID");
		locationKeyOID=SNMPOID.getProperty("LocationReportingKeyOID");
		locationPeriodOID=SNMPOID.getProperty("LocationReportingPeriodOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			xmslocallib.selectRadioButton("Enable Location Support", 2);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Location");
		//Get Location Version value configured into array 
		boolean locationEnable=xmslocallib.isRadioButtonSelected("Enable Location Support",2); //Location reporting Disable
		if(locationEnable)
			locationEnableGUI="disabled";
		else
			locationEnableGUI="NotMatch";
		//Verify Location reporting enable status configured into array by GUI
		App_Log.debug("Ref-3.12_GUI-Verification:-Verify Services Location reporting status default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableGUI, locationEnableExp);
		//Verify Location reporting enable status configured into array by SNMP
		App_Log.debug("Ref-3.12_SNMP-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableSNMP, "0");
		//Verify NLocation reporting enable status configured into array by CLI
		App_Log.debug("Ref-3.12_CLI-Verification:-Verify Services Location reporting status default value is disable in array");
		locationEnableCLI=xmslocallib.getValuebyCLI_Location(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("LocationEnableState", this.getClass().getSimpleName(), methodName, locationEnableCLI, locationEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.12_Verify Disable Location Support");
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
