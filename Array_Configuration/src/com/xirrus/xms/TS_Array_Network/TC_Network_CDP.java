package com.xirrus.xms.TS_Array_Network;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Network_CDP extends TestSuiteBase{
	private String cdpStateSNMP;
	private String cdpStateGUI;
	private String cdpStateCLI;
	private String cdpIntervalSNMP;
	private String cdpIntervalGUI;
	private String cdpIntervalCLI;
	private String cdpHoldTimeSNMP;
	private String cdpHoldTimeGUI;
	private String cdpHoldTimeCLI;
	private String cdpStateExp;
	private String cdpIntervalExp;
	private String cdpHoldTimeExp;
	private String cdpEnableIOD;
	private String cdpIntervalIOD;
	private String cdpHoldTimeIOD;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Network_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-2.1_Verify CDP State Interval and Hold time Default valu
	@Test(priority=1)
	public void testCase_verifyCDPStateIntervalHoldTimeDefaultvalue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify CDP State Interval and Hold time Default value");
		//*****************************************************************
		cdpStateExp="disabled";
		cdpIntervalExp="60";
		cdpHoldTimeExp="180";
		cdpEnableIOD=SNMPOID.getProperty("CDPENableOID");
		cdpIntervalIOD=SNMPOID.getProperty("CDPIntervalOID");
		cdpHoldTimeIOD=SNMPOID.getProperty("CDPHoldTimeOID");
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
			//Navigate to Array General Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Network CDP configured into Array by SNMP, CLI and GUI
		//Get CDP State value configured into array 
		boolean cdpEnable=xmslocallib.isRadioButtonSelected("Enable CDP",2); //CDP Disable
		if(cdpEnable)
			cdpStateGUI="disabled";
		else
			cdpStateGUI="enabled";
		//Verify CDP Disable state configured into array by GUI
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify Network CDP State default value is disable in array");
		boolean guiCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateGUI, cdpStateExp);
		//Verify CDP Disable state configured into array by SNMP
		App_Log.debug("Ref-2.1_SNMP-Verification:-Verify Network CDP State default value is disable in array");
		cdpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpEnableIOD);
		boolean snmpCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateSNMP, "0");
		//Verify CDP Disable state configured into array by CLI
		App_Log.debug("Ref-2.1_CLI-Verification:-Verify Network CDP State default value is disable in array");
		cdpStateCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "State");
		boolean cliCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateCLI, cdpStateExp);
		//Get CDP Interval value configured into array 
		cdpIntervalGUI=xmslocallib.getText("CDP Interval");
		//Verify CDP Interval value configured into array by GUI
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify Network CDP Interval default value is disable in array");
		boolean guiCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalGUI, cdpIntervalExp);
		//Verify CDP Interval value configured into array by SNMP
		App_Log.debug("Ref-2.1_SNMP-Verification:-Verify Network CDP Interval default value is disable in array");
		cdpIntervalSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpIntervalIOD);
		boolean snmpCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalSNMP,cdpIntervalExp );
		//Verify CDP Interval Value configured into array by CLI
		App_Log.debug("Ref-2.1_CLI-Verification:-Verify Network CDP Interval default value is disable in array");
		cdpIntervalCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Interval");
		cdpIntervalCLI=cdpIntervalCLI.replace("seconds", "").trim();
		boolean cliCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalCLI, cdpIntervalExp);
		//Get CDP Hold Time value configured into array 
		cdpHoldTimeGUI=xmslocallib.getText("CDP Hold Time");
		//Verify CDP Hold Time value configured into array by GUI
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify Network CDP Hold Time default value is disable in array");
		boolean guiCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeGUI, cdpHoldTimeExp);
		//Verify CDP Interval value configured into array by SNMP
		App_Log.debug("Ref-2.1_SNMP-Verification:-Verify Network CDP Hold Time default value is disable in array");
		cdpHoldTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpHoldTimeIOD);
		boolean snmpCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeSNMP,cdpHoldTimeExp );
		//Verify CDP Hold Time Value configured into array by CLI
		App_Log.debug("Ref-2.1_CLI-Verification:-Verify Network CDP Hold Time default value is disable in array");
		cdpHoldTimeCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Hold time");
		cdpHoldTimeCLI=cdpHoldTimeCLI.replace("seconds", "").trim();
		boolean cliCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeCLI, cdpHoldTimeExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiCDPStateResult&&snmpCDPStateResult&&cliCDPStateResult&&guiCDPIntervalResult&&snmpCDPIntervalResult&&cliCDPIntervalResult&&guiCDPHoldResult&&snmpCDPHoldResult&&cliCDPHoldResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.1_Verify CDP State Interval and Hold time Default value");
	}
	//Ref-2.2_Verify CDP Enable State
	@Test(priority=2)
	public void testCase_verifyCDPStateEnableValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.2_Verify CDP Enable State");
		//*****************************************************************
		cdpStateExp="enabled";
		cdpIntervalExp="60";
		cdpHoldTimeExp="180";
		cdpEnableIOD=SNMPOID.getProperty("CDPENableOID");
		cdpIntervalIOD=SNMPOID.getProperty("CDPIntervalOID");
		cdpHoldTimeIOD=SNMPOID.getProperty("CDPHoldTimeOID");
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
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			xmslocallib.selectRadioButton("Enable CDP", 1);
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
			//Get CDP State value configured into array 
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			boolean cdpEnable=xmslocallib.isRadioButtonSelected("Enable CDP",1); //CDP Disable
			if(cdpEnable)
				cdpStateGUI="enabled";
			else
				cdpStateGUI="disabled";
			//Verify CDP Disable state configured into array by GUI
			App_Log.debug("Ref-2.2_GUI-Verification:-Verify Network CDP State Enabled value in array");
			boolean guiCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateGUI, cdpStateExp);
			//Verify CDP Disable state configured into array by SNMP
			App_Log.debug("Ref-2.2_SNMP-Verification:-Verify Network CDP State Enabled value in array");
			cdpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpEnableIOD);
			boolean snmpCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateSNMP, "1");
			//Verify CDP Disable state configured into array by CLI
			App_Log.debug("Ref-2.2_CLI-Verification:-Verify Network CDP State Enabled value in array");
			cdpStateCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "State");
			boolean cliCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateCLI, cdpStateExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(guiCDPStateResult&&snmpCDPStateResult&&cliCDPStateResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.2_Verify CDP Enable State");
	}
	//Ref-2.3_Verify CDP Interval and hold time Edit value
	@Test(priority=3)
	public void testCase_verifyCDPIntervalHoldTimeEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify CDP Interval and hold time Edit value");
		//*****************************************************************
		cdpStateExp="enabled";
		cdpIntervalExp=CONFIG.getProperty("CDPInterval");
		cdpHoldTimeExp=CONFIG.getProperty("CDPHoldTime");
		cdpEnableIOD=SNMPOID.getProperty("CDPENableOID");
		cdpIntervalIOD=SNMPOID.getProperty("CDPIntervalOID");
		cdpHoldTimeIOD=SNMPOID.getProperty("CDPHoldTimeOID");
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
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			xmslocallib.selectRadioButton("Enable CDP", 1);
			xmslocallib.setText("CDP Interval", cdpIntervalExp);
			xmslocallib.setText("CDP Hold Time", cdpHoldTimeExp);
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
			//Get CDP Interval and CDP Hold Time value configured into array 
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			//Get CDP Interval value configured into array 
			cdpIntervalGUI=xmslocallib.getText("CDP Interval");
			//Verify CDP Interval value configured into array by GUI
			App_Log.debug("Ref-2.3_GUI-Verification:-Verify Network CDP Interval value in array");
			boolean guiCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalGUI, cdpIntervalExp);
			//Verify CDP Interval value configured into array by SNMP
			App_Log.debug("Ref-2.3_SNMP-Verification:-Verify Network CDP Interval value in array");
			cdpIntervalSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpIntervalIOD);
			boolean snmpCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalSNMP,cdpIntervalExp );
			//Verify CDP Interval Value configured into array by CLI
			App_Log.debug("Ref-2.3_CLI-Verification:-Verify Network CDP Interval value in array");
			cdpIntervalCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Interval");
			cdpIntervalCLI=cdpIntervalCLI.replace("seconds", "").trim();
			boolean cliCDPIntervalResult=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalCLI, cdpIntervalExp);
			//Get CDP Hold Time value configured into array 
			cdpHoldTimeGUI=xmslocallib.getText("CDP Hold Time");
			//Verify CDP Hold Time value configured into array by GUI
			App_Log.debug("Ref-2.3_GUI-Verification:-Verify Network CDP Hold Time value in array");
			boolean guiCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeGUI, cdpHoldTimeExp);
			//Verify CDP Interval value configured into array by SNMP
			App_Log.debug("Ref-2.3_SNMP-Verification:-Verify Network CDP Hold Time value in array");
			cdpHoldTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpHoldTimeIOD);
			boolean snmpCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeSNMP,cdpHoldTimeExp );
			//Verify CDP Hold Time Value configured into array by CLI
			App_Log.debug("Ref-2.3_CLI-Verification:-Verify Network CDP Hold Time Value in array");
			cdpHoldTimeCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Hold time");
			cdpHoldTimeCLI=cdpHoldTimeCLI.replace("seconds", "").trim();
			boolean cliCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeCLI, cdpHoldTimeExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(guiCDPIntervalResult&&snmpCDPIntervalResult&&cliCDPIntervalResult&&guiCDPHoldResult&&snmpCDPHoldResult&&cliCDPHoldResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.3_Verify CDP Interval and hold time Edit value");
	}
	//Ref-2.4_Verify CDP Interval and Hold time field validation
	@Test(priority=4)
	public void testCase_verifyCDPIntervalHoldTimeFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.4_Verify CDP Interval and Hold time field validation");
		//*****************************************************************
		cdpStateExp="enabled";
		cdpIntervalExp="60";
		cdpHoldTimeExp="180";
		cdpEnableIOD=SNMPOID.getProperty("CDPENableOID");
		cdpIntervalIOD=SNMPOID.getProperty("CDPIntervalOID");
		cdpHoldTimeIOD=SNMPOID.getProperty("CDPHoldTimeOID");
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
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			xmslocallib.selectRadioButton("Enable CDP", 1);
			xmslocallib.setText("CDP Interval", "4");
			xmslocallib.clickButton("Apply Config");
			//xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(90);
			}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			}
		//Verify CDP Interval Field Validation
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Interval Should not accept value lower than 5 Sec");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="CDP Interval must be between 5 and 900";
		boolean CDPIntLowerwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPIntLowerwarning){
			xmslocallib.verifyStringequals("CDPIntervalLowerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPIntervalLowerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Set CDP Interval value higher than 900
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Netowrk LLDP Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		xmslocallib.setText("CDP Interval", "901");
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Interval Should not accept value upper than 900 Sec");
		//Verify that XMS warning message is displaying and parameter not configured
		boolean CDPIntUpperwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPIntUpperwarning){
			xmslocallib.verifyStringequals("CDPIntervalUpperWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPIntervalUpperWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Set CDP Interval with non integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Netowrk LLDP Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		xmslocallib.setText("CDP Interval", "Kartik");
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Interval Should accept only Integer value");
		//Verify that XMS warning message is displaying and parameter not configured
		boolean CDPIntnonIntegerwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPIntnonIntegerwarning){
			xmslocallib.verifyStringequals("CDPIntervalNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPIntervalNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Set CDP Hold Time with lower than 10 integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Netowrk LLDP Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		xmslocallib.setText("CDP Hold Time", "9");
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Hold Time Should not accept value lower than 10 Sec");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="CDP Hold Time must be between 10 and 255";
		boolean CDPHTLowwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPHTLowwarning){
			xmslocallib.verifyStringequals("CDPHoldTimeLowerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPHoldTimeLowerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Set CDP Hold Time with higher than 255 integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Netowrk LLDP Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		xmslocallib.setText("CDP Hold Time", "256");
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Hold Time Should not accept value upper than 255 Sec");
		//Verify that XMS warning message is displaying and parameter not configured
		boolean CDPHTUpperwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPHTUpperwarning){
			xmslocallib.verifyStringequals("CDPHoldTimeHigherWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPHoldTimeHigherWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Set CDP Hold Time with Non Integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Netowrk LLDP Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("CDP");
		xmslocallib.setText("CDP Hold Time", "Batman");
		xmslocallib.clickButton("Apply Config");
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify CDP Hold Time Should accept only Integer value");
		//Verify that XMS warning message is displaying and parameter not configured
		boolean CDPHTnonIntegerwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(CDPHTnonIntegerwarning){
			xmslocallib.verifyStringequals("CDPHoldTimeNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("CDPHoldTimeNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}

			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(CDPIntLowerwarning&&CDPIntUpperwarning&&CDPIntnonIntegerwarning&&CDPHTLowwarning&&CDPHTUpperwarning&&CDPHTnonIntegerwarning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.4_Verify CDP Interval and Hold time field validation");
	}
	//Ref-2.5_Verify CDP Disable State
	@Test(priority=5)
	public void testCase_verifyCDPStateDisableValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.5_Verify CDP Disable State");
		//*****************************************************************
		cdpStateExp="disabled";
		cdpIntervalExp="60";
		cdpHoldTimeExp="180";
		cdpEnableIOD=SNMPOID.getProperty("CDPENableOID");
		cdpIntervalIOD=SNMPOID.getProperty("CDPIntervalOID");
		cdpHoldTimeIOD=SNMPOID.getProperty("CDPHoldTimeOID");
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
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			xmslocallib.setText("CDP Interval", cdpIntervalExp);
			xmslocallib.setText("CDP Hold Time", cdpHoldTimeExp);
			xmslocallib.selectRadioButton("Enable CDP", 2);
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
			//Get CDP State value configured into array 
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			boolean cdpEnable=xmslocallib.isRadioButtonSelected("Enable CDP",2); //CDP Disable
			if(cdpEnable)
				cdpStateGUI="disabled";
			else
				cdpStateGUI="enabled";
			//Verify CDP Disable state configured into array by GUI
			App_Log.debug("Ref-2.5_GUI-Verification:-Verify Network CDP State Disabled value in array");
			boolean guiCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateGUI, cdpStateExp);
			//Verify CDP Disable state configured into array by SNMP
			App_Log.debug("Ref-2.5_SNMP-Verification:-Verify Network CDP State Disabled value in array");
			cdpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpEnableIOD);
			boolean snmpCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateSNMP, "0");
			//Verify CDP Disable state configured into array by CLI
			App_Log.debug("Ref-2.5_CLI-Verification:-Verify Network CDP State Disabled value in array");
			cdpStateCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "State");
			boolean cliCDPStateResult=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateCLI, cdpStateExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(guiCDPStateResult&&snmpCDPStateResult&&cliCDPStateResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.5_Verify CDP Disable State");
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
