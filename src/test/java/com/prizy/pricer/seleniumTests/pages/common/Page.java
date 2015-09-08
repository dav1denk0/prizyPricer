package com.prizy.pricer.seleniumTests.pages.common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	private WebDriver driver;
	private Integer timeout;
	
	public Page(WebDriver driver, Integer timeout) {
		this.driver = driver;
		this.timeout = timeout;
	}
	
	public WebElement findElementBySelector(By selector) {
		WebElement element = (new WebDriverWait(driver, timeout)).
				until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}
	
	public List<WebElement> findAllElementsBySelector(By selector) {
		List<WebElement> elements = (new WebDriverWait(driver, timeout)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(selector));
		return elements;
	}
	
	public List<WebElement> findAllElementsInContainerBySelector(By container, By subElement) {
		WebElement element = (new WebDriverWait(driver, timeout)).
				until(ExpectedConditions.presenceOfElementLocated(container));
		List<WebElement> subElements = element.findElements(subElement);
		return subElements;
	}
	
	/* public void clickElement(WebElement element) {
		if(element != null && element.isDisplayed() && element.isEnabled())
			element.click();
		else
			throw new InvalidElementStateException("There was an error to perfom the click over the element");
	}*/
	
	public void clickElement(WebElement element) {
		new WebDriverWait(driver, timeout).
				until(ExpectedConditions.elementToBeClickable(element)).click();
		
	}
	
	public void sendKeysToWebElement(WebElement element, String keys) {
		if(element != null && element.isEnabled())
			element.sendKeys(keys);
		else
			throw new InvalidElementStateException("There was an error to edit the element");
	}
	
	public String getTextFromElement(WebElement element) {
		if(element != null)
			return element.getText();
		else
			throw new InvalidElementStateException("There was an error to retrieve the text from element");
	}
	
	public void selectDropdownOptionByText(WebElement dropDown, String optionText) {
		Select list = new Select(dropDown);
		list.selectByValue(optionText);
	}
	
	public String getElementAttributeByPropertyName(WebElement element, String property) {
		if(element != null)
			return element.getAttribute(property);
		else
			throw new InvalidElementStateException("There was an error to retrieve the atrtibute from the element");
	}
}
