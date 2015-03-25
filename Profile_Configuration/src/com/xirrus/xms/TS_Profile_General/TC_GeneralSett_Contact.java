package com.xirrus.xms.TS_Profile_General;

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
	private String adminContactAct;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsuser;
	private String xmspasswd;
	private String profileName;
	private String adminContactOID;
	private String screencaptureFileName;
	private String methodName;
	
	@BeforeTest
	public void checkTestSkip(){
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTC-TestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Profile_General_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		
		
	}
	
	@Test(priority=1)
	public void testCase_VerifydefaultAdminContactvalue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify default value for Admin Contact in Profile");
		//*******************************************************
		adminContactAct=CONFIG.getProperty("AdminContact");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//*******************************************************
		App_Log.debug("StartTC-Setup:-"+this.getClass().getSimpleName()+" Test Case setup");
		try{
		//Delete all profiles if exists first
		xmslocallib.deletAllProfile();
		//Create new profile with profile name from configuration file
		xmslocallib.createProfile(profileName);
		//#Add Array into profile
		xmslocallib.addArrayintoProfile(arrayIPadd, profileName);
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			
		}
		App_Log.debug("EndTC-Setup:-"+this.getClass().getSimpleName()+" Test Case setup");
		App_Log.debug("Ref-1.1-Verification:-Verify default value for Admin Contact in profile");
		try{
		//Navigate to new Profile created General Settings
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(profileName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//Get String value for Admin Contact Field
		adminContactGUI = xmslocallib.getText("Admin Contact");
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="Error-"+this.getClass().getSimpleName()+"-"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify default value for Admin Contact in profile
		App_Log.debug("Ref-1.1_GUI-Verification:-Verify default value for Admin Contact in profile");
		xmslocallib.verifyStringequals("AdminContact",this.getClass().getSimpleName(), methodName, adminContactGUI,"" );
		//Post result for Pass or FAIL
		//TBD
		App_Log.debug("End-Ref-1.1_Verify default value for Admin Contact in Profile");
	}
	
	@Test(priority=2)
	public void testCase_VerifyAdminContactFieldvalidation() throws IOException{
		App_Log.debug("Start-Ref-1.2_Verify Field validation for Admin Contact in Profile");
		//TBD
		App_Log.debug("End-Ref-1.2_Verify Field validation for Admin Contact in Profile");
	}
	@Test(priority=3)
	public void testCase_VerifyAdminContactSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify set value for Admin Contact in Profile");
		//**********************************************************
		adminContactAct=CONFIG.getProperty("AdminContact");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//**********************************************************
		try{
		//#Navigate to Admin Contact
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(profileName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//#Set Admin Contact information
		xmslocallib.setText("Admin Contact", adminContactAct);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.clickButton("OK");
		xmslocallib.setTimeout(90);
		if (! xmslocallib.findText("Done configuring Array")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
		}
		xmslocallib.pause(2000);
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="Error-"+this.getClass().getSimpleName()+"-"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify Admin Contact name get pushed into array by SNMP
		App_Log.debug("Ref-1.3_SNMP-Verification:-Verify Admin Contact value pushed into array through profile");
		adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactAct);
		//Assert.assertEquals(adminContactAct,adminContactSNMP);
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-1.3_CLI-Verification:-Verify Admin Contact value pushed into array through profile");
		adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactAct);
		//Assert.assertEquals(adminContactAct,adminContactCLI);
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into aray through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminContactGUI = xmslocallib.getText("Admin Contact");
		//Verify Admin Contact name pushed into array GUI
		App_Log.debug("Ref-1.3_GUI-Verification:-Verify Admin Contact value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactAct);
		//Assert.assertEquals(adminContactAct,adminContactGUI);
		//Post result for Pass or FAIL
		//TBD
		App_Log.debug("End-Ref-1.3_Verify set value for Admin Contact in Profile");
	}
	@Test(priority=4)
	public void testCase_VerifyAdminContactEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.4_Verify edit value for Admin Contact in Profile");
		//**********************************************************
		adminContactAct=CONFIG.getProperty("AdminContact");
		String adminContactNew="Robin Hood";
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//**********************************************************
		try{
		//Navigate to General Settings in Profile
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(profileName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//#Edit Admin Contact information
		xmslocallib.setText("Admin Contact", adminContactNew);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.clickButton("OK");
		xmslocallib.setTimeout(90);
		if (! xmslocallib.findText("Done configuring Array")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
		}
		xmslocallib.pause(1000);
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="Error-"+this.getClass().getSimpleName()+"-"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify Admin Contact name get pushed into array by SNMP
		App_Log.debug("Ref-1.4_SNMP-Verification:-Verify modified Admin Contact value pushed into array through profile");
		adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactNew);
		//Assert.assertEquals(adminContactSNMP,adminContactNew);
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-1.4_CLI-Verification:-Verify modified Admin Contact value pushed into array through profile");
		adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactNew);
		//Assert.assertEquals(adminContactCLI,adminContactNew);
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into aray through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminContactGUI = xmslocallib.getText("Admin Contact");
		//Verify Admin Contact name pushed into array GUI
		App_Log.debug("Ref-1.4_GUI-Verification:-Verify modified Admin Contact value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactNew);
		//Assert.assertEquals(adminContactGUI,adminContactNew);
		//Post result for Pass or FAIL
		//TBD
		
		App_Log.debug("End-Ref-1.4_Verify edit value for Admin Contact in Profile");
	}
	@Test(priority=5)
	public void testCase_VerifyAdminContactEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify empty value for Admin Contact in Profile");
		//**********************************************************
		adminContactAct=CONFIG.getProperty("AdminContact");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//**********************************************************
		try{
		//Navigate to General Settings in Profile
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(profileName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//#Empty Admin Contact information
		xmslocallib.setText("Admin Contact", "");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.clickButton("OK");
		xmslocallib.setTimeout(90);
		if (! xmslocallib.findText("Done configuring Array")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
		}
		xmslocallib.pause(1000);
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="Error-"+this.getClass().getSimpleName()+"-"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify Admin Contact name get pushed into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify empty Admin Contact value pushed into array through profile");
		adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
		if(adminContactSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
			adminContactSNMP="";
		}
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, "");
		//Assert.assertEquals(adminContactSNMP,"");
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-1.5_CLI-Verification:-Verify empty Admin Contact value pushed into array through profile");
		adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, "");
		//Assert.assertEquals(adminContactCLI,"");
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into array through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminContactGUI = xmslocallib.getText("Admin Contact");
		//Verify Admin Contact name pushed into array GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify empty Admin Contact value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, "");
		//Assert.assertEquals(adminContactGUI,"");
		//Post result for Pass or FAIL
		//TBD
		App_Log.debug("End-Ref-1.5_Verify empty value for Admin Contact in Profile");
		
	}
	@AfterTest
	public void testCompleted(){
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Profile_Menu_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
