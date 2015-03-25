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
public class TC_ArrayConfig_Services_NetFlow extends TestSuiteBase{
	//Netflow Parameters
	private String versionSNMP;
	private String versionGUI;
	private String versionCLI;
	private String hostSNMP;
	private String hostGUI;
	private String hostCLI;
	private String portSNMP;
	private String portGUI;
	private String portCLI;
	private String versionExp;
	private String hostExp;
	private String portExp;
	private String versionOID;
	private String portOID;
	private String hostOID;

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
	public void testCase_VerifyArrayConfigServicesNetflowParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Services Netflow Parameters");
		//*****************************************************************
		versionExp="v5";
		hostExp=CONFIG.getProperty("NetflowHost");
		portExp=CONFIG.getProperty("NetflowPort");
		versionOID=SNMPOID.getProperty("NetflowEnableOID");
		portOID=SNMPOID.getProperty("NetflowPortOID");
		hostOID=SNMPOID.getProperty("NetflowHostOID");
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
			xmslocallib.clickLink("Netflow");
		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification on Netflow paramters configured into Array by SNMP, CLI and GUI
		//Get Netflow Version value configured into array 
		if(xmslocallib.isRadioButtonSelected("Netflow Version",2))versionGUI="v5";else versionGUI="NotMatch"; //Netflow v5
		//Verify Netflow v5 state configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Netflow State v5 value is in array");
		boolean guiver=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionGUI, versionExp);
		//Verify Netflow v5 state configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Netflow State v5 value is in array");
		versionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, versionOID);
		boolean snmpver=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionSNMP, "1");
		//Verify Netflow v5 state configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Netflow State v5 value is in array");
		versionCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Version");
		boolean cliver=xmslocallib.verifyStringequals("NetflowState", this.getClass().getSimpleName(), methodName, versionCLI, versionExp);	
	
		//Get Netflow host value configured into array 
		hostGUI= xmslocallib.getText("Netflow Collector Host");
		//Verify Netflow host configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Netflow host value in array");
		boolean guihost=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostGUI, hostExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Netflow host value in array");
		hostSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostOID);
		boolean snmphost=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostSNMP, hostExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Netflow host value in array");
		hostCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Host");
		boolean clihost=xmslocallib.verifyStringequals("NetflowHost", this.getClass().getSimpleName(), methodName, hostCLI, hostExp);
		//Get Netflow port value configured into array 
		portGUI= xmslocallib.getText("Netflow Collector Port");
		//Verify Netflow port configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify Services Netflow port value in array");
		boolean guiport=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portGUI, portExp);
		//Verify Netflow Host configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify Services Netflow port value in array");
		portSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, portOID);
		boolean snmpport=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portSNMP, portExp);
		//Verify Netflow Host Configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify Services Netflow port value in array");
		portCLI=xmslocallib.getValuebyCLI_NetFlow(arrayIPadd, "Collector Port");
		boolean cliport=xmslocallib.verifyStringequals("Netflowport", this.getClass().getSimpleName(), methodName, portCLI, portExp);
		App_Log.debug("End-Verify Array Config Services Netflow Parameters");	
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
