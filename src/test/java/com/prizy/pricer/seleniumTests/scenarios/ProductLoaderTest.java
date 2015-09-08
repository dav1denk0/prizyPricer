package com.prizy.pricer.seleniumTests.scenarios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.EnvironmentData;
import com.prizy.pricer.seleniumTests.pages.ProductLoaderPage;
import com.prizy.pricer.seleniumTests.pages.ViewProductRegistryPage;
import com.prizy.pricer.seleniumTests.pages.common.IndexPage;

public class ProductLoaderTest {

	private IndexPage indexPage;
	private ProductLoaderPage productLoaderPage;
	private ViewProductRegistryPage viewProductRegistryPage;
	private Map<String, String> loaderValues;
	
	@BeforeClass
	public void setEnvironment() {
		WebDriver driver = new FirefoxDriver();
		driver.get(EnvironmentData.BASE_URL);
		Integer timeout = EnvironmentData.TIMEOUT;
		indexPage = new IndexPage(driver, timeout);
		productLoaderPage = new ProductLoaderPage(driver, timeout);
		viewProductRegistryPage = new ViewProductRegistryPage(driver, timeout);
	}
	
	@BeforeTest
	public void beforeTestActions() {
		this.loaderValues = new HashMap<String, String>();
	}
	
	@Test
	public void loadPricesForCreatedProducts() {
		indexPage.goToProductLoaderPage();
		HashMap<String, String> loadedProducts = EnvironmentData.getSavedProducts();
		Iterator<HashMap.Entry<String, String>> it = loadedProducts.entrySet().iterator();
		while(it.hasNext()) {
			HashMap.Entry<String, String> entry = it.next();
			String productSelection = "Bar Code: " + entry.getKey() + ", Description: " + entry.getValue();
			this.loaderValues.put("Product", productSelection);
			for(int i=0; i<10; i++) {
				loadProductPrice(productSelection);
				String registryMessageText = viewProductRegistryPage.getMessageText();
				Assert.assertTrue(registryMessageText.contains("creado"),
						"The message shown in the page should say that the product registry was created, but instead is: "
						+ registryMessageText);
				Assert.assertTrue(viewProductRegistryPage.validateCreatedRecordProperty(this.loaderValues),
						"The Product Registry Viewer Page is not showing all the product information");
				viewProductRegistryPage.clickCreateNewRegistry();
			}
		}
	}
	
	@Test
	public void validateRequiredFieldsInLoadPricesPage() {
		indexPage.goToProductLoaderPage();
		Assert.assertTrue(productLoaderPage.isStoreFieldRequired(), "Store text field should be required");
		Assert.assertTrue(productLoaderPage.isPriceFieldRequired(), "Price text field should be required");
	}
	
	@AfterTest
	public void afterTestActions() {
		this.loaderValues.clear();
	}
	
	private void loadProductPrice(String productSelection) {
		productLoaderPage.selectProductFromDropDown(productSelection);
		String storeName = EnvironmentData.getRandomStore();
		productLoaderPage.setStoreName(storeName);
		this.loaderValues.put("Store", storeName);
		BigDecimal productPrice = EnvironmentData.getRandomPriceForProduct();
		productLoaderPage.setProductPrice(productPrice);
		this.loaderValues.put("Price", productPrice.toString());
		String productNote = EnvironmentData.getRandomProductNote();
		productLoaderPage.setProductNote(productNote);
		this.loaderValues.put("Notes", productNote);
		productLoaderPage.createPriceRecord();
		
	}
}
