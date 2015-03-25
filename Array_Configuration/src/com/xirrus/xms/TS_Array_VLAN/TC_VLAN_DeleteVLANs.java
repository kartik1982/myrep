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
public class TC_VLAN_DeleteVLANs extends TestSuiteBase{
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
	public void testCase_VerifyDeleteSingleVLAN() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.1_Verify Delete Single VLAN");
		//*****************************************************************
		int vlandID=103;
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
		String vlan=".3";
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
			xmslocallib.toggleCheckbox(5);
			xmslocallib.clickButton("Delete");
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
		//Get all values from GUI, CLI and SNMP values compare with expected values
		xmslocallib.setTimeout(3);
		App_Log.debug("Ref-3.1_GUI-Verification:-Verify VLAN Deleted from Array");
		//get value for VLAN Name from GUI, CLI and SNMP
		if(!xmslocallib.findText(vlanNameExp))vlanNameGUI="Deleted"; else vlanNameGUI="NotDeleted";
		boolean guiResult1=xmslocallib.verifyStringequals("VLANNameGUI", this.getClass().getSimpleName(), methodName, vlanNameGUI, "Deleted");
		App_Log.debug("Ref-3.1_CLI-Verification:-Verify VLAN Deleted from Array");
		vlanNameCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, vlanNameExp, "%");
		if(vlanNameCLI.contains("VLAN does not exist"))vlanNameCLI="Deleted";else vlanNameCLI="NotDeleted";
		boolean cliResult1=xmslocallib.verifyStringequals("VLANNameCLI", this.getClass().getSimpleName(), methodName, vlanNameCLI, "Deleted");
		App_Log.debug("Ref-3.1_SNMP-Verification:-Verify VLAN Deleted from Array");
		vlanNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanNameOID);
		if(vlanNameSNMP.contains("noSuchInstance"))vlanNameSNMP="Deleted";else vlanNameSNMP="NotDeleted";
		boolean snmpResult1=xmslocallib.verifyStringequals("VLANNameSNMP", this.getClass().getSimpleName(), methodName, vlanNameSNMP, "Deleted");
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.1_Verify Delete Single VLAN");		
	}
	
	@Test(priority=2)
	public void testCase_VerifyDeleteAllVLANs() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-3.2_Verify Delete All VLANs");
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
		
		try{
			xmslocallib.setTimeout(3);
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("VLAN");
			xmslocallib.clickLink("VLAN Management");
			xmslocallib.toggleCheckbox(2);
			xmslocallib.clickButton("Delete");
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
		//Get all values from GUI, CLI and SNMP values compare with expected values
		xmslocallib.setTimeout(3);
		App_Log.debug("Ref-3.2_GUI-Verification:-Verify ALL VLAN Deleted from Array");
		//get value for VLAN Name from GUI, CLI and SNMP
		if(!xmslocallib.findText(vlanNameExp))vlanNameGUI="Deleted"; else vlanNameGUI="NotDeleted";
		boolean guiResult1=xmslocallib.verifyStringequals("VLANNameGUI", this.getClass().getSimpleName(), methodName, vlanNameGUI, "Deleted");
		App_Log.debug("Ref-3.2_CLI-Verification:-Verify ALL VLAN Deleted from Array");
		vlanNameCLI=xmslocallib.getValuebyCLI_VLANs(arrayIPadd, "NoVLAN", "");
		boolean cliResult1=xmslocallib.verifyStringequals("VLANNameCLI", this.getClass().getSimpleName(), methodName, vlanNameCLI, "No VLANs defined.");
		App_Log.debug("Ref-3.2_SNMP-Verification:-Verify ALL VLAN Deleted from Array");
		vlanNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, vlanNameOID);
		if(vlanNameSNMP.contains("noSuchInstance"))vlanNameSNMP="No VLANs defined.";else vlanNameSNMP="NotDeleted";
		boolean snmpResult1=xmslocallib.verifyStringequals("VLANNameSNMP", this.getClass().getSimpleName(), methodName, vlanNameSNMP, "No VLANs defined.");
		if(guiResult1&&cliResult1&&snmpResult1)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-3.2_Verify Delete All VLANs");		
	}
	
		
	@AfterTest
	public void testCompleted() throws IOException, InterruptedException{
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
