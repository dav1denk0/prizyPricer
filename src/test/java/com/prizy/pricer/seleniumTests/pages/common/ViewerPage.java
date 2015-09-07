package com.prizy.pricer.seleniumTests.pages.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ViewerPage extends IndexPage{

	private By recordMessage = By.cssSelector("#show-registry > div");
	private By recordList = By.cssSelector(".property-list.registry");
	private By recordRows = By.cssSelector(".fieldcontain");
	
	public ViewerPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public String getMessageText() {
		try {
			WebElement message = findElementBySelector(this.recordMessage);
			return getTextFromElement(message);
		}catch(NoSuchElementException e) {
			return e.getMessage();
		}
	}
	
	public Boolean validateCreatedRecordProperty(String propertyName, String propertyValue) {
		WebElement recordContainer = findElementBySelector(this.recordList);
		List<WebElement> records = recordContainer.findElements(this.recordRows);
		for(WebElement rec : records) {
			String recordName = getTextFromElement(rec.findElement(By.cssSelector("property-label")));
			String recordValue = getTextFromElement(rec.findElement(By.cssSelector("property-value")));
			if(recordName.equalsIgnoreCase(propertyName) && recordValue.equalsIgnoreCase(propertyValue))
				return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}
