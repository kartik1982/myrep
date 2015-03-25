package com.xirrus.xms.TS_Array_IAPs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_IAPs_RoamingAssist extends TestSuiteBase{
	private String roamingAssisSNMP;
	private String roamingAssisGUI;
	private String roamingAssisCLI;
	private String backoffPeriodSNMP;
	private String backoffPeriodGUI;
	private String backoffPeriodCLI;
	private String thresholdSNMP;
	private String thresholdGUI;
	private String thresholdCLI;
	private String dataRateSNMP;
	private String dataRateGUI;
	private String dataRateCLI;
	private String devicesSNMP;
	private String devicesGUI;
	private String devicesCLI;
	private String roamingAssisExp;
	private String backoffPeriodExp;
	private String thresholdExp;
	private String dataRateExp;
	private String devicesExp;
	private String roamingAssisOID;
	private String backoffPeriodOID;
	private String thresholdOID;
	private String dataRateOID;
	private String devicesOID;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_IAPs_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-1.1_Verify Enable Roaming Assist Default Value
	@Test(priority=1)
	public void testCase_VerifyEnableRoamingAssistDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify Enable Roaming Assist Default Value");
		//*****************************************************************
		roamingAssisExp="Disabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
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
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Assigned by DHCP value configured into array from GUI
		boolean roamingAssit=xmslocallib.isRadioButtonSelected("Enable Roaming Assist",1); //Roaming assist disable or enable
		if(roamingAssit)
			roamingAssisGUI="Enabled";
		else
			roamingAssisGUI="Disabled";
		//Verify Roaming Assist configured into array by GUI
		App_Log.debug("Ref-1.1_GUI-Verification:-Verify Enable Roaming Assist Default Value disabled");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisGUI, roamingAssisExp);
		//Verify Roaming Assist configured into array by SNMP
		App_Log.debug("Ref-1.1_SNMP-Verification:-Verify Enable Roaming Assist Default Value disabled");
		roamingAssisSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, roamingAssisOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisSNMP, "0");
		//Verify roaming assist configured into Array by CLI
		App_Log.debug("Ref-1.1_CLI-Verification:-Verify Enable Roaming Assist Default Value disabled");
		roamingAssisCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "State");
		cliResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisCLI, roamingAssisExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.1_Verify Enable Roaming Assist Default Value");
	}
	//Ref-1.2_Verify Backoff Period Default Value
	@Test(priority=2)
	public void testCase_VerifyBackoffPeriodDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.2_Verify Backoff Period Default Value");
		//*****************************************************************
		roamingAssisExp="Disabled";
		backoffPeriodExp="120";
		thresholdExp="0";
		dataRateExp="20";
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Backoff period configured into array from GUI
		backoffPeriodGUI=xmslocallib.getText("Backoff Period");
		//Verify Roaming Assist Backoff period configured into array by GUI
		App_Log.debug("Ref-1.2_GUI-Verification:-Verify Roaming Assist Backoff Period Default Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodGUI, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into array by SNMP
		App_Log.debug("Ref-1.2_SNMP-Verification:-Verify Roaming Assist Backoff Period Default Value");
		backoffPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, backoffPeriodOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodSNMP, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into Array by CLI
		App_Log.debug("Ref-1.2_CLI-Verification:-Verify Roaming Assist Backoff Period Default Value");
		backoffPeriodCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Backoff Period").replace("seconds", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodCLI, backoffPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.2_Verify Backoff Period Default Value");
	}
	//Ref-1.3_Verify Roaming Threshold Default Value
	@Test(priority=3)
	public void testCase_VerifyRoamingThresholdDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Roaming Threshold Default Value");
		//*****************************************************************
		roamingAssisExp="Disabled";
		backoffPeriodExp="120";
		thresholdExp="0";
		dataRateExp="20";
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Threshold configured into array from GUI
		thresholdGUI=xmslocallib.getText("Roaming Threshold");
		//Verify Roaming Assist Threshold configured into array by GUI
		App_Log.debug("Ref-1.3_GUI-Verification:-Verify Roaming Assist Threshold Default Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdGUI, thresholdExp);
		//Verify Roaming Assist Threshold configured into array by SNMP
		App_Log.debug("Ref-1.3_SNMP-Verification:-Verify Roaming Assist Threshold Default Value");
		thresholdSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, thresholdOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdSNMP, thresholdExp);
		//Verify Roaming Assist Threshold configured into Array by CLI
		App_Log.debug("Ref-1.3_CLI-Verification:-Verify Roaming Assist Threshold Default Value");
		thresholdCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Roaming Threshold").replace("dB", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdCLI, thresholdExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.3_Verify Roaming Threshold Default Value");
	}
	//Ref-1.4_Verify Minimum Data Rate Default Value
	@Test(priority=4)
	public void testCase_VerifyMinimumDataRateDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.4_Verify Minimum Data Rate Default Value");
		//*****************************************************************
		roamingAssisExp="Disabled";
		backoffPeriodExp="120";
		thresholdExp="0";
		dataRateExp="20";
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Data Rate configured into array from GUI
		dataRateGUI=xmslocallib.getText("Minimum Data Rate");
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("Ref-1.4_GUI-Verification:-Verify Roaming Assist Minimum Data Rate Default Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateGUI, dataRateExp);
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.4_SNMP-Verification:-Verify Roaming Assist Minimum Data Rate Default Value");
		dataRateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dataRateOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateSNMP, dataRateExp);
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.4_CLI-Verification:-Verify Roaming Assist Minimum Data Rate Default Value");
		dataRateCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Minimum Data Rate").replace("Mbps", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateCLI, dataRateExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.4_Verify Minimum Data Rate Default Value");
	}
	//Ref-1.5_Verify Devices Default Value
	@Test(priority=5)
	public void testCase_VerifyDevicesDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify Devices Default Value");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		List<String> devicesExp = new ArrayList<String>();
		devicesExp.add("phone");
		devicesExp.add("player");
		devicesExp.add("tablet");
		List<String> devicesGUI = new ArrayList<String>();
		if(xmslocallib.isCheckboxSelected("Array"))devicesGUI.add("array");
		if(xmslocallib.isCheckboxSelected("Notebook"))devicesGUI.add("notebook");
		if(xmslocallib.isCheckboxSelected("Phone"))devicesGUI.add("phone");
		if(xmslocallib.isCheckboxSelected("Player"))devicesGUI.add("player");
		if(xmslocallib.isCheckboxSelected("Game"))devicesGUI.add("game");
		if(xmslocallib.isCheckboxSelected("Tablet"))devicesGUI.add("tablet");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Devices configured into array from GUI
		guiResult=devicesGUI.containsAll(devicesExp);
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify Roaming Assist Default Device selected");
		if(guiResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify Roaming Assist Default Device selected");
		List<String> deviceSNMP= new ArrayList<String>();
		String outputSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, devicesOID);
		String temp[]=outputSNMP.split(" ");
		for(int i=0;i<temp.length;i++){
			deviceSNMP.add(temp[i].trim());
		}
		snmpResult=deviceSNMP.containsAll(devicesExp);
		if(snmpResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.4_CLI-Verification:-Verify Roaming Assist Default Device selected");
		List<String> devicesCLI= new ArrayList<String>();
		String outputCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Devices");
		String temp1[]=outputCLI.split(" ");
		for(int i=0;i<temp1.length;i++){
			devicesCLI.add(temp1[i].trim());
		}
		cliResult=devicesCLI.containsAll(devicesExp);
		if(cliResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-1.5_Verify Devices Default Value");
	}
	//Ref-1.6_Verify Enable Roaming Assist Enable
	@Test(priority=6)
	public void testCase_VerifyEnableRoamingAssistEnable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.6_Verify Enable Roaming Assist Enable");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
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
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
			xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
			xmslocallib.pause(10);
			xmslocallib.setText("Backoff Period", backoffPeriodExp);
			xmslocallib.setText("Roaming Threshold", thresholdExp);
			xmslocallib.setText("Minimum Data Rate", dataRateExp);
			xmslocallib.selectCheckBox("Array");
			xmslocallib.clickButton("Apply Config");
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
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Devices configured into array from GUI
		boolean roamingAssit=xmslocallib.isRadioButtonSelected("Enable Roaming Assist",1); //Roaming assist disable or enable
		if(roamingAssit)
			roamingAssisGUI="Enabled";
		else
			roamingAssisGUI="Disabled";
		//Verify Roaming Assist configured into array by GUI
		App_Log.debug("Ref-1.6_GUI-Verification:-Verify Enable Roaming Assist Enable");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisGUI, roamingAssisExp);
		//Verify Roaming Assist configured into array by SNMP
		App_Log.debug("Ref-1.6_SNMP-Verification:-Verify Enable Roaming Assist Enable");
		roamingAssisSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, roamingAssisOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisSNMP, "1");
		//Verify roaming assist configured into Array by CLI
		App_Log.debug("Ref-1.6_CLI-Verification:-Verify Enable Roaming Assist Enable");
		roamingAssisCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "State");
		cliResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisCLI, roamingAssisExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.6_Verify Enable Roaming Assist Enable");
	}
	//Ref-1.7_Verify Backoff Period Set Value
	@Test(priority=7)
	public void testCase_VerifyBackoffPeriodSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.7_Verify Backoff Period Set Value");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Backoff period configured into array from GUI
		backoffPeriodGUI=xmslocallib.getText("Backoff Period");
		//Verify Roaming Assist Backoff period configured into array by GUI
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify Roaming Assist Backoff Period set Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodGUI, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into array by SNMP
		App_Log.debug("Ref-1.7_SNMP-Verification:-Verify Roaming Assist Backoff Period set Value");
		backoffPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, backoffPeriodOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodSNMP, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into Array by CLI
		App_Log.debug("Ref-1.7_CLI-Verification:-Verify Roaming Assist Backoff Period set Value");
		backoffPeriodCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Backoff Period").replace("seconds", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodCLI, backoffPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.7_Verify Backoff Period Set Value");
	}
	//Ref-1.8_Verify Roaming Threshold Set Value
	@Test(priority=8)
	public void testCase_VerifyRoamingThresholdSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.8_Verify Roaming Threshold Set Value");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Threshold configured into array from GUI
		thresholdGUI=xmslocallib.getText("Roaming Threshold");
		//Verify Roaming Assist Threshold configured into array by GUI
		App_Log.debug("Ref-1.8_GUI-Verification:-Verify Roaming Assist Threshold Set Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdGUI, thresholdExp);
		//Verify Roaming Assist Threshold configured into array by SNMP
		App_Log.debug("Ref-1.8_SNMP-Verification:-Verify Roaming Assist Threshold Set Value");
		thresholdSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, thresholdOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdSNMP, thresholdExp);
		//Verify Roaming Assist Threshold configured into Array by CLI
		App_Log.debug("Ref-1.8_CLI-Verification:-Verify Roaming Assist Threshold Set Value");
		thresholdCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Roaming Threshold").replace("dB", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdCLI, thresholdExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.8_Verify Roaming Threshold Set Value");
	}
	//Ref-1.9_Verify Minimum Data Rate Set Value
	@Test(priority=9)
	public void testCase_VerifyMinimumDataRateSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.9_Verify Minimum Data Rate Set Value");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Data Rate configured into array from GUI
		dataRateGUI=xmslocallib.getText("Minimum Data Rate");
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("Ref-1.9_GUI-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateGUI, dataRateExp);
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.9_SNMP-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		dataRateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dataRateOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateSNMP, dataRateExp);
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.9_CLI-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		dataRateCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Minimum Data Rate").replace("Mbps", "").trim();
		cliResult=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateCLI, dataRateExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.9_Verify Minimum Data Rate Set Value");
	}
	//Ref-1.10_Verify Devices Set Value
	@Test(priority=10)
	public void testCase_VerifyDevicesSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.10_Verify Devices Set Value");
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		List<String> devicesExp = new ArrayList<String>();
		devicesExp.add("array");
		devicesExp.add("phone");
		devicesExp.add("player");
		devicesExp.add("tablet");
		List<String> devicesGUI = new ArrayList<String>();
		if(xmslocallib.isCheckboxSelected("Array"))devicesGUI.add("array");
		if(xmslocallib.isCheckboxSelected("Notebook"))devicesGUI.add("notebook");
		if(xmslocallib.isCheckboxSelected("Phone"))devicesGUI.add("phone");
		if(xmslocallib.isCheckboxSelected("Player"))devicesGUI.add("player");
		if(xmslocallib.isCheckboxSelected("Game"))devicesGUI.add("game");
		if(xmslocallib.isCheckboxSelected("Tablet"))devicesGUI.add("tablet");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Devices configured into array from GUI
		guiResult=devicesGUI.containsAll(devicesExp);
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify Roaming Assist Default Device selected");
		if(guiResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.10_SNMP-Verification:-Verify Roaming Assist Default Device selected");
		List<String> deviceSNMP= new ArrayList<String>();
		String outputSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, devicesOID);
		String temp[]=outputSNMP.split(" ");
		for(int i=0;i<temp.length;i++){
			deviceSNMP.add(temp[i].trim());
		}
		snmpResult=deviceSNMP.containsAll(devicesExp);
		if(snmpResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.10_CLI-Verification:-Verify Roaming Assist Default Device selected");
		List<String> devicesCLI= new ArrayList<String>();
		String outputCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Devices");
		String temp1[]=outputCLI.split(" ");
		for(int i=0;i<temp1.length;i++){
			devicesCLI.add(temp1[i].trim());
		}
		cliResult=devicesCLI.containsAll(devicesExp);
		if(cliResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-1.10_Verify Devices Set Value");
	}
	//Ref-1.11_Verify Backoff Period Field Validation
	@Test(priority=11)
	public void testCase_VerifyBackoffPeriodFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.11_Verify Backoff Period Field Validation");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		backoffPeriodExp="59"; //less than lower limit 60
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Backoff Period", backoffPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Backoff Period lower Limit 60
		App_Log.debug("Ref-1.11_GUI-Verification:-Verify warning message for Roaming Assist Backoff period lower limit");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Backoff Period must be no less than 60";
		boolean periodlowerLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodlowerLimit){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		backoffPeriodExp="10000"; //Higer than upper limit 9999
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Backoff Period", backoffPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Backoff Period Upper Limit 9999
		App_Log.debug("Ref-1.11_GUI-Verification:-Verify warning message for Roaming Assist Backoff period Upper limit");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Backoff Period must be no greater than 9999";
		boolean periodUpperLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodUpperLimit){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		backoffPeriodExp="TEST"; //Non Integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Backoff Period", backoffPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Backoff Period Non Integer
		App_Log.debug("Ref-1.11_GUI-Verification:-Verify warning message for Roaming Assist Backoff period non-integer");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Backoff Period is required";
		boolean periodInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodInvalid){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		backoffPeriodExp=""; //empty value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Backoff Period", backoffPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Backoff Period empty
		App_Log.debug("Ref-1.11_GUI-Verification:-Verify warning message for Roaming Assist Backoff period Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Backoff Period is required";
		boolean periodEmpty=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(periodEmpty){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(periodlowerLimit&&periodUpperLimit&&periodInvalid&&periodEmpty)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.11_Verify Backoff Period Field Validation");
	}
	//Ref-1.12_Verify Roaming Threshold Field Validation
	@Test(priority=12)
	public void testCase_VerifyRoamingThresholdFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.12_Verify Roaming Threshold Field Validation");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		thresholdExp="-51"; //Roaming Threshold below -50
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Roaming Threshold", thresholdExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Roaming Threshold lower Limit -50
		App_Log.debug("Ref-1.12_GUI-Verification:-Verify warning message for Roaming Assist Threshold lower limit");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Roaming Threshold must be no less than -50";
		boolean thresholdlowerLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(thresholdlowerLimit){
			xmslocallib.verifyStringequals("RoamingThresholdLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingThresholdLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		thresholdExp="51"; //Higer than upper limit 50
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Roaming Threshold", thresholdExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Backoff Period Upper Limit 9999
		App_Log.debug("Ref-1.12_GUI-Verification:-Verify warning message for Roaming Assist Threshold Upper limit");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Roaming Threshold must be no greater than 50";
		boolean thresholdUpperLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(thresholdUpperLimit){
			xmslocallib.verifyStringequals("RoamingThresholdUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingThresholdUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		thresholdExp="TEST"; //Non Integer value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Roaming Threshold", thresholdExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Threshold value Non Integer
		App_Log.debug("Ref-1.12_GUI-Verification:-Verify warning message for Roaming Assist Threshold non-integer");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Roaming Threshold is required";
		boolean thresholdInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(thresholdInvalid){
			xmslocallib.verifyStringequals("RoamingThresholdInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingThresholdInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		thresholdExp=""; //empty value
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Roaming Threshold", thresholdExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Threshold empty
		App_Log.debug("Ref-1.12_GUI-Verification:-Verify warning message for Roaming Assist Threshold Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Roaming Threshold is required";
		boolean thresholdEmpty=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(thresholdEmpty){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(thresholdlowerLimit&&thresholdUpperLimit&&thresholdInvalid&&thresholdEmpty)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.12_Verify Roaming Threshold Field Validation");	
	}
	//Ref-1.13_Verify Minimum Data Rate Field Validation
	@Test(priority=13)
	public void testCase_VerifyMinimumDataRateFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.13_Verify Minimum Data Rate Field Validation");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		dataRateExp="0"; //data Rate less than 1
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Minimum Data Rate", dataRateExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Roaming Data Rate lower Limit 1
		App_Log.debug("Ref-1.13_GUI-Verification:-Verify warning message for Roaming Assist Minimum Data Rate lower limit");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Minimum Data Rate must be no less than 1";
		boolean dataRatelowerLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dataRatelowerLimit){
			xmslocallib.verifyStringequals("RoamingDataRateLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingDataRateLowerLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		dataRateExp="100"; //data Rate higher than 99
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Minimum Data Rate", dataRateExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Roaming Data Rate upper limit 99
		App_Log.debug("Ref-1.13_GUI-Verification:-Verify warning message for Roaming Assist Minimum Data Rate Upper limit");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Minimum Data Rate must be no greater than 99";
		boolean dataRateUpperLimit=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dataRateUpperLimit){
			xmslocallib.verifyStringequals("RoamingDataRateUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingDataRateUpperLimit", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		dataRateExp="What"; //data rate value non integer
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Minimum Data Rate", dataRateExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Data Rate value Non Integer
		App_Log.debug("Ref-1.13_GUI-Verification:-Verify warning message for Roaming Assist Minimum Data Rate non-integer");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Minimum Data Rate is required";
		boolean dataRateInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dataRateInvalid){
			xmslocallib.verifyStringequals("RoamingDataRateInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingDataRateInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		dataRateExp=""; //data rate value empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
		xmslocallib.pause(5);
		xmslocallib.setText("Minimum Data Rate", dataRateExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		///Verify warning message Data Rate empty
		App_Log.debug("Ref-1.13_GUI-Verification:-Verify warning message for Roaming Assist Minimum Data Rate Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Minimum Data Rate is required";
		boolean dataRateEmpty=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dataRateEmpty){
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("RoamingAssistBackoffPeriodEmpty", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(dataRatelowerLimit&&dataRateUpperLimit&&dataRateInvalid&&dataRateEmpty)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);		
		App_Log.debug("End-Ref-1.13_Verify Minimum Data Rate Field Validation");
	}
	//Ref-1.14_Verify Devices Unselect All
	@Test(priority=14)
	public void testCase_VerifyDevicesUnselectAll()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.14_Verify Devices Unselect All");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		devicesExp="";
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
			xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
			xmslocallib.pause(10);
			xmslocallib.unSelectCheckBox("Array");
			xmslocallib.unSelectCheckBox("Notebook");
			xmslocallib.unSelectCheckBox("Phone");
			xmslocallib.unSelectCheckBox("Player");
			xmslocallib.unSelectCheckBox("Game");
			xmslocallib.unSelectCheckBox("Tablet");
			xmslocallib.clickButton("Apply Config");
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
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Devices configured into array from GUI
		int devCount=0;
		for(int i=2;i<8;i++){
			if(xmslocallib.isCheckboxSelected(i))
				devCount++;
		}
		if(devCount==0)devicesGUI="";else devicesGUI="Not Empty";
		//Verify Roaming Assist Devices Selected into array by GUI
		App_Log.debug("Ref-1.14_GUI-Verification:-Verify Devices Unselect All");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, devicesGUI, devicesExp);
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.14_SNMP-Verification:-Verify Devices Unselect All");
		devicesSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, devicesOID);
		if(devicesSNMP.contains("1.3.6.1.4.1.21013.1.2"))
			devicesSNMP="";
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, devicesSNMP, devicesExp);
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.14_CLI-Verification:-Verify Devices Unselect All");
		devicesCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Devices");
		cliResult=xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, devicesCLI, devicesExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.14_Verify Devices Unselect All");
	}
	//Ref-1.15_Verify Devices Select All
	@Test(priority=15)
	public void testCase_VerifyDevicesSelectAll()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.15_Verify Devices Select All");
		//*****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		devicesExp="";
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
			xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
			xmslocallib.pause(10);
			xmslocallib.selectCheckBox("Array");
			xmslocallib.selectCheckBox("Notebook");
			xmslocallib.selectCheckBox("Phone");
			xmslocallib.selectCheckBox("Player");
			xmslocallib.selectCheckBox("Game");
			xmslocallib.selectCheckBox("Tablet");
			xmslocallib.clickButton("Apply Config");
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
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist Data Rate configured into array from GUI
		List<String> devicesExp = new ArrayList<String>();
		devicesExp.add("array");
		devicesExp.add("notebook");
		devicesExp.add("phone");
		devicesExp.add("player");
		devicesExp.add("game");
		devicesExp.add("tablet");
		List<String> devicesGUI = new ArrayList<String>();
		if(xmslocallib.isCheckboxSelected("Array"))devicesGUI.add("array");
		if(xmslocallib.isCheckboxSelected("Notebook"))devicesGUI.add("notebook");
		if(xmslocallib.isCheckboxSelected("Phone"))devicesGUI.add("phone");
		if(xmslocallib.isCheckboxSelected("Player"))devicesGUI.add("player");
		if(xmslocallib.isCheckboxSelected("Game"))devicesGUI.add("game");
		if(xmslocallib.isCheckboxSelected("Tablet"))devicesGUI.add("tablet");
		guiResult=devicesGUI.containsAll(devicesExp);
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("Ref-1.15_GUI-Verification:-Verify Devices Select All");
		if(guiResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("Ref-1.15_SNMP-Verification:-Verify Devices Select All");
		List<String> deviceSNMP= new ArrayList<String>();
		String outputSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, devicesOID);
		String temp[]=outputSNMP.split(" ");
		for(int i=0;i<temp.length;i++){
			deviceSNMP.add(temp[i].trim());
		}
		snmpResult=deviceSNMP.containsAll(devicesExp);
		if(snmpResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("Ref-1.15_CLI-Verification:-Verify Devices Select All");
		List<String> devicesCLI= new ArrayList<String>();
		String outputCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Devices");
		String temp1[]=outputCLI.split(" ");
		for(int i=0;i<temp1.length;i++){
			devicesCLI.add(temp1[i].trim());
		}
		cliResult=devicesCLI.containsAll(devicesExp);
		if(cliResult){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-1.15_Verify Devices Select All");
	}
	//Ref-1.16_Verify Enable Roaming Assist Disable
	@Test(priority=16)
	public void testCase_VerifyEnableRoamingAssistDisable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.16_Verify Enable Roaming Assist Disable");
		//*****************************************************************
		roamingAssisExp="Disabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");
		roamingAssisOID=SNMPOID.getProperty("RoamingAssitEnableOID");
		backoffPeriodOID=SNMPOID.getProperty("RoamingAssitPeriodOID");
		thresholdOID=SNMPOID.getProperty("RoamingAssitThresholdOID");
		dataRateOID=SNMPOID.getProperty("RoamingAssitDataRateOID");
		devicesOID=SNMPOID.getProperty("RoamingAssitDevicesOID");
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
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
			xmslocallib.selectRadioButton("Enable Roaming Assist", 2);
			xmslocallib.pause(10);
			xmslocallib.clickButton("Apply Config");
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
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("IAPs", 2);
		xmslocallib.clickLink("Roaming Assist");
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Roaming Assist enable configured into array from GUI
		boolean roamingAssit=xmslocallib.isRadioButtonSelected("Enable Roaming Assist",1); //Roaming assist disable or enable
		if(roamingAssit)
			roamingAssisGUI="Enabled";
		else
			roamingAssisGUI="Disabled";
		//Verify Roaming Assist configured into array by GUI
		App_Log.debug("Ref-1.16_GUI-Verification:-Verify Enable Roaming Assist Disable");
		guiResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisGUI, roamingAssisExp);
		//Verify Roaming Assist configured into array by SNMP
		App_Log.debug("Ref-1.16_SNMP-Verification:-Verify Enable Roaming Assist Disable");
		roamingAssisSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, roamingAssisOID);
		snmpResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisSNMP, "0");
		//Verify roaming assist configured into Array by CLI
		App_Log.debug("Ref-1.16_CLI-Verification:-Verify Enable Roaming Assist Disable");
		roamingAssisCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "State");
		cliResult=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisCLI, roamingAssisExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.16_Verify Enable Roaming Assist Disable");
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
