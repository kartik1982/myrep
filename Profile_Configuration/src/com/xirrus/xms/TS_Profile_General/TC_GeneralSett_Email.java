package com.xirrus.xms.TS_Profile_General;

import java.io.IOException;

import com.xirrus.xms.globallib.SnmpClient;
import com.xirrus.xms.globallib.SslShell;
import com.xirrus.xms.globallib.Test_Utility;


import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_GeneralSett_Email extends TestSuiteBase{
	private String adminEmailSNMP;
	private String adminEmailGUI;
	private String adminEmailCLI;
	private String adminEmailAct;
	private String arrayIPadd;
	private String arrayHostName;
	private String communityStr;
	private String xmsuser;
	private String xmspasswd;
	private String profileName;
	private String adminEmailOID;
	private String screencaptureFileName;
	private String methodName;
	//This will check runmode of test case in excel sheet
	private String adminEmail;
	@BeforeTest
	public void checkTestSkip(){
		App_Log.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of "+this.getClass().getSimpleName()+" Test Case");
		if(!Test_Utility.isTestCaseRunnable(suite_Profile_General_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
	}
	
	@Test(priority=1)
	public void testCase_VerifydefaultAdminEmailvalue() throws InterruptedException, IOException{
		App_Log.debug("Start_Ref-2.1_Verify default value for Admin Email in Profile");
		//*******************************************************
		adminEmailAct=CONFIG.getProperty("AdminEmail");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//*******************************************************
		//Delete all profiles if exists first
		//xmslocallib.deletAllProfile(); //****Covered in first script
		//Create new profile with profile name from configuration file
		//xmslocallib.createProfile(profileName); //****Covered in first script
		//#Add Array into profile
		//xmslocallib.addArrayintoProfile(arrayIPadd, profileName); //Covered in First Script
		//TBD
		try{
		//Navigate to new Profile created General Settings
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(profileName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//Get String value for Admin Contact Field
		adminEmailGUI = xmslocallib.getText("Admin Email");
		}catch (Exception e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="Error-"+this.getClass().getSimpleName()+"-"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify default value for Admin Contact in profile
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify default value for Admin Email in profile");
		xmslocallib.verifyStringequals("AdminEmail",this.getClass().getSimpleName(), methodName, adminEmailGUI,"" );
		//Assert.assertEquals(adminEmailGUI, "");
		//Post result for Pass or FAIL
		//TBD
		//TBD
		App_Log.debug("End_Ref-2.1_Verify default value for Admin Email in Profile");
		
	}
	
	@Test(priority=2)
	public void testCase_VerifyAdminEmailFieldvalidation(){
		App_Log.debug("Start_Ref-2.2_Verify Field validation for Admin Email in Profile");
		//TBD
		App_Log.debug("End_Ref-2.2_Verify Field validation for Admin Email in Profile");
	}
	@Test(priority=3)
	public void testCase_VerifyAdminEmailSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start_Ref-2.3_Verify set value for Admin Email in Profile");
		//**********************************************************
		adminEmailAct=CONFIG.getProperty("AdminEmail");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
		methodName = new Object(){}.getClass().getEnclosingMethod().getName();
		//**********************************************************
		try{
		//#Navigate to Admin Email
		navigatelib.ConfigureProfile();
		xmslocallib.clickLink(CONFIG.getProperty("ProfileName"));
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("General");
		xmslocallib.clickLink("General Settings");
		//#Set Admin Contact information
		xmslocallib.setText("Admin Email", adminEmailAct);
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
		App_Log.debug("Ref-2.3_SNMP-Verification:-Verify Admin Email value pushed into array through profile");
		adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailAct);
		//Assert.assertEquals(adminEmailSNMP, adminEmailAct);
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-2.3_CLI-Verification:-Verify Admin Email value pushed into array through profile");
		adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailAct);
		//Assert.assertEquals(adminEmailCLI, adminEmailAct);
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into aray through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminEmailGUI = xmslocallib.getText("Admin Email");
		//Verify Admin Contact name pushed into array GUI
		App_Log.debug("Ref-2.3_GUI-Verification:-Verify Admin Contact value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailAct);
		//Assert.assertEquals(adminEmailGUI, adminEmailAct);
		//Post result for Pass or FAIL
		//TBD
		App_Log.debug("End_Ref-2.3_Verify set value for Admin Email in Profile");
	}
	
	@Test(priority=4)
	public void testCase_VerifyAdminEmailEditValue() throws InterruptedException, IOException{
		App_Log.debug("Start_Ref-2.4_Verify edit value for Admin Email in Profile");
		//**********************************************************
		adminEmailAct=CONFIG.getProperty("AdminEmail");
		String adminEmailNew="Robin.Hood@xirrus.com";
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
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
		xmslocallib.setText("Admin Email", adminEmailNew);
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
		App_Log.debug("Ref-2.4_SNMP-Verification:-Verify modified Admin Email value pushed into array through profile");
		adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailNew);
		//Assert.assertEquals(adminEmailSNMP,adminEmailNew);
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-2.4_CLI-Verification:-Verify modified Admin Email value pushed into array through profile");
		adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailNew);
		//Assert.assertEquals(adminEmailCLI,adminEmailNew);
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into aray through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminEmailGUI = xmslocallib.getText("Admin Email");
		//Verify Admin Contact name pushed into array GUI
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify modified Admin Email value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailNew);
		//Assert.assertEquals(adminEmailGUI,adminEmailNew);
		//Post result for Pass or FAIL
		//TBD
		//TBD
		App_Log.debug("End_Ref-2.4_Verify edit value for Admin Email in Profile");
	}
	@Test(priority=5)
	public void testCase_VerifyAdminEmailEmptyValue() throws InterruptedException, IOException{
		App_Log.debug("Start_Ref-2.5_Verify empty value for Admin Email in Profile");
		//**********************************************************
		adminEmailAct=CONFIG.getProperty("AdminEmail");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr= CONFIG.getProperty("community");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		profileName=CONFIG.getProperty("ProfileName");
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
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
		xmslocallib.setText("Admin Email", "");
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
		App_Log.debug("Ref-2.5_SNMP-Verification:-Verify empty Admin Email value pushed into array through profile");
		adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
		if(adminEmailSNMP.contains("1.3.6.1.4.1.21013.1.2.28")){
			adminEmailSNMP="";
		}
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, "");
		//Assert.assertEquals(adminEmailSNMP,"");
		//Post result for Pass or FAIL
		//TBD
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-2.5_CLI-Verification:-Verify empty Admin Email value pushed into array through profile");
		adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, "");
		//Assert.assertEquals(adminEmailCLI,"");
		//Post result for Pass or FAIL
		//TBD
		//Get value pushed into aray through profile
		navigatelib.ConfigureAccesssPoint();
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");		
		xmslocallib.clickLink("General",2 );
		xmslocallib.clickLink("General Settings");
		adminEmailGUI = xmslocallib.getText("Admin Email");
		//Verify Admin Email pushed into array GUI
		App_Log.debug("Ref-2.5_GUI-Verification:-Verify empty Admin email value pushed into array through profile");
		xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, "");
		//Assert.assertEquals(adminEmailGUI,"");
		//Post result for Pass or FAIL
		//TBD
		App_Log.debug("End_Ref-2.5_Verify empty value for Admin Email in Profile");
		
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
