package com.xirrus.xms.TS_Array_Security;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Security_GlobalSettings_WPA extends TestSuiteBase{
	private String radiusSrvrSNMP;
	private String radiusSrvrGUI;
	private String radiusSrvrCLI;
	private String enableTKIPSNMP;
	private String enableTKIPGUI;
	private String enableTKIPCLI;
	private String enableAESSNMP;
	private String enableAESGUI;
	private String enableAESCLI;
	private String wpaRekeySNMP;
	private String wpaRekeyGUI;
	private String wpaRekeyCLI;
	private String wpaRekeyTimeSNMP;
	private String wpaRekeyTimeGUI;
	private String wpaRekeyTimeCLI;
	private String wpaEAPSNMP;
	private String wpaEAPGUI;
	private String wpaEAPCLI;
	private String wpaPSKSNMP;
	private String wpaPSKGUI;
	private String wpaPSKCLI;
	private String wpaPreKeySNMP;
	private String wpaPreKeyGUI;
	private String wpaPreKeyCLI;
	private String radiusSrvrExp;
	private String enableTKIPExp;
	private String enableAESExp;
	private String wpaRekeyExp;
	private String wpaRekeyTimeExp;
	private String wpaEAPExp;
	private String wpaPSKExp;
	private String wpaPreKeyExp;
	private String radiusSrvrOID;
	private String enableTKIPOID;
	private String enableAESOID;
	private String wpaRekeyOID;
	private String wpaRekeyTimeOID;
	private String wpaEAPOID;
	private String wpaPSKOID;
	private String wpaPreKeyOID;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Security_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-2.1_Verify RADIUS Server Mode Default Value
	@Test(priority=1)
	public void testCase_VerifyRADIUSServerModeDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify RADIUS Server Mode Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="";
		wpaEAPExp="";
		wpaPSKExp="";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("Global Settings");
			xmslocallib.pause(10);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("RADIUS Server Mode", 1)) radiusSrvrGUI="external server"; else radiusSrvrGUI="internal server";
		//Verify Radius Server Configured into Array by GUI
		App_Log.debug("Ref-2.1_GUI-Verification:-Verify RADIUS Server Mode Default Value");
		guiResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrGUI, radiusSrvrExp);
		
		//Verify Radius Server Configured into Array by SNMP
		App_Log.debug("Ref-2.1_SNMP-Verification:-Verify RADIUS Server Mode Default Value");
		radiusSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, radiusSrvrOID);
		snmpResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrSNMP, "1");
		//Verify Radius Server Configured into Array by CLI
		App_Log.debug("Ref-2.1_CLI-Verification:-Verify RADIUS Server Mode Default Value");
		radiusSrvrCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "radius");
		cliResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrCLI, radiusSrvrExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.1_Verify RADIUS Server Mode Default Value");
	}
	//Ref-2.2_Verify WPA TKIP Enabled Default Value
	@Test(priority=2)
	public void testCase_VerifyWPATKIPEnabledDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.2_Verify WPA TKIP Enabled Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="";
		wpaEAPExp="";
		wpaPSKExp="";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("TKIP Enabled", 1)) enableTKIPGUI="on"; else enableTKIPGUI="off";
		//Verify WPA TKIP Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.2_GUI-Verification:-Verify WPA TKIP Enabled Default Value");
		guiResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPGUI, enableTKIPExp);
		//Verify WPA TKIP Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.2_SNMP-Verification:-Verify WPA TKIP Enabled Default Value");
		enableTKIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableTKIPOID);
		snmpResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPSNMP, "0");
		//Verify WPA TKIP Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.2_CLI-Verification:-Verify WPA TKIP Enabled Default Value");
		enableTKIPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "TKIP");
		cliResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPCLI, enableTKIPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.2_Verify WPA TKIP Enabled Default Value");
	}
	//Ref-2.3_Verify WPA AES Enabled Default Value
	@Test(priority=3)
	public void testCase_VerifyWPAAESEnabledDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify WPA AES Enabled Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="disabled";
		wpaEAPExp="";
		wpaPSKExp="";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("AES Enabled", 1)) enableAESGUI="on"; else enableAESGUI="off";
		//Verify WPA AES Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.3_GUI-Verification:-Verify WPA AES Enabled Default Value");
		guiResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESGUI, enableAESExp);
		//Verify WPA AES Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.3_SNMP-Verification:-Verify WPA AES Enabled Default Value");
		enableAESSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableAESOID);
		snmpResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.3_CLI-Verification:-Verify WPA AES Enabled Default Value");
		enableAESCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "AES");
		cliResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESCLI, enableAESExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.3_Verify WPA AES Enabled Default Value");
	}
	//Ref-2.4_Verify WPA Group Rekey Enabled Default Value
	@Test(priority=4)
	public void testCase_VerifyWPAGroupRekeyEnabledDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.4_Verify WPA Group Rekey Enabled Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="disabled";
		wpaEAPExp="";
		wpaPSKExp="";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Group Rekey Enabled", 1)) wpaRekeyGUI="enabled"; else wpaRekeyGUI="disabled";
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.4_GUI-Verification:-Verify WPA Group Rekey Enabled Default Value");
		guiResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyGUI, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.4_SNMP-Verification:-Verify WPA Group Rekey Enabled Default Value");
		wpaRekeyTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaRekeyTimeOID);
		snmpResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeSNMP, "0");
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.4_CLI-Verification:-Verify WPA Group Rekey Enabled Default Value");
		wpaRekeyTimeCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "rekey time");
		cliResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabledWPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeCLI, wpaRekeyTimeExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.4_Verify WPA Group Rekey Enabled Default Value");
	}
	//Ref-2.5_Verify WPA Authentication Default Value
	@Test(priority=5)
	public void testCase_VerifyWPAEAPDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.5_Verify WPA EAP Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="disabled";
		wpaEAPExp="on";
		wpaPSKExp="off";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 1)) wpaEAPGUI="on"; else wpaEAPGUI="off";
		//Verify WPA EAP Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.5_GUI-Verification:-Verify WPA EAP Default Value");
		guiResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPGUI, wpaEAPExp);
		//Verify WPA EAP Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.5_SNMP-Verification:-Verify WPA EAP Default Value");
		wpaEAPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaEAPOID);
		snmpResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPSNMP, "1");
		//Verify WPA EAP Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.5_CLI-Verification:-Verify WPA EAP Default Value");
		wpaEAPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "EAP");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPCLI, wpaEAPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-2.5_Verify WPA EAP Default Value");
	}
	//Ref-2.6_Verify WPA Authentication Default Value
	@Test(priority=6)
	public void testCase_VerifyWPAPSKDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.6_Verify WPA PSK Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="disabled";
		wpaEAPExp="on";
		wpaPSKExp="off";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 2)) wpaPSKGUI="on"; else wpaPSKGUI="off";
		//Verify WPA PSK Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.6_GUI-Verification:-Verify WPA PSK Default Value");
		guiResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKGUI, wpaPSKExp);
		//Verify WPA PSK Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.6_SNMP-Verification:-Verify WPA PSK Default Value");
		wpaPSKSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaPSKOID);
		snmpResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKSNMP, "0");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.6_CLI-Verification:-Verify WPA PSK Default Value");
		wpaPSKCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "PSK");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKCLI, wpaPSKExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.6_Verify WPA PSK Default Value");
	}
	//Ref-2.7_Verify WPA Preshared Key Default Value
	@Test(priority=7)
	public void testCase_VerifyWPAPresharedKeyDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.7_Verify WPA Preshared Key Default Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="off";
		enableAESExp="on";
		wpaRekeyTimeExp="disabled";
		wpaEAPExp="on";
		wpaPSKExp="off";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.7_CLI-Verification:-Verify WPA Preshared Key Default Value");
		wpaPreKeyCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "passphrase");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSKPassword", this.getClass().getSimpleName(), methodName, wpaPreKeyCLI, wpaPreKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-2.7_Verify WPA Preshared Key Default Value");
	}
	//Ref-2.8_Verify RADIUS Server Mode Internal
	@Test(priority=8)
	public void testCase_VerifyRADIUSServerModeInternal()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.8_Verify RADIUS Server Mode Internal");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("Global Settings");
			xmslocallib.selectRadioButton("RADIUS Server Mode", 2);
			xmslocallib.selectRadioButton("TKIP Enabled", 1);
			xmslocallib.selectRadioButton("AES Enabled", 2);
			xmslocallib.selectRadioButton("WPA Group Rekey Enabled", 1);
			xmslocallib.selectRadioButton("WPA Authentication", 2);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 1);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 2);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
		//message:-"Done configuring Array" PR-21595
		if (! xmslocallib.findText("Done Saving configuration to flash")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
			}
		}catch (Exception e) {
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
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("Global Settings");
		xmslocallib.pause(10);
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("RADIUS Server Mode", 1)) radiusSrvrGUI="external server"; else radiusSrvrGUI="internal server";
		//Verify Radius Server Configured into Array by GUI
		App_Log.debug("Ref-2.8_GUI-Verification:-Verify RADIUS Server Mode Internal");
		guiResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrGUI, radiusSrvrExp);
	
		//Verify Radius Server Configured into Array by SNMP
		App_Log.debug("Ref-2.8_SNMP-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, radiusSrvrOID);
		snmpResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrSNMP, "2");
		//Verify Radius Server Configured into Array by CLI
		App_Log.debug("Ref-2.8_CLI-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "radius");
		cliResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrCLI, radiusSrvrExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
		testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.8_Verify RADIUS Server Mode Internal");
	}
	//Ref-2.9_Verify WPA TKIP Enabled YES Value
	@Test(priority=9)
	public void testCase_VerifyWPATKIPEnabledYESValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.9_Verify WPA TKIP Enabled YES Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("TKIP Enabled", 1)) enableTKIPGUI="on"; else enableTKIPGUI="off";
		//Verify WPA TKIP Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.9_GUI-Verification:-Verify WPA TKIP Enabled YES Value");
		guiResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPGUI, enableTKIPExp);
		//Verify WPA TKIP Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.9_SNMP-Verification:-Verify WPA TKIP Enabled YES Value");
		enableTKIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableTKIPOID);
		snmpResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPSNMP, "1");
		//Verify WPA TKIP Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.9_CLI-Verification:-Verify WPA TKIP Enabled YES Value");
		enableTKIPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "TKIP");
		cliResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPCLI, enableTKIPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.9_Verify WPA TKIP Enabled YES Value");
	}
	//Ref-2.10_Verify WPA AES Enabled NO Value
	@Test(priority=10)
	public void testCase_VerifyWPAAESEnabledNOValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.10_Verify WPA AES Enabled NO Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("AES Enabled", 1)) enableAESGUI="on"; else enableAESGUI="off";
		//Verify WPA AES Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.10_GUI-Verification:-Verify WPA AES Enabled NO Value");
		guiResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESGUI, enableAESExp);
		//Verify WPA AES Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.10_SNMP-Verification:-Verify WPA AES Enabled NO Value");
		enableAESSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableAESOID);
		snmpResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESSNMP, "0");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.10_CLI-Verification:-Verify WPA AES Enabled NO Value");
		enableAESCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "AES");
		cliResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESCLI, enableAESExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.10_Verify WPA AES Enabled NO Value");
	}
	//Ref-2.11_Verify WPA Group Rekey Enabled YES Value
	@Test(priority=11)
	public void testCase_VerifyWPAGroupRekeyEnabledYESValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.11_Verify WPA Group Rekey Enabled YES Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Group Rekey Enabled", 1)) wpaRekeyGUI="enabled"; else wpaRekeyGUI="disabled";
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.11_GUI-Verification:-Verify WPA Group Rekey Enabled Default Value");
		guiResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyGUI, wpaRekeyExp);
		if(guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.11_Verify WPA Group Rekey Enabled YES Value");
	}
	//Ref-2.12_Verify WPA Group Rekey Time Set Value
	@Test(priority=12)
	public void testCase_VerifyWPAGroupRekeyTimeSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.12_Verify WPA Group Rekey Time Set Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		wpaRekeyTimeGUI= xmslocallib.getText("WPA Group Rekey Time (seconds)");
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.12_GUI-Verification:-Verify WPA Group Rekey Time Set Value");
		guiResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeGUI, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.12_SNMP-Verification:-Verify WPA Group Rekey Time Set Value");
		wpaRekeyTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaRekeyTimeOID);
		snmpResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeSNMP, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.12_CLI-Verification:-Verify WPA Group Rekey Time Set Value");
		wpaRekeyTimeCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "rekey time").replace("secs", "").trim();
		cliResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabledWPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeCLI, wpaRekeyTimeExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-2.12_Verify WPA Group Rekey Time Set Value");
	}
	//Ref-2.13_Verify WPA Authentication PSK Value
	@Test(priority=13)
	public void testCase_VerifyWPAAuthenticationEAPValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.13_Verify WPA Authentication EAP Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 1)) wpaEAPGUI="on"; else wpaEAPGUI="off";
		//Verify WPA EAP Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.13_GUI-Verification:-Verify WPA Authentication EAP Value");
		guiResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPGUI, wpaEAPExp);
		//Verify WPA EAP Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.13_SNMP-Verification:-Verify WPA Authentication EAP Value");
		wpaEAPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaEAPOID);
		snmpResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPSNMP, "0");
		//Verify WPA EAP Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.13_CLI-Verification:-Verify WPA Authentication EAP Value");
		wpaEAPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "EAP");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenitcationEAP", this.getClass().getSimpleName(), methodName, wpaEAPCLI, wpaEAPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		
		App_Log.debug("End-Ref-2.13_Verify WPA Authentication EAP Value");
	}
	//Ref-2.14_Verify WPA Authentication PSK Value
	@Test(priority=14)
	public void testCase_VerifyWPAAuthenticationPSKValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.14_Verify WPA Authentication PSK Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 2)) wpaPSKGUI="on"; else wpaPSKGUI="off";
		//Verify WPA PSK Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.14_GUI-Verification:-Verify WPA Authentication PSK Value");
		guiResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKGUI, wpaPSKExp);
		//Verify WPA PSK Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.14_SNMP-Verification:-Verify WPA Authentication PSK Value");
		wpaPSKSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaPSKOID);
		snmpResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.14_CLI-Verification:-Verify WPA Authentication PSK Value");
		wpaPSKCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "PSK");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKCLI, wpaPSKExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.14_Verify WPA Authentication PSK Value");
	}
	//Ref-2.15_Verify WPA Preshared Key Set Value
	@Test(priority=15)
	public void testCase_VerifyWPAPresharedKeySetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.15_Verify WPA Preshared Key Set Value");
		//*****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="off";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="100";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.15_CLI-Verification:-Verify WPA Preshared Key Set Value");
		wpaPreKeyCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "passphrase");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSKPassword", this.getClass().getSimpleName(), methodName, wpaPreKeyCLI, wpaPreKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.15_Verify WPA Preshared Key Set Value");
	}
	
	//Ref-2.16_Verify RADIUS Server Mode External
	@Test(priority=16)
	public void testCase_VerifyRADIUSServerModeExternal()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.16_Verify RADIUS Server Mode External");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("Global Settings");
			xmslocallib.selectRadioButton("RADIUS Server Mode", 1);
			xmslocallib.selectRadioButton("TKIP Enabled", 1);
			xmslocallib.selectRadioButton("AES Enabled", 1);
			xmslocallib.selectRadioButton("WPA Group Rekey Enabled", 1);
			xmslocallib.setText("WPA Group Rekey Time (seconds)", wpaRekeyTimeExp);
			xmslocallib.selectRadioButton("WPA Authentication", 2);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 1);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 2);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);
		//message:-"Done configuring Array" PR-21595
		if (! xmslocallib.findText("Done Saving configuration to flash")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
			}
		}catch (Exception e) {
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
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("Global Settings");
		xmslocallib.pause(10);
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("RADIUS Server Mode", 1)) radiusSrvrGUI="external server"; else radiusSrvrGUI="internal server";
		//Verify Radius Server Configured into Array by GUI
		App_Log.debug("Ref-2.16_GUI-Verification:-Verify RADIUS Server Mode Internal");
		guiResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrGUI, radiusSrvrExp);
	
		//Verify Radius Server Configured into Array by SNMP
		App_Log.debug("Ref-2.16_SNMP-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, radiusSrvrOID);
		snmpResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrSNMP, "1");
		//Verify Radius Server Configured into Array by CLI
		App_Log.debug("Ref-2.16_CLI-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "radius");
		cliResult=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrCLI, radiusSrvrExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
		testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.16_Verify RADIUS Server Mode Internal");
	}
	//Ref-2.17_Verify WPA TKIP Enabled YES Value
	@Test(priority=17)
	public void testCase_VerifyWPATKIPEnabledValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.17_Verify WPA TKIP Enabled Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("TKIP Enabled", 1)) enableTKIPGUI="on"; else enableTKIPGUI="off";
		//Verify WPA TKIP Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.17_GUI-Verification:-Verify WPA TKIP Enabled Value");
		guiResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPGUI, enableTKIPExp);
		//Verify WPA TKIP Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.17_SNMP-Verification:-Verify WPA TKIP Enabled Value");
		enableTKIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableTKIPOID);
		snmpResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPSNMP, "1");
		//Verify WPA TKIP Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.17_CLI-Verification:-Verify WPA TKIP Enabled Value");
		enableTKIPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "TKIP");
		cliResult=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPCLI, enableTKIPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.17_Verify WPA TKIP Enabled Value");
	}
	//Ref-2.18_Verify WPA AES Enabled NO Value
	@Test(priority=18)
	public void testCase_VerifyWPAAESEnabledValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.18_Verify WPA AES Enabled Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("AES Enabled", 1)) enableAESGUI="on"; else enableAESGUI="off";
		//Verify WPA AES Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.18_GUI-Verification:-Verify WPA AES Enabled  Value");
		guiResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESGUI, enableAESExp);
		//Verify WPA AES Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.18_SNMP-Verification:-Verify WPA AES Enabled Value");
		enableAESSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableAESOID);
		snmpResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.18_CLI-Verification:-Verify WPA AES Enabled Value");
		enableAESCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "AES");
		cliResult=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESCLI, enableAESExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.18_Verify WPA AES Enabled Value");
	}
	
	//Ref-2.19_Verify WPA Group Rekey Time Edit Value
	@Test(priority=19)
	public void testCase_VerifyWPAGroupRekeyTimeEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.19_Verify WPA Group Rekey Time Edit Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		wpaRekeyTimeGUI= xmslocallib.getText("WPA Group Rekey Time (seconds)");
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.19_GUI-Verification:-Verify WPA Group Rekey Time Edit Value");
		guiResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeGUI, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.19_SNMP-Verification:-Verify WPA Group Rekey Time Edit Value");
		wpaRekeyTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaRekeyTimeOID);
		snmpResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeSNMP, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.19_CLI-Verification:-Verify WPA Group Rekey Time Edit Value");
		wpaRekeyTimeCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "rekey time").replace("secs", "").trim();
		cliResult=xmslocallib.verifyStringequals("WPAGroupRekeyEnabledWPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeCLI, wpaRekeyTimeExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-2.19_Verify WPA Group Rekey Time Set Value");
	}
	
	//Ref-2.20_Verify WPA Authentication PSK Edit Value
	@Test(priority=20)
	public void testCase_VerifyWPAAuthenticationPSKEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.20_Verify WPA Authentication PSK Edit Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 2)) wpaPSKGUI="on"; else wpaPSKGUI="off";
		//Verify WPA PSK Default Value Configured into Array by GUI
		App_Log.debug("Ref-2.20_GUI-Verification:-Verify WPA Authentication PSK Edit Value");
		guiResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKGUI, wpaPSKExp);
		//Verify WPA PSK Default Value Configured into Array by SNMP
		App_Log.debug("Ref-2.20_SNMP-Verification:-Verify WPA Authentication PSK Edit Value");
		wpaPSKSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaPSKOID);
		snmpResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.20_CLI-Verification:-Verify WPA Authentication PSK Edit Value");
		wpaPSKCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "PSK");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKCLI, wpaPSKExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.20_Verify WPA Authentication PSK Edit Value");
	}
	//Ref-2.21_Verify WPA Preshared Key Edit Value
	@Test(priority=21)
	public void testCase_VerifyWPAPresharedKeyEditValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.21_Verify WPA Preshared Key Edit Value");
		//*****************************************************************
		radiusSrvrExp="external server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="TodayNewDay";
		radiusSrvrOID=SNMPOID.getProperty("RADIUS");
		enableTKIPOID=SNMPOID.getProperty("WPATKIP");
		enableAESOID=SNMPOID.getProperty("WPAAES");
		wpaRekeyTimeOID=SNMPOID.getProperty("WPARekey");
		wpaEAPOID=SNMPOID.getProperty("WPAEAP");
		wpaPSKOID=SNMPOID.getProperty("WPAPSK");
		wpaPreKeyOID=SNMPOID.getProperty("WPAPassPhrase");
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
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("Ref-2.21_CLI-Verification:-Verify WPA Preshared Key Edit Value");
		wpaPreKeyCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "passphrase");
		cliResult=xmslocallib.verifyStringequals("WPAAuthenticationPSKPassword", this.getClass().getSimpleName(), methodName, wpaPreKeyCLI, wpaPreKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.21_Verify WPA Preshared Key Edit Value");
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
