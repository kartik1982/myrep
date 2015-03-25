package com.xirrus.xms.TS_Array_Services;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;

import net.sf.saxon.functions.Concat;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Services_Netflow extends TestSuiteBase{
	private String versionSNMP;
	private String versionGUI;
	private String versionCLI;
	private String hostSNMP;
	private String hostGUI;
	private String hostCLI;
	private String portSNMP;
	private String portGUI;
	private String portCLI;
	private String versionExp;
	private String hostExp;
	private String portExp;
	private String versionOID;
	private String portOID;
	private String hostOID;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Services_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-1.1_Verify Netflow Default version
	@Test(priority=1)
	public void testCase_verifyNetflowDefaultVersion()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify Netflow Default version");
		//*****************************************************************
		versionExp="disabled";
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		boolean netflowEnable=xmslocallib.isRadioButtonSelected("Netflow Version",1); //Netflow Disable
		if(netflowEnable)
			versionGUI="disabled";
		else
			versionGUI="NotMatch";
		//Verify Netflow Disable state configured into array by GUI
		App_Log.debug("Ref-1.1_GUI-Verification:-Verify Services Netflow State default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow Disable state configured into array by SNMP
		App_Log.debug("Ref-1.1_SNMP-Verification:-Verify Services Netflow State default value is disable in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "0");
		//Verify Netflow Disable state configured into array by CLI
		App_Log.debug("Ref-1.1_CLI-Verification:-Verify Services Netflow State default value is disable in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, "Off");
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.1_Verify Netflow Default version");
	}
	//Ref-1.2_Verify Netflow Default collector host
	@Test(priority=2)
	public void testCase_verifyNetflowDefaultCollectorHost()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.2_Verify Netflow Default collector host");
		//*****************************************************************
		hostExp="";
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow host value configured into array 
		hostGUI= xmslocallib.getText("Netflow Collector Host");
		//Verify Netflow host configured into array by GUI
		App_Log.debug("Ref-1.2_GUI-Verification:-Verify Services Netflow host default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostGUI, hostExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("Ref-1.2_SNMP-Verification:-Verify Services Netflow host default value is empty in array");
		hostSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostOID);
		if(hostSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			hostSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostSNMP, hostExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("Ref-1.2_CLI-Verification:-Verify Services Netflow host default value is empty in array");
		hostCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Host");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostCLI, hostExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.2_Verify Netflow Default collector host");
	}
	//Ref-1.3_Verify Netflow Default collector port
	@Test(priority=3)
	public void testCase_verifyNetflowDefaultCollectorPort()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify Netflow Default collector port");
		//*****************************************************************
		portExp="2055";
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow port value configured into array 
		portGUI= xmslocallib.getText("Netflow Collector Port");
		//Verify Netflow port configured into array by GUI
		App_Log.debug("Ref-1.3_GUI-Verification:-Verify Services Netflow port default value is 2055 in array");
		boolean guiResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portGUI, portExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("Ref-1.3_SNMP-Verification:-Verify Services Netflow port default value is 2055 in array");
		portSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, portOID);
		boolean snmpResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portSNMP, portExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("Ref-1.3_CLI-Verification:-Verify Services Netflow port default value is 2055 in array");
		portCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Port");
		boolean cliResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portCLI, portExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.3_Verify Netflow Default collector port");
	}
	//Ref-1.4_Verify Netflow version v5 
	@Test(priority=4)
	public void testCase_verifyNetflowVersionV5()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.4_Verify Netflow version v5");
		//*****************************************************************
		versionExp="v5";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 2);
			xmslocallib.setText("Netflow Collector Host", hostExp);
			xmslocallib.setText("Netflow Collector Port", portExp);
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
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		boolean netflowEnable=xmslocallib.isRadioButtonSelected("Netflow Version",2); //Netflow v5
		if(netflowEnable)
			versionGUI="v5";
		else
			versionGUI="NotMatch";
		//Verify Netflow v5 state configured into array by GUI
		App_Log.debug("Ref-1.4_GUI-Verification:-Verify Services Netflow State v5 value is in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow v5 state configured into array by SNMP
		App_Log.debug("Ref-1.4_SNMP-Verification:-Verify Services Netflow State v5 value is in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "1");
		//Verify Netflow v5 state configured into array by CLI
		App_Log.debug("Ref-1.4_CLI-Verification:-Verify Services Netflow State v5 value is in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, versionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-Ref-1.4_Verify Netflow version v5");
	}
	
	
	//Ref-1.5_Verify Netflow collector host edit
	@Test(priority=5)
	public void testCase_verifyNetflowCollectorHostEdit()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify Netflow collector host edit");
		//*****************************************************************
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow host value configured into array 
		hostGUI= xmslocallib.getText("Netflow Collector Host");
		//Verify Netflow host configured into array by GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify Services Netflow host value in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostGUI, hostExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify Services Netflow host value in array");
		hostSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostSNMP, hostExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("Ref-1.5_CLI-Verification:-Verify Services Netflow host value in array");
		hostCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Host");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostCLI, hostExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.5_Verify Netflow collector host edit");
	}
	//Ref-1.6_Verify Netflow collector port edit  
	@Test(priority=6)
	public void testCase_verifyNetflowCollectorPortEdit()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.6_Verify Netflow collector port edit");
		//*****************************************************************
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow port value configured into array 
		portGUI= xmslocallib.getText("Netflow Collector Port");
		//Verify Netflow port configured into array by GUI
		App_Log.debug("Ref-1.6_GUI-Verification:-Verify Services Netflow port value in array");
		boolean guiResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portGUI, portExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("Ref-1.6_SNMP-Verification:-Verify Services Netflow port value in array");
		portSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, portOID);
		boolean snmpResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portSNMP, portExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("Ref-1.6_CLI-Verification:-Verify Services Netflow port value in array");
		portCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Port");
		boolean cliResult=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portCLI, portExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.6_Verify Netflow collector port edit");
	}
		
	//Ref-1.7_Verify Netflow version v9
	@Test(priority=7)
	public void testCase_verifyNetflowVersionV6()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.7_Verify Netflow version v9");
		//*****************************************************************
		versionExp="v9";
		hostExp="xirrus.com";
		portExp="5005";
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 3);
			xmslocallib.setText("Netflow Collector Host", hostExp);
			xmslocallib.setText("Netflow Collector Port", portExp);
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
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		boolean netflowEnable=xmslocallib.isRadioButtonSelected("Netflow Version",3); //Netflow v5
		if(netflowEnable)
			versionGUI="v9";
		else
			versionGUI="NotMatch";
		//Verify Netflow v9 state configured into array by GUI
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify Services Netflow State v9 value is in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow v9 state configured into array by SNMP
		App_Log.debug("Ref-1.7_SNMP-Verification:-Verify Services Netflow State v9 value is in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "2");
		//Verify Netflow v9 state configured into array by CLI
		App_Log.debug("Ref-1.7_CLI-Verification:-Verify Services Netflow State v9 value is in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, versionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.7_Verify Netflow version v9");
	}
	//Ref-1.8_Verify Netflow version IPFIX
	@Test(priority=8)
	public void testCase_verifyNetflowVersionIPFIX()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.8_Verify Netflow version IPFIX");
		//*****************************************************************
		versionExp="IPFIX";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 4);
			xmslocallib.setText("Netflow Collector Host", hostExp);
			xmslocallib.setText("Netflow Collector Port", portExp);
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
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		boolean netflowEnable=xmslocallib.isRadioButtonSelected("Netflow Version",4); //Netflow v5
		if(netflowEnable)
			versionGUI="IPFIX";
		else
			versionGUI="NotMatch";
		//Verify Netflow IPFIX state configured into array by GUI
		App_Log.debug("Ref-1.8_GUI-Verification:-Verify Services Netflow State IPFIX value is in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow IPFIX state configured into array by SNMP
		App_Log.debug("Ref-1.8_SNMP-Verification:-Verify Services Netflow State IPFIX value is in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "3");
		//Verify Netflow IPFIX state configured into array by CLI
		App_Log.debug("Ref-1.8_CLI-Verification:-Verify Services Netflow State IPFIX value is in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, versionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.8_Verify Netflow version IPFIX");
	}
	
	//Ref-1.9_Verify Netflow collector host field validation  
	@Test(priority=9)
	public void testCase_verifyNetflowCollectorHostFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.9_Verify Netflow collector host field validation");
		//*****************************************************************
		versionExp="IPFIX";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 3);
			xmslocallib.setText("Netflow Collector Host", "");
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		
		//Verify Netlow Host Field Validation
		App_Log.debug("Ref-1.9_GUI-Verification:-Verify Netflow collector host not accept Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Netflow Collector Host is required";
		boolean hostEmptywarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(hostEmptywarning){
			xmslocallib.verifyStringequals("NetflowHostEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowHostEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		App_Log.debug("Ref-1.9_GUI-Verification:-Verify Netflow collector host Invalid Domain Name");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		xmslocallib.selectRadioButton("Netflow Version", 3);
		xmslocallib.setText("Netflow Collector Host", "Kartik@123");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Collector Host must be a valid IP address or Hostname";
		boolean hostInvalidNameWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(hostInvalidNameWarning){
			xmslocallib.verifyStringequals("NetflowHostInvalidNameWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowHostInvalidNameWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		App_Log.debug("Ref-1.9_GUI-Verification:-Verify Netflow collector host Invalid IP");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		xmslocallib.selectRadioButton("Netflow Version", 3);
		xmslocallib.setText("Netflow Collector Host", "10.100.256.900");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Collector Host must be a valid IP address or Hostname";
		boolean hostInvalidIPWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(hostInvalidIPWarning){
			xmslocallib.verifyStringequals("NetflowHostInvalidIPWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowHostInvalidIPWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(hostEmptywarning&&hostInvalidNameWarning&&hostInvalidIPWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.9_Verify Netflow collector host field validation");
	}
	//Ref-1.10_Verify Netflow collector port field validation  
	@Test(priority=10)
	public void testCase_verifyNetflowCollectorPortFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.10_Verify Netflow collector port field validation");
		//*****************************************************************
		versionExp="IPFIX";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 3);
			xmslocallib.setText("Netflow Collector Port", "");
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
			//message:-"Done configuring Array" PR-21595
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		
		//Verify Netlow Host Field Validation
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify Netflow collector port not accept Empty value");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Netflow Collector Port is required";
		boolean portEmptywarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(portEmptywarning){
			xmslocallib.verifyStringequals("NetflowPortEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowPortEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify Netflow collector Port Invalid Name");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		xmslocallib.selectRadioButton("Netflow Version", 3);
		xmslocallib.setText("Netflow Collector Port", "Kartik");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Netflow Collector Port is required";
		boolean portInvalidNameWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(portInvalidNameWarning){
			xmslocallib.verifyStringequals("NetflowPortInvalidNameWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowPortInvalidNameWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify Netflow collector Port lower limit port number");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		xmslocallib.selectRadioButton("Netflow Version", 3);
		xmslocallib.setText("Netflow Collector Port", "-1");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Collector Port must be between 0 and 65535";
		boolean portlowerlimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(portlowerlimitWarning){
			xmslocallib.verifyStringequals("NetflowPortLowLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowPortLowLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify Netflow collector Port Upper limit port number");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		xmslocallib.selectRadioButton("Netflow Version", 3);
		xmslocallib.setText("Netflow Collector Port", "65536");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Collector Port must be between 0 and 65535";
		boolean portUpperlimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(portUpperlimitWarning){
			xmslocallib.verifyStringequals("NetflowPortUpperLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("NetflowPortUpperLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(portEmptywarning&&portInvalidNameWarning&&portlowerlimitWarning&&portUpperlimitWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);		
		
		App_Log.debug("End-Ref-1.10_Verify Netflow collector port field validation");
	}
	//Ref-1.11_Verify Netflow disable  
	@Test(priority=11)
	public void testCase_verifyNetflowDisable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.11_Verify Netflow disable");
		//*****************************************************************
		versionExp="Off";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			xmslocallib.selectRadioButton("Netflow Version", 1);
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
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("Netflow");
		boolean netflowEnable=xmslocallib.isRadioButtonSelected("Netflow Version",1); //Netflow v5
		if(netflowEnable)
			versionGUI="Off";
		else
			versionGUI="NotMatch";
		//Verify Netflow Disabled configured into array by GUI
		App_Log.debug("Ref-1.11_GUI-Verification:-Verify Services Netflow State disable value is in array");
		boolean guiResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow v5 state configured into array by SNMP
		App_Log.debug("Ref-1.11_SNMP-Verification:-Verify Services Netflow State disable value is in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "0");
		//Verify Netflow v5 state configured into array by CLI
		App_Log.debug("Ref-1.11_CLI-Verification:-Verify Services Netflow State disable value is in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliResult=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, versionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.11_Verify Netflow disable");
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
