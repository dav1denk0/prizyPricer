package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductLoaderPage extends IndexPage {

	public static final String PAGE_PATH = "registry/create";
	private By productDropDown = By.id("product");
	private By storeTextField = By.id("store");
	private By notesTextField = By.id("notes");
	private By createButton = By.id("create");
	
	public ProductLoaderPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
}
