package com.xirrus.xms.TS_Array_Services;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Services_WiFiTag extends TestSuiteBase{
	private String wifiEnableSNMP;
	private String wifiEnableGUI;
	private String wifiEnableCLI;
	private String wifiUDPPortSNMP;
	private String wifiUDPPortGUI;
	private String wifiUDPPortCLI;
	private String wifiTagChannelSNMP;
	private String wifiTagChannelGUI;
	private String wifiTagChannelCLI;
	private String ekahauSrvrSNMP;
	private String ekahauSrvrGUI;
	private String ekahauSrvrCLI;
	private String wifiEnableExp;
	private String wifiUDPPortExp;
	private String wifiTagChannelExp;
	private String ekahauSrvrExp;
	private String wifiEnableOID;
	private String wifiUDPPortOID;
	private String wifiTagChannelOID;
	private String ekahauSrvrOID;
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
	//Ref-2.1_Verify Enable WiFi Tag Support Default status
	@Test(priority=1)
	public void testCase_verifyWiFiTagDefaultState()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify Enable WiFi Tag Support Default status");
		//*****************************************************************
		wifiEnableExp="disabled";
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Get WiFi Tag Version value configured into array 
		boolean locationEnable=xmslocallib.isRadioButtonSelected("Enable WiFi Tag Support",2); //WiFi Tag Disable
		if(locationEnable)
			wifiEnableGUI="disabled";
		else
			wifiEnableGUI="NotMatch";
		//Verify Wifi Tag enable status configured into array by GUI
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify Services WiFi Tag status default value is disable in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableGUI, wifiEnableExp);
		//Verify WiFi Tag enable status configured into array by SNMP
		App_Log.debug("Ref-2.1_SNMP-Verification:-Verify Services WiFi Tag status default value is disable in array");
		wifiEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableSNMP, "0");
		//Verify WiFi Tag enable status configured into array by CLI
		App_Log.debug("Ref-2.1_CLI-Verification:-Verify Services WiFi Tag status default value is disable in array");
		wifiEnableCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableCLI, wifiEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.1_Verify Enable WiFi Tag Support Default status");
	}
	//Ref-2.2_Verify WiFi Tag UDP Port Default value
	@Test(priority=2)
	public void testCase_verifyWiFiTagDefaultUDPPortValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.2_Verify WiFi Tag UDP Port Default value");
		//*****************************************************************
		wifiUDPPortExp="1144";
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on WiFi Tag UDP Port paramters configured into Array by SNMP, CLI and GUI
		//Get Location URL value configured into array 
		wifiUDPPortGUI= xmslocallib.getText("WiFi Tag UDP Port");
		//Verify WiFi Tag UDP Port configured into array by GUI
		App_Log.debug("Ref-2.2_GUI-Verification:-Verify Services WiFi Tag UDP Port default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortGUI, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port configured into array by SNMP
		App_Log.debug("Ref-2.2_SNMP-Verification:-Verify Services WiFi Tag UDP Port default value in array");
		wifiUDPPortSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiUDPPortOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortSNMP, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port Configured into array by CLI
		App_Log.debug("Ref-2.2_CLI-Verification:-Verify Services WiFi Tag UDP Port default value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "UDP Port");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiUDPPortExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.2_Verify WiFi Tag UDP Port Default value");
	}
	//Ref-2.3_Verify WiFi Tag Channel Default value
	@Test(priority=3)
	public void testCase_verifyWiFiTagChannelDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify WiFi Tag Channel Default value");
		//*****************************************************************
		wifiTagChannelExp="none";
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on WiFi Tag Channel paramters configured into Array by SNMP, CLI and GUI
		//Get Location URL value configured into array 
		wifiTagChannelGUI= xmslocallib.getDropDownText("WiFi Tag Channel").toLowerCase();
		//Verify WiFi Tag Channel configured into array by GUI
		App_Log.debug("Ref-2.3_GUI-Verification:-Verify Services WiFi Tag Channel default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelGUI, wifiTagChannelExp);
		//Verify WiFi Tag Channel configured into array by SNMP
		App_Log.debug("Ref-2.3_SNMP-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiTagChannelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiTagChannelOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelSNMP, "0");
		//Verify WiFi Tag Channel Configured into array by CLI
		App_Log.debug("Ref-2.3_CLI-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Tag Channel-bg");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiTagChannelExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.3_Verify WiFi Tag Channel Default value");
	}
	//Ref-2.4_Verify Ekahau Server Default value 
	@Test(priority=4)
	public void testCase_verifyWiFiTagEkahauSrvrDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.4_Verify Ekahau Server Default value");
		//*****************************************************************
		ekahauSrvrExp="";
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Ekahau Server parameters configured into Array by SNMP, CLI and GUI
		//Get Ekahau Server value configured into array 
		ekahauSrvrGUI= xmslocallib.getText("Ekahau Server");
		//Verify Ekahau Server configured into array by GUI
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify Services Ekahau Server default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrGUI, ekahauSrvrExp);
		//Verify Ekahau Server configured into array by SNMP
		App_Log.debug("Ref-2.4_SNMP-Verification:-Verify Services Ekahau Server default value in array");
		ekahauSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ekahauSrvrOID);
		if(ekahauSrvrSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			ekahauSrvrSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrSNMP, ekahauSrvrExp);
		//Verify Ekahau Server Configured into array by CLI
		App_Log.debug("Ref-2.4_CLI-Verification:-Verify Services Ekahau Server default value in array");
		ekahauSrvrCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Ekahau Server");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrCLI, ekahauSrvrExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.4_Verify Ekahau Server Default value");
	}
	//Ref-2.5_Verify Enable WiFi Tag Support status
	@Test(priority=5)
	public void testCase_verifyWiFiTagEnableState()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.5_Verify Enable WiFi Tag Support status");
		//*****************************************************************
		wifiEnableExp="enabled";
		ekahauSrvrExp=CONFIG.getProperty("EkahauServer");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");
			xmslocallib.selectRadioButton("Enable WiFi Tag Support", 1);
			xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
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
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		//Get WiFi Tag Version value configured into array 
		boolean wifiEnable=xmslocallib.isRadioButtonSelected("Enable WiFi Tag Support",1); //WiFi Tag Enabled
		if(wifiEnable)
			wifiEnableGUI="enabled";
		else
			wifiEnableGUI="NotMatch";
		//Verify Wifi Tag enable status configured into array by GUI
		App_Log.debug("Ref-2.5_GUI-Verification:-Verify Services WiFi Tag status value is enabled in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableGUI, wifiEnableExp);
		//Verify WiFi Tag enable status configured into array by SNMP
		App_Log.debug("Ref-2.5_SNMP-Verification:-Verify Services WiFi Tag status value is enabled in array");
		wifiEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableSNMP, "1");
		//Verify WiFi Tag enable status configured into array by CLI
		App_Log.debug("Ref-2.5_CLI-Verification:-Verify Services WiFi Tag status value is enabled in array");
		wifiEnableCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableCLI, wifiEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.5_Verify Enable WiFi Tag Support status");
	}
	//Ref-2.6_Verify  WiFi Tag UDP Port edit
	@Test(priority=6)
	public void testCase_verifyWiFiTagUDPPortValueEdit()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.6_Verify  WiFi Tag UDP Port edit");
		//*****************************************************************
		wifiUDPPortExp=CONFIG.getProperty("WiFiTagUDPPort");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");
			xmslocallib.selectRadioButton("Enable WiFi Tag Support", 1);
			xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
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
		//Verification on WiFi Tag UDP Port paramters configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		//Get Location URL value configured into array 
		wifiUDPPortGUI= xmslocallib.getText("WiFi Tag UDP Port");
		//Verify WiFi Tag UDP Port configured into array by GUI
		App_Log.debug("Ref-2.6_GUI-Verification:-Verify Services WiFi Tag UDP Port value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortGUI, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port configured into array by SNMP
		App_Log.debug("Ref-2.6_SNMP-Verification:-Verify Services WiFi Tag UDP Port value in array");
		wifiUDPPortSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiUDPPortOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortSNMP, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port Configured into array by CLI
		App_Log.debug("Ref-2.6_CLI-Verification:-Verify Services WiFi Tag UDP Port value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "UDP Port");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiUDPPortExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.6_Verify  WiFi Tag UDP Port edit");
	}
	//Ref-2.7_Verify WiFi Tag Channel edit
	@Test(priority=7)
	public void testCase_verifyWiFiTagChannelValueEdit()throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-2.7_Verify WiFi Tag Channel edit");
		//*****************************************************************
		wifiTagChannelExp=CONFIG.getProperty("WiFiTagChannel");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");
			xmslocallib.selectRadioButton("Enable WiFi Tag Support", 1);
			xmslocallib.selectDropDown("WiFi Tag Channel", wifiTagChannelExp);
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
		//Verification on WiFi Tag Channel paramters configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		//Get Location URL value configured into array 
		wifiTagChannelGUI= xmslocallib.getDropDownText("WiFi Tag Channel").toLowerCase();
		//Verify WiFi Tag Channel configured into array by GUI
		App_Log.debug("Ref-2.7_GUI-Verification:-Verify Services WiFi Tag Channel default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelGUI, wifiTagChannelExp);
		//Verify WiFi Tag Channel configured into array by SNMP
		App_Log.debug("Ref-2.7_SNMP-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiTagChannelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiTagChannelOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelSNMP, wifiTagChannelExp);
		//Verify WiFi Tag Channel Configured into array by CLI
		App_Log.debug("Ref-2.7_CLI-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Tag Channel-bg");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiTagChannelExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.7_Verify WiFi Tag Channel edit");
	}
	//Ref-2.8_Verify Ekahau Server edit   
	@Test(priority=8)
	public void testCase_verifyWiFiTagEkahauSrvrEdit()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.8_Verify Ekahau Server edit");
		//*****************************************************************
		ekahauSrvrExp=CONFIG.getProperty("EkahauServer");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");
			xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
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
		//Verification on Ekahau Server parameters configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		//Get Ekahau Server value configured into array 
		ekahauSrvrGUI= xmslocallib.getText("Ekahau Server");
		//Verify Ekahau Server configured into array by GUI
		App_Log.debug("Ref-2.8_GUI-Verification:-Verify Services Ekahau Server value in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrGUI, ekahauSrvrExp);
		//Verify Ekahau Server configured into array by SNMP
		App_Log.debug("Ref-2.8_SNMP-Verification:-Verify Services Ekahau Server value in array");
		ekahauSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ekahauSrvrOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrSNMP, ekahauSrvrExp);
		//Verify Ekahau Server Configured into array by CLI
		App_Log.debug("Ref-2.8_CLI-Verification:-Verify Services Ekahau Server value in array");
		ekahauSrvrCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Ekahau Server");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrCLI, ekahauSrvrExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.8_Verify Ekahau Server edit");
	}
	//Ref-2.9_Verify  WiFi Tag UDP Port field validate  
	@Test(priority=9)
	public void testCase_verifyWiFiTagUDPPortFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.9_Verify  WiFi Tag UDP Port field validate");
		//*****************************************************************
		wifiUDPPortExp=CONFIG.getProperty("WiFiTagUDPPort");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//port value set to 1024
		wifiUDPPortExp="1024";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message	
		App_Log.debug("Ref-2.9_GUI-Verification:-Verify warning message for UDP port lower boundary");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="WiFi Tag UDP Port must be between 1025 and 65535";
		boolean udpPortLowerLimitwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(udpPortLowerLimitwarning){
			xmslocallib.verifyStringequals("WiFiTagUDPPortLowerLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("WiFiTagUDPPortLowerLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//port value set to 65536
		wifiUDPPortExp="65536";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-2.9_GUI-Verification:-Verify warning message for UDP port Upper boundary");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="WiFi Tag UDP Port must be between 1025 and 65535";
		boolean udpPortUpperLimitwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(udpPortUpperLimitwarning){
			xmslocallib.verifyStringequals("WiFiTagUDPPortUpperLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("WiFiTagUDPPortUpperLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//port value set to empty
		wifiUDPPortExp="";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-2.9_GUI-Verification:-Verify warning message for UDP port Empty");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="WiFi Tag UDP Port field cannot be blank";
		boolean udpPortEmptywarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(udpPortEmptywarning){
			xmslocallib.verifyStringequals("WiFiTagUDPPortEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("WiFiTagUDPPortEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//port value set to "Kartik"
		wifiUDPPortExp="kartik";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-2.9_GUI-Verification:-Verify warning message for UDP port Non Integer");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="WiFi Tag UDP Port is not a valid Integer";
		boolean udpPortNonIntwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(udpPortNonIntwarning){
			xmslocallib.verifyStringequals("WiFiTagUDPPortNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("WiFiTagUDPPortNonIntegerWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(udpPortLowerLimitwarning&&udpPortUpperLimitwarning&&udpPortEmptywarning&&udpPortNonIntwarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.9_Verify  WiFi Tag UDP Port field validate");
	}
	//Ref-2.10_Verify Ekahau Server field validate  
	@Test(priority=10)
	public void testCase_verifyWiFiTagEkahauSrvrFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.10_Verify Ekahau Server field validate");
		//*****************************************************************
		ekahauSrvrExp=CONFIG.getProperty("EkahauServer");
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//256 characters for server hostname
		ekahauSrvrExp="iamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestx";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message		
		App_Log.debug("Ref-2.10_GUI-Verification:-Verify warning message for Ekahau Server hostname 0-255 charaters limit");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="Ekahau Server is longer than the maximum of 255 characters.";
		boolean EkahauSrvrLimitwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(EkahauSrvrLimitwarning){
			xmslocallib.verifyStringequals("EkahauSrvrLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("EkahauSrvrLimitwarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		ekahauSrvrExp="Today Is funday";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message		
		App_Log.debug("Ref-2.10_GUI-Verification:-Verify warning message for Ekahau Server for Invalid Host name");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="wifiTagEkahauServer must be a valid IP address or hostname";
		boolean EkahauSrvrInvalidHostNamewarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(EkahauSrvrInvalidHostNamewarning){
			xmslocallib.verifyStringequals("EkahauSrvrInvalidHostNamewarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("EkahauSrvrInvalidHostNamewarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		ekahauSrvrExp="255.255.255.255";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message	
		App_Log.debug("Ref-2.10_GUI-Verification:-Verify warning message for Ekahau Server for Invalid IP address");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="wifiTagEkahauServer must be a valid IP address or hostname";
		boolean EkahauSrvrInvalidHostIPwarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(EkahauSrvrInvalidHostIPwarning){
			xmslocallib.verifyStringequals("EkahauSrvrInvalidHostNamewarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("EkahauSrvrInvalidHostNamewarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(EkahauSrvrLimitwarning&&EkahauSrvrInvalidHostNamewarning&&EkahauSrvrInvalidHostIPwarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.10_Verify Ekahau Server field validate");
	}
	//Ref-2.11_Verify Disable WiFi Tag Support   
	@Test(priority=11)
	public void testCase_verifyWiFiTagSupportDisable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.11_Verify Disable WiFi Tag Support");
		//*****************************************************************
		wifiEnableExp="disabled";
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
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
			xmslocallib.clickLink("WiFi Tag");
			xmslocallib.selectRadioButton("Enable WiFi Tag Support", 2);
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
		//Verification on Location reporting enable status configured into Array by SNMP, CLI and GUI
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("WiFi Tag");
		//Get WiFi Tag Version value configured into array 
		boolean wifiEnable=xmslocallib.isRadioButtonSelected("Enable WiFi Tag Support",2); //WiFi Tag Enabled
		if(wifiEnable)
			wifiEnableGUI="disabled";
		else
			wifiEnableGUI="NotMatch";
		//Verify Wifi Tag enable status configured into array by GUI
		App_Log.debug("Ref-2.11_GUI-Verification:-Verify Services WiFi Tag status value is disabled in array");
		boolean guiResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableGUI, wifiEnableExp);
		//Verify WiFi Tag enable status configured into array by SNMP
		App_Log.debug("Ref-2.11_SNMP-Verification:-Verify Services WiFi Tag status value is disabled in array");
		wifiEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiEnableOID);
		boolean snmpResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableSNMP, "0");
		//Verify WiFi Tag enable status configured into array by CLI
		App_Log.debug("Ref-2.11_CLI-Verification:-Verify Services WiFi Tag status value is disabled in array");
		wifiEnableCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "State");
		boolean cliResult=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableCLI, wifiEnableExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.11_Verify Disable WiFi Tag Support");
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
