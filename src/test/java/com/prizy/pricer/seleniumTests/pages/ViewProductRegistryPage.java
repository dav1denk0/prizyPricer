package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prizy.pricer.seleniumTests.pages.common.ViewerPage;

public class ViewProductRegistryPage extends ViewerPage {
	
	private By newRegistry = By.linkText("Nuevo Registry");
	
	public ViewProductRegistryPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void clickCreateNewRegistry() {
		WebElement newRegistryButton = findElementBySelector(this.newRegistry);
		clickElement(newRegistryButton);
	}
}
