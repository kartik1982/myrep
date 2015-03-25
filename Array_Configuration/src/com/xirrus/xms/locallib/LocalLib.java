package com.xirrus.xms.locallib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
	
	
	public boolean isWarningMessageDisplayed(String warningMessage){
		App_Log.debug("StartLib-isWarningMessageDisplayed: Warning message String displaying or Not Displaying");
		boolean isDisplayed=false;
		String warMessg=warningMessage;
		if (! findText(warningMessage)) {
			App_Log.debug("Warning Message -"+warMessg+" is Not Displayed");
			isDisplayed=false;
		}else{
			App_Log.debug("Warning Message -'"+warMessg+"' is Displayed");
			isDisplayed=true;
		}
		return isDisplayed;
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
	public boolean verifyStringequals(String field, String testCaseName, String testMethodName, String actualValue, String expectedValue) throws IOException{
		App_Log.debug("StartLib-verifyStringequals: Verify Actual String-"+actualValue+" with Expected String-"+expectedValue);
		boolean result=false;
		final String tcname=testCaseName;
		final String tcmethod=testMethodName;
		final String actual=actualValue;
		final String expected= expectedValue;
		final String param=field;
		String screencaptureFileName;
		
		try{
			Assert.assertEquals(actual, expected);
			result=true;
			screencaptureFileName="PASS-"+param+"-"+tcname+"-"+tcmethod+".png";
			//captureScreenshot(screencaptureFileName);
			App_Log.debug("PASS- Verification passed!!!"+param+"-"+tcname+"-"+tcmethod);
		}catch (AssertionError exception) {
			result=false;
			screencaptureFileName="FAIL-"+param+"-"+tcname+"-"+tcmethod+".png";
			captureScreenshot(screencaptureFileName);
			postResults(tcname+"|"+tcmethod+"|"+"FAIL");
			App_Log.debug("FAIL- Verification Failed!!!"+param+"-"+tcname+"-"+tcmethod);
			Assert.fail("FAIL- Verification Failed!!!"+param+"-"+tcname+"-"+tcmethod);
		}
		App_Log.debug("EndLib-verifyStringequals");
		return result;
		
	}
	
	
	public boolean addVLANintoArray(String arrayHostName, String vlanName, String vlanID, String ipAdd, String ipMask, String ipGateway, String tunnel, String tSecret, String tPort) throws InterruptedException{
		App_Log.debug("StartLib-addVLAN: This function will add VLANs but not push into array");
		boolean result=false;
		//Navigate to VLAN Management panel and click Add VLAN button
			setTimeout(3);
			if (findText("Cancel"))	clickButton("Cancel");
			if(!findText("Default Route")){
			//Navigate to Array Configuration
			navigatelib.ConfigureAccesssPoint();
			//Navigate to Array Services Configuration
			clickLink(arrayHostName);
			clickLink("Configuration");
			clickLink("VLAN");
			clickLink("VLAN Management");
			}
			clickButton("Add");
			do{
				pause(10);
			}while(!findText("New VLAN"));
			setText("Name", vlanName);
			setText("VLAN ID", vlanID);
			if(ipAdd!="")setText("IP Address", ipAdd);
			if(ipMask!="")setText("Subnet Mask", ipMask);
			if(ipGateway!="")setText("Gateway", ipGateway);
			if(tunnel!="")setText("Tunnel Server", tunnel);
			pause(5);
			if(tSecret!="")setText("New Secret", tSecret);
			if(tSecret!="")setText("Confirm Secret", tSecret);
			if(tPort!="")setText("Port", tPort);
			clickButton("OK");
			int i=1;
			do{
				pause(10);
				if(i>15)
					break;
				i++;
			}while(findText("New VLAN"));
			if (findText("New VLAN")) {
				result=false;
				App_Log.debug("SetupError-Did not find an expected configuration message.");
			}else
				result=true;
		App_Log.debug("EndLib-addVLAN");
			return result;
			
	}
	
	
	/**
	 * 
	 * @param arrayHostName
	 * @param vlanName
	 * @param vlanID
	 * @return
	 * @throws InterruptedException
	 */
	public boolean addVLANintoArray(String arrayHostName, String vlanName, String vlanID ) throws InterruptedException{
		boolean result= addVLANintoArray(arrayHostName, vlanName, vlanID, "", "", "", "", "", "");
		return result;
	}
	
	/**
	 * This function Add DHCP server bbut dont push into array by pressing apply configuration
	 * @param arrayhostname
	 * @param dhcpName
	 * @param startIP
	 * @param endIP
	 * @param subnetMask
	 * @param gateway
	 * @param domain
	 * @param dns1
	 * @param dns2
	 * @param dns3
	 * @return
	 * @throws InterruptedException
	 */
	public boolean addDHCPServer(String arrayhostname, String dhcpName, String startIP, String endIP, String subnetMask, String gateway, String domain, String dns1, String dns2, String dns3) throws InterruptedException{
	App_Log.debug("StartLib-addDHCPServer:Function will add DHCP server with parameters passed and return success or fail");
		boolean result=false;
		setTimeout(3);
		//Verify that no panel open if yes cancel
		if (findText("Cancel"))	clickButton("Cancel");
		if(!findText("DHCP Server")){
		//Navigate to Array configuration panel
		navigatelib.ConfigureAccesssPoint();
		clickLink(arrayhostname);
		//Navigate to DHCp Server panel 
		clickLink("Configuration");
		}
		clickLink("Services");
		clickLink("DHCP Server");
		//Click Add button
		clickButton("Add");
		//Add information received from function
		selectCheckBox("Enabled");
		setText("Name", dhcpName);
		selectCheckBox("NAT Enabled");
		setText("Start IP Range", startIP);
		setText("End IP Range", endIP);
		setText("Default Subnet Mask", subnetMask);
		setText("Gateway", gateway);
		setText("Default Domain", domain);
		setText("Default DNS Server 1", dns1);
		setText("Default DNS Server 2", dns2);
		setText("Default DNS Server 3", dns3);
		clickButton("OK");
		if (findText("Add DHCP Pool")) {
			result=false;
			App_Log.debug("Did not find an expected configuration message.");
		}else
			result=true;
	
		return result;
		
	}
	
	public boolean addDHCPServer(String arrayhostname, String dhcpName, String startIP, String endIP, String subnetMask, String gateway) throws InterruptedException{
		
		boolean result=addDHCPServer(arrayhostname, dhcpName, startIP, endIP, subnetMask, gateway, "", "", "",  "");
		return result;
	
	}


		
	/**
	 * Sets the specified drop down to the given value.
	 *
	 * @param name The name of the drop down selector.
	 * @param value The value to set it to.
	 * @throws InterruptedException 
	 */
	public void selectDropDown(String name, String value) throws InterruptedException {
		App_Log.debug("StartLib-selectDropDown: Select Drop down value provided to function");
		List<WebElement> elements;
		this.tempTimeout = this.timeout;
		App_Log.debug("Selecting value: " + value + " for drop-down menu " + name);
		pause(20);
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following-sibling::*/select"));
		
		if (elements.size() == 0) {
			this.setTimeout(1);
			elements = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/ancestor::tr[1]//select"));
		}
		
		this.setTimeout(this.tempTimeout);
		Select droplist = new Select(elements.get(0));
		droplist.selectByVisibleText(value);
		Thread.sleep(clickDelay);
		App_Log.debug("StartLib-selectDropDown");
	}
	
	/**
	 * Returns the option selected for the given drop drown
	 *
	 * @param name The name of the drop down selector.
	 * @throws InterruptedException 
	 */
	public String getDropDownText(String name) throws InterruptedException {
		App_Log.debug("StartLib-getDropDownText: Return Option selected for drop down");
		List<WebElement> elements;
		this.tempTimeout = this.timeout;
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/following-sibling::*/select"));
		if (elements.size() == 0) {
			this.setTimeout(1);
			elements = driver.findElements(By.xpath("//*[contains(text(),'" + name + "')]/ancestor::tr[1]/td/select"));
		}
		
		this.setTimeout(this.tempTimeout);
		Select droplist = new Select(elements.get(0));
		App_Log.debug("Getting the drop down option selected for this dropdown: " + name + ". Value: " + droplist.getFirstSelectedOption().getText());
		App_Log.debug("EndLib-getDropDownText");
		return droplist.getFirstSelectedOption().getText();
		
		
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
	 * Toggles the checkbox specified by a name
	 *
	 * @param checkboxname The name of the checkbox
	 */
	public void toggleCheckbox(String checkboxname) {
		App_Log.debug("Toggling checkbox: " + checkboxname);
		List<WebElement> elements;
		this.tempTimeout = this.timeout;
		
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + checkboxname + "')]/preceding-sibling::*[@type='checkbox']"));
		if(elements.size() == 0) {
			this.setTimeout(1);
			elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + checkboxname + "')]/following-sibling::*//*[@type='checkbox']")));
		}
		if(elements.size() == 0) {
			elements.addAll(driver.findElements(By.xpath("//td[contains(.,'" + checkboxname + "')]/*[@type='checkbox']")));
		}

		this.setTimeout(this.tempTimeout);
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
					elements.get(i).click();
					break;
				}
			}
		}
	
	public void unSelectCheckBox(String checkBoxName){
		App_Log.debug("StartLib-unSelectCheckBox: Unselect check box if check box is already selected");
		if(isCheckboxSelected(checkBoxName))
			toggleCheckbox(checkBoxName);
		App_Log.debug("EndLib-unSelectCheckBox");
	}
	public void selectCheckBox(String checkBoxName){
		App_Log.debug("StartLib-SelectCheckBox: select check box if check box is already not selected");
		if(!isCheckboxSelected(checkBoxName))
			toggleCheckbox(checkBoxName);
		App_Log.debug("EndLib-SelectCheckBox");
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
	
	public String getLabelValue(String lable)throws InterruptedException{
		App_Log.debug("StartLib-getLabelValue: Return value associated with label");
		String labelValue="";
		List<WebElement> elements;
		this.tempTimeout = this.timeout;
		String rowid;
		rowid = driver.findElement(By.xpath("//td[contains(div,'" + lable + "')]/..")).getAttribute("id");
//		elements = driver.findElements(By.xpath("//td[contains()]"));
		//driver.findElement(By.xpath("//td[contains(div,'" + rowtext + "')]/.."));
		//row = driver.findElement(By.xpath("//td[contains(div,'" + rowtext + "')]/..")).getAttribute("id");
		//[contains(text(),'" + checkboxname + "')]
//		for (int i = 0; i < elements.size() ; i++) {
//			if (elements.get(i).isDisplayed()) {
//
//					break;
//				}
			
//		}	
		App_Log.debug("EndLib-getLabelValue");
		return labelValue;
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
	 * Checks if the given radio button option is selected. 
	 *
	 * @param fieldName The name of the setting
	 * @param option The nth option to select
	 * @return Returns true if the radio button option is selected, false otherwise.  Also returns false if the radio button can't be found.
	 */
	public boolean isRadioButtonSelected(String fieldName, int option) {
		App_Log.debug("StartLib-isRadioButtonSelected: Check radio button is selected or not");
		Integer instancecounter = 0;
		boolean selected = false;
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + fieldName +"')]/following-sibling::*//*[@type='radio']"));
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
				instancecounter++;
				if (instancecounter == option) {
					selected = elements.get(i).isSelected();
					break;
				}
			}
		}
		App_Log.debug("Checking if radio button: " + option + " is selected for setting: " + fieldName + " : " + selected);
		App_Log.debug("EndLib-isRadioButtonSelected");
		return selected;
	}
	/**
	 * This function will return Field values from Stsem-Info, e.g License etc
	 * @param arrayIP
	 * @param strField	License Key
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_SystemInfo(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("show system-info");
		Thread.sleep(2000);
		String output=myshell.getOutput(); 
		myshell.finalize();
		//Extract Field value
		String lines[] = output.split("\\r?\\n");
		for(int i=0;i<lines.length;i++){
			if(lines[i].contains(expcfield))
				expcfiledValue= lines[i];
		}
		expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
		return expcfiledValue;

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
	 * 
	 * @param arrayIP
	 * @param strField radius, passphrase, rekey time, TKIP, AES, EAP, PSK
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_Security(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("security");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
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
		if(!expcfiledValue.contains(",")){
		expcfiledValue=expcfiledValue.replace(expcfield, "").replace(":", "").trim();
		}else{
			String temp[]=expcfiledValue.split(":");
			for(int i=0;i<temp.length;i++){
				if(temp[i].contains(expcfield))
					expcfiledValue= temp[i];
			}
			temp=expcfiledValue.split(",");
			for(int i=0;i<temp.length;i++){
				if(temp[i].contains(expcfield))
					expcfiledValue= temp[i].replace(expcfield, "").trim();
			}
		}
		return expcfiledValue;

	}
	
	
	/**
	 * 
	 * @param arrayIP
	 * @param strField Hostname, Domain, Server 1, Server 2, Server 3, Use DHCP
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_DNS(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("dns");
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
	 * 
	 * @param arrayIP
	 * @param strField State, Backoff Period, Roaming Threshold, Minimum Data Rate, Devices
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_RoamingAssit(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("roaming-assist");
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
	 * 
	 * @param arrayIP
	 * @param strField	 State, Period, Customer Key, Server URL
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_NetFlow(String arrayIP, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_NetFlow: Goging to return Netflow parameters value");
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
		myshell.sendLine("netflow");
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
		App_Log.debug("EndLib-getValuebyCLI_NetFlow");
		return expcfiledValue;

	}
	/**
	 * 
	 * @param arrayIP
	 * @param strField	 State, UDP Port, Tag Channel-bg, Ekahau Server
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_WiFiTag(String arrayIP, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_Location: Goging to return location parameters value");
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
		myshell.sendLine("wifi-tag");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
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
		App_Log.debug("EndLib-getValuebyCLI_Location");
		return expcfiledValue;

	}
	
	/**
	 * 
	 * @param arrayIP
	 * @param dhcpSrvrName e.g DHCP server name if no server than NoDHCP
	 * @param strField	State, NAT, IP range start, IP range end, IP mask , IP gateway, Default lease time, Maximum lease time, DNS Domain name, DNS Server 1, DNS Server 2, DNS Server 3
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_DHCPServer(String arrayIP, String dhcpSrvrName, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_DHCPServer: Goging to return DHCP Server parameters value");
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
		myshell.sendLine("dhcp-server");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
		myshell.expect("#");
		myshell.sendLine("show");
		Thread.sleep(2000);
		String output=myshell.getOutput(); 
		if(output.contains("No DHCP Pools defined.")){
			expcfiledValue="No DHCP Pools defined.";
		}else{
			myshell.sendLine("edit "+dhcpSrvrName);
			myshell.expect("#");
			myshell.sendLine("show");
			Thread.sleep(2000);
			output=myshell.getOutput();
			String lines[] = output.split("\\r?\\n");
			for(int i=0;i<lines.length;i++){
				if(lines[i].contains(expcfield))
					expcfiledValue= lines[i];
			}
			expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
		}
		myshell.finalize();	
		if(expcfiledValue.contains("Settings"))
			expcfiledValue=expcfiledValue.replace("Settings", "").replace("\"", "").trim();
		App_Log.debug("EndLib-getValuebyCLI_Location");
		
		return expcfiledValue;

	}
	

	
	/**
	 * 
	 * @param arrayIP
	 * @param strField	API URL, API key, API username, API password, API timeout, API poll period, API access error, Redirect URL
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_AirWatch(String arrayIP, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_AirWatch: Goging to return location parameters value");
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
		myshell.sendLine("mdm");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
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
		App_Log.debug("EndLib-getValuebyCLI_AirWatch");
		return expcfiledValue;

	}
	
	/**
	 * 
	 * @param arrayIP
	 * @param vlanName NoVLAN, Default, Native - to check no VLAN added, Default VLAN or Native VLAN or just VLAN name to get more info
	 * @param strField Number, Management, Fast Roaming, DHCP, IP address, IP mask, IP gateway, Tunnel server, Tunnel secret, Tunnel port, 
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_VLANs(String arrayIP, String vlanName, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_VLANs: Goging to return VLAN parameters value");
		int act=3;
		if(vlanName.equals("NoVLAN"))act=1;else if(vlanName.equals("Default")) act=2;else if(vlanName.equals("Native"))act=2;
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
		myshell.sendLine("vlan");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
		myshell.expect("#");
		myshell.sendLine("show");
		Thread.sleep(2000);
		String output=myshell.getOutput(); 
		switch(act){
		case 1:
			if(output.contains("No VLANs defined."))expcfiledValue="No VLANs defined."; else expcfiledValue="VLAN Exist";
	        break;
		case 2:
			String lines[] = output.split("\\r?\\n");
			for(int i=0;i<lines.length;i++){
				if(lines[i].contains(expcfield))
					expcfiledValue= lines[i];
			}
			if(expcfiledValue.contains("Default")) expcfiledValue=expcfiledValue.replace("Default Route     VLAN:", "").trim(); 
			else
				expcfiledValue=expcfiledValue.replace("Native (untagged) VLAN:", "").trim();
			break;
		case 3:
			myshell.sendLine("edit "+vlanName);
			Thread.sleep(2000);
			output=myshell.getOutput();
			if(!output.contains("%")){
			myshell.expect("#");
			myshell.sendLine("show");
			Thread.sleep(2000);
			output=myshell.getOutput();
			}
			String lines1[] = output.split("\\r?\\n");
			for(int i=0;i<lines1.length;i++){
				if(lines1[i].contains(expcfield))
					expcfiledValue= lines1[i];
			}
			expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
			break;
			
		default:
			expcfiledValue="Paramters passed not accepted by function";
	        	break;
		}
		App_Log.debug("EndLib-getValuebyCLI_VLANs");
		return expcfiledValue;

	}
	
	/**
	 * 
	 * @param arrayIP
	 * @param ssidName
	 * @param strField State, Active, Fallback, Authentication, Encryption, Security Settings, , Active IAPs, , VLAN Name , VLAN Number, QoS Level, Fast Roaming, Active Bands, Broadcast, DHCP Pool, DHCP Opt, Filter List, Access Control, Station Limit, Traffic Limit, Traffic/Station, Time on, ,Time off ,Days on , MDM AuthenticationWeb Page Redirect 
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_SSIDs(String arrayIP, String ssidName, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_SSIDs: Goging to return SSID parameters value");
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
		myshell.sendLine("ssid");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
		myshell.expect("#");
		myshell.sendLine("show");
		myshell.expect("#");
		myshell.sendLine("edit "+ssidName);
		Thread.sleep(2000);
		String output1=myshell.getOutput();
		if(output1.contains("SSID does not exist")){
			expcfiledValue="Deleted";
		}else{
		myshell.expect("#");
		myshell.sendLine("show");
		Thread.sleep(2000);
		String output=myshell.getOutput();
		String lines1[] = output.split("\\r?\\n");
		for(int i=0;i<lines1.length;i++){
			if(lines1[i].contains(expcfield))
			{
				expcfiledValue= lines1[i];
				break;
			}
		}
		if(expcfield.equals("Settings")){
			expcfiledValue=expcfiledValue.replace("\" Settings", "").trim();
			expcfiledValue=expcfiledValue.replace("SSID \"", "").trim();
		}else{
		expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
		}
		}
		App_Log.debug("EndLib-getValuebyCLI_SSIDs");
		return expcfiledValue;

	}
	
	
	/**
	 * 
	 * @param arrayIP
	 * @param strField	 State, Period, Customer Key, Server URL
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_Location(String arrayIP, String strField )throws InterruptedException, IOException{
		App_Log.debug("StartLib-getValuebyCLI_Location: Goging to return location parameters value");
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
		myshell.sendLine("location-reporting");
		myshell.expect("#");
		myshell.sendLine("show clear-text");
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
		App_Log.debug("EndLib-getValuebyCLI_Location");
		return expcfiledValue;

	}
	
	
	/**
	 * 
	 * @param arrayIP
	 * @param strField
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_CDP(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("cdp");
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
	 * 
	 * @param arrayIP
	 * @param strField
	 * @return
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public String getValuebyCLI_LLDP(String arrayIP, String strField )throws InterruptedException, IOException{
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
		myshell.sendLine("lldp");
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
	 * This function is used to set hostname by CLI
	 * @param arrayIP
	 * @param hostname
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void setArrayHostname(String arrayIP, String hostname)throws IOException, InterruptedException{
		final String arrayIPadd=arrayIP;
		final String arrayUser="admin";
		final String arrayPasswd= "admin";
		final String host= hostname;
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
		myshell.sendLine("hostname "+host);
		myshell.expect("#");
		myshell.sendLine("contact-info");
		myshell.expect("#");
		myshell.sendLine("show");
		Thread.sleep(2000);
		String output=myshell.getOutput(); 
		myshell.finalize();
		String lines[] = output.split("\\r?\\n");
		for(int i=0;i<lines.length;i++){
			if(lines[i].contains("Array Hostname"))
				expcfiledValue= lines[i];
		}
		expcfiledValue=expcfiledValue.replace("Array Hostname", "").trim();
		if(expcfiledValue.equals(host))
			App_Log.debug("Hostname set to "+host+" by CLI");
		else
			App_Log.debug("Unable to set hostname to "+host+" by CLI");

	}
	
	/**
	 * Returns the checkbox status (selected/not selected)
	 *
	 * @param instance The instance of the checkbox
	 * @return True if checkbox is selected, false otherwise
	 */
	public boolean isCheckboxSelected(int instance) {
		Integer instancecounter = 0;
		boolean selected = false;
		List<WebElement> elements;
		elements = driver.findElements(By.xpath("//*[@type='checkbox']"));
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
				instancecounter++;
				if (instancecounter == instance) {
					selected = elements.get(i).isSelected();
					break;
				}
			}
		}
		App_Log.debug("Checking if checkbox instance " + instance + " is selected: " + selected);
		return selected;	
	}
	
	/**
	 * Returns the checkbox status (selected/not selected)
	 *
	 * @param instance The instance of the checkbox
	 * @return True if checkbox is selected, false otherwise
	 */
	public boolean isCheckboxSelected(String checkboxname) {
		boolean selected = false;
		List<WebElement> elements;
		int tempTimeout = this.timeout;
		
		elements = driver.findElements(By.xpath("//*[contains(text(),'" + checkboxname + "')]/preceding-sibling::*[@type='checkbox']"));
		if(elements.size() == 0) {
			this.setTimeout(1);
			elements.addAll(driver.findElements(By.xpath("//*[contains(text(),'" + checkboxname + "')]/following-sibling::*//*[@type='checkbox']")));
		}
		if(elements.size() == 0) {
			elements.addAll(driver.findElements(By.xpath("//td[contains(.,'" + checkboxname + "')]/*[@type='checkbox']")));
		}
		
		
		this.setTimeout(tempTimeout);
		for (int i = 0; i < elements.size() ; i++) {
			if (elements.get(i).isDisplayed()) {
					selected = elements.get(i).isSelected();
				}
			}
		App_Log.debug("Checking if checkbox " + checkboxname + " is selected: " + selected);
		return selected;	
		
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
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		App_Log.debug("Setting the Selenium timeout to " + timeout + " seconds.");
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
        //Format Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 	    //get current date time with Date()
 	   	Date date = new Date();
        result=dateFormat.format(date)+"|"+result;
        bufferWritter.write(result+"\n");
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
	 * Sets the specified text field instance to the given value.
	 * This replaces the existing value.
	 *
	 * @param fieldname The name of the text field.
	 * @param text The text to enter.
	 * @throws InterruptedException 
	 */
	public void setText(String fieldname, String text, Integer instance) throws InterruptedException {
		boolean stale = false; //used to track if the web element becomes stale
		int maxRetry = 4;	//the maximum number of re-tries in case of a stale web element
		do {
			try {
				App_Log.debug("Setting field " + fieldname + "instance " + instance + " to value  " + text);
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
				
				int instanceCounter = 0;
				for (int i = 0; i < elements.size() ; i++) {
					if (elements.get(i).isDisplayed() && elements.get(i).isEnabled()) {
							instanceCounter++;
							if (instanceCounter == instance) {
								elements.get(i).clear();
								elements.get(i).click();
								elements.get(i).sendKeys(text);
								break;
							}
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
