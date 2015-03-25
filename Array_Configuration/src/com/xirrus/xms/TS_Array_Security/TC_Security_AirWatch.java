package com.xirrus.xms.TS_Array_Security;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.xirrus.xms.globallib.Test_Utility;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
@Test
public class TC_Security_AirWatch extends TestSuiteBase{
	private String hostnameURLSNMP;
	private String hostnameURLGUI;
	private String hostnameURLCLI;
	private String userNameSNMP;
	private String userNameGUI;
	private String userNameCLI;
	private String passwordSNMP;
	private String passwordGUI;
	private String passwordCLI;
	private String apiKeySNMP;
	private String apiKeyGUI;
	private String apiKeyCLI;
	private String apiTimeoutSNMP;
	private String apiTimeoutGUI;
	private String apiTimeoutCLI;
	private String pollPeriodSNMP;
	private String pollPeriodGUI;
	private String pollPeriodCLI;
	private String accessErrorActionSNMP;
	private String accessErrorActionGUI;
	private String accessErrorActionCLI;
	private String redirecthostnameURLSNMP;
	private String redirecthostnameURLGUI;
	private String redirecthostnameURLCLI;
	private String hostnameURLExp;
	private String userNameExp;
	private String passwordExp;
	private String apiKeyExp;
	private String apiTimeoutExp;
	private String pollPeriodExp;
	private String accessErrorActionExp;
	private String redirecthostnameURLExp;
	private String hostnameURLOID;
	private String userNameOID;
	private String passwordOID;
	private String apiKeyOID;
	private String apiTimeoutOID;
	private String pollPeriodOID;
	private String accessErrorActionOID;
	private String redirecthostnameURLOID;
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
		if(!Test_Utility.isTestCaseRunnable(suite_Array_Security_xls,  this.getClass().getSimpleName())){
			App_Log.debug("Test case "+this.getClass().getSimpleName()+" is skipped, was marked as No");
			throw new SkipException("Skipped Test case "+this.getClass().getSimpleName()+", was set as No");
		}else{
			App_Log.debug("Test Case Run Mode for "+this.getClass()+" is set as Yes, Test will execute!!!");
		}
		xmslocallib.postResults(this.getClass().getSimpleName());
	}
	//Ref-1.1_Verify AirWatch API Hostname default values
	@Test(priority=1)
	public void testCase_verifyAirWatchAPIHostnameDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.1_Verify AirWatch API Hostname default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("AirWatch");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
			throw new SkipException("Test Setup failed hence verification will not proceed for Test case"+this.getClass().getSimpleName());
		}
		//Verification AirWatch API Hostname default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Hostname default value configured into array 
		hostnameURLGUI=  xmslocallib.getText("API URL/Hostname");
		//Verify AirWatch API Hostname default value configured into array by GUI
		App_Log.debug("Ref-1.1_GUI-Verification:-Verify AirWatch API Hostname default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLGUI, hostnameURLExp);
		//Verify AirWatch API Hostname default value configured into array by SNMP
		App_Log.debug("Ref-1.1_SNMP-Verification:-Verify AirWatch API Hostname default value is empty in array");
		hostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostnameURLOID);
		if(hostnameURLSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			hostnameURLSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLSNMP, hostnameURLExp);
		//Verify AirWatch API Hostname default value configured into array by CLI
		App_Log.debug("Ref-1.1_CLI-Verification:-Verify AirWatch API Hostname default value is empty in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API URL");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLCLI, hostnameURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);		
		App_Log.debug("End-Ref-1.1_Verify AirWatch API Hostname default value");
	}
	//Ref-1.2_Verify AirWatch API Username default value
	@Test(priority=2)
	public void testCase_verifyAirWatchAPIUsernameDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.2_Verify AirWatch API username default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API user name default value in Array by SNMP, CLI and GUI
		//Get AirWatch API user name default value configured into array 
		userNameGUI=  xmslocallib.getText("API Username");
		//Verify AirWatch API user name default value configured into array by GUI
		App_Log.debug("Ref-1.2_GUI-Verification:-Verify AirWatch API Username default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameGUI, userNameExp);
		//Verify AirWatch API user name default value configured into array by SNMP
		App_Log.debug("Ref-1.2_SNMP-Verification:-Verify AirWatch API username default value is empty in array");
		userNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, userNameOID);
		if(userNameSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			userNameSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameSNMP, userNameExp);
		//Verify AirWatch API User name default value configured into array by CLI
		App_Log.debug("Ref-1.2_CLI-Verification:-Verify AirWatch API username default value is empty in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API username");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, hostnameURLCLI, userNameExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-1.2_Verify AirWatch API username default value");
	}
	//Ref-1.3_Verify AirWatch API Password default value
	@Test(priority=3)
	public void testCase_verifyAirWatchAPIPasswordDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.3_Verify AirWatch API Password default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Password default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Password default value configured into array 
		passwordGUI=  xmslocallib.getText("API Password/Verify Password");
		//Verify AirWatch API Password default value configured into array by GUI
		App_Log.debug("Ref-1.3_GUI-Verification:-Verify AirWatch API Password default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchPassword", this.getClass().getSimpleName(), methodName, passwordGUI, passwordExp);
		//Verify AirWatch API Password default value configured into array by SNMP
		App_Log.debug("Ref-1.3_SNMP-Verification:-Verify AirWatch API Password default value is empty in array");
		passwordSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, passwordOID);
		if(passwordSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			passwordSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchPassword", this.getClass().getSimpleName(), methodName, passwordSNMP, passwordExp);
		//Verify AirWatch API Password default value configured into array by CLI
		App_Log.debug("Ref-1.3_CLI-Verification:-Verify AirWatch API Password default value is empty in array");
		passwordCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API username");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchPassword", this.getClass().getSimpleName(), methodName, passwordCLI, passwordExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-1.3_Verify AirWatch API Password default value");
	}
	//Ref-1.4_Verify AirWatch API Key default value
	@Test(priority=4)
	public void testCase_verifyAirWatchAPIKeyDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.4_Verify AirWatch API Key default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Key default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Key default value configured into array 
		apiKeyGUI=  xmslocallib.getText("API Key");
		//Verify AirWatch API Key default value configured into array by GUI
		App_Log.debug("Ref-1.4_GUI-Verification:-Verify AirWatch API Key default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyGUI, apiKeyExp);
		//Verify AirWatch API Key default value configured into array by SNMP
		App_Log.debug("Ref-1.4_SNMP-Verification:-Verify AirWatch API Key default value is empty in array");
		apiKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiKeyOID);
		if(apiKeySNMP.contains("1.3.6.1.4.1.21013.1.2")){
			apiKeySNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeySNMP, apiKeyExp);
		//Verify AirWatch API Key default value configured into array by CLI
		App_Log.debug("Ref-1.4_CLI-Verification:-Verify AirWatch API Key default value is empty in array");
		apiKeyCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API key");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyCLI, apiKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.4_Verify AirWatch API Key default value");
	}
	//Ref-1.5_Verify AirWatch API Timeout default value
	@Test(priority=5)
	public void testCase_verifyAirWatchAPITimeoutDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.5_Verify AirWatch API Timeout default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Timeout default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Timeout default value configured into array 
		apiTimeoutGUI=  xmslocallib.getText("API Timeout (in seconds)");
		//Verify AirWatch API Timeout default value configured into array by GUI
		App_Log.debug("Ref-1.5_GUI-Verification:-Verify AirWatch API Timeout default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutGUI, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by SNMP
		App_Log.debug("Ref-1.5_SNMP-Verification:-Verify AirWatch API Timeout default value in array");
		apiTimeoutSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiTimeoutOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutSNMP, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by CLI
		App_Log.debug("Ref-1.5_CLI-Verification:-Verify AirWatch API Timeout default value  in array");
		apiTimeoutCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API timeout").replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutCLI, apiTimeoutExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.5_Verify AirWatch API Timeout default value");
	}
	//Ref-1.6_Verify AirWatch API Poll Period default value
	@Test(priority=6)
	public void testCase_verifyAirWatchAPIPollPeriodDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.6_Verify AirWatch API Poll Period default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Polling Period default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Polling Period default value configured into array 
		pollPeriodGUI=  xmslocallib.getText("API Poll Period (in seconds)");
		//Verify AirWatch API Polling Period default value configured into array by GUI
		App_Log.debug("Ref-1.6_GUI-Verification:-Verify AirWatch API Polling Period default value  in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodGUI, pollPeriodExp);
		//Verify AirWatch API Polling Period default value configured into array by SNMP
		App_Log.debug("Ref-1.6_SNMP-Verification:-Verify AirWatch API Polling Period default value in array");
		pollPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, pollPeriodOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodSNMP, pollPeriodExp);
		//Verify AirWatch API Polling Period default value configured into array by CLI
		App_Log.debug("Ref-1.6_CLI-Verification:-Verify AirWatch API Polling Period default value in array");
		pollPeriodCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API poll period").replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodCLI, pollPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.6_Verify AirWatch API Poll Period default value");
	}
	//Ref-1.7_Verify AirWatch API Access Error default value
	@Test(priority=7)
	public void testCase_verifyAirWatchAPIAccessErrorDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.7_Verify AirWatch API Access Error default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Access Error default value in Array by SNMP, CLI and GUI
		//Get AirWatch API Access Error default value configured into array 
		accessErrorActionGUI= xmslocallib.getDropDownText("API Access Error Action");
		//Verify AirWatch API Access Error default value configured into array by GUI
		App_Log.debug("Ref-1.7_GUI-Verification:-Verify AirWatch API Access Error default value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionGUI, accessErrorActionExp);
		//Verify AirWatch API Access Error default value configured into array by SNMP
		App_Log.debug("Ref-1.7_SNMP-Verification:-Verify AirWatch API Access Error default value in array");
		accessErrorActionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, accessErrorActionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionSNMP, "0");
		//Verify AirWatch API Access Error default value configured into array by CLI
		App_Log.debug("Ref-1.7_CLI-Verification:-Verify AirWatch API Access Error default value in array");
		accessErrorActionCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API access error");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionCLI, accessErrorActionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.7_Verify AirWatch API Access Error default value");
	}
	//Ref-1.8_Verify AirWatch Redirect URL or hostname default value
	@Test(priority=8)
	public void testCase_verifyAirWatchRedirectURLDefaultValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.8_Verify AirWatch Reditrect URL default value");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch Redirect URL default value in Array by SNMP, CLI and GUI
		//Get AirWatch Redirect URL default value configured into array 
		redirecthostnameURLGUI=  xmslocallib.getText("Redirect URL/Hostname");
		//Verify AirWatch Redirect URL default value configured into array by GUI
		App_Log.debug("Ref-1.8_GUI-Verification:-Verify AirWatch Redirect URL default value is empty in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLGUI, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL default value configured into array by SNMP
		App_Log.debug("Ref-1.8_SNMP-Verification:-Verify AirWatch Redirect URL default value is empty in array");
		redirecthostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, redirecthostnameURLOID);
		if(redirecthostnameURLSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			redirecthostnameURLSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLSNMP, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL default value configured into array by CLI
		App_Log.debug("Ref-1.8_CLI-Verification:-Verify AirWatch Redirect URL default value is empty in array");
		redirecthostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "Redirect URL");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLCLI, redirecthostnameURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.8_Verify AirWatch Reditrect URL default value");
	}
		
	//Ref-1.9_Verify AirWatch API Hostname updated value
	@Test(priority=9)
	public void testCase_verifyAirWatchAPIHostnameUpdatedValue()throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.9_Verify AirWatch API Hostname Updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("AirWatch");
			xmslocallib.setText("API URL/Hostname", hostnameURLExp);
			xmslocallib.setText("API Username", userNameExp);
			xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
			xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
			xmslocallib.setText("API Key", apiKeyExp);
			xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
			xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
			xmslocallib.selectDropDown("API Access Error Action", accessErrorActionExp);
			xmslocallib.setText("Redirect URL/Hostname", redirecthostnameURLExp);
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
			throw new SkipException("Test Setup failed hence verification will not proceed for Test case"+this.getClass().getSimpleName());
		}
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("AirWatch");

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}
		//Verification AirWatch API Hostname value in Array by SNMP, CLI and GUI
		//Get AirWatch API Hostname value configured into array 
		hostnameURLGUI=  xmslocallib.getText("API URL/Hostname");
		//Verify AirWatch API Hostname value configured into array by GUI
		App_Log.debug("Ref-1.9_GUI-Verification:-Verify AirWatch API Hostname value is updated in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLGUI, hostnameURLExp);
		//Verify AirWatch API Hostname value configured into array by SNMP
		App_Log.debug("Ref-1.9_SNMP-Verification:-Verify AirWatch API Hostname value is updated in array");
		hostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostnameURLOID);
		if(hostnameURLSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			hostnameURLSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLSNMP, hostnameURLExp);
		//Verify AirWatch API Hostname default value configured into array by CLI
		App_Log.debug("Ref-1.9_CLI-Verification:-Verify AirWatch API Hostname value is updated in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API URL");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLCLI, hostnameURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.9_Verify AirWatch API Hostname Updated value");
	}
	//Ref-1.10_Verify AirWatch API Username updated value
	@Test(priority=10)
	public void testCase_verifyAirWatchAPIUsernameUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.10_Verify AirWatch API username updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API user name value in Array by SNMP, CLI and GUI
		//Get AirWatch API user name value configured into array 
		userNameGUI=  xmslocallib.getText("API Username");
		//Verify AirWatch API user name default value configured into array by GUI
		App_Log.debug("Ref-1.10_GUI-Verification:-Verify AirWatch API Username value is updated in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameGUI, userNameExp);
		//Verify AirWatch API user name value configured into array by SNMP
		App_Log.debug("Ref-1.10_SNMP-Verification:-Verify AirWatch API username value is updated in array");
		userNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, userNameOID);
		if(userNameSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			userNameSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameSNMP, userNameExp);
		//Verify AirWatch API User name value configured into array by CLI
		App_Log.debug("Ref-1.10_CLI-Verification:-Verify AirWatch API username value is updated in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API username");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, hostnameURLCLI, userNameExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);	
		App_Log.debug("End-Ref-1.10_Verify AirWatch API username updated value");
	}
	//Ref-1.11_Verify AirWatch API Password updated value
	@Test(priority=11)
	public void testCase_verifyAirWatchAPIPasswordUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.11_Verify AirWatch API Password updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//******************************************************************
		//Verify AirWatch API Password default value configured into array by CLI
		App_Log.debug("Ref-1.11_CLI-Verification:-Verify AirWatch API Password default value is updated in array");
		passwordCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API password");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchPassword", this.getClass().getSimpleName(), methodName, passwordCLI, passwordExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.11_Verify AirWatch API Password updated value");
	}
	//Ref-1.12_Verify AirWatch API Key updated value
	@Test(priority=12)
	public void testCase_verifyAirWatchAPIKeyUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.12_Verify AirWatch API Key updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Key value in Array by SNMP, CLI and GUI
		//Get AirWatch API Key value configured into array 
		apiKeyGUI=  xmslocallib.getText("API Key");
		//Verify AirWatch API Key value configured into array by GUI
		App_Log.debug("Ref-1.12_GUI-Verification:-Verify AirWatch API Key value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyGUI, apiKeyExp);
		//Verify AirWatch API Key default value configured into array by SNMP
		App_Log.debug("Ref-1.12_SNMP-Verification:-Verify AirWatch API Key value in array");
		apiKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiKeyOID);
		if(apiKeySNMP.contains("1.3.6.1.4.1.21013.1.2")){
			apiKeySNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeySNMP, apiKeyExp);
		//Verify AirWatch API Key value configured into array by CLI
		App_Log.debug("Ref-1.12_CLI-Verification:-Verify AirWatch API Key value in array");
		apiKeyCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API key");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyCLI, apiKeyExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.12_Verify AirWatch API Key updated value");
	}
	//Ref-1.13_Verify AirWatch API Timeout updated value
	@Test(priority=13)
	public void testCase_verifyAirWatchAPITimeoutUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.13_Verify AirWatch API Timeout updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Timeout value in Array by SNMP, CLI and GUI
		//Get AirWatch API Timeout value configured into array 
		apiTimeoutGUI=  xmslocallib.getText("API Timeout (in seconds)");
		//Verify AirWatch API Timeout default value configured into array by GUI
		App_Log.debug("Ref-1.13_GUI-Verification:-Verify AirWatch API Timeout value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutGUI, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by SNMP
		App_Log.debug("Ref-1.13_SNMP-Verification:-Verify AirWatch API Timeout value in array");
		apiTimeoutSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiTimeoutOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutSNMP, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by CLI
		App_Log.debug("Ref-1.13_CLI-Verification:-Verify AirWatch API Timeout value  in array");
		apiTimeoutCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API timeout").replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutCLI, apiTimeoutExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.13_Verify AirWatch API Timeout updated value");
	}
	//Ref-1.14_Verify AirWatch API Poll Period Updated value
	@Test(priority=14)
	public void testCase_verifyAirWatchAPIPollPeriodUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.14_Verify AirWatch API Poll Period Updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Polling Period value in Array by SNMP, CLI and GUI
		//Get AirWatch API Polling Period value configured into array 
		pollPeriodGUI=  xmslocallib.getText("API Poll Period (in seconds)");
		//Verify AirWatch API Polling Period value configured into array by GUI
		App_Log.debug("Ref-1.14_GUI-Verification:-Verify AirWatch API Polling Period value  in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodGUI, pollPeriodExp);
		//Verify AirWatch API Polling Period value configured into array by SNMP
		App_Log.debug("Ref-1.14_SNMP-Verification:-Verify AirWatch API Polling Period value in array");
		pollPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, pollPeriodOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodSNMP, pollPeriodExp);
		//Verify AirWatch API Polling Period value configured into array by CLI
		App_Log.debug("Ref-1.14_CLI-Verification:-Verify AirWatch API Polling Period value in array");
		pollPeriodCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API poll period").replace("seconds", "").trim();
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodCLI, pollPeriodExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.14_Verify AirWatch API Poll Period Updated value");
	}
	//Ref-1.15_Verify AirWatch API Access Error Updated value
	@Test(priority=15)
	public void testCase_verifyAirWatchAPIAccessErrorUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.15_Verify AirWatch API Access Error Updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch API Access Error value in Array by SNMP, CLI and GUI
		//Get AirWatch API Access Error value configured into array 
		accessErrorActionGUI= xmslocallib.getDropDownText("API Access Error Action");
		//Verify AirWatch API Access Error value configured into array by GUI
		App_Log.debug("Ref-1.15_GUI-Verification:-Verify AirWatch API Access Error value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionGUI, accessErrorActionExp);
		//Verify AirWatch API Access Error value configured into array by SNMP
		App_Log.debug("Ref-1.15_SNMP-Verification:-Verify AirWatch API Access Error value in array");
		accessErrorActionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, accessErrorActionOID);
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionSNMP, "1");
		//Verify AirWatch API Access Error value configured into array by CLI
		App_Log.debug("Ref-1.15_CLI-Verification:-Verify AirWatch API Access Error value in array");
		accessErrorActionCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API access error");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionCLI, accessErrorActionExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.15_Verify AirWatch API Access Error Updated value");
	}
	//Ref-1.16_Verify AirWatch Redirect URL or hostname updated value
	@Test(priority=16)
	public void testCase_verifyAirWatchRedirectURLUpdatedValue()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.16_Verify AirWatch Reditrect URL Updated value");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//Verification AirWatch Redirect URL value in Array by SNMP, CLI and GUI
		//Get AirWatch Redirect URL default value configured into array 
		redirecthostnameURLGUI=  xmslocallib.getText("Redirect URL/Hostname");
		//Verify AirWatch Redirect URL default value configured into array by GUI
		App_Log.debug("Ref-1.16_GUI-Verification:-Verify AirWatch Redirect URL value in array");
		boolean guiResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLGUI, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL value configured into array by SNMP
		App_Log.debug("Ref-1.16_SNMP-Verification:-Verify AirWatch Redirect URL value in array");
		redirecthostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, redirecthostnameURLOID);
		if(redirecthostnameURLSNMP.contains("1.3.6.1.4.1.21013.1.2")){
			redirecthostnameURLSNMP="";
		}
		boolean snmpResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLSNMP, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL default value configured into array by CLI
		App_Log.debug("Ref-1.16_CLI-Verification:-Verify AirWatch Redirect URL value in array");
		redirecthostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "Redirect URL");
		boolean cliResult=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLCLI, redirecthostnameURLExp);
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(guiResult&&snmpResult&&cliResult)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.16_Verify AirWatch Reditrect URL Updated value");
	}
	//Ref-1.17_Verify AirWatch API Hostname Field Validation
	@Test(priority=17)
	public void testCase_verifyAirWatchAPIHostnameFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.17_Verify AirWatch API Hostname filed validation");
		//******************************************************************
	    hostnameURLExp="";
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		//256 characters for server hostname
		hostnameURLExp="iamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestxmsserveriamheretotestx";
		try{	
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			xmslocallib.clickLink(arrayHostName);
			xmslocallib.setTimeout(10);
			xmslocallib.clickLink("Configuration");
			xmslocallib.clickLink("Security");
			xmslocallib.clickLink("AirWatch");
			xmslocallib.setText("API URL/Hostname", hostnameURLExp);
			xmslocallib.clickButton("Apply Config");
			xmslocallib.setTimeout(90);

		}catch (InterruptedException e) {
			App_Log.error("Error:-"+e);
			screencaptureFileName="SetupError-"+this.getClass().getSimpleName()+"_"+methodName+".png";
			xmslocallib.captureScreenshot(screencaptureFileName);
		}

		//Verify warning message	
		App_Log.debug("Ref-1.17_GUI-Verification:-Verify warning message for API URL/Hostname 0-255 charaters limit");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="AirWatch API URL cannot be more than 255 characters";
		boolean apiURLWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiURLWarning){
			xmslocallib.verifyStringequals("AirWatchAPIURLHostnameLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIURLHostnameLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		hostnameURLExp="I am Invalid hostname";
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message		
		App_Log.debug("Ref-1.17_GUI-Verification:-Verify warning message for API URL/Hostname for Invalid Host name");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="AirWatch API URL must have a valid URL or Hostname";
		boolean apiURLInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiURLInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPIURLHostnameInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIURLHostnameInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		hostnameURLExp="254.100.60.30"; //Reserved IP name
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message	
		App_Log.debug("Ref-1.17_GUI-Verification:-Verify warning message for API URL/Hostname for Invalid IP address");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="AirWatch API URL must have a valid URL or Hostname";
			boolean apiURLInvalidIPWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
			if(apiURLInvalidIPWarning){
				xmslocallib.verifyStringequals("AirWatchAPIURLHostnameInvalidIPWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
			}
			else{
				xmslocallib.verifyStringequals("AirWatchAPIURLHostnameInvalidIPWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
			}
			//If GUI, CLI and SNMP result are passed mark test method as passed.
			if(apiURLWarning&&apiURLInvalidWarning&&apiURLInvalidIPWarning)
				testMethodResult="PASS";
			//Report Test Result into Test Result file
			testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
			xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.17_Verify AirWatch API Hostname filed validation");
	}
	//Ref-1.18_Verify AirWatch API Username Field Validation
	@Test(priority=18)
	public void testCase_verifyAirWatchAPIUsernameFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.18_Verify AirWatch API username Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");;
	    userNameExp="";
	    passwordExp="";
	    apiKeyExp="";
	    apiTimeoutExp="5";
	    pollPeriodExp="5";
	    accessErrorActionExp="Block";
	    redirecthostnameURLExp="";
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		userNameExp=""; //username empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message	
		App_Log.debug("Ref-1.18_GUI-Verification:-Verify warning message for API User Name empty when URL is present");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="AirWatch API Username is required";
		boolean apiUsernameEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiUsernameEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		userNameExp="94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz"; //username more than 50 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message	
		App_Log.debug("Ref-1.18_GUI-Verification:-Verify warning message for API User Name for Character limit MAX 50 char");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch API Username cannot be more than 50 characters";
		boolean apiUsernameLimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiUsernameLimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		userNameExp="kartik aiyar"; //Invalid username
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.18_GUI-Verification:-Verify warning message for API User Name for Invalid");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch API Username is Invalid";
		boolean apiUsernameInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiUsernameInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIURLusernameInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiUsernameEmptyWarning&&apiUsernameLimitWarning&&apiUsernameInvalidWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.18_Verify AirWatch API username Field Validation");
	}
	//Ref-1.19_Verify AirWatch API Password field Validation
	@Test(priority=19)
	public void testCase_verifyAirWatchAPIPasswordFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.19_Verify AirWatch API Password Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		passwordExp=""; //Password empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.clickButton("Clear");
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.19_GUI-Verification:-Verify warning message for API Password not empty when URL username is present");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="AirWatch API Password is required";
		boolean apiPasswdEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPasswdEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPasswdEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPasswdEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		passwordExp="Kartik@123"; //Password empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.clickButton("Clear");
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.19_GUI-Verification:-Verify warning message for API verify Password not empty when URL username is present");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch API Password Verify is required";
		boolean apiVerPasswdEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiVerPasswdEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIVerPasswdEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIVerPasswdEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		passwordExp="Kartik@123"; //Password empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", "funtime", 2);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.19_GUI-Verification:-Verify warning message for API Password and verify Password mismatch");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="Password confirmation value does not match";
		boolean apiPasswdMismatchWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPasswdMismatchWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPasswdMismatchWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPasswdMismatchWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		passwordExp="94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz"; //Password more than 50 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.19_GUI-Verification:-Verify warning message for API Password char limit 1-50 char");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch API Password cannot be more than 50 characters";
		boolean apiPasswdlimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPasswdlimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPasswdlimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPasswdlimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiPasswdEmptyWarning&&apiPasswdMismatchWarning&&apiPasswdlimitWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.19_Verify AirWatch API Password Field Validation");
	}
	//Ref-1.20_Verify AirWatch API Key Field Validation
	@Test(priority=20)
	public void testCase_verifyAirWatchAPIKeyFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.20_Verify AirWatch API Key Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		apiKeyExp=""; //API Key empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.20_GUI-Verification:-Verify warning message for API Key should not be empty when user name and password is there");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="AirWatch API Key is required";
		boolean apiKeyEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiKeyEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIKeyEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		apiKeyExp="94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz"; //API Key more than 50 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.20_GUI-Verification:-Verify warning message for API Key accept API Key max 50 character");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="AirWatch API Key cannot be more than 50 characters";
		boolean apiKeyLimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiKeyLimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPIKeyLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIKeyLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		apiKeyExp="Sunday Monday"; //API Key more than 50 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.20_GUI-Verification:-Verify warning message for API Key Invalid");
		//Verify that XMS warning message is displaying and parameter not configured
		 warningmessage="AirWatch API Key is Invalid";
		boolean apiKeyInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiKeyInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPIKeyInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIKeyInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiKeyEmptyWarning&&apiKeyLimitWarning&&apiKeyInvalidWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.20_Verify AirWatch API Key Field Validation");
	}
	//Ref-1.21_Verify AirWatch API Timeout Field Validation
	@Test(priority=21)
	public void testCase_verifyAirWatchAPITimeoutFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.21_Verify AirWatch API Timeout Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		apiTimeoutExp=""; //API Timeout empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.21_GUI-Verification:-Verify warning message for API Timeout should not empty");
		//Verify that XMS warning message is displaying and parameter not configured
		 String warningmessage="AirWatch API Timeout is required";
		boolean apiTimeoutEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiTimeoutEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPITimeoutEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPITimeoutEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		apiTimeoutExp="21"; //API Timeout outside boundary 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.21_GUI-Verification:-Verify warning message for API Timeout between 1-20 sec");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="API Timeout must be between 1 and 20";
		boolean apiTimeoutLimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiTimeoutLimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPITimeoutLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPITimeoutLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		apiTimeoutExp="Test"; //API Timeout Non-Integer 
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.21_GUI-Verification:-Verify warning message for API Timeout Non-Integer value");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="API Timeout must be valid number between 1 and 20";
		boolean apiTimeoutInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiTimeoutInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPITimeoutInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPITimeoutInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiTimeoutEmptyWarning&&apiTimeoutLimitWarning&&apiTimeoutInvalidWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.21_Verify AirWatch API Timeout Field Validation");
	}
	//Ref-1.22_Verify AirWatch API Poll Period default value
	@Test(priority=22)
	public void testCase_verifyAirWatchAPIPollPeriodFieldValidation()throws InterruptedException, IOException{
		App_Log.debug("Start-Ref-1.22_Verify AirWatch API Poll Period Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		pollPeriodExp=""; //API Timeout empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.22_GUI-Verification:-Verify warning message for API Poll Period should not empty");
		//Verify that XMS warning message is displaying and parameter not configured
		 String warningmessage="AirWatch API Poll Period is required";
		boolean apiPollPeriodEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPollPeriodEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		pollPeriodExp="0"; //API Timeout empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.22_GUI-Verification:-Verify warning message API Poll Period must be between 1 and 3600");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="API Poll Period must be between 1 and 3600";
		boolean apiPollPeriodLimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPollPeriodLimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		pollPeriodExp="Kartik"; //API Timeout empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.22_GUI-Verification:-Verify warning message API Poll Period must valid Integer");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="API Poll Period must be valid number between 1 and 3600";
		boolean apiPollPeriodInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiPollPeriodInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIPollPeriodInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiPollPeriodEmptyWarning&&apiPollPeriodLimitWarning&&apiPollPeriodInvalidWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.22_Verify AirWatch API Poll Period Field Validation");
	}
	
	//Ref-1.23_Verify AirWatch Redirect URL or hostname FieldValidation
	@Test(priority=23)
	public void testCase_verifyAirWatchRedirectURLFieldValidation()throws InterruptedException, IOException, InvocationTargetException{
		App_Log.debug("Start-Ref-1.23_Verify AirWatch Reditrect URL Field Validation");
		//******************************************************************
	    hostnameURLExp=CONFIG.getProperty("APIHostName");
	    userNameExp=CONFIG.getProperty("APIUsername");
	    passwordExp=CONFIG.getProperty("APIPassword");
	    apiKeyExp=CONFIG.getProperty("APIKey");
	    apiTimeoutExp="10";
	    pollPeriodExp="600";
	    accessErrorActionExp="Allow";
	    redirecthostnameURLExp=CONFIG.getProperty("RedirectHostName");
	    hostnameURLOID=SNMPOID.getProperty("AirWatchApiURL");
	    userNameOID=SNMPOID.getProperty("AirWatchApiUserName");;
	    passwordOID=SNMPOID.getProperty("AirWatchApiPassword");;
	    apiKeyOID=SNMPOID.getProperty("AirWatchApiKey");;
	    apiTimeoutOID=SNMPOID.getProperty("AirWatchApiTimeout");;
	    pollPeriodOID=SNMPOID.getProperty("AirWatchApiPollPeriod");;
	    accessErrorActionOID=SNMPOID.getProperty("AirWatchApiAcessError");;
	    redirecthostnameURLOID=SNMPOID.getProperty("AirWatchApiRedirectURL");;
	    arrayIPadd=CONFIG.getProperty("ArrayIPAddress");
		arrayHostName=CONFIG.getProperty("ArrayHostName");
		communityStr=CONFIG.getProperty("community");
		xmsserverIP=CONFIG.getProperty("XMSServerIPAddress");
		xmsuser=CONFIG.getProperty("XMSUsername");
		xmspasswd=CONFIG.getProperty("XMSPassword");
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();;
		testMethodResult="FAIL";
		//******************************************************************
		redirecthostnameURLExp=""; //API Timeout empty
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.selectDropDown("API Access Error Action", accessErrorActionExp);
		xmslocallib.setText("Redirect URL/Hostname", redirecthostnameURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.23_GUI-Verification:-Verify warning message Redirect URL should not be empty");
		//Verify that XMS warning message is displaying and parameter not configured
		String warningmessage="AirWatch Station Redirect URL is required";
		boolean apiRedirectURLEmptyWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiRedirectURLEmptyWarning){
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLEmptyWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		redirecthostnameURLExp="94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyz94444169a4196da411500946abcdefgyhijklmnopqrstuvwxyzc"; //Redirect URL more than 255 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.selectDropDown("API Access Error Action", accessErrorActionExp);
		xmslocallib.setText("Redirect URL/Hostname", redirecthostnameURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.23_GUI-Verification:-Verify warning message Redirect URL cannot be more than 255 char");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch Station Redirect URL cannot be more than 255 characters";
		boolean apiRedirectURLLimitWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiRedirectURLLimitWarning){
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLLimitWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		redirecthostnameURLExp="<google>.com"; //Redirect URL more than 255 char
		//Navigate to Array Configuration
		navigatelib.ConfigureAccesssPoint();
		//Navigate to Array Services Configuration
		xmslocallib.clickLink(arrayHostName);
		xmslocallib.setTimeout(10);
		xmslocallib.clickLink("Configuration");
		xmslocallib.clickLink("Security");
		xmslocallib.clickLink("AirWatch");
		xmslocallib.setText("API URL/Hostname", hostnameURLExp);
		xmslocallib.setText("API Username", userNameExp);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 1);
		xmslocallib.setText("API Password/Verify Password", passwordExp, 2);
		xmslocallib.setText("API Key", apiKeyExp);
		xmslocallib.setText("API Timeout (in seconds)", apiTimeoutExp);
		xmslocallib.setText("API Poll Period (in seconds)", pollPeriodExp);
		xmslocallib.selectDropDown("API Access Error Action", accessErrorActionExp);
		xmslocallib.setText("Redirect URL/Hostname", redirecthostnameURLExp);
		xmslocallib.clickButton("Apply Config");
		xmslocallib.setTimeout(90);
		//Verify warning message
		App_Log.debug("Ref-1.23_GUI-Verification:-Verify warning message Redirect URL Invalid");
		//Verify that XMS warning message is displaying and parameter not configured
		warningmessage="AirWatch Station Redirect URL must have a valid URL or Hostname";
		boolean apiRedirectURLInvalidWarning=xmslocallib.isWarningMessageDisplayed(warningmessage);
		if(apiRedirectURLInvalidWarning){
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning Displayed");
		}
		else{
			xmslocallib.verifyStringequals("AirWatchAPIRedirectURLInvalidWarning", this.getClass().getSimpleName(), methodName, "Warning Displayed", "Warning NOT Displayed");
		}
		//If GUI, CLI and SNMP result are passed mark test method as passed.
		if(apiRedirectURLEmptyWarning&&apiRedirectURLLimitWarning&&apiRedirectURLInvalidWarning)
			testMethodResult="PASS";
		//Report Test Result into Test Result file
		testMethodResult=this.getClass().getSimpleName()+"|"+methodName+"|"+testMethodResult;
		xmslocallib.postResults(testMethodResult);
		App_Log.debug("End-Ref-1.23_Verify AirWatch Reditrect URL Field Validation");
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
