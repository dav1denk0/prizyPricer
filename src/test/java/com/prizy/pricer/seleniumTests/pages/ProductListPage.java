package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ProductListPage extends IndexPage {

	public static final String PAGE_PATH = "product/list";
	private By productList = By.id("list-product");
	
	public ProductListPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
}
