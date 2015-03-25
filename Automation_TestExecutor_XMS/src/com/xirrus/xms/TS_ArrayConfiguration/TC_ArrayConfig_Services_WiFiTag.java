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
public class TC_ArrayConfig_Services_WiFiTag extends TestSuiteBase{
	//WiFi Tag Parameters
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
	public void testCase_VerifyArrayConfigServicesWiFiTagParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Services WiFi Tag Parameters");
		// *****************************************************************
		wifiEnableExp="enabled";
		wifiUDPPortExp=CONFIG.getProperty("WiFiTagUDPPort");
		wifiTagChannelExp=CONFIG.getProperty("WiFiTagChannel");
		ekahauSrvrExp=CONFIG.getProperty("EkahauServer");
		
		wifiEnableOID=SNMPOID.getProperty("WiFiTagEnableOID");
		wifiUDPPortOID=SNMPOID.getProperty("WiFiTagUDPPortOID");
		wifiTagChannelOID=SNMPOID.getProperty("WiFiTagChannel1OID");
		ekahauSrvrOID=SNMPOID.getProperty("WifiTagEkahauServerOID");
		
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("WiFi Tag");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Get WiFi Tag Version value configured into array 
		if(xmslocallib.isRadioButtonSelected("Enable WiFi Tag Support",1))wifiEnableGUI="enabled"; else wifiEnableGUI="disabled";//WiFi Tag Enabled
		//Verify Wifi Tag enable status configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services WiFi Tag status value is enabled in array");
		boolean guistate=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableGUI, wifiEnableExp);
		//Verify WiFi Tag enable status configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services WiFi Tag status value is enabled in array");
		wifiEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiEnableOID);
		boolean snmpstate=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableSNMP, "1");
		//Verify WiFi Tag enable status configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services WiFi Tag status value is enabled in array");
		wifiEnableCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "State");
		boolean clistate=xmslocallib.verifyStringequals("WiFiTagEnableState", this.getClass().getSimpleName(), methodName, wifiEnableCLI, wifiEnableExp);
		//Get Location URL value configured into array 
		wifiUDPPortGUI= xmslocallib.getText("WiFi Tag UDP Port");
		//Verify WiFi Tag UDP Port configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services WiFi Tag UDP Port value in array");
		boolean guiport=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortGUI, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services WiFi Tag UDP Port value in array");
		wifiUDPPortSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiUDPPortOID);
		boolean snmpport=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortSNMP, wifiUDPPortExp);
		//Verify WiFi Tag UDP Port Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services WiFi Tag UDP Port value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "UDP Port");
		boolean cliport=xmslocallib.verifyStringequals("WiFiTagUDPPort", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiUDPPortExp);
		//Get Location URL value configured into array 
		wifiTagChannelGUI= xmslocallib.getDropDownText("WiFi Tag Channel").toLowerCase();
		//Verify WiFi Tag Channel configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services WiFi Tag Channel default value in array");
		boolean guichannel=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelGUI, wifiTagChannelExp);
		//Verify WiFi Tag Channel configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiTagChannelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, wifiTagChannelOID);
		boolean snmpchannel=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiTagChannelSNMP, wifiTagChannelExp);
		//Verify WiFi Tag Channel Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services WiFi Tag Channel default value in array");
		wifiUDPPortCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Tag Channel-bg");
		boolean clichannel=xmslocallib.verifyStringequals("WiFiTagChannel", this.getClass().getSimpleName(), methodName, wifiUDPPortCLI, wifiTagChannelExp);
		//Get Ekahau Server value configured into array 
		ekahauSrvrGUI= xmslocallib.getText("Ekahau Server");
		//Verify Ekahau Server configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Ekahau Server value in array");
		boolean guisrvr=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrGUI, ekahauSrvrExp);
		//Verify Ekahau Server configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Ekahau Server value in array");
		ekahauSrvrSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ekahauSrvrOID);
		boolean snmpsrvr=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrSNMP, ekahauSrvrExp);
		//Verify Ekahau Server Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Ekahau Server value in array");
		ekahauSrvrCLI=xmslocallib.getValuebyCLI_WiFiTag(arrayIPadd, "Ekahau Server");
		boolean clisrvr=xmslocallib.verifyStringequals("WiFiTagEkahauSrvr", this.getClass().getSimpleName(), methodName, ekahauSrvrCLI, ekahauSrvrExp);
		
		App_Log.debug("End-Verify Array Config Services WiFi Tag Parameters");	
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
