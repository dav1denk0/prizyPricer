package com.prizy.pricer.seleniumTests.scenarios;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.EnvironmentData;
import com.prizy.pricer.seleniumTests.domain.ProductStub;
import com.prizy.pricer.seleniumTests.pages.CreateProductPage;
import com.prizy.pricer.seleniumTests.pages.ViewProductPage;
import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class CreateProductsTest {
	
	private WebDriver driver;
	private IndexPage indexPage;
	private CreateProductPage createProductPage;
	private ViewProductPage viewProductPage;

	@BeforeTest
	public void setEnvironment() {
		this.driver = EnvironmentData.getEnvironment();
		EnvironmentData.getBaseUrl();
		Integer timeout = EnvironmentData.getTimeout();
		this.indexPage = new IndexPage(driver, timeout);
		this.createProductPage = new CreateProductPage(driver, timeout);
		this.viewProductPage = new ViewProductPage(driver, timeout);
	}
	
	@BeforeMethod
	public void beforeTestActions() {
		this.indexPage.goToCreateProductPage();
	}
	
	@Test(priority = 1)
	public void createPrizyPricerProducts() {
		for(int i=0; i<3; i++) {
			String barCode = EnvironmentData.generateProductBarCode();
			String description = EnvironmentData.getRandomProductDescription();
			createProduct(barCode, description);
			String productMessageText = this.viewProductPage.getMessageText();
			Assert.assertTrue(productMessageText.contains("creado"),
					"The message shown in the page should say that the product was created, but instead is: "
					+ productMessageText);
			ProductStub createdProduct = new ProductStub();
			createdProduct.setBarCode(barCode);
			createdProduct.setDescription(description);
			EnvironmentData.setSavedProduct(createdProduct);
			this.viewProductPage.clickCreateNewProduct();
		}
	}
	
	@Test(priority = 2)
	public void createPrizyPricerProductWithoutBarCode() {
		Assert.assertTrue(this.createProductPage.isBarCodeFieldRequired(), "Bar Code text field should be required");
	}
	
	@Test(priority = 3)
	public void createPrizyPricerProductWithoutDescription() {
		Assert.assertTrue(this.createProductPage.isDescriptionFieldRequired(), "Description text field should be required");
	}
	
	@AfterMethod
	public void afterTestActions() {
		this.indexPage.goToHomePage();
	}
	
	@AfterTest
	public void afterSuiteActions() {
		this.driver.quit();
	}
	
	private void createProduct(String barCode, String description) {
		this.createProductPage.setTextInBarCodeField(barCode);
		this.createProductPage.setTextInDescriptionField(description);
		this.createProductPage.clickLinkCreateProduct();
	}
}
