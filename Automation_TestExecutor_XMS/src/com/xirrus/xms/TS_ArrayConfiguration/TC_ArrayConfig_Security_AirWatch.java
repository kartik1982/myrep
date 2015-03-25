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
public class TC_ArrayConfig_Security_AirWatch extends TestSuiteBase{
	//AirWatch parameters
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
	public void testCase_VerifyArrayConfigSecurityAirWatchParameters() throws InterruptedException, IOException{
		App_Log.debug("Start-Verify Array Config Security AirWatch Parameters");
		//*****************************************************************
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
		methodName=new Object(){}.getClass().getEnclosingMethod().getName();
		testMethodResult="FAIL";
		//*****************************************************************
		//Navigate to VLAN Management panel and click Add VLAN button
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
		//Get AirWatch API Hostname value configured into array 
		hostnameURLGUI=  xmslocallib.getText("API URL/Hostname");
		//Verify AirWatch API Hostname value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Hostname value configured in array");
		boolean guihost=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLGUI, hostnameURLExp);
		//Verify AirWatch API Hostname value configured into array by SNMP
		App_Log.debug("Ref-1.9_SNMP-Verification:-Verify AirWatch API Hostname value is empty in array");
		hostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, hostnameURLOID);
		boolean snmphost=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLSNMP, hostnameURLExp);
		//Verify AirWatch API Hostname default value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Hostname value is Configured in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API URL");
		boolean clihost=xmslocallib.verifyStringequals("AirWatchHostname", this.getClass().getSimpleName(), methodName, hostnameURLCLI, hostnameURLExp);
		//Get AirWatch API user name value configured into array 
		userNameGUI=  xmslocallib.getText("API Username");
		//Verify AirWatch API user name default value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Username value is Configured in array");
		boolean guiuser=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameGUI, userNameExp);
		//Verify AirWatch API user name value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch API username value is Configured in array");
		userNameSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, userNameOID);
		boolean snmpuser=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, userNameSNMP, userNameExp);
		//Verify AirWatch API User name value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API username value is Configured in array");
		hostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API username");
		boolean cliuser=xmslocallib.verifyStringequals("AirWatchUsername", this.getClass().getSimpleName(), methodName, hostnameURLCLI, userNameExp);
		//Verify AirWatch API Password default value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Password default value is configured in array");
		passwordCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API password");
		boolean clipasswd=xmslocallib.verifyStringequals("AirWatchPassword", this.getClass().getSimpleName(), methodName, passwordCLI, passwordExp);
		//Get AirWatch API Key value configured into array 
		apiKeyGUI=  xmslocallib.getText("API Key");
		//Verify AirWatch API Key value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Key value in array");
		boolean guikey=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyGUI, apiKeyExp);
		//Verify AirWatch API Key default value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch API Key value in array");
		apiKeySNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiKeyOID);
		boolean snmpkey=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeySNMP, apiKeyExp);
		//Verify AirWatch API Key value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Key value in array");
		apiKeyCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API key");
		boolean clikey=xmslocallib.verifyStringequals("AirWatchAPIKey", this.getClass().getSimpleName(), methodName, apiKeyCLI, apiKeyExp);
		//Get AirWatch API Timeout value configured into array 
		apiTimeoutGUI=  xmslocallib.getText("API Timeout (in seconds)");
		//Verify AirWatch API Timeout default value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Timeout value in array");
		boolean guitimeout=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutGUI, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch API Timeout value in array");
		apiTimeoutSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, apiTimeoutOID);
		boolean snmptimeout=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutSNMP, apiTimeoutExp);
		//Verify AirWatch API Timeout default value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Timeout value  in array");
		apiTimeoutCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API timeout").replace("seconds", "").trim();
		boolean clitimeout=xmslocallib.verifyStringequals("AirWatchAPITimeout", this.getClass().getSimpleName(), methodName, apiTimeoutCLI, apiTimeoutExp);
		//Get AirWatch API Polling Period value configured into array 
		pollPeriodGUI=  xmslocallib.getText("API Poll Period (in seconds)");
		//Verify AirWatch API Polling Period value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Polling Period value  in array");
		boolean guiperiod=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodGUI, pollPeriodExp);
		//Verify AirWatch API Polling Period value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch API Polling Period value in array");
		pollPeriodSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, pollPeriodOID);
		boolean snmpperiod=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodSNMP, pollPeriodExp);
		//Verify AirWatch API Polling Period value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Polling Period value in array");
		pollPeriodCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API poll period").replace("seconds", "").trim();
		boolean cliperiod=xmslocallib.verifyStringequals("AirWatchAPIPollPeriod", this.getClass().getSimpleName(), methodName, pollPeriodCLI, pollPeriodExp);
		//Get AirWatch API Access Error value configured into array 
		accessErrorActionGUI= xmslocallib.getDropDownText("API Access Error Action");
		//Verify AirWatch API Access Error value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch API Access Error value in array");
		boolean guierror=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionGUI, accessErrorActionExp);
		//Verify AirWatch API Access Error value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch API Access Error value in array");
		accessErrorActionSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, accessErrorActionOID);
		boolean snmperror=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionSNMP, "1");
		//Verify AirWatch API Access Error value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch API Access Error value in array");
		accessErrorActionCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "API access error");
		boolean clierror=xmslocallib.verifyStringequals("AirWatchAPIAccessError", this.getClass().getSimpleName(), methodName, accessErrorActionCLI, accessErrorActionExp);
		//Get AirWatch Redirect URL default value configured into array 
		redirecthostnameURLGUI=  xmslocallib.getText("Redirect URL/Hostname");
		//Verify AirWatch Redirect URL default value configured into array by GUI
		App_Log.debug("GUI-Verification:-Verify AirWatch Redirect URL value in array");
		boolean guiredirect=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLGUI, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL value configured into array by SNMP
		App_Log.debug("SNMP-Verification:-Verify AirWatch Redirect URL value in array");
		redirecthostnameURLSNMP=xmslocallib.getValueBySNMP(arrayIPadd, communityStr, redirecthostnameURLOID);
		boolean snmpredirect=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLSNMP, redirecthostnameURLExp);
		//Verify AirWatch Redirect URL default value configured into array by CLI
		App_Log.debug("CLI-Verification:-Verify AirWatch Redirect URL value in array");
		redirecthostnameURLCLI=xmslocallib.getValuebyCLI_AirWatch(arrayIPadd, "Redirect URL");
		boolean cliredirect=xmslocallib.verifyStringequals("AirWatchRedirectURL", this.getClass().getSimpleName(), methodName, redirecthostnameURLCLI, redirecthostnameURLExp);
		
		App_Log.debug("End-Verify Array Config Security AirWatch Parameters");		
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
