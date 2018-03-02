package com.tns.automation.pinterest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.TestNG;

public class PintMain {

	public static void main(String[] args) throws IOException {
			
		String path = PintMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		String decodedPath = URLDecoder.decode(path, "UTF-8");
		TestNG tn=new TestNG(); 
		List<String> suiteCases=new ArrayList<String>();
		int index=decodedPath.lastIndexOf('/');
		String strPath=decodedPath.substring(0,index);
	    
	    //System.out.println(decodedPath.substring(0,index));
		suiteCases.add(strPath+File.separator+"testng.xml");
		tn.setTestSuites(suiteCases);
		tn.setOutputDirectory(strPath+File.separator+"TestResult");
		tn.run();
		
		
		/*suiteCases.add("testng.xml");
		tn.setTestSuites(suiteCases);
		tn.setOutputDirectory("TestResult");
		tn.run();*/

	}

}
