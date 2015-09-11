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
	
	/**
	 * This method retrieves a WebElement by it's By selector
	 * @param: By selector
	 * @return: WebElement
	 */
	public WebElement findElementBySelector(By selector) {
		WebElement element = (new WebDriverWait(this.driver, this.timeout)).
				until(ExpectedConditions.presenceOfElementLocated(selector));
		return element;
	}
	
	/**
	 * This method retrieves a List of WebElements by it's By selector
	 * @param: By selector
	 * @return: List<WebElement>
	 */
	public List<WebElement> findAllElementsBySelector(By selector) {
		List<WebElement> elements = (new WebDriverWait(this.driver, this.timeout)).
				until(ExpectedConditions.presenceOfAllElementsLocatedBy(selector));
		return elements;
	}
	
	/**
	 * This method retrieves a List of WebElements by it's container with the subElement selector
	 * @param: By container, By subElement
	 * @return: List<WebElement>
	 */
	public List<WebElement> findAllElementsInContainerBySelector(By container, By subElement) {
		WebElement element = (new WebDriverWait(this.driver, this.timeout)).
				until(ExpectedConditions.presenceOfElementLocated(container));
		List<WebElement> subElements = element.findElements(subElement);
		return subElements;
	}
	
	/**
	 * This method clicks in an element passed by parameters
	 * @param: WebElement element
	 * @return: void
	 */
	public void clickElement(WebElement element) {
		new WebDriverWait(this.driver, this.timeout).
				until(ExpectedConditions.elementToBeClickable(element)).click();
		
	}
	
	/**
	 * This method sends keys to an element passed by parameters
	 * @param: WebElement element, String keys
	 * @return: void
	 */
	public void sendKeysToWebElement(WebElement element, String keys) {
		if(element != null && element.isEnabled())
			element.sendKeys(keys);
		else
			throw new InvalidElementStateException("There was an error to edit the element");
	}
	
	/**
	 * This method retrieves the text from a WebElement passed by parameters
	 * @param: WebElement element
	 * @return: String
	 */
	public String getTextFromElement(WebElement element) {
		if(element != null)
			return element.getText();
		else
			throw new InvalidElementStateException("There was an error to retrieve the text from element");
	}
	
	/**
	 * This method selects a drop down option by it's visible text passed by parameters
	 * @param: WebElement dropDown, String optionText
	 * @return: void
	 */
	public void selectDropdownOptionByText(WebElement dropDown, String optionText) {
		Select list = new Select(dropDown);
		list.selectByVisibleText(optionText);
	}
	
	/**
	 * This method retrieves a WebElement attribute by it's property name passed by parameters
	 * @param: WebElement element, String property
	 * @return: String
	 */
	public String getElementAttributeByPropertyName(WebElement element, String property) {
		if(element != null)
			return element.getAttribute(property);
		else
			throw new InvalidElementStateException("There was an error to retrieve the atrtibute from the element");
	}
}
