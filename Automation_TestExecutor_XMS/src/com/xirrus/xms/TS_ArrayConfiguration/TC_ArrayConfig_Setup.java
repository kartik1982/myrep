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
public class TC_ArrayConfig_Setup extends TestSuiteBase {
	// Admin General Settings Parameters
	private String locationInfoExp;
	private String adminContactExp;
	private String adminEmailExp;
	private String adminPhoneExp;
	// DNS Settings Paramters
	private String dnsDomainExp;
	private String dnsSrvr1Exp;
	private String dnsSrvr2Exp;
	private String dnsSrvr3Exp;
	//LLDP Settings Parameters
	private String lldpStateExp;
	//CDP Settings Parameters
	private String cdpStateExp;
	private String cdpIntervalExp;
	private String cdpHoldTimeExp;
	//VLAN Settings Parameter
	private String vlanNameExp;
	private String vlanIDExp;
	private String vlanMgmtExp;
	private String vlanDHCPExp;
	private String xirrusRoamingExp;
	private String vlanIPExp;
	private String vlanMaskExp;
	private String vlangatewayExp;
	private String vlantunnelExp;
	private String tunnelSecretExp;
	private String tunnelPortExp;
	//Service NetFlow Parameters
	private String versionExp;
	private String hostExp;
	private String portExp;
	//Services WiFi Tag
	private String wifiEnableExp;
	private String wifiUDPPortExp;
	private String wifiTagChannelExp;
	private String ekahauSrvrExp;
	//Services Location Parameters
	private String locationEnableExp;
	private String locationURLExp;
	private String locationKeyExp;
	private String locationPeriodExp;
	//Services DHCP Parameters
	private String dhcpEnableExp;
	private String dhcpNameExp;
	private String dhcpNATEnableExp;
	private String dhcpDefaultLeaseExp;
	private String dhcpMaxLeaseExp;
	private String dhcpStartIPExp;
	private String dhcpEndIPExp;
	private String dhcpMaskExp;
	private String dhcpGatewayExp;
	private String dhcpDomainExp;
	private String dhcpDNS1Exp;
	private String dhcpDNS2Exp;
	private String dhcpDNS3Exp;
	//Security AirWatch Parameters
	private String hostnameURLExp;
	private String userNameExp;
	private String passwordExp;
	private String apiKeyExp;
	private String apiTimeoutExp;
	private String pollPeriodExp;
	private String accessErrorActionExp;
	private String redirecthostnameURLExp;
	//Security Global Settings
	private String radiusSrvrExp;
	private String enableTKIPExp;
	private String enableAESExp;
	private String wpaRekeyExp;
	private String wpaRekeyTimeExp;
	private String wpaEAPExp;
	private String wpaPSKExp;
	private String wpaPreKeyExp;
	//IAPs Roaming Assist parameters
	private String roamingAssisExp;
	private String backoffPeriodExp;
	private String thresholdExp;
	private String dataRateExp;
	private String devicesExp;
	
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
	public void checkTestSkip() throws IOException {
		App_Log
				.debug("***************************Start of Test Case***************************");
		App_Log.debug("StartTC-TestCase-" + this.getClass().getSimpleName());
		App_Log.debug("Checking Run Mode of " + this.getClass().getSimpleName()
				+ " Test Case");
		if (!Test_Utility.isTestCaseRunnable(suite_ArrayConfiguration_xls, this
				.getClass().getSimpleName())) {
			App_Log.debug("Test case " + this.getClass().getSimpleName()
					+ " is skipped, was marked as No");
			throw new SkipException("Skipped Test case "
					+ this.getClass().getSimpleName() + ", was set as No");
		} else {
			App_Log.debug("!!!Test Case Run Mode for " + this.getClass()
					+ " is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}

	// Set All Paramters and last press Apply Configuration
	@Test(priority = 1)
	public void testCase_Begning_Setup_NavigateToArrayConfiguration()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Begning_Setup_NavigateToArrayConfiguration");
		// *****************************************************************
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
		// Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		// Navigate to Array General Settings Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.clickLink("Configuration");
		App_Log.debug("End-Begning_Setup_NavigateToArrayConfiguration");
	}

	// Set General Settings Paramters
	@Test(priority = 2)
	public void testCase_Setup1_GeneralSettings() throws InterruptedException,
			IOException {
		App_Log.debug("Start-Setup1_General Settings");
		// *****************************************************************
		locationInfoExp = CONFIG.getProperty("LocationInformation");
		adminContactExp = CONFIG.getProperty("AdminContact");
		adminEmailExp = CONFIG.getProperty("AdminEmail");
		adminPhoneExp = CONFIG.getProperty("AdminPhone");

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

		// **IMP:-Open General Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.clickLink("General", 2);
			xmslocallib.clickLink("General Settings");
			// Set Location Information
			xmslocallib.setText("Location Information", locationInfoExp);
			// Set Admin Contact Name
			xmslocallib.setText("Admin Contact", adminContactExp);
			// Set Admin Email
			xmslocallib.setText("Admin Email", adminEmailExp);
			// Set Admin Phone Number
			xmslocallib.setText("Admin Phone", adminPhoneExp);
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup1_General Settings");
	}

	// Set Network DNS Setup
	@Test(priority = 3)
	public void testCase_Setup2_NetworkDNSSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup2_NetworkDNSSettings");
		// *****************************************************************
		dnsDomainExp = CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp = CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp = CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp = CONFIG.getProperty("DNSSrvr3");

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
		// **IMP:-Click Network DNS Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("DNS");
			// Disable DNS IP assigned through DHCP Sever
			xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP",
					2);
			xmslocallib.pause(10);
			// Set DNS Domain Name
			xmslocallib.setText("DNS Domain", dnsDomainExp);
			// Set DNS Server 1
			xmslocallib.setText("DNS Server 1", dnsSrvr1Exp);
			// Set DNS Server 2
			xmslocallib.setText("DNS Server 2", dnsSrvr2Exp);
			// Set DNS Server 3
			xmslocallib.setText("DNS Server 3", dnsSrvr3Exp);
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup2_NetworkDNSSettings");
	}
	// Set Network LLDP Setup
	@Test(priority = 4)
	public void testCase_Setup3_NetworkLLDPSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup3_NetworkLLDPSettings");
		// *****************************************************************
		lldpStateExp = "enabled";

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
		// **IMP:-Click Network DNS Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Network LLDP
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("LLDP");
			//Enable LLDP
			xmslocallib.selectRadioButton("Enable LLDP", 1);
			
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup3_NetworkLLDPSettings");
	}
	
	// Set Network CDP Setup
	@Test(priority = 5)
	public void testCase_Setup4_NetworkCDPSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup4_NetworkCDPSettings");
		// *****************************************************************
		cdpStateExp="enabled";
		cdpIntervalExp=CONFIG.getProperty("CDPInterval");
		cdpHoldTimeExp=CONFIG.getProperty("CDPHoldTime");

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
		// **IMP:-Click Network CDP Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Network CDP
			xmslocallib.clickLink("Network");
			xmslocallib.clickLink("CDP");
			//Set CDP Parameters
			xmslocallib.selectRadioButton("Enable CDP", 1);
			xmslocallib.setText("CDP Interval", cdpIntervalExp);
			xmslocallib.setText("CDP Hold Time", cdpHoldTimeExp);
			
			
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup4_NetworkCDPSettings");
	}
	
	// Set VALN Setup
	@Test(priority = 6)
	public void testCase_Setup5_VLANSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup5_VLANSettings");
		// *****************************************************************
		int vlandID=101;
		vlanNameExp="VLAN"+Integer.toString(vlandID);
		vlanIDExp=Integer.toString(vlandID);
		vlanMgmtExp="Allowed";
		vlanDHCPExp="Disabled";
		xirrusRoamingExp="Enabled";
		vlanIPExp="10.100.59.31";
		vlanMaskExp="255.255.255.0";
		vlangatewayExp="10.100.59.1";
		vlantunnelExp="VLANTunnel";
		tunnelSecretExp="Kartik@123";
		tunnelPortExp="4000";

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
		// **IMP:-Click VLAN Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Network CDP
			xmslocallib.clickLink("VLAN");
			xmslocallib.clickLink("VLAN Management");
			//Set CDP Parameters
			boolean result=xmslocallib.addVLANintoArray(arrayHostName, vlanNameExp, vlanIDExp, vlanIPExp, vlanMaskExp, vlangatewayExp, vlantunnelExp, tunnelSecretExp, tunnelPortExp);
				if(result){
				xmslocallib.toggleCheckbox(3);
				xmslocallib.clickButton("Edit");
				xmslocallib.selectCheckBox("Management");
				xmslocallib.selectCheckBox("Xirrus Roaming");
				xmslocallib.clickButton("OK");
				}else{
					if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
					screencaptureFileName = "SetupError-"
						+ this.getClass().getSimpleName() + "_" + methodName
						+ ".png";
				xmslocallib.captureScreenshot(screencaptureFileName);
				}
			
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup5_VLANSettings");
	}
	
	// Set Services NetFlow Setup
	@Test(priority = 7)
	public void testCase_Setup6_ServicesNetFlowSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup6_ServicesNetFlowSettings");
		// *****************************************************************
		versionExp="v5";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");

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
		// **IMP:-Click Service NetFlow Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Service-> NetFlow
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Netflow");
			//Set Parameters for NetFlow
			xmslocallib.selectRadioButton("Netflow Version", 2); //v5
			xmslocallib.setText("Netflow Collector Host", hostExp);
			xmslocallib.setText("Netflow Collector Port", portExp);
	
			
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup6_ServicesNetFlowSettings");
	}
	// Set Services WiFi Tag Settings
	@Test(priority = 8)
	public void testCase_Setup7_ServicesWiFiTagSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup7_Services WiFiTag Settings");
		// *****************************************************************
		wifiEnableExp="enabled";
		wifiUDPPortExp=CONFIG.getProperty("WiFiTagUDPPort");
		wifiTagChannelExp=CONFIG.getProperty("WiFiTagChannel");
		ekahauSrvrExp=CONFIG.getProperty("EkahauServer");

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
		// **IMP:-Click Service WiFi Tag Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Service-> NetFlow
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("WiFi Tag");
			//Set Parameters for WiFi Tags
			xmslocallib.selectRadioButton("Enable WiFi Tag Support", 1);
			xmslocallib.setText("WiFi Tag UDP Port", wifiUDPPortExp);
			xmslocallib.selectDropDown("WiFi Tag Channel", wifiTagChannelExp);
			xmslocallib.setText("Ekahau Server", ekahauSrvrExp);
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup7_Services WiFiTag Settings");
	}
	
	// Set Services Location Settings
	@Test(priority = 9)
	public void testCase_Setup8_ServicesLocationSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup8_Services Location Settings");
		// *****************************************************************
		locationEnableExp="enabled";
		locationURLExp=CONFIG.getProperty("LocationURL");
		locationKeyExp=CONFIG.getProperty("LocationKey");
		locationPeriodExp=CONFIG.getProperty("LocationPeriod");

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
		// **IMP:-Click Service Location Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Service-> NetFlow
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("Location");
			//Set Parameters for WiFi Tags
			xmslocallib.selectRadioButton("Enable Location Support", 1);
			xmslocallib.setText("Location URL", locationURLExp);
			xmslocallib.setText("Location Key", locationKeyExp);
			xmslocallib.setText("Location Period", locationPeriodExp);
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup8_Services Location Settings");
	}
	
	// Set Services DHCP Server Settings
	@Test(priority = 10)
	public void testCase_Setup9_ServicesDHCPServerSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup9_Services DHCP Server Settings");
		// *****************************************************************
		dhcpEnableExp="enabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="300";
		dhcpMaxLeaseExp="300";
		dhcpStartIPExp="192.168.2.2";
		dhcpEndIPExp="192.168.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.168.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.168.2.10";
		dhcpDNS2Exp="192.168.3.10";
		dhcpDNS3Exp="192.168.4.10";

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
		// **IMP:-Click Service DHCP Sever Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Service-> DHCP Server
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			//Set Parameters for DHCP Server
			boolean result=xmslocallib.addDHCPServer(arrayHostName, dhcpNameExp, dhcpStartIPExp, dhcpEndIPExp, dhcpMaskExp, dhcpGatewayExp, dhcpDomainExp, dhcpDNS1Exp, dhcpDNS2Exp, dhcpDNS3Exp);
			if(result){
				xmslocallib.toggleCheckbox(3);
				xmslocallib.clickButton("Edit");
				xmslocallib.selectCheckBox("Enabled");
				xmslocallib.selectCheckBox("NAT Enabled");
				xmslocallib.clickButton("OK");
				}else{
					if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
					screencaptureFileName = "SetupError-"
						+ this.getClass().getSimpleName() + "_" + methodName
						+ ".png";
				xmslocallib.captureScreenshot(screencaptureFileName);
				}
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup9_Services DHCP Server Settings");
	}
	// Set Security AirWatch Settings
	@Test(priority = 11)
	public void testCase_Setup10_SecurityAirWatchSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup10_Security AirWatch Settings");
		// *****************************************************************
		hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
		passwordExp=CONFIG.getProperty("APIPassword");
		apiKeyExp=CONFIG.getProperty("APIKey");
		apiTimeoutExp="10";
		pollPeriodExp="600";
		accessErrorActionExp="Allow";
		redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");

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
		// **IMP:-Click Service Location Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Security-> AirWatch
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("AirWatch");
			//Set Paramters for AirWatch
			xmslocallib.setText("API URL/Hostname", hostnameURLExp);
			xmslocallib.setText("API Username", userNameExp);
			xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
			xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
			xmslocallib.setText("API Key", apiKeyExp);
			xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
			xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
			xmslocallib.selectDropDown("API Access Error Action", accessErrorActionExp);
			xmslocallib.setText("Redirect URL/Hostname", redirecthostnameURLExp);
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup10_Security AirWatch Settings");
	}
	// Set Security Global Settings WPA Settings
	@Test(priority = 12)
	public void testCase_Setup11_SecurityGlobalSettingsWPA()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup11_Security Global Settings WPA");
		// *****************************************************************
		radiusSrvrExp="internal server";
		enableTKIPExp="on";
		enableAESExp="on";
		wpaRekeyTimeExp="2000";
		wpaEAPExp="off";
		wpaPSKExp="on";
		wpaPreKeyExp="Kartik@123";

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
		// **IMP:-Click Service Location Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Security-> Global Settings
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("Global Settings");
			//Set Paramters for AirWatch
			xmslocallib.selectRadioButton("RADIUS Server Mode", 2);
			xmslocallib.selectRadioButton("TKIP Enabled", 1);
			xmslocallib.selectRadioButton("AES Enabled", 1);
			xmslocallib.selectRadioButton("WPA Group Rekey Enabled", 1);
			xmslocallib.setText("WPA Group Rekey Time (seconds)", wpaRekeyTimeExp);
			xmslocallib.selectRadioButton("WPA Authentication", 2);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 1);
			xmslocallib.setText("WPA Preshared Key / Verify Key", wpaPreKeyExp, 2);
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup11_Security Global Settings WPA");
	}
	
	// Set IAPs Roaming Assist Settings
	@Test(priority = 13)
	public void testCase_Setup11_IAPsRoamingAssitSettings()
			throws InterruptedException, IOException {
		App_Log.debug("Start-Setup12_IAPs Roaming Assit Settings");
		// *****************************************************************
		roamingAssisExp="Enabled";
		backoffPeriodExp=CONFIG.getProperty("BackoffPeriod");
		thresholdExp=CONFIG.getProperty("RoamingThreshold");
		dataRateExp=CONFIG.getProperty("MinimumDataRate");

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
		// **IMP:-Click IAPs Roaming Assist Settings Tab without moving outside Array
		// Configuration Tab
		try {
			xmslocallib.pause(10);
			//Click Security-> Global Settings
			xmslocallib.clickLink("IAPs", 2);
			xmslocallib.clickLink("Roaming Assist");
			//Set Paramters for AirWatch
			xmslocallib.selectRadioButton("Enable Roaming Assist", 1);
			xmslocallib.pause(10);
			xmslocallib.setText("Backoff Period", backoffPeriodExp);
			xmslocallib.setText("Roaming Threshold", thresholdExp);
			xmslocallib.setText("Minimum Data Rate", dataRateExp);
			xmslocallib.selectCheckBox("Array");
			xmslocallib.selectCheckBox("Notebook");
			xmslocallib.selectCheckBox("Phone");
			xmslocallib.selectCheckBox("Player");
			xmslocallib.selectCheckBox("Game");
			xmslocallib.selectCheckBox("Tablet");
		
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		App_Log.debug("End-Setup12_IAPs Roaming Assit Settings");
	}

	// Finally Settings All parameters press Apply Configuration
	@Test(priority = 14)
	public void testCase_End_Setup_ApplyConfigurationToArray()
			throws InterruptedException, IOException {
		App_Log.debug("Start-End_Setup_ApplyConfigurationToArray");
		try {
			xmslocallib.clickButton("Apply Config");
			// xmslocallib.clickButton("OK");
			xmslocallib.setTimeout(180);
			// message:-"Done configuring Array" PR-21595
			if (!xmslocallib.findText("Done Saving configuration to flash")) {
				App_Log
						.debug("Did not find an expected configuration message.");
				throw new InterruptedException(
						"Did not find an expected configuration message.");
			}
		} catch (Exception e) {
			App_Log.error("Error:-" + e);
			screencaptureFileName = "SetupError-"
					+ this.getClass().getSimpleName() + "_" + methodName
					+ ".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("End-End_Setup_ApplyConfigurationToArray");
	}

	@AfterTest
	public void testCompleted() throws IOException, InterruptedException {
		App_Log.debug("Test Case execution for " + this.getClass()
				+ " is completed!!!");
		App_Log.debug("EndTestCase-" + this.getClass().getSimpleName());
		App_Log
				.debug("***********************End Of test Case********************************");

	}

	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_ArrayConfiguration_xls, this
				.getClass().getSimpleName(), this.getClass().getSimpleName());

	}

}
