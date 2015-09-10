package com.prizy.pricer.seleniumTests.scenarios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.EnvironmentData;
import com.prizy.pricer.seleniumTests.domain.PriceStub;
import com.prizy.pricer.seleniumTests.domain.ProductStub;
import com.prizy.pricer.seleniumTests.pages.ProductListPage;
import com.prizy.pricer.seleniumTests.pages.ProductLoaderPage;
import com.prizy.pricer.seleniumTests.pages.ViewProductPage;
import com.prizy.pricer.seleniumTests.pages.ViewProductRegistryPage;
import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ProductLoaderTest {

	private WebDriver driver;
	private IndexPage indexPage;
	private ProductLoaderPage productLoaderPage;
	private ViewProductRegistryPage viewProductRegistryPage;
	private ViewProductPage viewProductPage;
	private ProductListPage productListPage;
	private Map<String, String> loaderValues;
	
	@BeforeTest
	public void setEnvironment() {
		this.driver = EnvironmentData.getEnvironment();
		EnvironmentData.getBaseUrl();
		Integer timeout = EnvironmentData.getTimeout();
		this.indexPage = new IndexPage(this.driver, timeout);
		this.productLoaderPage = new ProductLoaderPage(this.driver, timeout);
		this.viewProductRegistryPage = new ViewProductRegistryPage(this.driver, timeout);
		this.productListPage = new ProductListPage(driver, timeout);
	}
	
	@BeforeMethod
	public void beforeTestActions() {
		this.indexPage.goToProductLoaderPage();
	}
	
	@Test(priority = 1)
	public void loadPricesForCreatedProducts() {
		List<ProductStub> loadedProducts = EnvironmentData.getSavedProducts();
		if(loadedProducts != null && loadedProducts.size() > 0) {
			for(ProductStub product : loadedProducts) {
				String productSelection = "Bar code: " + product.getBarCode() + ", Description: " + product.getDescription();
				for(int i=0; i<10; i++) {
					this.loaderValues = new HashMap<String, String>();
					this.loaderValues.put("Product", productSelection);
					String storeName = EnvironmentData.getRandomStore();
					BigDecimal productPrice = EnvironmentData.getRandomPriceForProduct();
					String productNote = EnvironmentData.getRandomProductNote();
					loadProductPrice(productSelection, storeName, productPrice, productNote);
					String registryMessageText = viewProductRegistryPage.getMessageText();
					Assert.assertTrue(registryMessageText.contains("creado"),
							"The message shown in the page should say that the product registry was created, but instead is: "
							+ registryMessageText);
					List<String> errors = viewProductRegistryPage.validateCreatedPriceRecord(this.loaderValues);
					Assert.assertNull(errors, "Assertion Error " + errors.get(0));
					loaderValues = null;
					PriceStub createdPrice = new PriceStub();
					createdPrice.setStore(storeName);
					createdPrice.setPrice(productPrice);
					createdPrice.setNotes(productNote);
					product.addPrice(createdPrice);
					product.addPriceValue(productPrice);
					this.viewProductRegistryPage.clickCreateNewRegistry();
				}
			}
		}else
			Assert.assertTrue(false, "There's no generated products and it's impossible to load it's prices");
	}
	
	@Test(priority = 2)
	public void loadPriceWithoutStoreField() {
		Assert.assertTrue(productLoaderPage.isStoreFieldRequired(), "Store text field should be required");
	}
	
	@Test(priority = 3)
	public void loadPriceWithoutPriceField() {
		Assert.assertTrue(productLoaderPage.isPriceFieldRequired(), "Price text field should be required");
	}
	
	@Test(priority = 4)
	public void verifyProductDetailsInList() {
		this.indexPage.goToHomePage();
		List<ProductStub> savedProducts = EnvironmentData.getSavedProducts();
		if(savedProducts != null && savedProducts.size() > 0) {
			for(ProductStub product : savedProducts) {
				this.indexPage.findProduct(product.getBarCode());
				this.productListPage.accessToProductDetails(product.getBarCode());
				this.loaderValues = new HashMap<String, String>();
				this.loaderValues.put("Bar Code", product.getBarCode());
				this.loaderValues.put("Description", product.getDescription());
				product.setAvgPrice();
				String averagePrice = product.getAvgPrice().toString();
				this.loaderValues.put("Average Price", averagePrice);
				product.setLowestPrice();
				String lowestPrice = product.getLowestPrice().toString();
				this.loaderValues.put("Lowest Price", lowestPrice);
				product.setIdealPrice();
				String idealPrice = product.getIdealPrice().toString();
				this.loaderValues.put(("Ideal Price"), idealPrice);
				String numberOfPrices = Integer.toString(product.getPrices().size());
				this.loaderValues.put("Number of Prices", numberOfPrices);
				List<String> errors = viewProductPage.validateCreatedProduct(this.loaderValues);
				Assert.assertNull(errors, "Assertion Error " + errors.get(0));
				this.loaderValues = null;
			}
		}else
			Assert.assertTrue(false, "There's no product saved to check in the products list");
	}
	
	@AfterMethod
	public void afterMethodActions() {
		indexPage.goToHomePage();
	}
	
	@AfterTest
	public void afterTestActions() {
		this.driver.quit();
	}
	
	private void loadProductPrice(String productSelection, String storeName, 
			BigDecimal productPrice, String productNote) {
		this.productLoaderPage.selectProductFromDropDown(productSelection);
		this.productLoaderPage.setStoreName(storeName);
		this.loaderValues.put("Store", storeName);
		this.productLoaderPage.setProductPrice(productPrice);
		this.loaderValues.put("Price", productPrice.toString());
		this.productLoaderPage.setProductNote(productNote);
		this.loaderValues.put("Notes", productNote);
		this.productLoaderPage.createPriceRecord();
		
	}
}
