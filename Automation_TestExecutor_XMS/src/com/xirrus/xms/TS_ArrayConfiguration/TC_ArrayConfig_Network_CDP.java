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
public class TC_ArrayConfig_Network_CDP extends TestSuiteBase{
	//CDP Paramters
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
	public void testCase_VerifyArrayConfigNetworkCDPParamters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Network CDP Paramters");
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
			//Navigate to Array Services Configuration
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
		if(xmslocallib.isRadioButtonSelected("Enable CDP",1))cdpStateGUI="enabled";else cdpStateGUI="disabled"; 
		//Verify CDP Disable state configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Network CDP State Enabled value in array");
		boolean guiCDPState=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateGUI, cdpStateExp);
		//Verify CDP Disable state configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Network CDP State Enabled value in array");
		cdpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpEnableIOD);
		boolean snmpCDPState=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateSNMP, "1");
		//Verify CDP Disable state configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Network CDP State Enabled value in array");
		cdpStateCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "State");
		boolean cliCDPState=xmslocallib.verifyStringequals("CDPState", this.getClass().getSimpleName(), methodName, cdpStateCLI, cdpStateExp);		
		//Get CDP Interval value configured into array 
		cdpIntervalGUI=xmslocallib.getText("CDP Interval");
		//Verify CDP Interval value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Network CDP Interval value in array");
		boolean guiCDPInterval=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalGUI, cdpIntervalExp);
		//Verify CDP Interval value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Network CDP Interval value in array");
		cdpIntervalSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpIntervalIOD);
		boolean snmpCDPInterval=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalSNMP,cdpIntervalExp );
		//Verify CDP Interval Value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Network CDP Interval value in array");
		cdpIntervalCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Interval").replace("seconds", "").trim();;
		boolean cliCDPInterval=xmslocallib.verifyStringequals("CDPInterval", this.getClass().getSimpleName(), methodName, cdpIntervalCLI, cdpIntervalExp);
		//Get CDP Hold Time value configured into array 
		cdpHoldTimeGUI=xmslocallib.getText("CDP Hold Time");
		//Verify CDP Hold Time value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Network CDP Hold Time value in array");
		boolean guiCDPHoldResult=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeGUI, cdpHoldTimeExp);
		//Verify CDP Interval value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Network CDP Hold Time value in array");
		cdpHoldTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, cdpHoldTimeIOD);
		boolean snmpCDPHold=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeSNMP,cdpHoldTimeExp );
		//Verify CDP Hold Time Value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Network CDP Hold Time Value in array");
		cdpHoldTimeCLI=xmslocallib.getValuebyCLI_CDP(arrayIPadd, "Hold time").replace("seconds", "").trim();
		boolean cliCDPHold=xmslocallib.verifyStringequals("CDPHoldTime", this.getClass().getSimpleName(), methodName, cdpHoldTimeCLI, cdpHoldTimeExp);
	
		App_Log.debug("End-Verify Array Config Network CDP Paramters");		
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
