package com.xirrus.xms.locallib;


import com.xirrus.xms.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class NavigateTo extends TestBase {

	private int clickDelay = 500;

	/**
	 * Navigates to the specified XMS webpage.
	 * 
	 * @mainPage Main panel xmstest:id getting from PATH properties file
	 * @subPage Sub panel xmstest:id getting from PATH properties file
	 * @throws InterruptedException
	 */
	public void goTo(String mainPage, String subPage) throws InterruptedException {
		App_Log.debug("Debug:" + " Navigating to " + mainPage + " " + subPage);
		Actions actions = new Actions(driver);
		// Move mouse to Monitor
		if (subPage == null) {
			driver.findElement(By.xpath("//a[@*='" + mainPage + "']")).click();
		} else {
			// Navigate to Overview Access Points (Monitor>>Access Points)
			actions.moveToElement(
					driver.findElement(By.xpath("//a[@*='" + mainPage + "']")))
					.moveToElement(
							driver.findElement(By.xpath("//a[@*='" + subPage
									+ "']"))).click().build().perform();
		}
		Thread.sleep(clickDelay);
	}

	// **************************Monitor********************

	/**
	 * This function is used to navigate to Monitor
	 */
	public void Monitor() throws InterruptedException {
		// Navigate to Overview Monitor
		goTo(PATH.getProperty("Monitor"), null);
	}

	/**
	 * This function is used to navigate to Dashboard in Monitor Overview
	 */
	public void MonitorDashboard() throws InterruptedException {
		// Navigate to Overview Dashboard (Monitor>>Dash-board)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Dashboard"));
	}

	/**
	 * This function is used to navigate to Maps in Monitor Overview
	 */
	public void MonitorMaps() throws InterruptedException {
		// Navigate to Overview Maps (Monitor>>Maps)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Maps"));
	}

	/**
	 * This function is used to navigate to Access Points in Monitor Overview
	 */
	public void MonitorAccessPoint() throws InterruptedException {
		// Navigate to Overview Access Points (Monitor>>Access Points)
		goTo(PATH.getProperty("Monitor"), PATH
				.getProperty("Monitor_AccessPoint"));
	}

	/**
	 * This function is used to navigate to Radios in Monitor Overview
	 */
	public void MonitorRadios() throws InterruptedException {
		// Navigate to Overview Radios (Monitor>>Radios)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Radios"));
	}

	/**
	 * This function is used to navigate to SSIDs in Monitor Overview
	 */
	public void MonitorSSIDs() throws InterruptedException {
		// Navigate to Overview SSIDs (Monitor>>SSIDs)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_SSIDs"));
	}

	/**
	 * This function is used to navigate to Station in Monitor Overview
	 */
	public void MonitorStations() throws InterruptedException {
		// Navigate to Overview Stations (Monitor>>Stations)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Stations"));
	}

	/**
	 * This function is used to navigate to Legacy APS in Monitor Overview
	 */
	public void MonitorLegacyAPs() throws InterruptedException {
		// Navigate to Overview Legacy APs (Monitor>>Legacy APs)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_LegacyAPs"));
	}

	/**
	 * This function is used to navigate to Rogues in Monitor Security
	 */
	public void MonitorRogues() throws InterruptedException {
		// Navigate to Monitor Security (Monitor>>Rogues)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Rogues"));
	}

	/**
	 * This function is used to navigate IDs Events in Monitor Security
	 */
	public void MonitorIDsEvents() throws InterruptedException {
		// Navigate to Monitor Security (Monitor>>IDs Events)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_IDsEvents"));
	}

	/**
	 * This function is used to navigate to Station Assurance in Monitor
	 * Troubleshooting
	 */
	public void MonitorStationAssurance() throws InterruptedException {
		// Navigate to Monitor Troubleshooting (Monitor>>Station Assurance)
		goTo(PATH.getProperty("Monitor"), PATH
				.getProperty("Monitor_StationAssurance"));
	}

	/**
	 * This function is used to navigate to Alarms in Monitor Troubleshooting
	 */
	public void MonitorAlarms() throws InterruptedException {
		// Navigate to Monitor Troubleshooting (Monitor>>Alarms)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Alarms"));
	}

	/**
	 * This function is used to Navigate to Events in Monitor Troubleshooting
	 */
	public void MonitorEvents() throws InterruptedException {
		// Navigate to Monitor Troubleshooting (Monitor>>Events)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Events"));
	}

	/**
	 * This function is used to Navigate to Overview in Monitor Application
	 * Control
	 */
	public void MonitorOverview() throws InterruptedException {
		// Navigate to Monitor Application Control (Monitor>>Overview)
		goTo(PATH.getProperty("Monitor"), PATH.getProperty("Monitor_Overview"));
	}

	// ***************************Configure**********************************

	/**
	 * This function is used to navigate to Configure
	 */
	public void Configure() throws InterruptedException {
		App_Log.debug("StartLib-Configure:Navigate to Configure");
		// Navigate to Configure
		goTo(PATH.getProperty("Configure"), null);
		App_Log.debug("EndLib-Configure");
	}

	/**
	 * This function is used to navigate to Access Points in Configure
	 */
	public void ConfigureAccesssPoint() throws InterruptedException {
		App_Log.debug("StartLib-ConfigureAccesssPoint:Navigate to Arrays, Configure=>Arrays");
		// Navigate to Access Points(Configure>>Access Points)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_AccesssPoint"));
		App_Log.debug("EndLib-ConfigureAccesssPoint");
	}

	/**
	 * This function is used to navigate to Profile in Configure
	 */
	public void ConfigureProfile() throws InterruptedException {
		App_Log.debug("StartLib-ConfigureProfile:Navigate to Profile, Configure=> Profile");
		goTo(PATH.getProperty("Configure"), PATH.getProperty("Configure_Profile"));
		App_Log.debug("EndLib-ConfigureProfile");
	}

	/**
	 * This function is used to navigate to Access Points Group in Configure
	 */
	public void ConfigureAPGroup() throws InterruptedException {
		// Navigate to Access Points Group Configure(Configure>>Access Points
		// Group)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APGroup"));
	}

	/**
	 * This function is used to navigate to Edit Template in Configure
	 */
	public void ConfigureEditTemplate() throws InterruptedException {
		// Navigate to Edit Template Configure(Configure>>Edit Template)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_EditTemplate"));
	}

	/**
	 * This function is used to navigate to Load Template in Configure
	 */
	public void ConfigureLoadTemplate() throws InterruptedException {
		// Navigate to Load Template Configure(Configure>>Load Template)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_LoadTemplate"));
	}

	/**
	 * This function is used to navigate to Deploy Template in Configure
	 */
	public void ConfigureDeployTemplate() throws InterruptedException {
		// Navigate to Deploy Template Configure(Configure>>Deploy Template)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_DeployTemplate"));
	}

	/**
	 * This function is used to navigate to Custom Field Value in Configure
	 */
	public void ConfigureCustomFieldvalue() throws InterruptedException {
		// Navigate to Custom Field Value Configure(Configure>>Custom Field
		// Value)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_CustomFieldvalue"));
	}

	/**
	 * This function is used to navigate to Wireless Settings in Configure
	 */
	public void ConfigureConfigWirelessSett()
			throws InterruptedException {
		// Navigate to Configure Wireless Settings(Configure>>Configure Wireless
		// Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ConfigWirelessSett"));
	}

	/**
	 * This function is used to navigate to Export Wireless Settings in
	 * Configure
	 */
	public void ConfigureExportWirelessSett()
			throws InterruptedException {
		// Navigate to Export Wireless Settings Configure(Configure>>Export
		// Wireless Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ExportWirelessSett"));
	}

	/**
	 * This function is used to navigate to Import Wireless Settings in
	 * Configure
	 */
	public void ConfigureImportWirelessSett()
			throws InterruptedException {
		// Navigate to Import Wireless Settings(Configure>>Import Wireless
		// Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ImportWirelessSett"));
	}

	/**
	 * This function is used to navigate to Network Settings in Configure
	 */
	public void ConfigureConfigNetworkSett() throws InterruptedException {
		// Navigate to Configure Network Settings(Configure>>Configure Network
		// Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ConfigNetworkSett"));
	}

	/**
	 * This function is used to navigate to Export Network Settings in Configure
	 */
	public void ConfigureExportNetworkSett() throws InterruptedException {
		// Navigate to Export Network Settings Configure(Configure>>Export
		// Network Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ExportNetworkSett"));
	}

	/**
	 * This function is used to navigate to Import Network Settings in Configure
	 */
	public void ConfigureImportNetworkSett() throws InterruptedException {
		// Navigate to Import Network Settings(Configure>>Import Network
		// Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ImportNetworkSett"));

	}

	/**
	 * This function is used to navigate to Alarms Configure Alarm Definitions
	 * in Configure
	 */
	public void ConfigureAlarmDefinitions() throws InterruptedException {
		// Navigate to Alarms Configure Alarm Definitions(Configure>>Configure
		// Alarm Definitions)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_AlarmDefinitions"));
	}

	/**
	 * This function is used to navigate to Alarms Configure Notification
	 * Settings in Configure
	 */
	public void ConfigureNotificationSett() throws InterruptedException {
		// Navigate to Alarms Configure Notification Settings(Configure>>
		// Notification Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_NotificationSett"));
	}

	/**
	 * This function is used to navigate to Add Device in Configure
	 */
	public void ConfigureAddDevice() throws InterruptedException {
		// Navigate to Add Device in Configure(Configure>> Add Device)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_AddDevice"));
	}

	/**
	 * This function is used to navigate to Discovery SNMPv2 Settings in
	 * Configure
	 */
	public void ConfigureSNMPv2Settings() throws InterruptedException {
		// Navigate to Discovery SNMPv2 Settings in Configure(Configure>> SNMPv2
		// Settings)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_SNMPv2Settings"));
	}

	/**
	 * This function is used to navigate to Discovery SNMPv3Users Settings in
	 * Configure
	 */
	public void ConfigureSNMPv3Users() throws InterruptedException {
		// Navigate to Discovery SNMPv3 Users in Configure(Configure>> SNMPv3
		// Users)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_SNMPv3Users"));
	}

	/**
	 * This function is used to navigate to Discovery SSH Users Settings in
	 * Configure
	 */
	public void ConfigureSSHUsers() throws InterruptedException {

		// Navigate to Discovery SSH Users in Configure(Configure>> SSH Users)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_SSHUsers"));

	}

	/**
	 * This function is used to navigate to Discovery View Network in Configure
	 */
	public void ConfigureViewNetwork() throws InterruptedException {
		// Navigate to Discovery View Network in Configure(Configure>> View
		// Network)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ViewNetwork"));

	}

	/**
	 * This function is used to navigate to Security Rogues Rules in Configure
	 */
	public void ConfigureRogueRules() throws InterruptedException {
		// Navigate to Security Rogues Rules in Configure(Configure>> Rogues
		// Rules)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_RogueRules"));

	}

	/**
	 * This function is used to navigate to Security SSID Spoofing Auto Block in
	 * Configure
	 */
	public void ConfigureSSIDSpoofingAUtoBlock()
			throws InterruptedException {
		// Navigate to Security SSID Spoofing Auto Block in
		// Configure(Configure>> SSID Spoofing Auto Block)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_SSIDSpoofingAUtoBlock"));

	}

	/**
	 * This function is used to navigate to Access Point License AP deployed
	 * License in Configure
	 */
	public void Configure_APDeployedLicenses()
			throws InterruptedException {
		// Navigate to Access Point License Deployed Licenses in
		// Configure(Configure>>Access Point Deployed Licenses)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APDeployedLicenses"));

	}

	/**
	 * This function is used to navigate to Access Point License AP Exported
	 * License in Configure
	 */
	public void ConfigureAPExportedLicenses()
			throws InterruptedException {

		// Navigate to Access Point License Exported Licenses in
		// Configure(Configure>>Access Point Exported Licenses)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APExportedLicenses"));

	}

	/**
	 * This function is used to navigate to Access Point License AP Imported
	 * License in Configure
	 */
	public void ConfigureAPImportedLicenses()
			throws InterruptedException {
		// Navigate to Access Point License Imported Licenses in
		// Configure(Configure>>Access Point Imported Licenses)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APImportedLicenses"));

	}

	/**
	 * This function is used to navigate to Access Point License AP Edit License
	 * in Configure
	 */
	public  void ConfigureAPEditLicenses() throws InterruptedException {
		// Navigate to Access Point License Edit Licenses in
		// Configure(Configure>>Access Point Edit Licenses)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APEditLicenses"));
	}

	/**
	 * This function is used to navigate to Access Point License AP Pending
	 * License in Configure
	 */
	public  void ConfigureAPPendingLicenses() throws InterruptedException {
		// Navigate to Access Point License Pending Licenses in
		// Configure(Configure>>Access Point Pending Licenses)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_APPendingLicenses"));

	}

	/**
	 * This function is used to navigate to Access Point Upgrade Perform or
	 * Schedule Upgrade in Configure
	 */
	public  void ConfigurePerformOrScheduleUpgrade()
			throws InterruptedException {
		// Navigate to Access Point Upgrade Perform or Schedule Upgrade in
		// Configure(Configure>>Perform or Schedule Upgrade)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_PerformOrScheduleUpgrade"));
	}

	/**
	 * This function is used to navigate to Access Point Upgrade Scheduled
	 * Upgrade in Configure
	 */
	public  void ConfigureScheduledUpgrade() throws InterruptedException {
		// Navigate to Access Point Upgrade Scheduled Upgrade in
		// Configure(Configure>>Scheduled Upgrade)
		goTo(PATH.getProperty("Configure"), PATH
				.getProperty("Configure_ScheduledUpgrade"));
	}

	// **************************Reports********************

	/**
	 * This function is used to navigate to Reports
	 */
	public  void Reports() throws InterruptedException {
		// Navigate to Reports
		goTo(PATH.getProperty("Reports"), null);
	}

	/**
	 * This function is used to navigate to General Reports View
	 */
	public  void ReportsView() throws InterruptedException {
		// Navigate to General Report View (Reports>>View)
		goTo(PATH.getProperty("Reports"), PATH.getProperty("Reports_View"));
	}

	/**
	 * This function is used to navigate to General Reports Create
	 */
	public  void ReportsCreate() throws InterruptedException {
		// Navigate to General Report Create (Reports>>Create)
		goTo(PATH.getProperty("Reports"), PATH.getProperty("Reports_Create"));
	}

	/**
	 * This function is used to navigate to General Reports Create
	 */
	public  void Reports_CustomHeader() throws InterruptedException {
		// Navigate to General Report Custom Header (Reports>>Custom Header)
		goTo(PATH.getProperty("Reports"), PATH
				.getProperty("Reports_CustomHeader"));
	}

	// *************************************Settings************************************************
	/**
	 * This function is used to navigate to Settings
	 */
	public  void Settings() throws InterruptedException {
		// Navigate to Settings
		goTo(PATH.getProperty("Settings"), null);
	}

	/**
	 * This function is used to navigate to General Settings About XMS/WOS in
	 * settings
	 */
	public  void SettingsAboutXMS() throws InterruptedException {
		// Navigate to General Settings About XMS/WOS (Settings>>About XMS)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_AboutXMS"));
	}

	/**
	 * This function is used to navigate to General Settings Status in settings
	 */
	public  void SettingsStatus() throws InterruptedException {
		// Navigate to General Settings Status (Settings>>Status)
		goTo(PATH.getProperty("Settings"), PATH.getProperty("Settings_Status"));
	}

	/**
	 * This function is used to navigate to General Settings Server Logs in
	 * settings
	 */
	public  void SettingsServerLogs() throws InterruptedException {
		// Navigate to General Settings Server Logs (Settings>>Server Logs)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_ServerLogs"));
	}

	/**
	 * This function is used to navigate to General Settings XMS Licenses in
	 * settings
	 */
	public  void SettingsXMSLicense() throws InterruptedException {
		// Navigate to General Settings XMS License (Settings>>XMS Licenses)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_XMSLicense"));
	}

	/**
	 * This function is used to navigate to XMS Users Management in settings
	 */
	public  void SettingsManageUser() throws InterruptedException {
		// Navigate to XMS Manage USers (Settings>>Manage Users)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_ManageUser"));
	}

	/**
	 * This function is used to navigate to Backup Manage Location in Settings
	 */
	public  void SettingsManageLocation() throws InterruptedException {
		// Navigate to Backup Manage Location (Settings>>Manage Location)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_ManageLocation"));
	}

	/**
	 * This function is used to navigate to Backup Manage Schedule in Settings
	 */
	public  void SettingsManageSchedules() throws InterruptedException {
		// Navigate to Backup Manage Schedule (Settings>>Manage Schedule)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_ManageSchedules"));
	}

	/**
	 * This function is used to navigate to Backup Now in Settings
	 */
	public  void SettingsBackupNow() throws InterruptedException {
		// Navigate to Backup Now (Settings>>Backup now)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_BackupNow"));
	}

	/**
	 * This function is used to navigate to Backup Restore in Settings
	 */
	public  void SettingsBackupRestore() throws InterruptedException {
		// Navigate to Backup Restore (Settings>>Backup Restore)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_BackupRestore"));
	}

	/**
	 * This function is used to navigate to Backup Import Backup archive in
	 * Settings
	 */
	public  void SettingsImportBackupArchive()
			throws InterruptedException {
		// Navigate to Backup Import Backup Archive (Settings>>Import Backup
		// Archive)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_ImportBackupArchive"));
	}

	/**
	 * This function is used to navigate to Backup Status in Settings
	 */
	public  void SettingsBackupStatus() throws InterruptedException {
		// Navigate to Backup Status(Settings>>Backup Status)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_BackupStatus"));
	}

	/**
	 * This function is used to navigate to Application Email in Settings
	 */
	public  void SettingsEmail() throws InterruptedException {
		// Navigate to Application Email(Settings>>Email)
		goTo(PATH.getProperty("Settings"), PATH.getProperty("Settings_Email"));
	}

	/**
	 * This function is used to navigate to Application Polling in Settings
	 */
	public  void SettingsPolling() throws InterruptedException {
		// Navigate to Application Polling(Settings>>Polling)
		goTo(PATH.getProperty("Settings"), PATH.getProperty("Settings_Polling"));
	}

	/**
	 * This function is used to navigate to Application SSH Server in Settings
	 */
	public  void SettingsSSHServer() throws InterruptedException {
		// Navigate to Application SSH Server(Settings>>SSH Server)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_SSHServer"));
	}

	/**
	 * This function is used to navigate to Application Web Server in Settings
	 */
	public  void Settings_WebServer() throws InterruptedException {
		// Navigate to Application Web Server(Settings>>Web Server)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_WebServer"));
	}

	/**
	 * This function is used to navigate to Application SNMP Trap Receiver in
	 * Settings
	 */
	public  void SettingsSNMPTrapReceiver() throws InterruptedException {
		// Navigate to Application SNMP Trap receiver Server(Settings>>SNMP Trap
		// Receiver)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_SNMPTrapReceiver"));
	}

	/**
	 * This function is used to navigate to Application Audit Logs in Settings
	 */
	public  void Settings_AuditLogs() throws InterruptedException {
		// Navigate to Application Audit Logs(Settings>>Audit Logs)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_AuditLogs"));
	}

	/**
	 * This function is used to navigate to Customization Create Custom fields
	 * in Settings
	 */
	public  void SettingsCreateCustomFields() throws InterruptedException {
		// Navigate to Customization Create Custom Fields(Settings>>Create
		// Custom Fields)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_CreateCustomFields"));
	}

	/**
	 * This function is used to navigate to Customization Create Custom Action
	 * in Settings
	 */
	public  void SettingsCreateCutomAction() throws InterruptedException {
		// Navigate to Customization Create Custom Action(Settings>>Create
		// Custom Action)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_CreateCutomAction"));
	}

	/**
	 * This function is used to navigate to Supports Access Point Diagnostic
	 * LogsCustomization Create Custom Action in Settings
	 */
	public  void SettingsAPDigLogUpload() throws InterruptedException {
		// Navigate to Support Access Point Diagnostic Logs(Settings>>AP Diag
		// Logs)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_APDigLogUpload"));
	}

	/**
	 * This function is used to navigate to System Network in Settings
	 */
	public  void Settings_Network() throws InterruptedException {
		// Navigate to System Network(Settings>>Network)
		goTo(PATH.getProperty("Settings"), PATH.getProperty("Settings_Network"));
	}

	/**
	 * This function is used to navigate to System DATE TIME in Settings
	 */
	public  void SettingsDateTime() throws InterruptedException {
		// Navigate to System Date Time(Settings>>Date Time)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_DateTime"));
	}

	/**
	 * This function is used to navigate to Maintenance Upgrade in Settings
	 */
	public  void SettingsUpgrade() throws InterruptedException {
		// Navigate to Maintenance Upgrade(Settings>>Upgrade)
		goTo(PATH.getProperty("Settings"), PATH.getProperty("Settings_Upgrade"));
	}

	/**
	 * This function is used to navigate to Maintenance Factory Reset in
	 * Settings
	 */
	public  void SettingsFactoryReset() throws InterruptedException {
		// Navigate to Maintenance Factory Reset(Settings>>Factory Reset)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_FactoryReset"));
	}

	/**
	 * This function is used to navigate to API Settings in Settings
	 */
	public  void SettingsAPISettings() throws InterruptedException {
		// Navigate to API Settings(Settings>>API Settings)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_APISettings"));
	}

	/**
	 * This function is used to navigate to API Documentation in Settings
	 */
	public  void SettingsAPIDocumentation() throws InterruptedException {
		// Navigate to API Documentation(Settings>>API Documentation)
		goTo(PATH.getProperty("Settings"), PATH
				.getProperty("Settings_APIDocumentation"));

	}
}
