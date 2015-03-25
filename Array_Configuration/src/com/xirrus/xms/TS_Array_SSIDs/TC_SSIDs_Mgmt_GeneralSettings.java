package com.xirrus.xms.TS_Array_SSIDs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;



import com.xirrus.xms.globallib.Test_Utility;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.xirrus.xms.globallib.*;

@Test
public class TC_SSIDs_Mgmt_GeneralSettings extends TestSuiteBase{
	//This will check runmode of test case in excel sheet
	private String ssidNameExp;
	private String ssidStateExp;
	private String ssidBrdcastExp;
	private String ssidBandExp;
	private String ssidVLANExp;
	private String ssidQoSExp;
	private String ssidDHCPExp;
	private String ssidFilterExp;
	private String ssidRoamingExp;
	private String ssidFallbackExp;
	private String ssidMDMExp;
	private String ssidVLANIDExp;
	
	private String ssidNameCLI;
	private String ssidStateCLI;
	private String ssidBrdcastCLI;
	private String ssidBandCLI;
	private String ssidVLANCLI;
	private String ssidQoSCLI;
	private String ssidDHCPCLI;
	private String ssidFilterCLI;
	private String ssidRoamingCLI;
	private String ssidFallbackCLI;
	private String ssidMDMCLI;
	private String ssidVLANIDCLI;
	
	private String ssidNameGUI;
	private String ssidStateGUI;
	private String ssidBrdcastGUI;
	private String ssidBandGUI;
	private String ssidVLANGUI;
	private String ssidQoSGUI;
	private String ssidDHCPGUI;
	private String ssidFilterGUI;
	private String ssidRoamingGUI;
	private String ssidFallbackGUI;
	private String ssidMDMGUI;
	private String ssidVLANIDGUI;
	
	private String ssidNameSNMP;
	private String ssidStateSNMP;
	private String ssidBrdcastSNMP;
	private String ssidBandSNMP;
	private String ssidVLANSNMP;
	private String ssidQoSSNMP;
	private String ssidDHCPSNMP;
	private String ssidFilterSNMP;
	private String ssidRoamingSNMP;
	private String ssidFallbackSNMP;
	private String ssidMDMSNMP;
	private String ssidVLANIDSNMP;
	
	private String ssidNameOID;
	private String ssidStateOID;
	private String ssidBrdcastOID;
	private String ssidBandOID;
	private String ssidVLANOID;
	private String ssidQoSOID;
	private String ssidDHCPOID;
	private String ssidFilterOID;
	private String ssidRoamingOID;
	private String ssidFallbackOID;
	private String ssidMDMOID;
	private String ssidVLANIDOID;

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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_SSIDs_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("!!!Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	

	@Test(priority=1)
	public void testCase_VerifyDefaultSSIDGeneralSettings() throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.1_Verify Default SSID General Settings");
		//*****************************************************************
		ssidNameExp="xirrus";
		ssidStateExp="Enabled";
		ssidBrdcastExp="On";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="None";
		ssidFilterExp="None";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="None";
		ssidVLANIDExp="None";
		String ssid=".1";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Navigate to SSID panel
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			throw new SkipException("Test Setup failed hence verification will not proceed for Test case"+this.getClass().getSimpleName());
		}
		//Select Default SSID "xirrus"
		xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
		//Read SSID Name through GUI
		ssidNameGUI= xmslocallib.getText("Name");
		App_Log.debug("Ref-1.1.1_GUI-Verification:-Verify Default SSID Name in General Settings");
		boolean guiname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameGUI, ssidNameExp);
		//Read SSID Name through SNMP
		ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
		App_Log.debug("Ref-1.1.1_SNMP-Verification:-Verify Default SSID Name in General Settings");
		boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, ssidNameExp);
		//Read SSID Name through CLI
		ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
		App_Log.debug("Ref-1.1.1_CLI-Verification:-Verify Default SSID Name in General Settings");
		boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, ssidNameExp);
		
		//Read Enabled state through GUI
		if(xmslocallib.isCheckboxSelected("Enabled")) ssidStateGUI="Enabled"; else ssidStateGUI="Disabled";
		App_Log.debug("Ref-1.1.2_GUI-Verification:-Verify Default SSID State in General Settings");
		boolean guistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateGUI, ssidStateExp);
		//Read Enabled state through SNMP
		ssidStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidStateOID);
		if(ssidStateSNMP.equals("1"))ssidStateSNMP="Enabled"; else ssidStateSNMP="Disabled";
		App_Log.debug("Ref-1.1.2_SNMP-Verification:-Verify Default SSID State in General Settings");
		boolean snmpstate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateSNMP, ssidStateExp);
		//Read Enabled state through CLI
		ssidStateCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "State");
		App_Log.debug("Ref-1.1.2_CLI-Verification:-Verify Default SSID State in General Settings");
		boolean clistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateCLI, ssidStateExp);
		
		//Read SSID broadcast State by GUI
		if(xmslocallib.isCheckboxSelected("Broadcast"))ssidBrdcastGUI="On"; else ssidBrdcastGUI="Off";
		App_Log.debug("Ref-1.1.3_GUI-Verification:-Verify Default SSID Broadcast State in General Settings");
		boolean guibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastGUI, ssidBrdcastExp);
		//Read SSId broadcast State by SNMP
		ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBrdcastOID);
		if(ssidBrdcastSNMP.equals("1"))ssidBrdcastSNMP="On"; else ssidBrdcastSNMP="Off";
		App_Log.debug("Ref-1.1.3_SNMP-Verification:-Verify Default SSID Broadcast State in General Settings");
		boolean snmpbrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastSNMP, ssidBrdcastExp);
		//Read SSID broadcast state by CLI
		ssidBrdcastCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Broadcast");
		App_Log.debug("Ref-1.1.3_CLI-Verification:-Verify Default SSID Broadcast State in General Settings");
		boolean clibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastCLI, ssidBrdcastExp);
		
		//Read Band through GUI
		ssidBandGUI= xmslocallib.getDropDownText("Band");
		App_Log.debug("Ref-1.1.4_GUI-Verification:-Verify Default SSID Band in General Settings");
		boolean guiband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandGUI, ssidBandExp);
		//Read Band through SNMP
		ssidBandSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBandOID);
		if(ssidBandSNMP.equals("1"))ssidBandSNMP="Both"; else{if(ssidBandSNMP.equals("2"))ssidBandSNMP="5 Ghz"; else ssidBandSNMP="2.4 Ghz";}
		App_Log.debug("Ref-1.1.4_SNMP-Verification:-Verify Default SSID Band in General Settings");
		boolean snmpband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandSNMP, ssidBandExp);
		//Read Band through CLI
		ssidBandCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Active Bands");
		if(ssidBandCLI.equals("2.4GHz & 5GHz"))ssidBandCLI="Both";
		App_Log.debug("Ref-1.1.4_CLI-Verification:-Verify Default SSID Band in General Settings");
		boolean cliband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandCLI, ssidBandExp);
		
		//Read VLAN through GUI
		ssidVLANIDGUI= xmslocallib.getDropDownText("Vlan Number");
		App_Log.debug("Ref-1.1.5_GUI-Verification:-Verify Default SSID VLAN in General Settings");
		boolean guivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDGUI, ssidVLANIDExp);
		//Read VLAN through SNMP
		ssidVLANIDSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidVLANIDOID);
		if(ssidVLANIDSNMP.equals("0"))ssidVLANIDSNMP="None";
		App_Log.debug("Ref-1.1.5_SNMP-Verification:-Verify Default SSID VLAN in General Settings");
		boolean snmpvlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDSNMP, ssidVLANIDExp);
		//Read VLAN through CLI
		ssidVLANIDCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "VLAN Number");
		if(ssidVLANIDCLI.equals("-"))ssidVLANIDCLI="None";
		App_Log.debug("Ref-1.1.5_CLI-Verification:-Verify Default SSID VLAN in General Settings");
		boolean clivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDCLI, ssidVLANIDExp);
		
		//Read QOS through GUI
		ssidQoSGUI=xmslocallib.getDropDownText("QoS");
		App_Log.debug("Ref-1.1.6_GUI-Verification:-Verify Default SSID QOS in General Settings");
		boolean guiqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSGUI, ssidQoSExp);
		//Read QOS through SNMP
		ssidQoSSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidQoSOID);
		App_Log.debug("Ref-1.1.6_SNMP-Verification:-Verify Default SSID QOS in General Settings");
		boolean snmpqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSSNMP, ssidQoSExp);
		//Read QOS through CLI
		ssidQoSCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "QoS Level");
		App_Log.debug("Ref-1.1.6_CLI-Verification:-Verify Default SSID QOS in General Settings");
		boolean cliqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSCLI, ssidQoSExp);
		
		//Read DHCP Pool through GUI
		ssidDHCPGUI=xmslocallib.getDropDownText("DHCP Pool");
		App_Log.debug("Ref-1.1.7_GUI-Verification:-Verify Default SSID DHCP Pool in General Settings");
		boolean guidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPGUI, ssidDHCPExp);
		//Read DHCP Pool through SNMP
		ssidDHCPSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidDHCPOID);
		if(ssidDHCPOID.contains(ssidDHCPSNMP.trim()))ssidDHCPSNMP="None";
		App_Log.debug("Ref-1.1.7_SNMP-Verification:-Verify Default SSID DHCP Pool in General Settings");
		boolean snmpdhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPSNMP, ssidDHCPExp);
		//Read DHCP Pool through CLI
		ssidDHCPCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "DHCP Pool");
		App_Log.debug("Ref-1.1.7_CLI-Verification:-Verify Default SSID DHCP Pool in General Settings");
		boolean clidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPCLI, ssidDHCPExp);
		
		//Read Filter List through GUI
		ssidFilterGUI=xmslocallib.getDropDownText("Filter List");
		App_Log.debug("Ref-1.1.8_GUI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean guifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterGUI, ssidFilterExp);
		//Read Filter List through SNMP
		ssidFilterSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFilterOID);
		if(ssidFilterOID.contains(ssidFilterSNMP.trim()))ssidFilterSNMP="None";
		App_Log.debug("Ref-1.1.8_SNMP-Verification:-Verify Default SSID Filter List in General Settings");
		boolean snmpfilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterSNMP, ssidFilterExp);
		//Read Filter List through CLIssidFilterSNMP
		ssidFilterCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Filter List");
		App_Log.debug("Ref-1.1.8_CLI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean clifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterCLI, ssidFilterExp);
		
		//Read Roaming Assist through GUI
		ssidRoamingGUI=xmslocallib.getDropDownText("Xirrus Roaming");
		App_Log.debug("Ref-1.1.9_GUI-Verification:-Verify Default SSID Roaming in General Settings");
		boolean guiroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingGUI, ssidRoamingExp);
		//Read Roaming Assist through SNMP
		ssidRoamingSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidRoamingOID);
		if(ssidRoamingSNMP.equals("0"))ssidRoamingSNMP="L2";else{if(ssidRoamingSNMP.equals("1"))ssidRoamingSNMP="L2 & L3"; else ssidRoamingSNMP="None";}
		App_Log.debug("Ref-1.1.9_SNMP-Verification:-Verify Default SSID Roaming in General Settings");
		boolean snmproaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingSNMP, ssidRoamingExp);
		//Read Roaming Assist through CLI
		ssidRoamingCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fast Roaming");
		if(ssidRoamingCLI.equals("Layer 2 only"))ssidRoamingCLI="L2";
		App_Log.debug("Ref-1.1.9_CLI-Verification:-Verify Default SSID Roaming in General Settings");
		boolean cliroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingCLI, ssidRoamingExp);
		
		//Read Fallback through GUI
		ssidFallbackGUI=xmslocallib.getDropDownText("Fallback");
		App_Log.debug("Ref-1.1.10_GUI-Verification:-Verify Default SSID Fallback in General Settings");
		boolean guifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackGUI, ssidFallbackExp);
		//Read Fallback through SNMP
		ssidFallbackSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFallbackOID);
		if(ssidFallbackSNMP.equals("0"))ssidFallbackSNMP="None"; else ssidFallbackSNMP="Disable";
		App_Log.debug("Ref-1.1.10_SNMP-Verification:-Verify Default SSID Fallback in General Settings");
		boolean snmpfallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackSNMP, ssidFallbackExp);
		//Read Fallback through CLI
		ssidFallbackCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fallback").replace("SSID", "").trim();
		App_Log.debug("Ref-1.1.10_CLI-Verification:-Verify Default SSID Fallback in General Settings");
		boolean clifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackCLI, ssidFallbackExp);
		
		//Read MDM through GUI
		ssidMDMGUI=xmslocallib.getDropDownText("Mobile Device Management");
		App_Log.debug("Ref-1.1.11_GUI-Verification:-Verify Default SSID MDM in General Settings");
		boolean guimdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMGUI, ssidMDMExp);
		//Read MDM through SNMP
		ssidMDMSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidMDMOID);
		if(ssidMDMSNMP.equals("0"))ssidMDMSNMP="None"; else ssidMDMSNMP="AirWatch";
		App_Log.debug("Ref-1.1.11_SNMP-Verification:-Verify Default SSID MDM in General Settings");
		boolean snmpmdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMSNMP, ssidMDMExp);
		//Read MDM through CLI
		ssidMDMCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "MDM Authentication");
		App_Log.debug("Ref-1.1.11_CLI-Verification:-Verify Default SSID MDM in General Settings");
		boolean climdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMCLI, ssidMDMExp);
		
		App_Log.debug("End-Ref-1.1_Verify Default SSID General Settings");		
	}
	@Test(priority=2)
	public void testCase_VerifyAddSSIDwithDefaultGeneralSettings() throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.2_Verify Add SSID with Default General Settings");
		
		//*****************************************************************
		ssidNameExp="kartik";
		ssidStateExp="Enabled";
		ssidBrdcastExp="On";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="None";
		ssidFilterExp="None";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="None";
		ssidVLANIDExp="None";
		String ssid=".2";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Add SSID "kartik"
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
			driver.findElement(By.xpath("//*[@name='configPanelWrap:configPanel:header:newSsid']")).sendKeys(ssidNameExp);
			xmslocallib.clickButton("Add SSID");
			xmslocallib.selectCheckBox("Enabled");
			xmslocallib.selectCheckBox("Broadcast");
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
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
		//Select Default SSID "kartik"
		xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
		//Read SSID Name through GUI
		ssidNameGUI= xmslocallib.getText("Name");
		App_Log.debug("Ref-1.2.1_GUI-Verification:-Verify SSID Name in General Settings");
		boolean guiname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameGUI, ssidNameExp);
		//Read SSID Name through SNMP
		ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
		App_Log.debug("Ref-1.2.1_SNMP-Verification:-Verify SSID Name in General Settings");
		boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, ssidNameExp);
		//Read SSID Name through CLI
		ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
		App_Log.debug("Ref-1.2.1_CLI-Verification:-Verify SSID Name in General Settings");
		boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, ssidNameExp);
		
		//Read Enabled state through GUI
		if(xmslocallib.isCheckboxSelected("Enabled")) ssidStateGUI="Enabled"; else ssidStateGUI="Disabled";
		App_Log.debug("Ref-1.2.2_GUI-Verification:-Verify SSID State in General Settings");
		boolean guistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateGUI, ssidStateExp);
		//Read Enabled state through SNMP
		ssidStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidStateOID);
		if(ssidStateSNMP.equals("1"))ssidStateSNMP="Enabled"; else ssidStateSNMP="Disabled";
		App_Log.debug("Ref-1.2.2_SNMP-Verification:-Verify SSID State in General Settings");
		boolean snmpstate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateSNMP, ssidStateExp);
		//Read Enabled state through CLI
		ssidStateCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "State");
		App_Log.debug("Ref-1.2.2_CLI-Verification:-Verify SSID State in General Settings");
		boolean clistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateCLI, ssidStateExp);
		
		//Read SSID broadcast State by GUI
		if(xmslocallib.isCheckboxSelected("Broadcast"))ssidBrdcastGUI="On"; else ssidBrdcastGUI="Off";
		App_Log.debug("Ref-1.2.3_GUI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean guibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastGUI, ssidBrdcastExp);
		//Read SSId broadcast State by SNMP
		ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBrdcastOID);
		if(ssidBrdcastSNMP.equals("1"))ssidBrdcastSNMP="On"; else ssidBrdcastSNMP="Off";
		App_Log.debug("Ref-1.2.3_SNMP-Verification:-Verify SSID Broadcast State in General Settings");
		boolean snmpbrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastSNMP, ssidBrdcastExp);
		//Read SSID broadcast state by CLI
		ssidBrdcastCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Broadcast");
		App_Log.debug("Ref-1.2.3_CLI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean clibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastCLI, ssidBrdcastExp);
		
		//Read Band through GUI
		ssidBandGUI= xmslocallib.getDropDownText("Band");
		App_Log.debug("Ref-1.2.4_GUI-Verification:-Verify SSID Band in General Settings");
		boolean guiband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandGUI, ssidBandExp);
		//Read Band through SNMP
		ssidBandSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBandOID);
		if(ssidBandSNMP.equals("1"))ssidBandSNMP="Both"; else{if(ssidBandSNMP.equals("2"))ssidBandSNMP="5 Ghz"; else ssidBandSNMP="2.4 Ghz";}
		App_Log.debug("Ref-1.2.4_SNMP-Verification:-Verify SSID Band in General Settings");
		boolean snmpband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandSNMP, ssidBandExp);
		//Read Band through CLI
		ssidBandCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Active Bands");
		if(ssidBandCLI.equals("2.4GHz & 5GHz"))ssidBandCLI="Both";
		App_Log.debug("Ref-1.2.4_CLI-Verification:-Verify SSID Band in General Settings");
		boolean cliband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandCLI, ssidBandExp);
		
		//Read VLAN through GUI
		ssidVLANIDGUI= xmslocallib.getDropDownText("Vlan Number");
		App_Log.debug("Ref-1.2.5_GUI-Verification:-Verify SSID VLAN in General Settings");
		boolean guivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDGUI, ssidVLANIDExp);
		//Read VLAN through SNMP
		ssidVLANIDSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidVLANIDOID);
		if(ssidVLANIDSNMP.equals("0"))ssidVLANIDSNMP="None";
		App_Log.debug("Ref-1.2.5_SNMP-Verification:-Verify SSID VLAN in General Settings");
		boolean snmpvlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDSNMP, ssidVLANIDExp);
		//Read VLAN through CLI
		ssidVLANIDCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "VLAN Number");
		if(ssidVLANIDCLI.equals("-"))ssidVLANIDCLI="None";
		App_Log.debug("Ref-1.2.5_CLI-Verification:-Verify SSID VLAN in General Settings");
		boolean clivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDCLI, ssidVLANIDExp);
		
		//Read QOS through GUI
		ssidQoSGUI=xmslocallib.getDropDownText("QoS");
		App_Log.debug("Ref-1.2.6_GUI-Verification:-Verify SSID QOS in General Settings");
		boolean guiqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSGUI, ssidQoSExp);
		//Read QOS through SNMP
		ssidQoSSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidQoSOID);
		App_Log.debug("Ref-1.2.6_SNMP-Verification:-Verify SSID QOS in General Settings");
		boolean snmpqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSSNMP, ssidQoSExp);
		//Read QOS through CLI
		ssidQoSCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "QoS Level");
		App_Log.debug("Ref-1.2.6_CLI-Verification:-Verify SSID QOS in General Settings");
		boolean cliqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSCLI, ssidQoSExp);
		
		//Read DHCP Pool through GUI
		ssidDHCPGUI=xmslocallib.getDropDownText("DHCP Pool");
		App_Log.debug("Ref-1.2.7_GUI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean guidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPGUI, ssidDHCPExp);
		//Read DHCP Pool through SNMP
		ssidDHCPSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidDHCPOID);
		if(ssidDHCPOID.contains(ssidDHCPSNMP.trim()))ssidDHCPSNMP="None";
		App_Log.debug("Ref-1.2.7_SNMP-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean snmpdhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPSNMP, ssidDHCPExp);
		//Read DHCP Pool through CLI
		ssidDHCPCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "DHCP Pool");
		App_Log.debug("Ref-1.2.7_CLI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean clidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPCLI, ssidDHCPExp);
		
		//Read Filter List through GUI
		ssidFilterGUI=xmslocallib.getDropDownText("Filter List");
		App_Log.debug("Ref-1.2.8_GUI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean guifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterGUI, ssidFilterExp);
		//Read Filter List through SNMP
		ssidFilterSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFilterOID);
		if(ssidFilterOID.contains(ssidFilterSNMP.trim()))ssidFilterSNMP="None";
		App_Log.debug("Ref-1.2.8_SNMP-Verification:-Verify Default SSID Filter List in General Settings");
		boolean snmpfilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterSNMP, ssidFilterExp);
		//Read Filter List through CLIssidFilterSNMP
		ssidFilterCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Filter List");
		App_Log.debug("Ref-1.2.8_CLI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean clifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterCLI, ssidFilterExp);
		
		//Read Roaming Assist through GUI
		ssidRoamingGUI=xmslocallib.getDropDownText("Xirrus Roaming");
		App_Log.debug("Ref-1.2.9_GUI-Verification:-Verify SSID Roaming in General Settings");
		boolean guiroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingGUI, ssidRoamingExp);
		//Read Roaming Assist through SNMP
		ssidRoamingSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidRoamingOID);
		if(ssidRoamingSNMP.equals("0"))ssidRoamingSNMP="L2";else{if(ssidRoamingSNMP.equals("1"))ssidRoamingSNMP="L2 & L3"; else ssidRoamingSNMP="None";}
		App_Log.debug("Ref-1.2.9_SNMP-Verification:-Verify SSID Roaming in General Settings");
		boolean snmproaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingSNMP, ssidRoamingExp);
		//Read Roaming Assist through CLI
		ssidRoamingCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fast Roaming");
		if(ssidRoamingCLI.equals("Layer 2 only"))ssidRoamingCLI="L2";
		App_Log.debug("Ref-1.2.9_CLI-Verification:-Verify SSID Roaming in General Settings");
		boolean cliroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingCLI, ssidRoamingExp);
		
		//Read Fallback through GUI
		ssidFallbackGUI=xmslocallib.getDropDownText("Fallback");
		App_Log.debug("Ref-1.2.10_GUI-Verification:-Verify SSID Fallback in General Settings");
		boolean guifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackGUI, ssidFallbackExp);
		//Read Fallback through SNMP
		ssidFallbackSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFallbackOID);
		if(ssidFallbackSNMP.equals("0"))ssidFallbackSNMP="None"; else ssidFallbackSNMP="Disable";
		App_Log.debug("Ref-1.2.10_SNMP-Verification:-Verify SSID Fallback in General Settings");
		boolean snmpfallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackSNMP, ssidFallbackExp);
		//Read Fallback through CLI
		ssidFallbackCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fallback").replace("SSID", "").trim();
		App_Log.debug("Ref-1.2.10_CLI-Verification:-Verify SSID Fallback in General Settings");
		boolean clifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackCLI, ssidFallbackExp);
		
		//Read MDM through GUI
		ssidMDMGUI=xmslocallib.getDropDownText("Mobile Device Management");
		App_Log.debug("Ref-1.2.11_GUI-Verification:-Verify SSID MDM in General Settings");
		boolean guimdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMGUI, ssidMDMExp);
		//Read MDM through SNMP
		ssidMDMSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidMDMOID);
		if(ssidMDMSNMP.equals("0"))ssidMDMSNMP="None"; else ssidMDMSNMP="AirWatch";
		App_Log.debug("Ref-1.2.11_SNMP-Verification:-Verify SSID MDM in General Settings");
		boolean snmpmdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMSNMP, ssidMDMExp);
		//Read MDM through CLI
		ssidMDMCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "MDM Authentication");
		App_Log.debug("Ref-1.2.11_CLI-Verification:-Verify SSID MDM in General Settings");
		boolean climdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMCLI, ssidMDMExp);
		
		App_Log.debug("End-Ref-1.2_Verify Add SSID with Default General Settings");		
	}
	@Test(priority=3)
	public void testCase_VerifyAddSSIDwithcustomGeneralSettings() throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.3_Verify Add SSID with custom General Settings");
		//*****************************************************************
		ssidNameExp="custom";
		ssidStateExp="Enabled";
		ssidBrdcastExp="On";
		ssidBandExp="5 Ghz";
		ssidVLANExp="SSIDVLAN";
		ssidQoSExp="2";
		ssidDHCPExp="SSIDDHCPSERVER";
		ssidFilterExp="SSIDFILTER";
		ssidRoamingExp="Off";
		ssidFallbackExp="Disable";
		ssidMDMExp="AirWatch";
		ssidVLANIDExp="10";
		String ssid=".3";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Add SSID "custom"
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
			driver.findElement(By.xpath("//*[@name='configPanelWrap:configPanel:header:newSsid']")).sendKeys(ssidNameExp);
			xmslocallib.clickButton("Add SSID");
			xmslocallib.selectCheckBox("Enabled");
			xmslocallib.selectCheckBox("Broadcast");
			xmslocallib.selectDropDown("Band", "5 Ghz");
			xmslocallib.selectDropDown("Vlan", "SSIDVLAN");
			xmslocallib.selectDropDown("DHCP Pool", "SSIDDHCPSERVER");
			xmslocallib.selectDropDown("Filter List", "SSIDFILTER");
			xmslocallib.selectDropDown("Xirrus Roaming", "Off");
			xmslocallib.selectDropDown("Fallback", "Disable");
			xmslocallib.selectDropDown("Mobile Device Management", "AirWatch");
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
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
		//Select Default SSID "cutsom"
		xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
		//Read SSID Name through GUI
		ssidNameGUI= xmslocallib.getText("Name");
		App_Log.debug("Ref-1.3.1_GUI-Verification:-Verify SSID Name in General Settings");
		boolean guiname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameGUI, ssidNameExp);
		//Read SSID Name through SNMP
		ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
		App_Log.debug("Ref-1.3.1_SNMP-Verification:-Verify SSID Name in General Settings");
		boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, ssidNameExp);
		//Read SSID Name through CLI
		ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
		App_Log.debug("Ref-1.3.1_CLI-Verification:-Verify SSID Name in General Settings");
		boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, ssidNameExp);
		
		//Read Enabled state through GUI
		if(xmslocallib.isCheckboxSelected("Enabled")) ssidStateGUI="Enabled"; else ssidStateGUI="Disabled";
		App_Log.debug("Ref-1.3.2_GUI-Verification:-Verify SSID State in General Settings");
		boolean guistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateGUI, ssidStateExp);
		//Read Enabled state through SNMP
		ssidStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidStateOID);
		if(ssidStateSNMP.equals("1"))ssidStateSNMP="Enabled"; else ssidStateSNMP="Disabled";
		App_Log.debug("Ref-1.3.2_SNMP-Verification:-Verify SSID State in General Settings");
		boolean snmpstate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateSNMP, ssidStateExp);
		//Read Enabled state through CLI
		ssidStateCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "State");
		App_Log.debug("Ref-1.3.2_CLI-Verification:-Verify SSID State in General Settings");
		boolean clistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateCLI, ssidStateExp);
		
		//Read SSID broadcast State by GUI
		if(xmslocallib.isCheckboxSelected("Broadcast"))ssidBrdcastGUI="On"; else ssidBrdcastGUI="Off";
		App_Log.debug("Ref-1.3.3_GUI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean guibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastGUI, ssidBrdcastExp);
		//Read SSId broadcast State by SNMP
		ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBrdcastOID);
		if(ssidBrdcastSNMP.equals("1"))ssidBrdcastSNMP="On"; else ssidBrdcastSNMP="Off";
		App_Log.debug("Ref-1.3.3_SNMP-Verification:-Verify SSID Broadcast State in General Settings");
		boolean snmpbrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastSNMP, ssidBrdcastExp);
		//Read SSID broadcast state by CLI
		ssidBrdcastCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Broadcast");
		App_Log.debug("Ref-1.3.3_CLI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean clibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastCLI, ssidBrdcastExp);
		
		//Read Band through GUI
		ssidBandGUI= xmslocallib.getDropDownText("Band");
		App_Log.debug("Ref-1.3.4_GUI-Verification:-Verify SSID Band in General Settings");
		boolean guiband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandGUI, ssidBandExp);
		//Read Band through SNMP
		ssidBandSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBandOID);
		if(ssidBandSNMP.equals("1"))ssidBandSNMP="Both"; else{if(ssidBandSNMP.equals("2"))ssidBandSNMP="5 Ghz"; else ssidBandSNMP="2.4 Ghz";}
		App_Log.debug("Ref-1.3.4_SNMP-Verification:-Verify SSID Band in General Settings");
		boolean snmpband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandSNMP, ssidBandExp);
		//Read Band through CLI
		ssidBandCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Active Band");
		if(ssidBandCLI.equals("2.4GHz & 5GHz"))ssidBandCLI="Both";else{if(ssidBandCLI.equals("5GHz only"))ssidBandCLI="5 Ghz"; else ssidBandCLI="2.4 Ghz";}
		App_Log.debug("Ref-1.3.4_CLI-Verification:-Verify SSID Band in General Settings");
		boolean cliband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandCLI, ssidBandExp);
		
		//Read VLAN through GUI
		ssidVLANIDGUI= xmslocallib.getDropDownText("Vlan Number");
		App_Log.debug("Ref-1.3.5_GUI-Verification:-Verify SSID VLAN in General Settings");
		boolean guivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDGUI, ssidVLANIDExp);
		//Read VLAN through SNMP
		ssidVLANIDSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidVLANIDOID);
		if(ssidVLANIDSNMP.equals("0"))ssidVLANIDSNMP="None";
		App_Log.debug("Ref-1.3.5_SNMP-Verification:-Verify SSID VLAN in General Settings");
		boolean snmpvlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDSNMP, ssidVLANIDExp);
		//Read VLAN through CLI
		ssidVLANIDCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "VLAN Number");
		if(ssidVLANIDCLI.equals("-"))ssidVLANIDCLI="None";
		App_Log.debug("Ref-1.3.5_CLI-Verification:-Verify SSID VLAN in General Settings");
		boolean clivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDCLI, ssidVLANIDExp);
		
		//Read QOS through GUI
		ssidQoSGUI=xmslocallib.getDropDownText("QoS");
		App_Log.debug("Ref-1.3.6_GUI-Verification:-Verify SSID QOS in General Settings");
		boolean guiqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSGUI, ssidQoSExp);
		//Read QOS through SNMP
		ssidQoSSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidQoSOID);
		App_Log.debug("Ref-1.3.6_SNMP-Verification:-Verify SSID QOS in General Settings");
		boolean snmpqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSSNMP, ssidQoSExp);
		//Read QOS through CLI
		ssidQoSCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "QoS Level");
		App_Log.debug("Ref-1.3.6_CLI-Verification:-Verify SSID QOS in General Settings");
		boolean cliqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSCLI, ssidQoSExp);
		
		//Read DHCP Pool through GUI
		ssidDHCPGUI=xmslocallib.getDropDownText("DHCP Pool");
		App_Log.debug("Ref-1.3.7_GUI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean guidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPGUI, ssidDHCPExp);
		//Read DHCP Pool through SNMP
		ssidDHCPSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidDHCPOID);
		if(ssidDHCPOID.contains(ssidDHCPSNMP.trim()))ssidDHCPSNMP="None";
		App_Log.debug("Ref-1.3.7_SNMP-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean snmpdhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPSNMP, ssidDHCPExp);
		//Read DHCP Pool through CLI
		ssidDHCPCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "DHCP Pool");
		App_Log.debug("Ref-1.3.7_CLI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean clidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPCLI, ssidDHCPExp);
		
		//Read Filter List through GUI
		ssidFilterGUI=xmslocallib.getDropDownText("Filter List");
		App_Log.debug("Ref-1.3.8_GUI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean guifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterGUI, ssidFilterExp);
		//Read Filter List through SNMP
		ssidFilterSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFilterOID);
		if(ssidFilterOID.contains(ssidFilterSNMP.trim()))ssidFilterSNMP="None";
		App_Log.debug("Ref-1.3.8_SNMP-Verification:-Verify Default SSID Filter List in General Settings");
		boolean snmpfilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterSNMP, ssidFilterExp);
		//Read Filter List through CLIssidFilterSNMP
		ssidFilterCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Filter List");
		App_Log.debug("Ref-1.3.8_CLI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean clifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterCLI, ssidFilterExp);
		
		//Read Roaming Assist through GUI
		ssidRoamingGUI=xmslocallib.getDropDownText("Xirrus Roaming");
		App_Log.debug("Ref-1.3.9_GUI-Verification:-Verify SSID Roaming in General Settings");
		boolean guiroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingGUI, ssidRoamingExp);
		//Read Roaming Assist through SNMP
		ssidRoamingSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidRoamingOID);
		if(ssidRoamingSNMP.equals("0"))ssidRoamingSNMP="L2";else{if(ssidRoamingSNMP.equals("1"))ssidRoamingSNMP="L2 & L3"; else ssidRoamingSNMP="Off";}
		App_Log.debug("Ref-1.3.9_SNMP-Verification:-Verify SSID Roaming in General Settings");
		boolean snmproaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingSNMP, ssidRoamingExp);
		//Read Roaming Assist through CLI
		ssidRoamingCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fast Roaming");
		if(ssidRoamingCLI.equals("Disabled"))ssidRoamingCLI="Off";else{if (ssidRoamingCLI.equals("Layer 2 only"))ssidRoamingCLI="L2";else ssidRoamingCLI="L2 & L3";}
		App_Log.debug("Ref-1.3.9_CLI-Verification:-Verify SSID Roaming in General Settings");
		boolean cliroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingCLI, ssidRoamingExp);
		
		//Read Fallback through GUI
		ssidFallbackGUI=xmslocallib.getDropDownText("Fallback");
		App_Log.debug("Ref-1.3.10_GUI-Verification:-Verify SSID Fallback in General Settings");
		boolean guifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackGUI, ssidFallbackExp);
		//Read Fallback through SNMP
		ssidFallbackSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFallbackOID);
		if(ssidFallbackSNMP.equals("0"))ssidFallbackSNMP="None"; else ssidFallbackSNMP="Disable";
		App_Log.debug("Ref-1.3.10_SNMP-Verification:-Verify SSID Fallback in General Settings");
		boolean snmpfallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackSNMP, ssidFallbackExp);
		//Read Fallback through CLI
		ssidFallbackCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fallback").replace("SSID", "").trim();
		App_Log.debug("Ref-1.3.10_CLI-Verification:-Verify SSID Fallback in General Settings");
		boolean clifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackCLI, ssidFallbackExp);
		
		//Read MDM through GUI
		ssidMDMGUI=xmslocallib.getDropDownText("Mobile Device Management");
		App_Log.debug("Ref-1.3.11_GUI-Verification:-Verify SSID MDM in General Settings");
		boolean guimdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMGUI, ssidMDMExp);
		//Read MDM through SNMP
		ssidMDMSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidMDMOID);
		if(ssidMDMSNMP.equals("0"))ssidMDMSNMP="None"; else ssidMDMSNMP="AirWatch";
		App_Log.debug("Ref-1.3.11_SNMP-Verification:-Verify SSID MDM in General Settings");
		boolean snmpmdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMSNMP, ssidMDMExp);
		//Read MDM through CLI
		ssidMDMCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "MDM Authentication");
		App_Log.debug("Ref-1.3.11_CLI-Verification:-Verify SSID MDM in General Settings");
		boolean climdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMCLI, ssidMDMExp);
		
		App_Log.debug("End-Ref-1.3_Verify Add SSID with custom General Settings");		
	}
	@Test(priority=4)
	public void testCase_VerifyEditSSIDGeneralSettings() throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.4_Verify Edit SSID General Settings");
		//Edit SSID "Custom"
		//*****************************************************************
		ssidNameExp="custom";
		ssidStateExp="Disabled";
		ssidBrdcastExp="Off";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="SSIDDHCPSERVER";
		ssidFilterExp="SSIDFILTER";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="AirWatch";
		ssidVLANIDExp="None";
		String ssid=".3";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Add SSID "custom"
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
			//Select Default SSID "cutsom"
			xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
			xmslocallib.unSelectCheckBox("Enabled");
			xmslocallib.unSelectCheckBox("Broadcast");
			xmslocallib.selectDropDown("Band", ssidBandExp);
			xmslocallib.selectDropDown("Vlan", ssidVLANExp);
			xmslocallib.selectDropDown("Xirrus Roaming", ssidRoamingExp);
			xmslocallib.selectDropDown("Fallback", ssidFallbackExp);
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
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
		//Select Default SSID "custom"
		xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
		//Read SSID Name through GUI
		ssidNameGUI= xmslocallib.getText("Name");
		App_Log.debug("Ref-1.4.1_GUI-Verification:-Verify SSID Name in updated General Settings");
		boolean guiname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameGUI, ssidNameExp);
		//Read SSID Name through SNMP
		ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
		App_Log.debug("Ref-1.4.1_SNMP-Verification:-Verify SSID Name in updated General Settings");
		boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, ssidNameExp);
		//Read SSID Name through CLI
		ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
		App_Log.debug("Ref-1.4.1_CLI-Verification:-Verify SSID Name in updated General Settings");
		boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, ssidNameExp);
		
		//Read Enabled state through GUI
		if(xmslocallib.isCheckboxSelected("Enabled")) ssidStateGUI="Enabled"; else ssidStateGUI="Disabled";
		App_Log.debug("Ref-1.4.2_GUI-Verification:-Verify SSID State in updated General Settings");
		boolean guistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateGUI, ssidStateExp);
		//Read Enabled state through SNMP
		ssidStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidStateOID);
		if(ssidStateSNMP.equals("1"))ssidStateSNMP="Enabled"; else ssidStateSNMP="Disabled";
		App_Log.debug("Ref-1.4.2_SNMP-Verification:-Verify SSID State in updated General Settings");
		boolean snmpstate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateSNMP, ssidStateExp);
		//Read Enabled state through CLI
		ssidStateCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "State");
		App_Log.debug("Ref-1.4.2_CLI-Verification:-Verify SSID State in updated General Settings");
		boolean clistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateCLI, ssidStateExp);
		
		//Read SSID broadcast State by GUI
		if(xmslocallib.isCheckboxSelected("Broadcast"))ssidBrdcastGUI="On"; else ssidBrdcastGUI="Off";
		App_Log.debug("Ref-1.4.3_GUI-Verification:-Verify SSID Broadcast State in updated General Settings");
		boolean guibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastGUI, ssidBrdcastExp);
		//Read SSId broadcast State by SNMP
		ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBrdcastOID);
		if(ssidBrdcastSNMP.equals("1"))ssidBrdcastSNMP="On"; else ssidBrdcastSNMP="Off";
		App_Log.debug("Ref-1.4.3_SNMP-Verification:-Verify SSID Broadcast State in updated General Settings");
		boolean snmpbrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastSNMP, ssidBrdcastExp);
		//Read SSID broadcast state by CLI
		ssidBrdcastCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Broadcast");
		App_Log.debug("Ref-1.4.3_CLI-Verification:-Verify SSID Broadcast State in updated General Settings");
		boolean clibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastCLI, ssidBrdcastExp);
		
		//Read Band through GUI
		ssidBandGUI= xmslocallib.getDropDownText("Band");
		App_Log.debug("Ref-1.4.4_GUI-Verification:-Verify SSID Band in updated General Settings");
		boolean guiband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandGUI, ssidBandExp);
		//Read Band through SNMP
		ssidBandSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBandOID);
		if(ssidBandSNMP.equals("1"))ssidBandSNMP="Both"; else{if(ssidBandSNMP.equals("2"))ssidBandSNMP="5 Ghz"; else ssidBandSNMP="2.4 Ghz";}
		App_Log.debug("Ref-1.4.4_SNMP-Verification:-Verify SSID Band in updated General Settings");
		boolean snmpband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandSNMP, ssidBandExp);
		//Read Band through CLI
		ssidBandCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Active Band");
		if(ssidBandCLI.equals("2.4GHz only"))ssidBandCLI="2.4 Ghz";else{if(ssidBandCLI.equals("5GHz only"))ssidBandCLI="5 Ghz"; else ssidBandCLI="Both";}
		App_Log.debug("Ref-1.4.4_CLI-Verification:-Verify SSID Band in updated General Settings");
		boolean cliband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandCLI, ssidBandExp);
		
		//Read VLAN through GUI
		ssidVLANIDGUI= xmslocallib.getDropDownText("Vlan Number");
		App_Log.debug("Ref-1.4.5_GUI-Verification:-Verify SSID VLAN in updated General Settings");
		boolean guivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDGUI, ssidVLANIDExp);
		//Read VLAN through SNMP
		ssidVLANIDSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidVLANIDOID);
		if(ssidVLANIDSNMP.equals("0"))ssidVLANIDSNMP="None";
		App_Log.debug("Ref-1.4.5_SNMP-Verification:-Verify SSID VLAN in updated General Settings");
		boolean snmpvlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDSNMP, ssidVLANIDExp);
		//Read VLAN through CLI
		ssidVLANIDCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "VLAN Number");
		if(ssidVLANIDCLI.equals("-"))ssidVLANIDCLI="None";
		App_Log.debug("Ref-1.4.5_CLI-Verification:-Verify SSID VLAN in updated General Settings");
		boolean clivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDCLI, ssidVLANIDExp);
		
		//Read QOS through GUI
		ssidQoSGUI=xmslocallib.getDropDownText("QoS");
		App_Log.debug("Ref-1.4.6_GUI-Verification:-Verify SSID QOS in updated General Settings");
		boolean guiqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSGUI, ssidQoSExp);
		//Read QOS through SNMP
		ssidQoSSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidQoSOID);
		App_Log.debug("Ref-1.4.6_SNMP-Verification:-Verify SSID QOS in updated General Settings");
		boolean snmpqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSSNMP, ssidQoSExp);
		//Read QOS through CLI
		ssidQoSCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "QoS Level");
		App_Log.debug("Ref-1.4.6_CLI-Verification:-Verify SSID QOS in updated General Settings");
		boolean cliqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSCLI, ssidQoSExp);
		
		//Read DHCP Pool through GUI
		ssidDHCPGUI=xmslocallib.getDropDownText("DHCP Pool");
		App_Log.debug("Ref-1.4.7_GUI-Verification:-Verify SSID DHCP Pool in updated General Settings");
		boolean guidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPGUI, ssidDHCPExp);
		//Read DHCP Pool through SNMP
		ssidDHCPSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidDHCPOID);
		if(ssidDHCPOID.contains(ssidDHCPSNMP.trim()))ssidDHCPSNMP="None";
		App_Log.debug("Ref-1.4.7_SNMP-Verification:-Verify SSID DHCP Pool in updated General Settings");
		boolean snmpdhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPSNMP, ssidDHCPExp);
		//Read DHCP Pool through CLI
		ssidDHCPCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "DHCP Pool");
		App_Log.debug("Ref-1.4.7_CLI-Verification:-Verify SSID DHCP Pool in updated General Settings");
		boolean clidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPCLI, ssidDHCPExp);
		
		//Read Filter List through GUI
		ssidFilterGUI=xmslocallib.getDropDownText("Filter List");
		App_Log.debug("Ref-1.4.8_GUI-Verification:-Verify Default SSID Filter List in updated General Settings");
		boolean guifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterGUI, ssidFilterExp);
		//Read Filter List through SNMP
		ssidFilterSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFilterOID);
		if(ssidFilterOID.contains(ssidFilterSNMP.trim()))ssidFilterSNMP="None";
		App_Log.debug("Ref-1.4.8_SNMP-Verification:-Verify Default SSID Filter List in updated General Settings");
		boolean snmpfilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterSNMP, ssidFilterExp);
		//Read Filter List through CLIssidFilterSNMP
		ssidFilterCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Filter List");
		App_Log.debug("Ref-1.4.8_CLI-Verification:-Verify Default SSID Filter List in updated General Settings");
		boolean clifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterCLI, ssidFilterExp);
		
		//Read Roaming Assist through GUI
		ssidRoamingGUI=xmslocallib.getDropDownText("Xirrus Roaming");
		App_Log.debug("Ref-1.4.9_GUI-Verification:-Verify SSID Roaming in updated General Settings");
		boolean guiroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingGUI, ssidRoamingExp);
		//Read Roaming Assist through SNMP
		ssidRoamingSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidRoamingOID);
		if(ssidRoamingSNMP.equals("0"))ssidRoamingSNMP="L2";else{if(ssidRoamingSNMP.equals("1"))ssidRoamingSNMP="L2 & L3"; else ssidRoamingSNMP="Off";}
		App_Log.debug("Ref-1.4.9_SNMP-Verification:-Verify SSID Roaming in updated General Settings");
		boolean snmproaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingSNMP, ssidRoamingExp);
		//Read Roaming Assist through CLI
		ssidRoamingCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fast Roaming");
		if(ssidRoamingCLI.equals("Disabled"))ssidRoamingCLI="Off";else{if (ssidRoamingCLI.equals("Layer 2 only"))ssidRoamingCLI="L2";else ssidRoamingCLI="L2 & L3";}
		App_Log.debug("Ref-1.4.9_CLI-Verification:-Verify SSID Roaming in updated General Settings");
		boolean cliroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingCLI, ssidRoamingExp);
		
		//Read Fallback through GUI
		ssidFallbackGUI=xmslocallib.getDropDownText("Fallback");
		App_Log.debug("Ref-1.4.10_GUI-Verification:-Verify SSID Fallback in updated General Settings");
		boolean guifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackGUI, ssidFallbackExp);
		//Read Fallback through SNMP
		ssidFallbackSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFallbackOID);
		if(ssidFallbackSNMP.equals("0"))ssidFallbackSNMP="None"; else ssidFallbackSNMP="Disable";
		App_Log.debug("Ref-1.4.10_SNMP-Verification:-Verify SSID Fallback in updated General Settings");
		boolean snmpfallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackSNMP, ssidFallbackExp);
		//Read Fallback through CLI
		ssidFallbackCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fallback").replace("SSID", "").trim();
		App_Log.debug("Ref-1.4.10_CLI-Verification:-Verify SSID Fallback in updated General Settings");
		boolean clifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackCLI, ssidFallbackExp);
		
		//Read MDM through GUI
		ssidMDMGUI=xmslocallib.getDropDownText("Mobile Device Management");
		App_Log.debug("Ref-1.4.11_GUI-Verification:-Verify SSID MDM in updated General Settings");
		boolean guimdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMGUI, ssidMDMExp);
		//Read MDM through SNMP
		ssidMDMSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidMDMOID);
		if(ssidMDMSNMP.equals("0"))ssidMDMSNMP="None"; else ssidMDMSNMP="AirWatch";
		App_Log.debug("Ref-1.4.11_SNMP-Verification:-Verify SSID MDM in updated General Settings");
		boolean snmpmdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMSNMP, ssidMDMExp);
		//Read MDM through CLI
		ssidMDMCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "MDM Authentication");
		App_Log.debug("Ref-1.4.11_CLI-Verification:-Verify SSID MDM in updated General Settings");
		boolean climdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMCLI, ssidMDMExp);
		
		App_Log.debug("End-Ref-1.4_Verify Edit SSID General Settings");		
	}
	
	@Test(priority=5)
	public void testCase_VerifyDeleteSSID() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify Delete SSID"); 
		//Edit SSID "Custom"
		//*****************************************************************
		ssidNameExp="custom";
		ssidStateExp="Disabled";
		ssidBrdcastExp="Off";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="SSIDDHCPSERVER";
		ssidFilterExp="SSIDFILTER";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="AirWatch";
		ssidVLANIDExp="None";
		String ssid=".3";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Delete SSID "custom"
		ssidNameGUI=ssidNameExp;
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
			//Select Default SSID "cutsom"
			xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
			xmslocallib.clickButton("Delete selected SSID");
			do{
				xmslocallib.pause(10);
			}while(!xmslocallib.findText("Delete this SSID?"));
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
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");

			//Verify that SSID is deleted through SNMP
			ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
			if(ssidNameSNMP.equals("noSuchInstance")) ssidNameSNMP="Deleted";
			App_Log.debug("Ref-1.5_SNMP-Verification:-Verify Deleted SSID");
			boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, "Deleted");
			
			//Verify that SSId is deleted through CLI
			ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
			App_Log.debug("Ref-1.5_CLI-Verification:-Verify Deleted SSID");
			boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, "Deleted");
	
		App_Log.debug("End-Ref-1.5_Verify Delete SSID");
	}
	
	@Test(priority=6)
	public void testCase_VerifyRenameSSID() throws IOException, InterruptedException{
		App_Log.debug("Start-Ref-1.6_Verify Rename SSID in General Settings");
		//*****************************************************************
		ssidNameExp="Tester";
		ssidStateExp="Enabled";
		ssidBrdcastExp="On";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="None";
		ssidFilterExp="None";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="None";
		ssidVLANIDExp="None";
		String ssid=".2";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
		arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Add SSID "kartik"
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
			//Select Default SSID "cutsom"
			xmslocallib.selectDropDown("Currently selected SSID", "kartik");
			xmslocallib.setText("Name", ssidNameExp);			
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
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
		//Select Default SSID "Tester"
		xmslocallib.selectDropDown("Currently selected SSID", ssidNameExp);
		//Read SSID Name through GUI
		ssidNameGUI= xmslocallib.getText("Name");
		App_Log.debug("Ref-1.2.1_GUI-Verification:-Verify SSID Name in General Settings");
		boolean guiname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameGUI, ssidNameExp);
		//Read SSID Name through SNMP
		ssidNameSNMP= xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidNameOID);
		App_Log.debug("Ref-1.2.1_SNMP-Verification:-Verify SSID Name in General Settings");
		boolean snmpname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameSNMP, ssidNameExp);
		//Read SSID Name through CLI
		ssidNameCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Settings");
		App_Log.debug("Ref-1.6.1_CLI-Verification:-Verify SSID Name in General Settings");
		boolean cliname=xmslocallib.verifyStringequals("SSIDName", this.getClass().getSimpleName(), methodName, ssidNameCLI, ssidNameExp);
		
		//Read Enabled state through GUI
		if(xmslocallib.isCheckboxSelected("Enabled")) ssidStateGUI="Enabled"; else ssidStateGUI="Disabled";
		App_Log.debug("Ref-1.6.2_GUI-Verification:-Verify SSID State in General Settings");
		boolean guistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateGUI, ssidStateExp);
		//Read Enabled state through SNMP
		ssidStateSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidStateOID);
		if(ssidStateSNMP.equals("1"))ssidStateSNMP="Enabled"; else ssidStateSNMP="Disabled";
		App_Log.debug("Ref-1.6.2_SNMP-Verification:-Verify SSID State in General Settings");
		boolean snmpstate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateSNMP, ssidStateExp);
		//Read Enabled state through CLI
		ssidStateCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "State");
		App_Log.debug("Ref-1.6.2_CLI-Verification:-Verify SSID State in General Settings");
		boolean clistate=xmslocallib.verifyStringequals("SSIDState", this.getClass().getSimpleName(), methodName, ssidStateCLI, ssidStateExp);
		
		//Read SSID broadcast State by GUI
		if(xmslocallib.isCheckboxSelected("Broadcast"))ssidBrdcastGUI="On"; else ssidBrdcastGUI="Off";
		App_Log.debug("Ref-1.6.3_GUI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean guibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastGUI, ssidBrdcastExp);
		//Read SSId broadcast State by SNMP
		ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBrdcastOID);
		if(ssidBrdcastSNMP.equals("1"))ssidBrdcastSNMP="On"; else ssidBrdcastSNMP="Off";
		App_Log.debug("Ref-1.6.3_SNMP-Verification:-Verify SSID Broadcast State in General Settings");
		boolean snmpbrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastSNMP, ssidBrdcastExp);
		//Read SSID broadcast state by CLI
		ssidBrdcastCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Broadcast");
		App_Log.debug("Ref-1.6.3_CLI-Verification:-Verify SSID Broadcast State in General Settings");
		boolean clibrdcast=xmslocallib.verifyStringequals("SSIDBroadCast", this.getClass().getSimpleName(), methodName, ssidBrdcastCLI, ssidBrdcastExp);
		
		//Read Band through GUI
		ssidBandGUI= xmslocallib.getDropDownText("Band");
		App_Log.debug("Ref-1.6.4_GUI-Verification:-Verify SSID Band in General Settings");
		boolean guiband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandGUI, ssidBandExp);
		//Read Band through SNMP
		ssidBandSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidBandOID);
		if(ssidBandSNMP.equals("1"))ssidBandSNMP="Both"; else{if(ssidBandSNMP.equals("2"))ssidBandSNMP="5 Ghz"; else ssidBandSNMP="2.4 Ghz";}
		App_Log.debug("Ref-1.6.4_SNMP-Verification:-Verify SSID Band in General Settings");
		boolean snmpband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandSNMP, ssidBandExp);
		//Read Band through CLI
		ssidBandCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Active Bands");
		if(ssidBandCLI.equals("2.4GHz & 5GHz"))ssidBandCLI="Both";
		App_Log.debug("Ref-1.6.4_CLI-Verification:-Verify SSID Band in General Settings");
		boolean cliband=xmslocallib.verifyStringequals("SSIDBand", this.getClass().getSimpleName(), methodName, ssidBandCLI, ssidBandExp);
		
		//Read VLAN through GUI
		ssidVLANIDGUI= xmslocallib.getDropDownText("Vlan Number");
		App_Log.debug("Ref-1.6.5_GUI-Verification:-Verify SSID VLAN in General Settings");
		boolean guivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDGUI, ssidVLANIDExp);
		//Read VLAN through SNMP
		ssidVLANIDSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidVLANIDOID);
		if(ssidVLANIDSNMP.equals("0"))ssidVLANIDSNMP="None";
		App_Log.debug("Ref-1.6.5_SNMP-Verification:-Verify SSID VLAN in General Settings");
		boolean snmpvlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDSNMP, ssidVLANIDExp);
		//Read VLAN through CLI
		ssidVLANIDCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "VLAN Number");
		if(ssidVLANIDCLI.equals("-"))ssidVLANIDCLI="None";
		App_Log.debug("Ref-1.6.5_CLI-Verification:-Verify SSID VLAN in General Settings");
		boolean clivlan=xmslocallib.verifyStringequals("SSIDVLAN", this.getClass().getSimpleName(), methodName, ssidVLANIDCLI, ssidVLANIDExp);
		
		//Read QOS through GUI
		ssidQoSGUI=xmslocallib.getDropDownText("QoS");
		App_Log.debug("Ref-1.6.6_GUI-Verification:-Verify SSID QOS in General Settings");
		boolean guiqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSGUI, ssidQoSExp);
		//Read QOS through SNMP
		ssidQoSSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidQoSOID);
		App_Log.debug("Ref-1.6.6_SNMP-Verification:-Verify SSID QOS in General Settings");
		boolean snmpqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSSNMP, ssidQoSExp);
		//Read QOS through CLI
		ssidQoSCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "QoS Level");
		App_Log.debug("Ref-1.6.6_CLI-Verification:-Verify SSID QOS in General Settings");
		boolean cliqos=xmslocallib.verifyStringequals("SSIDQoS", this.getClass().getSimpleName(), methodName, ssidQoSCLI, ssidQoSExp);
		
		//Read DHCP Pool through GUI
		ssidDHCPGUI=xmslocallib.getDropDownText("DHCP Pool");
		App_Log.debug("Ref-1.6.7_GUI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean guidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPGUI, ssidDHCPExp);
		//Read DHCP Pool through SNMP
		ssidDHCPSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidDHCPOID);
		if(ssidDHCPOID.contains(ssidDHCPSNMP.trim()))ssidDHCPSNMP="None";
		App_Log.debug("Ref-1.6.7_SNMP-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean snmpdhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPSNMP, ssidDHCPExp);
		//Read DHCP Pool through CLI
		ssidDHCPCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "DHCP Pool");
		App_Log.debug("Ref-1.6.7_CLI-Verification:-Verify SSID DHCP Pool in General Settings");
		boolean clidhcp=xmslocallib.verifyStringequals("SSIDDHCPPool", this.getClass().getSimpleName(), methodName, ssidDHCPCLI, ssidDHCPExp);
		
		//Read Filter List through GUI
		ssidFilterGUI=xmslocallib.getDropDownText("Filter List");
		App_Log.debug("Ref-1.6.8_GUI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean guifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterGUI, ssidFilterExp);
		//Read Filter List through SNMP
		ssidFilterSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFilterOID);
		if(ssidFilterOID.contains(ssidFilterSNMP.trim()))ssidFilterSNMP="None";
		App_Log.debug("Ref-1.6.8_SNMP-Verification:-Verify Default SSID Filter List in General Settings");
		boolean snmpfilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterSNMP, ssidFilterExp);
		//Read Filter List through CLIssidFilterSNMP
		ssidFilterCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Filter List");
		App_Log.debug("Ref-1.6.8_CLI-Verification:-Verify Default SSID Filter List in General Settings");
		boolean clifilter=xmslocallib.verifyStringequals("SSIDFilterList", this.getClass().getSimpleName(), methodName, ssidFilterCLI, ssidFilterExp);
		
		//Read Roaming Assist through GUI
		ssidRoamingGUI=xmslocallib.getDropDownText("Xirrus Roaming");
		App_Log.debug("Ref-1.6.9_GUI-Verification:-Verify SSID Roaming in General Settings");
		boolean guiroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingGUI, ssidRoamingExp);
		//Read Roaming Assist through SNMP
		ssidRoamingSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidRoamingOID);
		if(ssidRoamingSNMP.equals("0"))ssidRoamingSNMP="L2";else{if(ssidRoamingSNMP.equals("1"))ssidRoamingSNMP="L2 & L3"; else ssidRoamingSNMP="None";}
		App_Log.debug("Ref-1.6.9_SNMP-Verification:-Verify SSID Roaming in General Settings");
		boolean snmproaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingSNMP, ssidRoamingExp);
		//Read Roaming Assist through CLI
		ssidRoamingCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fast Roaming");
		if(ssidRoamingCLI.equals("Layer 2 only"))ssidRoamingCLI="L2";
		App_Log.debug("Ref-1.6.9_CLI-Verification:-Verify SSID Roaming in General Settings");
		boolean cliroaming=xmslocallib.verifyStringequals("SSIDRoaming", this.getClass().getSimpleName(), methodName, ssidRoamingCLI, ssidRoamingExp);
		
		//Read Fallback through GUI
		ssidFallbackGUI=xmslocallib.getDropDownText("Fallback");
		App_Log.debug("Ref-1.6.10_GUI-Verification:-Verify SSID Fallback in General Settings");
		boolean guifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackGUI, ssidFallbackExp);
		//Read Fallback through SNMP
		ssidFallbackSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidFallbackOID);
		if(ssidFallbackSNMP.equals("0"))ssidFallbackSNMP="None"; else ssidFallbackSNMP="Disable";
		App_Log.debug("Ref-1.6.10_SNMP-Verification:-Verify SSID Fallback in General Settings");
		boolean snmpfallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackSNMP, ssidFallbackExp);
		//Read Fallback through CLI
		ssidFallbackCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "Fallback").replace("SSID", "").trim();
		App_Log.debug("Ref-1.6.10_CLI-Verification:-Verify SSID Fallback in General Settings");
		boolean clifallback=xmslocallib.verifyStringequals("SSIDFallback", this.getClass().getSimpleName(), methodName, ssidFallbackCLI, ssidFallbackExp);
		
		//Read MDM through GUI
		ssidMDMGUI=xmslocallib.getDropDownText("Mobile Device Management");
		App_Log.debug("Ref-1.6.11_GUI-Verification:-Verify SSID MDM in General Settings");
		boolean guimdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMGUI, ssidMDMExp);
		//Read MDM through SNMP
		ssidMDMSNMP=ssidBrdcastSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, ssidMDMOID);
		if(ssidMDMSNMP.equals("0"))ssidMDMSNMP="None"; else ssidMDMSNMP="AirWatch";
		App_Log.debug("Ref-1.6.11_SNMP-Verification:-Verify SSID MDM in General Settings");
		boolean snmpmdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMSNMP, ssidMDMExp);
		//Read MDM through CLI
		ssidMDMCLI=xmslocallib.getValuebyCLI_SSIDs(arrayIPadd,ssidNameExp, "MDM Authentication");
		App_Log.debug("Ref-1.6.11_CLI-Verification:-Verify SSID MDM in General Settings");
		boolean climdm=xmslocallib.verifyStringequals("SSIDMDM", this.getClass().getSimpleName(), methodName, ssidMDMCLI, ssidMDMExp);
		App_Log.debug("End-Ref-1.6_Verify Rename SSID in General Settings");
		
	}
	
	@Test(priority=7)
	public void testCase_VerifySSIDNameFieldValidation() throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.7_Verify SSID Name Field Validation"); //PR21997
		//*****************************************************************
		ssidNameExp="xirrus";
		ssidStateExp="Enabled";
		ssidBrdcastExp="On";
		ssidBandExp="Both";
		ssidVLANExp="None";
		ssidQoSExp="2";
		ssidDHCPExp="None";
		ssidFilterExp="None";
		ssidRoamingExp="L2";
		ssidFallbackExp="None";
		ssidMDMExp="None";
		ssidVLANIDExp="None";
		String ssid=".1";
		ssidNameOID=SNMPOID.getProperty("SSIDNameOID")+ssid;
		ssidStateOID=SNMPOID.getProperty("SSIDStateOID")+ssid;
		ssidBrdcastOID=SNMPOID.getProperty("SSIDBroadCastOID")+ssid;
		ssidBandOID=SNMPOID.getProperty("SSIDBandOID")+ssid;
		ssidQoSOID=SNMPOID.getProperty("SSIDQoSOID")+ssid;
		ssidDHCPOID=SNMPOID.getProperty("SSIDDHCPPoolOID")+ssid;
		ssidFilterOID=SNMPOID.getProperty("SSIDFilterOID")+ssid;
		ssidRoamingOID=SNMPOID.getProperty("SSIDRoamingOID")+ssid;
		ssidFallbackOID=SNMPOID.getProperty("SSIDFallBackOID")+ssid;
		ssidMDMOID=SNMPOID.getProperty("SSIDMDMOID")+ssid;
		ssidVLANIDOID=SNMPOID.getProperty("SSIDVLANID")+ssid;
		
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
			xmslocallib.setTimeout(5);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("SSIDs", 2);
			xmslocallib.clickLink("SSID Management");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Warning for SSD name should not be empty
		String warningmsgExp= "Please enter a valid SSID name";
		String warningmsgAct="";
		xmslocallib.clickButton("Add SSID");
		if(xmslocallib.isWarningMessageDisplayed(warningmsgExp))warningmsgAct=warningmsgExp;else warningmsgAct="Message not displayed";
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify warning message for empty SSID name");
		boolean guiemptySSID=xmslocallib.verifyStringequals("EmptySSIDNameWarning", this.getClass().getSimpleName(), methodName, warningmsgAct, warningmsgExp);
		
		//Warning for SSID name limit 1-32
		warningmsgExp="Maximum length for an SSID name is 32 characters";
		ssidNameExp="Kartikisnothereleavemessageforhim"; //more than 32 char SSID name
		driver.findElement(By.xpath("//*[@name='configPanelWrap:configPanel:header:newSsid']")).sendKeys(ssidNameExp);
		xmslocallib.clickButton("Add SSID");
		if(xmslocallib.isWarningMessageDisplayed(warningmsgExp))warningmsgAct=warningmsgExp;else warningmsgAct="Message not displayed";
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify warning message for SSID Name max char limit 1-32 char");
		boolean guicharlimit=xmslocallib.verifyStringequals("SSIDNameCharLimitWarning", this.getClass().getSimpleName(), methodName, warningmsgAct, warningmsgExp);
		
		
		//Warning for existing SSID
		warningmsgExp= "SSID with name xirrus already exists or is pending delete.";
		ssidNameExp="xirrus"; //already existing SSID
		xmslocallib.clickLink("SSID Management");
		driver.findElement(By.xpath("//*[@name='configPanelWrap:configPanel:header:newSsid']")).sendKeys(ssidNameExp);
		xmslocallib.clickButton("Add SSID");
		if(xmslocallib.isWarningMessageDisplayed(warningmsgExp))warningmsgAct=warningmsgExp;else warningmsgAct="Message not displayed";
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify warning message existing SSID Name");
		boolean guicexisitSSID=xmslocallib.verifyStringequals("ExistingSSIDWarning", this.getClass().getSimpleName(), methodName, warningmsgAct, warningmsgExp);
	
		App_Log.debug("End-Ref-1.4_Verify SSID Name Field Validation");
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
