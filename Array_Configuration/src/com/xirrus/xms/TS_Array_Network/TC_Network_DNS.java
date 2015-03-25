package com.xirrus.xms.TS_Array_Network;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_Network_DNS extends TestSuiteBase{
	private String dnsByDHCPSNMP;
	private String dnsByDHCPGUI;
	private String dnsByDHCPCLI;
	private String dnsHostSNMP;
	private String dnsHostGUI;
	private String dnsHostCLI;
	private String dnsDomainSNMP;
	private String dnsDomainGUI;
	private String dnsDomainCLI;
	private String dnsSrvr1SNMP;
	private String dnsSrvr1GUI;
	private String dnsSrvr1CLI;
	private String dnsSrvr2SNMP;
	private String dnsSrvr2GUI;
	private String dnsSrvr2CLI;
	private String dnsSrvr3SNMP;
	private String dnsSrvr3GUI;
	private String dnsSrvr3CLI;
	private String dnsByDHCPExp;
	private String dnsHostExp;
	private String dnsDomainExp;
	private String dnsSrvr1Exp;
	private String dnsSrvr2Exp;
	private String dnsSrvr3Exp;
	private String dnsByDHCPOID;
	private String dnsHostOID;
	private String dnsDomainOID;
	private String dnsSrvr1OID;
	private String dnsSrvr2OID;
	private String dnsSrvr3OID;
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
	//Ref-3.1_Verify DNS Hostname Default value
	@Test(priority=1)
	public void testCase_VerifyDNSHostnameDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.1_Verify DNS Hostname Default value");
		//*****************************************************************
		dnsByDHCPExp="enabled";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
			xmslocallib.clickLink("DNS");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		//get Location Information value configured into array from GUI
		dnsHostGUI = xmslocallib.getText("DNS Hostname");
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.1_GUI-Verification:-Verify DNS Hostname is not empty by default in array");
		guiResult=xmslocallib.verifyStringequals("DNSHostname", this.getClass().getSimpleName(), methodName, dnsHostGUI, dnsHostExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("Ref-3.1_SNMP-Verification:-Verify DNS Hostname is not empty by default in array");
		dnsHostSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsHostOID);
		snmpResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, dnsHostSNMP, dnsHostExp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.1_CLI-Verification:-Verify DNS Hostname is not empty by default in array");
		dnsHostCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Hostname");
		cliResult=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, dnsHostCLI, dnsHostExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.1_Verify DNS Hostname Default value");
	}
	//Ref-3.2_Verify Use DNS settings assigned by DHCP default value
	@Test(priority=2)
	public void testCase_VerifyUseDNSSettingsAssignedbyDHCPDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.2_Verify Use DNS settings assigned by DHCP default value");
		//*****************************************************************
		dnsByDHCPExp="on";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
		//get Assigned by DHCP value configured into array from GUI
		boolean byDHCPIPEnable=xmslocallib.isRadioButtonSelected("Use DNS settings assigned by DHCP",1); //WiFi Tag Enabled
		if(byDHCPIPEnable)
			dnsByDHCPGUI="on";
		else
			dnsByDHCPGUI="off";
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.2_GUI-Verification:-Verify Use DNS settings assigned by DHCP default value on");
		guiResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPGUI, dnsByDHCPExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("Ref-3.2_SNMP-Verification:-Verify Use DNS settings assigned by DHCP default value on");
		dnsByDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsByDHCPOID);
		snmpResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPSNMP, "1");
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.2_CLI-Verification:-Verify Use DNS settings assigned by DHCP default value on");
		dnsByDHCPCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Use DHCP");
		cliResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPCLI, dnsByDHCPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.2_Verify Use DNS settings assigned by DHCP default value");
	}
	//Ref-3.3_Verify Use DNS settings assigned by DHCP Disable
	@Test(priority=3)
	public void testCase_VerifyUseDNSSettingsAssignedByDHCPDisable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.3_Verify Use DNS settings assigned by DHCP Disable");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
			xmslocallib.clickLink("DNS");
			xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
			xmslocallib.pause(10);
			xmslocallib.setText("DNS Domain", dnsDomainExp);
			xmslocallib.setText("DNS Server 1", dnsSrvr1Exp);
			xmslocallib.setText("DNS Server 2", dnsSrvr2Exp);
			xmslocallib.setText("DNS Server 3", dnsSrvr3Exp);
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
		boolean guiResult=false,snmpResult=false, cliResult=false;
		xmslocallib.pause(1000);
		//Verification by SNMP, CLI and GUI
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		//get Assigned by DHCP value configured into array from GUI
		boolean byDHCPIPEnable=xmslocallib.isRadioButtonSelected("Use DNS settings assigned by DHCP",1); //Assign by DHCP disable
		if(byDHCPIPEnable)
			dnsByDHCPGUI="on";
		else
			dnsByDHCPGUI="off";
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.3_GUI-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		guiResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPGUI, dnsByDHCPExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("Ref-3.3_SNMP-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		dnsByDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsByDHCPOID);
		snmpResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPSNMP, "0");
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.3_CLI-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		dnsByDHCPCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Use DHCP");
		cliResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPCLI, dnsByDHCPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.3_Verify Use DNS settings assigned by DHCP Disable");
	}
	//Ref-3.4_Verify DNS Domain set value
	@Test(priority=4)
	public void testCase_VerifyDNSDomainSetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.4_Verify DNS Domain set value");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
		//get DNS Domain Name value configured into array from GUI
		dnsDomainGUI=xmslocallib.getText("DNS Domain"); //WiFi Tag Enabled
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.4_GUI-Verification:-Verify DNS Domain set value");
		guiResult=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainGUI, dnsDomainExp);
		//Verify Domain Name configured into array by SNMP
		App_Log.debug("Ref-3.4_SNMP-Verification:-Verify DNS Domain set value");
		dnsDomainSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsDomainOID);
		snmpResult=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainSNMP, dnsDomainExp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.4_CLI-Verification:-Verify DNS Domain set value");
		dnsDomainCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Domain");
		cliResult=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainCLI, dnsDomainExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.4_Verify DNS Domain set value");
	}
	//Ref-3.5_Verify DNS Server 1 set value
	@Test(priority=5)
	public void testCase_VerifyDNSServer1SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.5_Verify DNS Server 1 set value");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr1GUI=xmslocallib.getText("DNS Server 1"); //DNS Server 1
		//Verify DNS Server 1 configured into array by GUI
		App_Log.debug("Ref-3.5_GUI-Verification:-Verify DNS Server 1 set value");
		guiResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1GUI, dnsSrvr1Exp);
		//Verify Domain Server 1 configured into array by SNMP
		App_Log.debug("Ref-3.5_SNMP-Verification:-Verify DNS Server 1 set value");
		dnsSrvr1SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr1OID);
		snmpResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1SNMP, dnsSrvr1Exp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.5_CLI-Verification:-Verify DNS Server 1 set value");
		dnsSrvr1CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 1");
		cliResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1CLI, dnsSrvr1Exp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.5_Verify DNS Server 1 set value");
	}
	//Ref-3.6_Verify DNS Server 2 set value
	@Test(priority=6)
	public void testCase_VerifyDNSServer2SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.6_Verify DNS Server 2 set value");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr2GUI=xmslocallib.getText("DNS Server 2"); //DNS Server 1
		//Verify DNS Server 1 configured into array by GUI
		App_Log.debug("Ref-3.6_GUI-Verification:-Verify DNS Server 2 set value");
		guiResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2GUI, dnsSrvr2Exp);
		//Verify Domain Server 1 configured into array by SNMP
		App_Log.debug("Ref-3.6_SNMP-Verification:-Verify DNS Server 2 set value");
		dnsSrvr2SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr2OID);
		snmpResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2SNMP, dnsSrvr2Exp);
		//Verify DNS Server 2 name configured into Array by CLI
		App_Log.debug("Ref-3.6_CLI-Verification:-Verify DNS Server 2 set value");
		dnsSrvr2CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 2");
		cliResult=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2CLI, dnsSrvr2Exp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.6_Verify DNS Server 2 set value");
	}
	//Ref-3.7_Verify DNS Server 3 set value
	@Test(priority=7)
	public void testCase_VerifyDNSServer3SetValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.7_Verify DNS Server 3 set value");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr3GUI=xmslocallib.getText("DNS Server 3"); //DNS Server 1
		//Verify DNS Server 3 configured into array by GUI
		App_Log.debug("Ref-3.7_GUI-Verification:-Verify DNS Server 3 set value");
		guiResult=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3GUI, dnsSrvr3Exp);
		//Verify Domain Server 3 configured into array by SNMP
		App_Log.debug("Ref-3.7_SNMP-Verification:-Verify DNS Server 3 set value");
		dnsSrvr3SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr3OID);
		snmpResult=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3SNMP, dnsSrvr3Exp);
		//Verify DNS Server 3 name configured into Array by CLI
		App_Log.debug("Ref-3.7_CLI-Verification:-Verify DNS Server 3 set value");
		dnsSrvr3CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 3");
		cliResult=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3CLI, dnsSrvr3Exp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.7_Verify DNS Server 3 set value");
	}
	//Ref-3.8_Verify DNS Domain Field Validation
	@Test(priority=8)
	public void testCase_VerifyDNSDomainFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.8_Verify DNS Domain Field Validation");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Broadcat IP address
		dnsDomainExp="255.255.255.255";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Domain", dnsDomainExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.8_GUI-Verification:-Verify warning message for DNS Domain name should not be broadcast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="DNS Domain is not a valid URL domain";
		boolean dnsDomainNameBrcst=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsDomainNameBrcst){
			xmslocallib.verifyStringequals("DNSDomainNameBroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSDomainNameBroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Broadcat IP address
		dnsDomainExp="224.0.0.2";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Domain", dnsDomainExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.8_GUI-Verification:-Verify warning message for DNS Domain name should not be Multicast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Domain is not a valid URL domain";
		boolean dnsDomainNameMcast=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsDomainNameMcast){
			xmslocallib.verifyStringequals("DNSDomainNameMulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSDomainNameMulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Invalid Domain Name
		dnsDomainExp="$Kartik@";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Domain", dnsDomainExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.8_GUI-Verification:-Verify warning message for DNS Domain name should not be invalid");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Domain is not a valid URL domain";
		boolean dnsDomainNameInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsDomainNameInvalid){
			xmslocallib.verifyStringequals("DNSDomainNameInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSDomainNameInvalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(dnsDomainNameBrcst&&dnsDomainNameMcast&&dnsDomainNameInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.8_Verify DNS Domain Field Validation");
	}
	//Ref-3.9_Verify DNS Server 1 Field Validation
	@Test(priority=9)
	public void testCase_VerifyDNSServer1FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.9_Verify DNS Server 1 Field Validation");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Broadcat IP address
		dnsSrvr1Exp="255.255.255.255";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 1", dnsSrvr1Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify warning message for DNS Domain name should not be broadcast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="DNS Server 1 must be a valid Ip Address";
		boolean dnsServerBrcst=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerBrcst){
			xmslocallib.verifyStringequals("DNSServer1BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer1BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Mutlicast IP address
		dnsSrvr1Exp="224.0.0.25";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 1", dnsSrvr1Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify warning message for DNS Domain name should not be Multicast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 1 must be a valid Ip Address";
		boolean dnsServerMcast=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerMcast){
			xmslocallib.verifyStringequals("DNSServer1MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer1MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Non IP value in address
		dnsSrvr1Exp="kartik.com";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 1", dnsSrvr1Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.9_GUI-Verification:-Verify warning message for DNS Domain name should not be non IP value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 1 must be a valid Ip Address";
		boolean dnsServerInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerInvalid){
			xmslocallib.verifyStringequals("DNSServer1Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer1Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(dnsServerBrcst&&dnsServerMcast&&dnsServerInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.9_Verify DNS Server 1 Field Validation");
	}
	//Ref-3.10_Verify DNS Server 2 Field Validation
	@Test(priority=10)
	public void testCase_VerifyDNSServer2FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.10_Verify DNS Server 2 Field Validation");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Broadcat IP address
		dnsSrvr2Exp="255.255.255.255";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 2", dnsSrvr2Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.10_GUI-Verification:-Verify warning message for DNS Server 2 should not be broadcast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="DNS Server 2 must be a valid Ip Address";
		boolean dnsServerBrcst=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerBrcst){
			xmslocallib.verifyStringequals("DNSServer2BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer2BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Mutlicast IP address
		dnsSrvr2Exp="224.0.0.25";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 2", dnsSrvr2Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS SERVER 2 is not a valid URL domain
		App_Log.debug("Ref-3.10_GUI-Verification:-Verify warning message for DNS Server 2 should not be Multicast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 2 must be a valid Ip Address";
		boolean dnsServerMcast=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerMcast){
			xmslocallib.verifyStringequals("DNSServer2MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer2MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Non IP value in address
		dnsSrvr2Exp="kartik.com";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 2", dnsSrvr2Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.10_GUI-Verification:-Verify warning message for DNS Domain name should not be non IP value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 2 must be a valid Ip Address";
		boolean dnsServerInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerInvalid){
			xmslocallib.verifyStringequals("DNSServer2Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer2Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(dnsServerBrcst&&dnsServerMcast&&dnsServerInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.10_Verify DNS Server 2 Field Validation");
	}
	//Ref-3.11_Verify DNS Server 3 Field Validation
	@Test(priority=11)
	public void testCase_VerifyDNSServer3FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.11_Verify DNS Server 3 Field Validation");
		//*****************************************************************
		dnsByDHCPExp="off";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Broadcat IP address
		dnsSrvr3Exp="255.255.255.255";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 3", dnsSrvr3Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify warning message for DNS Server 3 should not be broadcast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="DNS Server 3 must be a valid Ip Address";
		boolean dnsServerBrcst=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerBrcst){
			xmslocallib.verifyStringequals("DNSServer3BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer3BroadcastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Mutlicast IP address
		dnsSrvr3Exp="224.0.0.25";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 3", dnsSrvr3Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS SERVER 2 is not a valid URL domain
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify warning message for DNS Server 3 should not be Multicast IP");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 3 must be a valid Ip Address";
		boolean dnsServerMcast=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerMcast){
			xmslocallib.verifyStringequals("DNSServer3MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer3MulticastIP", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//Non IP value in address
		dnsSrvr3Exp="kartik.com";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 2);
		xmslocallib.setText("DNS Server 3", dnsSrvr3Exp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message DNS Domain is not a valid URL domain
		App_Log.debug("Ref-3.11_GUI-Verification:-Verify warning message for DNS Domain name should not be non IP value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="DNS Server 3 must be a valid Ip Address";
		boolean dnsServerInvalid=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(dnsServerInvalid){
			xmslocallib.verifyStringequals("DNSServer3Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("DNSServer3Invalid", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(dnsServerBrcst&&dnsServerMcast&&dnsServerInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.11_Verify DNS Server 3 Field Validation");
	}
	//Ref-3.12_Verify Use DNS settings assigned by DHCP Enable
	@Test(priority=12)
	public void testCase_VerifyUseDNSsettingsAssignedByDHCPEnable()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.12_Verify Use DNS settings assigned by DHCP Enable");
		//*****************************************************************
		dnsByDHCPExp="on";
		dnsHostExp=CONFIG.getProperty("ArrayHostName");
		dnsDomainExp=CONFIG.getProperty("DNSDomainName");
		dnsSrvr1Exp=CONFIG.getProperty("DNSSrvr1");
		dnsSrvr2Exp=CONFIG.getProperty("DNSSrvr2");
		dnsSrvr3Exp=CONFIG.getProperty("DNSSrvr3");
		dnsHostOID=SNMPOID.getProperty("HostnameOID");
		dnsByDHCPOID=SNMPOID.getProperty("DNSUseDHCPOID");
		dnsDomainOID=SNMPOID.getProperty("DNSDomainOID");
		dnsSrvr1OID=SNMPOID.getProperty("DNSServer1OID");
		dnsSrvr2OID=SNMPOID.getProperty("DNSServer2OID");
		dnsSrvr3OID=SNMPOID.getProperty("DNSServer3OID");
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
			xmslocallib.clickLink("DNS");
			xmslocallib.selectRadioButton("Use DNS settings assigned by DHCP", 1);
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
		boolean guiResult=false,snmpResult=false, cliResult=false;
		//Verification by SNMP, CLI and GUI
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Network");
		xmslocallib.clickLink("DNS");
		//get Assigned by DHCP value configured into array from GUI
		boolean byDHCPIPEnable=xmslocallib.isRadioButtonSelected("Use DNS settings assigned by DHCP",1); //Assign by DHCP disable
		if(byDHCPIPEnable)
			dnsByDHCPGUI="on";
		else
			dnsByDHCPGUI="off";
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.12_GUI-Verification:-Verify Use DNS settings assigned by DHCP Enable");
		guiResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPGUI, dnsByDHCPExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("Ref-3.12_SNMP-Verification:-Verify Use DNS settings assigned by DHCP Enable");
		dnsByDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsByDHCPOID);
		snmpResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPSNMP, "1");
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.12_CLI-Verification:-Verify Use DNS settings assigned by DHCP Enable");
		dnsByDHCPCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Use DHCP");
		cliResult=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPCLI, dnsByDHCPExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(snmpResult&&cliResult&&guiResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-3.12_Verify Use DNS settings assigned by DHCP Enable");
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
