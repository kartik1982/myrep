package test;

import com.xirrus.xms.base.TestBase;
import com.xirrus.xms.globallib.*;





public class MyLogginTestClass extends TestBase{

	
public String getValuebyCLI_ContactInfo(String arrayIP, String arrayUsr, String arrayPssword, String strField )throws Exception{
	final String arrayIPadd=arrayIP;
	final String arrayUser=arrayUsr;
	final String arrayPasswd= arrayPssword;
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
	
	String lines[] = output.split("\\r?\\n");
	for(int i=0;i<lines.length;i++){
		if(lines[i].contains(expcfield))
			expcfiledValue= lines[i];
	}
	expcfiledValue=expcfiledValue.replace(expcfield, "").trim();
	return expcfiledValue;

}
	public static void main(String[] args) throws Exception {
		
//		// Initialize a ConnBean object, parameter list is ip, username, password
//		ConnBean cb = new ConnBean("10.100.59.174", "admin","admin");
//		// Put the ConnBean instance as parameter for SSHExec static method getInstance(ConnBean) to retrieve a singleton SSHExec instance
//		SSHExec  ssh = SSHExec.getInstance(cb);          
//		// Connect to server
//		ssh.connect();
//		SSHExec.showEnvConfig();
//		CustomTask sampleTask = new ExecCommand("echo 123");
//		ssh.exec(sampleTask);
		//ssh.disconnect();
//		String newSet;
		
		
		SslShell myshell = new SslShell("10.100.85.13", "22", "admin", "admin");
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
		String expctedString=null;
		String inputfiled="Contact Name";
		String lines[] = output.split("\\r?\\n");
		for(int i=0;i<lines.length;i++){
			if(lines[i].contains(inputfiled))
				expctedString= lines[i];
		}
		expctedString=expctedString.replace(inputfiled, "").trim();

		 
		System.out.println(expctedString);
		System.out.println("Hello world");
//		System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//         Actions actions = new Actions(driver);
//		//WebDriver driver = new FirefoxDriver();
//        
//        // Go to the Google Suggest home page
//        driver.get("http://10.100.55.249:9090");
//        
//        WebElement loginUserName= driver.findElement(By.xpath(".//*[@id='id1']"));
//        loginUserName.sendKeys("admin");
//        WebElement loginPasswd = driver.findElement(By.xpath(".//*[@id='id2']/table[2]/tbody/tr[2]/td[2]/input"));
//        loginPasswd.sendKeys("admin");
//        WebElement loginBtn= driver.findElement(By.name("loginButton"));
//        loginBtn.click();
//        
//
//		
//		//focus to the popup window using
//		newSet=driver.getWindowHandle();
//		driver.switchTo().window(newSet); 
//		//click the button with any attribute
//		driver.findElement(By.xpath("html/body/div[3]/div[1]")).click(); 
//		
//		driver.findElement(By.className("ui-dialog-buttonset")).click();
//		WebElement btnClear= driver.findElement(By.xpath("html/body/div[1]/div[3]/div/div/div/div[2]/div/div[1]/form/div[2]/div[1]/div[1]/span/span/span/span[1]/span/input"));
//		btnClear.click();
		
		
	}		
}
