package com.xirrus.xms.TS_Array_VLAN;

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
public class TC_VLAN_EditVLANs extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
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
	private String vlanNameSNMP;
	private String vlanIDSNMP;
	private String vlanMgmtSNMP;
	private String vlanDHCPSNMP;
	private String xirrusRoamingSNMP;
	private String vlanIPSNMP;
	private String vlanMaskSNMP;
	private String vlangatewaySNMP;
	private String vlantunnelSNMP;
	private String tunnelSecretSNMP;
	private String tunnelPortSNMP;	
	private String vlanNameGUI;
	private String vlanIDGUI;
	private String vlanMgmtGUI;
	private String vlanDHCPGUI;
	private String xirrusRoamingGUI;
	private String vlanIPGUI;
	private String vlanMaskGUI;
	private String vlangatewayGUI;
	private String vlantunnelGUI;
	private String tunnelSecretGUI;
	private String tunnelPortGUI;
	private String vlanNameCLI;
	private String vlanIDCLI;
	private String vlanMgmtCLI;
	private String vlanDHCPCLI;
	private String xirrusRoamingCLI;
	private String vlanIPCLI;
	private String vlanMaskCLI;
	private String vlangatewayCLI;
	private String vlantunnelCLI;
	private String tunnelSecretCLI;
	private String tunnelPortCLI;
	
	private String vlanNameOID;
	private String vlanIDOID;
	private String vlanMgmtOID;
	private String vlanDHCPOID;
	private String xirrusRoamingOID;
	private String vlanIPOID;
	private String vlanMaskOID;
	private String vlangatewayOID;
	private String vlantunnelOID;
	private String tunnelSecretOID;
	private String tunnelPortOID;

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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_VLAN_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}

	@Test(priority=1)
	public void testCase_VerifyAddSingleVLAN() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.1_Verify Edit Single VLAN");
		//*****************************************************************
		int vlandID=101;
		vlanNameExp="VLAN"+Integer.toString(vlandID);
		vlanIDExp=Integer.toString(vlandID);
		vlanMgmtExp="Disallowed";
		vlanDHCPExp="Disabled";
		xirrusRoamingExp="Disabled";
		vlanIPExp="10.100.59.31";
		vlanMaskExp="255.255.255.0";
		vlangatewayExp="10.100.59.1";
		vlantunnelExp="VLANTunnel";
		tunnelSecretExp="Kartik@123";
		tunnelPortExp="4000";
		String vlan=".1";
		vlanNameOID=SNMPOID.getProperty("VLANNameOID")+vlan;
		vlanIDOID=SNMPOID.getProperty("VLANNumberOID")+vlan;
		vlanMgmtOID=SNMPOID.getProperty("VLANManagementOID")+vlan;
		vlanDHCPOID=SNMPOID.getProperty("VLANDHCPOID")+vlan;
		xirrusRoamingOID=SNMPOID.getProperty("VLANRoamingOID")+vlan;
		vlanIPOID=SNMPOID.getProperty("VLANIPAddressOID")+vlan;
		vlanMaskOID=SNMPOID.getProperty("VLANIPMaskOID")+vlan;
		vlangatewayOID=SNMPOID.getProperty("VLANGatewayOID")+vlan;
		vlantunnelOID=SNMPOID.getProperty("VLANTunnelServerOID")+vlan;
		tunnelSecretOID=SNMPOID.getProperty("VLANTunnelSecretOID")+vlan;
		tunnelPortOID=SNMPOID.getProperty("VLANTunnelPortOID")+vlan;

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
			xmslocallib.setTimeout(3);
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("VLAN");
			xmslocallib.clickLink("VLAN Management");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			xmslocallib.unSelectCheckBox("Management");
			xmslocallib.unSelectCheckBox("Xirrus Roaming");
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
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("VLAN");
			xmslocallib.clickLink("VLAN Management");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			if (! xmslocallib.findText("Edit VLAN")) {
				App_Log.debug("Did not find an expected configuration message.");
				throw new InterruptedException("Did not find an expected configuration message.");
				}			

		//Get all values from GUI, CLI and SNMP values compare with expected values
		xmslocallib.setTimeout(3);
		App_Log.debug("Ref-2.1.1_GUI-Verification:-Verify VLAN Name configured into array");
		//get value for VLAN Name from GUI, CLI and SNMP
		vlanNameGUI=xmslocallib.getText("Name");
		boolean guiResult1=xmslocallib.verifyStringequals("VLANNameGUI", this.getClass().getSimpleName(), methodName, vlanNameGUI, vlanNameExp);
		App_Log.debug("Ref-2.1.1_CLI-Verification:-Verify VLAN Name configured into array");
		vlanNameCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Settings");
		boolean cliResult1=xmslocallib.verifyStringequals("VLANNameCLI", this.getClass().getSimpleName(), methodName, vlanNameExp, vlanNameExp);
		App_Log.debug("Ref-2.1.1_SNMP-Verification:-Verify VLAN Name configured into array");
		vlanNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanNameOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("VLANNameSNMP", this.getClass().getSimpleName(), methodName, vlanNameSNMP, vlanNameExp);
		
		App_Log.debug("Ref-2.1.2_GUI-Verification:-Verify VLAN ID configured into array");
		//Get value for VLAN Id from GUI, CLI and SNMP
		vlanIDGUI=xmslocallib.getText("VLAN ID");
		boolean guiResult2=xmslocallib.verifyStringequals("VLANIDGUI", this.getClass().getSimpleName(), methodName, vlanIDGUI, vlanIDExp);
		App_Log.debug("Ref-2.1.2_CLI-Verification:-Verify VLAN ID configured into array");
		vlanIDCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Number");
		boolean cliResult2=xmslocallib.verifyStringequals("VLANIDCLI", this.getClass().getSimpleName(), methodName, vlanIDCLI, vlanIDExp);
		App_Log.debug("Ref-2.1.2_SNMP-Verification:-Verify VLAN ID configured into array");
		vlanIDSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanIDOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("VLANIDSNMP", this.getClass().getSimpleName(), methodName, vlanIDSNMP, vlanIDExp);
		
		App_Log.debug("Ref-2.1.3_GUI-Verification:-Verify VLAN Management State Configured into Array");
		//Get value for VLAN Management State from GUI
		if(xmslocallib.isCheckboxSelected("Management"))vlanMgmtGUI="Allowed"; else vlanMgmtGUI="Disallowed";
		boolean guiResult3=xmslocallib.verifyStringequals("VLANManagementStateGUI", this.getClass().getSimpleName(), methodName, vlanMgmtGUI, vlanMgmtExp);
		App_Log.debug("Ref-2.1.3_CLI-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Management");
		boolean cliResult3=xmslocallib.verifyStringequals("VLANManagementStateCLI", this.getClass().getSimpleName(), methodName, vlanMgmtCLI, vlanMgmtExp);
		App_Log.debug("Ref-2.1.3_SNMP-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanMgmtOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("VLANManagementStateSNMP", this.getClass().getSimpleName(), methodName, vlanMgmtSNMP, "0");
		
		App_Log.debug("Ref-2.1.4_GUI-Verification:-Verify VLAN DHCP State configured into array");
		//Get value for VLAN DHCP State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("DHCP"))vlanDHCPGUI="Enabled"; else vlanDHCPGUI="Disabled";
		boolean guiResult4=xmslocallib.verifyStringequals("VLANDHCPStateGUI", this.getClass().getSimpleName(), methodName, vlanDHCPGUI, vlanDHCPExp);
		App_Log.debug("Ref-2.1.4_CLI-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "DHCP");
		boolean cliResult4=xmslocallib.verifyStringequals("VLANDHCPStateCLI", this.getClass().getSimpleName(), methodName, vlanDHCPCLI, vlanDHCPExp);
		App_Log.debug("Ref-2.1.4_SNMP-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanDHCPOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("VLANDHCPStateSNMP", this.getClass().getSimpleName(), methodName, vlanDHCPSNMP, "0");
		
		App_Log.debug("Ref-2.1.5_GUI-Verification:-Verify VLAN Roaming State configured into array");
		//Get value for VLAN Roaming State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("Xirrus Roaming")) xirrusRoamingGUI="Enabled"; else xirrusRoamingGUI="Disabled";
		boolean guiResult5=xmslocallib.verifyStringequals("VLANRoamingStateGUI", this.getClass().getSimpleName(), methodName, xirrusRoamingGUI, xirrusRoamingExp);
		App_Log.debug("Ref-2.1.5_CLI-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Fast Roaming");
		boolean cliResult5=xmslocallib.verifyStringequals("VLANRoamingStateCLI", this.getClass().getSimpleName(), methodName, xirrusRoamingCLI, xirrusRoamingExp);
		App_Log.debug("Ref-2.1.5_SNMP-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, xirrusRoamingOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("VLANRoamingStateSNMP", this.getClass().getSimpleName(), methodName, xirrusRoamingSNMP, "0");
		
		App_Log.debug("Ref-2.1.6_GUI-Verification:-Verify VLAN IP Address configured into array");
		//get value for VLAN IP Address from GUI
		vlanIPGUI=xmslocallib.getText("IP Address");
		boolean guiResult6=xmslocallib.verifyStringequals("VLANIPAddressGUI", this.getClass().getSimpleName(), methodName, vlanIPGUI, vlanIPExp);
		App_Log.debug("Ref-2.1.6_CLI-Verification:-Verify VLAN IP Address configured into array");
		vlanIPCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP address");
		boolean cliResult6=xmslocallib.verifyStringequals("VLANIPAddressCLI", this.getClass().getSimpleName(), methodName, vlanIPCLI, vlanIPExp);
		App_Log.debug("Ref-2.1.6_SNMP-Verification:-Verify VLAN IP Address configured into array");
		vlanIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanIPOID);
		boolean snmpResult6=xmslocallib.verifyStringequals("VLANIPAddressSNMP", this.getClass().getSimpleName(), methodName, vlanIPSNMP, vlanIPExp);
		
		App_Log.debug("Ref-2.1.7_GUI-Verification:-Verify VLAN IP Mask Configured into array");
		//get value for VLAN IP Mask from GUI
		vlanMaskGUI=xmslocallib.getText("Subnet Mask");
		boolean guiResult7=xmslocallib.verifyStringequals("VLANIPMaskGUI", this.getClass().getSimpleName(), methodName, vlanMaskGUI, vlanMaskExp);
		App_Log.debug("Ref-2.1.7_CLI-Verification:-Verify VLAN IP Mask Configured into array");
		vlanMaskCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP mask");
		boolean cliResult7=xmslocallib.verifyStringequals("VLANIPMaskCLI", this.getClass().getSimpleName(), methodName, vlanMaskCLI, vlanMaskExp);
		App_Log.debug("Ref-2.1.7_SNMP-Verification:-Verify VLAN IP Mask Configured into array");
		vlanMaskSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanMaskOID);
		boolean snmpResult7=xmslocallib.verifyStringequals("VLANIPMaskSNMP", this.getClass().getSimpleName(), methodName, vlanMaskSNMP, vlanMaskExp);
		
		App_Log.debug("Ref-2.1.8_GUI-Verification:-Verify VLAN IP Gateway configured into array");
		//get value for VLAN IP Gateway from GUI
		vlangatewayGUI=xmslocallib.getText("Gateway");
		boolean guiResult8=xmslocallib.verifyStringequals("VLANIPGatewayGUI", this.getClass().getSimpleName(), methodName, vlangatewayGUI, vlangatewayExp);
		App_Log.debug("Ref-2.1.8_CLI-Verification:-Verify VLAN IP Gateway configured into array");
		vlangatewayCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP gateway");
		boolean cliResult8=xmslocallib.verifyStringequals("VLANIPGatewayCLI", this.getClass().getSimpleName(), methodName, vlangatewayCLI, vlangatewayExp);
		App_Log.debug("Ref-2.1.8_SNMP-Verification:-Verify VLAN IP Gateway configured into array");
		vlangatewaySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlangatewayOID);
		boolean snmpResult8=xmslocallib.verifyStringequals("VLANIPGatewaySNMP", this.getClass().getSimpleName(), methodName, vlangatewaySNMP, vlangatewayExp);
		
		App_Log.debug("Ref-2.1.9_GUI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		//get value for VLAN Tunnel Server from GUI
		vlantunnelGUI=xmslocallib.getText("Tunnel Server");
		boolean guiResult9=xmslocallib.verifyStringequals("VLANTunnelServerGUI", this.getClass().getSimpleName(), methodName, vlantunnelGUI, vlantunnelExp);
		App_Log.debug("Ref-2.1.9_CLI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel server");
		boolean cliResult9=xmslocallib.verifyStringequals("VLANTunnelServerCLI", this.getClass().getSimpleName(), methodName, vlantunnelCLI, vlantunnelExp);
		App_Log.debug("Ref-2.1.9_SNMP-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlantunnelOID);
		boolean snmpResult9=xmslocallib.verifyStringequals("VLANTunnelServerSNMP", this.getClass().getSimpleName(), methodName, vlantunnelSNMP, vlantunnelExp);
		
		App_Log.debug("Ref-2.1.10_GUI-Verification:-Verify VLAN Tunnel Server Port Configured into array");
		//get value for VLAN Tunnel Server Port from GUI
		tunnelPortGUI=xmslocallib.getText("Port");
		boolean guiResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortGUI", this.getClass().getSimpleName(), methodName, tunnelPortGUI, tunnelPortExp);
		App_Log.debug("Ref-2.1.10_CLI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		tunnelPortCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel port");
		boolean cliResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortCLI", this.getClass().getSimpleName(), methodName, tunnelPortCLI, tunnelPortExp);
		App_Log.debug("Ref-2.1.10_SNMP-Verification:-Verify VLAN Tunnel Server Configured into Array");
		tunnelPortSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, tunnelPortOID);
		boolean snmpResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortSNMP", this.getClass().getSimpleName(), methodName, tunnelPortSNMP, tunnelPortExp);
		
		App_Log.debug("Ref-2.1.11_CLI-Verification:-Verify VLAN Tunnel Server Secret Configured into array");
		tunnelSecretCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel secret");
		boolean cliResult11=xmslocallib.verifyStringequals("VLANTunnelSrvrSecretCLI", this.getClass().getSimpleName(), methodName, tunnelSecretCLI, tunnelSecretExp);
		
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//if all value meet expected values mark test as pass on result file
		guiResult1=guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6&&guiResult7&&guiResult8&&guiResult9&&guiResult10;
		cliResult1=cliResult1&&cliResult2&&cliResult3&&cliResult4&&cliResult5&&cliResult6&&cliResult7&&cliResult8&&cliResult9&&cliResult10&&cliResult11;
		snmpResult1=snmpResult1&&snmpResult2&&snmpResult3&&snmpResult4&&snmpResult5&&snmpResult6&&snmpResult7&&snmpResult8&&snmpResult9&&snmpResult10;
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-2.1_Verify Edit Single VLAN");		
	}
	
	@Test(priority=2)
	public void testCase_VerifyEditMultipleVLANs() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.2_Verify Edit Multitple VLANs");
		//*****************************************************************
		int vlandID=101;
		vlanNameExp="VLAN"+Integer.toString(vlandID);
		vlanIDExp=Integer.toString(vlandID);
		vlanMgmtExp="Allowed";
		vlanDHCPExp="Disabled";
		xirrusRoamingExp="Enabled";
		vlanIPExp="10.100.55.2";
		vlanMaskExp="255.255.255.0";
		vlangatewayExp="10.100.55.1";
		vlantunnelExp="VLANTunnel";
		tunnelSecretExp="Today@123";
		tunnelPortExp="4000";
		String vlan=".1";
		vlanNameOID=SNMPOID.getProperty("VLANNameOID")+vlan;
		vlanIDOID=SNMPOID.getProperty("VLANNumberOID")+vlan;
		vlanMgmtOID=SNMPOID.getProperty("VLANManagementOID")+vlan;
		vlanDHCPOID=SNMPOID.getProperty("VLANDHCPOID")+vlan;
		xirrusRoamingOID=SNMPOID.getProperty("VLANRoamingOID")+vlan;
		vlanIPOID=SNMPOID.getProperty("VLANIPAddressOID")+vlan;
		vlanMaskOID=SNMPOID.getProperty("VLANIPMaskOID")+vlan;
		vlangatewayOID=SNMPOID.getProperty("VLANGatewayOID")+vlan;
		vlantunnelOID=SNMPOID.getProperty("VLANTunnelServerOID")+vlan;
		tunnelSecretOID=SNMPOID.getProperty("VLANTunnelSecretOID")+vlan;
		tunnelPortOID=SNMPOID.getProperty("VLANTunnelPortOID")+vlan;

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
			xmslocallib.setTimeout(3);
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("VLAN");
			xmslocallib.clickLink("VLAN Management");
			xmslocallib.toggleCheckbox(3);
			xmslocallib.clickButton("Edit");
			xmslocallib.selectCheckBox("Management");
			xmslocallib.selectCheckBox("Xirrus Roaming");
			xmslocallib.setText("IP Address", vlanIPExp);
			xmslocallib.setText("Gateway", vlangatewayExp);
			xmslocallib.setText("New Secret", tunnelSecretExp);
			xmslocallib.setText("Confirm Secret", tunnelSecretExp);
			xmslocallib.clickButton("OK");			
			xmslocallib.toggleCheckbox(4);
			xmslocallib.clickButton("Edit");
			xmslocallib.selectCheckBox("DHCP");
			xmslocallib.setText("Tunnel Server", "");
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
		App_Log.debug("End-Ref-2.2_Verify Edit Multitple VLANs");		
	}
	
	@Test(priority=3)
	public void testCase_VerifyUpdatedFirstVLANValues() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.3_Verify First VLAN Updated Values configured into array");
		//*****************************************************************
		int vlandID=101;
		vlanNameExp="VLAN"+Integer.toString(vlandID);
		vlanIDExp=Integer.toString(vlandID);
		vlanMgmtExp="Allowed";
		vlanDHCPExp="Disabled";
		xirrusRoamingExp="Enabled";
		vlanIPExp="10.100.55.2";
		vlanMaskExp="255.255.255.0";
		vlangatewayExp="10.100.55.1";
		vlantunnelExp="VLANTunnel";
		tunnelSecretExp="Today@123";
		tunnelPortExp="4000";
		String vlan=".1";
		vlanNameOID=SNMPOID.getProperty("VLANNameOID")+vlan;
		vlanIDOID=SNMPOID.getProperty("VLANNumberOID")+vlan;
		vlanMgmtOID=SNMPOID.getProperty("VLANManagementOID")+vlan;
		vlanDHCPOID=SNMPOID.getProperty("VLANDHCPOID")+vlan;
		xirrusRoamingOID=SNMPOID.getProperty("VLANRoamingOID")+vlan;
		vlanIPOID=SNMPOID.getProperty("VLANIPAddressOID")+vlan;
		vlanMaskOID=SNMPOID.getProperty("VLANIPMaskOID")+vlan;
		vlangatewayOID=SNMPOID.getProperty("VLANGatewayOID")+vlan;
		vlantunnelOID=SNMPOID.getProperty("VLANTunnelServerOID")+vlan;
		tunnelSecretOID=SNMPOID.getProperty("VLANTunnelSecretOID")+vlan;
		tunnelPortOID=SNMPOID.getProperty("VLANTunnelPortOID")+vlan;

		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("VLAN");
		xmslocallib.clickLink("VLAN Management");
		xmslocallib.toggleCheckbox(3);
		xmslocallib.clickButton("Edit");
		if (! xmslocallib.findText("Edit VLAN")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
			}	
		
		//Get all values from GUI, CLI and SNMP values compare with expected values
		xmslocallib.setTimeout(3);
		App_Log.debug("Ref-2.3.1_GUI-Verification:-Verify VLAN Name configured into array");
		//get value for VLAN Name from GUI, CLI and SNMP
		vlanNameGUI=xmslocallib.getText("Name");
		boolean guiResult1=xmslocallib.verifyStringequals("VLANNameGUI", this.getClass().getSimpleName(), methodName, vlanNameGUI, vlanNameExp);
		App_Log.debug("Ref-2.3.1_CLI-Verification:-Verify VLAN Name configured into array");
		vlanNameCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Settings");
		boolean cliResult1=xmslocallib.verifyStringequals("VLANNameCLI", this.getClass().getSimpleName(), methodName, vlanNameExp, vlanNameExp);
		App_Log.debug("Ref-2.3.1_SNMP-Verification:-Verify VLAN Name configured into array");
		vlanNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanNameOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("VLANNameSNMP", this.getClass().getSimpleName(), methodName, vlanNameSNMP, vlanNameExp);
		
		App_Log.debug("Ref-2.3.2_GUI-Verification:-Verify VLAN ID configured into array");
		//Get value for VLAN Id from GUI, CLI and SNMP
		vlanIDGUI=xmslocallib.getText("VLAN ID");
		boolean guiResult2=xmslocallib.verifyStringequals("VLANIDGUI", this.getClass().getSimpleName(), methodName, vlanIDGUI, vlanIDExp);
		App_Log.debug("Ref-2.3.2_CLI-Verification:-Verify VLAN ID configured into array");
		vlanIDCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Number");
		boolean cliResult2=xmslocallib.verifyStringequals("VLANIDCLI", this.getClass().getSimpleName(), methodName, vlanIDCLI, vlanIDExp);
		App_Log.debug("Ref-2.3.2_SNMP-Verification:-Verify VLAN ID configured into array");
		vlanIDSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanIDOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("VLANIDSNMP", this.getClass().getSimpleName(), methodName, vlanIDSNMP, vlanIDExp);
		
		App_Log.debug("Ref-2.3.3_GUI-Verification:-Verify VLAN Management State Configured into Array");
		//Get value for VLAN Management State from GUI
		if(xmslocallib.isCheckboxSelected("Management"))vlanMgmtGUI="Allowed"; else vlanMgmtGUI="Disallowed";
		boolean guiResult3=xmslocallib.verifyStringequals("VLANManagementStateGUI", this.getClass().getSimpleName(), methodName, vlanMgmtGUI, vlanMgmtExp);
		App_Log.debug("Ref-2.3.3_CLI-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Management");
		boolean cliResult3=xmslocallib.verifyStringequals("VLANManagementStateCLI", this.getClass().getSimpleName(), methodName, vlanMgmtCLI, vlanMgmtExp);
		App_Log.debug("Ref-2.3.3_SNMP-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanMgmtOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("VLANManagementStateSNMP", this.getClass().getSimpleName(), methodName, vlanMgmtSNMP, "1");
		
		App_Log.debug("Ref-2.3.4_GUI-Verification:-Verify VLAN DHCP State configured into array");
		//Get value for VLAN DHCP State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("DHCP"))vlanDHCPGUI="Enabled"; else vlanDHCPGUI="Disabled";
		boolean guiResult4=xmslocallib.verifyStringequals("VLANDHCPStateGUI", this.getClass().getSimpleName(), methodName, vlanDHCPGUI, vlanDHCPExp);
		App_Log.debug("Ref-2.3.4_CLI-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "DHCP");
		boolean cliResult4=xmslocallib.verifyStringequals("VLANDHCPStateCLI", this.getClass().getSimpleName(), methodName, vlanDHCPCLI, vlanDHCPExp);
		App_Log.debug("Ref-2.3.4_SNMP-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanDHCPOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("VLANDHCPStateSNMP", this.getClass().getSimpleName(), methodName, vlanDHCPSNMP, "0");
		
		App_Log.debug("Ref-2.3.5_GUI-Verification:-Verify VLAN Roaming State configured into array");
		//Get value for VLAN Roaming State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("Xirrus Roaming")) xirrusRoamingGUI="Enabled"; else xirrusRoamingGUI="Disabled";
		boolean guiResult5=xmslocallib.verifyStringequals("VLANRoamingStateGUI", this.getClass().getSimpleName(), methodName, xirrusRoamingGUI, xirrusRoamingExp);
		App_Log.debug("Ref-2.3.5_CLI-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Fast Roaming");
		boolean cliResult5=xmslocallib.verifyStringequals("VLANRoamingStateCLI", this.getClass().getSimpleName(), methodName, xirrusRoamingCLI, xirrusRoamingExp);
		App_Log.debug("Ref-2.3.5_SNMP-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, xirrusRoamingOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("VLANRoamingStateSNMP", this.getClass().getSimpleName(), methodName, xirrusRoamingSNMP, "1");
		
		App_Log.debug("Ref-2.3.6_GUI-Verification:-Verify VLAN IP Address configured into array");
		//get value for VLAN IP Address from GUI
		vlanIPGUI=xmslocallib.getText("IP Address");
		boolean guiResult6=xmslocallib.verifyStringequals("VLANIPAddressGUI", this.getClass().getSimpleName(), methodName, vlanIPGUI, vlanIPExp);
		App_Log.debug("Ref-2.3.6_CLI-Verification:-Verify VLAN IP Address configured into array");
		vlanIPCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP address");
		boolean cliResult6=xmslocallib.verifyStringequals("VLANIPAddressCLI", this.getClass().getSimpleName(), methodName, vlanIPCLI, vlanIPExp);
		App_Log.debug("Ref-2.3.6_SNMP-Verification:-Verify VLAN IP Address configured into array");
		vlanIPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanIPOID);
		boolean snmpResult6=xmslocallib.verifyStringequals("VLANIPAddressSNMP", this.getClass().getSimpleName(), methodName, vlanIPSNMP, vlanIPExp);
		
		App_Log.debug("Ref-2.3.7_GUI-Verification:-Verify VLAN IP Mask Configured into array");
		//get value for VLAN IP Mask from GUI
		vlanMaskGUI=xmslocallib.getText("Subnet Mask");
		boolean guiResult7=xmslocallib.verifyStringequals("VLANIPMaskGUI", this.getClass().getSimpleName(), methodName, vlanMaskGUI, vlanMaskExp);
		App_Log.debug("Ref-2.3.7_CLI-Verification:-Verify VLAN IP Mask Configured into array");
		vlanMaskCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP mask");
		boolean cliResult7=xmslocallib.verifyStringequals("VLANIPMaskCLI", this.getClass().getSimpleName(), methodName, vlanMaskCLI, vlanMaskExp);
		App_Log.debug("Ref-2.3.7_SNMP-Verification:-Verify VLAN IP Mask Configured into array");
		vlanMaskSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanMaskOID);
		boolean snmpResult7=xmslocallib.verifyStringequals("VLANIPMaskSNMP", this.getClass().getSimpleName(), methodName, vlanMaskSNMP, vlanMaskExp);
		
		App_Log.debug("Ref-2.3.8_GUI-Verification:-Verify VLAN IP Gateway configured into array");
		//get value for VLAN IP Gateway from GUI
		vlangatewayGUI=xmslocallib.getText("Gateway");
		boolean guiResult8=xmslocallib.verifyStringequals("VLANIPGatewayGUI", this.getClass().getSimpleName(), methodName, vlangatewayGUI, vlangatewayExp);
		App_Log.debug("Ref-2.3.8_CLI-Verification:-Verify VLAN IP Gateway configured into array");
		vlangatewayCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "IP gateway");
		boolean cliResult8=xmslocallib.verifyStringequals("VLANIPGatewayCLI", this.getClass().getSimpleName(), methodName, vlangatewayCLI, vlangatewayExp);
		App_Log.debug("Ref-2.3.8_SNMP-Verification:-Verify VLAN IP Gateway configured into array");
		vlangatewaySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlangatewayOID);
		boolean snmpResult8=xmslocallib.verifyStringequals("VLANIPGatewaySNMP", this.getClass().getSimpleName(), methodName, vlangatewaySNMP, vlangatewayExp);
		
		App_Log.debug("Ref-2.3.9_GUI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		//get value for VLAN Tunnel Server from GUI
		vlantunnelGUI=xmslocallib.getText("Tunnel Server");
		boolean guiResult9=xmslocallib.verifyStringequals("VLANTunnelServerGUI", this.getClass().getSimpleName(), methodName, vlantunnelGUI, vlantunnelExp);
		App_Log.debug("Ref-2.3.9_CLI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel server");
		boolean cliResult9=xmslocallib.verifyStringequals("VLANTunnelServerCLI", this.getClass().getSimpleName(), methodName, vlantunnelCLI, vlantunnelExp);
		App_Log.debug("Ref-2.3.9_SNMP-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlantunnelOID);
		boolean snmpResult9=xmslocallib.verifyStringequals("VLANTunnelServerSNMP", this.getClass().getSimpleName(), methodName, vlantunnelSNMP, vlantunnelExp);
		
		App_Log.debug("Ref-2.3.10_GUI-Verification:-Verify VLAN Tunnel Server Port Configured into array");
		//get value for VLAN Tunnel Server Port from GUI
		tunnelPortGUI=xmslocallib.getText("Port");
		boolean guiResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortGUI", this.getClass().getSimpleName(), methodName, tunnelPortGUI, tunnelPortExp);
		App_Log.debug("Ref-2.3.10_CLI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		tunnelPortCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel port");
		boolean cliResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortCLI", this.getClass().getSimpleName(), methodName, tunnelPortCLI, tunnelPortExp);
		App_Log.debug("Ref-2.3.10_SNMP-Verification:-Verify VLAN Tunnel Server Configured into Array");
		tunnelPortSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, tunnelPortOID);
		boolean snmpResult10=xmslocallib.verifyStringequals("VLANTunnelSrvrPortSNMP", this.getClass().getSimpleName(), methodName, tunnelPortSNMP, tunnelPortExp);
		
		App_Log.debug("Ref-2.3.11_CLI-Verification:-Verify VLAN Tunnel Server Secret Configured into array");
		tunnelSecretCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel secret");
		boolean cliResult11=xmslocallib.verifyStringequals("VLANTunnelSrvrSecretCLI", this.getClass().getSimpleName(), methodName, tunnelSecretCLI, tunnelSecretExp);
		
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//if all value meet expected values mark test as pass on result file
		guiResult1=guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6&&guiResult7&&guiResult8&&guiResult9&&guiResult10;
		cliResult1=cliResult1&&cliResult2&&cliResult3&&cliResult4&&cliResult5&&cliResult6&&cliResult7&&cliResult8&&cliResult9&&cliResult10&&cliResult11;
		snmpResult1=snmpResult1&&snmpResult2&&snmpResult3&&snmpResult4&&snmpResult5&&snmpResult6&&snmpResult7&&snmpResult8&&snmpResult9&&snmpResult10;
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		
		App_Log.debug("End-Ref-2.3_Verify First VLAN Updated Values configured into array");		
	}
	@Test(priority=4)
	public void testCase_VerifyUpdatedSecondVLANValues() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-2.4_Verify Second VLAN Updated Values configured into array");
		//*****************************************************************
		int vlandID=102;
		vlanNameExp="VLAN"+Integer.toString(vlandID);
		vlanIDExp=Integer.toString(vlandID);
		vlanMgmtExp="Disallowed";
		vlanDHCPExp="Enabled";
		xirrusRoamingExp="Enabled";
		vlanIPExp="10.100.86.31";
		vlanMaskExp="255.255.255.0";
		vlangatewayExp="10.100.86.1";
		vlantunnelExp="";
		tunnelSecretExp="";
		tunnelPortExp="0";
		String vlan=".2";
		vlanNameOID=SNMPOID.getProperty("VLANNameOID")+vlan;
		vlanIDOID=SNMPOID.getProperty("VLANNumberOID")+vlan;
		vlanMgmtOID=SNMPOID.getProperty("VLANManagementOID")+vlan;
		vlanDHCPOID=SNMPOID.getProperty("VLANDHCPOID")+vlan;
		xirrusRoamingOID=SNMPOID.getProperty("VLANRoamingOID")+vlan;
		vlanIPOID=SNMPOID.getProperty("VLANIPAddressOID")+vlan;
		vlanMaskOID=SNMPOID.getProperty("VLANIPMaskOID")+vlan;
		vlangatewayOID=SNMPOID.getProperty("VLANGatewayOID")+vlan;
		vlantunnelOID=SNMPOID.getProperty("VLANTunnelServerOID")+vlan;
		tunnelSecretOID=SNMPOID.getProperty("VLANTunnelSecretOID")+vlan;
		tunnelPortOID=SNMPOID.getProperty("VLANTunnelPortOID")+vlan;

		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("VLAN");
		xmslocallib.clickLink("VLAN Management");
		xmslocallib.toggleCheckbox(4);
		xmslocallib.clickButton("Edit");
		if (! xmslocallib.findText("Edit VLAN")) {
			App_Log.debug("Did not find an expected configuration message.");
			throw new InterruptedException("Did not find an expected configuration message.");
			}	
		
		//Get all values from GUI, CLI and SNMP values compare with expected values
		xmslocallib.setTimeout(3);
		App_Log.debug("Ref-2.4.1_GUI-Verification:-Verify VLAN Name configured into array");
		//get value for VLAN Name from GUI, CLI and SNMP
		vlanNameGUI=xmslocallib.getText("Name");
		boolean guiResult1=xmslocallib.verifyStringequals("VLANNameGUI", this.getClass().getSimpleName(), methodName, vlanNameGUI, vlanNameExp);
		App_Log.debug("Ref-2.4.1_CLI-Verification:-Verify VLAN Name configured into array");
		vlanNameCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Settings");
		boolean cliResult1=xmslocallib.verifyStringequals("VLANNameCLI", this.getClass().getSimpleName(), methodName, vlanNameExp, vlanNameExp);
		App_Log.debug("Ref-2.4.1_SNMP-Verification:-Verify VLAN Name configured into array");
		vlanNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanNameOID);
		boolean snmpResult1=xmslocallib.verifyStringequals("VLANNameSNMP", this.getClass().getSimpleName(), methodName, vlanNameSNMP, vlanNameExp);
		
		App_Log.debug("Ref-2.4.2_GUI-Verification:-Verify VLAN ID configured into array");
		//Get value for VLAN Id from GUI, CLI and SNMP
		vlanIDGUI=xmslocallib.getText("VLAN ID");
		boolean guiResult2=xmslocallib.verifyStringequals("VLANIDGUI", this.getClass().getSimpleName(), methodName, vlanIDGUI, vlanIDExp);
		App_Log.debug("Ref-2.4.2_CLI-Verification:-Verify VLAN ID configured into array");
		vlanIDCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Number");
		boolean cliResult2=xmslocallib.verifyStringequals("VLANIDCLI", this.getClass().getSimpleName(), methodName, vlanIDCLI, vlanIDExp);
		App_Log.debug("Ref-2.4.2_SNMP-Verification:-Verify VLAN ID configured into array");
		vlanIDSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanIDOID);
		boolean snmpResult2=xmslocallib.verifyStringequals("VLANIDSNMP", this.getClass().getSimpleName(), methodName, vlanIDSNMP, vlanIDExp);
		
		App_Log.debug("Ref-2.4.3_GUI-Verification:-Verify VLAN Management State Configured into Array");
		//Get value for VLAN Management State from GUI
		if(xmslocallib.isCheckboxSelected("Management"))vlanMgmtGUI="Allowed"; else vlanMgmtGUI="Disallowed";
		boolean guiResult3=xmslocallib.verifyStringequals("VLANManagementStateGUI", this.getClass().getSimpleName(), methodName, vlanMgmtGUI, vlanMgmtExp);
		App_Log.debug("Ref-2.4.3_CLI-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Management");
		boolean cliResult3=xmslocallib.verifyStringequals("VLANManagementStateCLI", this.getClass().getSimpleName(), methodName, vlanMgmtCLI, vlanMgmtExp);
		App_Log.debug("Ref-2.4.3_SNMP-Verification:-Verify VLAN Management State Configured into Array");
		vlanMgmtSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanMgmtOID);
		boolean snmpResult3=xmslocallib.verifyStringequals("VLANManagementStateSNMP", this.getClass().getSimpleName(), methodName, vlanMgmtSNMP, "0");
		
		App_Log.debug("Ref-2.4.4_GUI-Verification:-Verify VLAN DHCP State configured into array");
		//Get value for VLAN DHCP State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("DHCP"))vlanDHCPGUI="Enabled"; else vlanDHCPGUI="Disabled";
		boolean guiResult4=xmslocallib.verifyStringequals("VLANDHCPStateGUI", this.getClass().getSimpleName(), methodName, vlanDHCPGUI, vlanDHCPExp);
		App_Log.debug("Ref-2.4.4_CLI-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "DHCP");
		boolean cliResult4=xmslocallib.verifyStringequals("VLANDHCPStateCLI", this.getClass().getSimpleName(), methodName, vlanDHCPCLI, vlanDHCPExp);
		App_Log.debug("Ref-2.4.4_SNMP-Verification:-Verify VLAN DHCP State configured into array");
		vlanDHCPSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanDHCPOID);
		boolean snmpResult4=xmslocallib.verifyStringequals("VLANDHCPStateSNMP", this.getClass().getSimpleName(), methodName, vlanDHCPSNMP, "1");
		
		App_Log.debug("Ref-2.4.5_GUI-Verification:-Verify VLAN Roaming State configured into array");
		//Get value for VLAN Roaming State from GUI CLI and SNMP
		if(xmslocallib.isCheckboxSelected("Xirrus Roaming")) xirrusRoamingGUI="Enabled"; else xirrusRoamingGUI="Disabled";
		boolean guiResult5=xmslocallib.verifyStringequals("VLANRoamingStateGUI", this.getClass().getSimpleName(), methodName, xirrusRoamingGUI, xirrusRoamingExp);
		App_Log.debug("Ref-2.4.5_CLI-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Fast Roaming");
		boolean cliResult5=xmslocallib.verifyStringequals("VLANRoamingStateCLI", this.getClass().getSimpleName(), methodName, xirrusRoamingCLI, xirrusRoamingExp);
		App_Log.debug("Ref-2.4.5_SNMP-Verification:-Verify VLAN Roaming State configured into array");
		xirrusRoamingSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, xirrusRoamingOID);
		boolean snmpResult5=xmslocallib.verifyStringequals("VLANRoamingStateSNMP", this.getClass().getSimpleName(), methodName, xirrusRoamingSNMP, "1");

		App_Log.debug("Ref-2.4.6_GUI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		//get value for VLAN Tunnel Server from GUI
		vlantunnelGUI=xmslocallib.getText("Tunnel Server");
		boolean guiResult6=xmslocallib.verifyStringequals("VLANTunnelServerGUI", this.getClass().getSimpleName(), methodName, vlantunnelGUI, vlantunnelExp);
		App_Log.debug("Ref-2.4.6_CLI-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "Tunnel server");
		boolean cliResult6=xmslocallib.verifyStringequals("VLANTunnelServerCLI", this.getClass().getSimpleName(), methodName, vlantunnelCLI, vlantunnelExp);
		App_Log.debug("Ref-2.4.6_SNMP-Verification:-Verify VLAN Tunnel Server Configured into Array");
		vlantunnelSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlantunnelOID);
		if(vlantunnelSNMP.contains("1.3.6.1.4.1.21013.1"))vlantunnelSNMP="";
		boolean snmpResult6=xmslocallib.verifyStringequals("VLANTunnelServerSNMP", this.getClass().getSimpleName(), methodName, vlantunnelSNMP, vlantunnelExp);
				
		
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		//if all value meet expected values mark test as pass on result file
		guiResult1=guiResult1&&guiResult2&&guiResult3&&guiResult4&&guiResult5&&guiResult6;
		cliResult1=cliResult1&&cliResult2&&cliResult3&&cliResult4&&cliResult5&&cliResult6;
		snmpResult1=snmpResult1&&snmpResult2&&snmpResult3&&snmpResult4&&snmpResult5&&snmpResult6;
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-2.4_Verify Second VLAN Updated Values configured into array");		
	}
	
		
	
	@AfterTest
	public void testCompleted() throws IOException, InterruptedException{
		if (xmslocallib.findText("Cancel"))	xmslocallib.clickButton("Cancel");
		App_Log.debug("Test Case execution for "+this.getClass()+" is completed!!!");
		App_Log.debug("EndTestCase-"+this.getClass().getSimpleName());
		App_Log.debug("***********************End Of test Case********************************");
		
	}
	
	@DataProvider
	public Object[][] getTestData() {
		return Test_Utility.getTestData(suite_Array_General_xls, this.getClass()
				.getSimpleName(), this.getClass().getSimpleName());

	}
	
}
