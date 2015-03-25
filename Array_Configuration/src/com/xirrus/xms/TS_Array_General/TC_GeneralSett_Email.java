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
public class TC_GeneralSett_Email extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String adminEmailSNMP;
	private String adminEmailGUI;
	private String adminEmailCLI;
	private String adminEmailExp;
	private String adminEmailNew;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsserverIP;
	private String xmsuser;
	private String xmspasswd;
	private String adminEmailOID;
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
	public void testCase_VerifydefaultAdminEmailvalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.1_Verify default value for Admin Email in Array");
		//*****************************************************************
		adminEmailExp=""; //Default Admin Email value should be empty
		adminEmailNew="Robin Hood";
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
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
			//Navigate to Array Configuration General Settings
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
			//Verification Admin Email by SNMP, CLI and GUI
			//get Admin Email value configured into array from GUI
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email name configured into array by GUI
			App_Log.debug("Ref-4.1_GUI-Verification:-Verify Admin Email value Configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailExp);
			//Verify Admin Email name configured into array by SNMP
			App_Log.debug("Ref-4.1_SNMP-Verification:-Verify Admin Email value Configured into Array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			if(adminEmailSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminEmailSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailExp);
			//Verify Admin Email name configured into Array by CLI
			App_Log.debug("Ref-4.1_CLI-Verification:-Verify Admin Email value configured into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailExp);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.1_Verify default value for Admin Email in Array");
	}
	
	@Test(priority=2)
	public void testCase_VerifyAdminEmailFieldvalidation() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-4.2_Verify Field validation for Admin Email in Array");
		//*****************************************************************
		adminEmailExp=CONFIG.getProperty("AdminEmail");
		adminEmailNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bc-a"; //51 charactor string
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
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
		xmslocallib.setText("Admin Email", adminEmailNew);
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-4.2_Verification:-Admin Email is longer than the max of 50 characters");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Admin Email is longer than the max of 50 characters";
		boolean warning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(warning){
			xmslocallib.verifyStringequals("AdminEmailWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AdminEmailWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//50 Character for Admin Contact name
		adminEmailNew="abcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+<>?{:}{|bca"; //50 charactor string
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Email", adminEmailNew);
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
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email name configured into array by GUI
			App_Log.debug("Ref-4.2_GUI-Verification:-Verify Admin Email value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailNew);
			//Verify Admin Email name get Configured into array by SNMP
			App_Log.debug("Ref-4.2_SNMP-Verification:-Verify Admin Email value configured into array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailNew);
			//Verify Admin Email name get pushed into array by CLI
			App_Log.debug("Ref-4.2_CLI-Verification:-Verify Admin Email value configured into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailNew);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult&&warning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.2_Verify Field validation for Admin Email in Array");
	}
	@Test(priority=3)
	public void testCase_VerifyAdminEmailSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.3_Verify set value for Admin Email in Array");
		//*****************************************************************
		adminEmailExp=CONFIG.getProperty("AdminEmail"); 
		adminEmailNew="Robin.Hood@xirrus.com";
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Set Admin Email value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Email", adminEmailExp);
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
			//Verification on Admin Email value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email configured into array by GUI
			App_Log.debug("Ref-4.3_GUI-Verification:-Verify Admin Email value configured into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailExp);
			//Verify Admin Email name get Configured into array by SNMP
			App_Log.debug("Ref-4.3_SNMP-Verification:-Verify Admin Email value configured into array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailExp);
			//Verify Admin Email name get pushed into array by CLI
			App_Log.debug("Ref-4.3_CLI-Verification:-Verify Admin Email value configured into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailExp);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.3_Verify set value for Admin Email in Array");
		
	}
	@Test(priority=4)
	public void testCase_VerifyAdminEmailEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.4_Verify edit value for Admin Email in Array");
		//*****************************************************************
		adminEmailExp=CONFIG.getProperty("AdminEmail"); 
		adminEmailNew="Robin.Hood@xirrus.com";
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Edit Admin Email value
		try{
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			xmslocallib.setText("Admin Email", adminEmailNew);
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
			//Verification on Admin Email value configured into Array by SNMP, CLI and GUI
			//Navigate to Array General Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//Get value configured into array 
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email name configured into array by GUI
			App_Log.debug("Ref-4.4_GUI-Verification:-Verify Admin Email value updated into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailNew);
			//Verify Admin Email get Configured into array by SNMP
			App_Log.debug("Ref-4.4_SNMP-Verification:-Verify Admin Email value updated into array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			boolean snmpResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailNew);
			//Verify Admin Email name get pushed into array by CLI
			App_Log.debug("Ref-4.4_CLI-Verification:-Verify Admin Email value updated into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.4_Verify edit value for Admin Email in Array");
	}
	@Test(priority=5)
	public void testCase_VerifyAdminEmailEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.5_Verify empty value for Admin Email in Array");
		//*****************************************************************
		adminEmailExp=CONFIG.getProperty("AdminEmail"); 
		adminEmailNew="";
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
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
			xmslocallib.setText("Admin Email", adminEmailNew);
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
			//Navigate to Array Config General setings
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");		
			xmslocallib.clickLink("General",2 );
			xmslocallib.clickLink("General Settings");
			//get Admin Email value configured into array from GUI
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email name configured into array by GUI
			App_Log.debug("Ref-4.5_GUI-Verification:-Verify Admin Email value empty into array");
			boolean guiResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailNew);
			//Verify Admin Email name configured into array by SNMP
			App_Log.debug("Ref-4.5_SNMP-Verification:-Verify Admin Email value empty into Array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			if(adminEmailSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
				adminEmailSNMP="";
			}
			boolean snmpResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailNew);
			//Verify Admin Email name configured into Array by CLI
			App_Log.debug("Ref-4.5_CLI-Verification:-Verify Admin Email value empty into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliResult=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailNew);
			
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.5_Verify empty value for Admin Email in Array");
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
