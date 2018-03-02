package com.tns.automation.pinterest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.TestNG;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class Pint extends DriverManagementSetUp {
	 static File strPath;
	 static String strSheetName;
	 static XSSFSheet sheet;
	 static XSSFSheet sheetOther;
	 static XSSFWorkbook workbook;
	 static Object[][] rawData;
	 static XSSFCell cell;
	 static XSSFRow row;
	LoginPage loginPage;
	PinsPage pinsPage;
	FileReader reader;
	Properties property;
	public static String randomAgent() {
			String[] strUserAgent= {"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.1 Safari/537.36","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36"
					,"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2226.0 Safari/537.36","Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36","Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2224.3 Safari/537.36","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1","Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)","Googlebot/2.1 (+http://www.googlebot.com/bot.html)","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:2.0) Treco/20110515 Fireweb Navigator/2.4"
					,"Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; SV1; Crazy Browser 9.0.04)","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0; Crazy Browser 3.1.0)"};
	java.util.Random ran=new java.util.Random();
	int any=ran.nextInt(strUserAgent.length);

	return strUserAgent[any];
		}
	@BeforeTest
	public void login() throws InterruptedException, IOException {
		String path = PintMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();

		String decodedPath = URLDecoder.decode(path, "UTF-8");
		int index=decodedPath.lastIndexOf('/');
		String strPath=decodedPath.substring(0,index);
		reader=new FileReader(strPath+File.separator+"pint.properties");  
		//reader=new FileReader("pint.properties");    
	    property=new Properties(); 
	    property.load(reader);  
		// Moving to pinterest site
		/*driver.get("https://in.pinterest.com/login/");
		// Login page object creation
		loginPage = new LoginPage(driver);
		// Passing credentials
		loginPage.Login(property.getProperty("emailID","terru104@gmail.com"), property.getProperty("password", "admin@123"));
		// Login object confirming and moving to Pins page
		pinsPage = loginPage.getLogin();
	*/	
	}
	@Test(dataProvider="excelData",description="Actual Test Execution")
	public void Pinter(String strDescription, String strImageName, String strSiteURL, String strSiteCategory) throws InterruptedException, IOException {
		DesiredCapabilities dc=DesiredCapabilities.chrome();
		ChromeOptions cm=new ChromeOptions();
		cm.addArguments("-incognito");
		cm.addArguments("--start-maximized");
		cm.addArguments("--user-agent="+randomAgent());
		dc.setCapability(ChromeOptions.CAPABILITY, cm);
		driver = new ChromeDriver(cm);
		driver.get("https://www.pinterest.co.uk");
		Thread.sleep(2000);
		// Login page object creation
		loginPage = new LoginPage(driver);
		// Passing credentials
		loginPage.Login(property.getProperty("emailID","terru104@gmail.com"), property.getProperty("password", "admin@123"));
		// Login object confirming and moving to Pins page
		pinsPage = loginPage.getLogin();	
	    
		//String strPintCategory=property.getProperty("pint_category", "Tedt");
	
		// Moving to dynamic value from excel-site URL
		pinsPage.navigation(strSiteURL);
		// Implicit wait as forcing site to be set for a value
		Thread.sleep(8000);
		
		pinsPage.categoryAndDescription(strDescription, strSiteCategory,strImageName);
		Thread.sleep(1000);
			}
	 @DataProvider(name = "excelData")
	 public Object[][] getTableObject() throws IOException {
		// Data provider, row by row data will be provided.
		 
		strPath = new File(property.getProperty("fileName"));
		strSheetName=property.getProperty("sheet_name");
	  sheetOther = DataSheet(strPath, strSheetName);
	  int totalRows = sheetOther.getLastRowNum();
	  rawData = new Object[totalRows][4];
	  System.out.println(totalRows);
	  for (int i = 0; i< totalRows; i++) {
	   for (int j = 0; j<= 3; j++) {
		   switch (j) {
		   case 0: 
			   		String strDescription=getCellData(strSheetName, (i+1), (j+1));
			   		rawData[i][j]=strDescription;
			   		//System.out.println((i+1)+": i>>>"+(j+1)+":j<<"+rawData[i][j]);
			   break;
		   case 1: 
			   		String UrlFull=getCellData(strSheetName, (i+1), (j+1));
				   rawData[i][j]=fileNameFromUrlSubString(UrlFull);
				   //System.out.println((i+1)+": i>>>"+(j+1)+":j<<"+rawData[i][j]);
			   break;
		   case 2: 
			   		String UrlSite=getCellData(strSheetName, (i+1), (j+1));
		   			rawData[i][j]=UrlSite; 
		   			//System.out.println((i+1)+": i>>>"+(j+1)+":j<<"+rawData[i][j]);
			   break;
		   case 3: 
		   		String strCategory=getCellData(strSheetName, (i+1), (j+1));
	   			rawData[i][j]=strCategory; 
	   			//System.out.println((i+1)+": i>>>"+(j+1)+":j<<"+rawData[i][j]);
		   break;
		   }
		}
	  }
	  //System.out.println("Total Rows:"+totalRows);
	  return rawData;
	 }
		
	 // Function responsible to take image name from URL
	 public String fileNameFromUrlSubString(String stURL){
			int it=stURL.lastIndexOf("/");
			int length=stURL.length();
			String sub=stURL.substring((it+1), length);
			//System.out.println(it+": "+length+": "+sub);
			return sub;
			}
	
	// Method responsible to get DataSheet	
	public static XSSFSheet DataSheet(File strPath2, String SheetName) {
	  File file = strPath2;
	  try {
	   FileInputStream fis = new FileInputStream(file);
	   workbook = new XSSFWorkbook(fis);
	   sheet = workbook.getSheetAt(0);
	   fis.close();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return sheet;
	 }
	 @SuppressWarnings("deprecation")
	public static String getCellData(String Sheet, int row1, int col) {
		 try {
			 
			 sheet = workbook.getSheetAt(0);
			 row = sheet.getRow(row1);
			 if (row == null)
			 return "";
		   cell = row.getCell(col);
		   if (cell == null)
		     return "";
		   switch (cell.getCellType()) {

		   case Cell.CELL_TYPE_STRING:

		    return cell.getStringCellValue();
		   case Cell.CELL_TYPE_BOOLEAN:

		    return String.valueOf(cell.getBooleanCellValue());
		   case Cell.CELL_TYPE_BLANK:
		    return "";
		   case Cell.CELL_TYPE_ERROR:
		    return cell.getStringCellValue();
		   case Cell.CELL_TYPE_NUMERIC:
		    return String.valueOf(cell.getNumericCellValue());
		   default:
		    return "Cell not found";
		   }
		  }
		 catch (Exception e) {
		   e.printStackTrace();
		   return "row " + row + " or column " + col + " does not exist in xls";
		  }
	 }
}


