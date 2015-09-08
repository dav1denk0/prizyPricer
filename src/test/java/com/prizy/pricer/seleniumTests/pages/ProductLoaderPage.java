package com.prizy.pricer.seleniumTests.pages;

import java.math.BigDecimal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ProductLoaderPage extends IndexPage {

	public static final String PAGE_PATH = "registry/create";
	private By productDropDown = By.id("product");
	private By storeTextField = By.id("store");
	private By priceTextField = By.id("price");
	private By notesTextField = By.id("notes");
	private By createButton = By.id("create");
	
	public ProductLoaderPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void selectProductFromDropDown(String optionText) {
		WebElement productDropDown = findElementBySelector(this.productDropDown);
		selectDropdownOptionByText(productDropDown, optionText);
	}
	
	public void setStoreName(String storeName) {
		WebElement productStore = findElementBySelector(this.storeTextField);
		sendKeysToWebElement(productStore, storeName);
	}
	
	public void setProductPrice(BigDecimal price) {
		WebElement productPrice = findElementBySelector(this.priceTextField);
		String priceText = price.toString();
		sendKeysToWebElement(productPrice, priceText);
	}
	
	public void setProductNote(String note) {
		WebElement productNote = findElementBySelector(this.notesTextField);
		sendKeysToWebElement(productNote, note);
	}
	
	public void createPriceRecord() {
		WebElement createRecord = findElementBySelector(this.createButton);
		clickElement(createRecord);
	}
	
	public Boolean isStoreFieldRequired() {
		WebElement productStore = findElementBySelector(this.storeTextField);
		String requiredAttr = getElementAttributeByPropertyName(productStore, "required");
		if(requiredAttr.equalsIgnoreCase("true"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public Boolean isPriceFieldRequired() {
		WebElement productPrice = findElementBySelector(this.priceTextField);
		String requiredAttr = getElementAttributeByPropertyName(productPrice, "required");
		if(requiredAttr.equalsIgnoreCase("true"))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
}
