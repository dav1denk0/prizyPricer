package com.prizy.pricer.seleniumTests.scenarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.pages.CreateProductPage;
import com.prizy.pricer.seleniumTests.pages.IndexPage;
import com.prizy.pricer.seleniumTests.pages.ProductViewerPage;

public class TestCreateProducts {
	
	private IndexPage indexPage;
	private CreateProductPage createProductPage;
	private ProductViewerPage productViewerPage;

	@BeforeClass
	public void setEnvironment() {
		WebDriver driver = new FirefoxDriver();
		Integer timeout = new Integer(20);
		indexPage = new IndexPage(driver, timeout);
		createProductPage = new CreateProductPage(driver, timeout);
		productViewerPage = new ProductViewerPage(driver, timeout);
	}
	
	@Test
	public void createFirstProduct() {
		createProduct("", "");
	}
	
	private void createProduct(String barCode, String description) {
		indexPage.goToCreateProductPage();
		createProductPage.setTextInBarCodeField(barCode);
		createProductPage.setTextInDescriptionField(description);
		createProductPage.clickLinkCreateProduct();
		
		Assert.assertFalse(createProductPage.isErrorDisplayedInPage(), 
				"There was an error in the creation of the product");
		String productMessageText = productViewerPage.getProductMessageText();
		Assert.assertTrue(productMessageText.contains("creado"),
				"The message shown in the page should say that the product was created, but instead is: "
				+ productMessageText);
	}
}
