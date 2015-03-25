package com.xirrus.xms.TS_ArrayConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import com.xirrus.xms.globallib.Test_Utility;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xirrus.xms.globallib.*;

@Test
public class TC_ArrayConfig_IAPs_RoamingAssist extends TestSuiteBase{
	//parameters for roaming assist
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
	public void testCase_VerifyNewVLANDefaultValues() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config IAPs Roaming Assist Parameters");
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
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		
		//get Roaming Assist Devices configured into array from GUI
		if(xmslocallib.isRadioButtonSelected("Enable Roaming Assist",1))roamingAssisGUI="Enabled";else roamingAssisGUI="Disabled"; //Roaming assist disable or enable
		//Verify Roaming Assist configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Enable Roaming Assist Enable");
		boolean guienable=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisGUI, roamingAssisExp);
		//Verify Roaming Assist configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Enable Roaming Assist Enable");
		roamingAssisSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, roamingAssisOID);
		boolean snmpenable=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisSNMP, "1");
		//Verify roaming assist configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Enable Roaming Assist Enable");
		roamingAssisCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "State");
		boolean clienable=xmslocallib.verifyStringequals("RoamingAssitEnableState", this.getClass().getSimpleName(), methodName, roamingAssisCLI, roamingAssisExp);
		//get Roaming Assist Backoff period configured into array from GUI
		backoffPeriodGUI=xmslocallib.getText("Backoff Period");
		//Verify Roaming Assist Backoff period configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Roaming Assist Backoff Period set Value");
		boolean guibackoff=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodGUI, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Roaming Assist Backoff Period set Value");
		backoffPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, backoffPeriodOID);
		boolean snmpbackoff=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodSNMP, backoffPeriodExp);
		//Verify Roaming Assist Backoff period configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Roaming Assist Backoff Period set Value");
		backoffPeriodCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Backoff Period").replace("seconds", "").trim();
		boolean clibackoff=xmslocallib.verifyStringequals("RoamingAssitBackoffPeriod", this.getClass().getSimpleName(), methodName, backoffPeriodCLI, backoffPeriodExp);
		//get Roaming Assist Threshold configured into array from GUI
		thresholdGUI=xmslocallib.getText("Roaming Threshold");
		//Verify Roaming Assist Threshold configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Roaming Assist Threshold Set Value");
		boolean guithreshold=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdGUI, thresholdExp);
		//Verify Roaming Assist Threshold configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Roaming Assist Threshold Set Value");
		thresholdSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, thresholdOID);
		boolean snmpthresold=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdSNMP, thresholdExp);
		//Verify Roaming Assist Threshold configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Roaming Assist Threshold Set Value");
		thresholdCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Roaming Threshold").replace("dB", "").trim();
		boolean clithreshold=xmslocallib.verifyStringequals("RoamingAssitThreshold", this.getClass().getSimpleName(), methodName, thresholdCLI, thresholdExp);
		//get Roaming Assist Data Rate configured into array from GUI
		dataRateGUI=xmslocallib.getText("Minimum Data Rate");
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		boolean guidatarate=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateGUI, dataRateExp);
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		dataRateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dataRateOID);
		boolean snmpdatarate=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateSNMP, dataRateExp);
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Roaming Assist Minimum Data Rate Set Value");
		dataRateCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Minimum Data Rate").replace("Mbps", "").trim();
		boolean clidatarate=xmslocallib.verifyStringequals("RoamingAssitDataRate", this.getClass().getSimpleName(), methodName, dataRateCLI, dataRateExp);		
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
		boolean guidevices=devicesGUI.containsAll(devicesExp);
		//Verify Roaming Assist Data Rate configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Devices Select All");
		if(guidevices){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Devices Select All");
		List<String> deviceSNMP= new ArrayList<String>();
		String outputSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, devicesOID);
		String temp[]=outputSNMP.split(" ");
		for(int i=0;i<temp.length;i++){
			deviceSNMP.add(temp[i].trim());
		}
		boolean snmpdevices=deviceSNMP.containsAll(devicesExp);
		if(snmpdevices){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		//Verify Roaming Assist Data Rate configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Devices Select All");
		List<String> devicesCLI= new ArrayList<String>();
		String outputCLI=xmslocallib.getValuebyCLI_RoamingAssit(arrayIPadd, "Devices");
		String temp1[]=outputCLI.split(" ");
		for(int i=0;i<temp1.length;i++){
			devicesCLI.add(temp1[i].trim());
		}
		boolean clidevices=devicesCLI.containsAll(devicesExp);
		if(clidevices){
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device listed");
		}else{
			xmslocallib.verifyStringequals("RoamingAssitDevices", this.getClass().getSimpleName(), methodName, "Expected device listed", "Expected device NOT listed");
		}
		
		
		
		App_Log.debug("End-Verify Array Config IAPs Roaming Assist Parameters");		
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
