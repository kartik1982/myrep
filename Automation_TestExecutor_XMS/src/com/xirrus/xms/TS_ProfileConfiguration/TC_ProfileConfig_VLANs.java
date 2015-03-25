package com.xirrus.xms.TS_ProfileConfiguration;

import java.io.IOException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_ProfileConfig_VLANs extends TestSuiteBase{
	private String dhcpEnableGUI;
	private String dhcpNameGUI;
	private String dhcpNATEnableGUI;
	private String dhcpDefaultLeaseGUI;
	private String dhcpMaxLeaseGUI;
	private String dhcpStartIPGUI;
	private String dhcpEndIPGUI;
	private String dhcpMaskGUI;
	private String dhcpGatewayGUI;
	private String dhcpDomainGUI;
	private String dhcpDNS1GUI;
	private String dhcpDNS2GUI;
	private String dhcpDNS3GUI;
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
	private String dhcpEnableCLI;
	private String dhcpNameCLI;
	private String dhcpNATEnableCLI;
	private String dhcpDefaultLeaseCLI;
	private String dhcpMaxLeaseCLI;
	private String dhcpStartIPCLI;
	private String dhcpEndIPCLI;
	private String dhcpMaskCLI;
	private String dhcpGatewayCLI;
	private String dhcpDomainCLI;
	private String dhcpDNS1CLI;
	private String dhcpDNS2CLI;
	private String dhcpDNS3CLI;
	private String dhcpEnableSNMP;
	private String dhcpNameSNMP;
	private String dhcpNATEnableSNMP;
	private String dhcpDefaultLeaseSNMP;
	private String dhcpMaxLeaseSNMP;
	private String dhcpStartIPSNMP;
	private String dhcpEndIPSNMP;
	private String dhcpMaskSNMP;
	private String dhcpGatewaySNMP;
	private String dhcpDomainSNMP;
	private String dhcpDNS1SNMP;
	private String dhcpDNS2SNMP;
	private String dhcpDNS3SNMP;
	private String dhcpEnableOID;
	private String dhcpNameOID;
	private String dhcpNATEnableOID;
	private String dhcpDefaultLeaseOID;
	private String dhcpMaxLeaseOID;
	private String dhcpStartIPOID;
	private String dhcpEndIPOID;
	private String dhcpMaskOID;
	private String dhcpGatewayOID;
	private String dhcpDomainOID;
	private String dhcpDNS1OID;
	private String dhcpDNS2OID;
	private String dhcpDNS3OID;
	
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
		if(!Test_Utility.isTestCaseRunnable(suite_ProfileConfiguration_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-4.1_Verify DHCP Server Default Values
	@Test(priority=1)
	public void testCase_verifyDHCPServerDefaultValues()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.1_Verify DHCP Server Default Values");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="";
		dhcpNATEnableExp="disabled";
		dhcpDefaultLeaseExp="300";
		dhcpMaxLeaseExp="300";
		dhcpStartIPExp="192.168.2.2";
		dhcpEndIPExp="192.168.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.168.2.1";
		dhcpDomainExp="";
		dhcpDNS1Exp="";
		dhcpDNS2Exp="";
		dhcpDNS3Exp="";
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
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.clickButton("Add");
			if (! xmslocallib.findText("Add DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}			

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-4.1.1_GUI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool
		if(xmslocallib.isCheckboxSelected("Enabled"))dhcpEnableGUI="enabled"; else dhcpEnableGUI="disabled";
		boolean guiResult1=xmslocallib.verifyStringequals("DHCPEnableState", this.getClass().getSimpleName(), methodName, dhcpEnableGUI, dhcpEnableExp);
		App_Log.debug("Ref-4.1.2_GUI-Verification:-Verify Name for DHCP Server empty by Default");
		//Get value for DHCP Server name from GUI
		dhcpNameGUI= xmslocallib.getText("Name");
		boolean guiResult2=xmslocallib.verifyStringequals("DHCPServerName", this.getClass().getSimpleName(), methodName, dhcpNameGUI, dhcpNameExp);
		App_Log.debug("Ref-4.1.3_GUI-Verification:-Verify NAT Enable for DHCP Server Clear by Default");
		//get value for DHCP Server NAT State Default in Add DHCP Pool
		if(xmslocallib.isCheckboxSelected("NAT Enabled"))dhcpNATEnableGUI="enabled"; else dhcpNATEnableGUI="disabled";
		boolean guiResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableState", this.getClass().getSimpleName(), methodName, dhcpNATEnableGUI, dhcpNATEnableExp);
		App_Log.debug("Ref-4.1.4_GUI-Verification:-Verify Default Lease for DHCP Server by Default");
		//Get value for DHCP Server Default Lease from GUI
		dhcpDefaultLeaseGUI= xmslocallib.getText("Default Lease (sec)");
		boolean guiResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLease", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseGUI, dhcpDefaultLeaseExp);
		App_Log.debug("Ref-4.1.5_GUI-Verification:-Verify Max Lease for DHCP Server Clear by Default");
		//Get value for DHCP Server Max Lease from GUI
		dhcpMaxLeaseGUI= xmslocallib.getText("Max Lease (sec)");
		boolean guiResult5=xmslocallib.verifyStringequals("DHCPServerMaxLease", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseGUI, dhcpMaxLeaseExp);
		App_Log.debug("Ref-4.1.6_GUI-Verification:-Verify Start IP Range for DHCP Server by Default 192.168.2.2");
		//Get value for DHCP Server Start IP Range from GUI
		dhcpStartIPGUI= xmslocallib.getText("Start IP Range");
		boolean guiResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRange", this.getClass().getSimpleName(), methodName, dhcpStartIPGUI, dhcpStartIPExp);
		App_Log.debug("Ref-4.1.7_GUI-Verification:-Verify End IP Range for DHCP Server by Default 192.168.2.254");
		//Get value for DHCP Server End IP Range from GUI
		dhcpEndIPGUI= xmslocallib.getText("End IP Range");
		boolean guiResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRange", this.getClass().getSimpleName(), methodName, dhcpEndIPGUI, dhcpEndIPExp);
		App_Log.debug("Ref-4.1.8_GUI-Verification:-Verify Default Subnet Mask for DHCP Server by Default 255.255.255.0");
		//Get value for DHCP Server Default Subnet Mask from GUI
		dhcpMaskGUI= xmslocallib.getText("Default Subnet Mask");
		boolean guiResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMask", this.getClass().getSimpleName(), methodName, dhcpMaskGUI, dhcpMaskExp);
		App_Log.debug("Ref-4.1.9_GUI-Verification:-Verify Gatway for DHCP Server by Default 192.168.2.1");
		//Get value for DHCP Server Gateway from GUI
		dhcpGatewayGUI= xmslocallib.getText("Gateway");
		boolean guiResult9=xmslocallib.verifyStringequals("DHCPServerGateway", this.getClass().getSimpleName(), methodName, dhcpGatewayGUI, dhcpGatewayExp);
		App_Log.debug("Ref-4.1.10_GUI-Verification:-Verify Default Domain for DHCP Server empty by Default");
		//Get value for DHCP Server Default Domain from GUI
		dhcpDomainGUI= xmslocallib.getText("Default Domain");
		boolean guiResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomain", this.getClass().getSimpleName(), methodName, dhcpDomainGUI, dhcpDomainExp);
		App_Log.debug("Ref-4.1.11_GUI-Verification:-Verify Default DNS Server 1 for DHCP Server empty by Default");
		//Get value for DHCP Server Default DNS server 1 from GUI
		dhcpDNS1GUI= xmslocallib.getText("Default DNS Server 1");
		boolean guiResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomain", this.getClass().getSimpleName(), methodName, dhcpDNS1GUI, dhcpDNS1Exp);
		App_Log.debug("Ref-4.1.12_GUI-Verification:-Verify Default DNS Server 2 for DHCP Server empty by Default");
		//Get value for DHCP Server Default DNS server 2 from GUI
		dhcpDNS2GUI= xmslocallib.getText("Default DNS Server 2");
		boolean guiResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomain", this.getClass().getSimpleName(), methodName, dhcpDNS2GUI, dhcpDNS2Exp);
		App_Log.debug("Ref-4.1.13_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server empty by Default");
		//Get value for DHCP Server Default DNS server 3 from GUI
		dhcpDNS3GUI= xmslocallib.getText("Default DNS Server 3");
		boolean guiResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomain", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6&&guiResult7&&guiResult8&&guiResult9&&guiResult10&&guiResult11&&guiResult12&&guiResult13)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.1_Verify DHCP Server Default Values");
	}
	//Ref-4.2_Verify Add DHCP Server
	@Test(priority=2)
	public void testCase_verifyAddDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.2_Verify Add DHCP Server");
		//*****************************************************************
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
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.clickButton("Add");
			if (! xmslocallib.findText("Add DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}	
			xmslocallib.selectCheckBox("Enabled");
			xmslocallib.setText("Name", dhcpNameExp);
			xmslocallib.selectCheckBox("NAT Enabled");
			xmslocallib.setText("Default Lease (sec)", dhcpDefaultLeaseExp);
			xmslocallib.setText("Max Lease (sec)", dhcpMaxLeaseExp);
			xmslocallib.setText("Start IP Range", dhcpStartIPExp);
			xmslocallib.setText("End IP Range", dhcpEndIPExp);
			xmslocallib.setText("Default Subnet Mask", dhcpMaskExp);
			xmslocallib.setText("Gateway", dhcpGatewayExp);
			xmslocallib.setText("Default Domain", dhcpDomainExp);
			xmslocallib.setText("Default DNS Server 1", dhcpDNS1Exp);
			xmslocallib.setText("Default DNS Server 2", dhcpDNS2Exp);
			xmslocallib.setText("Default DNS Server 3", dhcpDNS3Exp);
			xmslocallib.clickButton("OK");
			if (xmslocallib.findText("Add DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}	

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
		//Verify DHCP server is successfully created
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			if (! xmslocallib.findText("Edit DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}			

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-4.2.1_GUI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by GUI
		if(xmslocallib.isCheckboxSelected("Enabled"))dhcpEnableGUI="enabled"; else dhcpEnableGUI="disabled";
		boolean guiResult1=xmslocallib.verifyStringequals("DHCPEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpEnableGUI, dhcpEnableExp);
		App_Log.debug("Ref-4.2.1_CLI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by CLI
		dhcpEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "State");
		boolean cliResult1=xmslocallib.verifyStringequals("DHCPEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpEnableCLI, dhcpEnableExp);
		App_Log.debug("Ref-4.2.1_SNMP-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by SNMP
		dhcpEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEnableOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("DHCPEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpEnableSNMP, "1");
		
		App_Log.debug("Ref-4.2.2_GUI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from GUI
		dhcpNameGUI= xmslocallib.getText("Name");	//need to find out way to read DHCP host name
		boolean guiResult2=xmslocallib.verifyStringequals("DHCPServerNameGUI", this.getClass().getSimpleName(), methodName, dhcpNameExp, dhcpNameExp);
		App_Log.debug("Ref-4.2.2_CLI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from CLI
		dhcpNameCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DHCP Pool");
		boolean cliResult2=xmslocallib.verifyStringequals("DHCPServerNameCLI", this.getClass().getSimpleName(), methodName, dhcpNameCLI, dhcpNameExp);
		App_Log.debug("Ref-4.2.2_SNMP-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name by SNMP
		dhcpNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNameOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("DHCPServerNameSNMP", this.getClass().getSimpleName(), methodName, dhcpNameSNMP, dhcpNameExp);
		
		
		App_Log.debug("Ref-4.2.3_GUI-Verification:-Verify NAT Enable for DHCP");
		//get value for DHCP Server NAT State by GUI
		if(xmslocallib.isCheckboxSelected("NAT Enabled"))dhcpNATEnableGUI="enabled"; else dhcpNATEnableGUI="disabled";
		boolean guiResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpNATEnableGUI, dhcpNATEnableExp);
		App_Log.debug("Ref-4.2.3_CLI-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by CLI
		dhcpNATEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "NAT");
		boolean cliResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpNATEnableCLI, dhcpNATEnableExp);
		App_Log.debug("Ref-4.2.3_SNMP-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by SNMP
		dhcpNATEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNATEnableOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpNATEnableSNMP, "1");

		
		App_Log.debug("Ref-4.2.4_GUI-Verification:-Verify Default Lease for DHCP Server");
		//Get value for DHCP Server Default Lease from GUI
		dhcpDefaultLeaseGUI= xmslocallib.getText("Default Lease (sec)");
		boolean guiResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseGUI, dhcpDefaultLeaseExp);
		App_Log.debug("Ref-4.2.4_CLI-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by CLI
		dhcpDefaultLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Default lease time").replace("seconds", "").trim();
		boolean cliResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseCLI, dhcpDefaultLeaseExp);
		App_Log.debug("Ref-4.2.4_SNMP-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by SNMP
		dhcpDefaultLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDefaultLeaseOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseSNMP, dhcpDefaultLeaseExp);
				
		
		App_Log.debug("Ref-4.2.5_GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by GUI
		dhcpMaxLeaseGUI= xmslocallib.getText("Max Lease (sec)");
		boolean guiResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseGUI, dhcpMaxLeaseExp);
		App_Log.debug("Ref-4.2.5_GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by CLI
		dhcpMaxLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Maximum lease time").replace("seconds", "").trim();
		boolean cliResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseCLI, dhcpMaxLeaseExp);
		App_Log.debug("Ref-4.2.5_GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by SNMP
		dhcpMaxLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaxLeaseOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseSNMP, dhcpMaxLeaseExp);
		
		
		App_Log.debug("Ref-4.2.6_GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by GUI
		dhcpStartIPGUI= xmslocallib.getText("Start IP Range");
		boolean guiResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpStartIPGUI, dhcpStartIPExp);
		App_Log.debug("Ref-4.2.6_GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by CLI
		dhcpStartIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range start");
		boolean cliResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpStartIPCLI, dhcpStartIPExp);
		App_Log.debug("Ref-4.2.6_GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by SNMP
		dhcpStartIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpStartIPOID);
		boolean snmpResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpStartIPSNMP, dhcpStartIPExp);
		
		
		App_Log.debug("Ref-4.2.7_GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by GUI
		dhcpEndIPGUI= xmslocallib.getText("End IP Range");
		boolean guiResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpEndIPGUI, dhcpEndIPExp);
		App_Log.debug("Ref-4.2.7_GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by CLI
		dhcpEndIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range end");
		boolean cliResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpEndIPCLI, dhcpEndIPExp);
		App_Log.debug("Ref-4.2.7_GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by SNMP
		dhcpEndIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEndIPOID);
		boolean snmpResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpEndIPSNMP, dhcpEndIPExp);
		
		App_Log.debug("Ref-4.2.8_GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by GUI
		dhcpMaskGUI= xmslocallib.getText("Default Subnet Mask");
		boolean guiResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskGU", this.getClass().getSimpleName(), methodName, dhcpMaskGUI, dhcpMaskExp);
		App_Log.debug("Ref-4.2.8_GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by CLI
		dhcpMaskCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP mask");
		boolean cliResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskCLI", this.getClass().getSimpleName(), methodName, dhcpMaskCLI, dhcpMaskExp);
		App_Log.debug("Ref-4.2.8_GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by SNMP
		dhcpMaskSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaskOID);
		boolean snmpResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskSNMP", this.getClass().getSimpleName(), methodName, dhcpMaskSNMP, dhcpMaskExp);
	
		App_Log.debug("Ref-4.2.9_GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by GUI
		dhcpGatewayGUI= xmslocallib.getText("Gateway");
		boolean guiResult9=xmslocallib.verifyStringequals("DHCPServerGatewayGUI", this.getClass().getSimpleName(), methodName, dhcpGatewayGUI, dhcpGatewayExp);
		App_Log.debug("Ref-4.2.9_GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by CLI
		dhcpGatewayCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP gateway");
		boolean cliResult9=xmslocallib.verifyStringequals("DHCPServerGatewayCLI", this.getClass().getSimpleName(), methodName, dhcpGatewayCLI, dhcpGatewayExp);
		App_Log.debug("Ref-4.2.9_GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by SNMP
		dhcpGatewaySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpGatewayOID);
		boolean snmpResult9=xmslocallib.verifyStringequals("DHCPServerGatewaySNMP", this.getClass().getSimpleName(), methodName, dhcpGatewaySNMP, dhcpGatewayExp);
		
		App_Log.debug("Ref-4.2.10_GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by GUI
		dhcpDomainGUI= xmslocallib.getText("Default Domain");
		boolean guiResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDomainGUI, dhcpDomainExp);
		App_Log.debug("Ref-4.2.10_GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by CLI
		dhcpDomainCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Domain name");
		boolean cliResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDomainCLI, dhcpDomainExp);
		App_Log.debug("Ref-4.2.10_GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by SNMP
		dhcpDomainSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDomainOID);
		boolean snmpResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDomainSNMP, dhcpDomainExp);
		
		App_Log.debug("Ref-4.2.11_GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by GUI
		dhcpDNS1GUI= xmslocallib.getText("Default DNS Server 1");
		boolean guiResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS1GUI, dhcpDNS1Exp);
		App_Log.debug("Ref-4.2.11_GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by CLI
		dhcpDNS1CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 1");
		boolean cliResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS1CLI, dhcpDNS1Exp);
		App_Log.debug("Ref-4.2.11_GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by SNMP
		dhcpDNS1SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS1OID);
		boolean snmpResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS1SNMP, dhcpDNS1Exp);
		
		App_Log.debug("Ref-4.2.12_GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by GUI
		dhcpDNS2GUI= xmslocallib.getText("Default DNS Server 2");
		boolean guiResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS2GUI, dhcpDNS2Exp);
		App_Log.debug("Ref-4.2.12_GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by CLI
		dhcpDNS2CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 2");
		boolean cliResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS2CLI, dhcpDNS2Exp);
		App_Log.debug("Ref-4.2.12_GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by SNMP
		dhcpDNS2SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS2OID);
		boolean snmpResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS2SNMP, dhcpDNS2Exp);

		App_Log.debug("Ref-4.2.13_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by GUI
		dhcpDNS3GUI= xmslocallib.getText("Default DNS Server 3");
		boolean guiResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		App_Log.debug("Ref-4.2.13_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by CLI
		dhcpDNS3CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 3");
		boolean cliResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS3CLI, dhcpDNS3Exp);
		App_Log.debug("Ref-4.2.13_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by SNMP
		dhcpDNS3SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS3OID);
		boolean snmpResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		//Close if DHCP Server window is open		
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		guiResult1=guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6&&guiResult7&&guiResult8&&guiResult9&&guiResult10&&guiResult11&&guiResult12&&guiResult13;
		cliResult1=cliResult1&&cliResult2&&guiResult3&&cliResult4&&cliResult5&&cliResult6&&cliResult7&&cliResult8&&cliResult9&&cliResult10&&cliResult11&&cliResult12&&cliResult13;
		snmpResult1=snmpResult1&&snmpResult2&&guiResult3&&snmpResult4&&snmpResult5&&snmpResult6&&snmpResult7&&snmpResult8&&snmpResult9&&snmpResult10&&snmpResult11&&snmpResult12&&snmpResult13;
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.2_Verify Add DHCP Server");
	}
	//Ref-4.3_Verify Edit DHCP Server
	@Test(priority=3)
	public void testCase_verifyEditDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.3_Verify Edit DHCP Server");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			if (! xmslocallib.findText("Edit DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}	
			xmslocallib.unSelectCheckBox("Enabled");
			//xmslocallib.setText("Name", dhcpNameExp);
			xmslocallib.selectCheckBox("NAT Enabled");
			xmslocallib.setText("Default Lease (sec)", dhcpDefaultLeaseExp);
			xmslocallib.setText("Max Lease (sec)", dhcpMaxLeaseExp);
			xmslocallib.setText("Start IP Range", dhcpStartIPExp);
			xmslocallib.setText("End IP Range", dhcpEndIPExp);
			xmslocallib.setText("Default Subnet Mask", dhcpMaskExp);
			xmslocallib.setText("Gateway", dhcpGatewayExp);
			xmslocallib.setText("Default Domain", dhcpDomainExp);
			xmslocallib.setText("Default DNS Server 1", dhcpDNS1Exp);
			xmslocallib.setText("Default DNS Server 2", dhcpDNS2Exp);
			xmslocallib.setText("Default DNS Server 3", dhcpDNS3Exp);
			xmslocallib.clickButton("OK");
			if (xmslocallib.findText("Edit DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}	

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
		//Verify DHCP server is successfully created
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			if (! xmslocallib.findText("Edit DHCP Pool")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}			

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		App_Log.debug("Ref-4.3.1_GUI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by GUI
		if(xmslocallib.isCheckboxSelected(2))dhcpEnableGUI="enabled"; else dhcpEnableGUI="disabled";
		boolean guiResult1=xmslocallib.verifyStringequals("DHCPEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpEnableGUI, dhcpEnableExp);
		App_Log.debug("Ref-4.3.1_CLI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by CLI
		dhcpEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "State");
		boolean cliResult1=xmslocallib.verifyStringequals("DHCPEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpEnableCLI, dhcpEnableExp);
		App_Log.debug("Ref-4.3.1_SNMP-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by SNMP
		dhcpEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEnableOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("DHCPEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpEnableSNMP, "0");
		
		App_Log.debug("Ref-4.3.2_GUI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from GUI
		dhcpNameGUI= xmslocallib.getText("Name");	//need to find out way to read DHCP host name
		boolean guiResult2=xmslocallib.verifyStringequals("DHCPServerNameGUI", this.getClass().getSimpleName(), methodName, dhcpNameExp, dhcpNameExp);
		App_Log.debug("Ref-4.3.2_CLI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from CLI
		dhcpNameCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DHCP Pool");
		boolean cliResult2=xmslocallib.verifyStringequals("DHCPServerNameCLI", this.getClass().getSimpleName(), methodName, dhcpNameCLI, dhcpNameExp);
		App_Log.debug("Ref-4.3.2_SNMP-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name by SNMP
		dhcpNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNameOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("DHCPServerNameSNMP", this.getClass().getSimpleName(), methodName, dhcpNameSNMP, dhcpNameExp);
		
		
		App_Log.debug("Ref-4.3.3_GUI-Verification:-Verify NAT Enable for DHCP");
		//get value for DHCP Server NAT State by GUI
		if(xmslocallib.isCheckboxSelected("NAT Enabled"))dhcpNATEnableGUI="enabled"; else dhcpNATEnableGUI="disabled";
		boolean guiResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpNATEnableGUI, dhcpNATEnableExp);
		App_Log.debug("Ref-4.3.3_CLI-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by CLI
		dhcpNATEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "NAT");
		boolean cliResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpNATEnableCLI, dhcpNATEnableExp);
		App_Log.debug("Ref-4.3.3_SNMP-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by SNMP
		dhcpNATEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNATEnableOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpNATEnableSNMP, "1");

		
		App_Log.debug("Ref-4.3.4_GUI-Verification:-Verify Default Lease for DHCP Server");
		//Get value for DHCP Server Default Lease from GUI
		dhcpDefaultLeaseGUI= xmslocallib.getText("Default Lease (sec)");
		boolean guiResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseGUI, dhcpDefaultLeaseExp);
		App_Log.debug("Ref-4.3.4_CLI-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by CLI
		dhcpDefaultLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Default lease time").replace("seconds", "").trim();
		boolean cliResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseCLI, dhcpDefaultLeaseExp);
		App_Log.debug("Ref-4.3.4_SNMP-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by SNMP
		dhcpDefaultLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDefaultLeaseOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseSNMP, dhcpDefaultLeaseExp);
				
		
		App_Log.debug("Ref-4.3.5_GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by GUI
		dhcpMaxLeaseGUI= xmslocallib.getText("Max Lease (sec)");
		boolean guiResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseGUI, dhcpMaxLeaseExp);
		App_Log.debug("Ref-4.3.5_CLI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by CLI
		dhcpMaxLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Maximum lease time").replace("seconds", "").trim();
		boolean cliResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseCLI, dhcpMaxLeaseExp);
		App_Log.debug("Ref-4.3.5_SNMP-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by SNMP
		dhcpMaxLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaxLeaseOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseSNMP, dhcpMaxLeaseExp);
		
		
		App_Log.debug("Ref-4.3.6_GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by GUI
		dhcpStartIPGUI= xmslocallib.getText("Start IP Range");
		boolean guiResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpStartIPGUI, dhcpStartIPExp);
		App_Log.debug("Ref-4.3.6_CLI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by CLI
		dhcpStartIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range start");
		boolean cliResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpStartIPCLI, dhcpStartIPExp);
		App_Log.debug("Ref-4.3.6_SNMP-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by SNMP
		dhcpStartIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpStartIPOID);
		boolean snmpResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpStartIPSNMP, dhcpStartIPExp);
		
		
		App_Log.debug("Ref-4.3.7_GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by GUI
		dhcpEndIPGUI= xmslocallib.getText("End IP Range");
		boolean guiResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpEndIPGUI, dhcpEndIPExp);
		App_Log.debug("Ref-4.3.7_CLI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by CLI
		dhcpEndIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range end");
		boolean cliResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpEndIPCLI, dhcpEndIPExp);
		App_Log.debug("Ref-4.3.7_SNMP-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by SNMP
		dhcpEndIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEndIPOID);
		boolean snmpResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpEndIPSNMP, dhcpEndIPExp);
		
		App_Log.debug("Ref-4.3.8_GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by GUI
		dhcpMaskGUI= xmslocallib.getText("Default Subnet Mask");
		boolean guiResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskGU", this.getClass().getSimpleName(), methodName, dhcpMaskGUI, dhcpMaskExp);
		App_Log.debug("Ref-4.3.8_CLI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by CLI
		dhcpMaskCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP mask");
		boolean cliResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskCLI", this.getClass().getSimpleName(), methodName, dhcpMaskCLI, dhcpMaskExp);
		App_Log.debug("Ref-4.3.8_SNMP-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by SNMP
		dhcpMaskSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaskOID);
		boolean snmpResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskSNMP", this.getClass().getSimpleName(), methodName, dhcpMaskSNMP, dhcpMaskExp);
	
		App_Log.debug("Ref-4.3.9_GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by GUI
		dhcpGatewayGUI= xmslocallib.getText("Gateway");
		boolean guiResult9=xmslocallib.verifyStringequals("DHCPServerGatewayGUI", this.getClass().getSimpleName(), methodName, dhcpGatewayGUI, dhcpGatewayExp);
		App_Log.debug("Ref-4.3.9_CLI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by CLI
		dhcpGatewayCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP gateway");
		boolean cliResult9=xmslocallib.verifyStringequals("DHCPServerGatewayCLI", this.getClass().getSimpleName(), methodName, dhcpGatewayCLI, dhcpGatewayExp);
		App_Log.debug("Ref-4.3.9_SNMP-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by SNMP
		dhcpGatewaySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpGatewayOID);
		boolean snmpResult9=xmslocallib.verifyStringequals("DHCPServerGatewaySNMP", this.getClass().getSimpleName(), methodName, dhcpGatewaySNMP, dhcpGatewayExp);
		
		App_Log.debug("Ref-4.3.10_GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by GUI
		dhcpDomainGUI= xmslocallib.getText("Default Domain");
		boolean guiResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDomainGUI, dhcpDomainExp);
		App_Log.debug("Ref-4.3.10_CLI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by CLI
		dhcpDomainCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Domain name");
		boolean cliResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDomainCLI, dhcpDomainExp);
		App_Log.debug("Ref-4.3.10_SNMP-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by SNMP
		dhcpDomainSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDomainOID);
		boolean snmpResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDomainSNMP, dhcpDomainExp);
		
		App_Log.debug("Ref-4.3.11_GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by GUI
		dhcpDNS1GUI= xmslocallib.getText("Default DNS Server 1");
		boolean guiResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS1GUI, dhcpDNS1Exp);
		App_Log.debug("Ref-4.3.11_CLI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by CLI
		dhcpDNS1CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 1");
		boolean cliResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS1CLI, dhcpDNS1Exp);
		App_Log.debug("Ref-4.3.11_SNMP-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by SNMP
		dhcpDNS1SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS1OID);
		boolean snmpResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS1SNMP, dhcpDNS1Exp);
		
		App_Log.debug("Ref-4.3.12_GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by GUI
		dhcpDNS2GUI= xmslocallib.getText("Default DNS Server 2");
		boolean guiResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS2GUI, dhcpDNS2Exp);
		App_Log.debug("Ref-4.3.12_CLI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by CLI
		dhcpDNS2CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 2");
		boolean cliResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS2CLI, dhcpDNS2Exp);
		App_Log.debug("Ref-4.3.12_SNMP-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by SNMP
		dhcpDNS2SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS2OID);
		boolean snmpResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS2SNMP, dhcpDNS2Exp);

		App_Log.debug("Ref-4.3.13_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by GUI
		dhcpDNS3GUI= xmslocallib.getText("Default DNS Server 3");
		boolean guiResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		App_Log.debug("Ref-4.3.13_CLI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by CLI
		dhcpDNS3CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 3");
		boolean cliResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS3CLI, dhcpDNS3Exp);
		App_Log.debug("Ref-4.3.13_SNMP-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by SNMP
		dhcpDNS3SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS3OID);
		boolean snmpResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		//Close if DHCP Server window is open		
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		guiResult1=guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6&&guiResult7&&guiResult8&&guiResult9&&guiResult10&&guiResult11&&guiResult12&&guiResult13;
		cliResult1=cliResult1&&cliResult2&&guiResult3&&cliResult4&&cliResult5&&cliResult6&&cliResult7&&cliResult8&&cliResult9&&cliResult10&&cliResult11&&cliResult12&&cliResult13;
		snmpResult1=snmpResult1&&snmpResult2&&guiResult3&&snmpResult4&&snmpResult5&&snmpResult6&&snmpResult7&&snmpResult8&&snmpResult9&&snmpResult10&&snmpResult11&&snmpResult12&&snmpResult13;
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-4.3_Verify Edit DHCP Server");
	}
	//Ref-4.4_Verify Delete DHCP Server
	@Test(priority=4)
	public void testCase_verifyDeleteDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.4_Verify Delete DHCP Server");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Delete");
			xmslocallib.pause(10);
			xmslocallib.clickButton("OK");
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
			xmslocallib.clickLink("Services");
			xmslocallib.clickLink("DHCP Server");
			App_Log.debug("Ref-4.4_GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
			//Get Default DNS Server 3 for DHCP Server by GUI
			if(!xmslocallib.findText(dhcpNameExp)) dhcpNameGUI= "DHCP Server Deleted"; else dhcpNameGUI= "DHCP Server NOT Deleted";
			boolean guiResult=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpNameGUI, "DHCP Server Deleted");
			App_Log.debug("Ref-4.4_CLI-Verification:-Verify Default DNS Server 3 for DHCP Server");
			//Get Default DNS Server 3 for DHCP Server by CLI
			dhcpNameCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, "NODHCP", "DNS Server 3");
			boolean cliResult=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpNameCLI, "No DHCP Pools defined.");
			App_Log.debug("Ref-4.4_SNMP-Verification:-Verify DHCP Server Deleted");
			//Get Default DNS Server 3 for DHCP Server by SNMP
			dhcpNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNameOID);
			if(dhcpNameSNMP.contains("noSuchInstance")) dhcpNameSNMP="DHCP Server Deleted"; else dhcpNameSNMP="DHCP Server NOT Deleted";
			boolean snmpResult=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpNameSNMP, "DHCP Server Deleted");
			if(guiResult&&cliResult&&snmpResult)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.4_Verify Delete DHCP Server");
	}
	//Ref-4.5_Verify DHCP Server Name Field Validation
	@Test(priority=5)
	public void testCase_verifyDHCPServerNameFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.5_Verify DHCP Server Name Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="12345678901234567890";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
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
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.clickButton("Add");
			xmslocallib.pause(10);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify warning message	
		App_Log.debug("Ref-4.5_GUI-Verification:-DHCP Server Name Field should not be empty");
		xmslocallib.clickButton("OK");
		String warningmessageExp="DHCP pool name is required."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct="DHCP pool name is required."; else warningmessageAct="Not Displayed warning message";
		boolean dhcpNameemptyWarn=xmslocallib.verifyStringequals("DHCPNameEmptyWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		App_Log.debug("Ref-4.5_GUI-Verification:-DHCP Server Name Field accept only 20 char");
		xmslocallib.setText("Name", "1234567890123456789012");
		dhcpNameGUI= xmslocallib.getText("Name");
		 //need to implement function to read span
		boolean dhcpNameChar=xmslocallib.verifyStringequals("DHCPName20Char", this.getClass().getSimpleName(), methodName, dhcpNameGUI, dhcpNameExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");		
		if(dhcpNameemptyWarn&&dhcpNameChar)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-4.5_Verify DHCP Server Name Field Validation");
	}
	//Ref-4.6_Verify DHCP Server Default Lease Field Validation
	@Test(priority=6)
	public void testCase_verifyDHCPServerDefaultLeaseFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.6_Verify DHCP Server Default Lease Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="12345678901234567890";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
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
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.clickButton("Add");
			xmslocallib.pause(10);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify warning message	
		App_Log.debug("Ref-4.6_GUI-Verification:-Default Lease period lower limit 60 sec");
		xmslocallib.setText("Default Lease (sec)","59");//lower limit
		xmslocallib.clickButton("OK");
		String warningmessageExp="Default lease time must be between 60 and 3000000"; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean defaultLeaselowlimit=xmslocallib.verifyStringequals("DefaultLeaseLowerLimitwarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		App_Log.debug("Ref-4.6_GUI-Verification:-Default Lease period Upper limit 3000000 sec");
		xmslocallib.setText("Default Lease (sec)","3000001");//Upper limit
		xmslocallib.clickButton("OK");
		warningmessageExp="Default lease time must be between 60 and 3000000"; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean defaultLeaseuplimit=xmslocallib.verifyStringequals("DefaultLeaseUpperLimitwarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");		
		if(defaultLeaselowlimit&&defaultLeaseuplimit)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.6_Verify DHCP Server Default Lease Field Validation");
	}
	//Ref-4.7_Verify DHCP Server Max Lease Field Validation
	@Test(priority=7)
	public void testCase_verifyDHCPServerMaxLeaseFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.7_Verify DHCP Server Max Lease Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="12345678901234567890";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
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
			xmslocallib.clickLink("DHCP Server");
			xmslocallib.clickButton("Add");
			xmslocallib.pause(10);
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verify warning message	
		App_Log.debug("Ref-4.7_GUI-Verification:-Max Lease period lower limit 60 sec");
		xmslocallib.setText("Max Lease (sec)","59");//lower limit
		xmslocallib.clickButton("OK");
		String warningmessageExp="Max lease time must be between 60 and 3000000"; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean maxLeaselowlimit=xmslocallib.verifyStringequals("MaxLeaseLowerLimitwarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		App_Log.debug("Ref-4.7_GUI-Verification:-Max Lease period Upper limit 3000000 sec");
		xmslocallib.setText("Max Lease (sec)","3000001");//Upper limit
		xmslocallib.clickButton("OK");
		warningmessageExp="Max lease time must be between 60 and 3000000"; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean maxLeaseuplimit=xmslocallib.verifyStringequals("MaxLeaseUpperLimitwarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");		
		if(maxLeaselowlimit&&maxLeaseuplimit)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-4.7_Verify DHCP Server Max Lease Field Validation");
	}
	//Ref-4.8_Verify DHCP Server Start IP Range Field Validation
	@Test(priority=8)
	public void testCase_verifyDHCPServerStartIPRangeFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.8_Verify DHCP Server Start IP Range Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message		
		App_Log.debug("Ref-4.8_GUI-Verification:-Verify Start IP Range should not be empty");
		xmslocallib.setText("Start IP Range", "");
		xmslocallib.clickButton("OK");
		String warningmessageExp="Start IP is required."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean startIPEmpty=xmslocallib.verifyStringequals("DHCPStartIPEmptyWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		App_Log.debug("Ref-4.8_GUI-Verification:-Verify Start IP Range should not accept Invalid input");
		xmslocallib.setText("Start IP Range", "Kartik");
		xmslocallib.clickButton("OK");
		warningmessageExp="Start IP is invalid."; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean startIPInvalid=xmslocallib.verifyStringequals("DHCPStartIPInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		App_Log.debug("Ref-4.8_GUI-Verification:-Verify Start IP must be in same network as gateway");
		xmslocallib.setText("Start IP Range", "192.168.3.2");
		xmslocallib.setText("Gateway", "192.168.2.1");
		xmslocallib.clickButton("OK");
		warningmessageExp="Start IP must be in the same network as Gateway as defined by the Subnet Mask"; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean startIPdiffNetwork=xmslocallib.verifyStringequals("DHCPStartIPDifferentNetWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		//Log result into file
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");		
		if(startIPEmpty&&startIPInvalid&&startIPdiffNetwork)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);

		App_Log.debug("End-Ref-4.8_Verify DHCP Server Start IP Range Field Validation");
	}
	//Ref-4.9_Verify DHCP Server End IP Range Field Validation
	@Test(priority=9)
	public void testCase_verifyDHCPServerEndIPRangeFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.9_Verify DHCP Server End IP Range Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.9_GUI-Verification:-Verify End IP Range shoild not be empty");
		xmslocallib.setText("End IP Range", "");
		xmslocallib.clickButton("OK");
		String warningmessageExp="End IP is required."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean endIPEmpty=xmslocallib.verifyStringequals("DHCPEndIPEmptyWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		
		App_Log.debug("Ref-4.9_GUI-Verification:-Verify End IP Range shoild not accept Invalid input");
		xmslocallib.setText("End IP Range", "TODAY");
		xmslocallib.clickButton("OK");
		warningmessageExp="End IP is invalid."; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean endIPInvalid=xmslocallib.verifyStringequals("DHCPEndIPInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		
		App_Log.debug("Ref-4.9_GUI-Verification:-Verify End IP must be in same network as gateway");
		xmslocallib.setText("End IP Range", "192.168.3.254");
		xmslocallib.setText("Gateway", "192.168.2.1");
		xmslocallib.clickButton("OK");
		warningmessageExp="End IP must be in the same network as Gateway as defined by the Subnet Mask."; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean endIPdiffNetwork=xmslocallib.verifyStringequals("DHCPEndIPDifferentNetWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		//Add DHCP server to check overlay scenario
		boolean dhcpOverlap=false;
		if(xmslocallib.addDHCPServer(arrayHostName, dhcpNameExp, dhcpStartIPExp, dhcpEndIPExp, dhcpMaskExp, dhcpGatewayExp)){
		App_Log.debug("Ref-4.9_GUI-Verification:-Verify DHCP IP Range should not overlap with other DHCP pool");
		xmslocallib.pause(10);
		xmslocallib.clickButton("Add");
		xmslocallib.setText("Name", "IAMNEW");
		xmslocallib.setText("Start IP Range", dhcpStartIPExp);
		xmslocallib.setText("End IP Range", dhcpEndIPExp);
		xmslocallib.setText("Gateway", dhcpGatewayExp);
		xmslocallib.clickButton("OK");
		warningmessageExp="DHCP Pool overlaps with an existing DHCP Pool"; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		dhcpOverlap=xmslocallib.verifyStringequals("DHCPEndIPDifferentNetWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		}else{
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
	//Log result into file
		if(endIPEmpty&&endIPInvalid&&endIPdiffNetwork&&dhcpOverlap)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.9_Verify DHCP Server End IP Range Field Validation");
	}
	//Ref-4.10_Verify DHCP Server Default Subnet Mask Field Validation
	@Test(priority=10)
	public void testCase_verifyDHCPServerDefaultSubnetMaskFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.10_Verify DHCP Server Default Subnet Mask Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.10_GUI-Verification:-Verify Subnet Mask value is Invalid warning message");
		xmslocallib.setText("Default Subnet Mask", "Kartik");
		xmslocallib.clickButton("OK");
		String warningmessageExp="Subnet Mask is invalid."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean markInvalid=xmslocallib.verifyStringequals("DHCPMaskInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(markInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.10_Verify DHCP Server Default Subnet Mask Field Validation");
	}
	//Ref-4.11_Verify DHCP Server Gateway Field Validation
	@Test(priority=11)
	public void testCase_verifyDHCPServerGatewayFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.11_Verify DHCP Server Gateway Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.11_GUI-Verification:-Verify Default Gateway should not be empty");
		xmslocallib.setText("Gateway", "");
		xmslocallib.clickButton("OK");
		String warningmessageExp="Gateway IP is required."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean gatewayEmpty=xmslocallib.verifyStringequals("DHCPGatewayEmptyWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		//Verify warning message	
		App_Log.debug("Ref-4.11_GUI-Verification:-Verify Default Gateway should not accept Invalid value");
		xmslocallib.setText("Gateway", "kartik");
		xmslocallib.clickButton("OK");
		warningmessageExp="Gateway IP is invalid."; 
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean gatewayInvalid=xmslocallib.verifyStringequals("DHCPGatewayInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(gatewayEmpty&&gatewayInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.11_Verify DHCP Server Gateway Field Validation");
	}
	//Ref-4.12_Verify DHCP Server Default Domain Field Validation
	@Test(priority=12)
	public void testCase_verifyDHCPServerDefaultDomainFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.12_Verify DHCP Server Default Domain Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.12_GUI-Verification:-Verify DNS Domain Name should not accept invalid input");
		xmslocallib.setText("Default Domain", "@@@@@");
		xmslocallib.clickButton("OK");
		String warningmessageExp="Default Domain is invalid."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean domainInvalid=xmslocallib.verifyStringequals("DHCPDomainInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(domainInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.12_Verify DHCP Server Default Domain Field Validation");
	}
	//Ref-4.13_Verify DHCP Server Default DNS Server 1 Field Validation
	@Test(priority=13)
	public void testCase_verifyDHCPServerDefaultDNSServer1FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.13_Verify DHCP Server Default DNS Server 1 Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.13_GUI-Verification:-Verify DNS Server 1 should not accept invalid input");
		xmslocallib.setText("Default DNS Server 1", "holiday");
		xmslocallib.clickButton("OK");
		String warningmessageExp="DNS server 1 IP is invalid."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean dns1IPInvalid=xmslocallib.verifyStringequals("DHCPDomainInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(dns1IPInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.13_Verify DHCP Server Default DNS Server 1 Field Validation");
	}
	//Ref-4.14_Verify DHCP Server Default DNS Server 2 Field Validation
	@Test(priority=14)
	public void testCase_verifyDHCPServerDefaultDNSServer2FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.14_Verify DHCP Server Default DNS Server 2 Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.14_GUI-Verification:-Verify DNS Server 2 should not accept invalid input");
		xmslocallib.setText("Default DNS Server 2", "holiday");
		xmslocallib.clickButton("OK");
		String warningmessageExp="DNS server 2 IP is invalid."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean dns2IPInvalid=xmslocallib.verifyStringequals("DHCPDomainInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(dns2IPInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.14_Verify DHCP Server Default DNS Server 2 Field Validation");
	}
	//Ref-4.15_Verify DHCP Server Default DNS Server 3 Field Validation
	@Test(priority=15)
	public void testCase_verifyDHCPServerDefaultDNSServer3FieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.15_Verify DHCP Server Default DNS Server 3 Field Validation");
		//*****************************************************************
		dhcpEnableExp="disabled";
		dhcpNameExp="DHCPServer1";
		dhcpNATEnableExp="enabled";
		dhcpDefaultLeaseExp="600";
		dhcpMaxLeaseExp="900";
		dhcpStartIPExp="192.160.2.2";
		dhcpEndIPExp="192.160.2.254";
		dhcpMaskExp="255.255.255.0";
		dhcpGatewayExp="192.160.2.1";
		dhcpDomainExp="Testing.com";
		dhcpDNS1Exp="192.160.2.12";
		dhcpDNS2Exp="192.160.2.11";
		dhcpDNS3Exp="192.160.2.10";
		String dhcp1=".1";
		dhcpEnableOID=SNMPOID.getProperty("DHCP1SRVRState")+dhcp1;
		dhcpNameOID=SNMPOID.getProperty("DHCP1SRVRName")+dhcp1;
		dhcpNATEnableOID=SNMPOID.getProperty("DHCP1NAT")+dhcp1;
		dhcpDefaultLeaseOID=SNMPOID.getProperty("DHCP1DefaultLease")+dhcp1;
		dhcpMaxLeaseOID=SNMPOID.getProperty("DHCP1MXLease")+dhcp1;
		dhcpStartIPOID=SNMPOID.getProperty("DHCP1PoolStartIP")+dhcp1;
		dhcpEndIPOID=SNMPOID.getProperty("DHCP1PoolEndIP")+dhcp1;
		dhcpMaskOID=SNMPOID.getProperty("DHCP1PoolMask")+dhcp1;
		dhcpGatewayOID=SNMPOID.getProperty("DHCP1PoolGatway")+dhcp1;
		dhcpDomainOID=SNMPOID.getProperty("DHCP1PoolDomain")+dhcp1;
		dhcpDNS1OID=SNMPOID.getProperty("DHCP1DNS1")+dhcp1;
		dhcpDNS2OID=SNMPOID.getProperty("DHCP1DNS2")+dhcp1;
		dhcpDNS3OID=SNMPOID.getProperty("DHCP1DNS3")+dhcp1;
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Verify that no panel open if yes cancel
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Services");
		xmslocallib.clickLink("DHCP Server");
		xmslocallib.clickButton("Add");
		xmslocallib.pause(10);
		//Verify warning message	
		App_Log.debug("Ref-4.15_GUI-Verification:-Verify DNS Server 3 should not accept invalid input");
		xmslocallib.setText("Default DNS Server 3", "holiday");
		xmslocallib.clickButton("OK");
		String warningmessageExp="DNS server 3 IP is invalid."; 
		String warningmessageAct;  
		if(xmslocallib.isWarningMessageDisplayed(warningmessageExp))warningmessageAct=warningmessageExp; else warningmessageAct="Not Displayed warning message";
		boolean dns3IPInvalid=xmslocallib.verifyStringequals("DHCPDomainInvalidWarning", this.getClass().getSimpleName(), methodName, warningmessageAct, warningmessageExp);
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//Log result into file
		if(dns3IPInvalid)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-4.15_Verify DHCP Server Default DNS Server 3 Field Validation");
	}
	//Ref-4.16_Verify DHCP Server Add Multiple DHCP Server
	@Test(priority=16, enabled=false)
	public void testCase_verifyAddMultipleDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.16_Verify DHCP Server Add Multiple DHCP Server");
		//TBD
		App_Log.debug("End-Ref-4.16_Verify DHCP Server Add Multiple DHCP Server");
	}
	//Ref-4.17_Verify DHCP Server Edit Multiple DHCP Server
	@Test(priority=17, enabled=false)
	public void testCase_verifyEditMultipleDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.17_Verify DHCP Server Edit Multiple DHCP Server");
		//TBD
		App_Log.debug("End-Ref-4.17_Verify DHCP Server Edit Multiple DHCP Server");
	}
	//Ref-4.18_Verify DHCP Server Delete Multiple DHCP Server
	@Test(priority=18, enabled=false)
	public void testCase_verifyDeleteMultipleDHCPServer()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-4.18_Verify DHCP Server Delete Multiple DHCP Server");
		//TDB
		App_Log.debug("End-Ref-4.18_Verify DHCP Server Delete Multiple DHCP Server");
	}
	
	
	@AfterTest
	public void testCompleted() throws InterruptedException{
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_ProfileConfiguration_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
