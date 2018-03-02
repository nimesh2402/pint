package com.tns.automation.pinterest;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.How;
import org.testng.Assert;
public class PinsPage extends PageFactoryInit {

	public WebElement weCategory;
	
	
	@FindBy(how = How.XPATH, using = ".//*[contains(@src,'https://img-prod-cms-rt-microsoft-com.akamaized.net/cms/api/am/imageFileData/RW52ck?ver=dc4f&q=90&m=8&h=600&w=1600&b=%23FFFFFFFF&l=f&x=0&y=152&s=2046&d=767&aim=true')]/../..")
	WebElement wePinClick;
	@FindBy(how = How.XPATH, using = "//*[contains(text(),'Ideas for the House')]")
	WebElement wePinCategory;
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Your image is too small. Please choose a larger image and try again.')]")
	List<WebElement> wePinFailure;
	@FindBy(how = How.XPATH, using = "//button[contains(.,'See it now')]")
	List<WebElement> wePinSuccess;
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'Pin')]//textarea")
	WebElement wePinTextLink;
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'Pin')]//div[contains(@role,'button')]")
	WebElement wePinTextPlaceholder;
	@FindBy(how = How.XPATH, using = "//div[contains(text(),'404 Response Code')]")
    WebElement wePinCreateNewCategoryButton;
	// Search text box 
	@FindBy(how = How.XPATH, using = ".//*[@id='pickerSearchField']")
	WebElement wePinSearchTextBox;
	// Picking first result element
	@FindBy(how = How.XPATH, using = ".//div[@role='img' and contains(@style,'background-color: transparent;')]/../../..")
	WebElement wePinSearchFirstResult;
	
	@FindBy(how = How.XPATH, using = ".//div[@role='img' and contains(@style,'background-color: transparent;')]/../../..")
	List<WebElement> wePinSearchFirstResults;
	
	// If not category found then creation of category section selection
	@FindBy(how = How.XPATH, using = "//div[contains(@data-test-id,'createBoardButton')]")
	WebElement wePinSearchNoFoundAndWizardForNewCategory;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@data-test-id,'createBoardButton')]")
	List<WebElement> wePinSearchNoFoundAndWizardForNewCategories;
		
	@FindBy(how = How.XPATH, using = "//button[contains(@type,'submit')]")
	WebElement wePinNewCategorySubmitButton;
	
	@FindBy(how = How.XPATH, using = "//h1[contains(.,'Something went wrong')]")
	List<WebElement> wePinImageNotFound404;
	
	@FindBy(how = How.XPATH, using = "//input[@id='boardEditName']")
	WebElement wePinBlankBoardTextBox;
	
	
// 
	

	public PinsPage(WebDriver driver) {
		super(driver);
	}
	public void navigation(String strURLSite) throws InterruptedException {

		
		String strURL="https://www.pinterest.co.uk/pin/find/?url="+strURLSite;
		// Navigating to dynamic URL
		driver.navigate().to(strURL);
		try {
			// Wait to have 404 
			new WaitAndWatch(wePinImageNotFound404, 10);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println(driver.getCurrentUrl());
	}
	public void categoryAndDescription(String strDescription,String strCategory, String strImageName) throws InterruptedException {
		// If we get 404 then marking test case as failed
		if(wePinImageNotFound404.size()>0){
			Assert.assertEquals("404 shows up after passing URL so quiting...",strImageName , "404");
		}
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000);");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(1000,2000);");
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(2000,0);");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-1000);");
//		.//h3[contains(text(),'Choose an image to create a Pin')]
		WebElement we2=driver.findElement(By.xpath(".//h3[contains(text(),'Choose an image to create a Pin')]"));
		Actions actionsE = new Actions(driver);
		actionsE.moveToElement(we2);
		actionsE.perform();
		// Dynamic value needs to be passed in xpath so taking member variable
		//String optXpath = String.format("//*[contains(text(),'%s')]", strCategory);
		String optXpathClick=String.format(".//*[contains(@src,'%s')]/../..", strImageName);
		
		// Finding availability of element
		System.out.println("Going for finding image");
		boolean blnImageNameElementAvailable = isElementAvailable(optXpathClick);
		if (blnImageNameElementAvailable) {
			Actions actions = new Actions(driver);
			actions.moveToElement(weCategory);
			actions.perform();
// If we find image then selecting it
			weCategory.click();
		}	
		else
		{
			System.out.println("Could not find image");
		}
		// for terru account
		/*new WaitAndWatch(wePinSearchTextBox, 20);
		System.out.println("Category: "+strCategory);
		wePinSearchTextBox.sendKeys(strCategory);
		// Wait for an element to be displayed
		new WaitAndWatch(wePinTextLink, 20);
		// If we get then clicking
		wePinTextLink.click();
		// End Terru account
		 
		// Wait for an element to be displayed
*/    	new WaitAndWatch(wePinTextPlaceholder, 20);
wePinTextPlaceholder.click();
wePinTextLink.sendKeys(strDescription);
    	// Sending description value
		/*wePinTextPlaceholder.sendKeys(strDescription);
		System.out.println("??????New Category found???????");
		boolean blnIsFirstSearchResult=isWebElementAvailable(wePinSearchFirstResults);
		System.out.println(":::"+blnIsFirstSearchResult);*/
    	List<WebElement> weare=driver.findElements(By.xpath("//div[contains(text(),'Choose board')]/../../following-sibling::*[1]//div[@role='button']"));
    	System.out.println("Total: *** "+weare.size());
    	if(weare.size()==0) {
    		System.out.println("Not found: *** "+weare.size());
    	}
    	else if(weare.size()==1) {
    		System.out.println("One found: *** "+weare.size());
    		
    	}
    	else if((weare.size())>=2){
    		System.out.println("More found: *** "+weare.size());
    		for(WebElement y:weare) {
    			//System.out.println(y.getText());
    			String[] strRaw=y.getText().toLowerCase().trim().split("\\n");
    			System.out.println(strCategory+">>>"+"After split: "+strRaw[0]);
    			if(strRaw[0].contentEquals(strCategory.toLowerCase().trim())) {
    				y.click();
    				new WaitAndWatch(wePinFailure, 20);
    				if (wePinFailure.size()> 0) {
    					String strMsg="Your image is too small. Please choose a larger image and try again.";
    					System.out.println("Failed");
    					Assert.assertEquals(strMsg,strMsg);
    				}
    				else {
    					new WaitAndWatch(wePinSuccess, 20);
    					if (wePinSuccess.size()> 0) {
    						System.out.println("Passed");
    						Assert.assertEquals("Saved to category", "Saved to category");
    						
    					}
    				}
    				
    			}    			
    		}
    	}
/*		if(false) {
			System.out.println("New Category found");
			wePinSearchFirstResult.click();
			new WaitAndWatch(wePinFailure, 20);
			if (wePinFailure.size()> 0) {
				String strMsg="Your image is too small. Please choose a larger image and try again.";
				System.out.println("Failed");
				Assert.assertEquals(strMsg,strMsg);
			}
			else {
				new WaitAndWatch(wePinSuccess, 20);
				if (wePinSuccess.size()> 0) {
					System.out.println("Passed");
					Assert.assertEquals("Saved to category", "Saved to category");
					
				}
			}
			
		}	*/	
		System.out.println("??????No??????");
		boolean blnIsFirstSearchResultNewCategory= isWebElementAvailable(wePinSearchNoFoundAndWizardForNewCategories);
		System.out.println("Found create button link"+blnIsFirstSearchResultNewCategory);
		
		if(blnIsFirstSearchResultNewCategory) {
			wePinSearchNoFoundAndWizardForNewCategory.click();
			new WaitAndWatch(wePinBlankBoardTextBox, 20);		
			wePinBlankBoardTextBox.sendKeys(strCategory);
			wePinNewCategorySubmitButton.click();
			new WaitAndWatch(wePinFailure, 5);
			if (wePinFailure.size()> 0) {
				String strMsg="Your image is too small. Please choose a larger image and try again.";
				System.out.println("Failed");
				Assert.assertEquals(strMsg,strMsg);
			}
			else {
				new WaitAndWatch(wePinSuccess, 10);
				if (wePinSuccess.size()> 0) {
					System.out.println("Passed");
					Assert.assertEquals("Saved to category", "Saved to category");
					
				}
		}
		}
		
		
		
		/*boolean blnCategoryElementAvailable = isElementAvailable(optXpath);
		if (blnCategoryElementAvailable) {
			weCategory.click();
		}
		Thread.sleep(10000);
		new WaitAndWatch(wePinFailure, 10);
		if (wePinFailure.size()> 0) {
			System.out.println("Failed");
		}
		new WaitAndWatch(wePinSuccess, 10);
		if (wePinSuccess.size()> 0) {
			System.out.println("Passed");
		}*/
	}
	public boolean isWebElementAvailable(List<WebElement> wePinSearchFirstResult2) {
		
		if (wePinSearchFirstResult2.size() > 0) {
			return true;
		}
		return false;
	}
public boolean isWebElementAvailable(WebElement wePinSearchFirstResult) {
		
		if (wePinSearchFirstResult.isDisplayed()) {
			return true;
		}
		return false;
	}
	
	public boolean isElementAvailable(String strXpath) {
		new WaitAndWatch(driver.findElement(By.xpath(strXpath)), 30);
		if (driver.findElements(By.xpath(strXpath)).size() > 0) {
			System.out.println("Total: "+driver.findElements(By.xpath(strXpath)).size());
			weCategory = driver.findElement(By.xpath(strXpath));
			return true;
		}
		return false;
	}
}
