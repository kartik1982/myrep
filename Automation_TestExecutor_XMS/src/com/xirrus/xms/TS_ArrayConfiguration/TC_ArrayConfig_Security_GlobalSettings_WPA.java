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
public class TC_ArrayConfig_Security_GlobalSettings_WPA extends TestSuiteBase{
	//Global Settings WPA parameters
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
	public void testCase_VerifyArrayConfigSecurityGlobalSettingsWPAParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Security Global Settings WPA Parameters");
		// *****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyExp="enabled";
		wpaRekeyTimeExp="2000";
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
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("RADIUS Server Mode", 1)) radiusSrvrGUI="external server"; else radiusSrvrGUI="internal server";
		//Verify Radius Server Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify RADIUS Server Mode Internal");
		boolean guiradius=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrGUI, radiusSrvrExp);
	
		//Verify Radius Server Configured into Array by SNMP
		App_Log.debug("SNMP-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, radiusSrvrOID);
		boolean snmpradius=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrSNMP, "2");
		//Verify Radius Server Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify RADIUS Server Mode Internal");
		radiusSrvrCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "radius");
		boolean cliradius=xmslocallib.verifyStringequals("RadiusServer", this.getClass().getSimpleName(), methodName, radiusSrvrCLI, radiusSrvrExp);
		
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("TKIP Enabled", 1)) enableTKIPGUI="on"; else enableTKIPGUI="off";
		//Verify WPA TKIP Enabled Default Value Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify WPA TKIP Enabled YES Value");
		boolean guitikip=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPGUI, enableTKIPExp);
		//Verify WPA TKIP Enabled Default Value Configured into Array by SNMP
		App_Log.debug("SNMP-Verification:-Verify WPA TKIP Enabled YES Value");
		enableTKIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableTKIPOID);
		boolean snmptkip=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPSNMP, "1");
		//Verify WPA TKIP Enabled Default Value Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify WPA TKIP Enabled YES Value");
		enableTKIPCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "TKIP");
		boolean clitkip=xmslocallib.verifyStringequals("TKIPEnabled", this.getClass().getSimpleName(), methodName, enableTKIPCLI, enableTKIPExp);
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("AES Enabled", 1)) enableAESGUI="on"; else enableAESGUI="off";
		//Verify WPA AES Enabled Default Value Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify WPA AES Enabled NO Value");
		boolean guiaes=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESGUI, enableAESExp);
		//Verify WPA AES Enabled Default Value Configured into Array by SNMP
		App_Log.debug("SNMP-Verification:-Verify WPA AES Enabled NO Value");
		enableAESSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, enableAESOID);
		boolean snmpaes=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify WPA AES Enabled NO Value");
		enableAESCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "AES");
		boolean cliaes=xmslocallib.verifyStringequals("AESEnabled", this.getClass().getSimpleName(), methodName, enableAESCLI, enableAESExp);
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Group Rekey Enabled", 1)) wpaRekeyGUI="enabled"; else wpaRekeyGUI="disabled";
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify WPA Group Rekey Enabled Default Value");
		boolean guirekeystate=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyGUI, wpaRekeyExp);
		//get Radius Server configured into array from GUI		
		wpaRekeyTimeGUI= xmslocallib.getText("WPA Group Rekey Time (seconds)");
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify WPA Group Rekey Time Set Value");
		boolean guirekey=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeGUI, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by SNMP
		App_Log.debug("SNMP-Verification:-Verify WPA Group Rekey Time Set Value");
		wpaRekeyTimeSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaRekeyTimeOID);
		boolean snmprekey=xmslocallib.verifyStringequals("WPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeSNMP, wpaRekeyTimeExp);
		//Verify WPA Group Rekey Enabled Default Value Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify WPA Group Rekey Time Set Value");
		wpaRekeyTimeCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "rekey time").replace("secs", "").trim();
		boolean clirekey=xmslocallib.verifyStringequals("WPAGroupRekeyEnabledWPAGroupRekeyEnabled", this.getClass().getSimpleName(), methodName, wpaRekeyTimeCLI, wpaRekeyTimeExp);
		//get Radius Server configured into array from GUI		
		if(xmslocallib.isRadioButtonSelected("WPA Authentication", 2)) wpaPSKGUI="on"; else wpaPSKGUI="off";
		//Verify WPA PSK Default Value Configured into Array by GUI
		App_Log.debug("GUI-Verification:-Verify WPA Authentication PSK Value");
		boolean guipsk=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKGUI, wpaPSKExp);
		//Verify WPA PSK Default Value Configured into Array by SNMP
		App_Log.debug("SNMP-Verification:-Verify WPA Authentication PSK Value");
		wpaPSKSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wpaPSKOID);
		boolean snmppsk=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKSNMP, "1");
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify WPA Authentication PSK Value");
		wpaPSKCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "PSK");
		boolean clipsk=xmslocallib.verifyStringequals("WPAAuthenticationPSK", this.getClass().getSimpleName(), methodName, wpaPSKCLI, wpaPSKExp);
		//Verify WPA AES Enabled Default Value Configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify WPA Preshared Key Set Value");
		wpaPreKeyCLI=xmslocallib.getValuebyCLI_Security(arrayIPadd, "passphrase");
		boolean clipskpasswd=xmslocallib.verifyStringequals("WPAAuthenticationPSKPassword", this.getClass().getSimpleName(), methodName, wpaPreKeyCLI, wpaPreKeyExp);
		
		App_Log.debug("End-Verify Array Config Security Global Settings WPA Parameters");		
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
