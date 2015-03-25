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
public class TC_GeneralSett_Phone extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String adminPhoneSNMP;
	private String adminPhoneGUI;
	private String adminPhoneCLI;
	private String adminPhoneExp;
	private String adminPhoneNew;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsserverIP;
	private String xmsuser;
	private String xmspasswd;
	private String adminPhoneOID;
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
	public void testCase_VerifydefaultAdminPhonevalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-5.1_Verify default value for Admin Phone in Array");
		//*****************************************************************
		adminPhoneExp=""; //Default Admin Phone value should be empty
		adminPhoneNew="723-584-9837";
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");
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
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			//Verification by SNMP, CLI and GUI
			//get Admin Phone value configured into array from GUI
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone name configured into array by GUI
			App_Log.debug("Ref-5.1_GUI-Verification:-Verify Admin Phone value Configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneExp);
			//Verify Admin Phone value configured into array by SNMP
			App_Log.debug("Ref-5.1_SNMP-Verification:-Verify Admin Phone value Configured into Array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			if(adminPhoneSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminPhoneSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneExp);
			//Verify Admin Phone configured into Array by CLI
			App_Log.debug("Ref-5.1_CLI-Verification:-Verify Admin Phone value configured into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneExp);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-5.1_Verify default value for Admin Phone in Array");
	}
	
	@Test(priority=2)
	public void testCase_VerifyAdminPhoneFieldvalidation() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-5.2_Verify Field validation for Admin Phone in Array");
		//*****************************************************************
		adminPhoneExp=CONFIG.getProperty("AdminPhone");
		adminPhoneNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bc-a"; //51 charactor string
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		xmslocallib.setText("Admin Phone", adminPhoneNew);
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-5.2_Verification:-Admin Phone is longer than the max of 50 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Admin Phone is longer than the max of 50 characters";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("AdminPhoneWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AdminPhoneWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//50 Character for Admin Contact name
		adminPhoneNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bca"; //50 charactor string
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Phone", adminPhoneNew);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			xmslocallib.pause(2000);
			//Verification on Admin value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone value configured into array by GUI
			App_Log.debug("Ref-5.2_GUI-Verification:-Verify Admin Phone value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneNew);
			//Verify Admin Phone value get Configured into array by SNMP
			App_Log.debug("Ref-5.2_SNMP-Verification:-Verify Admin Phone value configured into array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneNew);
			//Verify Admin Phone value get pushed into array by CLI
			App_Log.debug("Ref-5.2_CLI-Verification:-Verify Admin Phone value configured into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult&&warning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-5.2_Verify Field validation for Admin Phone in Array");
	}
	@Test(priority=3)
	public void testCase_VerifyAdminPhoneSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-5.3_Verify set value for Admin Phone in Array");
		//*****************************************************************
		adminPhoneExp=CONFIG.getProperty("AdminPhone"); 
		adminPhoneNew="(805)256-1702";
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Set Admin Phone value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Phone", adminPhoneExp);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			xmslocallib.pause(2000);
			//Verification on Admin Phone value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone configured into array by GUI
			App_Log.debug("Ref-5.3_GUI-Verification:-Verify Admin Phone value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneExp);
			//Verify Admin Phone name get Configured into array by SNMP
			App_Log.debug("Ref-5.3_SNMP-Verification:-Verify Admin Phone value configured into array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneExp);
			//Verify Admin Phone get Configured into array by CLI
			App_Log.debug("Ref-5.3_CLI-Verification:-Verify Admin Phone value configured into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneExp);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-5.3_Verify set value for Admin Phone in Array");
		
	}
	@Test(priority=4)
	public void testCase_VerifyAdminPhoneEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-5.4_Verify edit value for Admin Phone in Array");
		//*****************************************************************
		adminPhoneExp=CONFIG.getProperty("AdminPhone"); 
		adminPhoneNew="(505)256-9000";
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Edit Admin Phone value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Phone", adminPhoneNew);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			xmslocallib.pause(2000);
			//Verification on Admin Phone value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone name configured into array by GUI
			App_Log.debug("Ref-5.4_GUI-Verification:-Verify Admin Phone value updated into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneNew);
			//Verify Admin Phone get Configured into array by SNMP
			App_Log.debug("Ref-5.4_SNMP-Verification:-Verify Admin Phone value updated into array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneNew);
			//Verify Admin Phone value get pushed into array by CLI
			App_Log.debug("Ref-5.4_CLI-Verification:-Verify Admin Phone value updated into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-5.4_Verify edit value for Admin Phone in Array");
	}
	@Test(priority=5)
	public void testCase_VerifyAdminPhoneEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-5.5_Verify empty value for Admin Phone in Array");
		//*****************************************************************
		adminPhoneExp=CONFIG.getProperty("AdminPhone"); 
		adminPhoneNew="";
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");
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
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Phone", adminPhoneNew);
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
			if (! xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}
			}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			//Verification by SNMP, CLI and GUI
			//Navigate to Array Config General settings
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//get Admin Phone value configured into array from GUI
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone value configured into array by GUI
			App_Log.debug("Ref-5.5_GUI-Verification:-Verify Admin Phone value Configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneNew);
			//Verify Admin Phone value configured into array by SNMP
			App_Log.debug("Ref-5.5_SNMP-Verification:-Verify Admin Phone value Configured into Array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			if(adminPhoneSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminPhoneSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneNew);
			//Verify Admin Phone value configured into Array by CLI
			App_Log.debug("Ref-5.5_CLI-Verification:-Verify Admin Phone value configured into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliResult=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-5.5_Verify empty value for Admin Phone in Array");
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
