package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class CreateProductPage extends IndexPage {

	public static final String PAGE_PATH = "product/create";
	private By barCodeTextField = By.id("barCode");
	private By descriptionTextField = By.id("description");
	private By createButton = By.id("create");
	
	public CreateProductPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void setTextInBarCodeField(String text) {
		WebElement barCodeField = findElementBySelector(this.barCodeTextField);
		sendKeysToWebElement(barCodeField, text);
	}
	
	public void setTextInDescriptionField(String text) {
		WebElement descriptionField = findElementBySelector(this.descriptionTextField);
		sendKeysToWebElement(descriptionField, text);
	}
	
	public void clickLinkCreateProduct() {
		WebElement createLink = findElementBySelector(this.createButton);
		clickElement(createLink);
	}
	
	public Boolean isBarCodeFieldRequired() {
		WebElement productStore = findElementBySelector(this.barCodeTextField);
		String requiredAttr = getElementAttributeByPropertyName(productStore, "required");
		if(requiredAttr.equalsIgnoreCase("true"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public Boolean isDescriptionFieldRequired() {
		WebElement productStore = findElementBySelector(this.descriptionTextField);
		String requiredAttr = getElementAttributeByPropertyName(productStore, "required");
		if(requiredAttr.equalsIgnoreCase("true"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
