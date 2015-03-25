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
public class TC_GeneralSett_Contact extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String adminContactSNMP;
	private String adminContactGUI;
	private String adminContactCLI;
	private String adminContactExp;
	private String adminContactNew;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsserverIP;
	private String xmsuser;
	private String xmspasswd;
	private String adminContactOID;
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
	public void testCase_VerifydefaultAdminContactvalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.1_Verify default value for Admin Contact in Array");
		//*****************************************************************
		adminContactExp=""; //Default Admin Contact value should be empty
		adminContactNew="Robin Hood";
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
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
			}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
			//Verification by SNMP, CLI and GUI
			//get Admin Contact value configured into array from GUI
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-3.1_GUI-Verification:-Verify Admin Contact value Configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactExp);
			//Verify Admin Contact name configured into array by SNMP
			App_Log.debug("Ref-3.1_SNMP-Verification:-Verify Admin Contact value Configured into Array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			if(adminContactSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminContactSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactExp);
			//Verify Admin Contact name configured into Array by CLI
			App_Log.debug("Ref-3.1_CLI-Verification:-Verify Admin Contact value configured into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean cliResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.1_Verify default value for Admin Contact in Array");
	}
	
	@Test(priority=2)
	public void testCase_VerifyAdminContactFieldvalidation() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-3.2_Verify Field validation for Admin Contact in Array");
		//*****************************************************************
		adminContactExp=CONFIG.getProperty("AdminContact");
		adminContactNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bc-a"; //51 charactor string
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
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
		xmslocallib.setText("Admin Contact", adminContactNew);
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-3.2_Verification:-Admin Contact is longer than the max of 50 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Admin Contact is longer than the max of 50 characters";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("AdminContactWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AdminContactWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//50 Character for Admin Contact name
		adminContactNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bca"; //50 charactor string
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Contact", adminContactNew);
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
			//Verification on Admin value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-3.2_GUI-Verification:-Verify Admin Contact value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactNew);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("Ref-3.2_SNMP-Verification:-Verify Admin Contact value configured into array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactNew);
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("Ref-3.2_CLI-Verification:-Verify Admin Contact value configured into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean cliResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult&&warning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		
		App_Log.debug("End-Ref-3.2_Verify Field validation for Admin Contact in Array");
	}
	@Test(priority=3)
	public void testCase_VerifyAdminContactSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.3_Verify set value for Admin Contact in Array");
		//*****************************************************************
		adminContactExp=CONFIG.getProperty("AdminContact"); 
		adminContactNew="Robin Hood";
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Set Admin Contact value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Contact", adminContactExp);
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
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-3.3_GUI-Verification:-Verify Admin Contact value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactExp);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("Ref-3.3_SNMP-Verification:-Verify Admin Contact value configured into array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactExp);
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("Ref-3.3_CLI-Verification:-Verify Admin Contact value configured into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean cliResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactExp);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.3_Verify set value for Admin Contact in Array");
		
	}
	@Test(priority=4)
	public void testCase_VerifyAdminContactEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.4_Verify edit value for Admin Contact in Array");
		//*****************************************************************
		adminContactExp=CONFIG.getProperty("AdminContact"); 
		adminContactNew="Robin Hood";
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Edit Admin Contact value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Contact", adminContactNew);
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
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-3.4_GUI-Verification:-Verify Admin Contact value updated into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactNew);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("Ref-3.4_SNMP-Verification:-Verify Admin Contact value updated into array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactNew);
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("Ref-3.4_CLI-Verification:-Verify Admin Contact value updated into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean cliResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.4_Verify edit value for Admin Contact in Array");
	}
	@Test(priority=5)
	public void testCase_VerifyAdminContactEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.5_Verify empty value for Admin Contact in Array");
		//*****************************************************************
		adminContactExp=CONFIG.getProperty("AdminContact"); 
		adminContactNew="";
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
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
			xmslocallib.setText("Admin Contact", adminContactNew);
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
			//get Admin Contact value configured into array from GUI
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-3.5_GUI-Verification:-Verify Admin Contact value empty into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactNew);
			//Verify Admin Contact name configured into array by SNMP
			App_Log.debug("Ref-3.5_SNMP-Verification:-Verify Admin Contact value empty into Array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			if(adminContactSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminContactSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactNew);
			//Verify Admin Contact name configured into Array by CLI
			App_Log.debug("Ref-3.5_CLI-Verification:-Verify Admin Contact value empty into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean cliResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.5_Verify empty value for Admin Contact in Array");
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
