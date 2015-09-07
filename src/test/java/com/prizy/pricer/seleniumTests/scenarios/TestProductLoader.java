package com.prizy.pricer.seleniumTests.scenarios;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.prizy.pricer.seleniumTests.EnvironmentData;
import com.prizy.pricer.seleniumTests.pages.IndexPage;
import com.prizy.pricer.seleniumTests.pages.ProductLoaderPage;
import com.prizy.pricer.seleniumTests.pages.ShowRegistryPage;

public class TestProductLoader {

	private IndexPage indexPage;
	private ProductLoaderPage productLoaderPage;
	private ShowRegistryPage showRegistryPage;
	
	@BeforeClass
	public void setEnvironment() {
		WebDriver driver = new FirefoxDriver();
		driver.get(EnvironmentData.BASE_URL);
		Integer timeout = EnvironmentData.TIMEOUT;
		indexPage = new IndexPage(driver, timeout);
		productLoaderPage = new ProductLoaderPage(driver, timeout);
		showRegistryPage = new ShowRegistryPage(driver, timeout);
	}
	
	@Test
	public void loadPricesForCreatedProducts() {
		indexPage.goToProductLoaderPage();
		HashMap<String, String> loadedProducts = EnvironmentData.getSavedProducts();
		Iterator<HashMap.Entry<String, String>> it = loadedProducts.entrySet().iterator();
		while(it.hasNext()) {
			HashMap.Entry<String, String> entry = it.next();
			String productSelection = "Bar Code: " + entry.getKey() + ", Description: " + entry.getValue();
			for(int i=0; i<10; i++) {
				loadProductPrice(productSelection);
			}
		}
	}
	
	private void loadProductPrice(String productSelection) {
		productLoaderPage.selectProductFromDropDown(productSelection);
		String store = EnvironmentData.getRandomStore();
		productLoaderPage.setStoreName(store);
		BigDecimal price = EnvironmentData.getRandomPriceForProduct();
		productLoaderPage.setProductPrice(price);
		String note = EnvironmentData.getRandomProductNote();
		productLoaderPage.setProductNote(note);
		productLoaderPage.createPriceRecord();
	}
}
