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
public class TC_ArrayConfig_Network_DNS extends TestSuiteBase{
	//DNS Paramters
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
	public void testCase_VerifyArrayConfigNetworkDNSParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Network DNS Paramters");
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
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//get Assigned by DHCP value configured into array from GUI
		if(xmslocallib.isRadioButtonSelected("Use DNS settings assigned by DHCP",1))dnsByDHCPGUI="on";else dnsByDHCPGUI="off";//Assign by DHCP disable
		//Verify Location Information configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		boolean guibyDHCP=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPGUI, dnsByDHCPExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		dnsByDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsByDHCPOID);
		boolean snmpbyDHCP=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPSNMP, "0");
		//Verify Location information name configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify Use DNS settings assigned by DHCP Disable");
		dnsByDHCPCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Use DHCP");
		boolean clibyDHCP=xmslocallib.verifyStringequals("AssignIPbyDHCP", this.getClass().getSimpleName(), methodName, dnsByDHCPCLI, dnsByDHCPExp);
		//get DNS Domain Name value configured into array from GUI
		dnsDomainGUI=xmslocallib.getText("DNS Domain"); //WiFi Tag Enabled
		//Verify Location Information configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify DNS Domain set value");
		boolean guidomain=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainGUI, dnsDomainExp);
		//Verify Domain Name configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify DNS Domain set value");
		dnsDomainSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsDomainOID);
		boolean snmpdomain=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainSNMP, dnsDomainExp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify DNS Domain set value");
		dnsDomainCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Domain");
		boolean clidomain=xmslocallib.verifyStringequals("DNSDomainName", this.getClass().getSimpleName(), methodName, dnsDomainCLI, dnsDomainExp);
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr1GUI=xmslocallib.getText("DNS Server 1"); //DNS Server 1
		//Verify DNS Server 1 configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify DNS Server 1 set value");
		boolean guisrvr1=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1GUI, dnsSrvr1Exp);
		//Verify Domain Server 1 configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify DNS Server 1 set value");
		dnsSrvr1SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr1OID);
		boolean snmpsrvr1=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1SNMP, dnsSrvr1Exp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify DNS Server 1 set value");
		dnsSrvr1CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 1");
		boolean clisrvr1=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr1CLI, dnsSrvr1Exp);
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr2GUI=xmslocallib.getText("DNS Server 2"); //DNS Server 1
		//Verify DNS Server 1 configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify DNS Server 2 set value");
		boolean guisrvr2=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2GUI, dnsSrvr2Exp);
		//Verify Domain Server 1 configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify DNS Server 2 set value");
		dnsSrvr2SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr2OID);
		boolean snmpsrvr2=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2SNMP, dnsSrvr2Exp);
		//Verify DNS Server 2 name configured into Array by CLI
		App_Log.debug("CLI-Verification:-Verify DNS Server 2 set value");
		dnsSrvr2CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 2");
		boolean clisrvr2=xmslocallib.verifyStringequals("DNSServer1", this.getClass().getSimpleName(), methodName, dnsSrvr2CLI, dnsSrvr2Exp);
		//get DNS Domain Name value configured into array from GUI
		dnsSrvr3GUI=xmslocallib.getText("DNS Server 3"); //DNS Server 1
		//Verify DNS Server 3 configured into array by GUI
		App_Log.debug("Ref-3.7_GUI-Verification:-Verify DNS Server 3 set value");
		boolean guisrvr3=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3GUI, dnsSrvr3Exp);
		//Verify Domain Server 3 configured into array by SNMP
		App_Log.debug("Ref-3.7_SNMP-Verification:-Verify DNS Server 3 set value");
		dnsSrvr3SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsSrvr3OID);
		boolean snmpsrvr3=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3SNMP, dnsSrvr3Exp);
		//Verify DNS Server 3 name configured into Array by CLI
		App_Log.debug("Ref-3.7_CLI-Verification:-Verify DNS Server 3 set value");
		dnsSrvr3CLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Server 3");
		boolean clisrvr3=xmslocallib.verifyStringequals("DNSServer3", this.getClass().getSimpleName(), methodName, dnsSrvr3CLI, dnsSrvr3Exp);
		//get Location Information value configured into array from GUI
		dnsHostGUI = xmslocallib.getText("DNS Hostname");
		//Verify Location Information configured into array by GUI
		App_Log.debug("Ref-3.1_GUI-Verification:-Verify DNS Hostname is not empty by default in array");
		boolean guihost=xmslocallib.verifyStringequals("DNSHostname", this.getClass().getSimpleName(), methodName, dnsHostGUI, dnsHostExp);
		//Verify Location Information configured into array by SNMP
		App_Log.debug("Ref-3.1_SNMP-Verification:-Verify DNS Hostname is not empty by default in array");
		dnsHostSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dnsHostOID);
		boolean snmphost=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, dnsHostSNMP, dnsHostExp);
		//Verify Location information name configured into Array by CLI
		App_Log.debug("Ref-3.1_CLI-Verification:-Verify DNS Hostname is not empty by default in array");
		dnsHostCLI=xmslocallib.getValuebyCLI_DNS(arrayIPadd, "Hostname");
		boolean clihost=xmslocallib.verifyStringequals("ArrayHostname", this.getClass().getSimpleName(), methodName, dnsHostCLI, dnsHostExp);
	
		App_Log.debug("End-Verify Array Config Network DNS Paramters");		
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
