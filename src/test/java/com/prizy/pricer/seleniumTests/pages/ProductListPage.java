package com.prizy.pricer.seleniumTests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ProductListPage extends IndexPage {

	public static final String PAGE_PATH = "product/list";
	private By productList = By.id("list-product");
	
	public ProductListPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void accessToProductDetails(String barCode) {
		WebElement list = findElementBySelector(this.productList);
		WebElement productLink = list.findElement(By.linkText(barCode));
		productLink.click();
	}
}
