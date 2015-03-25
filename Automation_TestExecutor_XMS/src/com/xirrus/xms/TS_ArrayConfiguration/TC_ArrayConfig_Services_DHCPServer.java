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
public class TC_ArrayConfig_Services_DHCPServer extends TestSuiteBase{
	//DHCP Server Parameters
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
	public void testCase_VerifyArrayConfigServicesDHCPServerParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Services DHCP Server Parameters");
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
		App_Log.debug("GUI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by GUI
		if(xmslocallib.isCheckboxSelected("Enabled"))dhcpEnableGUI="enabled"; else dhcpEnableGUI="disabled";
		boolean guiResult1=xmslocallib.verifyStringequals("DHCPEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpEnableGUI, dhcpEnableExp);
		App_Log.debug("CLI-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by CLI
		dhcpEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "State");
		boolean cliResult1=xmslocallib.verifyStringequals("DHCPEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpEnableCLI, dhcpEnableExp);
		App_Log.debug("SNMP-Verification:-Verify Enable State for DHCP Server Clear by Default");
		//get value for DHCP Server State Default in Add DHCP Pool by SNMP
		dhcpEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEnableOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("DHCPEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpEnableSNMP, "1");
		
		App_Log.debug("GUI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from GUI
		dhcpNameGUI= xmslocallib.getText("Name");	//need to find out way to read DHCP host name
		boolean guiResult2=xmslocallib.verifyStringequals("DHCPServerNameGUI", this.getClass().getSimpleName(), methodName, dhcpNameExp, dhcpNameExp);
		App_Log.debug("CLI-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name from CLI
		dhcpNameCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DHCP Pool");
		boolean cliResult2=xmslocallib.verifyStringequals("DHCPServerNameCLI", this.getClass().getSimpleName(), methodName, dhcpNameCLI, dhcpNameExp);
		App_Log.debug("SNMP-Verification:-Verify Name for DHCP Server Name");
		//Get value for DHCP Server name by SNMP
		dhcpNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNameOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("DHCPServerNameSNMP", this.getClass().getSimpleName(), methodName, dhcpNameSNMP, dhcpNameExp);
		
		
		App_Log.debug("GUI-Verification:-Verify NAT Enable for DHCP");
		//get value for DHCP Server NAT State by GUI
		if(xmslocallib.isCheckboxSelected("NAT Enabled"))dhcpNATEnableGUI="enabled"; else dhcpNATEnableGUI="disabled";
		boolean guiResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateGUI", this.getClass().getSimpleName(), methodName, dhcpNATEnableGUI, dhcpNATEnableExp);
		App_Log.debug("CLI-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by CLI
		dhcpNATEnableCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "NAT");
		boolean cliResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateCLI", this.getClass().getSimpleName(), methodName, dhcpNATEnableCLI, dhcpNATEnableExp);
		App_Log.debug("SNMP-Verification:-Verify NAT Enable for DHCP Server");
		//get value for DHCP Server NAT State by SNMP
		dhcpNATEnableSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpNATEnableOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("DHCPServerNATEnableStateSNMP", this.getClass().getSimpleName(), methodName, dhcpNATEnableSNMP, "1");

		
		App_Log.debug("GUI-Verification:-Verify Default Lease for DHCP Server");
		//Get value for DHCP Server Default Lease from GUI
		dhcpDefaultLeaseGUI= xmslocallib.getText("Default Lease (sec)");
		boolean guiResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseGUI, dhcpDefaultLeaseExp);
		App_Log.debug("CLI-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by CLI
		dhcpDefaultLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Default lease time").replace("seconds", "").trim();
		boolean cliResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseCLI, dhcpDefaultLeaseExp);
		App_Log.debug("SNMP-Verification:-Verify Default Lease for DHCP Server");
		//Default Lease for DHCP Server by SNMP
		dhcpDefaultLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDefaultLeaseOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("DHCPServerDefaultLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpDefaultLeaseSNMP, dhcpDefaultLeaseExp);
				
		
		App_Log.debug("GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by GUI
		dhcpMaxLeaseGUI= xmslocallib.getText("Max Lease (sec)");
		boolean guiResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseGUI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseGUI, dhcpMaxLeaseExp);
		App_Log.debug("GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by CLI
		dhcpMaxLeaseCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "Maximum lease time").replace("seconds", "").trim();
		boolean cliResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseCLI", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseCLI, dhcpMaxLeaseExp);
		App_Log.debug("GUI-Verification:-Verify Max Lease for DHCP Server");
		//get Max Lease for DHCP Server by SNMP
		dhcpMaxLeaseSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaxLeaseOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("DHCPServerMaxLeaseSNMP", this.getClass().getSimpleName(), methodName, dhcpMaxLeaseSNMP, dhcpMaxLeaseExp);
		
		
		App_Log.debug("GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by GUI
		dhcpStartIPGUI= xmslocallib.getText("Start IP Range");
		boolean guiResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpStartIPGUI, dhcpStartIPExp);
		App_Log.debug("GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by CLI
		dhcpStartIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range start");
		boolean cliResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpStartIPCLI, dhcpStartIPExp);
		App_Log.debug("GUI-Verification:-Verify Start IP Range for DHCP Server");
		//get Start IP Range for DHCP Server by SNMP
		dhcpStartIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpStartIPOID);
		boolean snmpResult6=xmslocallib.verifyStringequals("DHCPServerStartIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpStartIPSNMP, dhcpStartIPExp);
		
		
		App_Log.debug("GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by GUI
		dhcpEndIPGUI= xmslocallib.getText("End IP Range");
		boolean guiResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeGUI", this.getClass().getSimpleName(), methodName, dhcpEndIPGUI, dhcpEndIPExp);
		App_Log.debug("GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by CLI
		dhcpEndIPCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP range end");
		boolean cliResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeCLI", this.getClass().getSimpleName(), methodName, dhcpEndIPCLI, dhcpEndIPExp);
		App_Log.debug("GUI-Verification:-Verify End IP Range for DHCP Server");
		//get End IP Range for DHCP Server by SNMP
		dhcpEndIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpEndIPOID);
		boolean snmpResult7=xmslocallib.verifyStringequals("DHCPServerEndIPRangeSNMP", this.getClass().getSimpleName(), methodName, dhcpEndIPSNMP, dhcpEndIPExp);
		
		App_Log.debug("GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by GUI
		dhcpMaskGUI= xmslocallib.getText("Default Subnet Mask");
		boolean guiResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskGU", this.getClass().getSimpleName(), methodName, dhcpMaskGUI, dhcpMaskExp);
		App_Log.debug("GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by CLI
		dhcpMaskCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP mask");
		boolean cliResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskCLI", this.getClass().getSimpleName(), methodName, dhcpMaskCLI, dhcpMaskExp);
		App_Log.debug("GUI-Verification:-Verify Default Subnet Mask for DHCP Server");
		//Get Default Subnet Mask for DHCP Server by SNMP
		dhcpMaskSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpMaskOID);
		boolean snmpResult8=xmslocallib.verifyStringequals("DHCPServerDefaultMaskSNMP", this.getClass().getSimpleName(), methodName, dhcpMaskSNMP, dhcpMaskExp);
	
		App_Log.debug("GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by GUI
		dhcpGatewayGUI= xmslocallib.getText("Gateway");
		boolean guiResult9=xmslocallib.verifyStringequals("DHCPServerGatewayGUI", this.getClass().getSimpleName(), methodName, dhcpGatewayGUI, dhcpGatewayExp);
		App_Log.debug("GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by CLI
		dhcpGatewayCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "IP gateway");
		boolean cliResult9=xmslocallib.verifyStringequals("DHCPServerGatewayCLI", this.getClass().getSimpleName(), methodName, dhcpGatewayCLI, dhcpGatewayExp);
		App_Log.debug("GUI-Verification:-Verify Gatway for DHCP Server");
		//get Gatway for DHCP Server by SNMP
		dhcpGatewaySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpGatewayOID);
		boolean snmpResult9=xmslocallib.verifyStringequals("DHCPServerGatewaySNMP", this.getClass().getSimpleName(), methodName, dhcpGatewaySNMP, dhcpGatewayExp);
		
		App_Log.debug("GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by GUI
		dhcpDomainGUI= xmslocallib.getText("Default Domain");
		boolean guiResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDomainGUI, dhcpDomainExp);
		App_Log.debug("GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by CLI
		dhcpDomainCLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Domain name");
		boolean cliResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDomainCLI, dhcpDomainExp);
		App_Log.debug("GUI-Verification:-Verify Default Domain for DHCP Server");
		//get Default Domain for DHCP Server by SNMP
		dhcpDomainSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDomainOID);
		boolean snmpResult10=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDomainSNMP, dhcpDomainExp);
		
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by GUI
		dhcpDNS1GUI= xmslocallib.getText("Default DNS Server 1");
		boolean guiResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS1GUI, dhcpDNS1Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by CLI
		dhcpDNS1CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 1");
		boolean cliResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS1CLI, dhcpDNS1Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 1 for DHCP Server");
		//Get Default DNS Server 1 for DHCP Server by SNMP
		dhcpDNS1SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS1OID);
		boolean snmpResult11=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS1SNMP, dhcpDNS1Exp);
		
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by GUI
		dhcpDNS2GUI= xmslocallib.getText("Default DNS Server 2");
		boolean guiResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS2GUI, dhcpDNS2Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by CLI
		dhcpDNS2CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 2");
		boolean cliResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS2CLI, dhcpDNS2Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 2 for DHCP Server");
		//Get Default DNS Server 2 for DHCP Server by SNMP
		dhcpDNS2SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS2OID);
		boolean snmpResult12=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS2SNMP, dhcpDNS2Exp);

		App_Log.debug("GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by GUI
		dhcpDNS3GUI= xmslocallib.getText("Default DNS Server 3");
		boolean guiResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainGUI", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by CLI
		dhcpDNS3CLI=xmslocallib.getValuebyCLI_DHCPServer(arrayIPadd, dhcpNameExp, "DNS Server 3");
		boolean cliResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainCLI", this.getClass().getSimpleName(), methodName, dhcpDNS3CLI, dhcpDNS3Exp);
		App_Log.debug("GUI-Verification:-Verify Default DNS Server 3 for DHCP Server");
		//Get Default DNS Server 3 for DHCP Server by SNMP
		dhcpDNS3SNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, dhcpDNS3OID);
		boolean snmpResult13=xmslocallib.verifyStringequals("DHCPServerDefaultDomainSNMP", this.getClass().getSimpleName(), methodName, dhcpDNS3GUI, dhcpDNS3Exp);
		//Close if DHCP Server window is open		
		if (xmslocallib.findText("Cancel")) {
			xmslocallib.clickButton("Cancel");
		}	
		
		App_Log.debug("End-Verify Array Config Services DHCP Server Parameters");	
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
