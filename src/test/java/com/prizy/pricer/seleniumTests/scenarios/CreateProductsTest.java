package com.prizy.pricer.seleniumTests.scenarios;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.EnvironmentData;
import com.prizy.pricer.seleniumTests.pages.CreateProductPage;
import com.prizy.pricer.seleniumTests.pages.ViewProductPage;
import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class CreateProductsTest {
	
	private IndexPage indexPage;
	private CreateProductPage createProductPage;
	private ViewProductPage productViewerPage;

	@BeforeClass
	public void setEnvironment() {
		WebDriver driver = new FirefoxDriver();
		driver.get(EnvironmentData.BASE_URL);
		Integer timeout = EnvironmentData.TIMEOUT;
		indexPage = new IndexPage(driver, timeout);
		createProductPage = new CreateProductPage(driver, timeout);
		productViewerPage = new ViewProductPage(driver, timeout);
	}
	
	@Test
	public void createPrizyPricerProducts() {
		indexPage.goToCreateProductPage();
		for(int i=0; i<3; i++) {
			String barCode = EnvironmentData.generateProductBarCode();
			String description = EnvironmentData.getRandomProductDescription();
			createProduct(barCode, description);
			Assert.assertFalse(createProductPage.isErrorDisplayedInPage(), 
					"There was an error in the creation of the product");
			String productMessageText = productViewerPage.getMessageText();
			Assert.assertTrue(productMessageText.contains("creado"),
					"The message shown in the page should say that the product was created, but instead is: "
					+ productMessageText);
			EnvironmentData.setSavedProduct(barCode, description);
			indexPage.goToHomePage(); // CAMBIAR
		}
	}
	
	@Test
	public void createPrizyPricerProductWithoutCodeBar() {
		String barCode = "";
		String description = EnvironmentData.getRandomProductDescription();
		createProduct(barCode, description);
	}
	
	private void createProduct(String barCode, String description) {
		createProductPage.setTextInBarCodeField(barCode);
		createProductPage.setTextInDescriptionField(description);
		createProductPage.clickLinkCreateProduct();
	}
}
