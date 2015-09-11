package com.prizy.pricer.seleniumTests.pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ViewProductPage extends IndexPage {
	
	private By createNewProductButton = By.linkText("Nuevo Product");
	private By recordProductMessage = By.cssSelector("#show-product > div");
	private By recordList = By.cssSelector(".property-list.product");
	private By recordRows = By.cssSelector(".fieldcontain");
	
	public ViewProductPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void clickCreateNewProduct() {
		WebElement createButton = findElementBySelector(this.createNewProductButton);
		clickElement(createButton);
	}
	
	public String getMessageText() {
		try {
			WebElement message = findElementBySelector(this.recordProductMessage);
			return getTextFromElement(message);
		}catch(NoSuchElementException e) {
			return e.getMessage();
		}
	}
	
	public String validateCreatedProduct(Map<String, String> loaderValues) {
		Map<String, String> values = loaderValues;
		WebElement recordContainer = findElementBySelector(this.recordList);
		List<WebElement> records = recordContainer.findElements(this.recordRows);
		for(WebElement rec : records) {
			String recordName = getTextFromElement(rec.findElement
					(By.cssSelector(".property-label")));
			String recordValue = getTextFromElement(rec.findElement
					(By.cssSelector(".property-value")));
			if(!values.containsKey(recordName) || !values.get(recordName)
					.equalsIgnoreCase(recordValue.replace(",", ".")))
				return "The property " + recordName + " with the value " 
					+ recordValue + " was not found in the page";
		}
		return "";
	}
}
