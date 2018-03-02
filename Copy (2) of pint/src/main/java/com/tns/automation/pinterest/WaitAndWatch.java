package com.tns.automation.pinterest;

import java.util.List;
import java.util.concurrent.TimeUnit;



import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;



public class WaitAndWatch extends DriverManagementSetUp {



	public WaitAndWatch(WebElement weAny, long lng) {

		new WebDriverWait(driver, lng).pollingEvery(1, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOf(weAny));

	}

	public WaitAndWatch(List<WebElement> weAny, long lng) {

		new WebDriverWait(driver, lng).withMessage("explicity wait").pollingEvery(2, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfAllElements(weAny));

	}

}
