package com.xirrus.xms.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;
import com.xirrus.xms.globallib.*;
import com.xirrus.xms.locallib.LocalLib;
import com.xirrus.xms.locallib.NavigateTo;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import junit.framework.Assert;

//import com.xirrus.xms.localLib.DataFile_Reader;
//import com.xirrus.xms.localLib.DataSheet_Reader;
//import com.xirrus.xms.localLib.ErrorUtil;

public class TestBase {

	public static Logger App_Log = null;
	public static Logger Result_Log=null;
	public static Properties CONFIG = null;
	public static Properties OR = null;
	public static Properties PATH=null;
	public static Properties SNMPOID=null;
	//test Master Suite xls file
	public static DataFile_Reader test_Suite_xls = null;
	//Feature test Suite xls file
	public static DataFile_Reader suite_ArrayConfiguration_xls = null;
	public static DataFile_Reader suite_ProfileConfiguration_xls = null;
	public static DataFile_Reader suite_WirelessConfiguration_xls = null;
	public static DataFile_Reader suite_TemplateConfiguration_xls = null;
	public static boolean isInitialized = false;
	public static WebDriver driver= null;
	public Actions actions;
//	NavigateTo navigatelib = new NavigateTo();
	public void initialize() throws Exception {
		if (!isInitialized) {
			
			// Initialize Logger
			App_Log = Logger.getLogger("devpinoyLogger");
			String log4jConfPath = System.getProperty("user.dir")+"\\src\\log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
			// Initialize Config property files
			App_Log.debug("*******Loading Property files- Config, SNMPOID, OR and PATH*******");
			FileInputStream fileConfig = new FileInputStream(
					System.getProperty("user.dir")
							+ "\\src\\com\\xirrus\\xms\\config\\Config.properties");
			FileInputStream fileOR = new FileInputStream(
					System.getProperty("user.dir")
							+ "\\src\\com\\xirrus\\xms\\config\\OR.properties");
			FileInputStream fileSNMPOID = new FileInputStream(
					System.getProperty("user.dir")
							+ "\\src\\com\\xirrus\\xms\\config\\SNMPOID.properties");
			FileInputStream filePath= new FileInputStream(
					System.getProperty("user.dir")
					+ "\\src\\com\\xirrus\\xms\\config\\PATH.properties");
			CONFIG = new Properties();
			OR = new Properties();
			PATH = new Properties();
			SNMPOID= new Properties();
			CONFIG.load(fileConfig);
			OR.load(fileOR);
			SNMPOID.load(fileSNMPOID);
			PATH.load(filePath);
			App_Log.debug("Property files loaded successfully");

			// Initialize XLS
			App_Log.debug("*******Loading XLSX files- TestSuite, TS_Configure_Profile_Menu, TS_Profile_General, TS_Profile_Network.xlsx*******");
			test_Suite_xls = new DataFile_Reader(System.getProperty("user.dir")
					+ "\\src\\com\\xirrus\\xms\\xls\\TestSuite.xlsx");
			suite_ArrayConfiguration_xls = new DataFile_Reader(System.getProperty("user.dir")
							+ "\\src\\com\\xirrus\\xms\\xls\\TS_ArrayConfiguration.xlsx");
			suite_ProfileConfiguration_xls = new DataFile_Reader(System.getProperty("user.dir")
					+ "\\src\\com\\xirrus\\xms\\xls\\TS_ProfileConfiguration.xlsx");
			suite_WirelessConfiguration_xls = new DataFile_Reader(System.getProperty("user.dir")
					+ "\\src\\com\\xirrus\\xms\\xls\\TS_WirelessConfiguration.xlsx");
			suite_TemplateConfiguration_xls = new DataFile_Reader(System.getProperty("user.dir")
							+ "\\src\\com\\xirrus\\xms\\xls\\TS_TemplateConfiguration.xlsx");
			
			App_Log.debug("Successfully loaded xlsx files");
			//Initiliaze result file
			//Result File
			try{
 
	    		File file =new File(CONFIG.getProperty("ResultFile"));
	    		//if file doesnt exists, then create it
	    		if(!file.exists()){
	    			file.createNewFile();
	    		}
	    		FileWriter fileWritter = new FileWriter(file);
    	        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    	        bufferWritter.close();
			}catch (Exception e) {
					App_Log.debug(e);
				}
			
			isInitialized = true;
		}

	}
	
	
	//Selenium Webdriver
	/**
	 * This function is used to open selected in Configuration file browser
	 */
	public  void openBrowser(){
		App_Log.debug("StartLib-openBrowser:Open Browser to access website under test.");
		if(CONFIG.getProperty("browserType").equals("Mozilla")){
		driver= new FirefoxDriver();
		App_Log.debug("***********Browser under Test is FireFox***********");
		}
		if(CONFIG.getProperty("browserType").equals("IE")){
			System.setProperty("webdriver.ie.driver", "C:\\workspace\\WebDrivers\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();
			App_Log.debug("***********Browser under Test is Internet Explorer***********");
		}
		if(CONFIG.getProperty("browserType").equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\workspace\\WebDrivers\\chromedriver.exe");
			driver= new ChromeDriver();
			App_Log.debug("***********Browser under Test is Chrome***********");
		}
		App_Log.debug("EndLib-openBrowser");				
	}
	
	public void closeBroweser(){
		App_Log.debug("StartLib-closeBroweser:Close Browser under Test.");
		driver.close();
		App_Log.debug("EndLib-closeBroweser:Close Browser under Test.");
		
	}
	
	public boolean compareTitle(String expectedTitle){
		try{
		Assert.assertEquals(expectedTitle,driver.getTitle() );
		
	}catch (Throwable t){
		ErrorUtil.addVerificationFailure(t);
		App_Log.debug("Title not matched");
		return false;
		
	}
		return true;
	}
}
