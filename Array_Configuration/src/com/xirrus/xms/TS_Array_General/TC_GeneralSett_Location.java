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
public class TC_GeneralSett_Location extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String locationInfoSNMP;
	private String locationInfoGUI;
	private String locationInfoCLI;
	private String locationInfoExp;
	private String locationInfoNew;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsserverIP;
	private String xmsuser;
	private String xmspasswd;
	private String locationInfoOID;
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
	public void testCase_VerifydefaultLocationInfovalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify default value for LocationInfo in Array");
		//*****************************************************************
		locationInfoExp=""; //Default Admin Contact value should be empty
		locationInfoNew="Simi Valley";
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
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
			//get Location Information value configured into array from GUI
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information configured into array by GUI
			App_Log.debug("Ref-2.1_GUI-Verification:-Verify Location Information value Configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoExp);
			//Verify Location Information configured into array by SNMP
			App_Log.debug("Ref-2.1_SNMP-Verification:-Verify Location Information value Configured into Array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			if(locationInfoSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				locationInfoSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoExp);
			//Verify Location information name configured into Array by CLI
			App_Log.debug("Ref-2.1_CLI-Verification:-Verify Location Information value configured into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean cliResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.1_Verify default value for LocationInfo in Array");
	}
	
	@Test(priority=2)
	public void testCase_VerifyLocationInfoFieldvalidation() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-2.2_Verify Field validation for LocationInfo in Array");
		//*****************************************************************
		locationInfoExp=CONFIG.getProperty("AdminPhone");
		locationInfoNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bc-a"; //51 charactor string
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
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
		xmslocallib.setText("Location Information", locationInfoNew);
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.2_Verification:-Location Information is longer than the max of 50 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Location Information is longer than the max of 50 characters";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("LocationInfoWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("LocationInfoWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//50 Character for Admin Contact name
		locationInfoNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bca"; //50 charactor string
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Location Information", locationInfoNew);
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
			//Verification on Location Information  value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information value configured into array by GUI
			App_Log.debug("Ref-2.2_GUI-Verification:-Verify Location Information value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoNew);
			//Verify Admin Phone value get Configured into array by SNMP
			App_Log.debug("Ref-2.2_SNMP-Verification:-Verify Location Information value configured into array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			boolean snmpResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoNew);
			//Verify Location Information  value get configured into array by CLI
			App_Log.debug("Ref-2.2_CLI-Verification:-Verify Location Information value configured into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean cliResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult&&warning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.2_Verify Field validation for LocationInfo in Array");
	}
	@Test(priority=3)
	public void testCase_VerifyLocationInfoSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify set value for LocationInfo in Array");
		//*****************************************************************
		locationInfoExp=CONFIG.getProperty("LocationInformation"); 
		locationInfoNew="Simi Valley";
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
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
			xmslocallib.setText("Location Information", locationInfoExp);
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
			//Verification on Location Information configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information configured into array by GUI
			App_Log.debug("Ref-2.3_GUI-Verification:-Verify Location Information value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoExp);
			//Verify Location Information get Configured into array by SNMP
			App_Log.debug("Ref-2.3_SNMP-Verification:-Verify Location Information value configured into array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			boolean snmpResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoExp);
			//Verify Location Information name get configured into array by CLI
			App_Log.debug("Ref-2.3_CLI-Verification:-Verify Location Information value configured into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean cliResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.3_Verify set value for LocationInfo in Array");
		
	}
	@Test(priority=4)
	public void testCase_VerifyLocationInfoEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.4_Verify edit value for LocationInfo in Array");
		//*****************************************************************
		locationInfoExp=CONFIG.getProperty("LocationInformation"); 
		locationInfoNew="Simi Valley";
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Edit Location Information value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Location Information", locationInfoNew);
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
			//Verification on Location Information configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information configured into array by GUI
			App_Log.debug("Ref-2.4_GUI-Verification:-Verify Location Information value updated into array");
			boolean guiResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoNew);
			//Verify Location Information get Configured into array by SNMP
			App_Log.debug("Ref-2.4_SNMP-Verification:-Verify Location Information value updated into array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			boolean snmpResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoNew);
			//Verify Location Information get pushed into array by CLI
			App_Log.debug("Ref-2.4_CLI-Verification:-Verify Location Information value updated into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean cliResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.4_Verify edit value for LocationInfo in Array");
	}
	@Test(priority=5)
	public void testCase_VerifyLocationInfoEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.5_Verify empty value for LocationInfo in Array");
		//*****************************************************************
		locationInfoExp=CONFIG.getProperty("LocationInformation"); 
		locationInfoNew="";
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
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
			xmslocallib.setText("Location Information", locationInfoNew);
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
			//get Location Information value configured into array from GUI
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information configured into array by GUI
			App_Log.debug("Ref-2.5_GUI-Verification:-Verify Location Information value empty into array");
			boolean guiResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoNew);
			//Verify Location Information configured into array by SNMP
			App_Log.debug("Ref-2.5_SNMP-Verification:-Verify Location Information value empty into Array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			if(locationInfoSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				locationInfoSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoNew);
			//Verify Location Information configured into Array by CLI
			App_Log.debug("Ref-2.5_CLI-Verification:-Verify Location Information value empty into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean cliResult=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.5_Verify empty value for LocationInfo in Array");
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
