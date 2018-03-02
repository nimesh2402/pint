package com.tns.automation.pinterest;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.LoadableComponent;

import org.testng.Assert;



public class PageFactoryInit extends LoadableComponent<LoginPage> {

 public WebDriver driver;



 public PageFactoryInit(WebDriver driver) {

  
  this.driver = driver;

  PageFactory.initElements(driver, this);

 }



 @Override

 protected void load() {

  // TODO Auto-generated method stub



 }



 @Override

 protected void isLoaded() throws Error {

  Assert.assertEquals(driver.getTitle(), "Pinterest");

 }



}
