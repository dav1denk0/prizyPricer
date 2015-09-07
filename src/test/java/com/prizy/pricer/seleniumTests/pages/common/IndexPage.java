package com.prizy.pricer.seleniumTests.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IndexPage extends Page {
	
	private By homeLink = By.className("header-main");
	private By findProductTextField = By.id("q");
	private By findProductButton = By.cssSelector("#productSearchForm > input[type='submit']:nth-child(2)");
	private By productListLink = By.cssSelector("#controller-list > ul > li:nth-child(2) > a");
	private By productLoaderLink = By.cssSelector("#controller-list > ul > li:nth-child(3) > a");
	private By createNewProductLink = By.cssSelector("#controller-list > ul > li:nth-child(4) > a");
	
	public IndexPage(WebDriver driver, Integer timeout) {
		super(driver, timeout);
	}
	
	public void goToHomePage() {
		WebElement linkToHomePage = findElementBySelector(homeLink);
		clickElement(linkToHomePage);
	}
	
	public void findProduct(String barCode) {
		WebElement searchField = findElementBySelector(this.findProductTextField);
		WebElement searchButton = findElementBySelector(this.findProductButton);
		sendKeysToWebElement(searchField, barCode);
		clickElement(searchButton);
	}
	
	public void goToProductListPage() {
		WebElement linkToProductListPage = findElementBySelector(this.productListLink);
		clickElement(linkToProductListPage);
	}
	
	public void goToProductLoaderPage() {
		WebElement linkToProductLoaderPage = findElementBySelector(this.productLoaderLink);
		clickElement(linkToProductLoaderPage);
	}
	
	public void goToCreateProductPage() {
		WebElement linkToCreateNewProductPage = findElementBySelector(this.createNewProductLink);
		clickElement(linkToCreateNewProductPage);
	}

}
