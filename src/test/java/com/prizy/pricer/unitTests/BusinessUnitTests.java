package com.prizy.pricer.unitTests;

import org.testng.annotations.Test;

public class BusinessUnitTests {
	
	@Test
	public void getNullProduct() {
		/*	
		 * In this case I would try to get properties from a 
		 * null product and Assert that a NullPointerException is thrown
		 * We can make the Assert exception with the Hamcrest library for asserts
		 * or a expected exception test with TestNG.
		 */
		System.out.println("A NullPointerException should be "
				+ "catched in an Assert statement");
	}
	
	@Test
	public void getNullPrice() {
		/*
		 * The same implementation that the above test, I would check that a 
		 * NullPointerException is thrown with an Assert match
		 * We could try to access to a Price's attribute like store or notes. 
		 * We can use Hamcrest library for Asserts or a expected exception test with TestNG
		 */
		System.out.println("A NullPointerException should be "
				+ "catched in an Assert statement");
	}
	
	@Test
	public void getNonExistentProduct() {
		/*
		 * I would perform an Assertion that a Null object is retrieved 
		 * when I try to get a non existent product
		 */
		System.out.println("Assert that a Null is retrieved");
	}
	
	@Test
	public void getNonExistentPrice() {
		/*
		 * I would perform an Assertion that a Null object is retrieved 
		 * when I try to get a non existent product 
		 */
		System.out.println("Assert that a Null is retrieved");
	}
	
	@Test
	public void deleteNonExistentProduct() {
		/*
		 * I would perform an Assertion that matches an SQL Error if the test 
		 * is against the database or a UI error if the test
		 * is for the UI
		 */
		System.out.println("Assert that an attempt to delete a non "
				+ "existent product throws an exception");
	}
	
	@Test
	public void deleteNonExistentPrice() {
		/*
		 * I would perform an Assertion that matches an SQL Error if the test 
		 * is against the database or a UI error if the test is for the UI
		 */
		System.out.println("Assert that an attempt to delete a non existent"
				+ " price throws an exception");
	}
	
	@Test
	public void saveProductPriceEqualOrLessThanZero() {
		/*
		 * In this case, we cannot save a product with a negative price or a 
		 * price equals to zero (0). We should Assert that a business rule
		 * (maybe a customized exception) is preventing to save forbidden prices. 
		 * We can use Hamcrest library to test this cases
		 */
		System.out.println("Assert that an error is thrown when "
				+ "attempting to save forbidden prices");
	}
	
	@Test
	public void saveOrUpdateNullPrice() {
		/*
		 * We should perform an Assertion that an exception is raised
		 *  when we try to save null prices.
		 */
		System.out.println("Check that Null prices are not saved in "
				+ "the database and exceptions are thrown");
	}
	
	@Test
	public void saveOrUpdatePricesWithoutMandatoryFields() {
		/*
		 * We should perform a set of Assertions that checks all the required
		 * fields for Prices and throw the expected errors.
		 * We cannot save prices with missing mandatory data.
		 */
		System.out.println("Check that missing attributes from Prices raise "
				+ "errors at the time to save them to the database");
	}
	
	@Test
	public void saveOrUpdateNullProduct() {
		/*
		 * We should perform an Assertion that an exception is raised
		 * when we try to save null products.
		 */
		System.out.println("Check that Null products are not "
				+ "saved in the database and exceptions are thrown");
	}
	
	@Test
	public void saveOrUpdateProductssWithoutMandatoryFields() {
		/*
		 * We should perform a set of Assertions that checks all the 
		 * required fields for Products and throw the expected errors.
		 * We cannot save products with missing mandatory data.
		 */
		System.out.println("Check that missing attributes from Products "
				+ "raise errors at the time to save them to the database");
	}
	
	@Test
	public void calculateIdealPriceWithTenThousandsOfPrices() {
		/*
		 * In this case we can make assertions with timeouts to ensure that 
		 * the method is working properly with a large number of prices
		 * Also, we can assert that Exceptions like OutOfMemory for 
		 * JVM are not thrown when the method is executed
		 * (We can use JMeter as well to test this)
		 */
		System.out.println("Attempt to calculate ideal prices with a large "
				+ "number of products and a timeout set up");
	}
	
	@Test
	public void calculateIdealPriceNullCollectionOfPrices() {
		/*
		 * In this case an exception should be thrown by the method and 
		 * we can catch it with an Assertion from Hamcrest or a expected exception test with TestNG
		 */
		System.out.println("Attempt to calculate ideal prices with a null "
				+ "collection of prices should be thrown an exception");
	}
	
	@Test
	public void calculateIdealPriceWithoutAmmountOfProducts() {
		/*
		 * In this case we cannot divide by zero when we're calculating the ideal price
		 * Since we're using BigDecimal we should obtain an AritmeticException or something similar
		 * that we can catch with an Assertion from Hamcrest or a expected exception test with TestNG
		 */
		System.out.println("Attempt to calculate the ideal price dividing by zero");
	}
}
