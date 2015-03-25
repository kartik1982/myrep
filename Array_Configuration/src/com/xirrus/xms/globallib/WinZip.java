package com.xirrus.xms.globallib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
// This class is going to handle all zip and UnZip(TBD) operation
public class WinZip {
	public static void zipFolder(String source, String destination)
			throws Exception {
		zipDirectory(source, destination);
	}
	
	
	/**
	 * This function will create zip file from input source folder to mentioned
	 * destination location with name passed
	 * 
	 * @param srcFolder
	 * @param destZipFile
	 * @throws Exception
	 */
	static private void zipDirectory(String srcFolder, String destZipFile)
			throws Exception {
		ZipOutputStream zip = null;
		FileOutputStream fileWriter = null;
		
		fileWriter = new FileOutputStream(destZipFile);
		zip = new ZipOutputStream(fileWriter);
		
		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}
	
	
	/**
	 * This function will look for input type file or folder and zip file to zip
	 * file
	 * 
	 * @param path
	 * @param srcFile
	 * @param zip
	 * @throws Exception
	 */
	static private void addFileToZip(String path, String srcFile,
			ZipOutputStream zip) throws Exception {
		
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
		}
	}
	
	
	/**
	 * This function is used to zip folder to zip file.
	 * 
	 * @param path
	 * @param srcFolder
	 * @param zip
	 * @throws Exception
	 */
	static private void addFolderToZip(String path, String srcFolder,
			ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);
		
		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/"
						+ fileName, zip);
			}
		}
	}
}
