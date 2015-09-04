package com.prizy.pricer.seleniumTests.pages;

import java.util.List;

import org.apache.bcel.verifier.exc.AssertionViolatedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateProductPage extends IndexPage {

	public static final String PAGE_PATH = "product/create";
	private By barCodeTextField = By.id("barCode");
	private By descriptionTextField = By.id("description");
	private By createButton = By.id("create");
	private By errorList = By.cssSelector(".errors > li");
	
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
	
	public Boolean isErrorDisplayedInPage() {
		try {
			List<WebElement> errors = findAllElementsBySelector(this.errorList);
			if(errors != null && errors.size() > 0) {
				String pageErrorMessage = "";
				for(WebElement error : errors) {
					pageErrorMessage = "\n" + getTextFromElement(error);
				}
				throw new AssertionViolatedException(pageErrorMessage);
			}
			return Boolean.TRUE;
		}catch(NoSuchElementException e) {
			return Boolean.FALSE;
		}
	}
}
