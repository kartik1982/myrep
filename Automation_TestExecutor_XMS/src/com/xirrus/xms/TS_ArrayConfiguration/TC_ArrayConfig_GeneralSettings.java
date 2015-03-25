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
public class TC_ArrayConfig_GeneralSettings extends TestSuiteBase{
	//General Settings Parameters
	private String adminContactSNMP;
	private String adminContactGUI;
	private String adminContactCLI;
	private String adminContactExp;
	private String adminContactOID;
	private String locationInfoSNMP;
	private String locationInfoGUI;
	private String locationInfoCLI;
	private String locationInfoExp;
	private String locationInfoOID;
	private String adminEmailSNMP;
	private String adminEmailGUI;
	private String adminEmailCLI;
	private String adminEmailExp;
	private String adminEmailOID;
	private String adminPhoneSNMP;
	private String adminPhoneGUI;
	private String adminPhoneCLI;
	private String adminPhoneExp;
	private String adminPhoneOID;

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
	public void testCase_VerifyArrayConfig_GeneralSettingsParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify ArrayConfig GeneralSettings Parameters");
		// *****************************************************************
		locationInfoExp = CONFIG.getProperty("LocationInformation");
		adminContactExp = CONFIG.getProperty("AdminContact");
		adminEmailExp = CONFIG.getProperty("AdminEmail");
		adminPhoneExp = CONFIG.getProperty("AdminPhone");
		
		locationInfoOID=SNMPOID.getProperty("LocationInformationOID");
		adminContactOID=SNMPOID.getProperty("AdminContactOID");
		adminEmailOID=SNMPOID.getProperty("AdminEmailOID");
		adminPhoneOID=SNMPOID.getProperty("AdminPhoneOID");

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
			//Get value configured into array 
			adminContactGUI = xmslocallib.getText("Admin Contact");
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("GUI-Verification:-Verify Admin Contact value configured into array");
			boolean guicontact=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactGUI, adminContactExp);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("SNMP-Verification:-Verify Admin Contact value configured into array");
			adminContactSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminContactOID);
			boolean snmpcontact=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactSNMP, adminContactExp);
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("CLI-Verification:-Verify Admin Contact value configured into array");
			adminContactCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Name");
			boolean clicontact=xmslocallib.verifyStringequals("AdminConact", this.getClass().getSimpleName(), methodName, adminContactCLI, adminContactExp);
			
			//Get value configured into array 
			locationInfoGUI = xmslocallib.getText("Location Information");
			//Verify Location Information configured into array by GUI
			App_Log.debug("GUI-Verification:-Verify Location Information value configured into array");
			boolean guilocation=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoGUI, locationInfoExp);
			//Verify Location Information get Configured into array by SNMP
			App_Log.debug("SNMP-Verification:-Verify Location Information value configured into array");
			locationInfoSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, locationInfoOID);
			boolean snmplocation=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoSNMP, locationInfoExp);
			//Verify Location Information get pushed into array by CLI
			App_Log.debug("CLI-Verification:-Verify Location Information value configured into array");
			locationInfoCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Array Location");
			boolean clilocation=xmslocallib.verifyStringequals("LocationInfo", this.getClass().getSimpleName(), methodName, locationInfoCLI, locationInfoExp);
			
			//Get value configured into array 
			adminEmailGUI = xmslocallib.getText("Admin Email");
			//Verify Admin Email configured into array by GUI
			App_Log.debug("GUI-Verification:-Verify Admin Email value configured into array");
			boolean guiemail=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailGUI, adminEmailExp);
			//Verify Admin Email name get Configured into array by SNMP
			App_Log.debug("SNMP-Verification:-Verify Admin Email value configured into array");
			adminEmailSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminEmailOID);
			boolean snmpemail=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailSNMP, adminEmailExp);
			//Verify Admin Email name get pushed into array by CLI
			App_Log.debug("CLI-Verification:-Verify Admin Email value configured into array");
			adminEmailCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact E-Mail");
			boolean cliemail=xmslocallib.verifyStringequals("AdminEmail", this.getClass().getSimpleName(), methodName, adminEmailCLI, adminEmailExp);
			
			//Get value configured into array 
			adminPhoneGUI = xmslocallib.getText("Admin Phone");
			//Verify Admin Phone value configured into array by GUI
			App_Log.debug("GUI-Verification:-Verify Admin Phone value configured into array");
			boolean guiphone=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneGUI, adminPhoneExp);
			//Verify Admin Phone value get Configured into array by SNMP
			App_Log.debug("SNMP-Verification:-Verify Admin Phone value configured into array");
			adminPhoneSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, adminPhoneOID);
			boolean snmpphone=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneSNMP, adminPhoneExp);
			//Verify Admin Phone value get pushed into array by CLI
			App_Log.debug("CLI-Verification:-Verify Admin Phone value configured into array");
			adminPhoneCLI=xmslocallib.getValuebyCLI_ContactInfo(arrayIPadd, "Contact Phone");
			boolean cliphone=xmslocallib.verifyStringequals("AdminPhone", this.getClass().getSimpleName(), methodName, adminPhoneCLI, adminPhoneExp);
		
		App_Log.debug("End-Verify ArrayConfig GeneralSettings Parameters");		
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
