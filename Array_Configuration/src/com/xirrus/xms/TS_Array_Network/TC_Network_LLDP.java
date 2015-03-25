package com.xirrus.xms.TS_Array_Network;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Network_LLDP extends TestSuiteBase{
	private String lldpStateSNMP;
	private String lldpStateGUI;
	private String lldpStateCLI;
	private String lldpStateExp;
	private String lldpStateNew;
	private String lldpEnableIOD;
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
	
	@Test(priority=1)
	public void testCase_verifyDefaultLLDPState()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify default State of LLDP in Array");
		//*****************************************************************
		lldpStateExp="disabled";
		lldpStateNew="enabled";
		lldpEnableIOD=SNMPOID.getProperty("LLDPEnableIOD");
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
			xmslocallib.clickLink("LLDP");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Network LLDP configured into Array by SNMP, CLI and GUI
		//Get LLDP value configured into array 
		boolean lldpEnable=xmslocallib.isRadioButtonSelected("Enable LLDP",2);
		if(lldpEnable)
			lldpStateGUI="disabled";
		else
			lldpStateGUI="enabled";
		//Verify Admin Contact name configured into array by GUI
		App_Log.debug("Ref-1.1_GUI-Verification:-Verify Network LLDP default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateGUI, lldpStateExp);
		//Verify Admin Contact name get Configured into array by SNMP
		App_Log.debug("Ref-1.1_SNMP-Verification:-Verify Network LLDP default value is disable in array");
		lldpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, lldpEnableIOD);
		boolean snmpResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateSNMP, "0");
		//Verify Admin Contact name get pushed into array by CLI
		App_Log.debug("Ref-1.1_CLI-Verification:-Verify Network LLDP default value is disable in array");
		lldpStateCLI=xmslocallib.getValuebyCLI_LLDP(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateCLI, lldpStateExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.1_Verify default State of LLDP in Array");
	}
	@Test(priority=2)
	public void testCase_verifyEnableLLDPState()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.2_Verify LLDP Enable State in Array");
		//*****************************************************************
		lldpStateExp="enabled";
		lldpStateNew="disabled";
		lldpEnableIOD=SNMPOID.getProperty("LLDPEnableIOD");
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
			xmslocallib.clickLink("LLDP");
			xmslocallib.selectRadioButton("Enable LLDP", 1);
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
			//Verification on Network LLDP configured into Array by SNMP, CLI and GUI
			//Get LLDP value configured into array 
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("LLDP");
			boolean lldpEnable=xmslocallib.isRadioButtonSelected("Enable LLDP",1);
			if(lldpEnable)
				lldpStateGUI="enabled";
			else
				lldpStateGUI="disabled";
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-1.2_GUI-Verification:-Verify Network LLDP value is enabled in array");
			boolean guiResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateGUI, lldpStateExp);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("Ref-1.2_SNMP-Verification:-Verify Network LLDP value is enabled in array");
			lldpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, lldpEnableIOD);
			boolean snmpResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateSNMP, "1");
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("Ref-1.2_CLI-Verification:-Verify Network LLDP value is enabled in array");
			lldpStateCLI=xmslocallib.getValuebyCLI_LLDP(arrayIPadd, "State");
			boolean cliResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateCLI, lldpStateExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.2_Verify LLDP Enable State in Array");
	}
	
	@Test(priority=3)
	public void testCase_verifyDisableLLDPState()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify LLDP Disable State in Array");
		//*****************************************************************
		lldpStateExp="disabled";
		lldpStateNew="enabled";
		lldpEnableIOD=SNMPOID.getProperty("LLDPEnableIOD");
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
			xmslocallib.clickLink("LLDP");
			xmslocallib.selectRadioButton("Enable LLDP", 2);
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
			//Verification on Network LLDP configured into Array by SNMP, CLI and GUI
			//Get LLDP value configured into array 
			//Navigate to Array Netowrk LLDP Settings Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("LLDP");
			boolean lldpEnable=xmslocallib.isRadioButtonSelected("Enable LLDP",2);
			if(lldpEnable)
				lldpStateGUI="disabled";
			else
				lldpStateGUI="enabled";
			//Verify Admin Contact name configured into array by GUI
			App_Log.debug("Ref-1.3_GUI-Verification:-Verify Network LLDP value is disable in array");
			boolean guiResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateGUI, lldpStateExp);
			//Verify Admin Contact name get Configured into array by SNMP
			App_Log.debug("Ref-1.3_SNMP-Verification:-Verify Network LLDP value is disable in array");
			lldpStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, lldpEnableIOD);
			boolean snmpResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateSNMP, "0");
			//Verify Admin Contact name get pushed into array by CLI
			App_Log.debug("Ref-1.3_CLI-Verification:-Verify Network LLDP value is disable in array");
			lldpStateCLI=xmslocallib.getValuebyCLI_LLDP(arrayIPadd, "State");
			boolean cliResult=xmslocallib.verifyStringequals("LLDPState", this.getClass().getSimpleName(), methodName, lldpStateCLI, lldpStateExp);
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(snmpResult&&cliResult&&guiResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.3_Verify LLDP Disable State in Array");
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
