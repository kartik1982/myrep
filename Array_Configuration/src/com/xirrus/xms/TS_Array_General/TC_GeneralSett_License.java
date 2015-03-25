package com.xirrus.xms.TS_Array_General;

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
public class TC_GeneralSett_License extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String licenseKeySNMP;
	private String licenseKeyGUI;
	private String licenseKeyCLI;
	private String licenseKeyExp;
	private String licenseKeyNew;
	private String licenseKeyOID;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_General_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	
	@Test(priority=1)
	public void testCase_VerifydefaultLicenseKeyNotEmptyvalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-6.1_Verify default value for License not Empty in Array");
		//*****************************************************************
		licenseKeyExp=CONFIG.getProperty("LicenseKey");
		licenseKeyNew=CONFIG.getProperty("LicenseKey2");
		licenseKeyOID=SNMPOID.getProperty("systemLicenseKey");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Verify that default Array Hostname is not empty
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			boolean guiResult=false,snmpResult=false, cliResult=false;
			//Verification by SNMP, CLI and GUI
			//get License Key value configured into array from GUI
			licenseKeyGUI = xmslocallib.getText("License Key");
			//Verify License Key configured into array by GUI
			App_Log.debug("Ref-6.1_GUI-Verification:-Verify Array License Key is not empty by default in array");
			if(licenseKeyGUI.equals("")){
				 guiResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				 guiResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//Verify License Key configured into array by SNMP
			App_Log.debug("Ref-6.1_SNMP-Verification:-Verify Array License Key is not empty by default in array");
			licenseKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, licenseKeyOID);
			if(licenseKeySNMP.equals("")){
				snmpResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				snmpResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//Verify License key configured into Array by CLI
			App_Log.debug("Ref-6.1_CLI-Verification:-Verify Array License Key is not empty by default in array");
			licenseKeyCLI=xmslocallib.getValuebyCLI_SystemInfo(arrayIPadd, "License Key");
			if(licenseKeyCLI.equals("")){
				cliResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				cliResult=xmslocallib.verifyStringequals("ArrayLicenseKey", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-6.1_Verify default value for License not Empty in Array");
	}
	
	@Test(priority=2)
	public void testCase_VerifyLicenseKeyNotAcceptEmptyValue() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-6.2_Verify License Key Not accept value Empty");
		//*****************************************************************
		licenseKeyExp=CONFIG.getProperty("LicenseKey");
		licenseKeyNew="";
		licenseKeyOID=SNMPOID.getProperty("systemLicenseKey");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Verify that License key can't set with empty value
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("License Key", licenseKeyNew);
			xmslocallib.clickButton("Apply Config");
			App_Log.debug("Ref-1.2_Verification:-Verify License Key Field not accept empty value in Array");
			//Verify that XMS warning message is displaying and parameter not configured
			String warningmessage="License key is missing. Please contact Xirrus Customer Support (support@xirrus.com) to request a new license key.";
			boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
			if(warning){
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
			}
			else{
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
			}
			if(warning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-6.2_Verify License Key Not accept value Empty");
	}
	@Test(priority=3)
	public void testCase_VerifyLicenseKeynotAcceptvalueMoreOrLessThan23Char()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-6.3_Verify License Key not accept value more or less than 23 char ");
		//*****************************************************************
		licenseKeyExp=CONFIG.getProperty("LicenseKey");
		licenseKeyNew="11V73-CGUQK-M6PDN-NK6C6Z"; //more than 23 char
		licenseKeyOID=SNMPOID.getProperty("systemLicenseKey");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Verify that License key can't set with empty value
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("License Key", licenseKeyNew);
			xmslocallib.clickButton("Apply Config");
			App_Log.debug("Ref-6.3_Verification:-Verify License Key Field not accept more than 23 char value in Array");
			//Verify that XMS warning message is displaying and parameter not configured
			String warningmessage="License Key is not exactly 23 characters long.";
			boolean warning1=xmslocallib.isWarningMessageDisplayed(warningmessage);
			if(warning1){
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
			}
			else{
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
			}
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink("General Settings");
			licenseKeyNew="11V73-CGUQK-M6PDN-NK6C";
			xmslocallib.setText("License Key", licenseKeyNew);
			xmslocallib.clickButton("Apply Config");
			App_Log.debug("Ref-6.3_Verification:-Verify License Key Field not accept less than 23 char value in Array");
			boolean warning2=xmslocallib.isWarningMessageDisplayed(warningmessage);
			if(warning2){
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
			}
			else{
				xmslocallib.verifyStringequals("LiceneKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
			}
			
			if(warning1&&warning2)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-6.3_Verify License Key not accept value more or less than 23 char ");
		
	}
	@Test(priority=4)
	public void testCase_VerifyLicenseKeyInvalidValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-6.4_Verify License Key Not accept Invalid Key value");
		//*****************************************************************
		licenseKeyExp=CONFIG.getProperty("LicenseKey");
		licenseKeyNew="XXV73-CGUQK-M6PDN-NK6XX"; //Invalid key
		licenseKeyOID=SNMPOID.getProperty("systemLicenseKey");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		try{
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array General Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		xmslocallib.setText("License Key", licenseKeyNew);
		xmslocallib.clickButton("Apply Config");
		//xmslocallib.clickButton("OK");
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
		xmslocallib.pause(2000);
		App_Log.debug("Ref-6.4_Verification:-Verify Invalid License Key Field not configured in Array");
		//Verify that XMS warning message is displaying and parameter not configured
		String errormessage="systemLicenseKey (failure)";
		boolean errormsg=xmslocallib.isWarningMessageDisplayed(errormessage);
		if(errormsg){
			xmslocallib.verifyStringequals("LiceneKeyInvalidFail", this.getClass().getSimpleName(), methodName, "Failure Displayed", "Failure Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LiceneKeyInvalidFail", this.getClass().getSimpleName(), methodName, "Failure Displayed", "Failure NOT Displayed");
		}
		
		//Verification License key not configured into array  by SNMP, CLI and GUI
		//Navigate to Array General Configuration
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		//Get value configured into array 
		licenseKeyGUI = xmslocallib.getText("License Key");
		//Verify Array hostname configured into array by GUI
		App_Log.debug("Ref-6.4_GUI-Verification:-Verify Array License Key value NOT configured into array");
		boolean guiResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeyGUI, licenseKeyExp);
		//Verify License Key not get Configured into array by SNMP
		App_Log.debug("Ref-6.4_SNMP-Verification:-Verify Array License key NOT value configured into array");
		licenseKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, licenseKeyOID);
		boolean snmpResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeySNMP, licenseKeyExp);
		//Verify Location Information name get configured into array by CLI
		App_Log.debug("Ref-6.4_CLI-Verification:-Verify Array License Key value NOT configured into array");
		licenseKeyCLI=xmslocallib.getValuebyCLI_SystemInfo(arrayIPadd, "License Key");
		boolean cliResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeyCLI, licenseKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(errormsg&&guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-6.4_Verify License Key Not accept Invalid Key value");
	}
	@Test(priority=5)
	public void testCase_VerifyLicenseKeyUpdatedValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-6.5_Verify License key set to updated value in Array");
		//*****************************************************************
		licenseKeyExp=CONFIG.getProperty("LicenseKey");
		licenseKeyNew=CONFIG.getProperty("LicenseKey2"); 
		licenseKeyOID=SNMPOID.getProperty("systemLicenseKey");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("License Key", licenseKeyNew);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
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
			xmslocallib.pause(2000);
			//Verification License key configured into array  by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			navigatelib.ConfigureAccesssPoint();
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			licenseKeyGUI = xmslocallib.getText("License Key");
			//Verify Array hostname configured into array by GUI
			App_Log.debug("Ref-6.5_GUI-Verification:-Verify Array License Key value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeyGUI, licenseKeyNew);
			//Verify License Key not get Configured into array by SNMP
			App_Log.debug("Ref-6.5_SNMP-Verification:-Verify Array License key  value configured into array");
			licenseKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, licenseKeyOID);
			boolean snmpResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeySNMP, licenseKeyNew);
			//Verify Location Information name get configured into array by CLI
			App_Log.debug("Ref-6.5_CLI-Verification:-Verify Array License Key value  configured into array");
			licenseKeyCLI=xmslocallib.getValuebyCLI_SystemInfo(arrayIPadd, "License Key");
			boolean cliResult=xmslocallib.verifyStringequals("LicenseKey", this.getClass().getSimpleName(), methodName, licenseKeyCLI, licenseKeyNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(guiResult&&snmpResult&&cliResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
			//@@@@@@@restore initial License key@@@@@@@@
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("License Key", licenseKeyExp);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
			
		App_Log.debug("End-Ref-6.5_Verify License key set to updated value in Array");
	}
		
	
	@AfterTest
	public void testCompleted(){
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
