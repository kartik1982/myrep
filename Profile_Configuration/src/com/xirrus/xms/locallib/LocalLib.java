package com.xirrus.xms.locallib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.xirrus.xms.base.TestBase;
import com.xirrus.xms.globallib.SnmpClient;
import com.xirrus.xms.globallib.SslShell;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class LocalLib extends TestBase{
	private int timeout = 10;
	private int tempTimeout = 1;
	private int clickDelay = 500;
	NavigateTo navigatelib = new NavigateTo();
	/**
	 * This local function is used to access Test web site with URL provided in Config file
	 * and login credentials from configuration file
	 */
	public static void AccessTestSite(){
		App_Log.debug("StartLib-AccessTestSite: Access Website under test and login");
		App_Log.debug("Access URL - "+ CONFIG.getProperty("XMSServerIPAddress")+":9090");
		//Access Test website http://xxx.yyy.zzz.aaa:9090
		String TestSiteName="http://"+CONFIG.getProperty("XMSServerIPAddress")+":9090";
		driver.get(TestSiteName);
        //Login to test site with password from configuration file
		App_Log.debug("Admin user name for login- "+CONFIG.getProperty("XMSUsername"));
        WebElement loginUserName= driver.findElement(By.xpath(".//*[@id='id1']"));
        loginUserName.sendKeys(CONFIG.getProperty("XMSUsername"));
        App_Log.debug("Admin user passwd for login- "+CONFIG.getProperty("XMSPassword"));
        WebElement loginPasswd = driver.findElement(By.xpath(".//*[@id='id2']/table[2]/tbody/tr[2]/td[2]/input"));
        loginPasswd.sendKeys(CONFIG.getProperty("XMSPassword"));
        WebElement loginBtn= driver.findElement(By.name("loginButton"));
        loginBtn.click();	
        App_Log.debug("EndLib-AccessTestSite");
		}
	
	
	/**
	 * Clicks on the nth option of the given setting name. 
	 *
	 * @param settingname The name of the setting
	 * @param option The nth option to select
	 */
	public void selectRadioButton(String settingname, int option) {
		App_Log.debug("StartLib-selectRadioButton: Select radio button");
		App_Log.debug("Selecting radio button: " + option + " for setting: " + settingname);
		Integer instancecounter = 0;
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + settingname +"')]/following-sibling::*//*[@type='radio']"));
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
				instancecounter++;
				if (instancecounter == option) {
					elements.get(i).click();
					break;
				}
			}
		}	
		App_Log.debug("EndLib-selectRadioButton");
	}
	
	/**
	 * This function will verify strings passed and capture screen for passed and failed both scenario
	 * @param field - Enter what parameter verifying any value will accept
	 * @param testCaseName
	 * @param TestMethodName
	 * @param actualValue
	 * @param expectedValue
	 * @return
	 * @throws IOException
	 */
	public boolean verifyStringequals(String field, String testCaseName, String TestMethodName, String actualValue, String expectedValue) throws IOException{
		App_Log.debug("StartLib-verifyStringequals: Verify Actual String-"+actualValue+" with Expected String-"+expectedValue);
		boolean result=false;
		final String tcname=testCaseName;
		final String tcmethod=TestMethodName;
		final String actual=actualValue;
		final String expected= expectedValue;
		final String param=field;
		String screencaptureFileName;
		
		try{
			Assert.assertEquals(actual, expected);
			result=true;
			screencaptureFileName="PASS-"+param+"-"+testCaseName+"-"+TestMethodName+".png";
			captureScreenshot(screencaptureFileName);
			App_Log.debug("PASS- Verification passed!!!"+param+"-"+testCaseName+"-"+TestMethodName);
		}catch (Exception e) {
			
			result=false;
			screencaptureFileName="FAIL-"+param+"-"+testCaseName+"-"+TestMethodName+".png";
			captureScreenshot(screencaptureFileName);
			App_Log.debug("FAIL- Verification Failed!!!"+param+"-"+testCaseName+"-"+TestMethodName);
			
		}
		App_Log.debug("EndLib-verifyStringequals");
		return result;
		
	}
	
	/**
	 * Capture a screenshot and stores it at the specified location
	 * @param filename The path where the file should be written to.
	 * @throws IOException 
	 */
	public void captureScreenshot(String fileName) throws IOException {
		App_Log.debug("StartLib-captureScreenshot: Capture screen and store to logs");
		try{
		String filePath=CONFIG.getProperty("CapturescreenPath");
		File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(filePath+fileName));
        App_Log.debug("Screenshot: " + fileName);
		}catch(IOException e){
			App_Log.error("Erorr:-"+e);
			
			
		}
        App_Log.debug("EndLib-captureScreenshot");
	}
	
	/**
	 * This function is used to logout from test web site
	 */
	public static void logoutFromTestSite(){
		App_Log.debug("StartLib-logoutFromTestSite: Logout from test web site");
		WebElement logoutLink= driver.findElement(By.xpath("html/body/div[1]/div[1]/div[1]/div/span/a"));
        logoutLink.click();
        App_Log.debug("EndLib-logoutFromTestSite");
	}
	
	/**
	 * This function used to create with prfoile name as string passed to function
	 * @param profileName
	 * @throws InterruptedException
	 */
	public void createProfile(String profileName)throws InterruptedException{
		App_Log.debug("StartLib-createProfile: Create profile "+profileName);
		final String profName=profileName;
		//Navigate to profile
		navigatelib.ConfigureProfile();
		clickButton("Add");
		setTimeout(timeout);
		driver.findElement(By.xpath("//*[@name='commandButtonsContainer:commandPanel:popupContainer:popupPanel:contentWrap:form:boundingBox:content:networkName']")).sendKeys(profName);
		setTimeout(timeout);
		clickButton("OK");
		setTimeout(timeout);
		App_Log.debug("EndLib-createProfile");
	}
	
	public void addArrayintoProfile(String arrayIPadd, String profileName) throws InterruptedException{
		App_Log.debug("StartLib-addArrayintoProfile: Add array-"+arrayIPadd+" into profile-"+profileName);
		final String ArrayIP=arrayIPadd;
		final String profName=profileName;
		navigatelib.ConfigureProfile();
		//Select CheckBox for profile
		selectRowCheckbox(profName);
		//Click edit button
		clickButton("Edit");
		//Select Checkbox for array
		selectRowCheckbox(ArrayIP);
		//Click OK button
		clickButton("OK");
		App_Log.debug("EndLib-addArrayintoProfile");
	}
	
	/**
	 * This function used to delete all profiles from profile panel is exists.
	 * @throws InterruptedException
	 */
	public void deletAllProfile()throws InterruptedException{
		App_Log.debug("StartLib-deletAllProfile- Delete all profiles");
		App_Log.debug("Delete all profile if previously exists");
		//Navigate to Profile 
		navigatelib.ConfigureProfile();
		//Delete all profile if previously exist
		List<WebElement> elements;
		toggleCheckbox(1);
		clickButton("Delete");
		String buttonname="Close";
		setTimeout(timeout);
		elements = driver.findElements(By.xpath("//*[text() ='" + buttonname + "']"));
		if(elements.size()!=0){
			clickButton("Close");
		}
		else {
			clickButton("OK");
		}
		setTimeout(timeout);
		App_Log.debug("EndLib-deletAllProfile");
	}
	
	/**
	 * Refreshes the array with the given IP address
	 * Waits up to 90 seconds for the array to refresh.
	 * 
	 * @param arrayIP or hostname of array to be refreshed
	 * @throws Exception 
	 */
	public void refreshAccessPoint (String arrayIP) throws InterruptedException {
		this.refreshAccessPoint(arrayIP, 90);
	}
	/**
	 * Refreshes the array with the given IP address
	 * 
	 * @param arrayIP or hostname of array to be refreshed
	 * @param timeoutSeconds The timeout period in seconds.
	 * @throws Exception 
	 */
	public void refreshAccessPoint (String arrayIP, int timeoutSeconds) throws InterruptedException {
		App_Log.debug("StartLib-refreshAccessPoint: Refresh Array from Configure Panel");
		App_Log.debug("Refreshing array: " + arrayIP + " with a timeout of " + timeoutSeconds + " seconds.");
		int currentTimeout = this.timeout;
		navigatelib.Configure();
		setTimeout(timeout);
		selectGroup("All Arrays");
		setTimeout(90);
		selectRowCheckbox(arrayIP);
		clickButton("Refresh");
		clickButton("OK");
		setTimeout(timeoutSeconds);
		if (! findText("Done performing discovery: Successfully refreshed existing device.")) {
			throw new InterruptedException("Failed to refresh the array.");
		}
		this.timeout = currentTimeout;
		setTimeout(timeout);
		App_Log.debug("EndLib-refreshAccessPoint: Refresh Array from Configure Panel");
	}
	
	/**
	 * Looks for the specified text on the page.
	 *
	 * @param text The text to look for.
	 * @return Returns true if the text is found on the page
	 */
	public boolean findText(String text) {
		App_Log.debug("Looking for this text on the page: " + text);
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
		
		if (elements.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Toggles the specified instance of a checkbox, only considers visible checkboxes.
	 *
	 * @param instance The instance of the checkbox
	 */
	public void toggleCheckbox(int instance) {
		App_Log.debug("Toggling checkbox instance: " + instance);
		Integer instancecounter = 0;
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//*[@type='checkbox']"));
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
				instancecounter++;
				if (instancecounter == instance) {
					elements.get(i).click();
					break;
				}
			}
		}	
		
	}
	
	/**
	 * Sets the specified text field to the given value.
	 * This replaces the existing value.
	 *
	 * @param fieldname The name of the text field.
	 * @param text The text to enter.
	 */
	public String getText(String fieldname) {
		String wmiText = null;
		List<WebElement> elements;
		boolean hidden = true;
		int tempTimeout = this.timeout;
		
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + ":')]/ancestor::tr[1]//input"));
		for (int i = 0; i < elements.size(); i++) {
		if (elements.get(i).isDisplayed()) {
			hidden = false;
			//System.out.println(":')]/ancestor::tr[1]//input");
			break;
			}
		}
		
		//if no element is visible or no elements were returned, search using a different xpath
		if (elements.size() == 0 || hidden) {
			this.setTimeout(1);
			elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + ":')]/input")));
			for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i).isDisplayed()) {
					hidden = false;
					//System.out.println(":')]/input");
					break;
				}
			}
		}
		

		
		//if no element is visible or no elements were returned, search using a different xpath
		if (elements.size() == 0 || hidden) {
			this.setTimeout(1);
			elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + "')]/following-sibling::*//input")));
			for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i).isDisplayed()) {
					hidden = false;
					//System.out.println("')]/following-sibling::*//input");
					break;
				}
			}
		}
		
		//if no element is visible or no elements were returned, search using a different xpath
		if (elements.size() == 0 || hidden) {
			elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + "')]/following-sibling::*//textarea")));
			//System.out.println(":')]/following-sibling::*//textarea");
		}
		
		this.setTimeout(tempTimeout);
		
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
					wmiText = elements.get(i).getAttribute("value");
					break;
			}
		}
		App_Log.debug("Reading the value in text field " + fieldname + ": " + wmiText);
		return wmiText; 

	}
	/**
	 * Selects the specified group from the group drop-down selector.
	 *
	 * @param groupname The name of the group to select.
	 */
	public void selectGroup(String groupname) {
		App_Log.debug("Selecting array group: " + groupname);
		Select droplist = new Select(driver.findElement(By.xpath("//span[@class='groupPicker']//select")));
		droplist.selectByVisibleText(groupname);
	}
	
	/**
	 * Select a checkbox in a grid based on text in its row.
	 *
	 * @param rowtext The text to use to select the checkbox
	 */
	public void selectRowCheckbox(String rowtext) {
		App_Log.debug("StartLib-selectRowCheckbox: Selecting the checkbox for the row containing the text: " + rowtext);
		String row;
		row = driver.findElement(By.xpath("//td[contains(div,'" + rowtext + "')]/..")).getAttribute("id");
		String xpath;
		xpath = "//tr[@id='" + row + "']//input[@type='checkbox']";
		driver.findElement(By.xpath(xpath)).click();
		App_Log.debug("EndLib-selectRowCheckbox");
	}

	/**
	 * Clicks on the specified generic button.
	 *
	 * @param buttonname The name of the button to click.
	 * @throws InterruptedException 
	 */
	public void clickButton(String buttonname) throws InterruptedException {
		App_Log.debug("StartLib-clickButton:Clicking button: " + buttonname);
		List<WebElement> elements;
		this.tempTimeout = this.timeout;
		elements = driver.findElements(By.xpath("//*[@value='" + buttonname + "']"));	//command buttons
		if(elements.size() == 0) {
			this.setTimeout(1);
			elements.addAll(driver.findElements(By.xpath("//*[text() ='" + buttonname + "']")));
		}
		
		this.setTimeout(this.tempTimeout);
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
					elements.get(i).click();
					Thread.sleep(this.clickDelay);
					break;
			}
		}
		App_Log.debug("EndLib-clickButton");
	}
	
	/**
	 * This function will add hard coded delay before next statement
	 * @param delay
	 */
	public void pause(int delay){
		App_Log.debug("Delay Request for "+delay+ " msec");
		try {
		    Thread.sleep(delay);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	
	/**
	 * This function will return value for filed in Contact info for arrays
	 * @param arrayIP

	 * @param strField "Array Hostname", "Array Location", "Contact Name","Contact E-Mail", "Contact Phone"
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public String getValuebyCLI_ContactInfo(String arrayIP, String strField )throws InterruptedException, IOException{
		final String arrayIPadd=arrayIP;
		final String arrayUser="admin";
		final String arrayPasswd= "admin";
		final String expcfield= strField;
		String expcfiledValue=null;

		//Code
		SslShell myshell = new SslShell(arrayIPadd, "22", arrayUser, arrayPasswd);
		myshell.connect();
		if (myshell.getStatusString().contains("Failed to connect via SSH")) {
			Thread.sleep(2000);
			myshell.connect();
		}
		myshell.startSession();
		myshell.sendLine("configure");
		myshell.expect("#");
		myshell.sendLine("contact-info");
		myshell.expect("#");
		myshell.sendLine("show");
		Thread.sleep(2000);
		String output=myshell.getOutput(); 
		myshell.finalize();
		
		String lines[] = output.split("\\r?\\n");
		for(int i=0;i<lines.length;i++){
			if(lines[i].contains(expcfield))
				expcfiledValue= lines[i];
		}
		expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
		return expcfiledValue;

	}
	/**
	 * This function will return value for object ID passed
	 * @param arrayIP
	 * @param comunity
	 * @param OID
	 * @return
	 */
	public String getValueBySNMP(String arrayIP, String comunity, String OID){
		App_Log.debug("StartLib-verifyBySNMP: Recive value OID by SNMP client");
		App_Log.debug("Value input:-arrayIP, comunity and OID"+arrayIP+" "+comunity+" "+OID);
		String value=null;
		String arrayIPaddress=arrayIP;
		String v2community=comunity;
		String snmpOID=OID;
		try{
			SnmpClient client = new SnmpClient(arrayIPaddress, v2community);
			value = client.get(snmpOID);
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
	
	/**
	 * Clicks the specified instance of a link. Used when there are multiple links with the same name on the page. Only works on links currently visible on the page.
	 *
	 * @param linkname The name of the link to click.
	 * @param instance The instance of the link.
	 * @throws InterruptedException 
	 */
	public void clickLink(String linkname, Integer instance) throws InterruptedException {
		App_Log.debug("Clicking link " + linkname + " instance " + instance);
		Integer instancecounter = 0;
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//a/span[text()='" + linkname + "']"));
		if (elements.size() <= 0) {
			elements.addAll(driver.findElements(By.xpath("//a[text()='" + linkname + "']")));
		}
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
				instancecounter++;
				if (instancecounter == instance) {
					elements.get(i).click();
					Thread.sleep(clickDelay);
					break;
				}
			}
		}
	}
	
	/**
	 * Sets the timeout when looking for an element in the page.
	 * @param timeout Time in seconds.
	 */
	public void setTimeout(int timeout) {
		App_Log.debug("Setting the Selenium timeout to " + timeout + " seconds.");
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		this.timeout = timeout;
	}
	
	/**
	 * Method to update Result file with verification result for Test case.  
	 * @param filename The path where the file should be written to.
	 */
	public void postResults(String result) throws IOException {
		App_Log.debug("Posting the test result to: " + result);
		FileWriter fileWritter = new FileWriter(CONFIG.getProperty("ResultFile"),true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(result);
        bufferWritter.close();
	}	
		
	
	
	/**
	 * Clicks the specified link. Only works on visible links.
	 *
	 * @param linkname The name of the link to click.
	 * @throws InterruptedException 
	 */
	public void clickLink(String linkname) throws InterruptedException {
		App_Log.debug("Clicking link " + linkname);
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//a/span[text()='" + linkname + "']"));
		if (elements.size() <= 0) {
			elements.addAll(driver.findElements(By.xpath("//a[text()='" + linkname + "']")));
		}
		
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
					elements.get(i).click();
					Thread.sleep(clickDelay);
					break;
			}
		}
	}
	
	/**
	 * Sets the specified text field to the given value.
	 * This replaces the existing value.
	 *
	 * @param fieldname The name of the text field.
	 * @param text The text to enter.
	 * @throws InterruptedException 
	 */
	public void setText(String fieldname, String text) throws InterruptedException {
		boolean stale = false; //used to track if the web element becomes stale
		int maxRetry = 4;	//the maximum number of re-tries in case of a stale web element
		do {
			try {
				App_Log.debug("Setting field " + fieldname + " to value " + text);
				List<WebElement> elements;
				boolean hidden = true;
				int tempTimeout = this.timeout;
				
				elements = driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + ":')]/ancestor::tr[1]//input"));
				for (int i = 0; i < elements.size(); i++) {
				if (elements.get(i).isDisplayed()) {
					hidden = false;
					//System.out.println(":')]/ancestor::tr[1]//input");
					break;
					}
				}
				
				//if no element is visible or no elements were returned, search using a different xpath
				if (elements.size() == 0 || hidden) {
					this.setTimeout(1);
					elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + ":')]/input")));
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).isDisplayed()) {
							hidden = false;
							//System.out.println(":')]/input");
							break;
						}
					}
				}
				
		
				
				//if no element is visible or no elements were returned, search using a different xpath
				if (elements.size() == 0 || hidden) {
					this.setTimeout(1);
					elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + "')]/following-sibling::*//input")));
					for (int i = 0; i < elements.size(); i++) {
						if (elements.get(i).isDisplayed()) {
							hidden = false;
							//System.out.println("')]/following-sibling::*//input");
							break;
						}
					}
				}
				
				//if no element is visible or no elements were returned, search using a different xpath
				if (elements.size() == 0 || hidden) {
					elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + fieldname + "')]/following-sibling::*//textarea")));
					//System.out.println(":')]/following-sibling::*//textarea");
				}
				
				
				for (int i = 0; i < elements.size() ; i++) {
					if (elements.get(i).isDisplayed()) {
							elements.get(i).clear();
							elements.get(i).click();
							elements.get(i).sendKeys(text);
							break;
					}
				}
				
				
				this.setTimeout(tempTimeout);
				stale = false;
			} catch (StaleElementReferenceException e) {
				System.out.println(e);
				stale = true;
				maxRetry -= 1;
			}
		} while (stale && (maxRetry>0));
	}
}
