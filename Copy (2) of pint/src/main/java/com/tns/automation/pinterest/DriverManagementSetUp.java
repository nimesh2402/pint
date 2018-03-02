package com.tns.automation.pinterest;



import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class DriverManagementSetUp {

protected static WebDriver driver;


	@BeforeSuite (description="Chrome Opening and config")
	public void setUp() throws UnsupportedEncodingException {
		String path = PintMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		int index=decodedPath.lastIndexOf('/');
		String strPath=decodedPath.substring(0,index);
		
		
		// Setting up system property to find chromedriver
		//System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", strPath+File.separator+"chromedriver.exe");
System.setProperty("webdriver.chrome.logfile", "chromeLogFile");
		/*DesiredCapabilities dc=DesiredCapabilities.chrome();*/
		/*ChromeOptions cm=new ChromeOptions();
		cm.addArguments("-incognito");
		cm.addArguments("--start-maximized");
		dc.setCapability(ChromeOptions.CAPABILITY, cm);*/
		/*driver = new ChromeDriver(cm);*/
	
		System.out.println("Driver setup");
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	@AfterSuite
	public void tearDown() {
		// Closing down driver
		/*driver.quit();*/
		System.out.println("Driver teared down");
	}
}