package com.prizy.pricer.seleniumTests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.prizy.pricer.seleniumTests.domain.ProductStub;

public class EnvironmentData {

	public static final String BASE_URL = "http://tomcat.jcor.com.ar/prizy-pricer/";
	public static final Integer TIMEOUT = new Integer(20);
	public static List<ProductStub> savedProducts = new ArrayList<ProductStub>();
	private static WebDriver driver = new FirefoxDriver();
	
	public static WebDriver getEnvironment() {
		return driver;
	}
	
	public static void getBaseUrl() {
		driver.get(EnvironmentData.BASE_URL);
	}
	
	public static Integer getTimeout() {
		return EnvironmentData.TIMEOUT;
	}
	
	public static String generateProductBarCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	private static List<String> setProductDescriptions() {
		List<String> descriptions = new ArrayList<String>();
		descriptions.add("Oreos");
		descriptions.add("Dr. Pepper Soda");
		descriptions.add("Snickers");
		descriptions.add("M&Ms");
		descriptions.add("Skittles");
		descriptions.add("Nutella");
		descriptions.add("Peanut Butter");
		descriptions.add("Toblerone");
		descriptions.add("Marshmallows");
		descriptions.add("Black Coffee");
		return descriptions;
	}
	
	public static String getRandomProductDescription() {
		List<String> descriptions = setProductDescriptions();
		Random random = new Random();
		Integer randomNumber = random.nextInt(descriptions.size());
		return descriptions.get(randomNumber);
	}
	
	public static void setSavedProduct(ProductStub product) {
		savedProducts.add(product);
	}
	
	public static List<ProductStub> getSavedProducts() {
		return savedProducts;
	}
	
	public static BigDecimal getRandomPriceForProduct() {
		BigDecimal minRange = new BigDecimal(0);
		BigDecimal maxRange = new BigDecimal(50);
		BigDecimal randomPrice = minRange.add(new BigDecimal(Math.random()).multiply(maxRange.subtract(minRange)));
		return randomPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	private static List<String> setStoreNames() {
		List<String> stores = new ArrayList<String>();
		stores.add("WalMart");
		stores.add("E-Market");
		stores.add("Carrefour");
		stores.add("Oil Station");
		stores.add("Serve Yourself");
		return stores;
	}
	
	public static String getRandomStore() {
		List<String> stores = setStoreNames();
		Random random = new Random();
		Integer randomNumber = random.nextInt(stores.size());
		return stores.get(randomNumber);
	}
	
	private static List<String> setProductNotes() {
		List<String> notes = new ArrayList<String>();
		notes.add("Product's price was increased from last observation");
		notes.add("Product's price was decreased from last observation");
		notes.add("There is a lack of stock for this product");
		notes.add("This product is not selling enough");
		notes.add("This product was renewed and it has a new packaging");
		return notes;
	}
	
	public static String getRandomProductNote() {
		List<String> notes = setProductNotes();
		Random random = new Random();
		Integer randomNumber = random.nextInt(notes.size());
		return notes.get(randomNumber);
	}
	
}
