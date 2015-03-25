package test;

//import com.xirrus.xms.localLib.DataFile_Reader;
//import com.xirrus.xms.localLib.DataSheet_Reader;
//import com.xirrus.xms.localLib.Test_Utility;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;

import com.xirrus.xms.globallib.SslShell;

public class SuiteRunMode {
	// Write function to read TestSuite.xls file to identify which test suite
	// need to run
	FileWriter resultFile = null;
	File file = null;
	public static void main(String[] args) throws Exception {
		SuiteRunMode sam=new SuiteRunMode();
		System.out.println("Hello world");

		sam.checkResultFile();
		sam.writeResultFile("hello bro");
		sam.writeResultFile("I am still same");
		sam.checkResultFile();
		sam.writeResultFile("back");
		sam.writeResultFile("how");
		System.out.println("Wait world");
	}

	public void writeResultFile(String str) {
		try{
			file = new File("c://workspace//logs//Result.txt");
			resultFile = new FileWriter(file);
			resultFile.append(str);
			resultFile.flush();
			resultFile.close();
			System.out.println("File written Succesfully");
		}catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public void checkResultFile(){
		try {
			file = new File("c://workspace//logs//Result.txt");
			if (!file.exists()) {
				file.createNewFile();
			}else{
				file.deleteOnExit();
				file.createNewFile();
			}
	}		 catch (IOException e) {
		e.printStackTrace();
	}
}}
