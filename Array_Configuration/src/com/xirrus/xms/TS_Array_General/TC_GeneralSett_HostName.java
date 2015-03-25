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
public class TC_GeneralSett_HostName extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String arrayHostnameSNMP;
	private String arrayHostnameGUI;
	private String arrayHostnameCLI;
	private String arrayHostnameExp;
	private String arrayHostnameNew;
	private String arrayHostnameOID;
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
	public void testCase_VerifydefaultHostnamevalueNotEmpty() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify default value for Hostname is not empty in Array");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew="TestingArray";
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
			//get Location Information value configured into array from GUI
			arrayHostnameGUI = xmslocallib.getText("Hostname");
			//Verify Location Information configured into array by GUI
			App_Log.debug("Ref-1.1_GUI-Verification:-Verify Array Hostname is not empty by default in array");
			if(arrayHostnameGUI.equals("")){
				 guiResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				 guiResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//Verify Location Information configured into array by SNMP
			App_Log.debug("Ref-1.1_SNMP-Verification:-Verify Array Hostname is not empty by default in array");
			arrayHostnameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, arrayHostnameOID);
			if(arrayHostnameSNMP.equals("")){
				snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//Verify Location information name configured into Array by CLI
			App_Log.debug("Ref-1.1_CLI-Verification:-Verify Array Hostname is not empty by default in array");
			arrayHostnameCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Hostname");
			if(arrayHostnameCLI.equals("")){
				cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "FAIL");
			}else{
				cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, "PASS", "PASS");
			}
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.1_Verify default value for Hostname is not empty in Array");
	}
	
	@Test(priority=2)
	public void testCase_verifyHostnameNotacceptemptyvalue() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-1.2_Verify Hostname Field not accept empty value in Array");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew="";
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
		xmslocallib.setText("Hostname", arrayHostnameNew);
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-1.2_Verification:-Verify Hostname Field not accept empty value in Array");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Hostname is required.";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("HostnameEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("HostnameEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		if(warning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.2_Verify Hostname Field not accept empty value in Array");
	}
	@Test(priority=3)
	public void testCase_verifyHostnameNotacceptvalueOtherThanIPOrHostname()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Hostname Field not accept value other than IP or hostname");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew="Kartik_@!";
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
		xmslocallib.setText("Hostname", arrayHostnameNew);
		xmslocallib.clickButton("Apply Config");
		//Verify that XMS warning message is displaying and parameter not configured
		App_Log.debug("Ref-1.3_GUI-Verification:-Verify Hostname Field not accept value other than IP or hostname");
		String warningmessage="must be a valid IP address or hostname";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("HostnameNotValidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("HostnameNotValidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		if(warning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.3_Verify Hostname Field not accept value other than IP or hostname");
		
	}
	@Test(priority=4)
	public void testCase_VerifyHostnamesetIPAddressValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.4_Verify Hostname set value IP address in Array");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew=CONFIG.getProperty("ArrayIPAddress");
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
			xmslocallib.setText("Hostname", arrayHostnameNew);
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
			//Verification Hostname configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			arrayHostName=arrayHostnameNew;
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			arrayHostnameGUI = xmslocallib.getText("Hostname");
			//Verify Array hostname configured into array by GUI
			App_Log.debug("Ref-1.4_GUI-Verification:-Verify Array Hostname value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameGUI, arrayHostnameNew);
			//Verify Location Information get Configured into array by SNMP
			App_Log.debug("Ref-1.4_SNMP-Verification:-Verify Array Hostname value configured into array");
			arrayHostnameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, arrayHostnameOID);
			boolean snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameSNMP, arrayHostnameNew);
			//Verify Location Information name get configured into array by CLI
			App_Log.debug("Ref-1.4_CLI-Verification:-Verify Array Hostname value configured into array");
			arrayHostnameCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Hostname");
			boolean cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameCLI, arrayHostnameNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-1.4_Verify Hostname set value IP address in Array");
	}
	@Test(priority=5)
	public void testCase_VerifyHostnamesetValidNameValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify Hostname set value Valid Name in Array");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew="TestingArray";
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
		arrayHostName=CONFIG.getProperty("ArrayIPAddress");
			//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		xmslocallib.setText("Hostname", arrayHostnameNew);
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
		//Verification Hostname configured into Array by SNMP, CLI and GUI
		//Navigate to Array General Configuration
		arrayHostName=arrayHostnameNew;
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		//Get value configured into array 
		arrayHostnameGUI = xmslocallib.getText("Hostname");
		//Verify Array hostname configured into array by GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify Array Hostname value configured into array");
		boolean guiResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameGUI, arrayHostnameNew);
		//Verify Location Information get Configured into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify Array Hostname value configured into array");
		arrayHostnameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, arrayHostnameOID);
		boolean snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameSNMP, arrayHostnameNew);
		//Verify Location Information name get configured into array by CLI
		App_Log.debug("Ref-1.5_CLI-Verification:-Verify Array Hostname value configured into array");
		arrayHostnameCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Hostname");
		boolean cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameCLI, arrayHostnameNew);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.5_Verify Hostname set value Valid Name in Array");
	}
	@Test(priority=6)
	public void testCase_VerifyHostnameEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify Hostname edit value in Array");
		//*****************************************************************
		arrayHostnameExp=CONFIG.getProperty("ArrayHostName");
		arrayHostnameNew="TestingArray";
		arrayHostnameOID=SNMPOID.getProperty("HostnameOID");
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
		arrayHostName=arrayHostnameNew;
			//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		xmslocallib.setText("Hostname", arrayHostnameExp);
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
		//Verification Hostname configured into Array by SNMP, CLI and GUI
		//Navigate to Array General Configuration
		arrayHostName=arrayHostnameExp;
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		//Get value configured into array 
		arrayHostnameGUI = xmslocallib.getText("Hostname");
		//Verify Array hostname configured into array by GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify Array Hostname value configured into array");
		boolean guiResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameGUI, arrayHostnameExp);
		//Verify Location Information get Configured into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify Array Hostname value configured into array");
		arrayHostnameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, arrayHostnameOID);
		boolean snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameSNMP, arrayHostnameExp);
		//Verify Location Information name get configured into array by CLI
		App_Log.debug("Ref-1.5_CLI-Verification:-Verify Array Hostname value configured into array");
		arrayHostnameCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Hostname");
		boolean cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, arrayHostnameCLI, arrayHostnameExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.5_Verify Hostname edit value in Array");
	}
		
	
	@AfterTest
	public void testCompleted() throws IOException, InterruptedException{
		xmslocallib.setArrayHostname(CONFIG.getProperty("ArrayIPAddress"), CONFIG.getProperty("ArrayHostName"));
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
