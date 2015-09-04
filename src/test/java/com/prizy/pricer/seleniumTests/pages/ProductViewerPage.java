package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductViewerPage extends IndexPage {
	
	private By productPropertiesList = By.cssSelector("#show-product > ol");
	private By productMessage = By.cssSelector("#show-product > div");
	
	public ProductViewerPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public String getProductMessageText() {
		try {
			WebElement message = findElementBySelector(this.productMessage);
			return getTextFromElement(message);
		}catch(NoSuchElementException e) {
			return e.getMessage();
		}
	}
}
